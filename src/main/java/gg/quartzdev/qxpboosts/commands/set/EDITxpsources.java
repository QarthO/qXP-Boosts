package gg.quartzdev.qxpboosts.commands.set;

import gg.quartzdev.qxpboosts.boost.Boost;
import gg.quartzdev.qxpboosts.inventory.pages.SourcesPage;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.ExperienceOrb;
import org.bukkit.entity.Player;

public class EDITxpsources extends qEDIT {
    public EDITxpsources(String settingName, String valueSyntax) {
        super(settingName, valueSyntax);
    }

    @Override
    public boolean logic(CommandSender sender, String[] args, Boost boost) {
        if(!(sender instanceof Player)){
            return false;
        }
        new SourcesPage((Player) sender, boost, ExperienceOrb.SpawnReason.class);
        return true;
    }

    @Override
    public Iterable<String> getTabCompletions(String[] args) {
        return null;
    }
}
