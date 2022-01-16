package dataMapper;

import java.awt.AWTException;
import java.awt.TrayIcon.MessageType;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import models.Device;
import notificationManager.NotificationManager;

public class DeviceMapper {
	NotificationManager notificationManager = NotificationManager.getInstance();
	
	private Device[] getDevices() throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader("resources/devices.csv"));  
		String line = "";
		String delimitator = ",";
		
		Device[] devices = new Device[10000];
		int i = 0;
		
		while ((line = reader.readLine()) != null) {
			String[] device = line.split(delimitator);
			
			Device deviceObject = new Device();
			deviceObject.setId(Integer.parseInt(device[0]));
			deviceObject.setName(device[1]);
			deviceObject.setOn(Boolean.parseBoolean(device[2]));
			
			devices[i] = deviceObject;
			i++;
		}
		
		reader.close();
		return devices;
	}
	
	public Device getDevice(int id) throws IOException {
		Device[] devices = this.getDevices();
		for (int i = 0; i < devices.length; i++) {
			if (devices[i].getId() == id) {
				return devices[i];
			}
		}
		return null;
	}
	
	public void create(Device device) throws IOException, AWTException {
		Device[] devices = this.getDevices();
		BufferedWriter writer = new BufferedWriter(new FileWriter("resources/devices.csv", true));
		int id = 1;
		for (int i = 0; i < devices.length; i++) {
			if (devices[i] != null) {
				id++;
			}
		}
		String line = id + "," + device.getName() + "," + device.isOn() +"\n";
		writer.append(line);
		writer.close();
		notificationManager.displayMessage("A fost creat: " + id + ". " + device.getName(), MessageType.INFO);
	}
	
	public Device[] read() throws IOException {
		Device[] devices = this.getDevices();
		return devices;
	}

	public void update(Device device) throws IOException, AWTException {
		Device[] devices = this.getDevices();
		for (int i = 0; i < devices.length; i++) {
			if (devices[i].getId() == device.getId()) {
				devices[i].setOn(device.isOn());
				break;
			}
		}
		
		BufferedWriter writer = new BufferedWriter(new FileWriter("resources/devices.csv", false));
		for (int i = 0; i < devices.length; i++) {
			if (devices[i] != null) {
				String line = devices[i].getId() + "," + devices[i].getName() + "," + devices[i].isOn() +"\n";
				writer.write(line);
			}
		}
		writer.close();
		
		if (device.isOn()) {
			notificationManager.displayMessage("A fost pornit: " + device.getId() + ". " + device.getName(), MessageType.INFO);
		} else {
			notificationManager.displayMessage("A fost oprit: " + device.getId() + ". " + device.getName(), MessageType.ERROR);
		}
	}
	
	public void delete(Device device) throws IOException, AWTException {
		Device[] devices = this.getDevices();
		
		boolean wasDeleted = false;
		BufferedWriter writer = new BufferedWriter(new FileWriter("resources/devices.csv", false));
		for (int i = 0; i < devices.length; i++) {
			if (devices[i] != null) {
				if (devices[i].getId() != device.getId()) {
					String line;
					if (!wasDeleted) {
						line = devices[i].getId() + "," + devices[i].getName() + "," + devices[i].isOn() +"\n";
					} else {
						line = devices[i].getId()-1 + "," + devices[i].getName() + "," + devices[i].isOn() +"\n";
					}
					writer.write(line);
				} else {
					wasDeleted = true;
				}
			}
		}
		writer.close();
		
		notificationManager.displayMessage("A fost sters: " + device.getId() + ". " + device.getName(), MessageType.INFO);
	}
}
