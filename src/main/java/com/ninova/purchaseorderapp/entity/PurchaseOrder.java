package com.ninova.purchaseorderapp.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;



import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

import lombok.Data;


import java.util.List;

@Entity
@Data
@Table(name = "purchase_orders")
public class PurchaseOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    
    private String description;
    
    private String status;
    
    private Date orderDate;
    
    private BigDecimal total;
    
   
    
    @OneToMany(mappedBy = "purchaseOrder")
    private List<Item> items;
    
    private String Title;
    
    private ApprovalWorkflow approvalWorkflow;
    private ApprovalLevel currentApprovalLevel;
    
    @ManyToOne
    @JoinColumn(name = "company_id")
    private String Company ;
    
    private String Address;
    
    private String companyDetails;
    
    public String getCompanyDetails() {
		return companyDetails;
	}
	public void setCompanyDetails(String companyDetails) {
		this.companyDetails = companyDetails;
	}
	public void setCompany(String company) {
		Company = company;
	}
	public void setAddress(String address) {
		Address = address;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public ApprovalWorkflow getApprovalWorkflow() {
		return approvalWorkflow;
	}
	public void setApprovalWorkflow(ApprovalWorkflow approvalWorkflow) {
		this.approvalWorkflow = approvalWorkflow;
	}
	public ApprovalLevel getCurrentApprovalLevel() {
		return currentApprovalLevel;
	}
	public void setCurrentApprovalLevel(ApprovalLevel currentApprovalLevel) {
		this.currentApprovalLevel = currentApprovalLevel;
	}
	
    // getters and setters
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public BigDecimal getTotal() {
		return total;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<Item> getItems() {
		return items;
	}
	public void setItems(List<Item> items) {
		this.items = items;
	}
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public String getCompany() {
		
		return "Ninova Technology";
	}
	public String getAddress() {
		// TODO Auto-generated method stub
		return "Qatar";
	}
	public List<ApprovalLevel> getApprovalLevels() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
    
    
}