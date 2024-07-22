package gg.quartzdev.qxpboosts.util;

import gg.quartzdev.qxpboosts.qPermission;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachmentInfo;

import java.text.DecimalFormat;
import java.util.HashSet;
import java.util.Set;

public class BoostUtil
{

    public static void notifyPlayerGotBoost(
            Player player, double multiplier, boolean sendsChat, boolean sendsActionBar, Sound sound)
    {

        DecimalFormat format = new DecimalFormat("0.###");
        String strMultiplier = format.format(multiplier);

        if(sendsChat)
        {
            String message = Messages.XP_CHAT_GAIN.get()
                    .replaceAll("<player>", player.getName())
                    .replaceAll("<multiplier>", strMultiplier);
            qUtil.sendMessage(player, message);
        }

        if(sendsActionBar)
        {
            String message = Messages.XP_ACTIONBAR_GAIN.get()
                    .replaceAll("<player>", player.getName())
                    .replaceAll("<multiplier>", strMultiplier);
            qUtil.sendActionBar(player, message);
        }

        if(sound != null)
        {
            player.playSound(player.getLocation(), sound, 1.0F, 1.0F);
        }

    }

    public static Set<String> getBoostNames(Player player)
    {
        Set<String> boostNames = new HashSet<>();

//        Gets each boost name from a player's permissions
        for(PermissionAttachmentInfo permInfo : player.getEffectivePermissions())
        {
            if(!permInfo.getValue()) continue;
            String perm = permInfo.getPermission();
            if(perm.startsWith(qPermission.BOOST.getPermission()))
            {
                String boostName = perm.replaceFirst(qPermission.BOOST.getPermission(), "");
                boostNames.add(boostName);
            }
        }

//        Add default boost if none are found
        if(boostNames.isEmpty())
        {
            boostNames.add("default");
        }

        return boostNames;
    }
}
