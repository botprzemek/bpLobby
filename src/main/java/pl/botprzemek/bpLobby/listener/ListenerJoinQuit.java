package pl.botprzemek.bpLobby.listener;

import eu.okaeri.injector.Injector;
import eu.okaeri.injector.annotation.Inject;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.PlayerInventory;
import pl.botprzemek.bpLobby.configuration.ConfigurationMessage;
import pl.botprzemek.bpLobby.configuration.ConfigurationPlugin;
import pl.botprzemek.bpLobby.gui.GuiButton;
import pl.botprzemek.bpLobby.lobby.BungeeChannel;
import pl.botprzemek.bpLobby.lobby.HiddenPlayers;
import pl.botprzemek.bpLobby.lobby.ManagerMessage;

import java.util.HashMap;

public class ListenerJoinQuit implements Listener {
    @Inject
    private Injector injector;
    @Inject
    private ConfigurationPlugin configurationPlugin;
    @Inject
    private ConfigurationMessage configurationMessage;
    @Inject
    private ManagerMessage managerMessage;
    @Inject
    private BungeeChannel bungeeChannel;
    @Inject
    private HiddenPlayers hiddenPlayers;

    @EventHandler
    public void onJoinEvent(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        event.setJoinMessage(managerMessage.getComponent(player, configurationMessage.getEventsConnect().getJoin(), player.getDisplayName()));
        setupHotbar(player, configurationPlugin, managerMessage);
        player.teleport(configurationPlugin.getLocation());
    }

    @EventHandler
    public void onQuitEvent(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        event.setQuitMessage(managerMessage.getComponent(player, configurationMessage.getEventsConnect().getQuit(), player.getDisplayName()));
        hiddenPlayers.removePlayer(player);
    }

    @EventHandler
    public void onPlayerRightClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (!player.hasPermission("bplobby.command.server")) return;
        if (!(event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)))
            return;
        int slot = player.getInventory().getHeldItemSlot();
        HashMap<Integer, GuiButton> buttons = configurationPlugin.getHotbarButtons();
        GuiButton button = buttons.get(slot);
        if (button == null) return;
        button.getAction().runAction(player, configurationPlugin, configurationMessage, managerMessage, bungeeChannel);
    }

    public void setupHotbar(Player player, ConfigurationPlugin configurationPlugin, ManagerMessage managerMessage) {
        PlayerInventory inventory = player.getInventory();
        HashMap<Integer, GuiButton> buttons = configurationPlugin.getHotbarButtons();
        inventory.clear();
        for (int slot : buttons.keySet())
            player.getInventory().setItem(slot, buttons.get(slot).getItem(managerMessage));
        inventory.setHeldItemSlot(4);
    }
}
