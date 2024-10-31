package tech.ada.products_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.ada.products_api.dto.ProductDTO;
import tech.ada.products_api.dto.ResponseDTO;
import tech.ada.products_api.service.ProductService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping(value = "/listAll", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ProductDTO> listAll() {
        return this.productService.listarTodos();
    }

    @GetMapping(value = "/by/{sku}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO<?>> bySKU(@PathVariable("sku") String sku) {
        return ResponseEntity.ok(this.productService.buscarPorSKU(sku));
    }

    @GetMapping(value = "/all-from/{from}/to/{to}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ProductDTO> listAll(LocalDateTime from, LocalDateTime to) {
        return this.productService.listAll(from, to);
    }

    @GetMapping(value = "/listAllByParams", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ProductDTO> listAll(@RequestParam(required = false) String name,
                                    @RequestParam LocalDateTime from,
                                    @RequestParam LocalDateTime to) {
        return this.productService.listAll(name, from, to);
    }

    @GetMapping(value = "/all-with-name-equalTo", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ProductDTO> listAllWithNameEqualTo(@RequestParam("name") String name) {
        return this.productService.buscarPorNome(name);
    }

    @PostMapping
    public ResponseEntity<ProductDTO> create(@RequestBody ProductDTO productDTO) {
        productService.criar(productDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(productDTO);
    }

    @PutMapping
    public ResponseEntity<ProductDTO> update(@RequestBody ProductDTO productDTO) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(this.productService.update(productDTO));
    }

    @DeleteMapping("/{sku}")
    public ResponseEntity<Void> delete(@PathVariable("sku") String sku) {
        productService.delete(sku);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
