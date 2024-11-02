package tech.ada.products_api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.Parameter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@ToString
public class ProductDTO {

    @NotBlank(message = "O sku é obrigatório.")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Somente letras.")
    private String sku;
    @NotBlank(message = "O nome é obrigatório.")
    private String name;
    private String description;
    @JsonProperty("price")
    private BigDecimal price;
    private LocalDateTime register;
    @JsonProperty("exchange")
    private BigDecimal exchange;
    private Double weight;
}
