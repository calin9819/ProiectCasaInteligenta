package views;

import models.Device;

public class DeviceViews {
	public void deviceMenu() {
		System.out.println("1. Lista dispozitive");
		System.out.println("2. Adaugare dispozitiv");
		System.out.println("3. Inapoi");
	}
	
	public void deviceListMenu() {
		System.out.println("1. Selectati un dispozitiv");
		System.out.println("2. Inapoi");
	}
	
	public void deviceDetails(Device device) {
		System.out.println("Id dispozitiv: " + device.getId());
		System.out.println("Nume dispozitiv: " + device.getName());
		System.out.print("Status dispozitiv: ");
		if (device.isOn()) {
			System.out.print("on\n");
		} else {
			System.out.print("off\n");
		}
	}
	
	public void deviceDetailsMenu(Device device) {
		if (device.isOn()) {
			System.out.println("1. Opriti dispozitivul");
		} else {
			System.out.println("1. Porniti dispozitivul");
		}
		System.out.println("2. Stergeti dispozitivul");
		System.out.println("3. Inapoi");
	}
	
	public void deviceList(Device[] devices) {
		System.out.println("Lista dispozitive:");
		for (int i = 0; i < devices.length; i++) {
			if (devices[i] != null) {
				System.out.print(devices[i].getId() + ": " + devices[i].getName() + " - ");
				if (devices[i].isOn()) {
					System.out.print("on\n");
				} else {
					System.out.print("off\n");
				}
			}
		}
	}
}
