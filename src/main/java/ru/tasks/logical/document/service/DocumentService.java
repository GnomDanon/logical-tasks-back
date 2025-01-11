package ru.tasks.logical.document.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.tasks.logical.document.repository.DocumentRepository;

@Service
@RequiredArgsConstructor
public class DocumentService {

    private final DocumentRepository documentRepository;
}
