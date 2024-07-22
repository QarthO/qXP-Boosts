package gg.quartzdev.qxpboosts.commands.set;

import gg.quartzdev.qxpboosts.boost.Boost;
import gg.quartzdev.qxpboosts.util.Messages;
import gg.quartzdev.qxpboosts.util.qUtil;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;

import java.util.EnumSet;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class EDITsound extends qEDIT
{
    public EDITsound(String settingName, String valueSyntax)
    {
        super(settingName, valueSyntax);
    }

    @Override
    public boolean logic(CommandSender sender, String[] args, Boost boost)
    {
//        /xpboosts set <boost> sound <sound>
//        /xpboosts args[0] args[1] args[2] args[3]
        if(args.length != 4)
        {
            this.sendSetSyntax(sender);
            return false;
        }
        if(this.value.equalsIgnoreCase("none"))
        {
            boost.setSound(null);
            return true;
        }
        try
        {
            Sound sound = Sound.valueOf(this.value.toUpperCase(Locale.ROOT));
            boost.setSound(sound);
            return true;
        } catch(IllegalArgumentException exception)
        {
            qUtil.sendMessage(sender, Messages.ERROR_SOUND_NOT_FOUND.parse("sound", this.value));
            return false;
        }
    }

    @Override
    public Iterable<String> getTabCompletions(String[] args)
    {
        if(args.length == 4)
        {
            List<String> sounds = EnumSet.allOf(Sound.class).stream().map(Enum::name).collect(Collectors.toList());
            sounds.add("NONE");
            return sounds;
        }
        return null;
    }
}
