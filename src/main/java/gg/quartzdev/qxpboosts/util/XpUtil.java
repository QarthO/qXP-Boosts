package gg.quartzdev.qxpboosts.util;

import gg.quartzdev.qxpboosts.boost.Boost;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.text.DecimalFormat;

public class XpUtil {

    public static void givePlayer(Player player, int amountGained, Boost boost, boolean chat, boolean actionBar, Sound sound){

        double bonus = amountGained * boost.getMultiplier();
        player.giveExp((int) bonus);

        DecimalFormat format = new DecimalFormat("0.##");
        String strMultiplier = format.format(boost.getMultiplier());

        if(chat) {
            String message = Language.XP_CHAT_GAIN.toString()
                    .replaceAll("<xp>", String.valueOf(bonus))
                    .replaceAll("<player>", player.getName())
                    .replaceAll("<boost-name>", boost.getName())
                    .replaceAll("<boost-multiplier>", strMultiplier)
                    .replaceAll("<prefix>", Language.CHAT_PREFIX.name());
            qUtil.sendMessage(player, message);
        }

        if(actionBar){
            String message = Language.XP_ACTIONBAR_GAIN.toString()
                    .replaceAll("<xp>", String.valueOf(bonus))
                    .replaceAll("<player>", player.getName())
                    .replaceAll("<boost-name>", boost.getName())
                    .replaceAll("<boost-multiplier>", strMultiplier)
                    .replaceAll("<prefix>", Language.CHAT_PREFIX.name());
            qUtil.sendActionBar(player, message);
        }

        if(sound != null)
            player.playSound(player.getLocation(), sound, 1.0F, 1.0F);

    }
}
