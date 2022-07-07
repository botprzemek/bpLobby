package pl.botprzemek.methods;

import com.iridium.iridiumcolorapi.IridiumColorAPI;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.profile.PlayerProfile;
import pl.botprzemek.bpLobby;

import java.lang.reflect.Field;

public class PlayerHead {

    private SkullMeta getSkull(String url, GameProfile profile, ItemStack itemStack) {
        if (!url.isEmpty()) {
            SkullMeta headMeta = (SkullMeta) itemStack.getItemMeta();
            profile.getProperties().put("textures", new Property("textures", url));

            try {
                assert headMeta != null;
                Field profileField = headMeta.getClass().getDeclaredField("profile");
                profileField.setAccessible(true);
                profileField.set(headMeta, profile);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
            return headMeta;
        } else {
            return null;
        }
    }

    public ItemStack getPlayerHead(PlayerProfile playerProfile) {

        String playerHeadName = bpLobby.plugin.getConfig().getString("player-head.name");

        Material head = Material.PLAYER_HEAD;
        ItemStack item = new ItemStack(head, 1);

        SkullMeta headMeta = (SkullMeta) item.getItemMeta();
        assert headMeta != null;
        headMeta.setOwnerProfile(playerProfile);
        assert playerHeadName != null;
        headMeta.setDisplayName(IridiumColorAPI.process(playerHeadName));
        item.setItemMeta(headMeta);

        return item;
    }

    public SkullMeta getCustomHead(String headURL, GameProfile profile, String headDisplay) {

        Material head = Material.PLAYER_HEAD;
        ItemStack item = new ItemStack(head, 1);

        SkullMeta headMeta = getSkull(headURL, profile, item);
        assert headMeta != null;
        headMeta.setDisplayName(IridiumColorAPI.process(headDisplay));
        item.setItemMeta(headMeta);

        return headMeta;
    }
}
