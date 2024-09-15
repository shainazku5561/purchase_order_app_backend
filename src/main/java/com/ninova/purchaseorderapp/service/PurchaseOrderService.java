package com.ninova.purchaseorderapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.ninova.purchaseorderapp.entity.ApprovalLevel;
import com.ninova.purchaseorderapp.entity.ApprovalWorkflow;
import com.ninova.purchaseorderapp.entity.Item;
import com.ninova.purchaseorderapp.entity.PurchaseOrder;
import com.ninova.purchaseorderapp.entity.User;
import com.ninova.purchaseorderapp.repository.ApprovalLevelRepository;
import com.ninova.purchaseorderapp.repository.ApprovalWorkflowRepository;
import com.ninova.purchaseorderapp.repository.MaterialRepository;
import com.ninova.purchaseorderapp.repository.PurchaseOrderItemRepository;
import com.ninova.purchaseorderapp.repository.PurchaseOrderRepository;

import jakarta.transaction.Transactional;

@Service
public class PurchaseOrderService {
    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;
    @Autowired
    private ApprovalLevelRepository approvalLevelRepository;
    
    @Autowired
    private ApprovalLevelService approvalLevelService;
    
    @Autowired
    private PurchaseOrderItemRepository itemRepository;
    
    @Autowired
    private ApprovalWorkflowRepository approvalWorkflowRepository;
    
    @Autowired
    private MaterialRepository materialRepository;
    
    //create PO
    @Transactional
    public PurchaseOrder createPo(PurchaseOrder purchaseOrder) {
    	 PurchaseOrder  savedPo = purchaseOrderRepository.save(purchaseOrder);  // Save the PO in the database
    	 approvalLevelService.initiateApprovalProcess(savedPo);  // Trigger the approval process
        return savedPo;  // Return the saved PO
    }
    
   
 // Submit PO method
    @Transactional
    public void submitPurchaseOrder(PurchaseOrder purchaseOrder) {
        // get the approval workflow configuration
        ApprovalWorkflow approvalWorkflow = approvalWorkflowRepository.findDefaultApprovalWorkflow();
        
        // trigger the approval workflow
        triggerApprovalWorkflow(approvalWorkflow, purchaseOrder);
    }
    
    private void triggerApprovalWorkflow(ApprovalWorkflow approvalWorkflow, PurchaseOrder purchaseOrder) {
        // get the first approval step
        ApprovalLevel firstApprovalStep = approvalWorkflow.getApprovalLevels().get(0);
        
        // send a notification to the approver
        sendNotification(firstApprovalStep.getApproverId(), "A new purchase order requires your approval.");
        
        // update the purchase order status
        purchaseOrder.setStatus("Pending Approval");
        PurchaseOrderItemRepository.save(purchaseOrder);
    }
  
    public void updatePurchaseOrder(PurchaseOrder purchaseOrder) {
    	purchaseOrderRepository.save(purchaseOrder);
    }

    public void deletePurchaseOrder(Long id) {
    	purchaseOrderRepository.deleteById(id);
    }

    public void addApprovalLevel(ApprovalLevel approvalLevel) {
        approvalLevelRepository.save(approvalLevel);
    }

    public void addItem(Item item) {
        itemRepository.save(item);
    }
    
    private void sendNotification(Long approverId, String string) {
		// TODO Auto-generated method stub
		
	}

    public PurchaseOrder rejectPO(Long id) {
		// TODO Auto-generated method stub
		return null;
	}



        
       


//    @Transactional(transactionManager = "transactionManager", 
//            isolation = Isolation.REPEATABLE_READ, 
//            rollbackFor = Exception.class)
//public void createPurchaseOrder(PurchaseOrder purchaseOrder){
//        // Perform database operations
//        purchaseOrderItemRepository.save(purchaseOrder);
//      }
//    }
    
   
//    private void triggerApprovalWorkflow(PurchaseOrder po) {
//        // Fetch the company's approval workflow from the database
//        ApprovalWorkflow approvalWorkflow = approvalWorkflowRepository.findById(po.getId());
//
//        // Assign the PO to the users responsible for approving it at each level
//        List<ApprovalLevel> approvalLevels = approvalLevelRepository.findByapproverId(approvalWorkflow.getId());
//        for (ApprovalLevel approvalLevel : approvalLevels) {
//            List<User> approvers = approvalLevel.getApproverRole();
//            for (User approver : approvers) {
//                ApprovalAssignment assignment = new ApprovalAssignment();
//                assignment.setPo(po);
//                assignment.setApprovalLevel(approvalLevel);
//                assignment.setApprover(approver);
//                approvalAssignmentRepository.save(assignment);
//            }
//        }
//    }
    
    
   

    

	

   
//    public List<PurchaseOrder> getPurchaseOrders() {
//        return PurchaseOrderRepository.findAll();
//    }

   

	


	
}