package gg.quartzdev.qxpboosts.commands.set;

import gg.quartzdev.qxpboosts.boost.Boost;
import gg.quartzdev.qxpboosts.inventory.pages.SearchPage;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class EDITsound extends qEDIT{
    public EDITsound(String settingName, String valueSyntax) {
        super(settingName, valueSyntax);
    }

    @Override
    public boolean logic(CommandSender sender, String[] args, Boost boost) {
        if(!(sender instanceof Player)){
            return false;
        }
        new SearchPage((Player) sender, boost);
        return true;
    }
}
