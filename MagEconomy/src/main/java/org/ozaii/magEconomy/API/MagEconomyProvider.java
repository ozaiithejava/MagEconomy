package org.ozaii.magEconomy.API;


import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

public class MagEconomyProvider {

    /**
     * MagEconomy API'sine erişim sağlar
     * @param plugin API'yi kullanacak eklenti
     * @return MagEconomyAPI instance veya null
     */
    public static MagEconomyAPI getAPI(Plugin plugin) {
        Plugin magEconomy = Bukkit.getPluginManager().getPlugin("MagEconomy");

        if (magEconomy == null || !magEconomy.isEnabled()) {
            plugin.getLogger().warning("MagEconomy eklentisi bulunamadı veya aktif değil!");
            return null;
        }

        MagEconomyAPI api = MagEconomyAPI.getInstance();
        if (!api.isReady()) {
            plugin.getLogger().warning("MagEconomy API henüz hazır değil!");
            return null;
        }

        return api;
    }

    /**
     * MagEconomy'nin yüklü olup olmadığını kontrol eder
     */
    public static boolean isAvailable() {
        Plugin magEconomy = Bukkit.getPluginManager().getPlugin("MagEconomy");
        return magEconomy != null && magEconomy.isEnabled() && MagEconomyAPI.getInstance().isReady();
    }
}