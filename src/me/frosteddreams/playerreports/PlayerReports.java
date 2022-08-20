package me.frosteddreams.playerreports;

import org.bukkit.plugin.java.JavaPlugin;

public class PlayerReports extends JavaPlugin {

    @Override
    public void onEnable() {
        register();
    }

    private void register() {
        registerCommands();
        registerListeners();
    }

    private void registerCommands() {

    }

    private void registerListeners() {
    }
}