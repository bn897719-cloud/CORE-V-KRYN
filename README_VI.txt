CORE V KRYN v0.1 — Minecraft Java 1.21.4 (Fabric)

Đây là MÃ NGUỒN dự án, chưa phải file .jar đã biên dịch.
Tính năng bản khởi đầu:
- Nhấn P mở/đóng bảng điều khiển.
- Giao diện tối tím neon.
- Bật/tắt FPS, Ping, giáp, hiệu ứng thuốc, túi đồ, viền neon.
- HUD hiển thị FPS/Ping, độ bền giáp, hiệu ứng thuốc và 9 ô túi đồ.
- Lưu cài đặt vào config/core-v-kryn.properties.

Yêu cầu:
- JDK 21
- Gradle (hoặc mở thư mục bằng IntelliJ IDEA)
- Minecraft 1.21.4 + Fabric Loader + Fabric API tương ứng.

Biên dịch:
  gradle build

File .jar sau khi build nằm trong:
  build/libs/

Cài vào PojavLauncher:
1. Cài Minecraft 1.21.4 Fabric.
2. Đảm bảo profile có Fabric API.
3. Chép core-v-kryn-0.1.0.jar vào thư mục mods của instance.
4. Mở game, nhấn P để mở menu.

Lưu ý:
- Chưa có .jar build sẵn trong gói này.
- Chưa được kiểm thử trên iPhone/PojavLauncher thực tế.
- Đây là bản khung đầu tiên; các module PvP nâng cao, hiệu ứng thế giới và trình chỉnh sửa HUD sẽ được phát triển tiếp.
