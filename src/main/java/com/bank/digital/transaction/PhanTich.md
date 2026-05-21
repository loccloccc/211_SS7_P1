# PHÂN TÍCH HỆ THỐNG XỬ LÝ GIAO DỊCH - ĐO HIỆU NĂNG

## 1. Các nguyên tắc thiết kế bị vi phạm khi chèn code thủ công

Việc chèn trực tiếp `System.currentTimeMillis()` vào đầu và cuối mỗi phương thức giao dịch vi phạm nghiêm trọng các nguyên tắc thiết kế phần mềm cốt lõi sau:

* **SRP (Single Responsibility Principle - Nguyên lý đơn nhiệm):** Một class/method chỉ nên giữ một trách nhiệm duy nhất. `TransactionService` nhiệm vụ chính là xử lý logic nghiệp vụ chuyển tiền, thanh toán. Việc bắt nó phải gánh thêm trách nhiệm đo lường hiệu năng và ghi log (System/Infrastructure Logic) là vi phạm SRP.
* **DRY (Don't Repeat Yourself):** Nếu hệ thống có 100 phương thức giao dịch, đoạn code lấy `startTime`, `endTime` và tính toán khoảng cách thời gian sẽ bị copy-paste lại đúng 100 lần. Khi cần thay đổi định dạng log (ví dụ: chuyển từ `System.out.println` sang `slf4j` hoặc đẩy lên Elasticsearch), ta phải sửa thủ công ở cả 100 nơi.
* **Code Tangling (Đan xen mã nguồn) & Code Scattering (Phân tán mã nguồn):**
    * **Tangling:** Trong một hàm, code nghiệp vụ và code log đan xen vào nhau làm giảm độ đọc hiểu (readability).
    * **Scattering:** Một tính năng hệ thống (đo hiệu năng) bị rải rác ở khắp các file, gói (package) khác nhau thay vì được quản lý tập trung.

## 2. Giải pháp khắc phục với Spring AOP

Giải pháp tối ưu là áp dụng **AOP (Aspect-Oriented Programming)** để cô lập tính năng đo hiệu năng thành một *Cross-cutting Concern* (mối quan tâm liên quan đến nhiều module).

* **Sử dụng @Around Advice:** Cho phép bọc quanh phương thức (join point), can thiệp trước và sau khi phương thức chạy.
* **Quản lý tập trung:** Logic đo thời gian được viết tại một file duy nhất, giúp mã nguồn nghiệp vụ luôn sạch sẽ (Clean Code).
