package com.sopheak.restfulapi001.utils;
import lombok.Builder;
import java.util.Date;

@Builder
public record AIPErrorResponse(
        String status,
        Date timeStamp,
        String errorMessage
){}