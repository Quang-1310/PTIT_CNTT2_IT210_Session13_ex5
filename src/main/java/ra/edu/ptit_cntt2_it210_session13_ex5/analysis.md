Hệ thống sẽ xảy ra lỗi gì? Khi bạn cấu hình fetch = FetchType.LAZY cho danh sách chi tiết thuốc, Hibernate sẽ không tải dữ liệu này lên ngay lập tức.
Nếu ta lấy đối tượng Prescription từ DB, sau đó thực hiện lệnh session.close() (hoặc kết thúc phương thức có đánh dấu @Transactional).

Khi trang Thymeleaf cố gắng truy cập prescription.details để hiển thị lên giao diện, Hibernate sẽ cố gắng tạo một kết nối mới để lấy dữ liệu. Nhưng vì Session đã đóng, nó không còn "cầu nối" nào đến DB nữa và tung ra lỗi

org.hibernate.LazyInitializationException: could not initialize proxy - no Session

Cách khắc phục:
Cách 1: Sử dụng @Transactional ở tầng Service để giữ Session mở cho đến khi View (Thymeleaf) render xong (Open Session in View).

Cách 2: Viết câu lệnh HQL có từ khóa JOIN FETCH: FROM Prescription p JOIN FETCH p.details WHERE p.id = :id. Cách này sẽ lấy luôn dữ liệu chi tiết ngay trong 1 lần truy vấn.

Cách 3: Dùng Hibernate.initialize(prescription.getDetails()) ngay khi Session còn mở.