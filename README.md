

# 💰 MagEconomy

**MagEconomy**, Bukkit/Spigot/Paper sunucuları için geliştirilen Vault destekli, MySQL ve SQLite uyumlu modern bir ekonomi eklentisidir. Performanslı, özelleştirilebilir ve geliştirici dostu yapısıyla sunucunuzun ihtiyaçlarına tam uyum sağlar.

![Java](https://img.shields.io/badge/Java-8%2B-blue.svg)
![License](https://img.shields.io/github/license/ozaii/MagEconomy)
![Vault Compatible](https://img.shields.io/badge/Vault-Compatible-brightgreen)
![Spigot](https://img.shields.io/badge/Supported-1.8--1.20%2B-yellow)

---

## ✨ Özellikler

- 🔌 **Vault Entegrasyonu**: Tüm Vault destekli sistemlerle tam uyum.
- ⚙️ **MySQL ve SQLite Desteği**: MySQL başarısız olursa otomatik olarak SQLite'a geçiş.
- 🚀 **Asenkron İşlemler**: Lag yaratmadan veri işlemleri.
- 💸 **Başlangıç Bakiyesi**: Oyunculara ilk girişte otomatik para.
- 🧠 **Akıllı DAO Sistemi**: ORM (ORMLite) destekli, önbellekli DAO altyapısı.
- 🌍 **Çoklu Dil ve Config Desteği**: Kolayca özelleştirilebilir.
- 📦 **Extensible API**: Geliştiriciler için hazır servis yapıları.

---

## 🧪 Test Edilen Sürümler

- Bukkit / Spigot 1.8.x - 1.20.x
- Vault 1.7+
- Java 8+

---

## 🚀 Kurulum

1. [Vault](https://www.spigotmc.org/resources/vault.34315/) eklentisini kurun.
2. `MagEconomy.jar` dosyasını `plugins` klasörüne ekleyin.
3. Sunucuyu başlatın veya `/reload` komutunu kullanın.
4. `config.yml` ve `database.yml` dosyaları otomatik oluşacaktır.
5. Gerekirse MySQL bilgilerinizi `plugins/MagEconomy/database.yml` içine girin.

---

## ⚙️ Komutlar

| Komut                                | Açıklama                                     | Yetki                      |
|--------------------------------------|----------------------------------------------|----------------------------|
| `/eco balance`                       | Kendi bakiyeni göster                        | Herkes                     |
| `/eco balance <oyuncu>`              | Başka oyuncunun bakiyesini göster            | `mageconomy.balance.others`|
| `/eco info`                          | Ekonomi sistemi bilgilerini göster           | Herkes                     |
| `/eco top [sayı]`                    | En zengin oyuncuları listeler                | Herkes                     |
| `/eco help`                          | Yardım menüsünü gösterir                     | Herkes                     |
| `/eco admin add <oyuncu> <miktar>`   | Oyuncuya para ekler                          | `mageconomy.admin`         |
| `/eco admin remove <oyuncu> <miktar>`| Oyuncudan para çeker                         | `mageconomy.admin`         |
| `/eco admin set <oyuncu> <miktar>`   | Oyuncunun bakiyesini belirli bir değere ayarlar | `mageconomy.admin`     |
| `/eco admin reset <oyuncu>`          | Oyuncunun bakiyesini sıfırlar                | `mageconomy.admin`         |
| `/eco admin see <oyuncu>`            | Oyuncunun mevcut bakiyesini gösterir         | `mageconomy.admin`         |
| `/eco admin check <oyuncu>`          | Oyuncunun hesap durumunu detaylı gösterir    | `mageconomy.admin`         |
| `/eco admin reload`                  | Config ve database ayarlarını yeniden yükler | `mageconomy.admin`         |

---

## 🧾 Yapılandırma

### `config.yml`
```yaml
start-money: 100.0
currency-symbol: '$'
````

### `database.yml`

```yaml
mysql:
  enabled: true
  host: localhost
  port: 3306
  database: minecraft
  username: root
  password: ""
  useSSL: false
  autoReconnect: true

sqlite:
  filename: database.db
```

---

## 📡 API Kullanımı

```java
// Öncelikle MagEconomy API'sine erişim sağlayın
MagEconomyAPI api = MagEconomyProvider.getAPI(plugin);
if (api == null) {
    plugin.getLogger().warning("MagEconomy API erişimi başarısız oldu!");
    return;
}

// Örnek: Bir oyuncunun ekonomisine ulaşmak ve para eklemek
PlayerEconomyService service = api.getPlayerEconomyService();
service.add(player.getUniqueId(), 100.0);

```

Geliştiriciler için genişletilebilir `MagEconomyAPI`, `MagEconomyProvider` ve `Events` sınıfları mevcuttur.

---

## 📂 Proje Yapısı

```
magEconomy/
├── config/
├── economy/
│   ├── models/
│   ├── services/
│   └── daos/
├── database/
├── MagEconomy.java
```

---

## 🧑‍💻 Geliştirici

* **ozaii**
* Java Developer / Bukkit Plugin Specialist
* [discord.gg/ozaii-dev](https://discord.gg/Magnesify)

---

## 📜 Lisans

Bu proje [MIT Lisansı](LICENSE) ile lisanslanmıştır.

---

## ❤️ Katkı Sağla

Pull request'ler, hata raporları ve öneriler her zaman hoş karşılanır.

> **Not**: Bu plugin profesyonel sunucular ve geliştiriciler için yazılmıştır. Geliştirmek için PR açmaktan çekinme!

```


