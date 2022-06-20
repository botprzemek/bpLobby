package pl.botprzemek.methods;

import com.iridium.iridiumcolorapi.IridiumColorAPI;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class DropItems {

    private final Material material;
    private final String customName;
    private final Map<Enchantment, Integer> enchantmentToLevelMap = new HashMap<>();
    private double chance;
    private int minAmount;
    private int maxAmount;

    public DropItems(ConfigurationSection section) {

        Material material;

        try {

            material = Material.valueOf(section.getString("material").toUpperCase().replace(" ", "_"));

        }

        catch (Exception e) {

            material = Material.AIR;

        }

        this.material = material;
        this.customName = IridiumColorAPI.process(Objects.requireNonNull(section.getString("name")));

        ConfigurationSection enchantmentsSection = section.getConfigurationSection("enchantments");

        if (enchantmentsSection != null) {

            for (String enchantmentKey : enchantmentsSection.getKeys(false)){

                Enchantment enchantment = Enchantment.getByKey(
                        NamespacedKey.minecraft(
                                enchantmentKey.toLowerCase(Locale.ROOT)
                        )
                );

                if (enchantment != null) {

                    int level = enchantmentsSection.getInt(enchantmentKey);
                    enchantmentToLevelMap.put(enchantment, level);

                }

            }

        }

        this.chance = section.getDouble("chance");
        this.minAmount = section.getInt("min-amount");
        this.maxAmount = section.getInt("max-amount");

    }

    public boolean shouldDrop(Random random) {

        double num = random.nextDouble();
        return  num > chance;

    }

    public ItemStack makeItem(ThreadLocalRandom random) {

        int amount = random.nextInt(minAmount, maxAmount+1);
        ItemStack item = new ItemStack(material, amount);
        ItemMeta meta = item.getItemMeta();

        if (customName == null) return null;

        assert meta != null;
        meta.setDisplayName(customName);

        if (enchantmentToLevelMap.isEmpty()) {

            item.setItemMeta(meta);
            return item;

        }

        for (Map.Entry<Enchantment, Integer> enchantEntry : enchantmentToLevelMap.entrySet()) {

            item.addEnchantment(

                    enchantEntry.getKey(),
                    enchantEntry.getValue()
            );

        }

        item.setItemMeta(meta);

        return item;

    }

}
