package me.frosteddreams.playerreports.managers;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public final class MessageManager {

    private static final String IMPROPER_LANG = "&b[&dReport&b] &cThe lang.yml is not setup correctly.";
    private static final Map<String, String> langMessages = new HashMap<>();

    public static void sendMessage(CommandSender sender, String key) {
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', getLangMessage(key)));
    }

    public static void sendMessage(Player sender, String key, Player target) {
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', getLangMessage(key, sender, target)));
    }

    public static void broadcast(Player p, String key, Player target) {
        Bukkit.broadcast(ChatColor.translateAlternateColorCodes('&', getLangMessage(key, p, target)), "playerreports.staff");
    }

    private static String getLangMessage(String key) {
        if (langMessages.get(key) == null) {
            return IMPROPER_LANG;
        }
        return langMessages.get(key).replaceAll("%prefix%", langMessages.get("REPORT_PREFIX"));
    }

    private static String getLangMessage(String key, Player p, Player target) {
        if (langMessages.get(key) == null) {
            return IMPROPER_LANG;
        }
        return langMessages.get(key).replaceAll("%prefix%", langMessages.get("REPORT_PREFIX")).replaceAll("%player%", p.getName()).replaceAll("%target%", target.getName());
    }

    public static Map<String, String> getLangMessages() {
        return langMessages;
    }
}