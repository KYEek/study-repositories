// 현재 사용자 ID (예시로 '123' 사용)
const currentUserId = "123";

// 알림 목록을 표시할 <ul> 요소 선택
const notificationList = document.getElementById("notification-list");

const socket = new SockJS("http://localhost:8080/ws-notifications");
const stompClient = Stomp.over(socket);

stompClient.connect({}, function (frame) {
  console.log("webSocket 연결됨: " + frame);

  stompClient.subscribe(
    "/topic/notifications/" + currentUserId,
    function (notificationMessage) {
      const notification = JSON.parse(notificationMessage.body);
      displayNotification(notification);
    }
  );
});

function displayNotification(notification) {
  const li = document.createElement("li");
  li.textContent = notification.message;
  notificationList.appendChild(li);
}
