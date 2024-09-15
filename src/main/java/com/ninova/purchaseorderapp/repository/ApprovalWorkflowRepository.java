package com.ninova.purchaseorderapp.repository;

import com.ninova.purchaseorderapp.entity.ApprovalHistory;
import com.ninova.purchaseorderapp.entity.ApprovalLevel;
import com.ninova.purchaseorderapp.entity.ApprovalWorkflow;

public interface ApprovalWorkflowRepository {

	public void save(ApprovalWorkflow approvalWorkflow);
	
	
	ApprovalWorkflow findDefaultApprovalWorkflow();

	

	ApprovalLevel getNextApprovalLevel(ApprovalLevel currentApprovalLevel);

	public void save(ApprovalHistory approvalHistory);


	

}
