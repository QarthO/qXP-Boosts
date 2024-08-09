package gg.quartzdev.qxpboosts.commands;

import gg.quartzdev.qxpboosts.commands.set.CMDset;
import gg.quartzdev.qxpboosts.util.Messages;
import gg.quartzdev.qxpboosts.util.qUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.util.StringUtil;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CommandManager extends Command
{

    List<String> aliases = new ArrayList<>();
    HashMap<String, qCMD> commandsMap = new HashMap<>();

    public CommandManager(String name)
    {
        super(name);
        aliases.add("xpboosts");
//        super.setPermission("qxpboosts.command");
        super.setAliases(aliases);

        commandsMap.put("", new CMD("version", "admin"));
        commandsMap.put("reload", new CMDreload("reload", "admin"));
        commandsMap.put("create", new CMDcreate("create", "admin"));
        commandsMap.put("enable", new CMDenable("enable", "admin"));
        commandsMap.put("disable", new CMDdisable("disable", "admin"));
        commandsMap.put("list", new CMDlist("list", "admin"));
        commandsMap.put("delete", new CMDdelete("delete", "admin"));
        commandsMap.put("set", new CMDset("set", "admin"));
        commandsMap.put("info", new CMDinfo("info", "player"));
        commandsMap.put("givexp", new CMDgivexp("give", "admin"));

        Bukkit.getCommandMap().register(name, this);
    }

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String labelOrAlias, @NotNull String[] args)
    {

//        Send info command
        if(args.length == 0)
        {
            return commandsMap.get("").run(sender, labelOrAlias, args);
        }

//        Get command from the label
        qCMD cmd = commandsMap.get(args[0]);

        if(cmd == null)
        {
            qUtil.sendMessage(sender, Messages.ERROR_CMD_NOT_FOUND.parse("cmd", args[0]));
            return false;
        }

//        Run the command
        return cmd.run(sender, labelOrAlias, args);
    }

    @Override
    public @NotNull List<String> tabComplete(@NotNull CommandSender sender, @NotNull String labelOrAlias, String[] args) throws IllegalArgumentException
    {
        List<String> completions = new ArrayList<>();
//
        if(args.length == 1)
        {
            Set<String> allowedSubCommands = new HashSet<>();
            for (Map.Entry<String, qCMD> entry : commandsMap.entrySet())
            {
                String commandName = entry.getKey();
                qCMD cmd = entry.getValue();
                if (cmd.hasPermission(sender))
                {
                    allowedSubCommands.add(commandName);
                }
            }
            StringUtil.copyPartialMatches(args[0], allowedSubCommands, completions);
        }

        if(args.length > 1)
        {
            qCMD cmd = commandsMap.get(args[0]);

            if(cmd == null)
            {
                return completions;
            }

            Iterable<String> rawCompletions = cmd.getTabCompletions(sender, args);
            if(rawCompletions != null)
            {
                StringUtil.copyPartialMatches(args[args.length - 1], rawCompletions, completions);
            }
        }

        Collections.sort(completions);
        return completions;
    }


}
