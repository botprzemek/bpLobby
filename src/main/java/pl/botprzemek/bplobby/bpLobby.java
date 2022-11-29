package pl.botprzemek.bplobby;

import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.plugin.java.JavaPlugin;
import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import pl.botprzemek.bplobby.events.ChatEvent;

public final class bpLobby extends JavaPlugin {

    private static bpLobby instance;
    private BukkitAudiences adventure;

    public BukkitAudiences adventure() {
        if(this.adventure == null) throw new IllegalStateException("Tried to access Adventure when the plugin was disabled!");
        return this.adventure;
    }

    @Override
    public void onEnable() {

        this.saveDefaultConfig();

        instance = this;

        adventure = BukkitAudiences.create(this);

        Audience sender = adventure.sender(getServer().getConsoleSender());

        MiniMessage mm = MiniMessage.miniMessage();

        sender.sendMessage(mm.deserialize("<gradient:#ff80f6:#ffcc70>Starting bpLobby-0.1-REMASTERED by botprzemek</gradient>"));
        sender.sendMessage(mm.deserialize("<gradient:#ff80f6:#ffcc70>    __          __                                       __  </gradient>"));
        sender.sendMessage(mm.deserialize("<gradient:#ff80f6:#ffcc70>   / /_  ____  / /_____  _________  ___  ____ ___  ___  / /__</gradient>"));
        sender.sendMessage(mm.deserialize("<gradient:#ff80f6:#ffcc70>  / __ \\/ __ \\/ __/ __ \\/ ___/_  / / _ \\/ __ `__ \\/ _ \\/ //_/</gradient>"));
        sender.sendMessage(mm.deserialize("<gradient:#ff80f6:#ffcc70> / /_/ / /_/ / /_/ /_/ / /    / /_/  __/ / / / / /  __/ ,<   </gradient>"));
        sender.sendMessage(mm.deserialize("<gradient:#ff80f6:#ffcc70>/_.___/\\____/\\__/ .___/_/    /___/\\___/_/ /_/ /_/\\___/_/|_|  </gradient>"));
        sender.sendMessage(mm.deserialize("<gradient:#ff80f6:#ffcc70>               /_/                                           </gradient>"));
        sender.sendMessage(mm.deserialize("<gradient:#ff80f6:#ffcc70>                                                             </gradient>"));

        new ChatEvent(this);
    }
    public void onDisable() {
        if(this.adventure != null) {
            this.adventure.close();
            this.adventure = null;
        }
    }
}