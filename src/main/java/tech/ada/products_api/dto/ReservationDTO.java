package tech.ada.products_api.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@ToString
public class ReservationDTO {
    private Long id;
    private String customerName;
    private LocalDate date;
    private LocalTime time;
    private int tableNumber;
}
