package com.ninova.purchaseorderapp.repository;

import com.ninova.purchaseorderapp.entity.Item;
import com.ninova.purchaseorderapp.entity.PurchaseOrder;

public interface PurchaseOrderItemRepository {

	static void save(PurchaseOrder purchaseOrder) {
		// TODO Auto-generated method stub
		
	}

	void save(Item item);

}
