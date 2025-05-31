package org.ozaii.magEconomy;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.java.JavaPlugin;
import org.ozaii.magEconomy.API.MagEconomyAPI;
import org.ozaii.magEconomy.command.EconomyCommand;
import org.ozaii.magEconomy.config.ConfigManager;
import org.ozaii.magEconomy.database.DatabaseManager;
import org.ozaii.magEconomy.economy.services.PlayerEconomyService;
import org.ozaii.magEconomy.listeners.PlayerAccountChecker;

import java.util.logging.Handler;
import java.util.logging.Logger;
import java.util.logging.Level;

public final class MagEconomy extends JavaPlugin implements Listener {

    private static MagEconomy instance;
    private Economy econ = null;
    private org.ozaii.magEconomy.economy.Economy customEconomy;

    /* close hikari logging */
    static {
        Logger hikariLogger = Logger.getLogger("com.zaxxer.hikari");
        hikariLogger.setLevel(Level.OFF);

        for (var handler : hikariLogger.getHandlers()) {
            handler.setLevel(Level.OFF);
        }
        hikariLogger.setUseParentHandlers(false);
    }
    @Override
    public void onEnable() {
        instance = this;

        // Config ve diğer servisler önce başlatılmalı
        ConfigManager.getInstance().initialize(this);
        ConfigManager.getInstance().createConfig("config");
        DatabaseManager.getInstance().initialize(this);
        PlayerEconomyService.getInstance().initialize(this);

        // Kendi economy implementasyonumuzu başlat
        customEconomy = org.ozaii.magEconomy.economy.Economy.getInstance();
        customEconomy.initialize(this);

        if (!setupEconomy()) {
            getLogger().severe("Vault economy kurulamadı!");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        EconomyCommand economyCommand = new EconomyCommand(this);
        PlayerAccountChecker.getInstance().initialize(this);

        getCommand("eco").setExecutor(economyCommand);
        getCommand("eco").setTabCompleter(economyCommand);

        /* api */
        MagEconomyAPI.getInstance().initialize(PlayerEconomyService.getInstance());
        getLogger().info("MagEconomy API başlatıldı!");

        getLogger().info("MagEconomy başarıyla yüklendi!");
    }

    @Override
    public void onDisable() {
        // Cleanup işlemleri
        if (DatabaseManager.getInstance() != null) {
            DatabaseManager.getInstance().close();
        }
        instance = null;
        getLogger().info("MagEconomy devre dışı bırakıldı!");
    }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            getLogger().warning("Vault bulunamadı! Economy sistemi çalışmayabilir.");
            return false;
        }

        // Kendi economy implementasyonumuzu Vault'a kaydet
        getServer().getServicesManager().register(
                Economy.class,
                customEconomy,
                this,
                ServicePriority.Highest
        );

        // Vault'tan economy servisini al
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager()
                .getRegistration(Economy.class);

        if (rsp == null) {
            getLogger().severe("Economy servisi bulunamadı!");
            return false;
        }

        econ = rsp.getProvider();
        return econ != null;
    }


    public static MagEconomy getInstance() {
        if (instance == null) {
            throw new IllegalStateException("Plugin henüz yüklenmedi!");
        }
        return instance;
    }

    public Economy getEconomy() {
        return econ;
    }
}