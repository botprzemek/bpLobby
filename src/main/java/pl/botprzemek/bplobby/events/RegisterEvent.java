package pl.botprzemek.bplobby.events;

import pl.botprzemek.bplobby.BpLobby;

public class RegisterEvent {

    public RegisterEvent(BpLobby instance) {

        instance.getServer().getPluginManager().registerEvents(new JoinQuitEvent(instance), instance);
        instance.getServer().getPluginManager().registerEvents(new ChatEvent(instance), instance);

    }

}
