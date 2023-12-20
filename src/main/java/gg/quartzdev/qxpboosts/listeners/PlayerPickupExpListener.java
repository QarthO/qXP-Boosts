package gg.quartzdev.qxpboosts.listeners;

import com.destroystokyo.paper.event.player.PlayerPickupExperienceEvent;
import gg.quartzdev.qxpboosts.boost.Boost;
import gg.quartzdev.qxpboosts.boost.BoostManager;
import gg.quartzdev.qxpboosts.storage.qConfig;
import gg.quartzdev.qxpboosts.qPermission;
import gg.quartzdev.qxpboosts.qXpBoosts;
import gg.quartzdev.qxpboosts.util.XpUtil;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.ExperienceOrb;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

import java.util.UUID;

public class PlayerPickupExpListener implements Listener {

    qXpBoosts plugin;
    qConfig config;
    BoostManager boostManager;

    public PlayerPickupExpListener(){
        this.plugin = qXpBoosts.getInstance();
        this.config = plugin.config;
        this.boostManager = plugin.boostManager;
    }


    @EventHandler
    public void onPlayerPickupExp(PlayerPickupExperienceEvent event){

        Player player = event.getPlayer();
        World world = player.getWorld();

//        World check
        if(config.isDisabledWorld(world)) {
            return;
        }

        if(config.requiresPermission() && !player.hasPermission(qPermission.PLAYER.getPermission())){
            return;
        }

        ExperienceOrb xpOrb = event.getExperienceOrb();
        ExperienceOrb.SpawnReason xpSource = xpOrb.getSpawnReason();

        if(!config.isBoostedXpSource(xpSource)){
            return;
        }

//        XP dropped from mobs from a spawner aren't boosted
//        TODO: Add config option for this
        if(xpSource.equals(ExperienceOrb.SpawnReason.ENTITY_DEATH)){
            UUID entityId = xpOrb.getSourceEntityId();
            if(entityId != null){
                Entity entity = Bukkit.getEntity(entityId);
                if(entity != null){
                    CreatureSpawnEvent.SpawnReason entitySpawnReason = entity.getEntitySpawnReason();
                    player.sendMessage(entitySpawnReason.name());
                    if(entitySpawnReason.equals(CreatureSpawnEvent.SpawnReason.SPAWNER)){
                        return;
                    }
                }
            }
        }

        Boost boost = boostManager.getBoost(player);

        if(!boostManager.isActive(boost)){
            return;
        }

        int amount = xpOrb.getExperience();

        XpUtil.givePlayer(player, amount, boost, false, true, null);

    }


}
