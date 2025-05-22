package gg.quartzdev.qxpboosts.listeners;

import com.destroystokyo.paper.event.player.PlayerPickupExperienceEvent;
import gg.quartzdev.qxpboosts.boost.Boost;
import gg.quartzdev.qxpboosts.boost.BoostManager;
import gg.quartzdev.qxpboosts.qPermission;
import gg.quartzdev.qxpboosts.qXpBoosts;
import gg.quartzdev.qxpboosts.storage.qConfig;
import gg.quartzdev.qxpboosts.util.BoostUtil;
import gg.quartzdev.qxpboosts.util.Messages;
import gg.quartzdev.qxpboosts.util.qUtil;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.ExperienceOrb;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class PlayerPickupExpListener implements Listener
{

    qXpBoosts plugin;
    qConfig config;
    BoostManager boostManager;

    public PlayerPickupExpListener()
    {
        this.plugin = qXpBoosts.getInstance();
        this.config = plugin.config;
        this.boostManager = plugin.boostManager;
    }


    @EventHandler(ignoreCancelled = true)
    public void onPlayerPickupExp(PlayerPickupExperienceEvent event)
    {

        Player player = event.getPlayer();
        World world = player.getWorld();

//        World check
        if(config.isDisabledWorld(world))
        {
            return;
        }

//        Permission check
        if(config.requiresPermission() && !player.hasPermission(qPermission.GROUP_PLAYER.getPermission()))
        {
            return;
        }

        Set<String> boostNames = BoostUtil.getBoostNames(player);

        ExperienceOrb xpOrb = event.getExperienceOrb();

        double multiplier = 0;
        double highestMultiplier = 0;
        boolean sendActionBar = false;
        boolean sendChat = false;
        Sound sound = null;

        for(String boostName : boostNames)
        {
            Boost boost = this.boostManager.getBoost(boostName);
            if(boost == null)
            {
                qUtil.sendMessage(Bukkit.getConsoleSender(), Messages.ERROR_BOOST_NOT_FOUND.parse("boost", boostName));
                continue;
            }

//            Is the boost active
            if(!boost.isActive())
            {
                continue;
            }

//            RNG if the boost will run
            if(boost.getChance() < 100)
            {
                ThreadLocalRandom random = ThreadLocalRandom.current();
                float rng = random.nextFloat(0.01F, 100.0F);
                if(rng > boost.getChance())
                {
                    continue;
                }
            }

//            Checks the xp source
            ExperienceOrb.SpawnReason xpSource = xpOrb.getSpawnReason();
            if(!boost.xpSources.contains(xpSource))
            {
                continue;
            }

//            If the xp came from a mob, check the mob source
            if(xpSource.equals(ExperienceOrb.SpawnReason.ENTITY_DEATH))
            {
                UUID entityId = xpOrb.getSourceEntityId();
                if(entityId != null)
                {
                    Entity entity = Bukkit.getEntity(entityId);
                    if(entity != null)
                    {
                        CreatureSpawnEvent.SpawnReason entitySpawnReason = entity.getEntitySpawnReason();
                        if(!boost.mobSources.contains(entitySpawnReason))
                        {
                            continue;
                        }
                    }
                }
            }

//            Requirements passed all checks
            int amount = xpOrb.getExperience();

//            Running multiplier total
            multiplier += boost.getMultiplier();

//            Running notify
            if(boost.sendsChat())
            {
                sendChat = true;
            }
            if(boost.sendsActionBar())
            {
                sendActionBar = true;
            }

//            Only will play the sound of the highest multiplier
            highestMultiplier = Math.max(highestMultiplier, boost.getMultiplier());
            if(boost.getSound() != null && boost.getMultiplier() == highestMultiplier)
            {
                sound = boost.getSound();
            }

            double bonus = amount * boost.getMultiplier();
            player.giveExp((int) bonus, true);
        }

        BoostUtil.notifyPlayerGotBoost(player, multiplier, sendChat, sendActionBar, sound);

    }


}
