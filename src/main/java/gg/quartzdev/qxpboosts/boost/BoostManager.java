package gg.quartzdev.qxpboosts.boost;

import gg.quartzdev.qxpboosts.qPermission;
import gg.quartzdev.qxpboosts.storage.YMLboosts;
import gg.quartzdev.qxpboosts.storage.qConfig;
import gg.quartzdev.qxpboosts.qXpBoosts;
import gg.quartzdev.qxpboosts.util.Language;
import gg.quartzdev.qxpboosts.util.qLogger;
import org.apache.commons.lang3.text.WordUtils;
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

    YMLboosts boostStorage;
    HashMap<String, Boost> boostsMap;


    public BoostManager(){
        this.plugin = qXpBoosts.getInstance();
        this.config = this.plugin.config;
        this.logger = this.plugin.logger;

        this.boostStorage = new YMLboosts("boosts.yml");
        this.boostsMap = new HashMap<>();
        this.loadBoosts();
    }

    public @NotNull Boost getBoost(Player player){
        Boost boost = boostsMap.get("default");
        player.sendMessage("using default");

//        Checks player permissions if they have any of the custom boosts
        for(String boostName : boostsMap.keySet()){
            if(player.hasPermission(qPermission.BOOST.boost(boostName))){
                boost = boostsMap.get(boostName);
                player.sendMessage("nevermind player has boost: " + boostName);
                break;
            }
        }

        return boost;
    }

    public @Nullable Boost getBoost(String boostName){
        return boostsMap.get(boostName);
    }

    public boolean isActive(String boostName){
        return boostsMap.get(boostName).isActive();
    }

    public void loadBoosts(){
        Set<Boost> boosts = this.boostStorage.loadAll();
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

        String boostStatus = (boost.isActive()) ? Language.BOOST_STATUS_ACTIVE.toString() : Language.BOOST_STATUS_DISABLED.toString();

        int spaces = 10;
        return Language.BOOST_INFO_LINE.toString()
                .replaceAll("<boost-name>", formattedInfoValue(WordUtils.capitalizeFully(boost.getName()), spaces))
                .replaceAll("<boost-multiplier>", formattedInfoValue(String.valueOf(boost.getMultiplier()), 5))
                .replaceAll("<boost-status>", boostStatus);

    }

    public void saveBoost(Boost boost){
        this.boostStorage.save(boost);
    }

    private String formattedInfoValue(String string, int spaces){
        return String.format("%-" + spaces + "s", string.substring(0, Math.min(string.length(), spaces)));
    }

}