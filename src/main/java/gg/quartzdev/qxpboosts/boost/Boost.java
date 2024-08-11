package gg.quartzdev.qxpboosts.boost;

import gg.quartzdev.qxpboosts.util.Messages;
import gg.quartzdev.qxpboosts.util.ReadUtil;
import gg.quartzdev.qxpboosts.util.WriteUtil;
import gg.quartzdev.qxpboosts.util.qUtil;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.configuration.serialization.SerializableAs;
import org.bukkit.entity.ExperienceOrb;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.jetbrains.annotations.NotNull;

import java.util.EnumSet;
import java.util.LinkedHashMap;
import java.util.Map;

@SerializableAs("qBoost")
public class Boost implements ConfigurationSerializable, Comparable<Boost>
{

    public EnumSet<ExperienceOrb.SpawnReason> xpSources;
    public EnumSet<CreatureSpawnEvent.SpawnReason> mobSources;
    private String name;
    private double multiplier;
    private boolean active;
    private double chance;
    private boolean chat;
    private boolean actionBar;
    private Sound sound;
    private int sortWeight;

    public Boost(String name, double multiplier)
    {
        this.name = name;
        this.multiplier = multiplier;
        this.chance = 100.0;
        this.chat = false;
        this.actionBar = true;
        this.sound = null;
        this.xpSources = EnumSet.noneOf(ExperienceOrb.SpawnReason.class);
        this.mobSources = EnumSet.noneOf(CreatureSpawnEvent.SpawnReason.class);
        this.sortWeight = 0;
    }

    //    Deserializes from boosts storage file
    public Boost(Map<String, Object> map)
    {
        this.multiplier = ReadUtil.getDouble(map.get("multiplier"));
        this.active = ReadUtil.getBoolean(map.get("active"));
        this.chance = ReadUtil.getDouble(map.get("chance"));
        this.chat = ReadUtil.getBoolean(map.get("chat"));
        this.actionBar = ReadUtil.getBoolean(map.get("action-bar"));
        this.sound = ReadUtil.getSound(map.get("sound"));
        this.xpSources = ReadUtil.getXpSources(map.get("xp-sources"));
        this.mobSources = ReadUtil.getMobSources(map.get("mob-sources"));
    }

    public static Boost deserialize(Map<String, Object> map)
    {
        return new Boost(map);
    }

    public void setActionBar(boolean sendsActionBar)
    {
        this.actionBar = sendsActionBar;
    }

    public void initName(String name)
    {
        if(this.name != null)
        {
            qUtil.sendMessage(Bukkit.getConsoleSender(), Messages.ERROR_CORRUPT_FILE.parse("file", "boosts.yml"));
            return;
        }
        this.name = name;
    }

    public @NotNull String getName()
    {
        return this.name;
    }

    public double getMultiplier()
    {
        return this.multiplier;
    }

    public void setMultiplier(double multiplier)
    {
        this.multiplier = multiplier;
    }

    public boolean isActive()
    {
        return this.active;
    }

    public double getChance()
    {
        return this.chance;
    }

    public void setChance(double chance)
    {
        this.chance = chance;
    }

    public boolean sendsChat()
    {
        return this.chat;
    }

    public void setChat(boolean sendsChat)
    {
        this.chat = sendsChat;
    }

    public boolean sendsActionBar()
    {
        return this.actionBar;
    }

    public Sound getSound()
    {
        return this.sound;
    }

    public void setSound(Sound sound)
    {
        this.sound = sound;
    }

    public void enable()
    {
        this.active = true;
    }

    public void disable()
    {
        this.active = false;
    }

    @Override
    public @NotNull LinkedHashMap<String, Object> serialize()
    {
        LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("multiplier", this.multiplier);
        map.put("active", this.active);
        map.put("chance", this.chance);
        map.put("chat", this.chat);
        map.put("action-bar", this.actionBar);
        map.put("sound", WriteUtil.getSound(this.sound));
        map.put("xp-sources", WriteUtil.getXpSourceList(this.xpSources));
        map.put("mob-sources", WriteUtil.getMobSourceList(this.mobSources));
        return map;
    }

    @Override
    public String toString()
    {
        return "{" + name + ", " + multiplier + "x, " + chance + "%}";
    }

    @Override
    public int compareTo(@NotNull Boost boost)
    {
//        Sort by chance
        if(this.chance > boost.chance) return 1;
        if(this.chance < boost.chance) return -1;

//        If chance is the same, sort by multiplier
        if (this.multiplier > boost.multiplier) return 1;
        if (this.multiplier < boost.multiplier) return -1;

//        If multiplier is the same, sort by name
        return this.name.compareTo(boost.name);
    }
}
