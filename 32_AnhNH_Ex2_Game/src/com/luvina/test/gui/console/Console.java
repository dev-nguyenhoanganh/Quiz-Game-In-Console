package com.luvina.test.gui.console;

/**
 * @Project_Name 32_AnhNH_Ex2_Quan Ly Diem Hoc Vien
 */

import java.util.Scanner;

import com.luvina.test.logic.manager.Manager;
import com.luvina.test.logic.user.User;
import com.luvina.test.thread.TimeoutThread;


/**
 * @author Hoang Anh
 * @since 27 thg 1, 2021
 * @version 1.0
 */
public class Console {
	private static Manager  		manager;
	private static User 			user;
	private static Scanner  		input;
	private static TimeoutThread	timeout; 
	private int score;
	
	
	private static final String REGEX_ALL_CHAR = "";
	private static final String REGEX_NAME = "[\\D|\\W]+";
	private static final String REGEX_ID = "[\\S]{5,}";
	private static final String REGEX_SCORE = "(\\d+(\\.\\d+)?)|(\s)";
	private static final String REGEX_DATE = "\\d{1,2}\\/\\d{1,2}\\/\\d{4}";
//	private static final String REGEX_PRIORITY = "[0-1]";

//	// Chữ thường + chữ hoa + số + ký tự đặc biệt + 8 ký tự trở lên
//	private static final String REGEX_PASS = 
//	"^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}";

//	// Chữ thường + chữ hoa + số + 8 ký tự trở lên
//	private static final String REGEX_PASS = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$";

	// Lớn hơn 8 ký tự
	private static final String REGEX_PASS = ".{8,}";

	/**
	 * 
	 */
	public Console() {
		manager = new Manager();
		input 	= new Scanner(System.in);
		timeout = new TimeoutThread();
		score	= 0;
	}

	public void wellcomePage() {
		System.out.println("\n---------- Game Ai Là Triệu Phú ----------");
		System.out.println("Vui lòng chọn:\n" 
						+  "[1] Đăng nhập\n" 
						+  "[2] Đăng ký\n" 
						+  "[0] Thoát chương trình");

		String choose = getInput("[0-2]", "[0-2]");
		switch (choose) {
		case "1":
			signInPage();
			break;
		case "2":
			signUpPage();
			break;
		case "0":
			return;
		}
	}

	public void signInPage() {
		System.out.println("------------------ Đăng nhập -------------------");
		System.out.print("Tài khoản: ");
		String account = getInput(REGEX_ALL_CHAR, "tài khoản");

		System.out.print("Mật khẩu: ");
		String pass = getInput(REGEX_ALL_CHAR, "mật khẩu");
		 
		if (!manager.signIn(account, pass)) {
			System.err.println("Tài khoản hoặc mật khẩu không đúng");
			pressToContinue();
			wellcomePage();
		} else {
			System.err.println("Đăng nhập thành công");
			mainPage();
		}
	}

	public void signUpPage() {
		System.out.println("\n--------------- Tạo tài khoản ----------------");
		String name;
		String account;
		String pass;

		System.out.print("Nhập tên của bạn: ");
		name = getInput(REGEX_NAME, "Lưu ý họ và tên không chứa số");

		System.out.print("Nhập tên tài khoản: ");
		account = getInput(REGEX_ID, "Lưu ý, tên tài khoản không được ít hơn 5 ký tự");

		System.out.print("Nhập mật khẩu : ");
		pass = getInput(REGEX_PASS, "Lưu ý, mật khẩu không được ít hơn 8 ký tự");

		if(manager.signUp(name, account, pass)) {
			System.err.println("Tạo tài khoản thành công");
		} else {
			System.err.println("Tạo tài khoản thất bại");
		}
		pressToContinue();
		wellcomePage();
	}

	public void mainPage() {
		user = manager.getUser();
		System.out.print("\n------------- Xin chào ");
		System.out.print(user.getName());
		System.out.println(" -------------");
		System.out.println(
			   "[1] Bắt đầu trò chơi\r\n"
			+  "[2] Xem điểm\r\n"
			+  "[3] Sửa thông tin\r\n"
			+  "[4] Xem bảng xếp hạng\r\n"
			+  "[0] Đăng xuất"
		);

		String choose = getInput("[0-4]", "[0-4]");
		switch (choose) {
		case "1":
			score = 0;
			startGame();
			pressToContinue();
			mainPage();
			break;
		case "2":
			showScore();
			pressToContinue();
			mainPage();
			break;
		case "3":
			editProfile();
			pressToContinue();
			mainPage();
			break;
		case "4":
			showTopScore();
			pressToContinue();
			mainPage();
			break;
		case "0":
			wellcomePage();
			break;
		}
	}

	/**
	 * 
	 */
	private void showTopScore() {
		System.out.println(manager.showTopScore());
	}

	/**
	 * 
	 */
	private void editProfile() {
		int index = manager.getIndexUser();
		System.out.println("\n--------------- Sửa thông tin ---------------");
		System.out.println(
			   "[1] Sửa tên\r\n"
			+  "[2] Sửa mật khẩu\r\n"
			+  "[0] Quay lại"
		);
		
		String inputValue;
		
		String choose = getInput("[0-2]", "[0-2]");
		switch (choose) {
		case "1":
			System.out.print("Nhập tên của bạn: ");
			inputValue = getInput(REGEX_NAME, "Lưu ý họ và tên không chứa số");
			user.setName(inputValue);
			manager.updateUser(user, index);
			System.err.println("Sửa thành công");
			pressToContinue();
			editProfile();
			break;
		case "2":
			System.out.print("Nhập mật khẩu : ");
			inputValue = getInput(REGEX_PASS, "Lưu ý, mật khẩu không được ít hơn 8 ký tự");
			user.setPass(inputValue);
			manager.updateUser(user, index);
			System.err.println("Sửa thành công");
			pressToContinue();
			editProfile();
			break;
		case "0":
			mainPage();
			break;
		}
		
		
	}

	/**
	 * 
	 */
	public void startGame() {
		String result = manager.showOneQuestion();
		if(result == null) {
			System.out.println("--------------------------------------------");
			System.err.println("\nTrò chơi đến đây là kết thúc");
			System.out.println("Điểm của bạn là: " + score);
			manager.updateUserScore(score);
			return;
		}
		if("Câu hỏi chưa được thêm vào".equals(result)) {
			System.err.println(result);
			return;
		}
		System.out.println(result);
		
		timeout.timeoutStart();
		String choose = input.nextLine();
		
		checkAnswer(choose);
		manager.increaseIndex();
		pressToContinue();
		startGame();
	}

	public void close() {
		input.close();
		System.err.println("Chương trình đã đóng");
	}

	// ----------------- Private Method -----------------

	private static String getInput(String regex, String name) {
		String result = "";
		while (true) {
			result = input.nextLine().trim();
			if (result.isEmpty() && REGEX_SCORE.equals(regex)) {
				return "0";
			}
			if (regex.isEmpty()) {
				return result;
			}
			if (result.matches(regex)) {
				return result;
			}
			System.err.println("Bạn đã nhập sai định dạng rồi! Mời nhập lại :");
			System.out.print(name + ": ");
		}
	}

	private void pressToContinue() {
		System.out.print("Nhấn phím bất kỳ để tiếp tục ! ");
		input.nextLine();
	}
	
	/**
	 * 
	 */
	private void showScore() {
		User temp = manager.getUser();
		if(temp.getScore().isEmpty()) {
			System.err.println("Bạn chưa chơi lần nào, lấy đâu ra điểm");
			return;
		}
		System.err.println("Điểm của bạn là : " + temp.getScore());
	}
	
	/**
	 * 
	 */
	private void checkAnswer(String choose) {
		if(timeout.isExitThread()) {
			if(choose.isEmpty()) {
				System.err.println("Hết thời gian rồi");
				return;
			}
		}
		timeout.setExitThread(true);
		if(manager.checkAnswer(choose)) {
			score++;
			System.err.println("Bạn giỏi thật, đúng rồi kìa !!");
		} else {
			System.err.println("Bạn rất tốt nhưng chúng tôi rất tiếc, sai rồi!!");
		}
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			System.err.println("Bộ định thời gặp sự cố");
		}
	}
}
