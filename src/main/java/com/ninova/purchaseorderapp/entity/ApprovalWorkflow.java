package com.ninova.purchaseorderapp.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class ApprovalWorkflow {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	
	@OneToMany(mappedBy = "approvalWorkflow", cascade = CascadeType.ALL, orphanRemoval = true)
    private static List<ApprovalLevel> approvalLevels;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public static List<ApprovalLevel> getApprovalLevels() {
		return approvalLevels;
	}
	public void setApprovalLevels(List<ApprovalLevel> approvalLevels) {
		this.approvalLevels = approvalLevels;
	}
	public void addApprovalStep(ApprovalLevel level2Approval) {
		// TODO Auto-generated method stub
		
	}

	// getters and setters
	
	
}
