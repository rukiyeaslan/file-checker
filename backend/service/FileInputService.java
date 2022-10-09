package com.example.FileCheck.service;

import com.example.FileCheck.documentTypes.*;
import com.example.FileCheck.entity.FileInput;
import com.example.FileCheck.repository.FileInputRepository;
import com.itextpdf.text.exceptions.InvalidPdfException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static com.example.FileCheck.shared.Constants.*;
import static com.example.FileCheck.shared.Utils.isMimeTypeImage;

@Service
public class FileInputService {

    @Autowired
    private FileInputRepository fileInputRepository;

    /**
     * Gets the multirpart file and according to its content type, checks if it containt malicious content.
     * @param file
     * @throws IOException
     */
    public void store(MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        FileInput fileDb = new FileInput(UUID.randomUUID().toString(), fileName, file.getContentType(),file.getBytes());

        File sendFile = multipartToFile(file, fileName);

        //System.out.println( "content type: "+ file.getContentType() + " file name: " + file.getName());

        if(file.getContentType().equals("application/pdf")){

            PdfDetector pdf = new PdfDetector();
            if(pdf.isSafe(sendFile)){
                fileInputRepository.save(fileDb);
                System.out.println("pdf success");
            }
            else{
                throw new IOException();

            }

        }
        else if(ALLOWED_EXCEL_FORMAT.contains(file.getContentType())){

            ExcelDetector excel = new ExcelDetector();
            if(excel.isSafe(sendFile)){
                fileInputRepository.save(fileDb);
                System.out.println("excel success");
            }
            else{
                throw new IOException();
            }
        }
        else if(ALLOWED_WORD_FORMAT.contains(file.getContentType())){

            WordDetector word = new WordDetector();
            if(word.isSafe(sendFile)){
                fileInputRepository.save(fileDb);
                System.out.println("word success");
            }
            else{
                throw new IOException();
            }
        }
        else if(isMimeTypeImage(file.getContentType())){

            ImageSanitizer image = new ImageSanitizer();
            if(image.madeSafe(sendFile)){
                fileInputRepository.save(fileDb);
                System.out.println("image success");
            }
            else{
                System.out.println("malicious image");
            }
        }
        else{
            System.out.println("fail");
        }
    }

    /**
     * Converts the input multipart file to a java.File which is required for detectors and sanitizers.
     * @param multipart
     * @param fileName
     * @return
     * @throws IllegalStateException
     * @throws IOException
     */
    public static File multipartToFile(MultipartFile multipart, String fileName) throws IllegalStateException, IOException {
        File convFile = new File(System.getProperty("java.io.tmpdir")+"/"+fileName);
        multipart.transferTo(convFile);
        return convFile;
    }

}
