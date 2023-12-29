package gg.quartzdev.qxpboosts.util;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class qUtil {

    public static void sendMessage(CommandSender sender, String message){
        if(sender instanceof Player)
            sender.sendMessage(parse(message, false));
        else
            sender.sendMessage(parse(message, true));
    }

    public static void sendMessage(CommandSender sender, Messages message){
        sendMessage(sender, message.get());
    }

    public static void sendMessage(Player player, Messages message){
        sendMessage(player, message.get());
    }

    public static void sendMessage(Player player, String message){
        player.sendMessage(parse(message, false));

    }

    public static void sendActionBar(Player player, String message){
        player.sendActionBar(parse(message, false));
    }

    private static Component parse(String message, boolean isConsole){
        MiniMessage mm =MiniMessage.miniMessage();

        if(isConsole)
            return mm.deserialize(message,
                    Placeholder.parsed("prefix", Messages.CONSOLE_PREFIX.get())
            );
        return mm.deserialize(message,
                Placeholder.parsed("prefix", Messages.CHAT_PREFIX.get())
        );
    }
}
