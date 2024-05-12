package model;

public class CustomerVO {
	private int c_no; //회원번호
	private String c_name; //회원이름
	private String c_bday; //회원 생년월일
	private String c_email; //회원 이메일
	private String c_addr; //회원 주소
	private String c_phone; //회원 폰번호
	private String c_id; //회원 아이디
	private String c_pw; //회원 비밀번호
	private int c_point; //회원 적립포인트
	public CustomerVO() {
		super();
	}
	public CustomerVO(int c_no, String c_name, String c_bday, String c_email, String c_addr, String c_phone,
			String c_id, String c_pw, int c_point) {
		super();
		this.c_no = c_no;
		this.c_name = c_name;
		this.c_bday = c_bday;
		this.c_email = c_email;
		this.c_addr = c_addr;
		this.c_phone = c_phone;
		this.c_id = c_id;
		this.c_pw = c_pw;
		this.c_point = c_point;
	}
	public int getC_no() {
		return c_no;
	}
	public void setC_no(int c_no) {
		this.c_no = c_no;
	}
	public String getC_name() {
		return c_name;
	}
	public void setC_name(String c_name) {
		this.c_name = c_name;
	}
	public String getC_bday() {
		return c_bday;
	}
	public void setC_bday(String c_bday) {
		this.c_bday = c_bday;
	}
	public String getC_email() {
		return c_email;
	}
	public void setC_email(String c_email) {
		this.c_email = c_email;
	}
	public String getC_addr() {
		return c_addr;
	}
	public void setC_addr(String c_addr) {
		this.c_addr = c_addr;
	}
	public String getC_phone() {
		return c_phone;
	}
	public void setC_phone(String c_phone) {
		this.c_phone = c_phone;
	}
	public String getC_id() {
		return c_id;
	}
	public void setC_id(String c_id) {
		this.c_id = c_id;
	}
	public String getC_pw() {
		return c_pw;
	}
	public void setC_pw(String c_pw) {
		this.c_pw = c_pw;
	}
	public int getC_point() {
		return c_point;
	}
	public void setC_point(int c_point) {
		this.c_point = c_point;
	}
	@Override
	public String toString() {
		return "[" + c_no + ",\t" + c_name + ",\t" + c_bday + ",\t" + c_email
				+ ",\t" + c_addr + ",\t" + c_phone + ",\t" + c_id + ",\t" + c_pw + ",\t"
				+ c_point + "]";
	}
	
}
