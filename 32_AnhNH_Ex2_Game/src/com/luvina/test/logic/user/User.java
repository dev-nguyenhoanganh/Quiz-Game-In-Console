/**
 * @Project_Name 32_AnhNH_Ex1_Quan Ly
 */
package com.luvina.test.logic.user;

/**
 * @author Hoang Anh
 * @since 26 thg 1, 2021
 * @version 1.0
 */
public class User {
	private String name;
	private String date;
	private String score;
	private String account;
	private String pass;


	/**
	 * 
	 */
	public User( String    name, 
				 String    date, 
				 String    score, 
				 String    account,
				 String    pass) {

		this.name		 = name;
		this.date		 = date;
		this.score 		 = score;
		this.account 	 = account;
		this.pass 		 = pass;
	}
	
	public User( String    name, 
				 String    account,
				 String    pass) {

		this.name		 = name;
		this.date		 = "";
		this.score 		 = "0";
		this.account 	 = account;
		this.pass 		 = pass;
}
	
	// ------------------ Student Method -----------------	
	@Override
	public String toString() {
		return     name    + "_" 
				+  date    + "_" 
				+  score   + "_" 
				+  account + "_" 
				+  pass    + "\n";
	}
	
	@Override
	public boolean equals(Object obj) {
		User object = (User) obj;
		return account.equals(object.getAccount());
	}

	public String showScore() {
		return "Tên: " + name + "\t\tĐiểm: " + score;
	}
	
	// ----------------- Getter & Setter -----------------

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * @return the score
	 */
	public String getScore() {
		return score;
	}

	/**
	 * @param score the score to set
	 */
	public void setScore(String score) {
		this.score = score;
	}

	/**
	 * @return the account
	 */
	public String getAccount() {
		return account;
	}

	/**
	 * @param account the account to set
	 */
	public void setAccount(String account) {
		this.account = account;
	}

	/**
	 * @return the pass
	 */
	public String getPass() {
		return pass;
	}

	/**
	 * @param pass the pass to set
	 */
	public void setPass(String pass) {
		this.pass = pass;
	}

}
