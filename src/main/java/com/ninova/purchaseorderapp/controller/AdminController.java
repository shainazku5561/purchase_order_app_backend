package com.ninova.purchaseorderapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ninova.purchaseorderapp.entity.ApprovalLevel;
import com.ninova.purchaseorderapp.entity.ApprovalWorkflow;
import com.ninova.purchaseorderapp.repository.ApprovalWorkflowRepository;
import com.ninova.purchaseorderapp.service.ApprovalLevelService;

@Controller
@RequestMapping("/admin")
public class AdminController {
    
    @Autowired
    private ApprovalWorkflowRepository approvalWorkflowRepository;
    
    @Autowired
    private ApprovalLevelService approvalLevelService;

    // Endpoint to create an approval workflow for a PO
    @PostMapping("/create-workflow/{purchaseOrderId}")
    public ResponseEntity<String> createApprovalWorkflow(
            @PathVariable Long purchaseOrderId,
            @RequestBody List<ApprovalLevel> approvalLevels) {
        
        approvalLevelService.createApprovalWorkflow(purchaseOrderId, approvalLevels);
        return ResponseEntity.ok("Approval workflow created successfully for PO: " + purchaseOrderId);
    }
    
    @PostMapping("/configure-approval-workflow")
    public String configureApprovalWorkflow() {
        // assume we have an ApprovalWorkflow entity to store the approval workflow configuration
        ApprovalWorkflow approvalWorkflow = new ApprovalWorkflow();

        // Level 1 Approval: Assign the first approval step to the Team Lead
        ApprovalLevel level1Approval = new ApprovalLevel();
        level1Approval.setApproverRole("Team Lead");
        level1Approval.setApprovalStepNumber(1);
        approvalWorkflow.addApprovalStep(level1Approval);

        // Level 2 Approval: Assign the second approval step to the Department Manager
        ApprovalLevel level2Approval = new ApprovalLevel();
        level2Approval.setApproverRole("Department Manager");
        level2Approval.setApprovalStepNumber(2);
        approvalWorkflow.addApprovalStep(level2Approval);
        
     // Level 3 Approval: Assign the third approval step to the Finance Manager
        ApprovalLevel level3Approval = new ApprovalLevel();
        level3Approval.setApproverRole("Finance Manager");
        level3Approval.setApprovalStepNumber(3);
        approvalWorkflow.addApprovalStep(level3Approval);


        // save the approval workflow configuration
        approvalWorkflowRepository.save(approvalWorkflow);
        
        return "redirect:/admin";
    }
}