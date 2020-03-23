package com.podkov.client;

import com.google.gwt.core.client.EntryPoint;
import com.podkov.client.controller.Controller;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class ArkanoidV1 implements EntryPoint {
	
	public void onModuleLoad() {
		Controller controller = new Controller();
	}
}
