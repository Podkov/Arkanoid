package com.podkov.client.view.dropdownlists;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.user.client.ui.ListBox;
import com.podkov.client.model.Ball;

public class DropDownListOfLevels {

	private static ListBox dropBox;

	public static ListBox getDropBox() {
		return dropBox;
	}

	//zainicjalizowanie wdgetu z rozwijan� list� do wybrania poziomu trudno�ci gry
	public static void initDropBox() {
		DropDownListOfLevelsChangeHandler dropDownListOfLevelsChangeHandler = new DropDownListOfLevelsChangeHandler();		
		
		dropBox = new ListBox(false);
		Integer[] difficultyLevels = new Integer[5];
		
		//zape�nienie listy poziomami od 1 do 5
		for (int i = 0; i < difficultyLevels.length; i++) {
			difficultyLevels[i] = i + 1;
			dropBox.addItem(difficultyLevels[i].toString());
		}
		dropBox.addChangeHandler(dropDownListOfLevelsChangeHandler);
		dropBox.setVisibleItemCount(1);
	}
	
	public static class DropDownListOfLevelsChangeHandler implements ChangeHandler {

		@Override
		public void onChange(ChangeEvent event) {
			
			//przekazanie warto�ci wybranego poziomu do tymczasowej warto�ci gotowej do wprowadzenia do gry
			Ball.setTempLevel(Integer.parseInt(dropBox.getSelectedItemText()));
		}
		
	}
	
	
	
}
