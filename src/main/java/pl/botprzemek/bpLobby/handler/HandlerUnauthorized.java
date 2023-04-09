package pl.botprzemek.bpLobby.handler;

import dev.rollczi.litecommands.command.LiteInvocation;
import dev.rollczi.litecommands.command.permission.RequiredPermissions;
import dev.rollczi.litecommands.handle.PermissionHandler;
import eu.okaeri.injector.annotation.Inject;
import org.bukkit.command.CommandSender;
import pl.botprzemek.bpLobby.configuration.ConfigurationMessage;
import pl.botprzemek.bpLobby.lobby.ManagerMessage;

public class HandlerUnauthorized implements PermissionHandler<CommandSender> {
    @Inject
    private ConfigurationMessage configurationMessage;
    @Inject
    private ManagerMessage managerMessage;

    @Override
    public void handle(CommandSender sender, LiteInvocation invocation, RequiredPermissions requiredPermissions) {
        managerMessage.sendMessage(sender, configurationMessage.getCommandsNoPermission().getFailed());
    }
}
