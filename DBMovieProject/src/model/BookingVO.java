package model;

public class BookingVO {
	private int b_no; //예약번호
	private int m_no; //영화번호
	private int c_no; //회원번호
	private String b_date; //예매한 날짜
	private String b_price; //성인, 청소년
	private String b_seat; // 자리
	private int b_count; // 수량
	
	public BookingVO() {
		super();
	}

	public BookingVO(int b_no, int m_no, int c_no, String b_date, String b_price, int b_count) {
		super();
		this.b_no = b_no;
		this.m_no = m_no;
		this.c_no = c_no;
		this.b_date = b_date;
		this.b_price = b_price;
		this.b_count = b_count;
	}

	public int getB_no() {
		return b_no;
	}

	public void setB_no(int b_no) {
		this.b_no = b_no;
	}

	public int getM_no() {
		return m_no;
	}

	public void setM_no(int m_no) {
		this.m_no = m_no;
	}

	public int getC_no() {
		return c_no;
	}

	public void setC_no(int c_no) {
		this.c_no = c_no;
	}

	public String getB_date() {
		return b_date;
	}

	public void setB_date(String b_date) {
		this.b_date = b_date;
	}

	public String getB_price() {
		return b_price;
	}

	public void setB_price(String b_price) {
		this.b_price = b_price;
	}

	public int getB_count() {
		return b_count;
	}

	public void setB_count(int b_count) {
		this.b_count = b_count;
	}
	

	public String getB_seat() {
		return b_seat;
	}

	public void setB_seat(String b_seat) {
		this.b_seat = b_seat;
	}

	@Override
	public String toString() {
		return "[" + b_no + ",\t" + m_no + ",\t" + c_no + ",\t" + b_date + ",\t"
				+ b_price + ",\t" + b_count + "]";
	}
	
}
