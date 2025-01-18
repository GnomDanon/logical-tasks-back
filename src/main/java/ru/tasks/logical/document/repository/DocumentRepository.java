package ru.tasks.logical.document.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.tasks.logical.document.entity.Document;

import java.util.List;
import java.util.UUID;

@Repository
public interface DocumentRepository extends JpaRepository<Document, UUID> {
	List<Document> findAllByOwner_Id(UUID ownerId);
}
