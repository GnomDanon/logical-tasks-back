package ru.tasks.logical.document.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.tasks.logical.document.dto.DocumentInfo;
import ru.tasks.logical.document.dto.DocumentUploadRequest;
import ru.tasks.logical.document.entity.Document;
import ru.tasks.logical.document.repository.DocumentRepository;
import ru.tasks.logical.user.entity.User;
import ru.tasks.logical.user.exception.UserNotFoundException;
import ru.tasks.logical.user.service.UserService;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DocumentService {

    private final DocumentRepository documentRepository;
    private final UserService userService;

    public DocumentInfo upload(Long userId, String name, String description, MultipartFile file)
            throws IOException, UserNotFoundException {

        byte[] content = file.getBytes();

        User user = userService.getById(userId);

        Document document = new Document().setName(name).setDescription(description).setContent(content).setOwner(user);
        document = documentRepository.save(document);
        return new DocumentInfo(document.getId(), document.getOwner().getId(), document.getName(), document.getDescription());
    }

    public List<DocumentInfo> findByOwnerId(Long ownerId) {
        List<Document> documents = documentRepository.findAllByOwner_Id(ownerId);
        return documents.stream()
                .map(document -> new DocumentInfo(
                        document.getId(),
                        document.getOwner().getId(),
                        document.getName(),
                        document.getDescription()))
                .collect(Collectors.toList());
    }
}
