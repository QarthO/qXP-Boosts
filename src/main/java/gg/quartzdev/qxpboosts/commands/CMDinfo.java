package gg.quartzdev.qxpboosts.commands;

import gg.quartzdev.qxpboosts.boost.Boost;
import gg.quartzdev.qxpboosts.boost.BoostManager;
import gg.quartzdev.qxpboosts.qXpBoosts;
import gg.quartzdev.qxpboosts.util.BoostUtil;
import gg.quartzdev.qxpboosts.util.Messages;
import gg.quartzdev.qxpboosts.util.qUtil;
import org.apache.commons.lang3.text.WordUtils;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Set;

public class CMDinfo extends qCMD
{

    BoostManager boostManager;

    public CMDinfo(String cmdName, String group)
    {
        super(cmdName, group);
        this.boostManager = qXpBoosts.getInstance().boostManager;
    }

    @Override
    public boolean logic(CommandSender sender, String label, String[] args)
    {
        if(!(sender instanceof Player))
        {
            qUtil.sendMessage(sender, Messages.ERROR_PLAYER_ONLY);
            return false;
        }
        Player player = (Player) sender;

        if(args.length != 2)
        {
            qUtil.sendMessage(sender, Messages.SYNTAX_INFO
                    .parse("label", label));
        }

        if(args.length == 1)
        {
            Set<String> boosts = BoostUtil.getBoostNames(player);

            return false;
        }

        String boostName = args[1];
        Boost boost = this.boostManager.getBoost(boostName);
        if(boost == null)
        {
            qUtil.sendMessage(sender, Messages.ERROR_BOOST_NOT_FOUND.parse("boost", boostName));
            return false;
        }
//        if(sender.hasPermission(this.permissionNode + ".others")){
//
//        }
        String status = boost.isActive() ? Messages.BOOST_STATUS_ACTIVE.get() : Messages.BOOST_STATUS_DISABLED.get();
        String soundName = boost.getSound() == null ? "NONE" : boost.getSound().name();
        qUtil.sendMessage(sender, Messages.BOOST_INFO
                .parse("boost", WordUtils.capitalizeFully(boost.getName()))
                .parse("status", status)
                .parse("multiplier", "" + boost.getMultiplier())
                .parse("chance", "" + boost.getChance() + "%")
                .parse("chat", "" + boost.sendsChat())
                .parse("action-bar", "" + boost.sendsActionBar())
                .parse("sound", soundName)
        );


        return false;
    }

    @Override
    public Iterable<String> tabCompletionLogic(CommandSender sender, String[] args)
    {
//        /xpboosts info
        if(sender.hasPermission(this.permissionNode + ".others"))
        {
            if(args.length == 2)
            {
                return this.boostManager.getBoostNames();
//                return Bukkit.getOnlinePlayers().stream().map(Player::getName).collect(Collectors.toList());
            }
        }
        return null;
    }
}
