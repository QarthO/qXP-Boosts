package gg.quartzdev.qxpboosts.commands.set;

import gg.quartzdev.qxpboosts.boost.Boost;
import gg.quartzdev.qxpboosts.boost.BoostManager;
import gg.quartzdev.qxpboosts.util.Language;
import gg.quartzdev.qxpboosts.util.qUtil;
import org.apache.commons.lang3.text.WordUtils;
import org.bukkit.command.CommandSender;

import java.util.Arrays;

public abstract class qEDIT {

    String syntax;
    String settingName;
    String valueSyntax;
    String value;

    public qEDIT(String settingName, String valueSyntax) {
        this.settingName = settingName;
        this.valueSyntax = valueSyntax;
    }

    public boolean run(CommandSender sender, String label, String[] args, Boost boost){

//        <prefix> <red>Syntax: /<label> set <boost> <setting> <value>
        this.syntax = Language.SYNTAX_SET_SETTING
                .parse("label", label)
                .parse("boost", boost.getName())
                .parse("setting", this.settingName)
                .get();

        if(args.length >= 4 ){
            this.value = args[3];
        }

        qUtil.sendMessage(sender, "args:  <blue>" + Arrays.toString(args));
        qUtil.sendMessage(sender, "Value:  <blue>" + this.value);

        if(!this.logic(sender, args, boost)){
            return false;
        }

//        Send success message
        qUtil.sendMessage(sender, Language.BOOST_SET_SETTING
                .parse("label", label)
                .parse("boost", boost.getName())
                .parse("setting", WordUtils.capitalizeFully(this.settingName))
                .parse("value", "poop"));
        return true;
    }

    public abstract boolean logic(CommandSender sender, String[] args, Boost boost);

    public void sendSyntax(CommandSender sender){
        qUtil.sendMessage(sender, this.syntax);
    }

}
