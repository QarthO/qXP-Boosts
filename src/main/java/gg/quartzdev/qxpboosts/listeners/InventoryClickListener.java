package gg.quartzdev.qxpboosts.listeners;

import gg.quartzdev.qxpboosts.inventory.SettingsInventory;
import gg.quartzdev.qxpboosts.qXpBoosts;
import gg.quartzdev.qxpboosts.util.qLogger;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryClickListener implements Listener {

    qLogger logger;

    public InventoryClickListener(){
        this.logger = qXpBoosts.getInstance().logger;
    }


    @EventHandler
    public void onInventoryClick(InventoryClickEvent event){
        if(!(event.getInventory().getHolder() instanceof SettingsInventory)){
            return;
        }
        ((SettingsInventory) event.getInventory().getHolder()).onClick(event);
    }
}
