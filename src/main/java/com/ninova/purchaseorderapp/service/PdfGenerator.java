package com.ninova.purchaseorderapp.service;


import com.ninova.purchaseorderapp.controller.AdminController;
import com.ninova.purchaseorderapp.entity.ApprovalLevel;
import com.ninova.purchaseorderapp.entity.ApprovalWorkflow;
import com.ninova.purchaseorderapp.entity.Item;


import com.ninova.purchaseorderapp.entity.PurchaseOrder;
import com.ninova.purchaseorderapp.repository.PurchaseOrderRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.Image;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.exceptions.PdfException;

import com.itextpdf.kernel.colors.ColorConstants;

import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;

@Service
public class PdfGenerator {
    @Autowired
    private ApprovalLevelService approvalService;
    
    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;
    
    @Autowired
    private AdminController adminController;
    
    public byte[] generatePurchaseOrderPdf(PurchaseOrder purchaseOrder) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(byteArrayOutputStream);
        PdfDocument pdfDoc = new PdfDocument(writer);
        Document document = new Document(pdfDoc);
        
        // Adding header and footer with company details
        addHeader(document, purchaseOrder.getCompanyDetails());
        
        // Adding table listing items, quantities, unit prices, and total amounts
        addItemsTable(document, purchaseOrder.getItems());
        
        // Adding section for signatures with dynamic approval steps
        addSignaturesSection(document, purchaseOrder.getApprovalLevels());
        
        document.close();
        
        // Return the generated PDF as a byte array
        return byteArrayOutputStream.toByteArray();
    }
    
    private void addHeader(Document document, String companyDetails) {
        
    	// Adding company logo and details to the header
    	Paragraph header = new Paragraph("Purchase Order")
                .setTextAlignment(TextAlignment.CENTER)
                .setFontSize(20)
                .setBold();
       
    	Paragraph companyInfo = new Paragraph(companyDetails)
                .setTextAlignment(TextAlignment.RIGHT)
                .setFontSize(10);
    	
    	document.add(header);
        document.add(companyInfo);
    }
        
     // Create a PdfPTable for the footer
    private void addFooter(Document document, String companyDetails) {
        Paragraph footer = new Paragraph("Company Details: " + companyDetails)
                .setTextAlignment(TextAlignment.CENTER)
                .setFontSize(10);

        document.add(footer);
    }
    
    private void addItemsTable(Document document, List<Item> items) {
    	  Table table = new Table(UnitValue.createPercentArray(new float[]{2, 5, 2, 2}))
                  .useAllAvailableWidth();
        table.addHeaderCell(new Cell().add(new Paragraph("Item")));
        table.addHeaderCell(new Cell().add(new Paragraph("Description")));
        table.addHeaderCell(new Cell().add(new Paragraph("Quantity")));
        table.addHeaderCell(new Cell().add(new Paragraph("Unit Price")));
        table.addHeaderCell(new Cell().add(new Paragraph("Total Amount")));

        for (Item item : items) {
            table.addCell(new Cell().add(new Paragraph(item.getItemName())));
            table.addCell(new Cell().add(new Paragraph(item.getDescription())));
            table.addCell(new Cell().add(new Paragraph(item.getQuantity().toString())));
            table.addCell(new Cell().add(new Paragraph(item.getUnitPrice().toString())));
            table.addCell(new Cell().add(new Paragraph(item.getTotalAmount().toString())));
        }

        document.add(table);
    }

    private void addSignaturesSection(Document document, List<ApprovalLevel> approvalLevels) {
        document.add(new Paragraph("Approval Steps")
                .setTextAlignment(TextAlignment.LEFT)
                .setFontSize(14)
                .setBold());

        for (ApprovalLevel step : approvalLevels) {
            document.add(new Paragraph("Step " + step.getApprovalStepNumber() + ": " + step.getApproverRole() +
                    " - " + (step.getApproverId() != null ? "Approved" : "Pending")));
        }
        
        Table table = new Table(new float[] { 1, 2, 1 });
        table.setWidth(100).setMarginBottom(10);
        
        int stepNumber = 1;
        for (ApprovalLevel level : ApprovalWorkflow.getApprovalLevels()) {
            ApprovalLevel approvalStep = new ApprovalLevel();
            approvalStep.setApprovalStepNumber(stepNumber);
            approvalStep.setStatus(level.getStatus());
            approvalStep.setApproverRole(level.getApproverRole());

////            table.addCell(new Cell().add(String.valueOf(approvalStep.getApprovalStepNumber())));
//            table.addCell(new Cell().add(approvalStep.getStatus()));
//            table.addCell(new Cell().add(approvalStep.getApprovedBy()));

            stepNumber++;
        }

        // Add the table to the document
        document.add(table);

        // Close the document
        document.close();
    }

    }