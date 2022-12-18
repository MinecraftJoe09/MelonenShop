package eu.melonensaft.minecraftjoe09.melonenshop.command;

import eu.melonensaft.minecraftjoe09.melonenshop.MelonenShop;
import net.kyori.adventure.text.Component;
import net.tnemc.core.item.ItemStackBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class Shop implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String string, @NotNull String[] strings) {
        try {
            FileConfiguration configuration = MelonenShop.instance.getConfig();
            if (strings[0].equalsIgnoreCase("create")) {
                if (strings.length == 2) {
                    configuration.set(strings[1].toLowerCase() + ".size", 54);
                    configuration.set(strings[1].toLowerCase() + ".title", "");
                }
                if (strings.length == 3) {
                    configuration.set(strings[1].toLowerCase() + ".size", Bukkit.getServer().createInventory(null, Integer.parseInt(strings[2])).getSize());
                    configuration.set(strings[1].toLowerCase() + ".title", "");
                }
            }
            if (strings[0].equalsIgnoreCase("delete")) {
                if (strings.length == 2) {
                    configuration.set(strings[1].toLowerCase(), null);
                }
            }
            if (strings[0].equalsIgnoreCase("open")) {
                if (strings.length == 2) {
                    if (sender instanceof Player) {
                        Player player = (Player) sender;
                        Inventory inventory = Bukkit.getServer().createInventory(null, configuration.getInt(strings[1].toLowerCase() + ".size"), Component.text(Objects.requireNonNull(configuration.getString(strings[0].toLowerCase() + ".title"))));
                        for (int i = 0; i < inventory.getSize(); i++) {
                            inventory.setItem(i, new ItemStackBuilder(Material.valueOf(configuration.getString(strings[0].toLowerCase() + ".items." + i + ".material"))).setAmount(configuration.getInt(strings[0].toLowerCase() + ".items." + i + ".amount")).setCustomModelData(configuration.getInt(strings[0].toLowerCase() + ".items." + i + ".price")).getStack());
                        }
                        player.openInventory(inventory);
                    }
                }
            }
            if (strings[0].equalsIgnoreCase("setitem")) {
                if (strings.length == 3) {
                    configuration.set(strings[1].toLowerCase() + ".items." + strings[2], null);
                }
                if (strings.length == 6) {
                    configuration.set(strings[1].toLowerCase() + ".items." + strings[2] + ".amount", Integer.parseInt(strings[4]));
                    configuration.set(strings[1].toLowerCase() + ".items." + strings[2] + ".material", Integer.parseInt(strings[3]));
                    configuration.set(strings[1].toLowerCase() + ".items." + strings[2] + ".price", Integer.parseInt(strings[5]));
                }
            }
            MelonenShop.instance.saveConfig();
        } catch (Exception exception) {
            exception.printStackTrace();
            return false;
        }
        return true;
    }
}