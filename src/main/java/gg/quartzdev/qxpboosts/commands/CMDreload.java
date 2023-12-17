package gg.quartzdev.qxpboosts.commands;

import gg.quartzdev.qxpboosts.qXpBoosts;
import gg.quartzdev.qxpboosts.util.Language;
import gg.quartzdev.qxpboosts.util.qUtil;
import org.bukkit.command.CommandSender;

public class CMDreload extends qCMD {

    public CMDreload(String name, String label) {
        super(name, label);
        this.permissionGroup = "qxpboosts.admin";
        this.permissionNode = "qxpboosts.command.reload";
    }

    @Override
    public boolean logic(CommandSender sender, String[] args) {
        qXpBoosts.getInstance().config.reload();
        qUtil.sendMessage(sender, Language.RELOAD_COMPLETE);
        return true;
    }

}
