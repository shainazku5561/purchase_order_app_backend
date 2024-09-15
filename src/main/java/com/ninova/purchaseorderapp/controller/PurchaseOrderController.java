package com.ninova.purchaseorderapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.ninova.purchaseorderapp.entity.ApprovalLevel;
import com.ninova.purchaseorderapp.entity.Item;
import com.ninova.purchaseorderapp.entity.PurchaseOrder;

import com.ninova.purchaseorderapp.repository.PurchaseOrderRepository;

import com.ninova.purchaseorderapp.service.PurchaseOrderService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/purchase-orders")
public class PurchaseOrderController {
    @Autowired
    private PurchaseOrderService service;

    @PostMapping("/createPo")
    public PurchaseOrder createPO(@RequestBody PurchaseOrder po) {
        return service.createPo(po);
    }
    
    @PostMapping("/submit")
    public ResponseEntity<String> submitPo(@RequestBody PurchaseOrder po) {
    	service.submitPurchaseOrder(po);
        return ResponseEntity.ok("PurchaseOrder submitted successfully for approval");
    }

    @PostMapping("/{id}/approval-levels")
    public void addApprovalLevel(@PathVariable Long id, @RequestBody ApprovalLevel approvalLevel) {
        service.addApprovalLevel(approvalLevel);
    }
    
    @PostMapping("/po/{id}/reject")
    public PurchaseOrder rejectPO(@PathVariable Long id) {
        return service.rejectPO(id);
    }

    @PostMapping("/{id}/items")
    public void addItem(@PathVariable Long id, @RequestBody Item item) {
        service.addItem(item);
    }


    @PutMapping("/{id}")
    public void updatePurchaseOrder(@PathVariable Long id, @RequestBody PurchaseOrder purchaseOrder) {
        service.updatePurchaseOrder(purchaseOrder);
    }

    @DeleteMapping("/{id}")
    public void deletePurchaseOrder(@PathVariable Long id) {
        service.deletePurchaseOrder(id);
    }
}