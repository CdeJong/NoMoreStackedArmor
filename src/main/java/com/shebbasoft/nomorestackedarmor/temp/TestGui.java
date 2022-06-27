package com.shebbasoft.nomorestackedarmor.temp;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

public class TestGui implements InventoryHolder {

    private final Inventory inventory;

    public TestGui() {
        this.inventory = Bukkit.createInventory(this, 45, "Test GUI");
        this.inventory.setItem(22, new ItemStack(Material.INK_SACK, 1, (byte) 10));
    }


    @Override
    public Inventory getInventory() {
        return inventory;
    }
}
