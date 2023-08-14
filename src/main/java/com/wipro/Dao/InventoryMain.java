package com.wipro.Dao;

/**
 * @author Ahtesham Ullah Khan
 *
 */
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="InventoryDatabase")
public class InventoryMain {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int inventoryId;
	
	
	
	private int productId;
	
	private int quantity;
	public int getInventoryId() {
		return inventoryId;
	}
	public void setInventoryId(int inventoryId) {
		this.inventoryId = inventoryId;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public InventoryMain(int inventoryId, int productId, int quantity) {
		super();
		this.inventoryId = inventoryId;
		this.productId = productId;
		this.quantity = quantity;
	}
	public InventoryMain() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "InventoryMain [inventoryId is : =" + inventoryId + ", productId is : =" + productId + ", quantity available : =" + quantity
				+ "]";
	}
	
	
	
	
	
	
	

}
