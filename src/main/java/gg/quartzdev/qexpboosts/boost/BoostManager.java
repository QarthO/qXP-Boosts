package gg.quartzdev.qexpboosts.boost;

import gg.quartzdev.qexpboosts.qPermission;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachmentInfo;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class BoostManager {
    HashMap<String, Boost> boosts;
    HashMap<Player, Boost> playerTracker;


    public BoostManager(){
        this.boosts = new HashMap<>();
        this.playerTracker = new HashMap<>();
    }

    public void updateBoost(Player player){

        if(!player.hasPermission(qPermission.PLAYER.name())) return;

        Set<PermissionAttachmentInfo> perms = player.getEffectivePermissions();
        Set<Boost> playerBoosts = new HashSet<>();
        for(PermissionAttachmentInfo perm : perms){
            if(!perm.getValue()) continue;

            if(perm.getPermission().startsWith(qPermission.BOOST.name())){
                String boostName = perm.getPermission().split(".")[2];
                Boost boost = boosts.get(boostName);
                if(boost != null) playerBoosts.add(boost);
            }

        }
    }

}
