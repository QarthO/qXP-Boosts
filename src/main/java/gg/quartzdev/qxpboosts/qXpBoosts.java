package gg.quartzdev.qxpboosts;

import gg.quartzdev.qxpboosts.boost.BoostManager;
import gg.quartzdev.qxpboosts.listeners.PlayerPickupExpListener;
import gg.quartzdev.qxpboosts.util.qLogger;
import org.bstats.bukkit.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class qXpBoosts extends JavaPlugin {

    private static qXpBoosts instance;
    public qConfig config;
    public qLogger logger;

    public BoostManager boostManager;

    public static qXpBoosts getInstance(){
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;

//        bStats.org Metrics
        int pluginId = 20504;
        Metrics metrics = new Metrics(this, pluginId);

        this.logger = new qLogger();
        this.config = new qConfig();
        this.boostManager = new BoostManager();

        registerHandlers();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void registerHandlers(){
        Bukkit.getServer().getPluginManager().registerEvents(new PlayerPickupExpListener(), this);
    }
}
