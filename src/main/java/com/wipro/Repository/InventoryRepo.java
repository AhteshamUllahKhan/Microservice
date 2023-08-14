package com.wipro.Repository;
/**
 * @author Ahtesham Ullah Khan
 *
 */
import org.springframework.data.jpa.repository.JpaRepository;

import com.wipro.Dao.InventoryMain;

public interface InventoryRepo extends JpaRepository<InventoryMain, Integer> {

}
