package com.tecylab.SaleSystem.service;

import com.tecylab.SaleSystem.dto.ProductDTO;
import com.tecylab.SaleSystem.entity.Product;
import com.tecylab.SaleSystem.mapper.ProductMapper;
import com.tecylab.SaleSystem.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ResponseEntity<List<Product>> getProducts() {
        return ResponseEntity.ok(productRepository.findAll());
    }

    public ResponseEntity<Product> getProductById(Long id) {
        return ResponseEntity.status(HttpStatus.FOUND).body(productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Producto no encontrado")));
    }

    public ResponseEntity<Product> createProduct(ProductDTO productDTO) {
        Product product = productMapper.toEntity(productDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(productRepository.save(product));
    }

    public ResponseEntity<Product> updateProduct(Long id, ProductDTO productDTO) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Producto no encontrado"));

        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        product.setStock(productDTO.getStock());

        return ResponseEntity.ok(productRepository.save(product));
    }

    public ResponseEntity<?> deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            return ResponseEntity.badRequest().body("Producto no encontrado para eliminar");
        }
        productRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
