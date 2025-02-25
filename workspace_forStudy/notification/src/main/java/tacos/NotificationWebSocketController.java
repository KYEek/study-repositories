package tacos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotificationWebSocketController {
	
	@Autowired
	private SimpMessagingTemplate messagingTemplate;
	
	public void sendNotification(String userId, String message) {
		Notification notification = new Notification(userId, message);
		
		messagingTemplate.convertAndSend("/topic/notifications/" + userId, notification);
	}
}
