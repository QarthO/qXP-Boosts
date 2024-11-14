package gg.quartzdev.qxpboosts.inventory.pages.listeners;

import gg.quartzdev.qxpboosts.inventory.SettingsInventory;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryType;

public class InventoryListener implements Listener
{

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event)
    {

//        Ignore if the opened inventory is not our custom inventory (SettingsInventory)
        if(!(event.getInventory().getHolder(false) instanceof SettingsInventory inv))
        {
            return;
        }

//        Ignore if the player is modifying their own inventory, not the custom inventory (SettingsInventory)
        if(event.getClickedInventory() == null || event.getClickedInventory().getType() == InventoryType.PLAYER)
        {
            return;
        }

//        Call the onClick method of our custom inventory (SettingsInventory)
        inv.onClick(event);
    }

    @EventHandler
    public void onInventoryDrag(InventoryDragEvent event)
    {
        if(!(event.getInventory().getHolder(false) instanceof SettingsInventory))
        {
            return;
        }
        event.setCancelled(true);
    }
}
