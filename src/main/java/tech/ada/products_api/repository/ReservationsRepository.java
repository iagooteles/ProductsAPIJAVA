package tech.ada.products_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.ada.products_api.model.Product;
import tech.ada.products_api.model.Reservation;

import java.util.List;
import java.util.Optional;

public interface ReservationsRepository extends JpaRepository<Reservation, Long> {

    Optional<Reservation> findById(Long id);
    List<Reservation> findByCustomerNameContainingIgnoreCase(String customerName);

}
