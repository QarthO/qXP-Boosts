package gg.quartzdev.qxpboosts.commands.set;

import gg.quartzdev.qxpboosts.boost.Boost;
import gg.quartzdev.qxpboosts.util.Language;
import gg.quartzdev.qxpboosts.util.qUtil;
import org.bukkit.command.CommandSender;

public class EDITactionbar extends qEDIT{

    public EDITactionbar(String settingName, String valueSyntax) {
        super(settingName, valueSyntax);
    }

    @Override
    public boolean logic(CommandSender sender, String[] args, Boost boost) {
        if(args.length !=4){
            this.sendSyntax(sender);
            return false;
        }
        if(this.value.equalsIgnoreCase("true") || this.value.equalsIgnoreCase("false")){
            boolean actionBar = Boolean.parseBoolean(this.value);
            boost.setActionBar(actionBar);
            return true;
        } else {
            return false;
        }
    }
}
