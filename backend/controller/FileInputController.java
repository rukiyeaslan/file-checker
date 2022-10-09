package com.example.FileCheck.controller;

import com.example.FileCheck.entity.FileInput;
import com.example.FileCheck.service.FileInputService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:3000")
public class FileInputController {
    @Autowired
    private FileInputService fileInputService;

    @PostMapping("/file")
    public void uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        fileInputService.store(file);
    }

}
