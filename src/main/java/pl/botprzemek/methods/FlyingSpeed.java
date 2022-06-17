package pl.botprzemek.methods;

import org.bukkit.entity.Player;

public class FlyingSpeed {
    public float setFlySpeed(Player player, int speed) {
        float flyingSpeed = (float) 0.25 * speed;
        player.setFlySpeed(flyingSpeed);
        return flyingSpeed;
    }
}
