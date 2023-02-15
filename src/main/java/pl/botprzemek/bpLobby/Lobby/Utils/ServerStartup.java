package pl.botprzemek.bpLobby.Lobby.Utils;

import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.GameRule;
import org.bukkit.World;
import pl.botprzemek.bpLobby.Lobby.LobbyManager;

public class ServerStartup {

    public ServerStartup(LobbyManager lobbyManager) {

        World world = lobbyManager.getConfigManager().getLobbyConfig().getLobbyWorld();

        world.setSpawnLocation(lobbyManager.getConfigManager().getLobbyConfig().getLobbyLocation());

        world.setGameRule(GameRule.DO_IMMEDIATE_RESPAWN, true);
        world.setGameRule(GameRule.DISABLE_ELYTRA_MOVEMENT_CHECK, true);
        world.setGameRule(GameRule.ANNOUNCE_ADVANCEMENTS, false);
        world.setGameRule(GameRule.COMMAND_BLOCK_OUTPUT, false);
        world.setGameRule(GameRule.SPECTATORS_GENERATE_CHUNKS, false);
        world.setGameRule(GameRule.DO_FIRE_TICK, false);
        world.setGameRule(GameRule.DO_MOB_SPAWNING, false);
        world.setGameRule(GameRule.DO_MOB_LOOT, false);
        world.setGameRule(GameRule.DO_ENTITY_DROPS, false);
        world.setGameRule(GameRule.DO_INSOMNIA, false);
        world.setGameRule(GameRule.SHOW_DEATH_MESSAGES, false);
        world.setGameRule(GameRule.SEND_COMMAND_FEEDBACK, false);
        world.setGameRule(GameRule.MOB_GRIEFING, false);
        world.setGameRule(GameRule.LOG_ADMIN_COMMANDS, false);
        world.setGameRule(GameRule.FALL_DAMAGE, false);
        world.setGameRule(GameRule.FIRE_DAMAGE, false);
        world.setGameRule(GameRule.DROWNING_DAMAGE, false);
        world.setGameRule(GameRule.DO_WEATHER_CYCLE, false);
        world.setGameRule(GameRule.DO_PATROL_SPAWNING, false);

        Audience sender = lobbyManager.getAdventure().sender(lobbyManager.getInstance().getServer().getConsoleSender());

        MiniMessage mm = MiniMessage.miniMessage();

        sender.sendMessage(mm.deserialize("<gradient:#ff80f6:#ffcc70>Starting bpLobby-0.5 by botprzemek</gradient>"));
        sender.sendMessage(mm.deserialize("<gradient:#ff80f6:#ffcc70>    __          __                                       __  </gradient>"));
        sender.sendMessage(mm.deserialize("<gradient:#ff80f6:#ffcc70>   / /_  ____  / /_____  _________  ___  ____ ___  ___  / /__</gradient>"));
        sender.sendMessage(mm.deserialize("<gradient:#ff80f6:#ffcc70>  / __ \\/ __ \\/ __/ __ \\/ ___/_  / / _ \\/ __ `__ \\/ _ \\/ //_/</gradient>"));
        sender.sendMessage(mm.deserialize("<gradient:#ff80f6:#ffcc70> / /_/ / /_/ / /_/ /_/ / /    / /_/  __/ / / / / /  __/ ,<   </gradient>"));
        sender.sendMessage(mm.deserialize("<gradient:#ff80f6:#ffcc70>/_.___/\\____/\\__/ .___/_/    /___/\\___/_/ /_/ /_/\\___/_/|_|  </gradient>"));
        sender.sendMessage(mm.deserialize("<gradient:#ff80f6:#ffcc70>               /_/                                           </gradient>"));
        sender.sendMessage(mm.deserialize("<gradient:#ff80f6:#ffcc70>                                                             </gradient>"));

    }
}
