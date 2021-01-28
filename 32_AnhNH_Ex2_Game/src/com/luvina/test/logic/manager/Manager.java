/**
 * @Project_Name 32_AnhNH_Ex1_Quan Ly
 */
package com.luvina.test.logic.manager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import com.luvina.test.logic.question.Question;
import com.luvina.test.logic.question.ReadFileXML;
import com.luvina.test.logic.user.User;

/**
 * @author Hoang Anh
 * @since 26 thg 1, 2021
 * @version 1.0
 */
public class Manager {
	public final String SORT_BY_NAME  = "name";
	public final String SORT_BY_SCORE  = "score";

	private ArrayList<User> listUser;
	private ArrayList<Question> listQuestion;
	private ReadFileXML readFile;
	
	private User user;
	private int indexUser;
	
	private static int questionIndex = 0;

	private static final String SRC_ACCOUNT  = ".\\data\\account.txt";
	
	private static final String QUESTION_FILE = ".\\data\\question.txt";

	/**
	 * 
	 */
	public Manager() {
		listUser = new ArrayList<User>();
		listUser = convertFileToList();
		
		readFile = new ReadFileXML(QUESTION_FILE);
		listQuestion = readFile.convertFileToList();
		
	}

	// ----------------- Sign In & Sign Up -----------------
	public boolean signIn(String account, String pass) {
		for (User user : listUser) {
			if(account.equals(user.getAccount())) {
				if(pass.equals(user.getPass())) {
					setUser(user);
					indexUser = listUser.indexOf(user);
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean signUp(String    name, 
						  String    account,
						  String    pass) {
		
		User newUser = new User(name, account, pass);
		if(listUser.contains(newUser)) {
			return false;
		}
		listUser.add(newUser);
		convertListToFile();
		return true;
	}
	
	// ----------------- Comparator -----------------
	public Comparator<User> sortByName = new Comparator<User>() {

		@Override
		public int compare(User o1, User o2) {
			return o1.getName().compareTo(o2.getName());
		}
	};
	
	public Comparator<User> sortByScore = new Comparator<User>() {

		@Override
		public int compare(User o1, User o2) {
			return Integer.parseInt(o2.getScore()) 
				 - Integer.parseInt(o1.getScore());
		}
	};
	
	// ----------------- Question Method -----------------
	public String showAllQuestion() {
		if(listQuestion == null) {
			return "Chưa có câu hỏi nào trong danh sách";
		}
		String result = "";
		int idx = 0;
		for (Question question : listQuestion) {
			result += "------------------- Item " + (++idx) 
					+ " -------------------\n";
			result += question.showQuestion();
		}
		return result;
	}
	
	public void shuffleQuestion() {
		Collections.shuffle(listQuestion);
		questionIndex = 0;
	}
	
	public String showOneQuestion() {
		if(listQuestion == null) {
			return "Câu hỏi chưa được thêm vào";
		}
		if(questionIndex == listQuestion.size()) {
			questionIndex = 0;
			return null;
		}
		return (listQuestion.get(questionIndex)).showQuestion();
	}
	
	public boolean checkAnswer(String index) {
		if(listQuestion.get(questionIndex).getCorrectAnswer().equals(index)) {
			return true;
		}
		return false;
	}
	
	public void increaseIndex() {
		questionIndex++;
	}
	
	// ----------------- User Method -----------------

	public boolean updateUser(User user, int listIndex) {
		listUser.set(listIndex, user);
		return convertListToFile();
	}
	
	public String showTopScore() {
		String result = "";
		listUser.sort(sortByScore);
		for (int i = 0; i < listUser.size(); i++) {
			if(i > 5) {
				return result;
			}
			result += "[" + i + "] " + listUser.get(i).showScore() + "\n";
		}
		return result;
	}

	public void refreshList() {
		listUser = convertFileToList();
	}
	
	public void updateUserScore(int score) {
		int lastScore = Integer.parseInt(user.getScore());
		if(lastScore < score) {
			user.setScore("" + score);
			listUser.set(indexUser, user);
			convertListToFile();
		}
	}
	
	/**
	 * @return the listStudent
	 */
	public ArrayList<User> getListStudent() {
		return listUser;
	}

	/**
	 * @param listStudent the listStudent to set
	 */
	public void setListStudent(ArrayList<User> listStudent) {
		this.listUser = listStudent;
	}
	
	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
		
	}
	/**
	 * @return the indexUser
	 */
	public int getIndexUser() {
		return indexUser;
	}

	/**
	 * @param indexUser the indexUser to set
	 */
	public void setIndexUser(int indexUser) {
		this.indexUser = indexUser;
	}
	
	// ----------------- Read and Write File -----------------

	/**
	 * 
	 */
	private ArrayList<User> convertFileToList() {
		try {
			String data = readFile(SRC_ACCOUNT);
			String[] dataLine = data.split("\n");
			ArrayList<User> list = new ArrayList<User>();
			for (int i = 0; i < dataLine.length; i++) {
				String[] studentInfor = dataLine[i].split("_");
				User student = new User(studentInfor[0], 
										studentInfor[1], 
										studentInfor[2], 
										studentInfor[3],
										studentInfor[4]);
				
				list.add(student);
			}
			return list;
		} catch (Exception e) {
			return new ArrayList<User>();
		}
	}

	public boolean convertListToFile() {
		String data = "";
		for (User user : listUser) {
			data += user.toString();
		}
		return writeToFile(data, SRC_ACCOUNT);
	}

	/**
	 * @param path
	 * @return
	 */
	private String readFile(String path) {
		String data = "";
		try {
			File file = new File(path);
			if (!file.exists()) {
				return null;
			}
			FileInputStream fI = new FileInputStream(file);
			byte[] arrByte = new byte[8192];
			int length = fI.read(arrByte);
			while (length != -1) {
				data += new String(arrByte, 0, length);
				length = fI.read(arrByte);
			}
			fI.close();
		} catch (IOException e) {
			System.out.println("Lỗi Hệ Thống");
		}
		return data;
	}

	private boolean writeToFile(String input, String path) {
		try {
			File file = new File(path);
			if (!file.exists()) {
				file.getParentFile().mkdirs();
				file.createNewFile();
			}
			FileOutputStream fO = new FileOutputStream(file);
			byte[] byteArr = input.getBytes();
			fO.write(byteArr);
			fO.close();
		} catch (IOException e) {
			return false;
		}
		return true;
	}
	
//	private boolean deleteFile(String path) {
//		try {
//			File file = new File(path);
//			return file.delete();
//		}  
//		catch(Exception e)  
//		{  
//			return false;
//		}
//	}
}
