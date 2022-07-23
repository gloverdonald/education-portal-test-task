package com.mst.handler.dto;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ValidationExceptionResponse {

    private List<ValidationErrorResponse> errors;
}