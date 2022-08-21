package me.frosteddreams.playerreports.gui;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;

public interface GUI {

    GUI handleClick(InventoryClickEvent e);

    Inventory getInventory();

    String getName();

    boolean isInventory(InventoryView view);

}