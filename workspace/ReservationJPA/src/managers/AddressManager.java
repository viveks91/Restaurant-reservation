package managers;

import java.io.Serializable;

import models.Address;
import dao.AddressDAO;

public class AddressManager implements Serializable {
	
	private AddressDAO addr_dao = new AddressDAO();
	
	public int createAddress (Address address) {
		// Create only if the address does not already exist
		if(addr_dao.findByAddress(address).isEmpty())
			addr_dao.create(address);
		return address.getId();
	}
	
	public Address findAddressById (int id) {
		Address address = addr_dao.findById(id);
		return address;
	}
	
	public int updateAddressById (int id, String street, String apt_no, String city, 
			String state, String zip) {
		int updated_id = id;
		if(findAddressById(id) != null)
			updated_id = addr_dao.updateById(id, street, apt_no, city, state, zip);
		return updated_id;
	}
	
/*	public static void main(String[] args)
	{
		Address a1 = new Address("15 Ave", "E501","Seattle", "WA", "98122");
		AddressManager manager = new AddressManager();
		manager.updateAddressById(5, "15 Ave", "E501","Seattle", "WA", "98122");
	}*/

}
