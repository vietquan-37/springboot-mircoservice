package com.vietquan37.ecommerce.service.impl;
import com.vietquan37.ecommerce.entity.Product;
import com.vietquan37.ecommerce.exception.ProductPurchaseException;
import com.vietquan37.ecommerce.mapper.ProductMapper;
import com.vietquan37.ecommerce.payload.request.ProductDTO;
import com.vietquan37.ecommerce.payload.request.ProductPurchaseDTO;
import com.vietquan37.ecommerce.payload.response.PageResponse;
import com.vietquan37.ecommerce.payload.response.ProductPurchaseResponse;
import com.vietquan37.ecommerce.payload.response.ProductResponse;
import com.vietquan37.ecommerce.repository.ProductRepository;
import com.vietquan37.ecommerce.service.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    @Override
    public Integer createProduct(ProductDTO dto) {
        Product product=productMapper.mapDto(dto);
        productRepository.save(product);
        return product.getId();
    }

    @Override
    public ProductResponse findProductById(Integer id) {
        Product product=productRepository.findById(id).orElseThrow(()-> new ProductPurchaseException("product not found"));
        return productMapper.mapResponse(product);
    }

    @Override
    public PageResponse<ProductResponse> findAllProducts(int page, int size) {
        if (page <= 0 || size <= 0) {
            throw new IllegalArgumentException("Page and pageSize must be non-negative and pageSize must be greater than zero");
        }
        Pageable pageable = PageRequest.of(page-1, size);
        Page<Product> products=productRepository.findAll(pageable);

        return PageResponse.<ProductResponse>builder()
                .currentPage(page)
                .pageSize(size)
                .totalPages(products.getTotalPages())
                .totalElements(products.getTotalElements())
                .data(products.map(productMapper::mapResponse).stream().toList())
                .build();
    }

    @Override
    public List<ProductPurchaseResponse> purchaseProducts(List<ProductPurchaseDTO> dto) {
        var productIds=dto
                .stream()
                .map(ProductPurchaseDTO::productId)
                .toList();
        var storedProducts=productRepository.findAllById(productIds);
        if(productIds.size()!=storedProducts.size()){
            throw new ProductPurchaseException("One or more products not found");
        }
        var storesRequest=dto
                .stream()
                .sorted(Comparator.comparing(ProductPurchaseDTO::productId))
                .toList();
        var purchaseProducts=new ArrayList<ProductPurchaseResponse>();
        for(int i=0;i<storedProducts.size();i++){
            var product=storedProducts.get(i);
            var productRequest=storesRequest.get(i);
            if(product.getStock()<productRequest.quantity()){
                throw new ProductPurchaseException("inSufficient stock for product with id "+product.getId());
            }
            var newAvailableQuantity=product.getStock()-productRequest.quantity();
            product.setStock(newAvailableQuantity);
            productRepository.save(product);
            purchaseProducts.add(productMapper.toProductPurchaseResponse(product,productRequest.quantity()));
        }
        return purchaseProducts;
    }


}
