package com.archlabs.query_service.entities;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "PRODUCT_QUERY")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String description;
    private double price;
}
