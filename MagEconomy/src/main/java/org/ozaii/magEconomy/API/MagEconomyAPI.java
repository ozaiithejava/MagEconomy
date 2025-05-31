package org.ozaii.magEconomy.API;


import org.bukkit.OfflinePlayer;
import org.ozaii.magEconomy.economy.models.PlayerEconomy;
import org.ozaii.magEconomy.economy.services.PlayerEconomyService;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class MagEconomyAPI {

    private static MagEconomyAPI instance;
    private PlayerEconomyService economyService;
    private boolean initialized = false;

    private MagEconomyAPI() {}

    /**
     * API instance'ını alır
     * @return MagEconomyAPI instance
     */
    public static MagEconomyAPI getInstance() {
        if (instance == null) {
            instance = new MagEconomyAPI();
        }
        return instance;
    }

    /**
     * API'yi başlatır (Sadece MagEconomy eklentisi tarafından çağrılmalı)
     * @param economyService PlayerEconomyService instance
     */
    public void initialize(PlayerEconomyService economyService) {
        this.economyService = economyService;
        this.initialized = true;
    }

    /**
     * API'nin hazır olup olmadığını kontrol eder
     * @return boolean
     */
    public boolean isReady() {
        return initialized && economyService != null;
    }

    // === TEMEL EKONOMİ İŞLEMLERİ ===

    /**
     * Oyuncu hesabının var olup olmadığını kontrol eder
     */
    public CompletableFuture<Boolean> hasAccount(OfflinePlayer player) {
        checkInitialized();
        return economyService.hasAccount(player);
    }

    public CompletableFuture<Boolean> hasAccount(UUID playerUUID) {
        checkInitialized();
        return economyService.hasAccount(playerUUID);
    }

    /**
     * Oyuncu hesabı oluşturur
     */
    public CompletableFuture<Boolean> createAccount(OfflinePlayer player) {
        checkInitialized();
        return economyService.createAccount(player);
    }

    public CompletableFuture<Boolean> createAccount(UUID playerUUID, String playerName) {
        checkInitialized();
        return economyService.createAccount(playerUUID, playerName);
    }

    /**
     * Oyuncunun bakiyesini getirir
     */
    public CompletableFuture<Double> getBalance(OfflinePlayer player) {
        checkInitialized();
        return economyService.getBalance(player);
    }

    public CompletableFuture<Double> getBalance(UUID playerUUID) {
        checkInitialized();
        return economyService.getBalance(playerUUID);
    }

    /**
     * Oyuncunun yeterli parası olup olmadığını kontrol eder
     */
    public CompletableFuture<Boolean> has(OfflinePlayer player, double amount) {
        checkInitialized();
        return economyService.has(player, amount);
    }

    public CompletableFuture<Boolean> has(UUID playerUUID, double amount) {
        checkInitialized();
        return economyService.has(playerUUID, amount);
    }

    /**
     * Oyuncunun bakiyesinden para çıkarır
     */
    public CompletableFuture<Boolean> withdraw(OfflinePlayer player, double amount) {
        checkInitialized();
        return economyService.withdraw(player, amount);
    }

    public CompletableFuture<Boolean> withdraw(UUID playerUUID, double amount) {
        checkInitialized();
        return economyService.withdraw(playerUUID, amount);
    }

    /**
     * Oyuncunun bakiyesine para ekler
     */
    public CompletableFuture<Boolean> deposit(OfflinePlayer player, double amount) {
        checkInitialized();
        return economyService.deposit(player, amount);
    }

    public CompletableFuture<Boolean> deposit(UUID playerUUID, double amount) {
        checkInitialized();
        return economyService.deposit(playerUUID, amount);
    }

    /**
     * Oyuncunun bakiyesini ayarlar
     */
    public CompletableFuture<Boolean> setBalance(UUID playerUUID, double balance) {
        checkInitialized();
        return economyService.setBalance(playerUUID, balance);
    }

    /**
     * İki oyuncu arasında para transferi yapar
     */
    public CompletableFuture<Boolean> transfer(UUID fromUUID, UUID toUUID, double amount) {
        checkInitialized();
        return economyService.transfer(fromUUID, toUUID, amount);
    }

    // === FORMAT VE PARA BİRİMİ ===

    /**
     * Para miktarını formatlar
     */
    public String format(double amount) {
        checkInitialized();
        return economyService.format(amount);
    }

    /**
     * Para biriminin tekil adını döndürür
     */
    public String getCurrencyName() {
        checkInitialized();
        return economyService.getCurrencyName();
    }

    /**
     * Para biriminin çoğul adını döndürür
     */
    public String getCurrencyNamePlural() {
        checkInitialized();
        return economyService.getCurrencyNamePlural();
    }

    /**
     * Ondalık basamak sayısını döndürür
     */
    public int getFractionalDigits() {
        checkInitialized();
        return economyService.getFractionalDigits();
    }

    // === LİMİTLER VE AYARLAR ===

    /**
     * Başlangıç bakiyesini döndürür
     */
    public double getStartingBalance() {
        checkInitialized();
        return economyService.getStartingBalance();
    }

    /**
     * Maksimum bakiyeyi döndürür
     */
    public double getMaxBalance() {
        checkInitialized();
        return economyService.getMaxBalance();
    }

    /**
     * Minimum bakiyeyi döndürür
     */
    public double getMinBalance() {
        checkInitialized();
        return economyService.getMinBalance();
    }

    // === İSTATİSTİKLER ===

    /**
     * En zengin oyuncuları getirir
     */
    public CompletableFuture<List<PlayerEconomy>> getTopPlayers(int limit) {
        checkInitialized();
        return economyService.getTopPlayers(limit);
    }

    /**
     * Toplam oyuncu sayısını döndürür
     */
    public CompletableFuture<Long> getTotalPlayers() {
        checkInitialized();
        return economyService.getTotalPlayers();
    }

    /**
     * Toplam ekonomik değeri döndürür
     */
    public CompletableFuture<Double> getTotalEconomicValue() {
        checkInitialized();
        return economyService.getTotalEconomicValue();
    }

    /**
     * Belirtilen bakiye aralığındaki oyuncuları getirir
     */
    public CompletableFuture<List<PlayerEconomy>> getPlayersByBalanceRange(double minBalance, double maxBalance) {
        checkInitialized();
        return economyService.getPlayersByBalanceRange(minBalance, maxBalance);
    }

    private void checkInitialized() {
        if (!isReady()) {
            throw new IllegalStateException("MagEconomy API henüz başlatılmadı! MagEconomy eklentisinin yüklü olduğundan emin olun.");
        }
    }
}