package gg.quartzdev.qxpboosts.inventory;

import gg.quartzdev.qxpboosts.boost.Boost;
import gg.quartzdev.qxpboosts.boost.BoostManager;
import gg.quartzdev.qxpboosts.qXpBoosts;
import gg.quartzdev.qxpboosts.util.qLogger;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

public abstract class SettingsInventory implements InventoryHolder {

    Inventory inventory;
    public qLogger logger;
    public NamespacedKey key;
    public BoostManager boostManager;
    public Boost boost;
    public Material active = Material.LIME_STAINED_GLASS_PANE;
    public Material disabled = Material.RED_STAINED_GLASS_PANE;

    public SettingsInventory(Boost boost){
        this.logger = qXpBoosts.getInstance().logger;
        this.key = new NamespacedKey(qXpBoosts.getInstance(), "source");
        this.boostManager = qXpBoosts.getInstance().boostManager;
        this.boost = boost;
    }

    public void createInv(int size, String titleMsg){
        Component title = MiniMessage.miniMessage().deserialize(titleMsg);
        this.inventory = Bukkit.createInventory(this, size, title);
    }

    public void setInventory(Inventory inventory){
        this.inventory = inventory;
    }

    @Override
    public @NotNull Inventory getInventory() {
        return this.inventory;
    }

    public abstract void onClick(InventoryClickEvent event);

    public ItemStack createItem(Enum<?> source, boolean isActive){
        ItemStack item = new ItemStack(isActive ? this.active : this.disabled);
        ItemMeta itemMeta = item.getItemMeta();
        Component component = MiniMessage.miniMessage().deserialize(source.name()).decoration(TextDecoration.ITALIC, false);
        itemMeta.displayName(component);
        item.setItemMeta(itemMeta);
        return item;
    }

}
