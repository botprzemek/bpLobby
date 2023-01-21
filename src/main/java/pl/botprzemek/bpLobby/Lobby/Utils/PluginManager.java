package pl.botprzemek.bpLobby.Lobby.Utils;

import io.th0rgal.oraxen.api.OraxenItems;
import org.bukkit.*;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import pl.botprzemek.bpLobby.BpLobby;
import pl.botprzemek.bpLobby.Lobby.Config.Configs.PluginConfig;
import pl.botprzemek.bpLobby.Lobby.LobbyManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class PluginManager {

    private final BpLobby instance;

    private final MessageManager messageManager;

    private final PluginConfig pluginConfig;

    private Location spawnLocation;

    private final List<UUID> hiddenPlayers;

    private int limit;

    public PluginManager(LobbyManager lobbyManager) {

        instance = lobbyManager.getInstance();

        messageManager = lobbyManager.getMessageManager();

        pluginConfig = lobbyManager.getConfigManager().getPluginConfig();

        hiddenPlayers = new ArrayList<>();

        loadConfigs();



    }

    public void loadConfigs() {

        setSpawnLocation();

        setLimit();

    }

    public void setSpawnLocation() {

        ConfigurationSection spawnSection = pluginConfig.getConfigurationSection("spawn");

        if (spawnSection == null) return;

        World world = Bukkit.getWorld(Objects.requireNonNull(spawnSection.getString("world")));

        double x = spawnSection.getDouble("x");
        double y = spawnSection.getDouble("y");
        double z = spawnSection.getDouble("z");

        float yaw = (float) spawnSection.getDouble("yaw");
        float pitch = (float) spawnSection.getDouble("pitch");

        spawnLocation = new Location(world, x, y, z, yaw, pitch);

    }

    public Location getSpawnLocation() {

        return spawnLocation;

    }

    public void setLimit() {

        ConfigurationSection spawnSection = pluginConfig.getConfigurationSection("spawn");

        if (spawnSection == null) return;

        limit = spawnSection.getInt("spawn.limit");

    }

    public int getLimit() {

        return limit;

    }

    public void setHiddenPlayer(Player player) {

        hiddenPlayers.add(player.getUniqueId());

        for (Player target : Bukkit.getOnlinePlayers()) player.hidePlayer(instance, target);

    }

    public void setNewHiddenPlayer(Player target) {

        for (UUID playerUUID : hiddenPlayers) Objects.requireNonNull(Bukkit.getPlayer(playerUUID)).hidePlayer(instance, target);

    }

    public boolean isHiddenPlayer(Player player) {

        return hiddenPlayers.contains(player.getUniqueId());

    }

    public void clearHiddenPlayer(Player player) {

        hiddenPlayers.remove(player.getUniqueId());

        for (Player target : Bukkit.getOnlinePlayers()) player.showPlayer(instance, target);

    }

    public void createCustomElements(Player player) {

        messageManager.playPlayerSound(player, "activate");

        ConfigurationSection particle = pluginConfig.getConfigurationSection("particle");

        if (particle == null) return;

        Particle.DustTransition dustTransition = new Particle.DustTransition(
                Color.fromRGB(Integer.parseInt(Objects.requireNonNull(particle.getString("color1")).replace("#", ""), 16)),
                Color.fromRGB(Integer.parseInt(Objects.requireNonNull(particle.getString("color2")).replace("#", ""), 16)), (float) particle.getDouble("size"));

        new BukkitRunnable(){
            double t = 0;

            public void run(){
                Location location = player.getLocation();
                location.setY(location.getY() + t/2);

                double size = particle.getDouble("radius");

                size += t;

                t += 0.05;

                for (int d = 0; d <= 16 * particle.getInt("amount"); d += 1) {
                    Location particleLoc = new Location(location.getWorld(), location.getX(), location.getY()+particle.getDouble("y-offset"), location.getZ());
                    particleLoc.setX(location.getX() + Math.cos(d) * size);
                    particleLoc.setZ(location.getZ() + Math.sin(d) * size);
                    player.spawnParticle(Particle.valueOf(Objects.requireNonNull(particle.getString("name")).toUpperCase()), particleLoc, particle.getInt("amount"), dustTransition);
                }
                if (t > particle.getInt("time")) {
                    this.cancel();
                }
            }
        }.runTaskTimer(instance, 0, particle.getInt("speed"));
    }

    public void setPlayerSelector(Player player) {

        if (!player.hasPermission("bplobby.server")) return;

        ConfigurationSection serverSelectorSection = pluginConfig.getConfigurationSection("selector-item");

        if (serverSelectorSection == null) return;

        ItemStack item = OraxenItems.getItemById(serverSelectorSection.getString("id")).build();

        if (item == null) return;

        player.getInventory().setItem(serverSelectorSection.getInt("slot"), item);

    }

}
