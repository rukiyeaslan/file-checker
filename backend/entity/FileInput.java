package com.example.FileCheck.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "file")
public class FileInput {
    @Id
    private String fileId;
    private String fileName;
    private String fileType;
    @Lob
    private byte[] fileData;

}
