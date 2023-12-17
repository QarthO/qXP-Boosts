package gg.quartzdev.qxpboosts.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.util.StringUtil;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CommandManager extends Command {

    List<String> aliases = new ArrayList<>();
    List<String> commandsList = new ArrayList<>();

    public CommandManager(String name){
        super(name);
        aliases.add("xpboosts");
        super.setPermission("qxpboosts.command");
        super.setAliases(aliases);
        commandsList.add("reload");
        commandsList.add("list");
        commandsList.add("enable");
        commandsList.add("disable");
        Bukkit.getCommandMap().register(name, this);
    }

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String labelOrAlias, @NotNull String[] args) {

        if(args.length >= 1)
            if(args[0].equalsIgnoreCase("reload"))
                return (new CMDreload(args[0], labelOrAlias)).run(sender, args);

        return (new CMD(null, labelOrAlias)).run(sender, args);

    }

    @Override
    public @NotNull List<String> tabComplete(@NotNull CommandSender sender, @NotNull String labelOrAlias, String[] args) throws IllegalArgumentException {
        List<String> completions = new ArrayList<>();

        if(args.length == 0) return completions;

        if(args.length == 1){
            StringUtil.copyPartialMatches(args[0], commandsList, completions);
        }
        Collections.sort(completions);
        return completions;
    }


}
