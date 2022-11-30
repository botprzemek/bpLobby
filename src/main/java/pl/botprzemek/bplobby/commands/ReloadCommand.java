//
//
//
//        ItemStack item = new ItemStack(Material.STONE, 1);
//
//                ItemMeta meta = item.getItemMeta();
//
//                meta.setDisplayName(legacyPrefix);
//
//                item.setItemMeta(meta);
//
//                event.getPlayer().getInventory().setItem(0, item);

package pl.botprzemek.bplobby.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import pl.botprzemek.bplobby.BpLobby;
import pl.botprzemek.bplobby.utils.StringSerialize;

public class ReloadCommand implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        BpLobby instance = BpLobby.getInstance();

        instance.loadInstanceConfig();

        FileConfiguration config = BpLobby.getInstanceConfig();

        if (!(sender instanceof Player)) {

            String legacyString = new StringSerialize().serializeString(config.getString("reload")
                    .replace("%prefix%", config.getString("prefix")));

            sender.sendMessage(legacyString);

            return true;

        }

        Player player = (Player) sender;

        String legacyString = new StringSerialize().serializeString(player, config.getString("reload")
                .replace("%prefix%", config.getString("prefix")));

        sender.sendMessage(legacyString);

        return true;

    }
}
