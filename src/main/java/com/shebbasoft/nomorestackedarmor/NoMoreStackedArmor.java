package com.shebbasoft.nomorestackedarmor;

import com.shebbasoft.nomorestackedarmor.temp.OpenTestGuiCommand;
import com.shebbasoft.nomorestackedarmor.temp.TestGuiEventListener;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashSet;
import java.util.Set;

public final class NoMoreStackedArmor extends JavaPlugin implements Listener {

    private static final int MAX_ARMOR_AMOUNT = 1;
    private static final String ARMOR_REMOVED_MESSAGE = ChatColor.RED + "" + ChatColor.BOLD + "[!] Your equipped stacked armor got removed from your inventory.";
    private static final Set<Material> ARMOR_ITEMS = new HashSet<>();

    static {
        ARMOR_ITEMS.add(Material.DIAMOND_HELMET);
        ARMOR_ITEMS.add(Material.DIAMOND_CHESTPLATE);
        ARMOR_ITEMS.add(Material.DIAMOND_LEGGINGS);
        ARMOR_ITEMS.add(Material.DIAMOND_BOOTS);
        ARMOR_ITEMS.add(Material.GOLD_HELMET);
        ARMOR_ITEMS.add(Material.GOLD_CHESTPLATE);
        ARMOR_ITEMS.add(Material.GOLD_LEGGINGS);
        ARMOR_ITEMS.add(Material.GOLD_BOOTS);
        ARMOR_ITEMS.add(Material.IRON_HELMET);
        ARMOR_ITEMS.add(Material.IRON_CHESTPLATE);
        ARMOR_ITEMS.add(Material.IRON_LEGGINGS);
        ARMOR_ITEMS.add(Material.IRON_BOOTS);
        ARMOR_ITEMS.add(Material.CHAINMAIL_HELMET);
        ARMOR_ITEMS.add(Material.CHAINMAIL_CHESTPLATE);
        ARMOR_ITEMS.add(Material.CHAINMAIL_LEGGINGS);
        ARMOR_ITEMS.add(Material.CHAINMAIL_BOOTS);
        ARMOR_ITEMS.add(Material.LEATHER_HELMET);
        ARMOR_ITEMS.add(Material.LEATHER_CHESTPLATE);
        ARMOR_ITEMS.add(Material.LEATHER_LEGGINGS);
        ARMOR_ITEMS.add(Material.LEATHER_BOOTS);
    }

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);


        // TODO REMOVE
        getServer().getPluginManager().registerEvents(new TestGuiEventListener(), this);
        getCommand("open").setExecutor(new OpenTestGuiCommand());

    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onArmorEquip(PlayerInteractEvent event) {
        // todo remove
        event.getPlayer().sendMessage("");
        event.getPlayer().sendMessage("event #0");

        Action action = event.getAction();
        event.getPlayer().sendMessage(action.toString());

        if (!action.equals(Action.RIGHT_CLICK_AIR) && !action.equals(Action.RIGHT_CLICK_BLOCK)) {
            return;
        }

        // todo remove
        event.getPlayer().sendMessage("event #1");

        ItemStack item = event.getItem();

        if (item == null || item.getAmount() <= MAX_ARMOR_AMOUNT || !ARMOR_ITEMS.contains(item.getType())) {
            return;
        }

        // todo remove
        event.getPlayer().sendMessage("event #2");

        event.setCancelled(true);

    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        PlayerInventory inventory = event.getPlayer().getInventory();
        boolean removedArmor = false;

        for (ItemStack armor : inventory.getArmorContents()) {
            if (armor == null || armor.getAmount() <= MAX_ARMOR_AMOUNT || !ARMOR_ITEMS.contains(armor.getType())) {
                continue;
            }

            armor.setAmount(MAX_ARMOR_AMOUNT);
            removedArmor = true;
        }

        if (removedArmor) {
            event.getPlayer().sendMessage(ARMOR_REMOVED_MESSAGE);
        }
    }
}
