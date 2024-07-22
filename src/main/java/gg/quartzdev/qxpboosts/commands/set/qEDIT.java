package gg.quartzdev.qxpboosts.commands.set;

import gg.quartzdev.qxpboosts.boost.Boost;
import gg.quartzdev.qxpboosts.util.Messages;
import gg.quartzdev.qxpboosts.util.qUtil;
import org.apache.commons.lang3.text.WordUtils;
import org.bukkit.command.CommandSender;

public abstract class qEDIT
{

    String syntax;
    String settingName;
    String valueSyntax;
    String value;

    public qEDIT(String settingName, String valueSyntax)
    {
        this.settingName = settingName;
        this.valueSyntax = valueSyntax;
    }

    public boolean run(CommandSender sender, String label, String[] args, Boost boost)
    {

//        <prefix> <red>Syntax: /<label> set <boost> <setting> <value>
        this.syntax = Messages.SYNTAX_SET_SETTING
                .parse("label", label)
                .parse("boost", boost.getName())
                .parse("setting", this.settingName)
                .get();

        if(args.length >= 4)
        {
            this.value = args[3];
        }

        if(!this.logic(sender, args, boost))
        {
            return false;
        }

        if(this.settingName.equalsIgnoreCase("xpsources") ||
                this.settingName.equalsIgnoreCase("mobsources"))
        {
            return true;
        }

//        Send success message
        qUtil.sendMessage(sender, Messages.BOOST_SET_SETTING
                .parse("label", label)
                .parse("boost", boost.getName())
                .parse("setting", WordUtils.capitalizeFully(this.settingName))
                .parse("value", this.value));
        return true;
    }

    public abstract boolean logic(CommandSender sender, String[] args, Boost boost);

    public abstract Iterable<String> getTabCompletions(String[] args);

    public void sendSetSyntax(CommandSender sender)
    {
        qUtil.sendMessage(sender, this.syntax);
    }

}
