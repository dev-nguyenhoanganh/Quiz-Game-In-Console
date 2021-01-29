# Câu hỏi ôn tập

## Developer [Nguyễn Hoàng Anh](https://www.facebook.com/ldcapt/)

---

### 1. Lý thuyết hướng đối tượng có mấy tính chất?
- Tính trừu tượng
- Tính kế thừa
- Tính đa hình
- Tính đóng gói và che dấu dữ liệu

### 2. Tính trừu tượng hóa? Ví dụ?
- Loại bỏ hoặc không chú ý đến một số thuộc tính, phương thức dư thừa của đối tượng

- Ví dụ:
	+ Một `học sinh` có rất nhiều `đặc điểm` như: Màu tóc, màu mắt, màu da, ...
	+ Với bài toán `quản lý học sinh` thì những đặc điểm cần quan tâm chỉ là: *ngày sinh*, *quê quán*, *họ tên*, ...

### 3. Tính kế thừa, ví dụ?
- Là sự thừa hưởng các phương thức, thuộc tính của đối tượng con từ một đối tượng cha mà không cần phải khai báo

- Ví dụ:
```java
public abstract class TuGiac {
	// Khai bao thuoc tinh
	protected int canhA;
	protected int canhB;
	protected int canhC;
	protected int canhD;

	protected int tinhDienTich() {
		// Nội dung phương thức	
	}

	protected void tinhChuVi() {
		// Nội dung phương thức
	}
}
----------------------------------------
public class HinhVuong extends TuGiac {
	/**
	 * Hình vuông sẽ được kết thừa và sử dụng 
	 *
	 *  - Phương thức
	 *    tinhDienTich();
	 *    tinhChuVi();
	 *
	 *  - Thuộc tính
	 *    canhA
	 *    canhB
	 *    canhC
	 *    canhD
	 *
	 * của hình tứ giác mà không cần khai báo
	 */
}

```

### 4. Ghi đè phương thức là gì? Viết 1 class cha và 1 class con để minh họa?
(Ghi đè chỉ xuất hiện trong kế thừa)
- Định nghĩa lại phương thức trừu tượng đã được khai báo trong class cha được gọi là ghi đè
- Ví dụ minh họa
```java
public class TuGiac {
	int tinhDienTich();
    
	void tinhChuVi() {
	    // Nội dung phương thức
	}
}
----------------------------------------
public class HinhVuong extends TuGiac {
	@Override
	int tinhDienTich();
	
	@Override
	void tinhChuVi() {
	    // Nội dung phương thức
	    super.tinhChuVi();
	    // Tính chu vi tiếp    
	}
}
```
### 5. Nạp chồng phương thức là gì? Nêu ví dụ:
- Là các phương thức có tên giống nhau nhưng khác nhau về kiểu dữ liệu trả về hoặc số lượng các tham số.

```java
public class TuGiac {
	int tinhDienTich() {
		// Nội dung phương thức
	}
    
	String tinhDienTich() {
	    // Nội dung phương thức
	}
}
```

