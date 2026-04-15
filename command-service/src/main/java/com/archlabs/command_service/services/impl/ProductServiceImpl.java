package com.archlabs.command_service.services.impl;

import com.archlabs.command_service.dtos.ProductEvent;
import com.archlabs.command_service.entities.Product;
import com.archlabs.command_service.repos.ProductRepository;
import com.archlabs.command_service.services.ProductService;
import com.archlabs.command_service.utils.ProductUtils;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import com.archlabs.events.*;


@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    final private ModelMapper modelMapper;
    final private ProductRepository productRepository;
    final private KafkaTemplate<String, CreateProductEvent> kafkaTemplate;

    public Product createProduct(ProductEvent productEvent) {

        Product product = modelMapper.map(productEvent.getProduct(), Product.class);
        if (!productEvent.getEventType().equals(ProductUtils.CREATE_PRODUCT_EVENT)){
           throw new RuntimeException("Different event type");
        }

        // save in db
        product = productRepository.save(product);

        CreateProductEvent createProductEvent = modelMapper.map(productEvent, CreateProductEvent.class);

        // sendEvent in kafka
        kafkaTemplate.send("product-event-topic",createProductEvent);

        return product;
    }

    @Override
    public Product updateProduct(Long id, ProductEvent productEvent) {

        Product existingProduct = productRepository.findById(id).get();

        Product newProduct=productEvent.getProduct();
        existingProduct = modelMapper.map(newProduct, Product.class);

        //save updated entity in db
        Product productDO = productRepository.save(existingProduct);

        //send event to kafka
        CreateProductEvent createProductEvent = modelMapper.map(productEvent, CreateProductEvent.class);

        kafkaTemplate.send("product-event-topic", createProductEvent);

        return productDO;

    }

}
