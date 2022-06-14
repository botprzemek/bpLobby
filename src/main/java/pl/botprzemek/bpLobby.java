package pl.botprzemek;

import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.checkerframework.checker.nullness.qual.NonNull;
import pl.botprzemek.commands.Fly;
import pl.botprzemek.commands.Lobby;
import pl.botprzemek.handlers.PlayerHandler;

import java.util.Objects;

public final class bpLobby extends JavaPlugin {

    public static bpLobby plugin;

    private BukkitAudiences adventure;

    public @NonNull BukkitAudiences adventure() {
        if(this.adventure == null) {
            throw new IllegalStateException("Tried to access Adventure when the plugin was disabled!");
        }
        return this.adventure;
    }

    @Override
    public void onEnable() {
        Bukkit.getLogger().info("Loading bpLobby");

        adventure = BukkitAudiences.create(this);
        this.saveDefaultConfig();
        plugin = this;

        Objects.requireNonNull(getCommand("bplobby")).setExecutor(new Lobby());
        Objects.requireNonNull(getCommand("fly")).setExecutor(new Fly());

        new PlayerHandler(this);
    }

    @Override
    public void onDisable() {

        if(this.adventure != null) {
            this.adventure.close();
            this.adventure = null;
        }

        Bukkit.getLogger().info("Shutting bpLobby");
    }
}
