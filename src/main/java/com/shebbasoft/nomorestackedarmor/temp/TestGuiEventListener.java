package com.shebbasoft.nomorestackedarmor.temp;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.InventoryHolder;

public class TestGuiEventListener implements Listener {

    @EventHandler
    public void onGuiClickEvent(InventoryClickEvent event) {
        InventoryHolder holder = event.getInventory().getHolder();

        if (!(holder instanceof TestGui)) {
            return;
        }

        event.setCancelled(true);
    }

}
