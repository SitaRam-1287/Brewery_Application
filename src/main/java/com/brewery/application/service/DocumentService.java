package com.brewery.application.service;

import com.brewery.application.entity.Document;
import org.springframework.web.multipart.MultipartFile;

import javax.print.Doc;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

public interface DocumentService {

    public Document storeDoc(MultipartFile file) throws IOException;

    public Document getDocById(UUID id);

    public Document getByFileName(String fileName);
}
