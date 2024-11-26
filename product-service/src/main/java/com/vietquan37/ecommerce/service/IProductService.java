package com.vietquan37.ecommerce.service;

import com.vietquan37.ecommerce.payload.request.ProductDTO;
import com.vietquan37.ecommerce.payload.request.ProductPurchaseDTO;
import com.vietquan37.ecommerce.payload.response.PageResponse;
import com.vietquan37.ecommerce.payload.response.ProductPurchaseResponse;
import com.vietquan37.ecommerce.payload.response.ProductResponse;

import java.util.List;

public interface IProductService {
    Integer createProduct(ProductDTO dto);
    ProductResponse findProductById(Integer id);
    PageResponse<ProductResponse>findAllProducts(int page, int size);
    List<ProductPurchaseResponse>purchaseProducts(List<ProductPurchaseDTO> dto);
}
