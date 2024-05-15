
# Blog-App

## Project Introduction
Blog-App là một nền tảng viết blog dựa trên Java cho phép người dùng tạo, chỉnh sửa, xem và xóa các bài viết blog. Ứng dụng này được xây dựng bằng Spring Boot, cung cấp một hệ thống backend mạnh mẽ và một giao diện web động.

## Project Information

1. *Repository Description*: Nền tảng viết blog dựa trên Java để tạo và quản lý bài đăng.
2. *Architecture Details*: Được xây dựng bằng Spring Boot và sử dụng các thành phần khung Java Spring.
3. *Branching Information*: Nhánh chính sử dụng Spring Boot 2.7+ và JDK 8; nhánh dev-v3 được nâng cấp lên Spring Boot 3.2+ và JDK 17.

## System Architecture
Blog-App được thiết kế với một kiến ​​trúc rõ ràng, có thể mở rộng:
- *Web Layer*: Xử lý các yêu cầu và phản hồi HTTP.
- *Service Layer*: Cung cấp logic quản lý cốt lõi.
- *Data Access Layer*: Quản lý hoạt động cơ sở dữ liệu.
- *Security Layer*: Đảm bảo xác thực.

## Technologies Used
- *Spring Boot*: Đơn giản hóa việc phát triển các ứng dụng Spring mới.
- *Java*: Cung cấp tính di động và hỗ trợ rộng rãi cho các ứng dụng web.
- *Maven*: Quản lý các phụ thuộc và vòng đời dự án.
- *MySQL*:  Xử lý lưu trữ dữ liệu với hỗ trợ giao dịch đáng tin cậy.
- *Spring Security*: Đảm bảo xác thực và phân quyền an toàn.

## Project Structure
- *src/main/java*: Các tệp mã nguồn Java cho logic ứng dụng.
- *src/main/resources*: Các tệp cấu hình và tài nguyên.
- *src/test/java*: Các tệp mã nguồn cho kiểm thử đơn vị và tích hợp.

## Quick Start
Clone the repository and navigate to the directory:
```bash
git clone https://github.com/VuHSO-edu/blog-app.git
cd blog-app
./mvnw spring-boot:run


