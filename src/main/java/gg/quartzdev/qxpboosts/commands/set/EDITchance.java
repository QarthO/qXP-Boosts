package gg.quartzdev.qxpboosts.commands.set;

import gg.quartzdev.qxpboosts.boost.Boost;
import gg.quartzdev.qxpboosts.util.Language;
import gg.quartzdev.qxpboosts.util.qUtil;
import org.bukkit.command.CommandSender;

public class EDITchance extends qEDIT {
    public EDITchance(String settingName, String valueSyntax) {
        super(settingName, valueSyntax);
    }

    @Override
    public boolean logic(CommandSender sender, String[] args, Boost boost) {
        if(args.length !=4){
            this.sendSyntax(sender);
            return false;
        }

//        Parse value
        double chance = -1;
        try{
            chance = Double.parseDouble(this.value);
        } catch(NumberFormatException ignored){}
        if(chance < 0){
            this.sendSyntax(sender);
            return false;
        }

//        Set value
        boost.setChance(chance);
        return true;
    }
}
