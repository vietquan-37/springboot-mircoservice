package com.vietquan37.ecommerce.payload.response;
import com.vietquan37.ecommerce.entity.Address;

public record CustomerResponse(
        String id,
        String firstName,
        String lastName,
        String email,
        Address address
) {
}
