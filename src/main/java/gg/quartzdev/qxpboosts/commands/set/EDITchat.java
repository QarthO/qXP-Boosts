package gg.quartzdev.qxpboosts.commands.set;

import gg.quartzdev.qxpboosts.boost.Boost;
import org.bukkit.command.CommandSender;

import java.util.Arrays;

public class EDITchat extends qEDIT {

    public EDITchat(String settingName, String valueSyntax) {
        super(settingName, valueSyntax);
    }

    @Override
    public boolean logic(CommandSender sender, String[] args, Boost boost) {
        if(args.length !=4){
            this.sendSetSyntax(sender);
            return false;
        }
        if(this.value.equalsIgnoreCase("true") || this.value.equalsIgnoreCase("false")){
            boolean chat = Boolean.parseBoolean(this.value);
            boost.setChat(chat);
            return true;
        }
        return false;
    }

    @Override
    public Iterable<String> getTabCompletions(String[] args) {
        if(args.length == 4){
            String[] rawCompletions = {"true","false"};
            return Arrays.asList(rawCompletions);
        }
        return null;
    }
}
