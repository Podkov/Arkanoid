package com.podkov.client.model;

import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.event.dom.client.MouseMoveHandler;
import com.podkov.client.view.screens.MainScreen;

public class Paddle {
	
	private int paddleWidth;
	private int paddleHeight;
	private int coordinateX;
	private int coordinateY;
	private static boolean rightTriggered = false;
	private static boolean leftTriggered = false;
	
	public void paddleMoveByKeys() {
		if(rightTriggered) {
			this.setCoordinateX(getCoordinateX() + 5);
			//coordinateX += 5;
		}else if(leftTriggered){
			this.setCoordinateX(getCoordinateX() - 5);
			//coordinateX -= 5;
		}
	}
	
	public int getPaddleWidth() {
		return paddleWidth;
	}
	public void setPaddleWidth(int paddleWidth) {
		if (paddleWidth >= 30 && paddleWidth <= 120) {
			this.paddleWidth = paddleWidth;
		}else {
			System.out.println("Input value between 30 and 120 unless you want to add a bonus changing size of paddle");
		}
		
	}
	public int getPaddleHeight() {
		return paddleHeight;
	}
	public void setPaddleHeight(int paddleHeight) {
		if (paddleHeight >= 10 && paddleHeight <= 50) {
		this.paddleHeight = paddleHeight;
		}else {
			System.out.println("Input value between 10 and 50 for comfort paddle apperance");
		}
	}
	public int getCoordinateX() {
		return coordinateX;
	}
	
	//Podobnie jak w przypdaku pi�ki ustali�em sta�� warto��
	//uwarunkowania maj� za zadanie zadba�, by paletka nie opuszcza�a obiektu Canvas.
	public void setCoordinateX(int coordinateX) {
		if(coordinateX >= 0 && coordinateX <= 700 - paddleWidth) {
			this.coordinateX = coordinateX;
		}else if (coordinateX < 0) {
			this.coordinateX = 0;
		}else if (coordinateX > 700 - paddleWidth) {
			this.coordinateX = 700 - paddleWidth;
		}
	}
	public int getCoordinateY() {
		return coordinateY;
	}
	
	//Automatycznie ustawia paletk� przyklejon� do dolnego pod�o�a
	//Tymczasowo u�yto sta�ych warto�ci podobnie jak wy�ej
	public void setCoordinateY() {
		this.coordinateY = 700 - paddleHeight;
	}
	public static boolean isRightTriggered() {
		return rightTriggered;
	}
	public static void setRightTriggered(boolean rightTriggered) {
		Paddle.rightTriggered = rightTriggered;
	}
	public static boolean isLeftTriggered() {
		return leftTriggered;
	}
	public static void setLeftTriggered(boolean leftTriggered) {
		Paddle.leftTriggered = leftTriggered;
	}
	
	//Nie jestem pewien czy te elementy nie powinny by� zaimplementowane w
	//cz�ci "Controller", jako, �e s� one elementem sterowania. Tak�e w razie czego
	//mam �wiadomo�� tego, �e mo�e to by� umieszczone w jednym miejscu
	//oraz, �e mo�na z��czy� to w jeden Handler implementuj�cy wszystkie trzy interfejsy
	//Zostawiam to tak, by by�o to dla mnie czytelniejsze w tym momencie
	public static class PaddleKeyDownHandler implements KeyDownHandler{
		
		@Override
		public void onKeyDown(KeyDownEvent event) {
			int key = event.getNativeKeyCode();
			
			//kierunek w prawo
			if (key == 39) {
				Paddle.setRightTriggered(true);
			
			//kierunek w lewo
			}else if (key == 37) {
				Paddle.setLeftTriggered(true);
			}	
		}
	}
	
	public static class PaddleKeyUpHandler implements KeyUpHandler{

		@Override
		public void onKeyUp(KeyUpEvent event) {
			int key = event.getNativeKeyCode();
			
			if (key == 39) {
				Paddle.setRightTriggered(false);
			} else if (key == 37) {
				Paddle.setLeftTriggered(false);
			}
		}	
	}
	
	public static class PaddleMouseMoveHandler implements MouseMoveHandler {
		
		Paddle paddle;
		
		public PaddleMouseMoveHandler(Paddle paddle) {
			this.paddle = paddle;
		}
		
		//Dla uproszczenia projektu u�y�em koordynat dla elementu Canvas, 
		//jednak dla wi�kszego komfortu gry nale�a�oby u�y� koordynat dla ca�ego ekranu
		//by mysz mog�a spe�nia� swoj� funkcj� poza ekranem gry
		
		//Trzeba dodatkowo wprowadzi� zmienn� canvasWidth je�li o niej nie zapomn� :)
		//Na razie na sztywno ustawiam 700
		@Override
		public void onMouseMove(MouseMoveEvent event) {
			if (event.getX() >= 0 && event.getX() + paddle.getPaddleWidth() <= MainScreen.getCanvaswidth()) {
				paddle.setCoordinateX(event.getX());
			}
			
		}
		
	}
	
	

}
