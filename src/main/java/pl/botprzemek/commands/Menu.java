//package pl.botprzemek.commands;
//
//import org.bukkit.Bukkit;
//import org.bukkit.Material;
//import org.bukkit.Sound;
//import org.bukkit.command.Command;
//import org.bukkit.command.CommandExecutor;
//import org.bukkit.command.CommandSender;
//import org.bukkit.entity.Player;
//import org.bukkit.event.inventory.InventoryClickEvent;
//import org.bukkit.inventory.Inventory;
//import org.bukkit.inventory.ItemStack;
//import org.bukkit.inventory.meta.ItemMeta;
//import pl.botprzemek.bpLobby;
//
//import java.util.Objects;
//
//public class Menu implements CommandExecutor {
//
//    public void onClick(InventoryClickEvent event){
//        event.setCancelled(true);
//    }
//
//    @Override
//    public boolean onCommand(CommandSender sender, Command command, String label, String [] args) {
//
//        Player player = (Player) sender;
//
//        if (args.length == 0){
//            player.sendMessage("Złe użycie komendy!");
//            return false;
//        }
//        if(sender instanceof Player){
//            if(Objects.equals(args[0], "menu")){
//                String iname = bpLobby.plugin.getConfig().getString("inventory.name");
//                int isize = Integer.parseInt(bpLobby.plugin.getConfig().getString("inventory.size"));
//
//                String fname = bpLobby.plugin.getConfig().getString("fill.name");
//                String fmaterial = bpLobby.plugin.getConfig().getString("fill.material").toUpperCase().replace(' ', '_');
//                int famount = Integer.parseInt(bpLobby.plugin.getConfig().getString("fill.amount"));
//
//                String cname = bpLobby.plugin.getConfig().getString("center.name");
//                String cmaterial = bpLobby.plugin.getConfig().getString("center.material").toUpperCase().replace(' ', '_');
//                int camount = Integer.parseInt(bpLobby.plugin.getConfig().getString("center.amount"));
//
//                String sound = bpLobby.plugin.getConfig().getString("inventory.sound").toUpperCase().replace(' ', '_');
//
//                ItemStack filling = new ItemStack(Material.matchMaterial(fmaterial), famount);
//                ItemMeta fmeta = filling.getItemMeta();
//                if(fmeta != null){
//                    fmeta.setDisplayName(fname);
//                    filling.setItemMeta(fmeta);
//                }
//
//                ItemStack center = new ItemStack(Material.matchMaterial(cmaterial), camount);
//                ItemMeta cmeta = center.getItemMeta();
//                if(cmeta != null){
//                    cmeta.setDisplayName(cname);
//                    center.setItemMeta(cmeta);
//                }
//
//                Inventory inv = Bukkit.createInventory(player, isize, iname);
//
//                for(int i=0;i<isize;++i){
//                    inv.setItem(i, filling);
//                }
//
//                inv.setItem(Math.round(isize/2), center);
//
//                player.openInventory(inv);
//                player.playSound(player.getLocation(), Sound.valueOf(sound), 1.0f, 1.0f);
//            }
//        }
//        return true;
//    }
//}
