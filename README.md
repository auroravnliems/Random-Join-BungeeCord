# 🎲 RandomJoin Plugin - BungeeCord

The plugin allows players to randomly connect to any server configured in the config, used at the BungeeCord hub.

---

## ⚙️ Config (config.yml)

```yaml
servers:
  - survival       # server name must like Bungeecord config.yml
  - skyblock
  - minigames
  - pvp

command:
  name: rjoin
  permission: randomjoin.use
  aliases:
    - randomjoin
    - rj

exclude-current-server: true   # no connect to current server
check-online: false            # true = only connect to server have players
```

---

## 🕹️ Commands & Permissions

| Lệnh              | Mô tả                             | Quyền             |
|-------------------|-----------------------------------|--------------------|
| `/rjoin`          | random connect to some server     | `randomjoin.use`   |
| `/rjoin reload`   | Reload config plugin              | `randomjoin.admin` |

**Alias:** `/randomjoin`, `/rj`

---

## 🔧 Requ

- BungeeCord (or Waterfall) 1.8+
- Java 8+

---

## 📝 Note

- Tên server trong `config.yml` phải **khớp chính xác** với tên trong `BungeeCord/config.yml`
- Nếu server không tồn tại trong BungeeCord, nó sẽ tự động bị bỏ qua
- Hỗ trợ mã màu `&` trong tất cả thông báo
