package eu.melonensaft.minecraftjoe09.melonenshop.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class Shop implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String string, @NotNull String[] strings) {
        try {
            if (strings.length == 2) {
                if (strings[0].equalsIgnoreCase("create")) {

                }
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            return false;
        }
        return true;
    }
}