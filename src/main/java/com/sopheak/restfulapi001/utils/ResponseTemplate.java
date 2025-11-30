package com.sopheak.restfulapi001.utils;
import lombok.Builder;
import java.util.Date;

@Builder
public record ResponseTemplate<T>(
        String staus,
        Date date,
        String message,
        // generic type
        T data
){}
