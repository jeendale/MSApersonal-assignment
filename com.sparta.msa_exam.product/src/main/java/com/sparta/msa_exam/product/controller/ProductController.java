package com.sparta.msa_exam.product.controller;

import com.sparta.msa_exam.product.dto.ProductReqDto;
import com.sparta.msa_exam.product.dto.ProductResDto;
import com.sparta.msa_exam.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {
    @Value("${server.port}")
    private String serverPort;

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<String> createProduct(@RequestBody ProductReqDto productReqDto){
        String msg =productService.createProduct(productReqDto);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Server-Port", serverPort);
        return ResponseEntity.ok()
                .headers(headers)
                .body(msg);
    }

    @GetMapping
    public ResponseEntity<List<ProductResDto>> getProductList(){
        List<ProductResDto> productList=productService.getProductList();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Server-Port", serverPort);
        return ResponseEntity.ok()
                .headers(headers)
                .body(productList);
    }

}
