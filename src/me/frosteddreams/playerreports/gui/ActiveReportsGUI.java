package me.frosteddreams.playerreports.gui;

import me.frosteddreams.playerreports.PlayerReports;
import me.frosteddreams.playerreports.managers.MessageManager;
import me.frosteddreams.playerreports.reports.Report;
import me.frosteddreams.playerreports.util.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;

public class ActiveReportsGUI implements GUI {

    private final PlayerReports playerReports;
    private final Inventory inventory;

    public ActiveReportsGUI(PlayerReports playerReports) {
        this.playerReports = playerReports;
        inventory = Bukkit.createInventory(null, 54, getName());
        refreshGUI(inventory);
    }

    private void refreshGUI(Inventory inventory) {
        inventory.clear();
        for (Report report : playerReports.getActiveReports()) {
            inventory.addItem(new ItemBuilder(Material.PLAYER_HEAD).setSkullOwner(report.getTargetName()).
                    setName("&6" + report.getTargetName()).addLoreLine("").
                    addLoreLine("&aReporter: &f" + report.getReporterName()).
                    addLoreLine("&cTarget: &f" + report.getTargetName()).addLoreLine("").
                    addLoreLine("&eReason: &f" + report.getReportReason()).addLoreLine("").
                    addLoreLine("&eTime Created: &f" + report.getTimeStamp()).addLoreLine("").
                    addLoreLine("&7(Left Click to teleport to the target)").
                    addLoreLine("&7(Shift-Right Click to close report)").
                    toItemStack());
        }
    }

    @Override
    public GUI handleClick(InventoryClickEvent e) {
        if (!isInventory(e.getView())) return null;
        e.setCancelled(true);
        Player p = (Player) e.getWhoClicked();
        ClickType click = e.getClick();
        ItemStack itemStack = e.getCurrentItem();
        String itemName = ChatColor.stripColor(itemStack.getItemMeta().getDisplayName());
        if (click == ClickType.LEFT) {
            Player target = Bukkit.getPlayer(itemName);
            if (!target.isOnline()) {
                MessageManager.sendMessage(p, "REPORT_TARGET_OFFLINE", target);
            } else {
                p.teleport(target);
                MessageManager.sendMessage(p, "REPORT_TELEPORT", target);
            }
        } else if (click == ClickType.SHIFT_RIGHT) {
            playerReports.getActiveReports().remove(e.getSlot());
            refreshGUI(inventory);
            MessageManager.sendMessage(p, "REPORT_DELETED");
        }
        return null;
    }

    @Override
    public Inventory getInventory() {
        return inventory;
    }

    @Override
    public String getName() {
        return "Active Reports";
    }

    @Override
    public boolean isInventory(InventoryView view) {
        return view.getTitle().equals(getName());
    }
}