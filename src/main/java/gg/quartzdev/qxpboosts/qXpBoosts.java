package gg.quartzdev.qxpboosts;

import gg.quartzdev.qxpboosts.boost.Boost;
import gg.quartzdev.qxpboosts.boost.BoostManager;
import gg.quartzdev.qxpboosts.commands.CommandManager;
import gg.quartzdev.qxpboosts.inventory.pages.listeners.InventoryListener;
import gg.quartzdev.qxpboosts.listeners.PlayerPickupExpListener;
import gg.quartzdev.qxpboosts.storage.qConfig;
import gg.quartzdev.qxpboosts.util.qLogger;
import org.bstats.bukkit.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.plugin.java.JavaPlugin;

public final class qXpBoosts extends JavaPlugin
{

    private static qXpBoosts instance;

    static
    {
    }

    public qConfig config;
    public qLogger logger;

    public BoostManager boostManager;

    public static qXpBoosts getInstance()
    {
        return instance;
    }

    @Override
    public void onEnable()
    {
        instance = this;

        ConfigurationSerialization.registerClass(Boost.class, "qBoost");

//        bStats.org Metrics
        int pluginId = 20504;
        Metrics metrics = new Metrics(this, pluginId);

        this.logger = new qLogger();
        this.config = new qConfig();
        this.boostManager = new BoostManager();

        registerHandlers();

        new CommandManager("qxpboosts");
    }

    @Override
    public void onDisable()
    {
        // Plugin shutdown logic
    }

    private void registerHandlers()
    {
        Bukkit.getServer().getPluginManager().registerEvents(new PlayerPickupExpListener(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new InventoryListener(), this);
    }

    @SuppressWarnings("UnstableApiUsage")
    public String getPluginVersion()
    {
        return this.getPluginMeta().getVersion();
    }

}
