package pl.botprzemek.methods;

import com.iridium.iridiumcolorapi.IridiumColorAPI;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.profile.PlayerProfile;
import pl.botprzemek.bpLobby;

public class PlayerHead {
    public ItemStack getPlayerHead(String player) {

        String playerHeadStatistics = bpLobby.plugin.getConfig().getString("player-head.name");

        Material head = Material.PLAYER_HEAD;
        ItemStack item = new ItemStack(head, 1);

        SkullMeta headMeta = (SkullMeta) item.getItemMeta();
        assert headMeta != null;
        headMeta.setOwner(player);
        assert playerHeadStatistics != null;
        headMeta.setDisplayName(IridiumColorAPI.process(playerHeadStatistics));
        item.setItemMeta(headMeta);

        return item;
    }
}
