package gg.quartzdev.qxpboosts.boost;

import gg.quartzdev.qxpboosts.qConfig;
import gg.quartzdev.qxpboosts.qXpBoosts;
import gg.quartzdev.qxpboosts.util.Language;
import gg.quartzdev.qxpboosts.util.qLogger;
import org.apache.commons.lang3.text.WordUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class BoostManager {

    qXpBoosts plugin;
    qConfig config;
    qLogger logger;

    HashMap<String, Boost> boostsMap;
    HashMap<Player, Boost> playerTracker;
    Set<Boost> activeBoosts;


    public BoostManager(){
        this.plugin = qXpBoosts.getInstance();
        this.config = this.plugin.config;
        this.logger = this.plugin.logger;

        this.boostsMap = new HashMap<>();
        this.playerTracker = new HashMap<>();
        this.activeBoosts = new HashSet<>();

        this.loadBoosts();
    }

    public @NotNull Boost getBoost(Player player){

        Boost boost = playerTracker.get(player);

        if(boost == null) {
            player.sendMessage("boost not found, assinging default boost");
            boost = boostsMap.get("default");
        }

        return boost;
    }

    public @Nullable Boost getBoost(String boostName){
        return boostsMap.get(boostName);
    }

    public boolean isActive(Boost boost){
        return activeBoosts.contains(boost);
    }

    public void enable(Boost boost){
        activeBoosts.add(boost);
    }

    public void disable(Boost boost){
        activeBoosts.remove(boost);
    }

    public void loadBoosts(){
        Set<Boost> boosts = this.config.getBoosts();
        for(Boost boost : boosts){
            this.boostsMap.put(boost.getName(), boost);
        }
    }

    public Set<String> listBoosts(){
        Set<String> boostList = new HashSet<>();

        for(Boost boost : boostsMap.values())
            boostList.add(this.getBoostInfo(boost));

        return boostList;
    }

    public String getBoostInfo(Boost boost){

        String boostStatus = (this.isActive(boost)) ? Language.BOOST_STATUS_ACTIVE.toString() : Language.BOOST_STATUS_DISABLED.toString();

        return Language.BOOST_INFO.toString()
                .replaceAll("<boost-name>", WordUtils.capitalizeFully(boost.getName()))
                .replaceAll("<boost-multiplier>", String.valueOf(boost.getMultiplier()))
                .replaceAll("<boost-status>", boostStatus);

    }

}