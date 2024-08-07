package gg.quartzdev.qxpboosts.storage;

import gg.quartzdev.qxpboosts.qXpBoosts;
import gg.quartzdev.qxpboosts.util.qLogger;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.ExperienceOrb;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class qConfig
{

    qXpBoosts plugin;
    qLogger logger;
    FileConfiguration file;

    //    CONFIG OPTIONS
    String CONFIG_VERSION;
    boolean CHECK_UPDATES;
    boolean REQUIRES_PERMISSION = true;
    Set<World> DISABLED_WORLDS;
    Set<ExperienceOrb.SpawnReason> XP_SOURCES;

    public qConfig()
    {
        this.plugin = qXpBoosts.getInstance();
        this.logger = plugin.logger;

        this.file = plugin.getConfig();
        this.plugin.saveDefaultConfig();

        this.DISABLED_WORLDS = new HashSet<>();
        this.loadAll();

    }

    private void save()
    {
        this.plugin.saveConfig();
    }

    public void reload()
    {
        this.plugin.reloadConfig();
        this.file = this.plugin.getConfig();
        this.save();
        this.loadAll();
    }

    private void loadAll()
    {
        this.loadCheckUpdates();
        this.loadDisabledWorlds();
        this.loadRequiresPermission();
    }

    private void loadCheckUpdates()
    {
        this.CHECK_UPDATES = this.file.getBoolean("check-updates");
    }

    public void loadDisabledWorlds()
    {
        this.DISABLED_WORLDS.clear();
        List<String> disabledWorldNames = this.file.getStringList("disabled-worlds");
        if(disabledWorldNames.isEmpty()) return;
        for(String worldName : disabledWorldNames)
        {
            World world = Bukkit.getWorld(worldName);
            if(world == null)
            {
//                    logger.error(Language.ERROR_WORLD_NOT_FOUND.parse("world", worldName));
            } else
            {
                DISABLED_WORLDS.add(world);
            }
        }
    }

    public boolean isDisabledWorld(World world)
    {
        return DISABLED_WORLDS.contains(world);
    }

    public void loadRequiresPermission()
    {
        this.REQUIRES_PERMISSION = this.file.getBoolean("requires-permission");
    }

    public boolean requiresPermission()
    {
        return this.REQUIRES_PERMISSION;
    }

}