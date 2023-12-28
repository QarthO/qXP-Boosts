package gg.quartzdev.qxpboosts.commands.set;

import gg.quartzdev.qxpboosts.boost.Boost;
import gg.quartzdev.qxpboosts.boost.BoostManager;
import gg.quartzdev.qxpboosts.commands.qCMD;
import gg.quartzdev.qxpboosts.qXpBoosts;
import gg.quartzdev.qxpboosts.util.Language;
import gg.quartzdev.qxpboosts.util.qUtil;
import org.bukkit.command.CommandSender;

import java.util.Collection;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

public class CMDset extends qCMD {

    BoostManager boostManager;
    Set<String> setCmds;

    public CMDset(String cmdName, String group) {
        super(cmdName, group);
        this.boostManager = qXpBoosts.getInstance().boostManager;
        this.setCmds = new HashSet<>();
//        setCmds.add("multiplier");
//        setCmds.add("chance");
//        setCmds.add("chat");
//        setCmds.add("actionbar");
//        setCmds.add("sound");
        setCmds.add("xpsources");
        setCmds.add("mobsources");
    }

//    /command args[0]  args[1]     args[2]     args[3]
//    /command set      <boost>     <setting>   <value>
    @Override
    public boolean logic(CommandSender sender, String label, String[] args) {

//        /xpboosts set
        if(args.length == 1){
            qUtil.sendMessage(sender, Language.SYNTAX_SET
                    .parse("label", label));
            return false;
        }

//        Check if boost exists
        String boostName = args[1].toLowerCase(Locale.ROOT);
        Boost boost = boostManager.getBoost(boostName);
        if(boost == null){
            qUtil.sendMessage(sender, Language.ERROR_BOOST_NOT_FOUND.parse("boost", boostName));
            return false;
        }

//        /xpboosts set <boost-name>
        if(args.length == 2){
            qUtil.sendMessage(sender, Language.SYNTAX_SET
                    .parse("label", label)
                    .parse("boost", boostName));
            return false;
        }

        String setting = args[2].toLowerCase(Locale.ROOT);
        boolean success = false;

        switch(setting){
//            case "multiplier":
//                success = new EDITmultiplier("multiplier", "<multiplier>").run(sender, label, args, boost);
//                break;
//            case "chance":
//                success = new EDITchance("chance", "<chance>").run(sender, label, args, boost);
//                break;
//            case "chat":
//                success = new EDITchat("chat", "<true/false>").run(sender, label, args, boost);
//                break;
//            case "actionbar":
//                success = new EDITactionbar("actionBar", "<true/false>").run(sender, label, args, boost);
//                break;
            case "sound":
                success = new EDITsound("sound", "<true/false>").run(sender, label, args, boost);
                break;
            case "xpsources":
                success = new EDITxpsources("xpsources", "").run(sender, label, args, boost);
                break;
            case "mobsources":
                success = new EDITmobsources("mobsources", "").run(sender, label, args, boost);
                break;
            default:
                qUtil.sendMessage(sender, Language.SYNTAX_SET.parse("boost", boostName));
                return false;
        }
        if(success) {
            this.boostManager.saveBoost(boost);
        }
        return success;
    }

    @Override
    public Iterable<String> getTabCompletions(String[] args) {
        if(args.length == 2){
            return this.boostManager.getBoostNames();
        }
        if(args.length == 3){
            return setCmds;
        }
        return null;
    }
}
