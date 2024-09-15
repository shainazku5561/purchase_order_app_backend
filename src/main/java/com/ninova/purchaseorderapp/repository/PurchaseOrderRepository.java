package com.ninova.purchaseorderapp.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ninova.purchaseorderapp.entity.ApprovalWorkflow;
import com.ninova.purchaseorderapp.entity.PurchaseOrder;

import jakarta.transaction.Transactional;
@Repository
public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Long> {

//	@Transactional
//	  public void savePurchaseOrders(List<PurchaseOrder> purchaseOrders) {
//	    for (PurchaseOrder purchaseOrder : purchaseOrders) {
//	      entityManager.persist(purchaseOrder);
//	    }
//	  }

	@Transactional
	public void save(ApprovalWorkflow approvalWorkflow);
	}
