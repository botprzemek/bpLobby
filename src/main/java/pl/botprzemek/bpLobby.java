package pl.botprzemek;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;
import pl.botprzemek.commands.*;
import pl.botprzemek.handlers.JoinQuit;
import pl.botprzemek.handlers.LaunchPad;
import pl.botprzemek.handlers.PlayerChat;
import pl.botprzemek.handlers.PlayerClickGUI;
import pl.botprzemek.methods.LaunchPadFall;
import pl.botprzemek.methods.ServerConnect;

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

        getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");

        this.saveDefaultConfig();
        plugin = this;

        setupPermissions();
        setupChat();

        Objects.requireNonNull(this.getCommand("bplobby")).setExecutor(new Lobby());
        Objects.requireNonNull(this.getCommand("fly")).setExecutor(new Fly());
        Objects.requireNonNull(this.getCommand("speed")).setExecutor(new Speed());
        Objects.requireNonNull(this.getCommand("teleport")).setExecutor(new Teleport());
        Objects.requireNonNull(this.getCommand("lobby-br")).setExecutor(new Broadcast());
        Objects.requireNonNull(this.getCommand("players")).setExecutor(new HideShowPlayers());
        Objects.requireNonNull(this.getCommand("server")).setExecutor(new Server());

        new JoinQuit(this);
        new PlayerChat(this);
        new PlayerClickGUI(this);

        if(this.getConfig().getBoolean("launch-pad.enable")){
            new LaunchPad(this);
            new LaunchPadFall(this);
        }

    }

    @Override
    public void onDisable() {
        Bukkit.getLogger().info("Shutting bpLobby");
        this.getServer().getMessenger().unregisterOutgoingPluginChannel(this);
        this.getServer().getMessenger().unregisterIncomingPluginChannel(this);
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
