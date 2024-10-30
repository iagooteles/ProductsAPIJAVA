package tech.ada.products_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.ada.products_api.dto.ProductDTO;
import tech.ada.products_api.dto.ReservationDTO;
import tech.ada.products_api.dto.ResponseDTO;
import tech.ada.products_api.model.Reservation;
import tech.ada.products_api.service.ProductService;
import tech.ada.products_api.service.ReservationService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @GetMapping(value = "listAll", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ReservationDTO> listAll() {
        return this.reservationService.listAllReservations();
    }

    @GetMapping(value = "/all-with-name-equalTo", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ReservationDTO> listAllWithNameEqualTo(@RequestParam("name") String name) {
        return this.reservationService.buscarPorNome(name);
    }

    @PostMapping
    public ResponseEntity<ReservationDTO> create(@RequestBody ReservationDTO reservationDTO) {
        Reservation savedReservation = reservationService.criar(reservationDTO);

        ReservationDTO responseDTO = new ReservationDTO();
        responseDTO.setId(savedReservation.getId());
        responseDTO.setCustomerName(savedReservation.getCustomerName());
        responseDTO.setDate(savedReservation.getDate());
        responseDTO.setTime(savedReservation.getTime());
        responseDTO.setTableNumber(savedReservation.getTableNumber());

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @PutMapping
    public ResponseEntity<ReservationDTO> update(@RequestBody ReservationDTO reservationDTO) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(this.reservationService.update(reservationDTO));
    }

    @DeleteMapping("/{id}/")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        reservationService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
