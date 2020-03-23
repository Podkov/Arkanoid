package com.podkov.client.model;

public class Bricks {
	
	private int numberOfRows;
	private int numberOfColumns;
	private int brickWidth;
	private int brickHeight;
	private int space;
	private int[][] bricksTab;
	private int numberOfDestroyedBricks;
	private int typeNumber;
	
	//inicjalizacja tablicy warunkuj¹cymi obecnoœæ cegie³ek oraz iloœæ ich ¿yæ
	public void initBricksTab(int numberOfRows, int numberOfColumns) {
		for (int i = 0; i < numberOfRows; i++) {
			for (int j = 0; j < numberOfColumns; j++) {
				if (i < 3) {
					this.bricksTab[i][j] = 3 - i;
					this.typeNumber = 3 - i;
				}else if(i >= 3){
					this.bricksTab[i][j] = 1;
					this.typeNumber = 1;
				}
			}
		}
		numberOfDestroyedBricks = 0;
	}
	
	
	
	public int getTypeNumber() {
		return typeNumber;
	}


	public void setTypeNumber(int typeNumber) {
		this.typeNumber = typeNumber;
	}


	public int getNumberOfDestroyedBricks() {
		return numberOfDestroyedBricks;
	}

	public void setNumberOfDestroyedBricks(int numberOfDestroyedBricks) {
		this.numberOfDestroyedBricks = numberOfDestroyedBricks;
	}

	public int[][] getBricksTab() {
		return bricksTab;
	}
	
	public void setBricksTab(int[][] bricksTab) {
		this.bricksTab = bricksTab;
	}
	
	//odejmuje 1 punkt wytrzyma³oœci cegie³ki
	public void setBricksTabCollision(int numberOfRow, int numberOfColumn) {
		this.bricksTab[numberOfRow][numberOfColumn] -= 1;
	}
	
	public int getNumberOfRows() {
		return numberOfRows;
	}
	public void setNumberOfRows(int numberOfRows) {
		if(numberOfRows >= 1) {
		this.numberOfRows = numberOfRows;
		}else {
			System.out.println("Number of rows must be higher or equal than 1");
		}
	}
	public int getNumberOfColumns() {
		return numberOfColumns;
	}
	
	public void setNumberOfColumns(int numberOfColumns) {
		if(numberOfColumns >= 1) {
		this.numberOfColumns = numberOfColumns;
		} else {
			System.out.println("Number of columns must be higher or equal than 1, but it is suggested to be even higher");
		}
	}
	public int getBrickWidth() {
		return brickWidth;
	}
	
	//tu mo¿naby zastosowaæ automatyczne ustalanie wielkoœci cegie³ki w zale¿noœci
	//od iloœci kolumn (przyjmuj¹c w argumencie szerokoœæ Canvas oraz iloœæ kolumn i dziel¹c waroœci), 
	//jednak¿e dla uproszczenia projektu zastosujê sztywn¹ wartoœæ, by nie by³o problemu z grafik¹
	
	public void setBrickWidth(int brickWidth) {
		if(brickWidth >= 10 && brickWidth <= 700) {
		this.brickWidth = brickWidth;
		} else {
			System.out.println("Brick width is too small or too large. Input value between 10 and 700");
		}
	}
	public int getBrickHeight() {
		return brickHeight;
	}
	
	//tu tak samo mo¿na by ustaliæ pewne zasady tej wartoœci, ale dla uproszczenia pomijam ten fragment
	public void setBrickHeight(int brickHeight) {
		if(brickHeight >= 5 && brickHeight <= 250) {
		this.brickHeight = brickHeight;
		} else {
			System.out.println("Brick height is too small or too large. Input value between 5 and 250");
		}
	}
	public int getSpace() {
		return space;
	}
	public void setSpace(int space) {
		if(space > 0 && space <= 5) {
		this.space = space;
		} else {
			System.out.println("Space between");
		}
	}
	
	

}
