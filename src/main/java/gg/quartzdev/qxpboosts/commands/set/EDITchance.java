package gg.quartzdev.qxpboosts.commands.set;

import gg.quartzdev.qxpboosts.boost.Boost;
import org.bukkit.command.CommandSender;

import java.util.Arrays;

public class EDITchance extends qEDIT
{
    public EDITchance(String settingName, String valueSyntax)
    {
        super(settingName, valueSyntax);
    }

    @Override
    public boolean logic(CommandSender sender, String[] args, Boost boost)
    {
        if(args.length != 4)
        {
            this.sendSetSyntax(sender);
            return false;
        }

//        Parse value
        double chance = -1;
        try
        {
            chance = Double.parseDouble(this.value);
        } catch(NumberFormatException ignored)
        {
        }
        if(chance < 0)
        {
            this.sendSetSyntax(sender);
            return false;
        }

//        Set value
        boost.setChance(chance);
        return true;
    }

    @Override
    public Iterable<String> getTabCompletions(String[] args)
    {
        if(args.length == 4)
        {
            String[] rawCompletions = {"<chance>"};
            return Arrays.asList(rawCompletions);
        }
        return null;
    }
}
