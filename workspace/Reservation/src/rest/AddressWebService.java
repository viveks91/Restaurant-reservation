package rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import models.Address;
import managers.AddressManager;

@Path("/address")
public class AddressWebService {
	
	AddressManager addrmgr = new AddressManager();
	
	@POST
	@Path("/create")
	@Consumes("application/json")
	public int createAddress(Address newAddress) {
//		System.out.print(newAddress.getCity());
		
		return addrmgr.createAddress(newAddress);
	}

}
