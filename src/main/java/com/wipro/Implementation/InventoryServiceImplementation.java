package com.wipro.Implementation;
/**
 * @author Ahtesham Ullah Khan
 *
 */
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.wipro.Dao.InventoryMain;
import com.wipro.Repository.InventoryRepo;
import com.wipro.Service.InventoryService;

@Service
public class InventoryServiceImplementation implements InventoryService  {
	
	@Autowired
	InventoryRepo inre;
	
	
	@Autowired 
	EurekaClient eurekaClient;
	
	@Autowired
	RestTemplateBuilder restTemplateBuilder;

	@Override
	public InventoryMain addInventory(InventoryMain inv) {
		InventoryMain a = inre.save(inv);
		return a;
	}

	@Override
	public List<InventoryMain> getInventory() {
		List<InventoryMain> inventory = inre.findAll();
     	return inventory;
	}

	@Override
	public Optional<InventoryMain> getInventoryById(int eid) {
		Optional<com.wipro.Dao.InventoryMain> inventory = inre.findById(eid);

		if (inventory != null)
			return inventory;
		else
			return null;
	}

	@Override
	public InventoryMain updateInventory(InventoryMain e, int eid) {
		InventoryMain in = inre.findById(eid).get();
		in.setProductId(e.getProductId());
		in.setQuantity(e.getQuantity());
		inre.save(in);
		return in;
		
	}

	@Override
	public String deleteInventory(int eid) {
		Optional<InventoryMain> lst = inre.findById(eid);
		if (lst.isPresent()) {
			inre.delete(lst.get());
			return "Deleted";
		} 
		else 
		{
			return "No data available";
		}
	}
	
	
	
	



	@Override
	public String deleteAllInventory() {
		 inre.deleteAll();
			
			return "Deleted All";
	}

	@HystrixCommand(fallbackMethod = "defaultGreeting")
	public String helloMsg() {
		RestTemplate restTemp = restTemplateBuilder.build();
		InstanceInfo instanceInfo = eurekaClient.getNextServerFromEureka("Shopping-service", false);
		String baseURL = instanceInfo.getHomePageUrl();
		baseURL = baseURL + "/hello";
		return restTemp.getForObject(baseURL, String.class);
	}
	
	public String defaultGreeting() {
		return "				Hello User  :"+"\n" +"				Composite Service is not up and running . Please Check Again :) ";
	}
	
	@HystrixCommand(fallbackMethod = "defaultCustomer")
	public String helloMsg1() {
		RestTemplate restTemp = restTemplateBuilder.build();
		InstanceInfo instanceInfo = eurekaClient.getNextServerFromEureka("Shopping-service", false);
		String baseURL = instanceInfo.getHomePageUrl();
		baseURL = baseURL + "/GetCustomer";
		return restTemp.getForObject(baseURL, String.class);
	}
	
	public String defaultCustomer() {
		return "				Hello User  :"+"\n" +"				Customer Service is not up and running . Please Check Again :) ";
	}
	
	
	

}
