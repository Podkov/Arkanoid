package com.podkov.client.controller;

import com.google.gwt.user.client.Timer;
import com.podkov.client.model.Ball;
import com.podkov.client.model.Bricks;
import com.podkov.client.model.CountdownTimer;
import com.podkov.client.model.Paddle;
import com.podkov.client.model.Paddle.PaddleKeyDownHandler;
import com.podkov.client.model.Paddle.PaddleKeyUpHandler;
import com.podkov.client.model.Paddle.PaddleMouseMoveHandler;
import com.podkov.client.view.Gui;
import com.podkov.client.view.music.MusicClass;
import com.podkov.client.view.screens.MainScreen;

public class Controller {
	
	private Gui gui;
	private Paddle paddle;
	private Ball ball;
	private Bricks bricks;
	private static boolean endGameFlag;
	
	private MusicClass musicObject = new MusicClass();

	private PaddleKeyDownHandler paddleKeyDownHandler = new PaddleKeyDownHandler();
	private PaddleKeyUpHandler paddleKeyUpHandler = new PaddleKeyUpHandler();
	private PaddleMouseMoveHandler paddleMouseMoveHandler;
	
	Timer t;
	
	public Controller() {
		
		gui = new Gui(this);
		
		t = new Timer() {
			
			@Override
			public void run() {
				gui.drawStartScreen();
			}
		};
		t.scheduleRepeating(100);
	}
	
	public void startNewGame() {
		t.cancel();
		initPaddle();
		initBall();
		initBricks();
		CountdownTimer.setTimeLeft(300);
		endGameFlag = false;
		musicObject.playMusic();
		
		paddleMouseMoveHandler = new PaddleMouseMoveHandler(paddle);
		
		gui.getMainScreen().getCanvas().addKeyDownHandler(paddleKeyDownHandler);
		gui.getMainScreen().getCanvas().addKeyUpHandler(paddleKeyUpHandler);
		gui.getMainScreen().getCanvas().addMouseMoveHandler(paddleMouseMoveHandler);
		
		Timer animationTimer = new Timer() {
			
			@Override
			public void run() {
				gui.drawGameScreen(ball, paddle, bricks);
				checkIfEndGame();
				if (endGameFlag) {
					this.cancel();
				}
			}
		};
		
		Timer countDownTimer = new Timer() {
			
			@Override
			public void run() {
				CountdownTimer.setTimeLeft(CountdownTimer.getTimeLeft() - 1);
				if (endGameFlag) {
					this.cancel();
				}
			}
		};
		
		animationTimer.scheduleRepeating(10);
		countDownTimer.scheduleRepeating(1000);
		
//		if (!endGameFlag) {
//			animationTimer.scheduleRepeating(10);
//			countDownTimer.scheduleRepeating(1000);
//		}else if (endGameFlag) {
//			animationTimer.cancel();
//			countDownTimer.cancel();
//		}
	}
	
	public void endGame(boolean isWinner) {
		gui.drawEndGameScreen(isWinner);
	}
	
	public void winGame() {
		endGame(true);
		musicObject.stopMusic();
		musicObject.playYouWin();
	}
	
	public void loseGame() {
		endGame(false);
		musicObject.stopMusic();
		musicObject.playGameOver();
	}
	
	//Metoda wymaga drobnych poprawek, by w stu procentach poprawnie wy³apywaæ kolizje
	public void collision() {
		double ballMiddlePointX = ball.getCoordinateX() + ball.getRadius();
		double ballMiddlePointY = ball.getCoordinateY() + ball.getRadius();
		
		int rowHeight = bricks.getBrickHeight() + bricks.getSpace();
		int columnWidth = bricks.getBrickWidth() + bricks.getSpace();
		
		int ballCurrentRow = (int)Math.floor(ball.getCoordinateY()/rowHeight);
		int ballCurrentColumn = (int)Math.floor(ball.getCoordinateX()/columnWidth);
		
		//kolizja z cegie³kami
		if (ball.getCoordinateY() < (bricks.getNumberOfRows() * rowHeight) && ballCurrentRow >= 0 && ballCurrentColumn >= 0 && bricks.getBricksTab()[ballCurrentRow][ballCurrentColumn] >= 1) {
			ball.setDirectionY(-ball.getDirectionY());
			musicObject.playWallBounce();
			
			//wywo³uje metodê odejmuj¹c¹ 1 wytrzyma³oœci cegie³ki
			bricks.setBricksTabCollision(ballCurrentRow, ballCurrentColumn);
			
			//zliczanie zniszczonych cegie³ek
			if (bricks.getBricksTab()[ballCurrentRow][ballCurrentColumn] == 0) {
				bricks.setNumberOfDestroyedBricks(bricks.getNumberOfDestroyedBricks() + 1);
				musicObject.playBrickBreak();
			}
		}
		
		//kolizja z pionowymi granicami
		if (ball.getCoordinateX() + (ball.getRadius() * 2) > MainScreen.getCanvaswidth() || ball.getCoordinateX() < 0) {
			ball.setDirectionX(-ball.getDirectionX());
			musicObject.playWallBounce();
		}
		
		//kolizja z poziomymi granicami
		if (ball.getCoordinateY() < 0) {
			ball.setDirectionY(-ball.getDirectionY());
			musicObject.playWallBounce();
		} else if (ballMiddlePointY + ball.getRadius() > MainScreen.getCanvasheight() - paddle.getPaddleHeight()) {
			
			//kolizja z paletk¹
			//Niestety nie uda³o mi siê osi¹gn¹æ regulacji odchylenia trajektorii w przedziale 0-60 stopni
			//Wykorzysta³em zatem znaleziony wzór daj¹cy zbli¿ony efekt
			//Mój opracowany algorytm niestety zawiód³
			if (ballMiddlePointX >= paddle.getCoordinateX() &&  ballMiddlePointX < paddle.getCoordinateX() + paddle.getPaddleWidth()) {
				ball.setDirectionY(-ball.getDirectionY());
				ball.setDirectionX(8 * ((ballMiddlePointX - ( paddle.getCoordinateX() + paddle.getPaddleWidth() / 2 ) ) / paddle.getPaddleWidth()));
				musicObject.playPaddleBounce();
			} else { // ucieczka pi³ki
				
				//wypuszczenie kolejnej pi³ki z pozycji startowej
				ball.setCoordinateX(0 + ball.getRadius());
				ball.setCoordinateY(MainScreen.getCanvasheight() - ball.getRadius()*2 - paddle.getPaddleHeight());
				ball.setDirectionX(1);
				ball.setDirectionY(-2);
				
				//odjêcie jednego ¿ycia
				ball.setNumberOfLives(ball.getNumberOfLives() - 1);
				
				musicObject.playBallLost();
			}
			
		}
	}
	
	public void checkIfEndGame() {
		if (CountdownTimer.getTimeLeft() <= 0 || ball.getNumberOfLives() == 0) {
			endGameFlag = true;
			loseGame();
		}else if (bricks.getNumberOfDestroyedBricks() == bricks.getNumberOfColumns() * bricks.getNumberOfRows()) {
			endGameFlag = true;
			winGame();
		}
	}
	
	public void initPaddle() {
		paddle = new Paddle();
		paddle.setPaddleWidth(90);
		paddle.setPaddleHeight(20);
		paddle.setCoordinateX(350 - paddle.getPaddleWidth()/2);
		paddle.setCoordinateY();
	}
	
	public void initBall() {
		ball = new Ball();
		ball.setRadius(10);
		ball.setSpeedLevel();
		ball.setCoordinateX(0 + ball.getRadius());
		ball.setCoordinateY(700 - ball.getRadius()*2 - paddle.getPaddleHeight());
		ball.setDirectionX(1);
		ball.setDirectionY(-2);
		ball.setNumberOfLives(3);
	}
	
	public void initBricks() {
		bricks = new Bricks();
		bricks.setNumberOfRows(3);
		bricks.setNumberOfColumns(10);
		bricks.setBrickWidth(70);
		bricks.setBrickHeight(25);
		bricks.setSpace(1);
		bricks.setBricksTab(new int[bricks.getNumberOfRows()][bricks.getNumberOfColumns()]);
		bricks.initBricksTab(bricks.getNumberOfRows(), bricks.getNumberOfColumns());
	}



	public static boolean isEndGameFlag() {
		return endGameFlag;
	}



	public static void setEndGameFlag(boolean endGameFlag) {
		Controller.endGameFlag = endGameFlag;
	}



	public PaddleKeyDownHandler getPaddleKeyDownHandler() {
		return paddleKeyDownHandler;
	}



	public PaddleKeyUpHandler getPaddleKeyUpHandler() {
		return paddleKeyUpHandler;
	}



	public PaddleMouseMoveHandler getPaddleMouseMoveHandler() {
		return paddleMouseMoveHandler;
	}
	
	
	
}
