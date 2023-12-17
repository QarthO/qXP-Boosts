package gg.quartzdev.qxpboosts.boost;

import gg.quartzdev.qxpboosts.qConfig;
import gg.quartzdev.qxpboosts.qXpBoosts;
import gg.quartzdev.qxpboosts.util.Language;
import gg.quartzdev.qxpboosts.util.qLogger;
import org.apache.commons.lang3.text.WordUtils;
import org.bukkit.entity.Player;
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
        this.boostTracker = new HashMap<>();
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

    public Set<String> listBoosts(){
        Set<String> boostList = new HashSet<>();

        boostList.add(this.getBoostInfo(this.defaultBoost));

        for(Boost boost : boosts.values())
            boostList.add(this.getBoostInfo(boost));

        return boostList;
    }

    public String getBoostInfo(Boost boost){

        String boostStatus = Language.BOOST_STATUS_ERROR.toString();

        if(boostTracker.get(boost) != null)
            boostStatus = (boostTracker.get(boost)) ? Language.BOOST_STATUS_ACTIVE.toString() : Language.BOOST_STATUS_DISABLED.toString();

        String bootInfo = Language.BOOST_INFO.toString()
                .replaceAll("<boost-name>", WordUtils.capitalizeFully(boost.getName()))
                .replaceAll("<boost-multiplier>", String.valueOf(defaultBoost.getMultiplier()))
                .replaceAll("<boost-status>", boostStatus)
                .replaceAll("<prefix>", Language.CHAT_PREFIX.name());

        return bootInfo;
    }

}
