package pl.botprzemek;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.plugin.java.JavaPlugin;
import pl.botprzemek.commands.Fly;
import pl.botprzemek.commands.Lobby;
import pl.botprzemek.commands.Speed;
import pl.botprzemek.commands.Teleport;
import pl.botprzemek.handlers.JoinQuit;
import pl.botprzemek.handlers.LaunchPad;
import pl.botprzemek.handlers.SpikesTrap;
import pl.botprzemek.handlers.SuperPick;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class bpLobby extends JavaPlugin {


    public static bpLobby plugin;
    public List<Material> ores = new ArrayList<>();

    @Override
    public void onEnable() {

        Bukkit.getLogger().info("Starting bpLobby by botprzemek");

        this.saveDefaultConfig();
        plugin = this;

        Objects.requireNonNull(this.getCommand("bplobby")).setExecutor(new Lobby());
        Objects.requireNonNull(this.getCommand("fly")).setExecutor(new Fly());
        Objects.requireNonNull(this.getCommand("speed")).setExecutor(new Speed());
        //Objects.requireNonNull(this.getCommand("tp")).setExecutor(new Teleport());

        new JoinQuit(this);

        if(this.getConfig().getBoolean("launch-pad.enable")) new LaunchPad(this);


        new SpikesTrap(this);

        List<String> oreList = this.getConfig().getStringList("super-pick.ores");

        for(String ore : oreList) {
            ores.add(Material.getMaterial(ore.toUpperCase().replace(" ", "_")));
        }

        new SuperPick(this);
    }

    @Override
    public void onDisable() {

        Bukkit.getLogger().info("Shutting bpLobby");
    }
}
