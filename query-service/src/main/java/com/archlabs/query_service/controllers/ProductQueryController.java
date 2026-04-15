package com.archlabs.query_service.controllers;

import com.archlabs.query_service.entities.Product;
import com.archlabs.query_service.services.ProductQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RequestMapping("/products")
@RestController
@RequiredArgsConstructor
public class ProductQueryController {

    private final ProductQueryService queryService;

    @GetMapping
    public List<Product> fetchAllProducts(){
        return queryService.getAllProducts();
    }


}
