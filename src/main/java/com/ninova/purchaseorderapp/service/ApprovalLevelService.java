package com.ninova.purchaseorderapp.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import com.ninova.purchaseorderapp.entity.ApprovalHistory;
import com.ninova.purchaseorderapp.entity.ApprovalLevel;
import com.ninova.purchaseorderapp.entity.ApprovalWorkflow;
import com.ninova.purchaseorderapp.entity.PurchaseOrder;
import com.ninova.purchaseorderapp.repository.ApprovalHistoryRepository;
import com.ninova.purchaseorderapp.repository.ApprovalLevelRepository;
import com.ninova.purchaseorderapp.repository.ApprovalWorkflowRepository;
import com.ninova.purchaseorderapp.repository.PurchaseOrderRepository;
import com.ninova.purchaseorderapp.repository.UserRepository;


@Service
public class ApprovalLevelService {
    @Autowired
    private ApprovalLevelRepository approvalLevelRepository;
    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;
    
    @Autowired
    private ApprovalWorkflowRepository approvalWorkflowRepository;
    
    @Autowired
    private ApprovalHistoryRepository approvalHistoryRepository;
    
    @Autowired
    private PdfGenerator pdfGenerator;
 
	public void createApprovalWorkflow(Long purchaseOrderId, List<ApprovalLevel> approvalLevels) {
        ApprovalWorkflow approvalWorkflow = new ApprovalWorkflow();
        approvalWorkflow.setApprovalLevels(approvalLevels);
        purchaseOrderRepository.save(approvalWorkflow);
    }
	
	public void submitForApproval(Long purchaseOrderId) {
        PurchaseOrder purchaseOrder = purchaseOrderRepository.findById(purchaseOrderId).orElseThrow();
        ApprovalLevel currentApprovalLevel = purchaseOrder.getCurrentApprovalLevel();
        if (currentApprovalLevel != null) {
            // send notification to approver
            sendNotification(currentApprovalLevel.getApproverId());
        } else {
            // no more approval steps, finalize the order
            finalizeOrder(purchaseOrder);
        }
    }

    
   
	
	public void initiateApprovalProcess(PurchaseOrder po) {
        // Get the default approval workflow configuration
        ApprovalWorkflow approvalWorkflow = approvalWorkflowRepository.findDefaultApprovalWorkflow();
        
        // Start the approval process with the first approval step
        ApprovalLevel currentApprovalLevel = approvalWorkflow.getApprovalLevels().get(0);
        sendNotification(currentApprovalLevel, po);
    }
    

	
	public void approve(PurchaseOrder po, ApprovalLevel approverRole) {

        // Check if the approver is at the correct approval level
        if (canApprove(approverRole, po)) {
            po.setStatus("Approved by " + approverRole);

            // If all levels are approved, mark the PO as fully approved
            if (isFinalApproval(approverRole, po)) {
                po.setStatus("Fully Approved");
            }
		
        purchaseOrderRepository.save(po);
        
        // Create an approval history entry
        ApprovalHistory approvalHistory = new ApprovalHistory();
        approvalHistory.setPurchaseOrder(po);
        approvalHistory.setApprovalLevel(approverRole);;
        approvalHistory.setApproved(true);
        approvalHistory.setApprovedDate(new Date());
        approvalHistoryRepository.save(approvalHistory);
        
        // Move to the next approval step
        ApprovalLevel nextApprovalLevel = approvalWorkflowRepository.getNextApprovalLevel(approverRole);
        if (nextApprovalLevel != null) {
            sendNotification(nextApprovalLevel, po);
        } else {
            // Finalize the PO if approved
            finalizeOrder(po);
        }
        
        }
	}

    
    private boolean isFinalApproval(ApprovalLevel approverRole, PurchaseOrder po) {
		// TODO Auto-generated method stub
		return true;
	}

	private boolean canApprove(ApprovalLevel approverRole, PurchaseOrder po) {
		//Logic to check if this is the final approval level
        // E.g., Finance Manager may be the final approver
        return "Finance Manager".equals(approverRole);
	}

	private void finalizeOrder(PurchaseOrder purchaseOrder) {
	    // update the purchase order status to "approved"
	    purchaseOrder.setStatus("approved");
	    // save the updated purchase order
	    purchaseOrderRepository.save(purchaseOrder);
	    // send a notification to the user who created the purchase order
	    sendNotification(purchaseOrder);
	}

	private void sendNotification(Long approverId) {
    	System.out.println("Notification sent to Approver ");
		
	}
    
    private void sendNotification(ApprovalLevel currentApprovalLevel, PurchaseOrder po) {
    	System.out.println("Notification sent to Approver ID: " + currentApprovalLevel.getApproverId() + " for PO ID: " + po.getId());
		
	}
    
    private void sendNotification(PurchaseOrder purchaseOrder) {
		System.out.println("Notification sent to User: " + purchaseOrder.getUser());
		
	}

    



}