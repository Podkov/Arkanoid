package com.podkov.client.model;

public class CountdownTimer {
	
	private static int timeLeft;

	public static int getTimeLeft() {
		return timeLeft;
	}

	public static void setTimeLeft(int timeLeft) {
		if (timeLeft >= 0) {
			CountdownTimer.timeLeft = timeLeft;
		} else {
			System.out.println("Time cannot be lower than 0");
		}
	}
	
	

}
