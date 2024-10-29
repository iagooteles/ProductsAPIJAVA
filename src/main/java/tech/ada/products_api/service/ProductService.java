package tech.ada.products_api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.ada.products_api.dto.ProductDTO;
import tech.ada.products_api.dto.ResponseDTO;
import tech.ada.products_api.model.Product;
import tech.ada.products_api.repository.ProductRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<ProductDTO> listarTodos() {
        return this.productRepository
                .findAll()
                .stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }

    public List<ProductDTO> buscarPorNome(String name) {
        List<Product> products = productRepository.findByNameContainingIgnoreCase(name);
        return products.stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }

    public void criar(ProductDTO productDTO) {
        Product product = new Product();
        product.setSku(productDTO.getSku());
        product.setName(product.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setWeight(productDTO.getWeight());

        productRepository.save(product);
    }

    public ProductDTO update(ProductDTO productDTO) {
        Optional<Product> optionalProduct = this.getBySku(productDTO.getSku());

        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            product.setSku(productDTO.getSku());
            product.setName(productDTO.getName());
            product.setDescription(productDTO.getDescription());
            product.setPrice(productDTO.getPrice());
            product.setWeight(productDTO.getWeight());
            this.productRepository.save(product);
            return productDTO;
        }
        return null;
    }

    public void delete(String sku) {
        Optional<Product> productToBeDeleted = this.productRepository.findBySku(sku);
        productToBeDeleted.ifPresent(product -> this.productRepository.delete(product));
    }

    public ResponseDTO<?> buscarPorSKU(String sku) {
        Optional<Product> optionalProduct = getBySku(sku);
        if(optionalProduct.isPresent()) {
            return ResponseDTO
                    .builder()
                    .message("Produto encontrado")
                    .timestamp(LocalDateTime.now())
                    .data(convert(optionalProduct.get())).build();
        }
        return ResponseDTO
                .builder()
                .message("Nenhum produto encontrado.").build();
    }

    private Optional<Product> getBySku(String sku) {
        return this.productRepository.findBySku(sku);
    }

    private ProductDTO convert (Product product) {
        ProductDTO productDTO = new ProductDTO();

        productDTO.setSku(product.getSku());
        productDTO.setName(product.getName());
        productDTO.setDescription(product.getDescription());
        productDTO.setPrice(product.getPrice());
        productDTO.setWeight(product.getWeight());

        return productDTO;
    }
}
