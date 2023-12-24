package gg.quartzdev.qxpboosts.util;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.ExperienceOrb;
import org.bukkit.event.entity.CreatureSpawnEvent;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

public class WriteUtil {

    public static String getSound(Sound sound){
        return sound == null ? "NONE" : sound.name();
    }

    public static List<String> getXpSourceList(EnumSet<ExperienceOrb.SpawnReason> xpSources){
        List<String> list = new ArrayList<>();
        if(xpSources.isEmpty()){
            return list;
        }
        EnumSet<ExperienceOrb.SpawnReason> leftover = EnumSet.complementOf(EnumSet.copyOf(xpSources));
        if(leftover.size() < 4){
            list.add("ALL");
            for(ExperienceOrb.SpawnReason xpSource : leftover){
                list.add("!" + xpSource.name());
            }
            return list;
        }
        for(ExperienceOrb.SpawnReason xpSource : xpSources){
            list.add(xpSource.name());
        }
        return list;
    }

    public static List<String> getMobSourceList(EnumSet<CreatureSpawnEvent.SpawnReason> mobSources){
        List<String> list = new ArrayList<>();
        if(mobSources.isEmpty()){
            return list;
        }
        EnumSet<CreatureSpawnEvent.SpawnReason> leftover = EnumSet.complementOf(EnumSet.copyOf(mobSources));

        if(leftover.size() < 4){
            list.add("ALL");
            for(CreatureSpawnEvent.SpawnReason mobSource : leftover){
                list.add("!" + mobSource.name());
            }
            return list;
        }
        for(CreatureSpawnEvent.SpawnReason mobSource : mobSources){
            list.add(mobSource.name());
        }
        return list;
    }

}
