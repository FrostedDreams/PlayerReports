package me.frosteddreams.playerreports.listeners;

import me.frosteddreams.playerreports.PlayerReports;
import me.frosteddreams.playerreports.gui.GUI;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryClick implements Listener {

    private final PlayerReports playerReports;

    public InventoryClick(PlayerReports playerReports) {
        this.playerReports = playerReports;
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        if (e.getCurrentItem() == null) return;
        if (e.getCurrentItem().getItemMeta() == null) return;
        Player p = (Player) e.getWhoClicked();
        GUI gui = playerReports.getGuiManager().getOpenGUI(p);
        if (gui == null) return;
        GUI newGUI = gui.handleClick(e);
        if (newGUI != null) {
            playerReports.getGuiManager().setGUI(p, newGUI);
        }
    }
}