package com.ninova.purchaseorderapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ninova.purchaseorderapp.entity.ApprovalLevel;
import com.ninova.purchaseorderapp.entity.PurchaseOrder;


public interface ApprovalLevelRepository extends JpaRepository<ApprovalLevel, Long> {
	
	List<ApprovalLevel> findByPurchaseOrderId(Long purchaseOrderId);
	 List<ApprovalLevel> findByapproverId(Long id);
	
}