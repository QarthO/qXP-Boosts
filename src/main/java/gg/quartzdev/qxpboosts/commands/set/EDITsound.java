package gg.quartzdev.qxpboosts.commands.set;

import gg.quartzdev.qxpboosts.boost.Boost;
import org.bukkit.command.CommandSender;

public class EDITsound extends qEDIT{
    public EDITsound(String settingName, String valueSyntax) {
        super(settingName, valueSyntax);
    }

    @Override
    public boolean logic(CommandSender sender, String[] args, Boost boost) {
        return false;
    }
}
