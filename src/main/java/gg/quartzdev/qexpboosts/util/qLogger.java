package gg.quartzdev.qexpboosts.util;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;

public class qLogger {
    private final String CONSOLE_PREFIX = "<gray>[<red>q<aqua>MobsDropEggs<gray>]<reset>";

    public qLogger(){

    }


    /**
     * Logs a general message
     * @param msg
     */
    public void log(String msg){
        qUtil.sendMessage(Bukkit.getConsoleSender(), msg);
    }

    /**
     * Logs a warning
     * @param msg
     */
    public void warning(String msg){
        log("<yellow>" + msg);
    }

    /**
     * Logs error
     * @param msg
     */
    public void error(String msg){
        log("<red>" + msg);
//        TODO: log errors to a file
    }

    public void error(Language msg){
        log("<red>" + msg.get());
//        TODO: log errors to a file
    }

    public void error(StackTraceElement[] error){
        for(StackTraceElement e : error){
            error(e.toString());
        }
    }
}
