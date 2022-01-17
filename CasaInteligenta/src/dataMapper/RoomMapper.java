package dataMapper;

import java.awt.AWTException;
import java.awt.TrayIcon.MessageType;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import models.Room;
import notificationManager.NotificationManager;

public class RoomMapper {
	NotificationManager notificationManager = NotificationManager.getInstance();
	
	private Room[] getRooms() throws IOException {
		
		BufferedReader reader = new BufferedReader(new FileReader("resources/rooms.csv"));  
		String line = "";
		String delimitator = ",";
		
		Room[] rooms = new Room[10000];
		int i = 0;
		
		while((line = reader.readLine()) != null) {
			String[] room = line.split(delimitator);
			
			Room roomObject = new Room();
			roomObject.setId(Integer.parseInt(room[0]));
			roomObject.setName(room[1]);
			
			rooms[i] = roomObject;
			i++;
		}
		
		reader.close();
		return rooms;
	}
	
	public Room getRoom(int id) throws IOException {
		Room[] rooms = this.getRooms();
		for (int i = 0; i < rooms.length; i++) {
			if (rooms[i].getId() == id) {
				return rooms[i];
			}
		}
		return null;
	}
	
	public void create(Room room) throws IOException, AWTException {
		Room[] rooms = this.getRooms();
		BufferedWriter writer = new BufferedWriter(new FileWriter("resources/rooms.csv", true));
		int id = 1;
		for (int i = 0; i < rooms.length; i++) {
			if (rooms[i] != null) {
				id++;
			}
		}
		String line = id + "," + room.getName() + "\n";
		writer.append(line);
		writer.close();
		notificationManager.displayMessage("A fost creat: " + id + ". " + room.getName(), MessageType.INFO);
	}
	
	public Room[] read() throws IOException {
		Room[] rooms = this.getRooms();
		return rooms;	
	}
	
	public void update(Room room) throws IOException, AWTException {
		Room[] rooms= this.getRooms();
		for (int i = 0; i < rooms.length; i++) {
			if (rooms[i].getId() == room.getId()) {
				rooms[i].setDevices(room.getDevices());
				break;
			}
		}
		
		BufferedWriter writer = new BufferedWriter(new FileWriter("resources/rooms.csv", false));
		for (int i = 0; i < rooms.length; i++) {
			if (rooms[i] != null) {
				String line = rooms[i].getId() + "," + rooms[i].getName() + "\n";
				writer.write(line);
			}
		}
		writer.close();
	}
	
	public void delete(Room room) throws IOException, AWTException {
		Room[] rooms= this.getRooms();
		
		boolean wasDeleted = false;
		BufferedWriter writer = new BufferedWriter(new FileWriter("resources/rooms.csv", false));
		for (int i = 0; i < rooms.length; i++) {
			if (rooms[i] != null) {
				if (rooms[i].getId() != room.getId()) {
					String line;
					if (!wasDeleted) {
						line = rooms[i].getId() + "," + rooms[i].getName() +"\n";
					} else {
						line = rooms[i].getId()-1 + "," + rooms[i].getName() +"\n";
					}
					writer.write(line);
				} else {
					wasDeleted = true;
				}
			}
		}
		writer.close();
		
		notificationManager.displayMessage("A fost sters: " + room.getId() + ". " + room.getName(), MessageType.INFO);
	}

}
