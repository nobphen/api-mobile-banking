package com.phen.mbanking.features.fileupload;

import com.phen.mbanking.features.fileupload.dto.FileUploadResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/upload")

@RequiredArgsConstructor
public class FileUploadController {

    private final FileUploadService fileUploadService;


    /**
     * Delete by file name
     * @param fileName {@link fileName}
     */
    @DeleteMapping("/{fileName}")
    void deleteByFileName(@PathVariable String fileName) {
        fileUploadService.deleteByFileName(fileName);
    }

    /**
     * Upload multiple image
     *
     * @param files {@link List<MultipartFile>}
     * @return {@link List<FileUploadResponse>}
     */
    @PostMapping("/multiple")
    List<FileUploadResponse> uploadMultiple(@RequestPart List<MultipartFile> files) {
        return fileUploadService.uploadMultiple(files);
    }


    /**
     * Upload single image
     *
     * @param file {@link MultipartFile}
     * @return {@link FileUploadResponse}
     */
    @PostMapping()
    FileUploadResponse upload(@RequestPart MultipartFile file) {
        return fileUploadService.upload(file);
    }
}
