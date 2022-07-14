package pl.botprzemek;

import com.iridium.iridiumcolorapi.IridiumColorAPI;
import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import pl.botprzemek.commands.*;
import pl.botprzemek.handlers.*;
import pl.botprzemek.methods.LaunchPadFall;
import pl.botprzemek.scrap.Fly;
import pl.botprzemek.scrap.Speed;
import pl.botprzemek.scrap.Teleport;

import java.util.ArrayList;
import java.util.Objects;

public final class bpLobby extends JavaPlugin {

    public static bpLobby plugin;
    public ArrayList<Player> jumpingPlayers = new ArrayList<>();
    public static Permission perms;
    public static Chat chat;

    @Override
    public void onEnable() {

        getLogger().info(IridiumColorAPI.process("<GRADIENT:FF80F6>Starting bpLobby by botprzemek</GRADIENT:FFCC70>"));
        getLogger().info(IridiumColorAPI.process("<GRADIENT:FF80F6>    __          __                                       __  </GRADIENT:FFCC70>"));
        getLogger().info(IridiumColorAPI.process("<GRADIENT:FF80F6>   / /_  ____  / /_____  _________  ___  ____ ___  ___  / /__</GRADIENT:FFCC70>"));
        getLogger().info(IridiumColorAPI.process("<GRADIENT:FF80F6>  / __ \\/ __ \\/ __/ __ \\/ ___/_  / / _ \\/ __ `__ \\/ _ \\/ //_/</GRADIENT:FFCC70>"));
        getLogger().info(IridiumColorAPI.process("<GRADIENT:FF80F6> / /_/ / /_/ / /_/ /_/ / /    / /_/  __/ / / / / /  __/ ,<   </GRADIENT:FFCC70>"));
        getLogger().info(IridiumColorAPI.process("<GRADIENT:FF80F6>/_.___/\\____/\\__/ .___/_/    /___/\\___/_/ /_/ /_/\\___/_/|_|  </GRADIENT:FFCC70>"));
        getLogger().info(IridiumColorAPI.process("<GRADIENT:FF80F6>               /_/                                           </GRADIENT:FFCC70>"));
        getLogger().info(IridiumColorAPI.process("<GRADIENT:FF80F6>                                                             </GRADIENT:FFCC70>"));

        getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");

        this.saveDefaultConfig();
        plugin = this;

        setupPermissions();
        setupChat();

        new JoinQuit(this);
        new PlayerChat(this);
        new PlayerClickGUI(this);
        new PlayerServerSelector(this);

        Objects.requireNonNull(this.getCommand("bplobby")).setExecutor(new Lobby());
        Objects.requireNonNull(this.getCommand("lobby-br")).setExecutor(new Broadcast());
        Objects.requireNonNull(this.getCommand("players")).setExecutor(new HideShowPlayers());
        Objects.requireNonNull(this.getCommand("server")).setExecutor(new Server());

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
