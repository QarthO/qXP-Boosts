package gg.quartzdev.qxpboosts.commands;

import gg.quartzdev.qxpboosts.boost.BoostManager;
import gg.quartzdev.qxpboosts.qXpBoosts;
import org.bukkit.command.CommandSender;

public class CMDedit extends qCMD
{
    BoostManager boostManager;

    public CMDedit(String cmdName, String group)
    {
        super(cmdName, group);
        this.boostManager = qXpBoosts.getInstance().boostManager;
    }

    @Override
    public boolean logic(CommandSender sender, String label, String[] args)
    {
        return false;
    }

    @Override
    public Iterable<String> tabCompletionLogic(CommandSender sender, String[] args)
    {
        return null;
    }
}
