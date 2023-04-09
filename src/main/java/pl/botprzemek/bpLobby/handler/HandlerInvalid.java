package pl.botprzemek.bpLobby.handler;

import dev.rollczi.litecommands.command.LiteInvocation;
import dev.rollczi.litecommands.handle.InvalidUsageHandler;
import dev.rollczi.litecommands.schematic.Schematic;
import eu.okaeri.injector.annotation.Inject;
import org.bukkit.command.CommandSender;
import pl.botprzemek.bpLobby.configuration.ConfigurationMessage;
import pl.botprzemek.bpLobby.lobby.ManagerMessage;

import java.util.List;

public class HandlerInvalid implements InvalidUsageHandler<CommandSender> {
    @Inject
    private ConfigurationMessage configurationMessage;
    @Inject
    private ManagerMessage managerMessage;

    @Override
    public void handle(CommandSender sender, LiteInvocation invocation, Schematic schematic) {
        List<String> schematics = schematic.getSchematics();
        if (schematics.size() == 1) {
            managerMessage.sendMessage(sender, configurationMessage.getCommandsInvalid().getInvalid());
            return;
        }

        managerMessage.sendMessage(sender, configurationMessage.getCommandsInvalid().getInvalidUsage());
        schematics.forEach(sch -> sender.sendMessage("&8 >> &7" + sch));
    }
}
