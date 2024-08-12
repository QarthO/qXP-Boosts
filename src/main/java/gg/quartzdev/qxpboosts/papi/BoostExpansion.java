package gg.quartzdev.qxpboosts.papi;

import gg.quartzdev.qxpboosts.boost.Boost;
import gg.quartzdev.qxpboosts.boost.BoostManager;
import gg.quartzdev.qxpboosts.qXpBoosts;
import gg.quartzdev.qxpboosts.util.BoostUtil;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class BoostExpansion extends PlaceholderExpansion
{
    @Override
    public @NotNull String getIdentifier() {
        return "qxpboosts";
    }

    @Override
    public @NotNull String getAuthor() {
        return "quartzdev.gg";
    }

    @Override
    public @NotNull String getVersion() {
        return "1.0.0";
    }

    @Override
    public String onRequest(OfflinePlayer offlinePlayer, @NotNull String params){
        if(offlinePlayer == null || offlinePlayer.getPlayer() == null) return null;
        Player player = offlinePlayer.getPlayer();

//        [boost:index]_[option]
        String[] split = params.toLowerCase().split("_");
//        if missing option
        if(split.length != 2) return "error";

//        get the boost
        Boost boost = null;
        if(params.startsWith("boost"))
        {
//            parse the index from the first param, defaults to 0 if no index given
            int sortIndex = getBoostIndex(split[0]);
            try
            {
//                get the boost based on the sort index
                boost = BoostUtil.getSortedBoosts(player).get(sortIndex);
            } catch (IndexOutOfBoundsException ignored)
            {
//                return "None if player doesn't have a boost at the index
                return "None";
            }
        }
        if(boost == null) return "error";

//        parse and return the value off the given option
        return switch(split[1])
        {
            case "chance" -> "" + boost.getChance();
            case "multiplier" -> "" + boost.getMultiplier();
            case "name" -> boost.getName();
            default -> "error";
        };
    }

    /**
     * Parses the index from the first param, defaults to 0 if no index given, or if the index is invalid
     * @param firstParam the first param of the placeholder in format [boost:<index>]
     * @return the requested index
     */
    private int getBoostIndex(String firstParam)
    {
        String[] split = firstParam.split(":");
        try
        {
            return Integer.parseInt(split[1])-1;
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException ignored)
        {
            return 0;
        }
    }

}
