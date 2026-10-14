package com.phen.mbanking.features.fileupload;

import com.phen.mbanking.features.fileupload.dto.FileUploadResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileUploadService {

    /**
     * Delete by file name
     *
     * @param fileName {@link fileName}
     */
    void deleteByFileName(String fileName);


    /**
     * Upload multiple
     *
     * @param files {@link List<MultipartFile>}
     * @return {@link List<MultipartFile>}
     */
    List<FileUploadResponse> uploadMultiple(List<MultipartFile> files);


    /**
     * Upload single image
     *
     * @param file {@link MultipartFile}
     * @return {@link  FileUploadResponse}
     */
    FileUploadResponse upload(MultipartFile file);
}
