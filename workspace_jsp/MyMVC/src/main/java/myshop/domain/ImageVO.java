package myshop.domain;

//VO(value Object) == DTO(Date Transfer Object)

public class ImageVO {
	private int imgno;
	private String imgname;
	
	
	
	
	public int getImgno() {
		return imgno;
	}
	public void setImgno(int imgno) {
		this.imgno = imgno;
	}
	public String getImgname() {
		return imgname;
	}
	public void setImgname(String imgname) {
		this.imgname = imgname;
	}
	public String getImgfilename() {
		return imgfilename;
	}
	public void setImgfilename(String imgfilename) {
		this.imgfilename = imgfilename;
	}
	private String imgfilename;
	
	
}