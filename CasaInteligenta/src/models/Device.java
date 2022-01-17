package models;

public class Device {
	private int id;
	private String name;
	private boolean on;
	private int roomId;
	
	public Device(int id, String name, boolean on, int roomId) {
		super();
		this.id = id;
		this.name = name;
		this.on = on;
		this.setRoomId(roomId);
	}

	public Device() {
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

	public boolean isOn() {
		return on;
	}

	public void setOn(boolean on) {
		this.on = on;
	}

	public int getRoomId() {
		return roomId;
	}

	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}
}
