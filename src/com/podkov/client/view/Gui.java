package com.podkov.client.view;

import com.google.gwt.canvas.dom.client.CssColor;
import com.google.gwt.dom.client.ImageElement;
import com.google.gwt.user.client.ui.Image;
import com.podkov.client.controller.Controller;
import com.podkov.client.model.Ball;
import com.podkov.client.model.Bricks;
import com.podkov.client.model.CountdownTimer;
import com.podkov.client.model.Paddle;
import com.podkov.client.view.screens.MainScreen;

public class Gui {
	
	private MainScreen mainScreen;
	private Controller controller;
	private Ball ball;
	private Paddle paddle;
	private Bricks bricks;
	
	ImageElement paddleImageElemnt, ballIE, brickYellowIE, brickBlue2IE, brickBlue1IE, brickRed3IE, brickRed2IE, brickRed1IE,
					startScreenIE, gameBackgroundIE, winScreenIE, loseScreenIE;
	
	//private PaddleKeyDownHandler paddleKeyDownHandler = new PaddleKeyDownHandler();
	//private PaddleKeyUpHandler paddleKeyUpHandler = new PaddleKeyUpHandler();
	//private PaddleMouseMoveHandler paddleMouseMoveHandler = new PaddleMouseMoveHandler(paddle);
	
	public Gui(Controller controller) {
		
		this.controller = controller;
		loadImages();
		mainScreen = new MainScreen(controller);
		//drawStartScreen();
		
	}
	
	public void drawStartScreen() {
		
		mainScreen.getContext().drawImage(startScreenIE, 0, 0);
	}
	
	public void drawGameScreen(Ball ball, Paddle paddle, Bricks bricks) {
		this.ball = ball;
		this.paddle = paddle;
		this.bricks = bricks;
		
		//dzia�a, ale wydajno�� tragiczna trzeba to st�d wywali�
		//PaddleMouseMoveHandler paddleMouseMoveHandler = new PaddleMouseMoveHandler(paddle);
		
		//przypisanie Handler�w tymczasowo w tym miejscu - stwarzaj� ogromne problemy z wydajno�ci�
//		mainScreen.getCanvas().addKeyDownHandler(controller.getPaddleKeyDownHandler());
//		mainScreen.getCanvas().addKeyUpHandler(controller.getPaddleKeyUpHandler());
//		mainScreen.getCanvas().addMouseMoveHandler(controller.getPaddleMouseMoveHandler());
		
		//wywo�anie metody sprawdzaj�cej kolizje
		controller.collision();
		
		//zaczytanie nowych pozycji pi�ki
		ball.ballMove();
		
		//oczyszczenie ekranu w celu wy�wietlenia kolejnej klatki
		clearCanvas();
		
		//wyrysowanie element�w
		drawPaddle();
		drawBricks();
		drawBall();
		
		//wy�wietlenie licznika z czasem
		mainScreen.getContext().setFillStyle(CssColor.make("RED"));
		mainScreen.getContext().setFont("bold 18px sans-serif");
		mainScreen.getContext().fillText("Time left: " + CountdownTimer.getTimeLeft(), 10, 350);
		
		//wy�wietlanie liczby �y�
		mainScreen.getContext().fillText("Lifes left: " + ball.getNumberOfLives(), 10, 375);
	}
	
	public void drawEndGameScreen(boolean isWinner) {
		if (isWinner) {
			mainScreen.getContext().drawImage(winScreenIE, 0, 0);
		}else {
			mainScreen.getContext().drawImage(loseScreenIE, 0, 0);
		}
		
	}
	
	public void clearCanvas() {
		mainScreen.getContext().clearRect(0, 0, MainScreen.getCanvaswidth(), MainScreen.getCanvasheight());
		
//		mainScreen.getContext().setLineWidth(2);
//		mainScreen.getContext().setStrokeStyle("#000000");
//		mainScreen.getContext().strokeRect(0, 0, MainScreen.getCanvaswidth(), MainScreen.getCanvasheight());
//		mainScreen.getContext().stroke();
		
		mainScreen.getContext().drawImage(gameBackgroundIE, 0, 0);
	}
	
	public void loadImages() {
		Image paddleImage = new Image("/images/paddle.png");
		paddleImageElemnt = ImageElement.as(paddleImage.getElement());
		
		Image ballImage = new Image("/images/ball.png");
		ballIE = ImageElement.as(ballImage.getElement());
		
		Image brickBlueTwoImage = new Image("/images/brickBlueTwo.png");
		brickBlue2IE = ImageElement.as(brickBlueTwoImage.getElement());
		
		Image brickBlueOneImage = new Image("/images/brickBlueOne.png");
		brickBlue1IE = ImageElement.as(brickBlueOneImage.getElement());
		
		Image brickRedThreeImage = new Image("/images/brickRedThree.png");
		brickRed3IE = ImageElement.as(brickRedThreeImage.getElement());
		
		Image brickRedTwoImage = new Image("/images/brickRedTwo.png");
		brickRed2IE = ImageElement.as(brickRedTwoImage.getElement());
		
		Image brickRedOneImage = new Image("/images/brickRedOne.png");
		brickRed1IE = ImageElement.as(brickRedOneImage.getElement());
		
		Image brickYellowImage = new Image("/images/brickYellow.png");
		brickYellowIE = ImageElement.as(brickYellowImage.getElement());
		
		Image gameBackGroundImage = new Image("/images/gameBackGround.png");
		gameBackgroundIE = ImageElement.as(gameBackGroundImage.getElement());
		
		Image loseScreenImage = new Image("/images/loseScreen.png");
		loseScreenIE = ImageElement.as(loseScreenImage.getElement());
		
		//problem - zbyt du�y obraz na start programu...
		//rozwi�zanie: zmiana lokalizacji wywo�ania metody loadImages()
		Image startScreenImage = new Image("/images/startScreen.png");
		startScreenIE = ImageElement.as(startScreenImage.getElement());
//		startScreenImage.addLoadHandler(new LoadHandler() {
//			
//			@Override
//			public void onLoad(LoadEvent event) {
//				// TODO Auto-generated method stub
//				startScreenIE = ImageElement.as(startScreenImage.getElement());
//				drawStartScreen();
//			}
//		});
//		
		Image winScreenImage = new Image("/images/winScreen.png");
		winScreenIE = ImageElement.as(winScreenImage.getElement());
	}
	
	public void drawPaddle() {
		
		//wywo�anie metody w celu aktywacji ruchu za pomoc� klawiatury
		paddle.paddleMoveByKeys();
		
		
		//odkomentowa� gdyby obrazki nie dzia�a�y
//		mainScreen.getContext().beginPath();
//		mainScreen.getContext().setFillStyle(CssColor.make("BLACK"));
//		mainScreen.getContext().fillRect(paddle.getCoordinateX(), paddle.getCoordinateY(), paddle.getPaddleWidth(), paddle.getPaddleHeight());
//		mainScreen.getContext().fill();
//		mainScreen.getContext().closePath();
		
		mainScreen.getContext().drawImage(paddleImageElemnt, paddle.getCoordinateX(), paddle.getCoordinateY());
	}
	
	public void drawBall() {
		
		mainScreen.getContext().drawImage(ballIE, ball.getCoordinateX(), ball.getCoordinateY());
		
//		mainScreen.getContext().beginPath();
//		mainScreen.getContext().setFillStyle(CssColor.make("RED"));
//		//mainScreen.getContext().arc(260, 260, 10, 0, Math.PI*2, true);     nie funkcjonuje ruch pi�ki
//		//dlatego zaimplementowa�em ruch kwadratu z zachowaniem jak u pi�ki
//		mainScreen.getContext().fillRect(ball.getCoordinateX(), ball.getCoordinateY(), ball.getRadius()*2, ball.getRadius()*2);
//		mainScreen.getContext().fill();
//		mainScreen.getContext().closePath();
	}
	
	public void drawBricks() {
		mainScreen.getContext().beginPath();
		
		for (int numberOfRow = 0; numberOfRow < bricks.getNumberOfRows(); numberOfRow++) {
			for (int numberOfColumn = 0; numberOfColumn < bricks.getNumberOfColumns(); numberOfColumn++) {
				
				int tempx = numberOfColumn * (bricks.getBrickWidth() + bricks.getSpace());
				int tempy = numberOfRow * (bricks.getBrickHeight() + bricks.getSpace());
				
				//zapomnia�em,�e by wykorzysta� brickType w odpowiedni spos�b musia�bym utworzy� tablic� obiekt�w typu Brick
				//maj�cych to pole... Tak�e by zmiana odcieniu funkcjonowa�a musia�bym przebudowa� troch� projekt
				//w zasobach aplikacji s� cegie�ki ze zmienionymi odcieniami
				
				//obecnie funkcjonuje zamiana wygl�du cegie�ki z czerwonej na niebiesk� i z niebieskiej na ��t�
				//po utracie �ycia
				if (bricks.getBricksTab()[numberOfRow][numberOfColumn] == 3) {
					mainScreen.getContext().drawImage(brickRed3IE, tempx, tempy);
					
//					mainScreen.getContext().setFillStyle(CssColor.make("RED"));
//					mainScreen.getContext().fillRect(tempx, tempy, bricks.getBrickWidth(), bricks.getBrickHeight());
//					mainScreen.getContext().fill();
					
				}else if (bricks.getBricksTab()[numberOfRow][numberOfColumn] == 2) {
					mainScreen.getContext().drawImage(brickBlue2IE, tempx, tempy);
					
//					mainScreen.getContext().setFillStyle(CssColor.make("BLUE"));
//					mainScreen.getContext().fillRect(tempx, tempy, bricks.getBrickWidth(), bricks.getBrickHeight());
//					mainScreen.getContext().fill();
					
				} else if (bricks.getBricksTab()[numberOfRow][numberOfColumn] ==1) {
					mainScreen.getContext().drawImage(brickYellowIE, tempx, tempy);
					
//					mainScreen.getContext().setFillStyle(CssColor.make("YELLOW"));
//					mainScreen.getContext().fillRect(tempx, tempy, bricks.getBrickWidth(), bricks.getBrickHeight());
//					mainScreen.getContext().fill();
				}
			}
		}
		
		//mainScreen.getContext().closePath();
	}

	public MainScreen getMainScreen() {
		return mainScreen;
	}
	
	

}
