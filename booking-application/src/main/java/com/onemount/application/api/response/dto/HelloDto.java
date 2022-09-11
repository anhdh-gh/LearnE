package com.onemount.application.api.response.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Hello World Response DTO
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HelloDto {
    private String id;
    private String message;
}
