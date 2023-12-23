package gg.quartzdev.qxpboosts.commands;

import gg.quartzdev.qxpboosts.qXpBoosts;
import gg.quartzdev.qxpboosts.util.Language;
import gg.quartzdev.qxpboosts.util.qUtil;
import org.bukkit.command.CommandSender;

public class CMD extends qCMD {

    public CMD(String cmdName, String group) {
        super(cmdName, group);
    }

    @Override
    public boolean logic(CommandSender sender, String label, String[] args) {
        qUtil.sendMessage(sender, Language.PLUGIN_INFO.parse("version", qXpBoosts.getInstance().getPluginMeta().getVersion()));
        return true;
    }

    @Override
    public Iterable<String> getTabCompletions(String[] args) {
        return null;
    }

}
