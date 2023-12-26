package gg.quartzdev.qxpboosts.commands.set;

import gg.quartzdev.qxpboosts.boost.Boost;
import gg.quartzdev.qxpboosts.inventory.pages.SourcesPage;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.ExperienceOrb;
import org.bukkit.entity.Player;

public class EDITxpsources extends qEDIT {
    @Override
    public boolean run(CommandSender sender, String label, String[] args, Boost boost) {
        if(!(sender instanceof Player)){
            return false;
        }
        new SourcesPage((Player) sender, boost, ExperienceOrb.SpawnReason.class);
        return true;
    }
}
