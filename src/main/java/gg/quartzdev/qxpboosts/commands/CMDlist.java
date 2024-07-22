package gg.quartzdev.qxpboosts.commands;

import gg.quartzdev.qxpboosts.qXpBoosts;
import gg.quartzdev.qxpboosts.util.Messages;
import gg.quartzdev.qxpboosts.util.qUtil;
import org.bukkit.command.CommandSender;

import java.util.Set;

public class CMDlist extends qCMD
{

    public CMDlist(String cmdName, String group)
    {
        super(cmdName, group);
        this.permissionGroup = "qmbde.admin";
        this.permissionNode = "qmbde.command.reload";
    }


    //    /command args[0] args[1] args[2] ....
    @Override
    public boolean logic(CommandSender sender, String label, String[] args)
    {
//        Boost list
        Set<String> boostList = qXpBoosts.getInstance().boostManager.listBoosts();
        String boosts = String.join(", ", boostList);

        qUtil.sendMessage(sender, Messages.BOOST_LIST
                .parse("boost-count", "" + boostList.size())
                .parse("boost-list", boosts)
        );
        return false;
    }

    @Override
    public Iterable<String> tabCompletionLogic(CommandSender sender, String[] args)
    {
        return null;
    }

}
