package Models;

/**
 * Address POJO
 * @author Vivek
 *
 */

public class Address {
	private Integer id;
	private String street;
	private String apt_no;
	private String city;
	private String state;
	private String zip;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getApt_no() {
		return apt_no;
	}
	public void setApt_no(String apt_no) {
		this.apt_no = apt_no;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public Address(Integer id, String street, String apt_no, String city,
			String state, String zip) {
		super();
		this.id = id;
		this.street = street;
		this.apt_no = apt_no;
		this.city = city;
		this.state = state;
		this.zip = zip;
	}
	public Address(String street, String apt_no, String city, String state,
			String zip) {
		super();
		this.street = street;
		this.apt_no = apt_no;
		this.city = city;
		this.state = state;
		this.zip = zip;
	}
	public Address() {
		super();
	}
	

}
