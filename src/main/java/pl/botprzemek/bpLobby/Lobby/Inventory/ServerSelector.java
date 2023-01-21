//package pl.botprzemek.bpLobby.Lobby.Inventory;
//
//import io.th0rgal.oraxen.api.OraxenItems;
//import org.bukkit.entity.Player;
//import org.bukkit.inventory.Inventory;
//import org.bukkit.inventory.ItemStack;
//import pl.botprzemek.bpLobby.Lobby.Config.Configs.InventoryConfig;
//import pl.botprzemek.bpLobby.Lobby.LobbyManager;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.UUID;
//
//public class ServerSelector {
//
//    private final HashMap<UUID, Inventory> serverSelectors;
//
//    private final String serverSelectorName;
//
//    public ServerSelector(LobbyManager lobbyManager) {
//
//        serverSelectorName = "selector";
//
//        serverSelectors = new HashMap<>();
//
//    }
//
//    public void setInventory(Player player) {
//
//        Inventory inventory = inventoryConfig.getInventory(player, serverSelectorName);
//
//        inventory = setInventoryItems(player, inventory);
//
//        serverSelectors.put(player.getUniqueId(), inventory);
//
//    }
//
//    public Inventory getInventory(Player player) {
//
//        return serverSelectors.get(player.getUniqueId());
//
//    }
//
//    public void removeInventory(Player player) {
//
//        serverSelectors.remove(player.getUniqueId());
//
//    }
//
//    public Inventory setInventoryItems(Player player, Inventory inventory) {
//
//        List<Button> items = inventoryConfig.getInventoryItems(serverSelectorName);
//
//        if (items == null) {
//
//            serverSelectors.put(player.getUniqueId(), inventory);
//
//            return inventory;
//
//        }
//
//        for (Button item : items) inventory.setItem(item.getSlot(), OraxenItems.getItemById(item.getItemID()).build());
//
//        return inventory;
//
//    }
//
//    public List<Button> getInventoryItems() {
//
//        return inventoryConfig.getInventoryItems(serverSelectorName);
//
//    }
//
//    public List<String> getServerSelectorNames() {
//
//        return inventoryConfig.getServerNames(serverSelectorName);
//
//    }
//
//    public ItemStack getInventoryItem(int slot) {
//
//        List<Button> items = inventoryConfig.getInventoryItems(serverSelectorName);
//
//        for (Button item : items) {
//
//            if (item.getSlot() == slot) return OraxenItems.getItemById(item.getItemID()).build();
//
//        }
//
//        return null;
//
//    }
//
//    public String getServerName(int slot) {
//
//        List<Button> items = inventoryConfig.getInventoryItems(serverSelectorName);
//
//        for (Button item : items) {
//
//            if (item.getSlot() == slot) return item.getAction();
//
//        }
//
//        return null;
//
//    }
//
//}
