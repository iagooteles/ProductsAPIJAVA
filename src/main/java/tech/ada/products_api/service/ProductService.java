package tech.ada.products_api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.ada.products_api.dto.ProductDTO;
import tech.ada.products_api.model.Product;
import tech.ada.products_api.repository.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public void criar(ProductDTO productDTO) {

        Product product = new Product();
        product.setSku(productDTO.getSku());
        product.setName(product.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setWeight(productDTO.getWeight());

        productRepository.save(product);

    }

    // fazer o get
    // usar o swagger
}
