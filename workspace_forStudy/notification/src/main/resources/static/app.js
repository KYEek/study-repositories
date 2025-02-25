// 백엔드의 알림 API URL
const API_URL = 'http://localhost:8080/api/notifications';

// 현재 사용자 ID (예시로 '123' 사용)
const currentUserId = '123';

// 알림 데이터를 표시할 <ul> 요소를 선택
const notificationList = document.getElementById('notification-list');

/**
 * 알림 데이터를 REST API를 통해 가져오는 함수
 */
async function fetchNotifications() {
  try {
    // GET 요청: 특정 사용자의 알림 데이터를 가져옴
    const response = await fetch(`${API_URL}/${currentUserId}`);
    
    // 요청 실패 시 에러 발생
    if (!response.ok) {
      throw new Error(`HTTP error! status: ${response.status}`);
    }
    
    // JSON 형태로 응답 데이터를 파싱
    const notifications = await response.json();
    
    // 받아온 데이터를 화면에 업데이트
    updateNotificationList(notifications);
  } catch (error) {
    console.error('알림 데이터를 불러오는데 실패함:', error);
  }
}

/**
 * 받아온 알림 데이터를 이용해 DOM을 업데이트하는 함수
 * @param {Array} notifications - 알림 데이터 배열
 */
function updateNotificationList(notifications) {
  // 기존 알림 목록 초기화
  notificationList.innerHTML = '';
  
  // 각 알림 데이터를 순회하며 <li> 요소 생성
  notifications.forEach(notification => {
    const li = document.createElement('li');
    li.textContent = notification.message;
    
    // 이미 읽은 알림은 회색으로 표시
    if (notification.isRead) {
      li.style.color = '#999';
    }
    
    // 알림 클릭 시 읽음 처리 API 호출
    li.addEventListener('click', () => markAsRead(notification.id, li));
    
    // 생성한 <li> 요소를 목록에 추가
    notificationList.appendChild(li);
  });
}

/**
 * 특정 알림을 읽음 처리하는 함수 (PUT 요청)
 * @param {string} notificationId - 읽음 처리할 알림의 ID
 * @param {HTMLElement} listItem - 클릭한 알림의 <li> 요소
 */
async function markAsRead(notificationId, listItem) {
  try {
    const response = await fetch(`${API_URL}/read/${notificationId}`, {
      method: 'PUT'
    });
    
    // API 호출 성공 시, UI 업데이트: 텍스트 색상을 회색으로 변경
    if (response.ok) {
      listItem.style.color = '#999';
    }
  } catch (error) {
    console.error('읽음 처리 실패:', error);
  }
}

// 페이지 로드 시 알림 데이터를 가져오는 함수 실행
fetchNotifications();