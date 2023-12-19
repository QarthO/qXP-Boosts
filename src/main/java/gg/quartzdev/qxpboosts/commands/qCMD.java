package gg.quartzdev.qxpboosts.commands;

import gg.quartzdev.qxpboosts.util.Language;
import gg.quartzdev.qxpboosts.util.qUtil;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public abstract class qCMD {

    String label;
    String name;
    String permissionGroup;
    String permissionNode;
    String commandSyntax;

    public qCMD(String name, String label){
        this.name = name;
        this.label = label;
        this.commandSyntax = "/" + label + " " + name;
    }
    public boolean run(CommandSender sender, String[] args){
        //        checks permission
        if(!this.hasPermission(sender)){
            qUtil.sendMessage(sender, Language.ERROR_NO_PERMISSION);
            return false;
        }
//        runs command
        return logic(sender, args);
    }
    public abstract boolean logic(CommandSender sender, String[] args);
    public boolean hasPermission(CommandSender sender){
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
