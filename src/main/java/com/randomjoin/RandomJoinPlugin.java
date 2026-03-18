package com.randomjoin;

import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

public class RandomJoinPlugin extends Plugin {

    private Configuration config;

    @Override
    public void onEnable() {
        loadConfig();
        getProxy().getPluginManager().registerCommand(this, new RandomJoinCommand(this));
        getLogger().info("The RandomJoin plugin has been enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("The RandomJoin plugin has been disabled!");
    }

    public void loadConfig() {
        if (!getDataFolder().exists()) {
            getDataFolder().mkdir();
        }

        File configFile = new File(getDataFolder(), "config.yml");

        if (!configFile.exists()) {
            try (InputStream in = getResourceAsStream("config.yml")) {
                Files.copy(in, configFile.toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(configFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void reloadConfig() {
        loadConfig();
    }

    public Configuration getConfig() {
        return config;
    }
}
