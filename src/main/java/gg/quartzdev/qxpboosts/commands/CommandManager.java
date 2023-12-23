package gg.quartzdev.qxpboosts.commands;

import gg.quartzdev.qxpboosts.qXpBoosts;
import gg.quartzdev.qxpboosts.util.Language;
import gg.quartzdev.qxpboosts.util.qUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.util.StringUtil;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class CommandManager extends Command {

    List<String> aliases = new ArrayList<>();
    HashMap<String, qCMD> commandsMap = new HashMap<>();

    public CommandManager(String name){
        super(name);
        aliases.add("xpboosts");
        super.setPermission("qxpboosts.command");
        super.setAliases(aliases);
        commandsMap.put("reload", new CMDreload("/<label> <cmd>"));
//        commandsMap.add("list");
//        commandsMap.add("enable");
//        commandsMap.add("disable");
        Bukkit.getCommandMap().register(name, this);
    }

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String labelOrAlias, @NotNull String[] args) {

        if(args.length >= 1) {
            if (args[0].equalsIgnoreCase("reload")) {
                return (new CMDreload(args[0], labelOrAlias)).run(sender, args);
            }
            if (args[0].equalsIgnoreCase("list")) {
                return (new CMDlist(args[0], labelOrAlias)).run(sender, args);
            }
            if (args[0].equalsIgnoreCase("enable")) {
                return (new CMDenable(args[0], labelOrAlias)).run(sender, args);
            }
            if (args[0].equalsIgnoreCase("disable")) {
                return (new CMDdisable(args[0], labelOrAlias)).run(sender, args);
            }

            qUtil.sendMessage(sender, Language.ERROR_CMD_NOT_FOUND.parse("cmd", args[0]));
            return false;
        }

        return (new CMD(null, labelOrAlias)).run(sender, args);

    }

    @Override
    public @NotNull List<String> tabComplete(@NotNull CommandSender sender, @NotNull String labelOrAlias, String[] args) throws IllegalArgumentException {
        List<String> completions = new ArrayList<>();

        if(args.length == 0) return completions;

        if(args.length == 1){
            StringUtil.copyPartialMatches(args[0], commandsList, completions);
        }

        if(args.length == 2){
            if(args[0].equalsIgnoreCase("enable")){
                StringUtil.copyPartialMatches(args[1], qXpBoosts.getInstance().boostManager.getDisabledBoostNames(), completions);
            }
            if(args[0].equalsIgnoreCase("disable")){
                StringUtil.copyPartialMatches(args[1], qXpBoosts.getInstance().boostManager.getActiveBoostNames(), completions);
            }
            if(args[0].equalsIgnoreCase("info")){
                StringUtil.copyPartialMatches(args[1], qXpBoosts.getInstance().boostManager.getBoostNames(), completions);
            }
        }

        Collections.sort(completions);
        return completions;
    }


}
