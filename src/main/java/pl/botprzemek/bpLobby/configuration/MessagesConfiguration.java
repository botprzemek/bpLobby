package pl.botprzemek.bpLobby.configuration;

import eu.okaeri.configs.OkaeriConfig;
import lombok.Getter;

@Getter
public class MessagesConfiguration extends OkaeriConfig {
    private String prefix = "<gradient:#FFE04A:#FFA400>★ <bold>ʙʜɪᴠᴇ.ᴘʟ</bold> ★</gradient>";

    public class CommandsElement extends OkaeriConfig {
        private String reloadSuccess = "%prefix% Konfiguracja <gradient:#FFE04A:#FFA400><bold>przeładowana!</bold></gradient>";
        private String reloadFailed = "%prefix% Wystąpił <gradient:#FFE04A:#FFA400><bold>błąd</bold></gradient> przy przeładowywaniu <gradient:#FFE04A:#FFA400><bold>konfiguracji!</bold></gradient>";
    }
}
