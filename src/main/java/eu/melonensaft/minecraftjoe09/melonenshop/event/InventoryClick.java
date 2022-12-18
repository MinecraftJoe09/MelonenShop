package eu.melonensaft.minecraftjoe09.melonenshop.event;

import net.kyori.adventure.text.TextComponent;
import net.tnemc.core.TNE;
import net.tnemc.core.common.account.TNEAccount;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.math.BigDecimal;
import java.util.Objects;

public class InventoryClick implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        try {
            if (((TextComponent) event.getView().title()).content().equals("")) {
                event.setCancelled(true);
                TNEAccount account = TNE.instance().api().getAccount(event.getWhoClicked().getUniqueId());
                if (Objects.requireNonNull(event.getCursor()).getItemMeta().getCustomModelData() >= 0) {
                    assert account.getHoldings().intValue() >= event.getCursor().getItemMeta().getCustomModelData();
                    assert event.getWhoClicked().getInventory().contains(Material.AIR);
                    account.setHoldings(new BigDecimal(account.getHoldings().intValue() - event.getCursor().getItemMeta().getCustomModelData()));
                    ItemStack stack = event.getCursor();
                    ItemMeta meta = stack.getItemMeta();
                    meta.setCustomModelData(0);
                    stack.setItemMeta(meta);
                    event.getWhoClicked().getInventory().addItem(stack);
                }
                if (event.getCursor().getItemMeta().getCustomModelData() < 0) {
                    ItemStack stack = event.getCursor();
                    ItemMeta meta = stack.getItemMeta();
                    meta.setCustomModelData(0);
                    stack.setItemMeta(meta);
                    assert event.getWhoClicked().getInventory().contains(stack);
                    event.getWhoClicked().getInventory().removeItem(stack);
                    account.setHoldings(new BigDecimal(account.getHoldings().intValue() + event.getCursor().getItemMeta().getCustomModelData()));
                }
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}