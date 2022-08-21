package me.frosteddreams.playerreports.managers;

import me.frosteddreams.playerreports.PlayerReports;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class FileManager {

    private final PlayerReports playerReports;

    private File langFile;
    private FileConfiguration lang;

    public FileManager(PlayerReports playerReports) {
        this.playerReports = playerReports;
        createConfigFiles();
    }

    private void createConfigFiles() {
        if (!playerReports.getDataFolder().exists()) {
            playerReports.getDataFolder().mkdir();
        }
        playerReports.saveResource("lang.yml", false);
        this.langFile = new File(playerReports.getDataFolder(), "lang.yml");
        this.lang = YamlConfiguration.loadConfiguration(this.langFile);
        loadMessages();
    }

    private void loadMessages() {
        FileConfiguration langFile = playerReports.getFileManager().getLangFile();
        for (String key : langFile.getConfigurationSection("").getKeys(false)) {
            playerReports.getLangMessages().put(key, langFile.getString(key));
            Bukkit.broadcastMessage("KEY: " + key + " - STRING: " + langFile.getString(key));
        }
    }

    public FileConfiguration getLangFile() {
        return lang;
    }
}