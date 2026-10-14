package com.phen.mbanking.features.fileupload.dto;


import lombok.Builder;

@Builder
public record FileUploadResponse(

        String name,

        String uri,

        String contentType,

        long size
) {
}
