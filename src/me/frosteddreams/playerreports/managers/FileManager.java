package me.frosteddreams.playerreports.managers;

import me.frosteddreams.playerreports.PlayerReports;
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
        loadMessages();
    }

    private void createConfigFiles() {
        if (!playerReports.getDataFolder().exists()) {
            playerReports.getDataFolder().mkdir();
        }
        playerReports.saveResource("lang.yml", false);
        this.langFile = new File(playerReports.getDataFolder(), "lang.yml");
        this.lang = YamlConfiguration.loadConfiguration(this.langFile);
    }

    private void loadMessages() {
        FileConfiguration langFile = this.lang;
        for (String key : langFile.getConfigurationSection("").getKeys(false)) {
            MessageManager.getLangMessages().put(key, langFile.getString(key));
        }
    }
}