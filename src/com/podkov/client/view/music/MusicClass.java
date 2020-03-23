package com.podkov.client.view.music;

import com.google.gwt.media.client.Audio;

public class MusicClass {
	
	private Audio music, ballLost, brickBreak, gameOver, paddleBounce,
				wallBounce, youWin, youWin2;
	
	public MusicClass() {
		loadMusic();
	}
	
	
	public void playMusic() {
		
		music.setLoop(true);
		music.play();
	}
	
	public void stopMusic() {
		music.pause();
	}
	
	public void playBallLost() {
		ballLost.play();
	}
	
	public void playBrickBreak() {
		brickBreak.play();
	}
	
	public void playGameOver() {
		gameOver.play();
	}
	
	public void playPaddleBounce() {
		paddleBounce.play();
	}
	
	public void playWallBounce() {
		wallBounce.play();
	}
	
	public void playYouWin() {
		youWin.play();
	}
	
	public void playYouWin2() {
		youWin2.play();
	}
	
	public void loadMusic() {
		music = Audio.createIfSupported();
		
		//losowanie, bo nie mog³em siê zdecydowaæ na muzykê :D
		if(System.currentTimeMillis() % 2 == 0) {
			music.setSrc("/music/music1.mp3");
		}else {
			music.setSrc("/music/music2.mp3");
		}
		music.load();
		
		ballLost = Audio.createIfSupported();
		ballLost.setSrc("/music/ballLost.wav");
		ballLost.load();
		
		brickBreak = Audio.createIfSupported();
		brickBreak.setSrc("/music/brickBreak.wav");
		brickBreak.load();
		
		gameOver = Audio.createIfSupported();
		gameOver.setSrc("/music/gameOver.wav");
		gameOver.load();
		
		paddleBounce = Audio.createIfSupported();
		paddleBounce.setSrc("/music/paddleBounce.wav");
		paddleBounce.load();
		
		wallBounce = Audio.createIfSupported();
		wallBounce.setSrc("/music/wallBounce.wav");
		wallBounce.load();
		
		youWin = Audio.createIfSupported();
		youWin.setSrc("/music/youWin.wav");
		youWin.load();
		
		youWin2 = Audio.createIfSupported();
		youWin2.setSrc("/music/youWin2.mp3");
		youWin2.load();
	}

}
