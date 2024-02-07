package com.example.service;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Component
public class FileValidator {

    private static final List<String> SUPPORTED_IMAGE_FORMATS = Arrays.asList("image/jpeg", "image/png");
    private static final long MAX_FILE_SIZE = 5 * 1024 * 1024; // 5 MB

    public void validate(MultipartFile file) throws IOException {
        // Check if the file is empty
        if (file.isEmpty()) {
            throw new IllegalArgumentException("File is empty");
        }

        // Check if the file size exceeds the maximum allowed size
        if (file.getSize() > MAX_FILE_SIZE) {
            throw new IllegalArgumentException("File size exceeds the maximum allowed size");
        }

        // Check if the file format is supported (JPEG or PNG)
        if (!SUPPORTED_IMAGE_FORMATS.contains(file.getContentType())) {
            throw new IllegalArgumentException("Unsupported image format. Only JPEG or PNG images are allowed");
        }
    }
}
