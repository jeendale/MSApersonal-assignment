package com.sparta.msa_exam.product.entity;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "products")
@Getter
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long productId;

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private Integer supplyPrice;

    @Builder
    public Product(String name, Integer supplyPrice) {
        this.name = name;
        this.supplyPrice = supplyPrice;
    }
}
