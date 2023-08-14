package com.wipro.Controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.discovery.EurekaClient;
import com.wipro.Dao.InventoryMain;
import com.wipro.Implementation.InventoryServiceImplementation;
/**
 * @author Ahtesham Ullah Khan
 *
 */


@RestController
@RefreshScope
//@RequestMapping("/inventory")
public class InventoryController {
	
	@Autowired
	InventoryServiceImplementation acimple;
	
	
	
	
	@Value("${MAIN: SERVICE IS WORKING}")
	private String MAIN;
	
	

	@RequestMapping("/")
	public String getMessageDefault() {
		return "Use /message to check Default message .... use /myrole/{username} for role based response"+"\n"
				+ "/Find : To Fetch all product from Inventory" +"\n"
				+ "/AddInventory : To Add new Inventory"+"\n"
				+ "/GetInventoryById/{eid} : To get a perticular Inventory"+"\n"
				+ "/Update/{eid} : To update a inventory"+"\n"
				+ "/Delete/{eid} : To delete a inventory"+"\n"
				+ "*****************Hystrix****************"+"\n"
				+ "/HelloHystrix"+"\n"+"/GetCustomerDetail";
	}

	/*
	 * @RequestMapping("/message") public String getMessage() { return this.message;
	 * }
	 * 
	 * 
	 * 
	 * 
	 * @RequestMapping(value = "/myrole/{username}", method = RequestMethod.GET)
	 * public String myrole(@PathVariable("username") String userName) { return
	 * "Hello you are  a " + userName + " your Role is  " + role;
	 */
	/* } */

	@GetMapping("/HelloHystrix")
	public String hello() {
		return acimple.helloMsg();

	}
	
	@GetMapping("/GetCustomerDetail")
	public String getCustomer() {
		return acimple.helloMsg1();

	}
	
	
	@RequestMapping("/MAIN")
	public String getMAIN() {
		return this.MAIN;
	}
	
	@GetMapping("/message1")
	public String greetString1() {
		return "Hello Greeting Controller from Inventory App ";
	}

	@GetMapping("/greet")
	public String greetString() {
		return "Hello Greeting Controller from Service 1 App ";
	}
	
	@GetMapping("/Find")
	public ResponseEntity<?> getInventory()
	{
		List<InventoryMain> lst = acimple.getInventory();
		
		if(lst.isEmpty())

		   return new ResponseEntity<>( "Not available" ,HttpStatus.OK);
		else
			return new ResponseEntity<>(lst,HttpStatus.OK);
	}
	
//    @PathVariable ("e") InventoryMain e @RequestBody InventoryMain e
	@RequestMapping("/AddInventory/e")
	public ResponseEntity<?> addInventory(@PathVariable ("e") InventoryMain e )
	{
		InventoryMain emp = acimple.addInventory(e);
		
		if(emp==null)
			return new ResponseEntity<>("Not Saved",HttpStatus.INTERNAL_SERVER_ERROR);
		else
			return new ResponseEntity<>(emp,HttpStatus.CREATED);
	}
	
	@RequestMapping("/GetInventoryById/{eid}")
	public ResponseEntity<?> getInventoryById(@PathVariable ("eid") int eid) 
	{
		Optional<InventoryMain> lst = acimple.getInventoryById(eid);
		
		if(lst==null)
			
		//	throw new IdNotFoundException("No Account Exist With given Account Number ");
			return	 new ResponseEntity<>(" No Account Exist With given Account Number",HttpStatus.BAD_REQUEST);
		else
			return new ResponseEntity<>(lst,HttpStatus.OK);
	}
	
	@RequestMapping("/Update/{eid}")
	public ResponseEntity<?> updateInventory(@PathVariable ("eid") int eid, @RequestBody InventoryMain e)
	{
		InventoryMain lst = acimple.updateInventory(e, eid);
		
		if(lst==null)
			
		return	 new ResponseEntity<>(" No Account Exist With given Account Number",HttpStatus.BAD_REQUEST);
		else
		{
			
			return new ResponseEntity<>(lst,HttpStatus.OK);
		}
	}
	
	@RequestMapping("/Delete/{eid}")
	public ResponseEntity<?> deleteInventory(@PathVariable ("eid") int eid)
	{
		String lst = acimple.deleteInventory(eid);
		
		if(lst.isEmpty())
			return new ResponseEntity<>("Not Available",HttpStatus.BAD_REQUEST);
			//throw new IdNotFoundException("Customer Not Found With Given Id");		
		else
		{
			String msg="Deleted";
					
			return new ResponseEntity<>(msg,HttpStatus.OK);
		}
	
	
	
	}
	
	@RequestMapping("/Delete")
	public ResponseEntity<?> deleteAllInventory()
	{
		String lst = acimple.deleteAllInventory();
		
		if(lst.isEmpty())
			return new ResponseEntity<>("Not Available",HttpStatus.BAD_REQUEST);
			//throw new IdNotFoundException("Customer Not Found With Given Id");		
		else
		{
			String msg="Deleted";
					
			return new ResponseEntity<>(msg,HttpStatus.OK);
		}
	
	
	
	}
	
	
	
	

	
}
