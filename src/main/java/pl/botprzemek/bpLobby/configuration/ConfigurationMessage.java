package pl.botprzemek.bpLobby.configuration;

import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.Comment;
import lombok.Getter;

@Getter
public class ConfigurationMessage extends OkaeriConfig {
    @Comment("Prefix")
    private final String prefix = "鱂";
    @Comment("No permission message")
    private final CommandsNoPermission commandsNoPermission = new CommandsNoPermission();
    @Comment("Invalid usage message")
    private final CommandsInvalid commandsInvalid = new CommandsInvalid();
    @Comment("Player Command")
    private final CommandsPlayer commandsPlayer = new CommandsPlayer();
    @Comment("Reload Command")
    private final CommandsReload commandsReload = new CommandsReload();
    @Comment("Server Command")
    private final CommandsServer commandsServer = new CommandsServer();
    @Comment("Vanish Command")
    private final CommandsVanish commandsVanish = new CommandsVanish();
    @Comment("Connect Events")
    private final EventsConnect eventsConnect = new EventsConnect();
    @Comment("Disconnect Events")
    private final EventsDisconnect eventsDisonnect = new EventsDisconnect();
    @Comment("Disconnect Events")
    private final EventsChat eventsChat = new EventsChat();
    @Comment("Link Events")
    private final String eventsLink = "%prefix% %value_1%";
    @Comment("Sounds")
    private final Sounds sounds = new Sounds();

    @Getter
    public class CommandsNoPermission extends OkaeriConfig {
        private final String failed = "%prefix% Nie masz <gradient:#4fa943:#9ec52f><bold>dostępu</bold></gradient> do tej <gradient:#4fa943:#9ec52f><bold>komendy!</bold></gradient>";
    }

    @Getter
    public class CommandsInvalid extends OkaeriConfig {
        private final String invalid = "%prefix% Niepoprawne użycie <gradient:#4fa943:#9ec52f><bold>komendy!</bold></gradient>";
        private final String invalidUsage = "%prefix% Niepoprawne użycie <gradient:#4fa943:#9ec52f><bold>komendy!</bold></gradient> Spróbuj użyć <gradient:#4fa943:#9ec52f><bold>%value_1%!</bold></gradient>";
    }

    @Getter
    public class CommandsPlayer extends OkaeriConfig {
        private final String offline = "%prefix% Wybrany gracz jest <gradient:#4fa943:#9ec52f><bold>offline!</bold></gradient>";
        private final String only = "%prefix% Użycie dostępne tylko dla <gradient:#4fa943:#9ec52f><bold>graczy!</bold></gradient>";
    }

    @Getter
    public class CommandsReload extends OkaeriConfig {
        private final String success = "%prefix% Konfiguracja <gradient:#4fa943:#9ec52f><bold>przeładowana!</bold></gradient>";
        private final String failed = "%prefix% Wystąpił <gradient:#4fa943:#9ec52f><bold>błąd</bold></gradient> przy przeładowywaniu <gradient:#4fa943:#9ec52f><bold>konfiguracji!</bold></gradient>";
    }

    @Getter
    public class CommandsServer extends OkaeriConfig {
        private final String success = "%prefix% Zostałeś/aś przeniesiony/a na <gradient:#4fa943:#9ec52f><bold><bold>%value_1%!</bold></gradient>";
        private final String error = "%prefix% Wystąpił <gradient:#4fa943:#9ec52f><bold>błąd</bold></gradient> przy przenoszeniu na <gradient:#4fa943:#9ec52f><bold>%value_1%!</bold></gradient>";
        private final String invalid = "%prefix% Serwer, który wpisałeś/aś nie <gradient:#4fa943:#9ec52f><bold>istnieje!</bold></gradient>";
        private final String notFound = "%prefix% Serwer, który wpisałeś/aś nie jest <gradient:#4fa943:#9ec52f><bold>dostępny!</bold></gradient>";
    }

    @Getter
    public class CommandsVanish extends OkaeriConfig {
        private final String show = "%prefix% Teraz widzisz innych <gradient:#4fa943:#9ec52f><bold>graczy!</bold></gradient>";
        private final String hide = "%prefix% Ukryłeś/aś innych <gradient:#4fa943:#9ec52f><bold>graczy!</bold></gradient>";
    }

    @Getter
    public class EventsConnect extends OkaeriConfig {
        private final String join = "%prefix% %value_1% dołączył/a do <gradient:#4fa943:#9ec52f><bold>Lobby!</bold></gradient>";
        private final String quit = "%prefix% %value_1% opuścił/a <gradient:#4fa943:#9ec52f><bold>Lobby!</bold></gradient>";
    }

    @Getter
    public class EventsDisconnect extends OkaeriConfig {
        private final String timeout = "<gradient:#4fa943:#9ec52f><bold>ᴇхᴏᴛɪᴀ.ɴᴇᴛ</bold></gradient>\n\nMinął czas na <gradient:#4fa943:#9ec52f><bold>wybór trybu!</bold></gradient>\nPołącz się <gradient:#4fa943:#9ec52f><bold>ponownie!</bold></gradient>\n\n<gradient:#4fa943:#9ec52f><bold>ᴅᴄ.ᴇхᴏᴛɪᴀ.ɴᴇᴛ</bold></gradient>";
    }

    @Getter
    public class EventsChat extends OkaeriConfig {
        private final String success = "%vault_prefix% %value_1% %value_2%%value_3%";
        private final String failed = "%prefix% Nie masz <gradient:#4fa943:#9ec52f><bold>dostępu</bold></gradient> do wysyłania <gradient:#4fa943:#9ec52f><bold>wiadomości!</bold></gradient>";
    }

    @Getter
    public class Sounds extends OkaeriConfig {
        private final String success = "ui_toast_challenge_complete";
        private final String activate = "block_note_block_pling";
        private final String step = "block_note_block_xylophone";
        private final String error = "block_note_block_bit";
        private final String click = "ui_button_click";
    }
}
