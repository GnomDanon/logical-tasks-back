package ru.tasks.logical.document.mapper;

import ru.tasks.logical.document.dto.DocumentInfo;
import ru.tasks.logical.document.entity.Document;
import ru.tasks.logical.user.mapper.UserMapper;

public class DocumentMapper {

    public static DocumentInfo mapDocumentToDocumentInfo(Document document) {
        DocumentInfo documentInfo = new DocumentInfo();
        documentInfo.setId(document.getId());
        documentInfo.setName(document.getName());
        documentInfo.setDescription(documentInfo.getDescription());
        documentInfo.setOwner(UserMapper.mapUserToUserInfo(document.getOwner()));
        return documentInfo;
    }
}
