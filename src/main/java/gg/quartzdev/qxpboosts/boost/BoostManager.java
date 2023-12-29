package gg.quartzdev.qxpboosts.boost;

import gg.quartzdev.qxpboosts.qPermission;
import gg.quartzdev.qxpboosts.storage.YMLboosts;
import gg.quartzdev.qxpboosts.storage.qConfig;
import gg.quartzdev.qxpboosts.qXpBoosts;
import gg.quartzdev.qxpboosts.util.Messages;
import gg.quartzdev.qxpboosts.util.qLogger;
import org.apache.commons.lang3.text.WordUtils;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

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

//        Checks player permissions if they have any of the custom boosts
        for(String boostName : boostsMap.keySet()){
            if(player.hasPermission(qPermission.BOOST.boost(boostName))){
                boost = boostsMap.get(boostName);
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

    public Set<String> getBoostNames(){
        return boostsMap.keySet();
    }

    public Set<String> getActiveBoostNames(){
        Set<String> activeBoostNames = new HashSet<>();
        for(Boost boost : boostsMap.values()){
            if(boost.isActive()){
                activeBoostNames.add(boost.getName());
            }
        }
        return activeBoostNames;
    }

    public Set<String> getDisabledBoostNames(){
        Set<String> disabledBoostNames = new HashSet<>();
        for(Boost boost : boostsMap.values()){
            if(!boost.isActive()){
                disabledBoostNames.add(boost.getName());
            }
        }
        return disabledBoostNames;
    }

    public Set<String> listBoosts(){
        Set<String> boostList = new HashSet<>();

        for(Boost boost : boostsMap.values()) {
            String statusColor = boost.isActive() ? "<green>" : "<red>";
            String interact = "<hover:show_text:'<light_purple>" + boost.getName() + " <gray>- <green>Click for info'><click:run_command:/xpboosts info " + boost.getName() + ">";
            boostList.add(interact + statusColor + boost.getName() + "<reset>");
        }
        return boostList;
    }

    public void saveBoost(Boost boost){
        this.boostStorage.save(boost);
    }

    private String formattedInfoValue(String string, int spaces){
        return String.format("%-" + spaces + "s", string.substring(0, Math.min(string.length(), spaces)));
    }

    public void createBoost(String boostName, double multiplier){
        Boost boost = new Boost(boostName, multiplier);
        Boost defaultBoost = boostsMap.get("default");
        boost.xpSources = EnumSet.copyOf(defaultBoost.xpSources);
        boost.mobSources = EnumSet.copyOf(defaultBoost.mobSources);

        boostsMap.put(boostName, boost);
        this.saveBoost(boost);
    }

    public boolean deleteBoost(String boostName){
        if(boostsMap.get(boostName) == null) {
            return false;
        }
        boostsMap.remove(boostName);
        boostStorage.delete(boostName);
        return true;
    }

    public void reload(){
        boostsMap.clear();
        boostStorage.reload();
        this.loadBoosts();
    }

}