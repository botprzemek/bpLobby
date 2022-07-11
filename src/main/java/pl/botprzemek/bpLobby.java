package pl.botprzemek;

import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import pl.botprzemek.commands.*;
import pl.botprzemek.handlers.ClickChest;
import pl.botprzemek.handlers.JoinQuit;
import pl.botprzemek.handlers.LaunchPad;
import pl.botprzemek.handlers.PlayerChat;
import pl.botprzemek.methods.LaunchPadFall;
import pl.botprzemek.scrap.StoneDrop;

import java.util.ArrayList;
import java.util.Objects;

public final class bpLobby extends JavaPlugin {

    public static bpLobby plugin;
    public ArrayList<Player> jumpingPlayers = new ArrayList<>();
    public static Permission perms;
    public static Chat chat;

    @Override
    public void onEnable() {

        Bukkit.getLogger().info("Starting bpLobby by botprzemek");

        this.saveDefaultConfig();
        plugin = this;

        //getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");

        setupPermissions();
        setupChat();

        Objects.requireNonNull(this.getCommand("bplobby")).setExecutor(new Lobby());
        Objects.requireNonNull(this.getCommand("fly")).setExecutor(new Fly());
        Objects.requireNonNull(this.getCommand("speed")).setExecutor(new Speed());
        Objects.requireNonNull(this.getCommand("teleport")).setExecutor(new Teleport());
        Objects.requireNonNull(this.getCommand("lobby-br")).setExecutor(new Broadcast());
        Objects.requireNonNull(this.getCommand("players")).setExecutor(new HideShowPlayers());

        new JoinQuit(this);
        new PlayerChat(this);

//        new ClickChest(this);
//        ClickChest dropItems = new ClickChest(this);
//        dropItems.dropManager("drop.chest");

        if(this.getConfig().getBoolean("launch-pad.enable")){
            new LaunchPad(this);
            new LaunchPadFall(this);
        }

    }

    @Override
    public void onDisable() {

        Bukkit.getLogger().info("Shutting bpLobby");
    }

    private boolean setupPermissions() {
        RegisteredServiceProvider<Permission> rsp = getServer().getServicesManager().getRegistration(Permission.class);
        perms = rsp.getProvider();
        return true;
    }

    private boolean setupChat() {
        RegisteredServiceProvider<Chat> rsp = getServer().getServicesManager().getRegistration(Chat.class);
        chat = rsp.getProvider();
        return chat != null;
    }

    public static Permission getPermissions() {
        return perms;
    }

    public static Chat getChat() {
        return chat;
    }
}
