package com.brewery.application.repository;

import com.brewery.application.entity.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DocumentRepository extends JpaRepository<Document, UUID> {

    public Document findByFileName(String fileName);
}
