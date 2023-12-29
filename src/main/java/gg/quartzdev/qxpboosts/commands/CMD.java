package gg.quartzdev.qxpboosts.commands;

import gg.quartzdev.qxpboosts.qXpBoosts;
import gg.quartzdev.qxpboosts.util.Messages;
import gg.quartzdev.qxpboosts.util.qUtil;
import org.bukkit.command.CommandSender;

public class CMD extends qCMD {

    public CMD(String cmdName, String group) {
        super(cmdName, group);
    }

    @Override
    public boolean logic(CommandSender sender, String label, String[] args) {
        qUtil.sendMessage(sender, Messages.PLUGIN_INFO.parse("version", qXpBoosts.getInstance().getPluginMeta().getVersion()));
        return true;
    }

    @Override
    public Iterable<String> tabCompletionLogic(CommandSender sender, String[] args) {
        return null;
    }

}
