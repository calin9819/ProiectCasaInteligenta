package controllers;

import java.awt.AWTException;
import java.io.IOException;
import java.util.Scanner;

import dataMapper.DeviceMapper;
import models.Device;
import views.DeviceViews;

public class DeviceController {
	DeviceViews views = new DeviceViews();
	DeviceMapper deviceMapper = new DeviceMapper();
	Device model = new Device();
	
	public void handleDeviceMenu(Scanner scanner) throws IOException, AWTException {
		this.views.deviceMenu();
		System.out.println("Alegerea dvs.:");
		int choice = Integer.parseInt(scanner.nextLine());
		switch (choice) {
			case 1:
				this.handleDeviceList(scanner);
				break;
			case 2:
				this.handleDeviceAdd(scanner);
				break;
			case 3:
				return;
			default:
				throw new IllegalArgumentException("Valoare invalida: " + choice);
		}
	}
	
	public void handleDeviceList(Scanner scanner) throws IOException, AWTException {
		Device[] devices = this.deviceMapper.read();
		this.views.deviceList(devices);
		this.views.deviceListMenu();
		System.out.println("Alegerea dvs.:");
		int choice = Integer.parseInt(scanner.nextLine());
		switch (choice) {
			case 1:
				System.out.print("Alegeti dispozitivul dupa id: ");
				int id = Integer.parseInt(scanner.nextLine());
				this.model = this.deviceMapper.getDevice(id);
				this.handleDeviceDetails(scanner);
				break;
			case 2:
				return;
			default:
				throw new IllegalArgumentException("Valoare invalida: " + choice);
		}
	}
	
	public void handleDeviceDetails(Scanner scanner) throws IOException, AWTException {
		this.views.deviceDetails(this.model);
		this.views.deviceDetailsMenu(this.model);
		System.out.println("Alegerea dvs.:");
		int choice = Integer.parseInt(scanner.nextLine());
		switch (choice) {
			case 1:
				this.model.setOn(!this.model.isOn());
				this.deviceMapper.update(this.model);
				break;
			case 2:
				this.deviceMapper.delete(this.model);
				break;
			case 3: 
				return;
			default:
				throw new IllegalArgumentException("Valoare invalida: " + choice);
		}
	}
	
	public void handleDeviceAdd(Scanner scanner) throws IOException, AWTException {
		System.out.println("Introduceti numele dispozitivului: ");
		String name = scanner.nextLine();
		
		Device device = new Device();
		device.setName(name);
		device.setOn(false);
		
		this.deviceMapper.create(device);
	}
}
