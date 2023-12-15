package gg.quartzdev.qxpboosts.listeners;

import com.destroystokyo.paper.event.player.PlayerPickupExperienceEvent;
import gg.quartzdev.qxpboosts.boost.BoostManager;
import gg.quartzdev.qxpboosts.qConfig;
import gg.quartzdev.qxpboosts.qXPBoosts;
import gg.quartzdev.qxpboosts.util.ExpUtil;
import org.bukkit.World;
import org.bukkit.entity.ExperienceOrb;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class PlayerPickupExpListener implements Listener {

    qXPBoosts plugin;
    qConfig config;
    BoostManager boostManager;

    public PlayerPickupExpListener(){
        this.plugin = qXPBoosts.getInstance();
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
        ExperienceOrb.SpawnReason spawnReason = xpOrb.getSpawnReason();

        int amount = xpOrb.getExperience();

        double multiplier = 2.0;

        ExpUtil.givePlayer(player, amount, multiplier, false, true, null);

    }


}
