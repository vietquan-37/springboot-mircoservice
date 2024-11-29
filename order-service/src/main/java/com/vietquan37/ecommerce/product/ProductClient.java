package com.vietquan37.ecommerce.product;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(
        name = "order-service",
        url = "${application.config.product-url}"
)
public interface ProductClient {
    @PostMapping("/purchase")
    List<ProductPurchaseResponse> purchaseProduct(@RequestBody List<PurchaseDTO> request) ;
}
