package pl.botprzemek;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import pl.botprzemek.commands.Fly;
import pl.botprzemek.commands.Lobby;
import pl.botprzemek.handlers.JoinQuitHandler;

import java.util.List;
import java.util.Objects;

public final class bpLobby extends JavaPlugin {

    public static bpLobby plugin;

    @Override
    public void onEnable() {

        Bukkit.getLogger().info("Starting bpLobby by botprzemek");

        this.saveDefaultConfig();
        plugin = this;

        Objects.requireNonNull(this.getCommand("bplobby")).setExecutor(new Lobby());
        Objects.requireNonNull(this.getCommand("fly")).setExecutor(new Fly());

        new JoinQuitHandler(this);
    }

    @Override
    public void onDisable() {

        Bukkit.getLogger().info("Shutting bpLobby");
    }
}
