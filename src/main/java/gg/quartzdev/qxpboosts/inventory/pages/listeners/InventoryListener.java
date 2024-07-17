package gg.quartzdev.qxpboosts.inventory.pages.listeners;

import gg.quartzdev.qxpboosts.inventory.SettingsInventory;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryType;

public class InventoryListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event){
        if(!(event.getInventory().getHolder() instanceof SettingsInventory)){
            return;
        }
        if(event.getClickedInventory() == null || event.getClickedInventory().getType() == InventoryType.PLAYER){
            return;
        }
        if(event.getCurrentItem() == null){
            return;
        }
        ((SettingsInventory) event.getInventory().getHolder()).onClick(event);
    }

    @EventHandler
    public void onInventoryDrag(InventoryDragEvent event){
        if(!(event.getInventory().getHolder() instanceof SettingsInventory)){
            return;
        }
        event.setCancelled(true);
    }
}
