package pl.botprzemek;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import pl.botprzemek.commands.Fly;
import pl.botprzemek.commands.Lobby;
import pl.botprzemek.handlers.JoinQuitHandler;
import pl.botprzemek.handlers.PlayerHandler;

import java.util.Objects;

public final class bpLobby extends JavaPlugin {

    public static bpLobby plugin;

    @Override
    public void onEnable() {
        Bukkit.getLogger().info("Loading bpLobby");

        this.saveDefaultConfig();
        plugin = this;

        Objects.requireNonNull(getCommand("bplobby")).setExecutor(new Lobby());
        Objects.requireNonNull(getCommand("fly")).setExecutor(new Fly());

        new JoinQuitHandler(this);
    }

    @Override
    public void onDisable() {

        Bukkit.getLogger().info("Shutting bpLobby");
    }
}
