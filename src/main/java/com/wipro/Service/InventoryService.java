package com.wipro.Service;
/**
 * @author Ahtesham Ullah Khan
 *
 */
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.wipro.Dao.InventoryMain;

@Service
public interface InventoryService {
	
	public InventoryMain addInventory (InventoryMain inv );
	public List<InventoryMain> getInventory();
	public Optional <InventoryMain> getInventoryById (int eid);
	public InventoryMain updateInventory (InventoryMain e , int eid);
	public String deleteInventory (int eid );
	public String deleteAllInventory();
	
	

}
