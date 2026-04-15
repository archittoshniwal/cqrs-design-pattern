package com.archlabs.command_service.entities;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "PRODUCT_COMMAND")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private double price;
}
