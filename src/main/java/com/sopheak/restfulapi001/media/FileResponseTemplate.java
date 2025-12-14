package com.sopheak.restfulapi001.media;
import lombok.Builder;

@Builder
public record FileResponseTemplate(
        String fileName,
        long size,
        String type,
        String previewUrl,
        String downloadUrl
){}
