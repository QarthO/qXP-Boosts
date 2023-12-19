package gg.quartzdev.qxpboosts.commands;

import gg.quartzdev.qxpboosts.qXpBoosts;
import gg.quartzdev.qxpboosts.util.Language;
import gg.quartzdev.qxpboosts.util.qUtil;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

import java.util.Set;

public class CMDlist extends qCMD{

    public CMDlist(String name, String label) {
        super(name, label);
        this.permissionGroup = "qmbde.admin";
        this.permissionNode = "qmbde.command.reload";
    }

    @Override
    public boolean logic(CommandSender sender, String[] args) {
//        Boost list
        Set<String> boostList = qXpBoosts.getInstance().boostManager.listBoosts();
        String message = String.join("<reset><newline>", boostList);
        qUtil.sendMessage(sender, message);

        return false;
    }

}
