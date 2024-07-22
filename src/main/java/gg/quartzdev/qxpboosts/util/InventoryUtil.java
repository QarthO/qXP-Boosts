package gg.quartzdev.qxpboosts.util;

import com.jeff_media.morepersistentdatatypes.DataType;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.apache.commons.lang3.text.WordUtils;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class InventoryUtil
{

    final static Material active = Material.LIME_STAINED_GLASS_PANE;
    final static Material disabled = Material.RED_STAINED_GLASS_PANE;

    public static <E extends Enum<E>> ItemStack setSource(NamespacedKey key, E source, boolean isActive)
    {
        ItemStack item = new ItemStack(isActive ? active : disabled);
        ItemMeta itemMeta = item.getItemMeta();

        MiniMessage mm = MiniMessage.miniMessage();

//        Display Name
        String name = source.name().replaceAll("_", " ");
        Component component = mm.deserialize(WordUtils.capitalizeFully(name)).decoration(TextDecoration.ITALIC, false);
        itemMeta.displayName(component);

//        Lore
        updateLore(itemMeta, isActive);

//        Setting Enum to NBT
        itemMeta.getPersistentDataContainer().set(key, DataType.asEnum(source.getDeclaringClass()), source);
        item.setItemMeta(itemMeta);
        return item;
    }

    public static <E extends Enum<E>> @Nullable E getSource(NamespacedKey key, @Nullable ItemStack item, Class<E> sourceType)
    {
        if(item == null)
        {
            return null;
        }
        ItemMeta itemMeta = item.getItemMeta();
        return itemMeta.getPersistentDataContainer().get(key, DataType.asEnum(sourceType));
    }

    public static void updateLore(ItemMeta itemMeta, boolean isActive)
    {
        MiniMessage mm = MiniMessage.miniMessage();
        List<Component> lore = new ArrayList<>();

        Component component = mm.deserialize("").decoration(TextDecoration.ITALIC, false);
        lore.add(component);
        String status = isActive ? "<green>Enabled" : "<red>Disabled";
        component = mm.deserialize("<bold> <white>Current Status: " + status).decoration(TextDecoration.ITALIC, false);
        ;
        lore.add(component);
        status = isActive ? "disable" : "enable";
        component = mm.deserialize("  <yellow>Click to " + status).decoration(TextDecoration.ITALIC, false);
        lore.add(component);

        itemMeta.lore(lore);
    }
}
