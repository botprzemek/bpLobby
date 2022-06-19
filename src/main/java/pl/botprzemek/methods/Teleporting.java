package pl.botprzemek.methods;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class Teleporting {

    public void selfTeleport(Player player, Player target) {

        Location locationTarget = target.getLocation();
        player.teleport(locationTarget);

    }

    public void playerTeleport(Player target1, Player target2) {

        Location locationTarget2 = target2.getLocation();
        target1.teleport(locationTarget2);

    }
}
