package gg.quartzdev.qexpboosts.listeners;

import com.destroystokyo.paper.event.player.PlayerPickupExperienceEvent;
import gg.quartzdev.qexpboosts.qConfig;
import gg.quartzdev.qexpboosts.qExpBoosts;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.ExperienceOrb;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.permissions.PermissionAttachmentInfo;

public class PlayerPickupExpListener implements Listener {

    qExpBoosts plugin;
    qConfig config;

    public PlayerPickupExpListener(){
        this.plugin = qExpBoosts.getInstance();
        this.config = plugin.config;
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



        for(PermissionAttachmentInfo perm : player.getEffectivePermissions()){
            if(!perm.getValue()) continue;

            if(perm.getPermission().startsWith("qexpboost.boost.")){

            }
        }

    }


}
