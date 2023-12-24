package gg.quartzdev.qxpboosts.util;

import gg.quartzdev.qxpboosts.boost.Boost;
import gg.quartzdev.qxpboosts.qPermission;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachmentInfo;

import java.text.DecimalFormat;
import java.util.HashSet;
import java.util.Set;

public class BoostUtil {

    public static void givePlayerXp(Player player, int amountGained, Boost boost){

        double bonus = amountGained * boost.getMultiplier();
        player.giveExp((int) bonus);

        DecimalFormat format = new DecimalFormat("0.###");
        String strMultiplier = format.format(boost.getMultiplier());

        if(boost.sendsChat()) {
            String message = Language.XP_CHAT_GAIN.toString()
                    .replaceAll("<xp>", String.valueOf(bonus))
                    .replaceAll("<player>", player.getName())
                    .replaceAll("<boost>", boost.getName())
                    .replaceAll("<multiplier>", strMultiplier);
            qUtil.sendMessage(player, message);
        }

        if(boost.sendsActionBar()){
            String message = Language.XP_ACTIONBAR_GAIN.toString()
                    .replaceAll("<xp>", String.valueOf(bonus))
                    .replaceAll("<player>", player.getName())
                    .replaceAll("<name>", boost.getName())
                    .replaceAll("<multiplier>", strMultiplier);
            qUtil.sendActionBar(player, message);
        }

        if(boost.getSound() != null) {
            player.playSound(player.getLocation(), boost.getSound(), 1.0F, 1.0F);
        }

    }

    public static Set<String> getBoostNames(Player player){
        Set<String> boostNames = new HashSet<>();

//        Gets each boost name from a player's permissions
        for(PermissionAttachmentInfo permInfo : player.getEffectivePermissions()){
            if(!permInfo.getValue()) continue;
            String perm = permInfo.getPermission();
            if(perm.startsWith(qPermission.BOOST.getPermission())){
                String boostName = perm.replaceFirst(qPermission.BOOST.getPermission(), "");
                boostNames.add(boostName);
            }
        }

//        Add default boost if none are found
        if(boostNames.isEmpty()){
            boostNames.add("default");
        }

        return boostNames;
    }
}
