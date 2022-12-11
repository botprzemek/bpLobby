package pl.botprzemek.bpLobby.Lobby.Utils;

import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.minimessage.MiniMessage;
import pl.botprzemek.bpLobby.Lobby.LobbyManager;

public class ConsoleStartup {

    public ConsoleStartup(LobbyManager lobbyManager) {

        Audience sender = lobbyManager.getAdventure().sender(lobbyManager.getInstance().getServer().getConsoleSender());

        MiniMessage mm = MiniMessage.miniMessage();

        sender.sendMessage(mm.deserialize("<gradient:#ff80f6:#ffcc70>Starting bpLobby-0.1-REMASTERED by botprzemek</gradient>"));
        sender.sendMessage(mm.deserialize("<gradient:#ff80f6:#ffcc70>    __          __                                       __  </gradient>"));
        sender.sendMessage(mm.deserialize("<gradient:#ff80f6:#ffcc70>   / /_  ____  / /_____  _________  ___  ____ ___  ___  / /__</gradient>"));
        sender.sendMessage(mm.deserialize("<gradient:#ff80f6:#ffcc70>  / __ \\/ __ \\/ __/ __ \\/ ___/_  / / _ \\/ __ `__ \\/ _ \\/ //_/</gradient>"));
        sender.sendMessage(mm.deserialize("<gradient:#ff80f6:#ffcc70> / /_/ / /_/ / /_/ /_/ / /    / /_/  __/ / / / / /  __/ ,<   </gradient>"));
        sender.sendMessage(mm.deserialize("<gradient:#ff80f6:#ffcc70>/_.___/\\____/\\__/ .___/_/    /___/\\___/_/ /_/ /_/\\___/_/|_|  </gradient>"));
        sender.sendMessage(mm.deserialize("<gradient:#ff80f6:#ffcc70>               /_/                                           </gradient>"));
        sender.sendMessage(mm.deserialize("<gradient:#ff80f6:#ffcc70>                                                             </gradient>"));

    }
}
