package me.frosteddreams.playerreports;

import me.frosteddreams.playerreports.commands.ReportCommand;
import me.frosteddreams.playerreports.commands.ReportsCommand;
import me.frosteddreams.playerreports.gui.GUIManager;
import me.frosteddreams.playerreports.listeners.InventoryClick;
import me.frosteddreams.playerreports.managers.FileManager;
import me.frosteddreams.playerreports.reports.Report;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class PlayerReports extends JavaPlugin {

    private final List<Report> activeReports = new ArrayList<>();
    private GUIManager guiManager;

    @Override
    public void onEnable() {
        register();
    }

    private void register() {
        registerCommands();
        registerListeners();
        registerManagers();
    }

    private void registerCommands() {
        getCommand("report").setExecutor(new ReportCommand(this));
        getCommand("reports").setExecutor(new ReportsCommand(this));
    }

    private void registerListeners() {
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new InventoryClick(this), this);
    }

    private void registerManagers() {
        new FileManager(this);
        this.guiManager = new GUIManager();
    }

    public GUIManager getGuiManager() {
        return guiManager;
    }

    public List<Report> getActiveReports() {
        return activeReports;
    }
}