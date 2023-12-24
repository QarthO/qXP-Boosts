package gg.quartzdev.qxpboosts.commands.set;

import gg.quartzdev.qxpboosts.boost.Boost;
import gg.quartzdev.qxpboosts.util.Language;
import gg.quartzdev.qxpboosts.util.qUtil;
import org.bukkit.command.CommandSender;

import java.util.Locale;

public class EDITmultiplier extends qEDIT{
    @Override
    public boolean run(CommandSender sender, String label, String[] args, Boost boost) {
//        /xpboosts args[0] args[1] args[2] args[3]
//        /xpboosts set     boost   multiplier
        if(args.length !=4){
            Language message = Language.SYNTAX_SET_MULTIPLIER
                    .parse("label", label)
                    .parse("boost", args[1]);
            qUtil.sendMessage(sender, message);
            return false;
        }

        double newMultiplier = -1;
        try{
            newMultiplier = Double.parseDouble(args[3]);
        } catch(NumberFormatException ignored){}

        if(newMultiplier < 0){
            Language message = Language.SYNTAX_SET_MULTIPLIER
                    .parse("label", label)
                    .parse("boost", args[1]);
            qUtil.sendMessage(sender, message);
            return false;
        }

        boost.setMultiplier(newMultiplier);
        qUtil.sendMessage(sender, Language.BOOST_SET_MULTIPLIER
                .parse("label", label)
                .parse("boost", args[1])
                .parse("multiplier", args[3]));
        return true;

    }
}
