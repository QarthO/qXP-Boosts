package gg.quartzdev.qxpboosts.inventory.pages;

import gg.quartzdev.qxpboosts.boost.Boost;
import gg.quartzdev.qxpboosts.inventory.SettingsInventory;
import gg.quartzdev.qxpboosts.util.InventoryUtil;
import gg.quartzdev.qxpboosts.util.Messages;
import org.apache.commons.lang3.text.WordUtils;
import org.bukkit.entity.ExperienceOrb;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.EnumSet;

public class SourcesPage extends SettingsInventory {

    Class<? extends Enum<?>> sourceType;
    EnumSet<?> sources;

    public <E extends Enum<E>> SourcesPage(Player player, Boost boost, Class<E> sourceType) {
        super(boost);
        this.sourceType = sourceType;
        String title = Messages.INVENTORY_TITLE_SOURCES.
                parse("boost", WordUtils.capitalizeFully(boost.getName())).get();
        if(sourceType == ExperienceOrb.SpawnReason.class){
            this.sources = this.boost.xpSources;
            title = title.replaceAll("<source-type>", "XP-Sources");
        }
        else if(sourceType == CreatureSpawnEvent.SpawnReason.class){
            this.sources = this.boost.mobSources;
            title = title.replaceAll("<source-type>", "Mob-Sources");
        }
        else {
            return;
        }
        this.createInv(this.getNeededSlots(sourceType), title);


        this.fill(sources);
        player.openInventory(this.getInventory());

    }

    @Override
    public void onClick(InventoryClickEvent event) {
        event.setCancelled(true);
        ItemStack item = event.getCurrentItem();
        if(item == null){
            return;
        }
        if(this.sourceType == ExperienceOrb.SpawnReason.class){
            ExperienceOrb.SpawnReason source = InventoryUtil.getSource(this.key, item, ExperienceOrb.SpawnReason.class);
            this.updateSources(item, this.boost.xpSources, source);
        }
        else if(this.sourceType == CreatureSpawnEvent.SpawnReason.class){
            CreatureSpawnEvent.SpawnReason source = InventoryUtil.getSource(this.key, item, CreatureSpawnEvent.SpawnReason.class);
            this.updateSources(item, this.boost.mobSources, source);
        }
        else {
            this.logger.error("GUI Error: Please report this in the discord");
            return;
        }
        this.boostManager.saveBoost(this.boost);

    }
    public <E extends Enum<E>> void fill(EnumSet<E> sources){
        if(!sources.stream().findAny().isPresent()){
            return;
        }
        EnumSet<E> all = EnumSet.allOf(sources.stream().findAny().get().getDeclaringClass());
        for(E source : all){
            ItemStack item = InventoryUtil.setSource(this.key, source, sources.contains(source));
            this.getInventory().addItem(item);
        }
    }

    public <E extends Enum<E>> void updateSources(ItemStack item, EnumSet<E> sources, E source){
        ItemMeta itemMeta = item.getItemMeta();
        if(sources.remove(source)){
            item.setType(this.disabled);
            InventoryUtil.updateLore(itemMeta, false);
            item.setItemMeta(itemMeta);
            return;
        }
        sources.add(source);
        item.setType(this.active);
        InventoryUtil.updateLore(itemMeta, true);
        item.setItemMeta(itemMeta);
    }

    public <E extends Enum<E>> int getNeededSlots(Class<E> sourceType){
        int all = EnumSet.allOf(sourceType).size();
        int rows = all/9 + 1;
        return Math.min(rows*9, 54);
    }

}
