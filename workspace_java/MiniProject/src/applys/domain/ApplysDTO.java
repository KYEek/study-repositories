package applys.domain;

public class ApplysDTO {

	private int apply_no;
	private int job_postno;
	private int resume_no;
	private String applys_date;
	
	
	public String getApplys_date() {
		return applys_date;
	}
	public void setApplys_date(String applys_date) {
		this.applys_date = applys_date;
	}
	public int getApply_no() {
		return apply_no;
	}
	public void setApply_no(int apply_no) {
		this.apply_no = apply_no;
	}
	public int getJob_postno() {
		return job_postno;
	}
	public void setJob_postno(int job_postno) {
		this.job_postno = job_postno;
	}
	public int getResume_no() {
		return resume_no;
	}
	public void setResume_no(int resume_no) {
		this.resume_no = resume_no;
	}
	
	
}
