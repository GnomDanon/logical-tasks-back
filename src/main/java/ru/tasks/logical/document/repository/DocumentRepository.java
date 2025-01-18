package ru.tasks.logical.document.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.tasks.logical.document.entity.Document;

import java.util.List;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {
	List<Document> findAllByOwner_Id(Long ownerId);
}
