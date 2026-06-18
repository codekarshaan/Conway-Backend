package com.conway.backend.common.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T> {

    private boolean success;

    private String message;

    private T data;

    private LocalDateTime timestamp;

    private Object errors;

}