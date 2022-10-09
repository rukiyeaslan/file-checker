package com.example.FileCheck.repository;

import java.io.File;

public interface DocumentSanitizer {
    boolean madeSafe(File file);
}
