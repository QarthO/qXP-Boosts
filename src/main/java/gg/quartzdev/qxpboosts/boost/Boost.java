package gg.quartzdev.qxpboosts.boost;

import gg.quartzdev.qxpboosts.util.qUtil;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.entity.ExperienceOrb;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class Boost implements ConfigurationSerializable {

    private String name;
    private double multiplier;
    private double chance;

    private Set<ExperienceOrb.SpawnReason> xpSources;

    private Set<CreatureSpawnEvent.SpawnReason> mobSources;

    private boolean chat;
    private boolean actionBar;
    private Sound sound;

    public Boost(String name, double multiplier){
        this.name = name;
        this.multiplier = multiplier;
        this.chat = false;
        this.actionBar = true;
        this.sound = null;
        this.xpSources = new HashSet<>();
        this.mobSources = new HashSet<>();
    }

    //    Deserializes from arena storage file
    public Boost(Map<String, Object> map){
        this.name = (String) map.get("name");
        this.multiplier = (double) map.get("multiplier");
        this.chance = (double) map.get("chance");
        this.chat = (boolean) map.get("chat");
        this.actionBar = (boolean) map.get("action-bar");
        this.sound = (Sound) map.get("sound");
        Set<ExperienceOrb.SpawnReason> xpSources = new HashSet<>();
        qUtil.sendMessage(Bukkit.getConsoleSender(), map.get("xp-sources").toString());
    }

    public void setName(String name) throws BoostLoadException{
        if(this.name != null) {
            throw new BoostLoadException(name);
        }
        this.name = name;
    }


    public String getName(){
        return this.name;
    }

    public double getMultiplier(){
        return this.multiplier;
    }

    @Override
    public @NotNull LinkedHashMap<String, Object> serialize() {
        LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("multiplier", this.multiplier);
        map.put("chance", this.chance);
        map.put("chat", this.chat);
        map.put("actionBar", this.actionBar);
        map.put("sound", this.sound);
        map.put("xp-sources", this.xpSources);


        return map;
    }

    public static Boost deserialize(Map<String, Object> map) {
        return new Boost(map);
    }


}
