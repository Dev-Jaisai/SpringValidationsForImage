package com.example.controller;

import com.example.service.FileValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
public class FileUploadController {

    private static final String UPLOAD_DIRECTORY = "uploaded_files/";

    @Autowired
    private FileValidator fileValidator;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            // Validate the uploaded file
            fileValidator.validate(file);

            // Save the file to the classpath
            String fileName = file.getOriginalFilename();
            String filePath = UPLOAD_DIRECTORY + fileName;
            File destFile = new File(filePath);
            file.transferTo(destFile);

            return ResponseEntity.ok("File uploaded successfully");
        } catch (IOException | IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
