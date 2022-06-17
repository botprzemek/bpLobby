package pl.botprzemek.methods;

import org.bukkit.entity.Player;

public class WalkingSpeed{
    public float setWalkSpeed(Player player, int speed) {
        float walkingSpeed = (float) 0.1 * speed;
        player.setWalkSpeed(walkingSpeed);
        return walkingSpeed;
    }
}
