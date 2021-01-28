/**
 * @Project_Name 32_AnhNH_Ex2_Game
 */
package com.luvina.test.logic.question;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Hoang Anh
 * @since 28 thg 1, 2021
 * @version 1.0
 */
public class ReadFileXML {

	// REGEX FILE
	private static final String CONTENT_OPEN_REGEX = "<content>";
	private static final String CONTENT_CLOSE_REGEX = "<\\/content>";
	private static final String ANSWER_OPEN_REGEX = "<answer>";
	private static final String ANSWER_CLOSE_REGEX = "<\\/answer>";
	private static final String CORRECT_OPEN_REGEX = "<correct>";
	private static final String CORRECT_CLOSE_REGEX = "<\\/correct>";

	private static final String CORRECT_REGEX = CORRECT_OPEN_REGEX + "(.*)" 
											  + CORRECT_CLOSE_REGEX;
	
	private static final String ANSWER_REGEX = ANSWER_OPEN_REGEX + "(.*)" 
											  + ANSWER_CLOSE_REGEX;
	
	private static final String CONTENT_REGEX = CONTENT_OPEN_REGEX + "(.*)" 
											  + CONTENT_CLOSE_REGEX;
	
	// Khai báo thuộc tính
	private String text;
	private ArrayList<Question> listQuestion;

	/**
	 * Phương thức khởi tạo giá trị cho đối tượng SmartString
	 */
	public ReadFileXML(String path) {
		this.text = readFile(path);
		listQuestion = new ArrayList<Question>();
	}

	// ----------------- Xử lý file question.txt -----------------

	public void print() {
		if (listQuestion.isEmpty()) {
			System.out.println("List rỗng");
		} else {
			int idx = 0;
			for (Question answer : listQuestion) {
				System.out.println("------------------- Item " + (++idx) + " -------------------");
				System.out.println(answer.showQuestion());
			}
		}
	}

	public ArrayList<Question> convertFileToList() {
		if(text.isEmpty()) {
			return null;
		}
		listQuestion = new ArrayList<Question>();

		String itemList[] = text.split("<question>\r\n");

		for (int i = 0; i < itemList.length; i++) {
			if (itemList[i].contains("</question>")) {
				listQuestion.add(getItemInformation(itemList[i]));
			}
		}
		
		return listQuestion;
	}

	public Question getItemInformation(String item) {
		String[] questionInfo = item.split("\n");
		int size = questionInfo.length;

		String[] result = new String[size];
		result[0] = getAnswer(item, CONTENT_REGEX);
		result[size - 2] = getAnswer(item, CORRECT_REGEX);
		
		Question question = new Question(result[0]);
		question.setCorrectAnswer(result[size - 2]);
		
		for(int i = 1; i < size - 1; i++) {
			result[i] = getAnswer(questionInfo[i], ANSWER_REGEX);
			if("Not Found".equals(result[i])) {
				continue;
			}
			question.addAnswer(result[i]);
		}
		return question;
	}

	private String getAnswer(String item, String regex) {
		Pattern r = Pattern.compile(regex);
		Matcher m = r.matcher(item);

		if (m.find()) {
			return m.group(1);
		}
		return "Not Found";
	}
	
	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	// ----------------- Read and Write File -----------------

	/**
	 * @param path
	 * @return
	 */
	private String readFile(String path) {
		String data = "";
		try {
			// 1 trỏ vào file
			File file = new File(path);
			// 2. Kiểm tra tồn tại
			if (!file.exists()) {
				return "";
			}
			// 3. Mở File để đọc
			FileInputStream fI = new FileInputStream(file);
			byte[] arrByte = new byte[8192];
			int length = fI.read(arrByte);
			while (length != -1) {
				data += new String(arrByte, 0, length);
				length = fI.read(arrByte);
			}
			fI.close();
		} catch (IOException e) {
			return "";
		}
		return data;
	}
}
