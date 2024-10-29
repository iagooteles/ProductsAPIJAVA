package tech.ada.products_api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.ada.products_api.dto.ReservationDTO;
import tech.ada.products_api.model.Reservation;
import tech.ada.products_api.repository.ReservationsRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReservationService {

    @Autowired
    private ReservationsRepository reservationsRepository;

    public List<ReservationDTO> listAllReservations() {
        return this.reservationsRepository
                .findAll()
                .stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }

    public List<ReservationDTO> buscarPorNome(String name) {
        List<Reservation> reservations = reservationsRepository.findByCustomerNameContainingIgnoreCase(name);
        return reservations.stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }

    public Reservation criar(ReservationDTO reservationDTO) {
        Reservation reservation = new Reservation();

        reservation.setCustomerName(reservationDTO.getCustomerName());
        reservation.setDate(reservationDTO.getDate());
        reservation.setTime(reservationDTO.getTime());
        reservation.setTableNumber(reservationDTO.getTableNumber());

        return reservationsRepository.save(reservation);
    }

    public ReservationDTO update(ReservationDTO reservationDTO) {
        Optional<Reservation> optionalReservation = this.getById(reservationDTO.getId());

        if (optionalReservation.isPresent()) {
            Reservation reservation = optionalReservation.get();
            reservation.setCustomerName(reservationDTO.getCustomerName());
            reservation.setDate(reservationDTO.getDate());
            reservation.setTime(reservationDTO.getTime());
            reservation.setTableNumber(reservationDTO.getTableNumber());
            this.reservationsRepository.save(reservation);
            return reservationDTO;
        }
        return null;
    }


//    @GetMapping("/date/{date}/time/{time}")
//    public ResponseEntity<List<ReservationDTO>> getReservationsByDateAndTime(
//            @PathVariable("date") LocalDate date,
//            @PathVariable("time") LocalTime time) {
//
//        List<ReservationDTO> reservations = reservationService.findByDateAndTime(date, time);
//
//        if (reservations.isEmpty()) {
//            return ResponseEntity.noContent().build(); // Retorna 204 se não houver reservas
//        }
//
//        return ResponseEntity.ok(reservations); // Retorna 200 com a lista de reservas
//    }


    private Optional<Reservation> getById(Long id) {
        return this.reservationsRepository.findById(id);
    }

    private ReservationDTO convert (Reservation reservation) {
        ReservationDTO reservationDTO = new ReservationDTO();
        reservationDTO.setId(reservation.getId());
        reservationDTO.setCustomerName(reservation.getCustomerName());
        reservationDTO.setDate(reservation.getDate());
        reservationDTO.setTime(reservation.getTime());
        reservationDTO.setTableNumber(reservation.getTableNumber());

        return reservationDTO;
    }

}
