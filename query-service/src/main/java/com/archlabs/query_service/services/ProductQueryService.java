package com.archlabs.query_service.services;

import com.archlabs.events.CreateProductEvent;
import com.archlabs.query_service.entities.Product;

import java.util.List;

public interface ProductQueryService {
    List<Product> getAllProducts();
    void processProductEvents(CreateProductEvent productEvent);
}
