package gg.quartzdev.qxpboosts;

import gg.quartzdev.qxpboosts.boost.BoostManager;
import gg.quartzdev.qxpboosts.listeners.PlayerPickupExpListener;
import gg.quartzdev.qxpboosts.util.qLogger;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class qXPBoosts extends JavaPlugin {

    private static qXPBoosts instance;
    public qConfig config;
    public qLogger logger;

    public BoostManager boostManager;

    public static qXPBoosts getInstance(){
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;
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
        Bukkit.getLogger().info("Register Event Handlers");
        Bukkit.getServer().getPluginManager().registerEvents(new PlayerPickupExpListener(), this);
    }
}
