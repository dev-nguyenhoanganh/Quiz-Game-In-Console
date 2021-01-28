/**
 * @Project_Name 32_AnhNH_Ex2_Game
 */
package com.luvina.test.thread;

import java.awt.Robot;
import java.awt.event.KeyEvent;

/**
 * @author Hoang Anh
 * @since 28 thg 1, 2021
 * @version 1.0
 */
public class TimeoutThread {

	private static final int DEFAULT_TIME = 5;
	
	private int timeCount;
	private boolean exitThread;
	
	private Runnable runnable;
	
	/**
	 * 
	 */
	public TimeoutThread() {
		initRunnable();
		this.timeCount = DEFAULT_TIME;
		
	}
	
	public void timeoutStart() {
		exitThread = false;
		Thread timeoutThread = new Thread(runnable);
		timeoutThread.start();
	}
	
	// ---------------- Getter & Setter ----------------
	
	/**
	 * @return the timeCount
	 */
	public int getTimeCount() {
		return timeCount;
	}

	/**
	 * @param timeCount the timeCount to set
	 */
	public void setTimeCount(int timeCount) {
		this.timeCount = timeCount;
	}

	/**
	 * @return the exitThread
	 */
	public boolean isExitThread() {
		return exitThread;
	}

	/**
	 * @param exitThread the exitThread to set
	 */
	public void setExitThread(boolean exitThread) {
		this.exitThread = exitThread;
	}

	private void initRunnable() {
		runnable = new Runnable() {
			
			@Override
			public void run() {
				try {
					int count = 0;
					while (count <=  timeCount * 5) {
						if(exitThread) {
							return;
						}
						count++;
						Thread.sleep(200);
					}
					
					Robot robot = new Robot();
					robot.keyPress(KeyEvent.VK_ENTER);
					robot.keyRelease(KeyEvent.VK_ENTER);
					exitThread = true;
		        } catch(Exception e) {
		            e.printStackTrace();
		        }
			}
		};
	}
}
