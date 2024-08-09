package gg.quartzdev.qxpboosts.commands;

import gg.quartzdev.qxpboosts.boost.Boost;
import gg.quartzdev.qxpboosts.boost.BoostManager;
import gg.quartzdev.qxpboosts.qPermission;
import gg.quartzdev.qxpboosts.qXpBoosts;
import gg.quartzdev.qxpboosts.storage.qConfig;
import gg.quartzdev.qxpboosts.util.BoostUtil;
import gg.quartzdev.qxpboosts.util.Messages;
import gg.quartzdev.qxpboosts.util.ReadUtil;
import gg.quartzdev.qxpboosts.util.qUtil;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.ExperienceOrb;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.CreatureSpawnEvent;

import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class CMDgivexp extends qCMD
{
    qConfig config;
    BoostManager boostManager;

    public CMDgivexp(String cmdName, String group)
    {
        super(cmdName, group);
        config = qXpBoosts.getInstance().config;
        boostManager = qXpBoosts.getInstance().boostManager;
    }

//    /xpboosts givexp <player> <amount>
    @Override
    public boolean logic(CommandSender sender, String label, String[] args)
    {
        if(args.length != 3)
        {
            qUtil.sendMessage(sender, Messages.SYNTAX_GIVEXP.parse("label", label));
            return false;
        }

        String playerName = args[1];
        Player player = Bukkit.getPlayer(playerName);
        if(player == null)
        {
            qUtil.sendMessage(sender, Messages.ERROR_PLAYER_NOT_FOUND.parse("player", playerName));
            return false;
        }

        String amount = args[2];
        try {
            int amountInt = Integer.parseInt(amount);
            if(amountInt < 0)
            {
                qUtil.sendMessage(sender, Messages.ERROR_NEGATIVE_AMOUNT.parse("amount", amount));
                return false;
            }
            player.giveExp(amountInt);
            calculateBoostAttempt(player, amountInt);
            qUtil.sendMessage(sender, Messages.XP_GIVEN.parse("player", player.getName()).parse("amount", amount));
        } catch(NumberFormatException exception)
        {
            qUtil.sendMessage(sender, Messages.ERROR_NUMBER_FORMAT.parse("amount", amount));
            return false;
        }

        return true;
    }

    @Override
    public Iterable<String> tabCompletionLogic(CommandSender sender, String[] args)
    {
        return null;
    }

    private void calculateBoostAttempt(Player player, int amount)
    {
//        World check
        if(config.isDisabledWorld(player.getWorld())) return;

//        Permission check
        if(config.requiresPermission() && !player.hasPermission(qPermission.GROUP_PLAYER.getPermission())) return;

        Set<String> boostNames = BoostUtil.getBoostNames(player);

        double multiplier = 0;
        double highestMultiplier = 0;
        boolean sendActionBar = false;
        boolean sendChat = false;
        Sound sound = null;

        for(String boostName : boostNames)
        {
            Boost boost = this.boostManager.getBoost(boostName);
            if(boost == null)
            {
                qUtil.sendMessage(Bukkit.getConsoleSender(), Messages.ERROR_BOOST_NOT_FOUND.parse("boost", boostName));
                continue;
            }

//            Is the boost active
            if(!boost.isActive()) continue;

//            RNG if the boost will run
            if(boost.getChance() < 100)
            {
                ThreadLocalRandom random = ThreadLocalRandom.current();
                float rng = random.nextFloat(0.01F, 100.0F);
                if(rng > boost.getChance())
                {
                    continue;
                }
            }

//            Running multiplier total
            multiplier += boost.getMultiplier();

//            Running notify
            if(boost.sendsChat())
            {
                sendChat = true;
            }
            if(boost.sendsActionBar())
            {
                sendActionBar = true;
            }

//            Only will play the sound of the highest multiplier
            highestMultiplier = Math.max(highestMultiplier, boost.getMultiplier());
            if(boost.getSound() != null && boost.getMultiplier() == highestMultiplier)
            {
                sound = boost.getSound();
            }

            double newAmount = amount * boost.getMultiplier();
            player.giveExp((int) newAmount, true);
        }

        BoostUtil.notifyPlayerGotBoost(player, multiplier, sendChat, sendActionBar, sound);
    }
}
