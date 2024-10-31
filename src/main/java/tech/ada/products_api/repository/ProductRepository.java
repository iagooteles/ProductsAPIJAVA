package tech.ada.products_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tech.ada.products_api.dto.ProductDTO;
import tech.ada.products_api.model.Product;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findBySku(String sku);
    List<Product> findByNameContainingIgnoreCase(String name);

    //JPQL
    //@Query("select p from Product p where p.register between :from and :to")
    //NATIVE
    @Query(value = "select p from Product where p.register between :from and :to", nativeQuery = true)
    List<Product> findByRegisterDate(@Param("from") LocalDateTime from,
                                     @Param("to") LocalDateTime to);

    @Query("SELECT p FROM Product p WHERE p.name LIKE %:name% AND p.register BETWEEN :from AND :to")
    List<Product> listAll(@Param("name") String name,
                             @Param("from") LocalDateTime from,
                             @Param("to") LocalDateTime to);

//    @Modifying
//    @Query("delete p from Product where p.sku = :sku") // esses dois não vão ser necessário pois estamos usando notation @Transactional no service
    void deleteBySku(String sku);
}
