package controllers;

import java.awt.AWTException;
import java.io.IOException;
import java.util.Scanner;

import notificationManager.NotificationManager;
import views.ApplicationViews;

public class ApplicationController {
	ApplicationViews views = new ApplicationViews();
	NotificationManager notificationManager = NotificationManager.getInstance();
	DeviceController deviceController = new DeviceController();
	RoomController roomController = new RoomController();
	
	public void launchApplication() throws AWTException, IOException {
		Scanner scanner = new Scanner(System.in);
		while (true) {
			int command = this.handleMainMenu(scanner);
			if (command == -1) {
				scanner.close();
				return;
			}
		}
	}
	
	public int handleMainMenu(Scanner scanner) throws AWTException, IOException {
		this.views.mainMenu();
		System.out.println("Alegerea dvs.: ");
		int choice = Integer.parseInt(scanner.nextLine());
		switch (choice) {
			case 1:
				this.deviceController.handleDeviceMenu(scanner);
				break;
			case 2:
				this.roomController.handleRoomMenu(scanner);
				break;
			case 3:
				return -1;
			default:
				throw new IllegalArgumentException("Valoare invalida: " + choice);
		}
		return 0;
	}
}
