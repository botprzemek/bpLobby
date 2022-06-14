package pl.botprzemek.inventories;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Listener;
import pl.botprzemek.bpLobby;
import pl.botprzemek.commands.Lobby;

public class CustomInventory<lobby> implements Listener {
    public CustomInventory(bpLobby plugin){
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }
    public void CreateInventory(){
        Lobby lobby = new Lobby();
        //lobby.onCommand();
    }
}
