package net.maploop.listener;

import net.maploop.GrapplingHook;
import net.momirealms.customfishing.api.event.RodCastEvent;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class CustomFishingRodCastListener implements Listener {
    private final GrapplingHook plugin;

    public CustomFishingRodCastListener(GrapplingHook plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onRodCast(RodCastEvent event) {
        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();
        if (item.getType() == Material.AIR) {
            item = player.getInventory().getItemInOffHand();
        }
        ItemMeta meta = item.getItemMeta();
        PersistentDataContainer container = meta.getPersistentDataContainer();
        Integer dataValue = container.get(plugin.key(), PersistentDataType.INTEGER);

        if (dataValue != null) {
            // Then it's a grappling hook
            event.setCancelled(true);
        }
    }
}
