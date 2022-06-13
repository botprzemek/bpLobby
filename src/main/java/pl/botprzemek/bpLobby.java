package pl.botprzemek;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import pl.botprzemek.commands.fly;
import pl.botprzemek.commands.reload;
import pl.botprzemek.handlers.playerHandler;
import pl.botprzemek.handlers.test;

import java.util.Objects;

public final class bpLobby extends JavaPlugin {

    @Override
    public void onEnable() {
        Bukkit.getLogger().info("Loading bpLobby");

        this.saveDefaultConfig();
        Bukkit.getLogger().info(
                this.getConfig().getString("fill.material").toUpperCase().replace(' ', '_'));

        Objects.requireNonNull(getCommand("bplobby")).setExecutor(new reload());
        Objects.requireNonNull(getCommand("fly")).setExecutor(new fly());

        new playerHandler(this);
        new test(this);
    }

    @Override
    public void onDisable() {
        Bukkit.getLogger().info("Shutting bpLobby");
    }
}
