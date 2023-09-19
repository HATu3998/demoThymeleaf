# Sử dụng ảnh Java mở rộng
FROM adoptopenjdk:17-jre-hotspot

# Thiết lập thư mục làm việc
WORKDIR /app

# Sao chép tất cả các tệp từ thư mục target của dự án Spring Boot vào thư mục /app trong container
COPY target/*jar /app/app.jar

# Mở cổng mạng cho ứng dụng
EXPOSE 8080

# Khởi chạy ứng dụng khi container được khởi động
CMD ["java", "-jar", "app.jar"]
