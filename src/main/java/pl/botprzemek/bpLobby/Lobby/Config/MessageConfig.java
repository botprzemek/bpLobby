package pl.botprzemek.bpLobby.Lobby.Config;

import pl.botprzemek.bpLobby.BpLobby;

public class MessageConfig extends Config {

    public MessageConfig(BpLobby instance) {

        super(instance, "messages.yml");

    }

    public String getMessage(String messageName) {

        return getString(messageName);

    }

}
