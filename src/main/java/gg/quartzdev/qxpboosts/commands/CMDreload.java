package gg.quartzdev.qxpboosts.commands;

import gg.quartzdev.qxpboosts.boost.BoostManager;
import gg.quartzdev.qxpboosts.qXpBoosts;
import gg.quartzdev.qxpboosts.storage.qConfig;
import gg.quartzdev.qxpboosts.util.Messages;
import gg.quartzdev.qxpboosts.util.qUtil;
import org.bukkit.command.CommandSender;

public class CMDreload extends qCMD
{

    qConfig config;
    BoostManager boostManager;

    public CMDreload(String cmdName, String group)
    {
        super(cmdName, group);
        this.permissionGroup = "qxpboosts.admin";
        this.permissionNode = "qxpboosts.command.reload";

        this.config = qXpBoosts.getInstance().config;
        this.boostManager = qXpBoosts.getInstance().boostManager;
    }

    @Override
    public boolean logic(CommandSender sender, String label, String[] args)
    {
        config.reload();
        boostManager.reload();
        qUtil.sendMessage(sender, Messages.RELOAD_COMPLETE);
        return true;
    }

    @Override
    public Iterable<String> tabCompletionLogic(CommandSender sender, String[] args)
    {
        return null;
    }

}
