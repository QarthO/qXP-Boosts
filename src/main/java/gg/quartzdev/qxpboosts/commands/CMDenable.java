package gg.quartzdev.qxpboosts.commands;

import gg.quartzdev.qxpboosts.boost.Boost;
import gg.quartzdev.qxpboosts.boost.BoostManager;
import gg.quartzdev.qxpboosts.qXpBoosts;
import gg.quartzdev.qxpboosts.util.Language;
import gg.quartzdev.qxpboosts.util.qUtil;
import org.apache.commons.lang3.text.WordUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

import java.util.Arrays;
import java.util.Locale;
import java.util.Set;

public class CMDenable extends qCMD {

    BoostManager boostManager;

    public CMDenable(String name, String label) {
        super(name, label);
        this.permissionGroup = "qxpboosts.admin";
        this.permissionNode = "qxpboosts.command.enable";

        this.boostManager = qXpBoosts.getInstance().boostManager;

    }

    @Override
    public boolean logic(CommandSender sender, String[] args) {

//        Incorrect Syntax
        if(args.length != 2){
            qUtil.sendMessage(sender, Language.SYNTAX_ENABLE.parse("label", this.label));
            return false;
        }

//         Gets boost from name
        String boostName = args[1].toLowerCase(Locale.ROOT);
        Boost boost = boostManager.getBoost(boostName);

//        Boost not found
        if(boost == null){
            qUtil.sendMessage(sender, Language.ERROR_BOOST_NOT_FOUND.parse("boost", boostName));
            return false;
        }

//        Enables boost
        boostManager.enable(boost);
        qUtil.sendMessage(sender, Language.BOOST_ENABLED.parse("boost", boost.getName()));

        return true;
    }

}
