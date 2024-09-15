package com.ninova.purchaseorderapp.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "approval_levels")
public class ApprovalLevel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer level;
    private Long approverId;
    private String status;
    
    @Column(nullable = false)
    private String approverRole;
    
    
	@Column(nullable = false)
    private Integer approvalStepNumber;
    
    @ManyToOne
    @JoinColumn(name = "purchase_order_id")
    private PurchaseOrder purchaseOrder;
    // getters and setters
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public Long getApproverId() {
		return approverId;
	}
	public void setApproverId(Long approverId) {
		this.approverId = approverId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getApproverRole() {
		return approverRole;
	}
	public void setApproverRole(String approverRole) {
		this.approverRole = approverRole;
	}
	public Integer getApprovalStepNumber() {
		return approvalStepNumber;
	}
	public void setApprovalStepNumber(Integer approvalStepNumber) {
		this.approvalStepNumber = approvalStepNumber;
	}
	public PurchaseOrder getPurchaseOrder() {
		return purchaseOrder;
	}
	public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
		this.purchaseOrder = purchaseOrder;
	}
	
	public Object getApprovalWorkflow() {
		// TODO Auto-generated method stub
		return null;
	}
	
    
}