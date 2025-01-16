package ru.tasks.logical.document.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ru.tasks.logical.document.dto.DocumentInfo;
import ru.tasks.logical.document.dto.DocumentUploadRequest;

@RestController
@RequestMapping("/document")
@RequiredArgsConstructor
@Tag(name = "Документ")
public class DocumentController {

    @Operation(summary = "Загрузка файла")
    @PostMapping("/upload")
    public DocumentInfo upload(@RequestPart("meta") @Valid DocumentUploadRequest request, @RequestParam("file") MultipartFile file) {
        return new DocumentInfo();
    }
}
