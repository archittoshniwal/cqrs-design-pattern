package com.archlabs.query_service.services.impl;

import com.archlabs.events.CreateProductEvent;
import com.archlabs.query_service.entities.Product;
import com.archlabs.query_service.repos.ProductRepository;
import com.archlabs.query_service.services.ProductQueryService;
import com.archlabs.query_service.utils.ProductUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
public class ProductQueryServiceImpl implements ProductQueryService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    @Value("${kafka.topic.product-event-topic}")
    private String KAFKA_TOPIC_EVENT;

    @Value("${kafka.consumer.group-id}")
    private String consumer_group_id;


    @Override
    public List<Product> getAllProducts() {
        log.info("Getting all products from DB");
        return productRepository.findAll();
    }

    @KafkaListener(topics = "${KAFKA_TOPIC_EVENT}",groupId = "${consumer_group_id}")
    public void processProductEvents(CreateProductEvent productEvent) {
        log.info("Processing product event");
        Product product = modelMapper.map(productEvent.getProduct(), Product.class);
        log.info("productEvent {} has been created", productEvent.getEventType());
        if (productEvent.getEventType().toString().equals(ProductUtils.CREATE_PRODUCT_EVENT)) {
            productRepository.save(product);
            log.info("Product saved successfully");
        }
    }
}
