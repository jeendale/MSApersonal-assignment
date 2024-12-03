package com.sparta.msa_exam.product.dto;

import com.sparta.msa_exam.product.entity.Product;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Builder
public class ProductResDto {
    private Long productId;
    private String name;
    private Integer price;

    public ProductResDto(Long productId,String name, Integer price) {
        this.productId = productId;
        this.name=name;
        this.price=price;
    }
}
