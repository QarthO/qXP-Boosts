package gg.quartzdev.qxpboosts.commands;

import gg.quartzdev.qxpboosts.qXpBoosts;
import gg.quartzdev.qxpboosts.util.Language;
import gg.quartzdev.qxpboosts.util.qUtil;
import org.bukkit.command.CommandSender;

public class CMD extends qCMD {

    public CMD(String name, String label) {
        this.permissionGroup = "qxpboosts.admin";
        this.permissionNode = "qxpboosts.command.info";
    }

    @Override
    public boolean logic(CommandSender sender, String label, String[] args) {
        qUtil.sendMessage(sender, Language.PLUGIN_INFO.parse("version", qXpBoosts.getInstance().getPluginMeta().getVersion()));
        return true;
    }

}
