package ru.tasks.logical.document.mapper;

import ru.tasks.logical.document.dto.DocumentInfo;
import ru.tasks.logical.document.entity.Document;

public class DocumentMapper {

    public static DocumentInfo mapDocumentToDocumentInfo(Document document) {
        DocumentInfo documentInfo = new DocumentInfo();
        documentInfo.setId(document.getId());
        documentInfo.setName(document.getName());
        documentInfo.setDescription(document.getDescription());
        documentInfo.setUserId(document.getOwner().getId());
        return documentInfo;
    }
}
