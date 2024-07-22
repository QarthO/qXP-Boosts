package gg.quartzdev.qxpboosts.commands.set;

import gg.quartzdev.qxpboosts.boost.Boost;
import gg.quartzdev.qxpboosts.boost.BoostManager;
import gg.quartzdev.qxpboosts.commands.qCMD;
import gg.quartzdev.qxpboosts.qXpBoosts;
import gg.quartzdev.qxpboosts.util.Messages;
import gg.quartzdev.qxpboosts.util.qUtil;
import org.bukkit.command.CommandSender;

import java.util.HashMap;
import java.util.Locale;

public class CMDset extends qCMD
{

    BoostManager boostManager;
    HashMap<String, qEDIT> subCmds;

    public CMDset(String cmdName, String group)
    {
        super(cmdName, group);
        this.boostManager = qXpBoosts.getInstance().boostManager;
        this.subCmds = new HashMap<>();
        subCmds.put("multiplier", new EDITmultiplier("multiplier", "<multiplier>"));
        subCmds.put("chance", new EDITchance("chance", "<chance>"));
        subCmds.put("chat", new EDITchat("chat", "<true/false>"));
        subCmds.put("actionbar", new EDITactionbar("actionbar", "<true/false>"));
        subCmds.put("sound", new EDITsound("sound", "<sound>"));
        subCmds.put("xpsources", new EDITxpsources("xpsources", ""));
        subCmds.put("mobsources", new EDITmobsources("mobsources", ""));
    }

    //    /command args[0]  args[1]     args[2]     args[3]
//    /command set      <boost>     <setting>   <value>
    @Override
    public boolean logic(CommandSender sender, String label, String[] args)
    {

//        /xpboosts set
        if(args.length == 1)
        {
            qUtil.sendMessage(sender, Messages.SYNTAX_SET
                    .parse("label", label));
            return false;
        }

//        Check if boost exists
        String boostName = args[1].toLowerCase(Locale.ROOT);
        Boost boost = boostManager.getBoost(boostName);
        if(boost == null)
        {
            qUtil.sendMessage(sender, Messages.ERROR_BOOST_NOT_FOUND.parse("boost", boostName));
            return false;
        }

//        /xpboosts set <boost-name>
        if(args.length == 2)
        {
            qUtil.sendMessage(sender, Messages.SYNTAX_SET
                    .parse("label", label)
                    .parse("boost", boostName));
            return false;
        }

        String setting = args[2].toLowerCase(Locale.ROOT);
        qEDIT edit = this.subCmds.get(setting);
        if(edit == null)
        {
            qUtil.sendMessage(sender, Messages.SYNTAX_SET.parse("boost", boostName));
            return false;
        }

        boolean success = edit.run(sender, label, args, boost);

        if(success)
        {
            this.boostManager.saveBoost(boost);
        }
        return success;
    }

    @Override
    public Iterable<String> tabCompletionLogic(CommandSender sender, String[] args)
    {
        if(args.length == 2)
        {
            return this.boostManager.getBoostNames();
        }
        if(args.length == 3)
        {
            return subCmds.keySet();
        }
        if(args.length >= 4)
        {
            String setting = args[2].toLowerCase(Locale.ROOT);
            qEDIT edit = this.subCmds.get(setting);
            if(edit == null)
            {
                return null;
            }
            return edit.getTabCompletions(args);
        }
        return null;
    }
}
