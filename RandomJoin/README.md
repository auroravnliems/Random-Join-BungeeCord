# 🎲 RandomJoin Plugin - BungeeCord

Plugin cho phép người chơi kết nối ngẫu nhiên đến bất kỳ server nào được cài đặt trong config, sử dụng tại hub BungeeCord.

---

## 📦 Cài đặt

1. Build bằng Maven: `mvn clean package`
2. Copy file `.jar` vào thư mục `plugins/` của BungeeCord
3. Khởi động lại BungeeCord
4. Chỉnh sửa `plugins/RandomJoin/config.yml`

---

## ⚙️ Cấu hình (config.yml)

```yaml
servers:
  - survival       # Tên server phải khớp với BungeeCord config.yml
  - skyblock
  - minigames
  - pvp

command:
  name: rjoin
  permission: randomjoin.use
  aliases:
    - randomjoin
    - rj

exclude-current-server: true   # Không kết nối về server đang đứng
check-online: false            # true = chỉ kết nối server có người online
```

---

## 🕹️ Lệnh & Quyền

| Lệnh              | Mô tả                             | Quyền             |
|-------------------|-----------------------------------|--------------------|
| `/rjoin`          | Kết nối ngẫu nhiên đến 1 server  | `randomjoin.use`   |
| `/rjoin reload`   | Reload config plugin              | `randomjoin.admin` |

**Alias:** `/randomjoin`, `/rj`

---

## 🔧 Yêu cầu

- BungeeCord (hoặc Waterfall) 1.8+
- Java 8+

---

## 📝 Ghi chú

- Tên server trong `config.yml` phải **khớp chính xác** với tên trong `BungeeCord/config.yml`
- Nếu server không tồn tại trong BungeeCord, nó sẽ tự động bị bỏ qua
- Hỗ trợ mã màu `&` trong tất cả thông báo
