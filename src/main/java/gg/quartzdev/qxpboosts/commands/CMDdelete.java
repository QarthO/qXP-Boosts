package gg.quartzdev.qxpboosts.commands;

import gg.quartzdev.qxpboosts.boost.BoostManager;
import gg.quartzdev.qxpboosts.qXpBoosts;
import gg.quartzdev.qxpboosts.util.Messages;
import gg.quartzdev.qxpboosts.util.qUtil;
import org.bukkit.command.CommandSender;

import java.util.*;

public class CMDdelete extends qCMD{

    BoostManager boostManager;

    public CMDdelete(String cmdName, String group) {
        super(cmdName, group);
        this.boostManager = qXpBoosts.getInstance().boostManager;
    }

    @Override
    public boolean logic(CommandSender sender, String label, String[] args) {

        if(args.length != 2){
            qUtil.sendMessage(sender, Messages.SYNTAX_CREATE.parse("label", label));
            return false;
        }

        String boostName = args[1].toLowerCase(Locale.ROOT);
        if(boostName.equalsIgnoreCase("default")){
            qUtil.sendMessage(sender, Messages.ERROR_DELETE_DEFAULT);
            return false;
        }

        Messages message = this.boostManager.deleteBoost(boostName) ?
                Messages.BOOST_DELETE.parse("boost", boostName) :
                Messages.ERROR_BOOST_NOT_FOUND.parse("boost", boostName);

        qUtil.sendMessage(sender, message);
        return true;

    }

    @Override
    public Iterable<String> tabCompletionLogic(CommandSender sender, String[] args) {
        if(args.length == 2){
            Set<String> boostNames = new HashSet<>(boostManager.getBoostNames());
            boostNames.remove("default");
            return boostNames;
        }
        return null;
    }
}
