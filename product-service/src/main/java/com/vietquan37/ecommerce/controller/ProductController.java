package com.vietquan37.ecommerce.controller;

import com.vietquan37.ecommerce.entity.Product;
import com.vietquan37.ecommerce.payload.request.ProductDTO;
import com.vietquan37.ecommerce.payload.request.ProductPurchaseDTO;
import com.vietquan37.ecommerce.payload.response.APIResponse;
import com.vietquan37.ecommerce.service.IProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/product")
@RequiredArgsConstructor
public class ProductController {
    private final IProductService productService;

    @GetMapping("/{id}")
    public ResponseEntity<APIResponse> findProductById(@PathVariable("id") Integer id) {
        var response = productService.findProductById(id);
        return ResponseEntity.ok(APIResponse.builder()
                .data(response)
                .message("Retrieving product successfully")
                .build()
        );
    }
    @PostMapping
    public ResponseEntity<APIResponse> createProduct(@RequestBody @Valid ProductDTO dto) {
        var response = productService.createProduct(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(APIResponse.builder()
                .data(response)
                .message("Retrieving product successfully")
                .build()
        );
    }
    @GetMapping
    public ResponseEntity<APIResponse> findAllProducts(@RequestParam(value = "page", required = false, defaultValue = "1") int page, @RequestParam(value = "size", required = false, defaultValue = "10") int size) {
        var response = productService.findAllProducts(page, size);
        return ResponseEntity.ok(APIResponse.builder()
                .data(response)
                .message("Retrieving products successfully")
                .build()
        );
    }
    @PostMapping("/purchase")
    public ResponseEntity<APIResponse> purchaseProducts(@RequestBody @Valid List<ProductPurchaseDTO> dto) {
        var response = productService.purchaseProducts(dto);
        return ResponseEntity.ok(APIResponse.builder()
                .data(response)
                .message("Retrieving purchase products successfully")
                .build()
        );
    }
}
