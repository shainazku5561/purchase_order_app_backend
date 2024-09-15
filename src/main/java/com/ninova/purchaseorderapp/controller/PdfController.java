package com.ninova.purchaseorderapp.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ninova.purchaseorderapp.entity.PurchaseOrder;
import com.ninova.purchaseorderapp.service.PdfGenerator;

@RestController
@RequestMapping("/api/pdfs")
public class PdfController {
    @Autowired
    private PdfGenerator pdfGenerator;

    @PostMapping("/generate-po")
    public ResponseEntity<byte[]> generatePurchaseOrderPdf(@RequestBody PurchaseOrder purchaseOrder) {
        byte[] pdfContent = pdfGenerator.generatePurchaseOrderPdf(purchaseOrder);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(org.springframework.http.MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("filename", "purchase_order.pdf");

        return new ResponseEntity<>(pdfContent, headers, HttpStatus.OK);
    }
}