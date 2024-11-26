package com.vietquan37.ecommerce.mapper;

import com.vietquan37.ecommerce.entity.Product;
import com.vietquan37.ecommerce.payload.request.ProductDTO;
import com.vietquan37.ecommerce.payload.response.ProductPurchaseResponse;
import com.vietquan37.ecommerce.payload.response.ProductResponse;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    Product mapDto(ProductDTO dto);
    @Mapping(target = "categoryId",source = "category.id")
    @Mapping(target = "categoryName",source = "category.name")
    ProductResponse mapResponse(Product product);
    @Mapping(target = "productId", source = "product.id")
    @Mapping(target = "quantity", expression = "java(quantity)")
    ProductPurchaseResponse toProductPurchaseResponse(Product product, @Context double quantity);
}
