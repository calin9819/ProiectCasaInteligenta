package main;

import java.awt.AWTException;
import java.io.IOException;

import controllers.ApplicationController;

public class main {

	public static void main(String[] args) throws AWTException, IOException {
		ApplicationController controller = new ApplicationController();
		controller.launchApplication();
	}

}
