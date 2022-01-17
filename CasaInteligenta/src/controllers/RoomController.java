package controllers;

import java.awt.AWTException;
import java.awt.TrayIcon.MessageType;
import java.io.IOException;
import java.util.Collections;
import java.util.Scanner;

import dataMapper.DeviceMapper;
import dataMapper.RoomMapper;
import models.Device;
import models.Room;
import views.DeviceViews;
import views.RoomViews;

public class RoomController {
	
	RoomViews views = new RoomViews();
	RoomMapper mapper = new RoomMapper();
	Room room = new Room();
	Device device = new Device();
	DeviceMapper deviceMapper = new DeviceMapper();
	DeviceViews deviceViews = new DeviceViews();
	
	public void handleRoomMenu(Scanner scanner) throws IOException, AWTException {
		this.views.roomMenu();
		System.out.println("Alegerea dvs.: ");
		int choice = Integer.parseInt(scanner.nextLine());
		switch(choice) {
			case 1:
				this.handleRoomList(scanner);
				break;
			case 2:
				this.handleRoomAdd(scanner);
				break;
			case 3:
				return;
			default:
				throw new IllegalArgumentException("Valoare invalida: " + choice);
		}
	}
	
	public void handleRoomList(Scanner scanner) throws IOException, AWTException {
		Room[] rooms = this.mapper.read();
		this.views.roomList(rooms);
		this.views.roomListMenu();
		System.out.println("Alegerea dvs.: ");
		int choice = Integer.parseInt(scanner.nextLine());
		switch(choice) {
			case 1:
				System.out.println("Alegeti camera dupa id: ");
				int id = Integer.parseInt(scanner.nextLine());
				this.room = this.mapper.getRoom(id);
				this.handleRoomDetails(scanner);
				break;
			case 2: 
				return;
			default: 
				throw new IllegalArgumentException("Valoare invalida: " + choice);
		}
	}
	
	public void handleRoomDetails(Scanner scanner) throws IOException, AWTException {
		Device[] devices = this.deviceMapper.read();
		Device[] devicesInRoom = new Device[1000];
		int deviceCount = 0;
		for (int i = 0; i < devices.length; i++) {
			if (devices[i] != null) {
				if (devices[i].getRoomId() == this.room.getId()) {
					devicesInRoom[deviceCount] = devices[i];
					deviceCount++;
				}
			}
		}
		this.views.roomDetails(this.room, devicesInRoom);
		this.views.roomDetailsMenu(this.room);
		System.out.println("Alegerea dvs.:");
		int choice = Integer.parseInt(scanner.nextLine());
		switch(choice) {
		case 1:
			addInRoom(scanner);
			break;
		case 2:
			this.mapper.delete(this.room);
			for (int i = 0; i < deviceCount; i++) {
				devicesInRoom[i].setRoomId(0);
				this.deviceMapper.update(devicesInRoom[i]);
			}
		case 3: 
			return;
		default:
			throw new IllegalArgumentException("Valoare invalida: " + choice);
		}
	}
	
	public void addInRoom(Scanner scanner) throws IOException, AWTException {
		Device[] devices = this.deviceMapper.read();
		this.deviceViews.deviceList(devices);
		System.out.println();
		this.deviceViews.deviceListMenu();
		System.out.println("Alegerea dvs.:");
		int choice = Integer.parseInt(scanner.nextLine());
		switch(choice) {
			case 1:
				System.out.println("Alegeti dispozitivul dupa id: ");
				int id = Integer.parseInt(scanner.nextLine());
				Device device = this.deviceMapper.getDevice(id);
				device.setRoomId(this.room.getId());
				this.deviceMapper.update(device);
				break;		
			case 2:
				return;
			default:
				throw new IllegalArgumentException("Valoare invalida: " + choice);
		}
	}
	
	public void handleRoomAdd(Scanner scanner) throws IOException, AWTException {
		System.out.println("Intrudeceti numele camerei: ");
		String name = scanner.nextLine();
		Room room = new Room();
		room.setName(name);
		room.setDevices(Collections.<Device>emptyList());
		
		this.mapper.create(room);
	}
}
