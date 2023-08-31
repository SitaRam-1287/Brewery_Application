package com.brewery.application.controller;

import com.brewery.application.entity.Document;
import com.brewery.application.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/pdf")
public class DocumentController {

    @Autowired
    private DocumentService DocumentService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadPdf(@RequestParam MultipartFile file) {
        try {
            Document pdfDocument = DocumentService.storeDoc(file);
            return ResponseEntity.ok("PDF uploaded with ID: " + pdfDocument.getId());
        } catch (IOException e) {
            return ResponseEntity.badRequest().body("Failed to upload PDF.");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getPdfById(@PathVariable UUID id) {
        Document pdfDocument = DocumentService.getDocById(id);
        if (pdfDocument != null) {
            return ResponseEntity.ok()
                    .header("Content-Type", pdfDocument.getContentType())
                    .body(pdfDocument.getData());
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping()
    public ResponseEntity<byte[]> getPdfByName(@RequestParam String fileName) {
        Document pdfDocument = DocumentService.getByFileName(fileName);
        if (pdfDocument != null) {
            return ResponseEntity.ok()
                    .header("Content-Type", pdfDocument.getContentType())
                    .body(pdfDocument.getData());
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }
}
