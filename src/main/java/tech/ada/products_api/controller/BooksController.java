package tech.ada.products_api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.ada.products_api.dto.BooksDTO;
import tech.ada.products_api.dto.ProductDTO;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BooksController {

    static List<BooksDTO> books = new ArrayList<>();

    @GetMapping(value = "listAll", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<BooksDTO> listAll() {
        return books;
    }

    @PostMapping
    public ResponseEntity<BooksDTO> create(@RequestBody BooksDTO booksDTO) {
        books.add(booksDTO);

        return ResponseEntity.ok(booksDTO);
    }

    @PutMapping
    public ResponseEntity<BooksDTO> update(@RequestBody BooksDTO booksDTO) {
        BooksDTO book = books.stream()
                .filter(b -> b.getId().equalsIgnoreCase(booksDTO.getId()))
                .findFirst()
                .orElseThrow();

        int index = books.indexOf(book);
        book.setTitle(booksDTO.getTitle());

        books.set(index, book);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(book);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") String id) {
        BooksDTO book = books.stream()
                .filter(b -> b.getId().equalsIgnoreCase(id))
                .findFirst()
                .orElseThrow();

        books.remove(book);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
