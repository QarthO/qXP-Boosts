package gg.quartzdev.qxpboosts.commands.set;

import gg.quartzdev.qxpboosts.boost.Boost;
import gg.quartzdev.qxpboosts.inventory.pages.SourcesPage;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.CreatureSpawnEvent;

public class EDITmobsources extends qEDIT
{

    public EDITmobsources(String settingName, String valueSyntax)
    {
        super(settingName, valueSyntax);
    }

    @Override
    public boolean logic(CommandSender sender, String[] args, Boost boost)
    {
        if(!(sender instanceof Player))
        {
            return false;
        }
        new SourcesPage((Player) sender, boost, CreatureSpawnEvent.SpawnReason.class);
        return true;
    }

    @Override
    public Iterable<String> getTabCompletions(String[] args)
    {
        return null;
    }

}
