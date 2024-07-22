package gg.quartzdev.qxpboosts.commands;

import gg.quartzdev.qxpboosts.qPermission;
import gg.quartzdev.qxpboosts.util.Messages;
import gg.quartzdev.qxpboosts.util.qUtil;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public abstract class qCMD
{
    String name;
    String permissionGroup;
    String permissionNode;

    public qCMD(String cmdName, String group)
    {
        this.name = cmdName;
        this.permissionGroup = qPermission.PLUGIN_NAME.getPermission() + "." + group;
        this.permissionNode = qPermission.PLUGIN_NAME.getPermission() + ".command." + cmdName;
    }

    public boolean run(CommandSender sender, String label, String[] args)
    {
        //        checks permission
        if(!this.hasPermission(sender))
        {
            qUtil.sendMessage(sender, Messages.ERROR_NO_PERMISSION);
            return false;
        }
//        runs command
        return logic(sender, label, args);
    }

    public abstract boolean logic(CommandSender sender, String label, String[] args);

    public Iterable<String> getTabCompletions(CommandSender sender, String[] args)
    {
        if(!this.hasPermission(sender))
        {
            return null;
        }
        return this.tabCompletionLogic(sender, args);
    }

    public abstract Iterable<String> tabCompletionLogic(CommandSender sender, String[] args);

    public boolean hasPermission(CommandSender sender)
    {
//        If sender is console
        if(!(sender instanceof Player)) return true;
//        If sender is op
        if(sender.isOp()) return true;
//        If sender has permission group
        if(sender.hasPermission(this.permissionGroup)) return true;
//        If sender has permission node
        if(sender.hasPermission(this.permissionNode)) return true;
//        else
        return false;
    }

}
