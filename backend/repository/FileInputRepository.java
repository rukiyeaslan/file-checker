package com.example.FileCheck.repository;

import com.example.FileCheck.entity.FileInput;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileInputRepository extends JpaRepository<FileInput, Long> {
}
