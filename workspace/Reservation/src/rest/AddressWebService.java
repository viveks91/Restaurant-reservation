package rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import models.Address;
import managers.AddressManager;

@Path("/address")
public class AddressWebService {
	
	AddressManager addrmgr = new AddressManager();
	
	@POST
	@Path("/create")
	@Consumes("application/json")
	@Produces("application/json")
	public Address createAddress(Address newAddress) {

		int id = addrmgr.createAddress(newAddress);
		newAddress.setId(id);
		return newAddress;
	}

}
