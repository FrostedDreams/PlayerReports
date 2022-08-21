package me.frosteddreams.playerreports.commands;

import me.frosteddreams.playerreports.PlayerReports;
import me.frosteddreams.playerreports.managers.MessageManager;
import me.frosteddreams.playerreports.reports.Report;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ReportCommand implements CommandExecutor {

    private final PlayerReports playerReports;

    public ReportCommand(PlayerReports playerReports) {
        this.playerReports = playerReports;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("report")) {
            if (!(sender instanceof Player)) {
                MessageManager.sendMessage(sender, "GAME_ONLY_COMMAND");
                return true;
            }
            Player p = (Player) sender;
            if (!p.hasPermission("playerreports.use")) {
                MessageManager.sendMessage(p, "REPORT_COMMAND_NO_PERMISSION");
                return true;
            }
            if (args.length < 2) {
                MessageManager.sendMessage(p, "REPORT_COMMAND_USAGE");
                return true;
            }
            Player target = Bukkit.getPlayer(args[0]);
            if (target == null || !target.isOnline()) {
                MessageManager.sendMessage(p, "REPORT_COMMAND_UNKNOWN_TARGET");
                return true;
            }
            StringBuilder sb = new StringBuilder();
            for (int i = 1; i < args.length; i++) {
                sb.append(args[i]).append(" ");
            }
            String msg = sb.toString();
            Report report = new Report(p.getUniqueId(), target.getUniqueId(), msg);
            playerReports.getActiveReports().add(report);
            MessageManager.sendMessage(p, "REPORT_COMMAND_REPORT_CREATED");
            MessageManager.broadcast(p, "REPORT_CREATED_BROADCAST", target);
        }
        return true;
    }
}