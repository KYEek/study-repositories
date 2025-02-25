package tacos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificatonService {
	
	@Autowired
	private NotificationRepository repository;
	
	public void createNotification(String userId, String message) {
		Notification notification = new Notification(userId, message);
		repository.save(notification);
	}
	
	public List<Notification> getNotifications(String userId) {
		return repository.findByUserId(userId);
	}
	
	public void markAsRead(String notificationId) {
		Notification notification = repository.findById(notificationId).orElse(null);
		if (notification != null) {
			notification.setRead(true);
			repository.save(notification);
		}
	}
	
	public void deleteNotification(String notificationId) {
		repository.deleteById(notificationId);
	}
}
