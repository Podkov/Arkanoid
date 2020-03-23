package com.podkov.client.model;

public class Ball {
	
	private int radius;
	private static double speedLevel;
	private static int tempLevel;
	private double coordinateX;
	private double coordinateY;
	private double directionX;
	private double directionY;
	private int numberOfLives;
	
	//Zmiana po�o�enia pi�ki w zale�no�ci od obecnego kierunku i ustawionego poziomu trudno��i gry
	public void ballMove() {
		coordinateX = coordinateX + directionX * speedLevel;
		coordinateY = coordinateY + directionY * speedLevel;
	}
	
	//ustawienie mno�nika wektor�w ruchu pi�ki w zale�no�ci od wybranego poziomu trudno��i
	public static void setDifficultyLevel() {
		switch (tempLevel) {
		case 1:
			speedLevel = 1;
			break;
		case 2:
			speedLevel = 1.25;
			break;
		case 3:
			speedLevel = 1.5;
			break;
		case 4:
			speedLevel = 1.75;
			break;
		case 5:
			speedLevel = 2;
			break;
		default:
			speedLevel = 1;
			break;
		}
	}
	
	public int getRadius() {
		return radius;
	}
	public void setRadius(int radius) {
		//preferowany promie� pi�ki 10px
		if(radius > 0 && radius <= 50) {
		this.radius = radius;
		}else {
			System.out.println("Input correct value of ball radius - between 1 and 50");
		}
	}
	public static double getSpeedLevel() {
		return speedLevel;
	}
	
	//wywo�anie metody ustawiaj�cej pr�dko�� pi�ki w zale�no�ci od poziomu
	public static void setSpeedLevel() {
		setDifficultyLevel();
	}
	public static int getTempLevel() {
		return tempLevel;
	}
	public static void setTempLevel(int tempLevel) {
		Ball.tempLevel = tempLevel;
	}
	public double getCoordinateX() {
		return coordinateX;
	}
	
	//zadbanie by pi�ka nie crashowa�a si� poza granicami elementu Canvas
	//Oczywi�cie trzeba by tu uwzgl�dni� zmienn� szeroko�� elementu Canvas
	//jednak dla uproszczenia u�y�em sta�ej warto�ci
	public void setCoordinateX(double coordinateX) {
		if (coordinateX >= 0 & coordinateX <= 700 - radius*2) {
			this.coordinateX = coordinateX;
		}else if (coordinateX < 0) {
			this.coordinateX = 0;
		}else if (coordinateX > 700 - radius*2) {
			this.coordinateX = 700 - radius*2;
		} 
		
	}
	
	public double getCoordinateY() {
		return coordinateY;
	}
	
	//podobnie jak wy�ej
	public void setCoordinateY(double coordinateY) {
		if (coordinateY >= 0 & coordinateY <= 700 - radius*2) {
			this.coordinateY = coordinateY;
		}else if (coordinateY < 0) {
			this.coordinateY = 0;
		}else if (coordinateY > 700 - radius*2) {
			this.coordinateY = 700 - radius*2;
		} 
	}
	public double getDirectionX() {
		return directionX;
	}
	public void setDirectionX(double directionX) {
		this.directionX = directionX;
	}
	public double getDirectionY() {
		return directionY;
	}
	public void setDirectionY(double directionY) {
		this.directionY = directionY;
	}
	public int getNumberOfLives() {
		return numberOfLives;
	}
	public void setNumberOfLives(int numberOfLives) {
		if(numberOfLives >= 0) {
		this.numberOfLives = numberOfLives;
		}else {
			System.out.println("Number of lives can't be less than 0");
		}
	}
	
	

}
