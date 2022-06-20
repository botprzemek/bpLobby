package pl.botprzemek;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import pl.botprzemek.commands.*;
import pl.botprzemek.handlers.JoinQuit;
import pl.botprzemek.handlers.LaunchPad;
import pl.botprzemek.handlers.StoneDrop;
import pl.botprzemek.methods.LaunchPadFall;

import java.util.ArrayList;
import java.util.Objects;

public final class bpLobby extends JavaPlugin {

    public static bpLobby plugin;
    public ArrayList<Player> jumpingPlayers = new ArrayList<>();

    @Override
    public void onEnable() {

        Bukkit.getLogger().info("Starting bpLobby by botprzemek");

        this.saveDefaultConfig();
        plugin = this;

        Objects.requireNonNull(this.getCommand("bplobby")).setExecutor(new Lobby());
        Objects.requireNonNull(this.getCommand("fly")).setExecutor(new Fly());
        Objects.requireNonNull(this.getCommand("speed")).setExecutor(new Speed());
        Objects.requireNonNull(this.getCommand("teleport")).setExecutor(new Teleport());
        //Objects.requireNonNull(this.getCommand("gui")).setExecutor(new GameMode());

        new JoinQuit(this);

        StoneDrop dropItems = new StoneDrop(this);
        dropItems.dropManager();

        if(this.getConfig().getBoolean("launch-pad.enable")){
            new LaunchPad(this);
            new LaunchPadFall(this);
        }

    }

    @Override
    public void onDisable() {

        Bukkit.getLogger().info("Shutting bpLobby");
    }
}
