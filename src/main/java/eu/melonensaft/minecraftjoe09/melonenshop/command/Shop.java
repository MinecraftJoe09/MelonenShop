package eu.melonensaft.minecraftjoe09.melonenshop.command;

import eu.melonensaft.minecraftjoe09.melonenshop.MelonenShop;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class Shop implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String string, @NotNull String[] strings) {
        try {
            if (strings[0].equalsIgnoreCase("create")) {
                if (strings.length == 2) {
                    MelonenShop.getInstance().getConfig().set(strings[1].toLowerCase() + ".size", 54);
                    MelonenShop.getInstance().getConfig().set(strings[1].toLowerCase() + ".title", "Shop");
                }
                if (strings.length == 3) {
                    MelonenShop.getInstance().getConfig().set(strings[1].toLowerCase() + ".size", Bukkit.getServer().createInventory(null, Integer.parseInt(strings[2])).getSize());
                    MelonenShop.getInstance().getConfig().set(strings[1].toLowerCase() + ".title", "Shop");
                }
                if (strings.length == 4) {
                    MelonenShop.getInstance().getConfig().set(strings[1].toLowerCase() + ".size", Bukkit.getServer().createInventory(null, Integer.parseInt(strings[2])).getSize());
                    MelonenShop.getInstance().getConfig().set(strings[1].toLowerCase() + ".title", strings[3]);
                }
            }
            if (strings[0].equalsIgnoreCase("delete")) {
                if (strings.length == 2) {
                    MelonenShop.getInstance().getConfig().set(strings[1].toLowerCase(), null);
                }
            }
            if (strings[0].equalsIgnoreCase("setitem")) {
                if (strings.length == 3) {
                    MelonenShop.getInstance().getConfig().set(strings[1].toLowerCase() + ".items." + strings[2], null);
                }
                if (strings.length == 6) {
                    MelonenShop.getInstance().getConfig().set(strings[1].toLowerCase() + ".items." + strings[2] + ".amount", Integer.parseInt(strings[4]));
                    MelonenShop.getInstance().getConfig().set(strings[1].toLowerCase() + ".items." + strings[2] + ".item", Integer.parseInt(strings[3]));
                    MelonenShop.getInstance().getConfig().set(strings[1].toLowerCase() + ".items." + strings[2] + ".price", Integer.parseInt(strings[5]));
                }
            }
            MelonenShop.getInstance().saveConfig();
        } catch (Exception exception) {
            exception.printStackTrace();
            return false;
        }
        return true;
    }
}