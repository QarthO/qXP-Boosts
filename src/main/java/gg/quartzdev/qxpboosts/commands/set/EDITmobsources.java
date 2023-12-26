package gg.quartzdev.qxpboosts.commands.set;

import gg.quartzdev.qxpboosts.boost.Boost;
import gg.quartzdev.qxpboosts.inventory.pages.SourcesPage;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.CreatureSpawnEvent;

public class EDITmobsources extends qEDIT {

    @Override
    public boolean run(CommandSender sender, String label, String[] args, Boost boost) {
        if(!(sender instanceof Player)){
            return false;
        }
        new SourcesPage((Player) sender, boost, CreatureSpawnEvent.SpawnReason.class);
        return true;
    }

}
