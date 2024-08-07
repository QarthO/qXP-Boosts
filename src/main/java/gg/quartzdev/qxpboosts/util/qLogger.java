package gg.quartzdev.qxpboosts.util;

import org.bukkit.Bukkit;

public class qLogger
{
    private final String CONSOLE_PREFIX = "<gray>[<red>q<aqua>MobsDropEggs<gray>]<reset>";

    /**
     * Logs a general message
     *
     * @param msg
     */
    public void log(String msg)
    {
        qUtil.sendMessage(Bukkit.getConsoleSender(), msg);
    }

    /**
     * Logs a general message
     *
     * @param msg
     */
    public void log(Messages msg)
    {
        qUtil.sendMessage(Bukkit.getConsoleSender(), msg);
    }

    /**
     * Logs a warning
     *
     * @param msg
     */
    public void warning(String msg)
    {
        log("<yellow>" + msg);
    }

    /**
     * Logs error
     *
     * @param msg
     */
    public void error(String msg)
    {
        log("<red>" + msg);
//        TODO: log errors to a file
    }

    public void error(Messages msg)
    {
        log("<red>" + msg.get());
//        TODO: log errors to a file
    }

    public void error(StackTraceElement[] error)
    {
        for(StackTraceElement e : error)
        {
            error(e.toString());
        }
    }
}
