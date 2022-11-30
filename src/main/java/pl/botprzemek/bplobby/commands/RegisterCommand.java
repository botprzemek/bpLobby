package pl.botprzemek.bplobby.commands;

import pl.botprzemek.bplobby.BpLobby;

public class RegisterCommand {

    public RegisterCommand(BpLobby instance){

        instance.getCommand("bplobby").setExecutor(new ReloadCommand());

    }

}
