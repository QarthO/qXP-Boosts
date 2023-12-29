package gg.quartzdev.qxpboosts.commands;

import gg.quartzdev.qxpboosts.boost.Boost;
import gg.quartzdev.qxpboosts.boost.BoostManager;
import gg.quartzdev.qxpboosts.qXpBoosts;
import gg.quartzdev.qxpboosts.util.Messages;
import gg.quartzdev.qxpboosts.util.qUtil;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.Collection;

public class CMDcreate extends qCMD {

    BoostManager boostManager;

    public CMDcreate(String cmdName, String group) {
        super(cmdName, group);
        this.boostManager = qXpBoosts.getInstance().boostManager;
    }

    @Override
    public boolean logic(CommandSender sender, String label, String[] args) {

        if(args.length != 3){
            qUtil.sendMessage(sender, Messages.SYNTAX_CREATE.parse("label", label));
            return false;
        }

        String boostName = args[1];

        Boost boost = this.boostManager.getBoost(boostName);
        if(boost != null){
            qUtil.sendMessage(sender, Messages.ERROR_BOOST_ALREADY_EXISTS
                    .parse("boost", boostName));
            return false;
        }

        double multiplier = 0.0;
        try {
            multiplier = Double.parseDouble(args[2]);
        } catch(NumberFormatException exception){

            return false;
        }

        this.boostManager.createBoost(boostName, multiplier);
        qUtil.sendMessage(sender, Messages.BOOST_CREATE.parse("boost", boostName));
        return true;

    }

    @Override
    public Iterable<String> tabCompletionLogic(CommandSender sender, String[] args) {
        Collection<String> rawCompletions = new ArrayList<>();
        if(args.length == 2){
            rawCompletions.add("<boost>");
        }

        if(args.length == 3){
            rawCompletions.add("<multiplier>");
        }

        return rawCompletions;
    }
}
