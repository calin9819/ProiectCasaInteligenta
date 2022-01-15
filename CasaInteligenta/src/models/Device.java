package models;

public class Device {
	private int id;
	private String name;
	private boolean on;
	
	public Device(int id, String name, boolean on) {
		super();
		this.id = id;
		this.name = name;
		this.on = on;
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
	
	
}
