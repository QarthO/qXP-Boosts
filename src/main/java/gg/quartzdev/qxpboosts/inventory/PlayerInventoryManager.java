package gg.quartzdev.qxpboosts.inventory;

import com.jeff_media.morepersistentdatatypes.DataType;
import gg.quartzdev.qxpboosts.qXpBoosts;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;

import javax.naming.Name;
import java.util.ArrayList;
import java.util.Collection;

public class PlayerInventoryManager {

    Collection<Player> players;
    NamespacedKey key;

    public PlayerInventoryManager(){
        this.players = new ArrayList<>();
        this.key = new NamespacedKey(qXpBoosts.getInstance(), "inventory_contents");
    }

    public void store(Player player){
        ItemStack[] contents = player.getInventory().getContents();
        if(contents.length == 0){
            return;
        }
        players.add(player);
        PersistentDataContainer pdc = player.getPersistentDataContainer();
        pdc.set(this.key, DataType.ITEM_STACK_ARRAY, contents);
        player.getInventory().clear();
    }

    public void collect(Player player){
        players.remove(player);
        PersistentDataContainer pdc = player.getPersistentDataContainer();
        ItemStack[] contents = pdc.get(this.key, DataType.ITEM_STACK_ARRAY);
        if(contents == null){
            return;
        }
        player.getInventory().setContents(contents);
        player.updateInventory();
    }
}
