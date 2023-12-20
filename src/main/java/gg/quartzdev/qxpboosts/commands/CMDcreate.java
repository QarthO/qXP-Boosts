package gg.quartzdev.qxpboosts.commands;

import org.bukkit.command.CommandSender;

public class CMDcreate extends qCMD {

    public CMDcreate(String name, String label) {
        super(name, label);
        this.permissionGroup = "qxpboosts.admin";
        this.permissionNode = "qxpboosts.command.create";
    }

    @Override
    public boolean logic(CommandSender sender, String[] args) {
        return false;
    }
}
