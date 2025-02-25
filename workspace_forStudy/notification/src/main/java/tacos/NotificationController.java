package tacos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

	@Autowired
	private NotificatonService service;
	
	@PostMapping
	public void createNotification(@RequestParam String userId, @RequestParam String message) {
		service.createNotification(userId, message);
	}
	
	@GetMapping("/{userId}")
	public List<Notification> getNotifications(@PathVariable String userId) {
		return service.getNotifications(userId);
	}
	
	@PutMapping("read/{id}")
	public void markAsRead(@PathVariable String id) {
		service.markAsRead(id);
	}
	
	@DeleteMapping("{id}")
	public void deleteNotification(@PathVariable String id) {
		service.deleteNotification(id);
	}
	
	
}
