package gg.quartzdev.qexpboosts;

import gg.quartzdev.qexpboosts.boost.BoostManager;
import gg.quartzdev.qexpboosts.listeners.PlayerPickupExpListener;
import gg.quartzdev.qexpboosts.util.qLogger;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class qExpBoosts extends JavaPlugin {

    private static qExpBoosts instance;
    public qConfig config;
    public qLogger logger;

    public BoostManager boostManager;

    public static qExpBoosts getInstance(){
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
