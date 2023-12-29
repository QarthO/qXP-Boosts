package gg.quartzdev.qxpboosts.commands;

import gg.quartzdev.qxpboosts.boost.Boost;
import gg.quartzdev.qxpboosts.boost.BoostManager;
import gg.quartzdev.qxpboosts.qXpBoosts;
import gg.quartzdev.qxpboosts.util.Messages;
import gg.quartzdev.qxpboosts.util.qUtil;
import org.bukkit.command.CommandSender;

import java.util.Locale;

public class CMDdisable extends qCMD {

    BoostManager boostManager;

    public CMDdisable(String cmdName, String group) {
        super(cmdName, group);
        this.permissionGroup = "qxpboosts.admin";
        this.permissionNode = "qxpboosts.command.enable";

        this.boostManager = qXpBoosts.getInstance().boostManager;

    }

    @Override
    public boolean logic(CommandSender sender, String label, String[] args) {

//        Incorrect Syntax
        if(args.length != 2){
            qUtil.sendMessage(sender, Messages.SYNTAX_DISABLE.parse("label", label));
            return false;
        }

//         Gets boost from name
        String boostName = args[1].toLowerCase(Locale.ROOT);
        Boost boost = boostManager.getBoost(boostName);

//        Boost not found
        if(boost == null){
            qUtil.sendMessage(sender, Messages.ERROR_BOOST_NOT_FOUND.parse("boost", boostName));
            return false;
        }

        if(!boost.isActive()){
            qUtil.sendMessage(sender, Messages.ERROR_BOOST_ALREADY_DISABLED
                    .parse("boost", boost.getName()));
            return false;
        }

//        Enables boost
        boost.disable();
        qUtil.sendMessage(sender, Messages.BOOST_DISABLED.parse("boost", boost.getName()));

//        Saves to storage
        this.boostManager.saveBoost(boost);

        return true;
    }

    @Override
    public Iterable<String> tabCompletionLogic(CommandSender sender, String[] args) {
        return args.length == 2 ? this.boostManager.getActiveBoostNames() : null;
    }
}
