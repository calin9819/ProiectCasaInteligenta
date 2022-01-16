package views;

import models.Device;
import models.Room;

public class RoomViews {
	
	public void roomMenu() {
		System.out.println("1. Lista camerelor");
		System.out.println("2. Adauga o camera");
		System.out.println("3. Inapoi");
	}
	
	public void roomListMenu() {
		System.out.println();
		System.out.println("1. Selecteaza camera");
		System.out.println("2. Inapoi");
	}
	
	public void roomDetails(Room room) {
		System.out.println("Id camera: " + room.getId());
		System.out.println("Denumirea camerei: " + room.getName());
		System.out.println("Dispozitive aflate in camera: ");
		if(room.getDevices() != null) {
			for(int i = 0; i < room.getDevices().size(); i++) {
				System.out.println(i + " " + room.getDevices().get(i));
			}
			System.out.println();
		} else {
			System.out.println("Camera nu are nici un dispozitiv! \n");
		}
	}
	
	public void roomDetailsMenu(Room room) {
		if (room.getDevices() == null) {
			System.out.println("1. Adaugati un dispozitiv.");
		} else {
			System.out.println("1. Modificati starea dispozitivelor");
		}
		System.out.println("2. Stergeri camera");
		System.out.println("3. Inapoi");
	}

	public void roomList(Room[] rooms) {
		System.out.println("Lista camerilor:");
		for(int i = 0; i < rooms.length; i++) {
			if(rooms[i] != null) {
				System.out.println(rooms[i].getId() + ": " + rooms[i].getName());
				if(rooms[i].getDevices() != null) {
					System.out.print(" include urmatoarele device-uri:");
					for(int x = 0; x < rooms[i].getDevices().size(); x++) {
						System.out.println(x + " " + rooms[i].getDevices().get(i));
					}
				}
			}
		}
	}
	
	public void addDeviceInRoom(Room room, Device device) {
		room.addDeviceInRoom(device);
		System.out.println("Ati adaugat: " + device.getName() + " in camera " + room.getName());
	}
	
}
