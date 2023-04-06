//package pl.botprzemek.bpLobby.command;
//
//import dev.rollczi.litecommands.command.permission.Permission;
//import dev.rollczi.litecommands.command.route.Route;
//import eu.okaeri.injector.annotation.Inject;
//import pl.botprzemek.bpLobby.configuration.ConfigurationMessage;
//import pl.botprzemek.bpLobby.lobby.ManagerMessage;
//import pl.botprzemek.bpLobby.lobby.ManagerPlugin;
//import pl.botprzemek.bpLobby.lobby.BungeeChannel;
//
//@Route(name = "server", aliases = "serwer")
//@Permission("bplobby.command.server")
//public class CommandServer {
//    @Inject private BungeeChannel bungeeChannel;
//    @Inject private ConfigurationMessage configurationMessage;
//    @Inject private ManagerPlugin managerPlugin;
//    @Inject private ManagerMessage managerMessage;
//
////    @Execute
////    public void openGUI(Player player) {
////        //player.openInventory(pluginManager.getGUI(player));
////        managerMessage.playSound(player, "activate");
////    }
////
////    @Execute(required = 1)
////    public void sendPlayer(Player player, @Arg String serverName) {
////        serverName = serverName.substring(0, 1).toUpperCase() + serverName.substring(1).toLowerCase();
////        if (!managerPlugin.isServerViable(serverName.toLowerCase())) {
////            managerMessage.sendMessage(player, configurationMessage.getCommandsServer().getInvalid(), serverName);
////            managerMessage.playSound(player, configurationMessage.getSounds().getError());
////            return;
////        }
////        if (!player.hasPermission("bplobby.server." + serverName.toLowerCase())) {
////            managerMessage.sendMessage(player, configurationMessage.getCommandsServer().getNotFound(), serverName);
////            managerMessage.playSound(player, configurationMessage.getSounds().getError());
////            return;
////        }
////        bungeeChannel.sendPlayer(player, serverName);
////        managerMessage.playSound(player, configurationMessage.getSounds().getActivate());
////    }
//}
