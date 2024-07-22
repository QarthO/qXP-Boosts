package gg.quartzdev.qxpboosts.commands.set;

import gg.quartzdev.qxpboosts.boost.Boost;
import org.bukkit.command.CommandSender;

import java.util.Arrays;

public class EDITactionbar extends qEDIT
{

    public EDITactionbar(String settingName, String valueSyntax)
    {
        super(settingName, valueSyntax);
    }

    @Override
    public boolean logic(CommandSender sender, String[] args, Boost boost)
    {
//        /xpboosts set default actionbar true
        if(args.length != 4)
        {
            this.sendSetSyntax(sender);
            return false;
        }
        if(this.value.equalsIgnoreCase("true") || this.value.equalsIgnoreCase("false"))
        {
            boolean actionBar = Boolean.parseBoolean(this.value);
            boost.setActionBar(actionBar);
            return true;
        }
        return false;
    }

    @Override
    public Iterable<String> getTabCompletions(String[] args)
    {
        if(args.length == 4)
        {
            String[] rawCompletions = {"true", "false"};
            return Arrays.asList(rawCompletions);
        }
        return null;
    }
}
