package com.vietquan37.ecommerce.product;

import com.vietquan37.ecommerce.order.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Service
@RequiredArgsConstructor
public class ProductClient {
    @Value("${application.config.product-url}")
    private String productUrl;
    private final RestTemplate restTemplate;
    public List<ProductPurchaseResponse> purchaseProducts(List<PurchaseDTO> requestBody) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, APPLICATION_JSON_VALUE);
        HttpEntity<List<PurchaseDTO>> requestEntity=new HttpEntity<>(requestBody,headers);
        ParameterizedTypeReference<List<ProductPurchaseResponse>> responseType =
                new ParameterizedTypeReference<>() {};
        ResponseEntity<List<ProductPurchaseResponse>> responseEntity=restTemplate.exchange(
                productUrl+"/purchase",
                POST,
                requestEntity,
                responseType
        );
        if (responseEntity.getStatusCode().isError()) {
            throw new BusinessException("error while processing the product purchase: "+responseEntity.getStatusCode());
        }
        return responseEntity.getBody();
    }
}
