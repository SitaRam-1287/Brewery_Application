package com.brewery.application.service.Impl;

import com.brewery.application.entity.Document;
import com.brewery.application.exception.ElementNotFoundException;
import com.brewery.application.repository.DocumentRepository;
import com.brewery.application.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
public class DocumentServiceImpl implements DocumentService {

    @Autowired
    private DocumentRepository documentRepository;
    @Override
    public Document storeDoc(MultipartFile file) throws IOException {
        Document pdfDocument = new Document();
        pdfDocument.setFileName(file.getOriginalFilename());
        pdfDocument.setContentType(file.getContentType());
        pdfDocument.setData(file.getBytes());
        return documentRepository.save(pdfDocument);
    }
    @Override
    public Document getDocById(UUID id) {
        return documentRepository.findById(id).orElseThrow(()->new ElementNotFoundException("file with given id is not found"));
    }

    @Override
    public Document getByFileName(String fileName) {
        return documentRepository.findByFileName(fileName);
    }
}
