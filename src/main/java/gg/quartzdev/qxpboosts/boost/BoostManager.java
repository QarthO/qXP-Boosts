package gg.quartzdev.qxpboosts.boost;

import gg.quartzdev.qxpboosts.qConfig;
import gg.quartzdev.qxpboosts.qPermission;
import gg.quartzdev.qxpboosts.qXpBoosts;
import gg.quartzdev.qxpboosts.util.qLogger;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachmentInfo;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class BoostManager {

    qXpBoosts plugin;
    qConfig config;
    qLogger logger;

    HashMap<String, Boost> boosts;
    HashMap<Player, Boost> playerTracker;
    HashMap<Boost, Boolean> boostTracker;
    Boost defaultBoost;


    public BoostManager(){
        this.plugin = qXpBoosts.getInstance();
        this.config = this.plugin.config;
        this.logger = this.plugin.logger;

        this.boosts = new HashMap<>();
        this.playerTracker = new HashMap<>();
        this.defaultBoost = new Boost("default", 1.25);
    }

    public @NotNull Boost getBoost(Player player){

        Boost boost = playerTracker.get(player);

        if(boost == null)
            boost = defaultBoost;

        return boost;
    }

    public boolean isActive(Boost boost){
        return boostTracker.get(boost);
    }

    public void enable(Boost boost){
        boostTracker.put(boost, true);
    }

    public void disable(Boost boost){
        boostTracker.put(boost, false);
    }

    public void loadBoosts(){

    }

}
