package gg.quartzdev.qxpboosts.inventory.pages.listeners;

import gg.quartzdev.qxpboosts.inventory.PlayerInventoryManager;
import gg.quartzdev.qxpboosts.inventory.SettingsInventory;
import gg.quartzdev.qxpboosts.inventory.pages.SearchPage;
import gg.quartzdev.qxpboosts.qXpBoosts;
import gg.quartzdev.qxpboosts.util.qLogger;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class InventoryListener implements Listener {

    qXpBoosts plugin;
    qLogger logger;
    PlayerInventoryManager pim;

    public InventoryListener(){
        this.plugin = qXpBoosts.getInstance();
        this.logger = this.plugin.logger;
        this.pim = this.plugin.pim;

    }


    @EventHandler
    public void onInventoryClick(InventoryClickEvent event){
        if(!(event.getInventory().getHolder() instanceof SettingsInventory)){
            return;
        }
        ((SettingsInventory) event.getInventory().getHolder()).onClick(event);
    }

    @EventHandler
    public void onPlayerCloseInventory(InventoryCloseEvent event){
        if(!(event.getInventory().getHolder() instanceof SearchPage)){
            return;
        }
        if(!(event.getPlayer() instanceof Player)){
            return;
        }
        Player player = (Player) event.getPlayer();

    }
}
