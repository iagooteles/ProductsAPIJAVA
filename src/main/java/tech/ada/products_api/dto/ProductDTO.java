package tech.ada.products_api.dto;

import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;

@Data
@ToString
public class ProductDTO {
    private String sku;
    private String name;
    private String description;
    private BigDecimal price;
    private Double weight;
}
