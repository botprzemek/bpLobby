package pl.botprzemek.bpLobby.Lobby.Utils;

import io.th0rgal.oraxen.api.OraxenItems;
import org.bukkit.*;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import pl.botprzemek.bpLobby.BpLobby;
import pl.botprzemek.bpLobby.Lobby.Config.Configs.PluginConfig;
import pl.botprzemek.bpLobby.Lobby.Inventory.Button;
import pl.botprzemek.bpLobby.Lobby.LobbyManager;

import java.util.*;

public class PluginManager {

    private final BpLobby instance;

    private final MessageManager messageManager;

    private final PluginConfig pluginConfig;

    private Location spawnLocation;

    private final List<UUID> hiddenPlayers;

    private int limit;

    private final HashMap<UUID, Inventory> serverSelectors;

    private final List<Button> buttons;

    public PluginManager(LobbyManager lobbyManager) {

        instance = lobbyManager.getInstance();

        messageManager = lobbyManager.getMessageManager();

        pluginConfig = lobbyManager.getConfigManager().getPluginConfig();

        hiddenPlayers = new ArrayList<>();

        serverSelectors = new HashMap<>();

        buttons = new ArrayList<>();

        loadConfigs();

    }

    public void loadConfigs() {

        setSpawnLocation();

        setLimit();

        setButtons();

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

    public void setPlayerSelectorItem(Player player) {

        if (!player.hasPermission("bplobby.server")) return;

        ItemStack item = getPlayerSelectorItem();

        if (item == null) return;

        player.getInventory().setItem(Objects.requireNonNull(pluginConfig.getConfigurationSection("selector-item")).getInt("slot"), item);

    }

    public ItemStack getPlayerSelectorItem() {

        ConfigurationSection serverSelectorSection = pluginConfig.getConfigurationSection("selector-item");

        if (serverSelectorSection == null) return null;

        return OraxenItems.getItemById(serverSelectorSection.getString("id")).build();

    }

    public boolean isPlayerSelectorViable(Player player) {

        ConfigurationSection serverSelectorSection = pluginConfig.getConfigurationSection("selector-item");

        if (serverSelectorSection == null) return false;

        return player.getInventory().getItemInMainHand().isSimilar(getPlayerSelectorItem());

    }

    public void setButtons() {

        ConfigurationSection itemsSection = pluginConfig.getConfigurationSection("selector-gui.items");

        if (itemsSection == null) return;

        for (String itemName : itemsSection.getKeys(false)) {

            ConfigurationSection itemSection = itemsSection.getConfigurationSection(itemName);

            if (itemSection == null) return;

            Button button = new Button(
                itemSection.getInt("slot"),
                itemSection.getString("id"),
                itemSection.getString("action")
            );

            buttons.add(button);

        }

    }

    public List<Button> getButtons() {

        return buttons;

    }

    public Button getButtonBySlot(int slot) {

        for (Button button : buttons) {

            if (slot == button.getSlot()) return button;

        }

        return null;

    }

    public Button getButtonByServerName(String serverName) {

        for (Button button : buttons) {

            if (serverName.equals(button.getAction())) return button;

        }

        return null;

    }

    public void setInventory(Player player) {

        Inventory inventory = setInventoryItems(player);

        serverSelectors.put(player.getUniqueId(), inventory);

    }

    public Inventory getInventory(Player player) {

        return serverSelectors.get(player.getUniqueId());

    }

    public void removeInventory(Player player) {

        serverSelectors.remove(player.getUniqueId());

    }

    public boolean isInventorySelector(Player player, Inventory inventory) {

        return serverSelectors.get(player.getUniqueId()).equals(inventory);

    }

    public Inventory setInventoryItems(Player player) {

        ConfigurationSection inventorySection = pluginConfig.getConfigurationSection("selector-gui");

        if (inventorySection == null) return null;

        Inventory inventory = Bukkit.createInventory(player, inventorySection.getInt("size"), messageManager.getConfigStringMessage(player, "selector-gui.title"));

        List<Button> items = getButtons();

        if (items == null) {

            serverSelectors.put(player.getUniqueId(), inventory);

            return inventory;

        }

        for (Button item : items) inventory.setItem(item.getSlot(), OraxenItems.getItemById(item.getItemId()).build());

        return inventory;

    }

    public boolean isServerViable(String serverName) {

        return getButtons().contains(getButtonByServerName(serverName));

    }

}