package gg.quartzdev.qxpboosts.boost;

import gg.quartzdev.qxpboosts.qPermission;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachmentInfo;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class BoostManager {
    HashMap<String, Boost> boosts;
    HashMap<Player, Boost> playerTracker;

    Boost defaultBoost;


    public BoostManager(){
        this.boosts = new HashMap<>();
        this.playerTracker = new HashMap<>();
        this.defaultBoost = new Boost("default", 1.25);
    }

    public @NotNull Boost getBoost(Player player){

        Boost boost = playerTracker.get(player);

        if(boost == null)
            boost = new Boost()

        if(!playerTracker.containsKey(player))

        return playerTracker.get(player);
    }

    public void updateBoost(Player player){

//        if(!player.hasPermission(qPermission.PLAYER.name())) return;
//
//        Set<PermissionAttachmentInfo> perms = player.getEffectivePermissions();
//        Set<Boost> playerBoosts = new HashSet<>();
//        for(PermissionAttachmentInfo perm : perms){
//            if(!perm.getValue()) continue;
//
//            if(perm.getPermission().startsWith(qPermission.BOOST.name())){
//                String boostName = perm.getPermission().split(".")[2];
//                Boost boost = boosts.get(boostName);
//                if(boost != null) playerBoosts.add(boost);
//            }
//
//        }
    }

}
