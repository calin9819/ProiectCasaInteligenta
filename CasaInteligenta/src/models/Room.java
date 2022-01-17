package models;

import java.util.List;

public class Room {
	private int id;
	private String name;
	private List<Device> devices;
	
	public Room(int id, String name) {
		super();
		this.id = id;
		this.name = name;
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
}
