package com.archlabs.command_service.services;

import com.archlabs.command_service.dtos.ProductEvent;
import com.archlabs.command_service.entities.Product;

public interface ProductService {
    Product createProduct(ProductEvent productEvent);
    Product updateProduct(Long id,ProductEvent productEvent);
}
