package pl.botprzemek.bpLobby.configuration;

import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.Comment;
import lombok.Getter;

@Getter
public class ConfigurationMessage extends OkaeriConfig {
    @Comment("Prefix")
    private String prefix = "鱂";
    @Comment("No permission message")
    private CommandsNoPermission commandsNoPermission = new CommandsNoPermission();
    @Comment("Invalid usage message")
    private CommandsInvalid commandsInvalid = new CommandsInvalid();
    @Comment("Player Command")
    private CommandsPlayer commandsPlayer = new CommandsPlayer();
    @Comment("Reload Command")
    private CommandsReload commandsReload = new CommandsReload();
    @Comment("Server Command")
    private CommandsServer commandsServer = new CommandsServer();
    @Comment("Vanish Command")
    private CommandsVanish commandsVanish = new CommandsVanish();
    @Comment("Connect Events")
    private EventsConnect eventsConnect = new EventsConnect();
    @Comment("Disconnect Events")
    private EventsDisconnect eventsDisonnect = new EventsDisconnect();
    @Comment("Disconnect Events")
    private EventsChat eventsChat = new EventsChat();
    @Comment("Sounds")
    private Sounds sounds = new Sounds();

    @Getter
    public class CommandsNoPermission extends OkaeriConfig {
        private String failed = "%prefix% Nie masz <gradient:#4fa943:#9ec52f><bold>dostępu</bold></gradient> do tej <gradient:#4fa943:#9ec52f><bold>komendy!</bold></gradient>";
    }

    @Getter
    public class CommandsInvalid extends OkaeriConfig {
        private String invalid = "%prefix% Niepoprawne użycie <gradient:#4fa943:#9ec52f><bold>komendy!</bold></gradient>";
        private String invalidUsage = "%prefix% Niepoprawne użycie <gradient:#4fa943:#9ec52f><bold>komendy!</bold></gradient> Spróbuj użyć <gradient:#4fa943:#9ec52f><bold>%value_1%!</bold></gradient>";
    }

    @Getter
    public class CommandsPlayer extends OkaeriConfig {
        private String offline = "%prefix% Wybrany gracz jest <gradient:#4fa943:#9ec52f><bold>offline!</bold></gradient>";
        private String only = "%prefix% Użycie dostępne tylko dla <gradient:#4fa943:#9ec52f><bold>graczy!</bold></gradient>";
    }

    @Getter
    public class CommandsReload extends OkaeriConfig {
        private String success = "%prefix% Konfiguracja <gradient:#4fa943:#9ec52f><bold>przeładowana!</bold></gradient>";
        private String failed = "%prefix% Wystąpił <gradient:#4fa943:#9ec52f><bold>błąd</bold></gradient> przy przeładowywaniu <gradient:#4fa943:#9ec52f><bold>konfiguracji!</bold></gradient>";
    }

    @Getter
    public class CommandsServer extends OkaeriConfig {
        private String success = "%prefix% Zostałeś/aś przeniesiony/a na <gradient:#4fa943:#9ec52f><bold><bold>%value_1%!</bold></gradient>";
        private String error = "%prefix% Wystąpił <gradient:#4fa943:#9ec52f><bold>błąd</bold></gradient> przy przenoszeniu na <gradient:#4fa943:#9ec52f><bold>%value_1%!</bold></gradient>";
        private String invalid = "%prefix% Serwer, który wpisałeś/aś nie <gradient:#4fa943:#9ec52f><bold>istnieje!</bold></gradient>";
        private String notFound = "%prefix% Serwer, który wpisałeś/aś nie jest <gradient:#4fa943:#9ec52f><bold>dostępny!</bold></gradient>";
    }

    @Getter
    public class CommandsVanish extends OkaeriConfig {
        private String show = "%prefix% Teraz widzisz innych <gradient:#4fa943:#9ec52f><bold>graczy!</bold></gradient>";
        private String hide = "%prefix% Ukryłeś/aś innych <gradient:#4fa943:#9ec52f><bold>graczy!</bold></gradient>";
    }

    @Getter
    public class EventsConnect extends OkaeriConfig {
        private String join = "%prefix% %value_1% dołączył/a do <gradient:#4fa943:#9ec52f><bold>Lobby!</bold></gradient>";
        private String quit = "%prefix% %value_1% opuścił/a <gradient:#4fa943:#9ec52f><bold>Lobby!</bold></gradient>";
    }

    @Getter
    public class EventsDisconnect extends OkaeriConfig {
        private String timeout = "<gradient:#4fa943:#9ec52f><bold>ᴇхᴏᴛɪᴀ.ɴᴇᴛ</bold></gradient>\n\nMinął czas na <gradient:#4fa943:#9ec52f><bold>wybór trybu!</bold></gradient>\nPołącz się <gradient:#4fa943:#9ec52f><bold>ponownie!</bold></gradient>\n\n<gradient:#4fa943:#9ec52f><bold>ᴅᴄ.ᴇхᴏᴛɪᴀ.ɴᴇᴛ</bold></gradient>";
    }

    @Getter
    public class EventsChat extends OkaeriConfig {
        private String success = "%vault_prefix% %value_1% %value_2%%value_3%";
        private String failed = "%prefix% Nie masz <gradient:#4fa943:#9ec52f><bold>dostępu</bold></gradient> do wysyłania <gradient:#4fa943:#9ec52f><bold>wiadomości!</bold></gradient>";
    }

    @Getter
    public class Sounds extends OkaeriConfig {
        private String success = "ui_toast_challenge_complete";
        private String activate = "block_note_block_pling";
        private String step = "block_note_block_xylophone";
        private String error = "block_note_block_bit";
        private String click = "ui_button_click";
    }
}
