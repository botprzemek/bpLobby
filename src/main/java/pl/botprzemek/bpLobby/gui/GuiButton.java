package pl.botprzemek.bpLobby.gui;

import eu.okaeri.configs.OkaeriConfig;
import io.th0rgal.oraxen.api.OraxenItems;
import lombok.Builder;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import pl.botprzemek.bpLobby.lobby.ManagerMessage;

import java.util.List;

@Builder
@Getter
public class GuiButton extends OkaeriConfig {
    private List<Integer> slots;
    private String oraxenID;
    private String displayName;
    private List<String> lore;
    private GuiAction action;

    public ItemStack getItem(ManagerMessage managerMessage) {
        ItemStack item = OraxenItems.getItemById(oraxenID) != null ? OraxenItems.getItemById(oraxenID).build() : new ItemStack(Material.STONE);
        ItemMeta meta = item.getItemMeta();
        if (meta == null) return item;
        meta.setDisplayName(managerMessage.getMessage(displayName));
        meta.setLore(managerMessage.getMessages(lore));
        item.setItemMeta(meta);
        return item;
    }
}
