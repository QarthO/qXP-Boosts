package gg.quartzdev.qxpboosts.listeners;

import com.destroystokyo.paper.event.player.PlayerPickupExperienceEvent;
import gg.quartzdev.qxpboosts.boost.Boost;
import gg.quartzdev.qxpboosts.boost.BoostManager;
import gg.quartzdev.qxpboosts.qConfig;
import gg.quartzdev.qxpboosts.qXpBoosts;
import gg.quartzdev.qxpboosts.util.XpUtil;
import org.bukkit.World;
import org.bukkit.entity.ExperienceOrb;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

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

        ExperienceOrb xpOrb = event.getExperienceOrb();
        ExperienceOrb.SpawnReason xpSource = xpOrb.getSpawnReason();

        if(!config.isBoostedXpSource(xpSource)){
            return;
        }

        Boost boost = boostManager.getBoost(player);

        int amount = xpOrb.getExperience();

        double multiplier = 2.0;

        XpUtil.givePlayer(player, amount, multiplier, false, true, null);

    }


}
