package notificationManager;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.TrayIcon.MessageType;


//Costea Calin - Singleton
public class NotificationManager {
	private static NotificationManager instance = null;
	private SystemTray tray;

	private NotificationManager() {
		this.tray = SystemTray.getSystemTray();
	}
	
	public static NotificationManager getInstance()
    {
        if (instance == null)
        	instance = new NotificationManager();
 
        return instance;
    }
	
	public void displayMessage(String message, MessageType type) throws AWTException {
		Image img = Toolkit.getDefaultToolkit().createImage("info.png");
		TrayIcon icon = new TrayIcon(img, "Mesaj notificare");
		icon.setImageAutoSize(true);
		this.tray.add(icon);
		icon.displayMessage("Casa Inteligenta", message, type);
		System.out.println(message);
	}
}