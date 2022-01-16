package models;

import java.util.List;

public class Room {
	private int id;
	private String name;
	private List<Device> devices;
	
	public Room(int id, String name, List<Device> devices) {
		super();
		this.id = id;
		this.name = name;
		this.devices = devices;
	}
	
	public Room() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Device> getDevices() {
		return devices;
	}

	public void setDevices(List<Device> devices) {
		this.devices = devices;
	}
	
	public void addDeviceInRoom(Device device) {
		//TODO: Crash: Cannot invoke "java.util.List.add(Object)" becouse this.devices is null
		devices.add(device);
	}
	
	public void removeDeviceFromRoom(Device device) {
		devices.remove(device);
	}
	
	

}
