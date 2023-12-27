package gg.quartzdev.qxpboosts.commands.set;

import gg.quartzdev.qxpboosts.boost.Boost;
import gg.quartzdev.qxpboosts.util.Language;
import gg.quartzdev.qxpboosts.util.qUtil;
import org.bukkit.command.CommandSender;

import java.util.Locale;

public class EDITmultiplier extends qEDIT{
    public EDITmultiplier(String settingName, String valueSyntax) {
        super(settingName, valueSyntax);
    }

    @Override
    public boolean logic(CommandSender sender, String[] args, Boost boost) {
        if(args.length !=4){
            this.sendSyntax(sender);
            return false;
        }

//        Parse value
        double newMultiplier = -1;
        try{
            newMultiplier = Double.parseDouble(this.value);
        } catch(NumberFormatException ignored){}
        if(newMultiplier < 0){
            this.sendSyntax(sender);
            return false;
        }

//        Set value
        boost.setMultiplier(newMultiplier);
        return true;
    }
}
