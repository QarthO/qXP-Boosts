package gg.quartzdev.qxpboosts.papi;

import gg.quartzdev.qxpboosts.boost.Boost;
import gg.quartzdev.qxpboosts.boost.BoostManager;
import gg.quartzdev.qxpboosts.qXpBoosts;
import gg.quartzdev.qxpboosts.util.BoostUtil;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Set;

public class BoostExpansion extends PlaceholderExpansion
{

    private BoostManager boostManager;

    public BoostExpansion()
    {
        this.boostManager = qXpBoosts.getInstance().boostManager;
    }

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

        String[] split = params.toLowerCase().split("_");

        Boost boost = null;
        if(params.startsWith("boost"))
        {
            int sortIndex = getBoostIndex(split[0]);
            try {
                boost = BoostUtil.getSortedBoosts(player).get(sortIndex);
            } catch (IndexOutOfBoundsException ignored) {
                return "None";
            }
        }
        if(split.length < 2) return "error";
        if(boost == null) return "error";
        return switch(split[1])
        {
            case "chance" -> "" + boost.getChance();
            case "multiplier" -> "" + boost.getMultiplier();
            case "name" -> boost.getName();
            default -> "error";
        };
    }

    private int getBoostIndex(String firstParam)
    {
        String[] split = firstParam.split(":");
        try {
            return Integer.parseInt(split[1])-1;
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException ignored) {
            return 0;
        }
    }

}
