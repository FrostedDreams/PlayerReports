package me.frosteddreams.playerreports.gui;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class GUIManager {

    private final Map<Player, GUI> playerGUIMap = new HashMap<>();

    public GUI getOpenGUI(Player player) {
        return playerGUIMap.get(player);
    }

    public void setGUI(Player player, GUI gui) {
        playerGUIMap.put(player, gui);
        player.closeInventory();
        player.openInventory(gui.getInventory());
    }
}