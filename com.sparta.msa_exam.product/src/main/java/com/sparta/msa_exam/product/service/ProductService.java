package com.sparta.msa_exam.product.service;

import com.sparta.msa_exam.product.dto.ProductReqDto;
import com.sparta.msa_exam.product.dto.ProductResDto;
import com.sparta.msa_exam.product.entity.Product;
import com.sparta.msa_exam.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public String createProduct(ProductReqDto productReqDto) {
        productRepository.save(Product.builder()
                .name(productReqDto.getName())
                .supplyPrice(productReqDto.getPrice())
                .build());

        return "상품 생성!";
    }

    public List<ProductResDto> getProductList() {
        List<Product> productList = productRepository.findAll();
        List<ProductResDto> productResDtoList = new ArrayList<>();
        for(Product product : productList){
            ProductResDto productResDto = ProductResDto.builder()
                    .productId(product.getProductId())
                    .name(product.getName())
                    .price(product.getSupplyPrice())
                    .build();

            productResDtoList.add(productResDto);
        }
        return productResDtoList;
    }
}
