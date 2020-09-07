package Model;

public class Items {
	private String id,name,sp_id,sec_id,unit;
	private String price;
	private String status;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSp_id() {
		return sp_id;
	}
	public void setSp_id(String sp_id) {
		this.sp_id = sp_id;
	}
	public String getSec_id() {
		return sec_id;
	}
	public void setSec_id(String sec_id) {
		this.sec_id = sec_id;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	
	
	
	public Items(String id, String name, String sp_id, String sec_id,String price, String unit, String status) {
		super();
		this.id = id;
		this.name = name;
		this.sp_id = sp_id;
		this.sec_id = sec_id;
		this.unit = unit;
		this.price = price;
		this.status = status;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Items() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
