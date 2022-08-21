package me.frosteddreams.playerreports.commands;

import me.frosteddreams.playerreports.PlayerReports;
import me.frosteddreams.playerreports.gui.ActiveReportsGUI;
import me.frosteddreams.playerreports.managers.MessageManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ReportsCommand implements CommandExecutor {

    private final PlayerReports playerReports;

    public ReportsCommand(PlayerReports playerReports) {
        this.playerReports = playerReports;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("reports")) {
            if (!(sender instanceof Player)) {
                MessageManager.sendMessage(sender, "GAME_ONLY_COMMAND");
                return true;
            }
            Player p = (Player) sender;
            if (!p.hasPermission("playerreports.staff")) {
                MessageManager.sendMessage(p, "REPORTS_COMMAND_NO_PERMISSION");
                return true;
            }
            ActiveReportsGUI activeReportsGUI = new ActiveReportsGUI(playerReports);
            playerReports.getGuiManager().setGUI(p, activeReportsGUI);
        }
        return true;
    }
}
