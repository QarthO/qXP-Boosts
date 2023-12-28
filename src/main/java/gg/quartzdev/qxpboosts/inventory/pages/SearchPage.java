package gg.quartzdev.qxpboosts.inventory.pages;

import gg.quartzdev.qxpboosts.boost.Boost;
import gg.quartzdev.qxpboosts.inventory.PlayerInventoryManager;
import gg.quartzdev.qxpboosts.inventory.SettingsInventory;
import gg.quartzdev.qxpboosts.qXpBoosts;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;

public class SearchPage extends SettingsInventory {

    public SearchPage(Player player, Boost boost) {
        super(boost);
        this.setInventory(Bukkit.createInventory(this, InventoryType.ANVIL, Component.text("Search Sounds")));
        player.openInventory(this.getInventory());
        PlayerInventoryManager pim = qXpBoosts.getInstance().pim;
        pim.collect(player);
    }

    @Override
    public void onClick(InventoryClickEvent event) {
        event.setCancelled(true);
    }

}
