package gg.quartzdev.qxpboosts.util;

import gg.quartzdev.qxpboosts.boost.Boost;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.text.DecimalFormat;

public class XpUtil {

    public static void givePlayer(Player player, int amountGained, double multiplier, boolean chat, boolean actionBar, Sound sound){

        Boost boost = new Boost("", 2);

        double bonus = amountGained * multiplier;
        player.giveExp((int) bonus);
        MiniMessage mm = MiniMessage.miniMessage();

        DecimalFormat format = new DecimalFormat("0.##");
        String strMultiplier = format.format(multiplier);

        if(chat) {
            String message = Language.XP_CHAT_GAIN.toString()
                    .replaceAll("<xp>", String.valueOf(bonus))
                    .replaceAll("<player>", player.getName())
                    .replaceAll("<boost-name>", boost.getName())
                    .replaceAll("<boost-multiplier>", strMultiplier)
                    .replaceAll("<prefix>", Language.CHAT_PREFIX.name());
            player.sendMessage(mm.deserialize(message));
        }

        if(actionBar){
            String message = Language.XP_ACTIONBAR_GAIN.toString()
                    .replaceAll("<xp>", String.valueOf(bonus))
                    .replaceAll("<player>", player.getName())
                    .replaceAll("<boost-name>", boost.getName())
                    .replaceAll("<boost-multiplier>", strMultiplier)
                    .replaceAll("<prefix>", Language.CHAT_PREFIX.name());
            player.sendActionBar(mm.deserialize(message));
        }

        if(sound != null)
            player.playSound(player.getLocation(), sound, 1.0F, 1.0F);

    }
}
