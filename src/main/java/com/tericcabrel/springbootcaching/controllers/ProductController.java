package com.tericcabrel.springbootcaching.controllers;

import com.tericcabrel.springbootcaching.models.Product;
import com.tericcabrel.springbootcaching.models.dtos.CreateProductDto;
import com.tericcabrel.springbootcaching.models.dtos.SearchProductDto;
import com.tericcabrel.springbootcaching.models.responses.ProductListResponse;
import com.tericcabrel.springbootcaching.models.responses.ProductResponse;
import com.tericcabrel.springbootcaching.services.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<ProductResponse> create(@Valid @RequestBody CreateProductDto createProductDto) {
        Product product = productService.create(createProductDto);

        return ResponseEntity.ok(new ProductResponse(product));
    }

    @GetMapping
    public ResponseEntity<ProductListResponse> getAll() {
        List<Product> categories = productService.findAll();

        return ResponseEntity.ok(new ProductListResponse(categories));
    }

    @GetMapping("/search")
    public ResponseEntity<ProductListResponse> search(@Valid SearchProductDto searchProductDto) throws InterruptedException {
        List<Product> categories = productService.search(searchProductDto);

        return ResponseEntity.ok(new ProductListResponse(categories));
    }
}
