package tech.ada.products_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.ada.products_api.dto.ProductDTO;
import tech.ada.products_api.service.ProductService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    static List<ProductDTO> products = new ArrayList<>();

    @PostMapping
    public ResponseEntity<ProductDTO> create(@RequestBody ProductDTO productDTO) {
        productService.criar(productDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(productDTO);
    }



    @GetMapping(value = "/listAll", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ProductDTO> listAll() {
        return products;
    }

    @PutMapping
    public ResponseEntity<ProductDTO> update(@RequestBody ProductDTO productDTO) {
        ProductDTO product = products.stream()
                .filter(p -> p.getSku().equalsIgnoreCase(productDTO.getSku()))
                .findFirst()
                .orElseThrow();

        int index = products.indexOf(product);
        product.setPrice(productDTO.getPrice());

        products.set(index, product);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(product);
    }

    @DeleteMapping("/{sku}")
    public ResponseEntity<Void> delete(@PathVariable("sku") String sku) {
        ProductDTO product = products.stream()
                .filter(p -> p.getSku().equalsIgnoreCase(sku))
                .findFirst()
                .orElseThrow();

        products.remove(product);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
