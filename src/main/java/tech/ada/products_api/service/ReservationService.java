package tech.ada.products_api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.ada.products_api.dto.ReservationDTO;
import tech.ada.products_api.model.Product;
import tech.ada.products_api.model.Reservation;
import tech.ada.products_api.repository.ReservationsRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReservationService {

    @Autowired
    private ReservationsRepository reservationsRepository;
    @Autowired
    private NameService nameService;

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

        if (reservationDTO.getCustomerName().equalsIgnoreCase("")) {
            reservation.setCustomerName(nameService.getRandomName());
        } else {
            reservation.setCustomerName(reservationDTO.getCustomerName());
        }

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

    public void delete(Long id) {
        Optional<Reservation> reservationToBeDeleted = Optional.of(this.reservationsRepository.getById(id));
        reservationToBeDeleted.ifPresent(reservation -> this.reservationsRepository.delete(reservation));
    }

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
