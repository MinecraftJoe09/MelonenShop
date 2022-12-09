package eu.melonensaft.minecraftjoe09.melonenshop;

import eu.melonensaft.minecraftjoe09.melonenshop.command.Shop;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class MelonenShop extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        Objects.requireNonNull(this.getCommand("shop")).setExecutor(new Shop());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}