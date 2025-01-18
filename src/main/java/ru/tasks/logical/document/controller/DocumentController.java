package ru.tasks.logical.document.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ru.tasks.logical.document.dto.DocumentInfo;
import ru.tasks.logical.document.dto.DocumentUploadRequest;
import ru.tasks.logical.document.service.DocumentService;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/document")
@RequiredArgsConstructor
@Tag(name = "Документ")
public class DocumentController {

    private final DocumentService documentService;

    @Operation(summary = "Загрузка файла")
    @PostMapping("/upload")
    public ResponseEntity<DocumentInfo> upload(@RequestParam("userId") UUID userId, @RequestParam("name") String name,
                                               @RequestParam("description") String description,
                                               @RequestParam("file") MultipartFile file) {
        try {
            DocumentInfo savedDocument = documentService.upload(userId, name, description, file);
            return ResponseEntity.ok(savedDocument);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @Operation(summary = "Получение документов, принадлежащих пользователю")
    @GetMapping("/owner/{id}")
    public ResponseEntity<List<DocumentInfo>> getDocuments(@PathVariable UUID id) {
        return ResponseEntity.ok(documentService.findByOwnerId(id));
    }
}
