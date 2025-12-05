package com.tecylab.SaleSystem.mapper;

import com.tecylab.SaleSystem.dto.ProductDTO;
import com.tecylab.SaleSystem.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public Product toEntity(ProductDTO productDTO) {
        return Product.builder()
                .name(productDTO.getName())
                .price(productDTO.getPrice())
                .stock(productDTO.getStock())
                .build();
    }

}
