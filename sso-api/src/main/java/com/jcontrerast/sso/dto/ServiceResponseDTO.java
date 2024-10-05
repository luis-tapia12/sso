package com.jcontrerast.sso.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ServiceResponseDTO {
    private String message;
    private LocalDateTime timestamp;
    private List<String> details;

    public static ServiceResponseDTO create(String message) {
        ServiceResponseDTO response = new ServiceResponseDTO();
        response.setMessage(message);
        response.setTimestamp(LocalDateTime.now());
        return response;
    }

    public void addDetail(String detail) {
        if (details == null) details = new ArrayList<>();
        details.add(detail);
    }
}
