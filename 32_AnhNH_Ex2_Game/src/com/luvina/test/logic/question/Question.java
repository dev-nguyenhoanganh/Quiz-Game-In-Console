/**
 * @Project_Name 32_AnhNH_Ex2_Game
 */
package com.luvina.test.logic.question;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author Hoang Anh
 * @since 28 thg 1, 2021
 * @version 1.0
 */
public class Question {
	private String content;
	private ArrayList<String> listAnswer;
	private String correctAnswer;

	public Question(String content) {
		this.content = content;
		listAnswer = new ArrayList<String>();
	}
	
	public boolean addAnswer(String answer) {
		if(listAnswer.contains(answer)) {
			return false;
		}
		listAnswer.add(answer);
		return true;
	}
	
	public String showQuestion() {
		shuffleAnswer();
		return displayAnswer();
	}
	
	@Override
	public String toString() {
		String result = "<question>\n";
		 result += "<content>" + content + "</content>\n";
		for (String answer : listAnswer) {
			result += "<answer>" + answer + "</answer>\n";
		}
		result += "<correct>" + correctAnswer + "</correct>\n";
		return result + "</question>\n";
	}

	// ---------------- Getter & Setter ----------------
	
	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return the listAnswer
	 */
	public ArrayList<String> getListAnswer() {
		return listAnswer;
	}

	/**
	 * @param listAnswer the listAnswer to set
	 */
	public void setListAnswer(ArrayList<String> listAnswer) {
		this.listAnswer = listAnswer;
	}
	
	/**
	 * @param correctAnswer the correctAnswer to set
	 */
	public void setCorrectAnswer(String index) {
		this.correctAnswer = index;
	}
	
	/**
	 * @return the correctAnswer
	 */
	public String getCorrectAnswer() {
		return correctAnswer;
	}
	
	// ---------------- Private Layer ----------------
	private void shuffleAnswer() {
		int index = Integer.parseInt(correctAnswer) - 1;
		String correct = listAnswer.get(index);
		Collections.shuffle(listAnswer);
		index = listAnswer.indexOf(correct);
		setCorrectAnswer("" + (index + 1));
	}
	
	private String displayAnswer() {
		String result = "Câu hỏi:\n" + content + "\n";
		for (int i = 0; i < listAnswer.size(); i++) {
			result += "["+ (i + 1) + "] " + listAnswer.get(i) + "\n";
		}
		return result;
	}
	
}
