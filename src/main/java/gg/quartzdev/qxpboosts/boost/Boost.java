package gg.quartzdev.qxpboosts.boost;

import gg.quartzdev.qxpboosts.util.Language;
import gg.quartzdev.qxpboosts.util.qUtil;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.configuration.serialization.SerializableAs;
import org.bukkit.entity.ExperienceOrb;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.stream.Collectors;

@SerializableAs("qBoost")
public class Boost implements ConfigurationSerializable {

    private String name;
    private double multiplier;
    private boolean active;
    private double chance;

    public Set<ExperienceOrb.SpawnReason> xpSources;

    public Set<CreatureSpawnEvent.SpawnReason> mobSources;

    private boolean chat;
    private boolean actionBar;
    private Sound sound;

    public Boost(String name, double multiplier){
        this.name = name;
        this.multiplier = multiplier;
        this.chance = 100.0;
        this.chat = false;
        this.actionBar = true;
        this.sound = null;
        this.xpSources = new HashSet<>();
        this.mobSources = new HashSet<>();
    }

    //    Deserializes from boosts storage file
    public Boost(Map<String, Object> map){
        this.multiplier = (double) map.get("multiplier");
        this.active = (boolean) map.get("active");
        this.chance = (double) map.get("chance");
        this.chat = (boolean) map.get("chat");
        this.actionBar = (boolean) map.get("action-bar");
//        this.sound = (Sound) map.get("sound");
        Set<ExperienceOrb.SpawnReason> xpSources = new HashSet<>();
        List<String> xpSourceNames = (List<String>) map.get("xp-sources");
        for(String xpSourceName : xpSourceNames){
            try{
                ExperienceOrb.SpawnReason xpSource = ExperienceOrb.SpawnReason.valueOf(xpSourceName);
                xpSources.add(xpSource);
            } catch(IllegalArgumentException exception){
                qUtil.sendMessage(Bukkit.getConsoleSender(), Language.ERROR_XP_SOURCE_NOT_FOUND.parse("xp-source", xpSourceName));
            }
        }
        this.xpSources = xpSources;
        Set<CreatureSpawnEvent.SpawnReason> mobSources = new HashSet<>();
        List<String> mobSourceNames = (List<String>) map.get("mob-sources");
        for(String mobSourceName : mobSourceNames){
            try{
                CreatureSpawnEvent.SpawnReason mobSource = CreatureSpawnEvent.SpawnReason.valueOf(mobSourceName);
                mobSources.add(mobSource);
            } catch(IllegalArgumentException exception){
                qUtil.sendMessage(Bukkit.getConsoleSender(), Language.ERROR_XP_SOURCE_NOT_FOUND.parse("mob-source", mobSourceName));
            }
        }
        this.mobSources = mobSources;
    }

    public void setActionBar(boolean sendsActionBar){
        this.actionBar = sendsActionBar;
    }

    public void initName(String name){
        if(this.name != null) {
            qUtil.sendMessage(Bukkit.getConsoleSender(), Language.ERROR_CORRUPT_FILE.parse("file", "boosts.yml"));
            return;
        }
        this.name = name;
    }


    public @NotNull String getName(){
        return this.name;
    }

    public double getMultiplier(){
        return this.multiplier;
    }
    public void setMultiplier(double multiplier){
        this.multiplier = multiplier;
    }

    public boolean isActive(){
        return this.active;
    }

    public double getChance(){
        return this.chance;
    }

    public boolean sendsChat(){
        return this.chat;
    }

    public boolean sendsActionBar(){
        return this.actionBar;
    }

    public Sound getSound(){
        return this.sound;
    }

    public void enable(){
        this.active = true;
    }

    public void disable(){
        this.active = false;
    }

    @Override
    public @NotNull LinkedHashMap<String, Object> serialize() {
        LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("multiplier", this.multiplier);
        map.put("active", this.active);
        map.put("chance", this.chance);
        map.put("chat", this.chat);
        map.put("action-bar", this.actionBar);
        map.put("sound", this.sound);
        map.put("xp-sources", this.xpSources.stream().map(ExperienceOrb.SpawnReason::name).collect(Collectors.toList()));
        map.put("mob-sources", this.mobSources.stream().map(CreatureSpawnEvent.SpawnReason::name).collect(Collectors.toList()));
        return map;
    }

    public static Boost deserialize(Map<String, Object> map) {
        return new Boost(map);
    }


}
