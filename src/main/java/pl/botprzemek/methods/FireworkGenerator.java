package pl.botprzemek.methods;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.meta.FireworkMeta;

public class FireworkGenerator {

    public void generateFireworks(PlayerJoinEvent event, Player player, String fireworkShape, Color fireworkColor, Color fireworkFade, int fireworkTime) {

        Firework firework = player.getWorld().spawn(event.getPlayer().getLocation(), Firework.class);
        FireworkMeta fireworkMeta = firework.getFireworkMeta();
        fireworkMeta.addEffect(FireworkEffect.builder()
                .flicker(false)
                .trail(true)
                .with(FireworkEffect.Type.valueOf(fireworkShape.toUpperCase()))
                .withColor(fireworkColor)
                .withFade(fireworkFade)
                .build());
        fireworkMeta.setPower(fireworkTime);
        firework.setFireworkMeta(fireworkMeta);

    }

    public void generateFireworks(PlayerInteractEvent event, Player player, Location loc, String fireworkShape, Color fireworkColor, Color fireworkFade, int fireworkTime) {

        Firework firework = player.getWorld().spawn(loc, Firework.class);
        FireworkMeta fireworkMeta = firework.getFireworkMeta();
        fireworkMeta.addEffect(FireworkEffect.builder()
                .flicker(false)
                .trail(true)
                .with(FireworkEffect.Type.valueOf(fireworkShape.toUpperCase()))
                .withColor(fireworkColor)
                .withFade(fireworkFade)
                .build());
        fireworkMeta.setPower(fireworkTime);
        firework.setFireworkMeta(fireworkMeta);

    }

}
