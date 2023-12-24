package gg.quartzdev.qxpboosts.util;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.ExperienceOrb;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.stream.Collectors;

public class ReadUtil {

    public static void logReadError(String dataType, String fileName){
        qUtil.sendMessage(Bukkit.getConsoleSender(), Language.ERROR_READ_FILE
                .parse("file", fileName)
                .parse("data-type", dataType));
    }

    public static boolean getBoolean(Object data){
        if(data == null){
            logReadError("boolean", "boosts.yml");
            return false;
        }
        try {
            return (boolean) data;
        } catch(ClassCastException exception){
            logReadError("boolean", "boosts.yml");
            return false;
        }
    }

    public static @Nullable Sound getSound(Object data){
        if(data == null){
            logReadError("sound", "boosts.yml");
            return null;
        }
        String rawData = data.toString();
        try {
            if(rawData.equalsIgnoreCase("none") || rawData.equalsIgnoreCase("null")){
                return null;
            }
            return Sound.valueOf(rawData.toUpperCase(Locale.ROOT));
        } catch(IllegalArgumentException exception){
            qUtil.sendMessage(Bukkit.getConsoleSender(), Language.ERROR_SOUND_NOT_FOUND.parse("sound", rawData).get());
            return null;
        }
    }

    public static double getDouble(Object data){
//       If data isn't found
        if(data == null){
            return 0;
        }

//        Try returning it as a double
        if(data instanceof Double){
            return (double) data;
        }

//        Convert to string and try parsing
        String rawData = data.toString();
        Number number = null;
        try {
            number = Float.parseFloat(rawData);
        } catch(NumberFormatException e) {
            try {
                number = Double.parseDouble(rawData);
            } catch(NumberFormatException e1) {
                try {
                    number = Integer.parseInt(rawData);
                } catch(NumberFormatException e2) {
                    try {
                        number = Long.parseLong(rawData);
                    } catch(NumberFormatException e3) {
                        return 0;
                    }
                }
            }
        }
        return number.doubleValue();
    }

    public static @NotNull List<String> getStringList(Object data){
        List<String> list = new ArrayList<>();
        if(!(data instanceof List)){
            logReadError("list", "boosts.yml");
            return list;
        }
        list = ((List<?>) data).stream().map(obj -> Objects.toString(obj, null)).collect(Collectors.toList());
        return list;
    }
    public static @NotNull EnumSet<ExperienceOrb.SpawnReason> getXpSources(Object data){
        List<String> xpSourceNames = getStringList(data);
        EnumSet<ExperienceOrb.SpawnReason> xpSources;
        if(xpSourceNames.contains("ALL")){
            xpSources = EnumSet.allOf(ExperienceOrb.SpawnReason.class);
        } else {
            xpSources = EnumSet.noneOf(ExperienceOrb.SpawnReason.class);
        }
        for(String xpSourceName : xpSourceNames){
            try{
                ExperienceOrb.SpawnReason xpSource = ExperienceOrb.SpawnReason.valueOf(xpSourceName.replaceFirst("!", "").toUpperCase(Locale.ROOT));
                if(xpSourceName.startsWith("!")){
                    xpSources.remove(xpSource);
                } else {
                    xpSources.add(xpSource);
                }
            } catch(IllegalArgumentException exception){
                qUtil.sendMessage(Bukkit.getConsoleSender(), Language.ERROR_XP_SOURCE_NOT_FOUND.parse("xp-source", xpSourceName));
            }
        }
        return xpSources;
    }

    public static @NotNull EnumSet<CreatureSpawnEvent.SpawnReason> getMobSources(Object data){
        List<String> mobSourceNames = getStringList(data);
        EnumSet<CreatureSpawnEvent.SpawnReason> mobSources;
        if(mobSourceNames.contains("ALL")){
            mobSources = EnumSet.allOf(CreatureSpawnEvent.SpawnReason.class);
        } else {
            mobSources = EnumSet.noneOf(CreatureSpawnEvent.SpawnReason.class);
        }
        for(String mobSourceName : mobSourceNames){
            try{
                CreatureSpawnEvent.SpawnReason mobSource = CreatureSpawnEvent.SpawnReason.valueOf(mobSourceName.replaceFirst("!", "").toUpperCase(Locale.ROOT));
                if(mobSourceName.startsWith("!")){
                    mobSources.remove(mobSource);
                } else {
                    mobSources.add(mobSource);
                }
            } catch(IllegalArgumentException exception){
                qUtil.sendMessage(Bukkit.getConsoleSender(), Language.ERROR_MOB_SOURCE_NOT_FOUND.parse("mob-source", mobSourceName));
            }
        }
        return mobSources;
    }
}
