package tech.ada.products_api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class ResponseDTO <T> {
    private String message;
    private LocalDateTime timestamp = LocalDateTime.now();
    private T data;
}
