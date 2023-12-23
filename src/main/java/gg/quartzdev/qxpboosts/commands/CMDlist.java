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

    public CMDlist(String cmdName, String group) {
        super(cmdName, group);
        this.permissionGroup = "qmbde.admin";
        this.permissionNode = "qmbde.command.reload";
    }


//    /command args[0] args[1] args[2] ....
    @Override
    public boolean logic(CommandSender sender, String label, String[] args) {
//        Boost list
        Set<String> boostList = qXpBoosts.getInstance().boostManager.listBoosts();
        String message = Language.BOOST_INFO_HEADER.get() +"<reset><newline>" + String.join("<reset><newline>", boostList);
        qUtil.sendMessage(sender, message);

        return false;
    }

    @Override
    public Iterable<String> getTabCompletions(String[] args) {
        return null;
    }

}
