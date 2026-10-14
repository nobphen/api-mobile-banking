package com.phen.mbanking.features.fileupload;

import com.phen.mbanking.features.fileupload.dto.FileUploadResponse;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class FileUploadServiceImp implements FileUploadService {

    @Value("${file-upload.server-path}")
    private String serverPath;

    @Value("${file-upload.base-uri}")
    private String baseUri;

    /**
     * Delete by file name
     *
     * @param fileName {@link fileName}
     */
    @Override
    public void deleteByFileName(String fileName) {
        Path path = Paths.get(serverPath + fileName);

        log.info("File path {}", Files.exists(path));

        /// Check path true
        if (Files.exists(path)) {
            try {
                Files.delete(path);
            } catch (IOException e) {
                throw new ResponseStatusException(
                        HttpStatus.INTERNAL_SERVER_ERROR,
                        "File is field to delete"
                );
            }
        }

        /// path false
        else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "File name is not found"
            );
        }
    }

    /**
     * Upload multiple image
     *
     * @param files {@link List<MultipartFile>}
     * @return {@link List<MultipartFile>}
     */
    @Override
    public List<FileUploadResponse> uploadMultiple(List<MultipartFile> files) {

        List<FileUploadResponse> fileUploadResponses = new ArrayList<>();

        files.forEach(file -> {
            FileUploadResponse fileUploadResponse = upload(file);
            fileUploadResponses.add(fileUploadResponse);
        });


        return fileUploadResponses;
    }

    /**
     * Upload single image
     *
     * @param file {@link MultipartFile}
     * @return {@link FileUploadResponse}
     */
    @Override
    public FileUploadResponse upload(MultipartFile file) {

        String extension = Objects.requireNonNull(file.getContentType()).split("/")[1];
        String newFileName = String.format("%s.%s", UUID.randomUUID().toString(), extension);

        log.info("newFileName {}", newFileName);
        log.info("extension {}", extension);


        Path path = Paths.get(serverPath + newFileName);

        try {
            Files.copy(file.getInputStream(), path);
        } catch (IOException e) {


            log.error(e.getMessage());

            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "File upload field"
            );
        }

        return FileUploadResponse
                .builder()
                .name(newFileName)
                .uri(baseUri + newFileName)
                .contentType(file.getContentType())
                .size(file.getSize())
                .build();

    }
}
