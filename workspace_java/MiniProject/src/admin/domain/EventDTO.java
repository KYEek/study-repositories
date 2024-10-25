package admin.domain;

public class EventDTO {

	// field
	private int event_no;			// 이벤트번호
	private String event_name;		// 이벤트명
	private String event_start;		// 이벤트시작일
	private String event_end;		// 이벤트종료일
	private String event_contents;	// 이벤트내용
	
	// select용
	private String during_days;		// 남은일수
	
	// getter & setter
	public int getEvent_no() {
		return event_no;
	}
	public void setEvent_no(int event_no) {
		this.event_no = event_no;
	}
	public String getEvent_name() {
		return event_name;
	}
	public void setEvent_name(String event_name) {
		this.event_name = event_name;
	}
	public String getEvent_start() {
		return event_start;
	}
	public void setEvent_start(String event_start) {
		this.event_start = event_start;
	}
	public String getEvent_end() {
		return event_end;
	}
	public void setEvent_end(String event_end) {
		this.event_end = event_end;
	}
	public String getEvent_contents() {
		return event_contents;
	}
	public void setEvent_contents(String event_contents) {
		this.event_contents = event_contents;
	}
	public String getDuring_days() {
		return during_days;
	}
	public void setDuring_days(String during_days) {
		this.during_days = during_days;
	}
	
}
