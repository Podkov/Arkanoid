package com.podkov.client.view.screens;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.podkov.client.controller.Controller;
import com.podkov.client.view.dropdownlists.DropDownListOfLevels;

public class MainScreen {
	
	Controller controller;
	
	private Canvas canvas;
	private Context2d context;
	private static final int canvasHeight = 700;
	private static final int canvasWidth = 700;
	private static final String divTagId = "canvasholder";
	
	private Button startButton;
	//private StartButtonHandler startButtonHandler = new StartButtonHandler();
	
	public MainScreen(Controller controller) {
		
		this.controller = controller;
		
		HorizontalPanel hPanel = new HorizontalPanel();
		hPanel.setSpacing(15);
		
		DropDownListOfLevels.initDropBox();
		hPanel.add(DropDownListOfLevels.getDropBox());
		
		startButton = new Button("Start Game");
		
		//wymaga statycznej metody startNewGame()
		//startButton.addClickHandler(startButtonHandler);
		//Lambdy nie dzia³aj¹ :D
		//startButton.addClickHandler(e -> controller.startNewGame());
		
		//nie dzia³a - wymaga controllera final
		//wiêc trzeba by³o obejœæ system
		startButton.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				//controller.startNewGame();
				executeStartNewGameMethod();
			}
		});
		
		hPanel.add(startButton);
		
		RootPanel.get().add(hPanel);
		
		canvas = Canvas.createIfSupported();
		
		if (canvas == null) {
			RootPanel.get().add(new Label("Your browser does not support Canvas"));
			return;
		}
		
		canvas.setStyleName("mainStyle");
		
		canvas.setWidth(canvasWidth + "px");
		canvas.setCoordinateSpaceWidth(canvasWidth);
		
		canvas.setHeight(canvasHeight + "px");
		canvas.setCoordinateSpaceHeight(canvasHeight);
		
		RootPanel.get(divTagId).add(canvas);
		
		context = canvas.getContext2d();
		
	}
	
	private void executeStartNewGameMethod() {
		controller.startNewGame();
	}

	public Canvas getCanvas() {
		return canvas;
	}

	public void setCanvas(Canvas canvas) {
		this.canvas = canvas;
	}

	public Context2d getContext() {
		return context;
	}

	public void setContext(Context2d context) {
		this.context = context;
	}

	public static int getCanvasheight() {
		return canvasHeight;
	}

	public static int getCanvaswidth() {
		return canvasWidth;
	}

	public static String getDivtagid() {
		return divTagId;
	}

	//pomys³ nie funkcjonuje przy niestatycznej metodzie w której chcemy przekazaæ controller
//	public static class StartButtonHandler implements ClickHandler{
//
//		@Override
//		public void onClick(ClickEvent event) {
//			
//			//wywo³uje metodê z Controllera inicjalizuj¹c¹ grê
//			Controller.startNewGame();
//		}
//		
//	}

	
}
