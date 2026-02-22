package com.legalhelp.backend.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileStorageServiceImpl implements FileStorageService{

    private final Path uploadDir = Paths.get("uploads");

    public FileStorageServiceImpl() {
        try{
            Files.createDirectories(uploadDir);
        }catch (IOException e) {
            throw new RuntimeException("Could not create upload directory");
        }
    }



    @Override
    public String save(MultipartFile file) {
        try {
            String fileName =
                    System.currentTimeMillis() + "_" + file.getOriginalFilename();

            Path target = uploadDir.resolve(fileName);
            Files.copy(file.getInputStream(), target, StandardCopyOption.REPLACE_EXISTING);

            return "uploads/" + fileName;

        } catch (IOException e) {
            throw new RuntimeException("File upload failed");
        }
    }
}
