package view;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * @author ayrton
 *
 */
public class Message {

	private static Message instance;
	private Alert alert;
	
	
	
	private Message() {
		alert = new Alert(AlertType.INFORMATION);
	}
	
	public void viewMessage(AlertType type, 
			String title, String header, String message) {
		
		alert.setAlertType(type);
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(message);
		
		alert.show();
	}

	/**
	 * @return the instance
	 */
	public static Message getInstance() {
		if(instance == null) {
			instance = new Message();
		}
		return instance;
	}
	
}