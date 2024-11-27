package com.vietquan37.ecommerce.orderline;

import com.vietquan37.ecommerce.order.payload.response.APIResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/order-lines")
@RequiredArgsConstructor
public class OrderLineController {
    private final OrderLineService orderLineService;

    @GetMapping("/{orderId}")
    public ResponseEntity<APIResponse> getOrderLine(@PathVariable("orderId") Integer orderId) {
        var response = orderLineService.getAllOrderLines(orderId);
        return ResponseEntity.ok(APIResponse.builder()
                .data(response)
                .message("Retrieves order line")
                .build());
    }
}
