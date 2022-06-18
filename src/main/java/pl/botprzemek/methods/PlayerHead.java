package pl.botprzemek.methods;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.profile.PlayerProfile;

public class PlayerHead {
    public ItemStack getPlayerHead(String player) {

        Material head = Material.PLAYER_HEAD;
        ItemStack item = new ItemStack(head, 1);

        SkullMeta headMeta = (SkullMeta) item.getItemMeta();
        assert headMeta != null;
        headMeta.setOwner(player);
        item.setItemMeta(headMeta);

        return item;
    }
}
