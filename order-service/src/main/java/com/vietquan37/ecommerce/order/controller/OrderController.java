package com.vietquan37.ecommerce.order.controller;

import com.vietquan37.ecommerce.order.payload.request.OrderDTO;
import com.vietquan37.ecommerce.order.payload.response.APIResponse;
import com.vietquan37.ecommerce.order.service.IOrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/order")
@RequiredArgsConstructor
public class OrderController {
    private final IOrderService orderService;
    @PostMapping
    public ResponseEntity<APIResponse> createOrder(@Valid @RequestBody OrderDTO dto) {
        var response=orderService.createOrder(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                APIResponse.builder().data(response).message("Order create successfully").build()
        );
    }
    @GetMapping
    public ResponseEntity<APIResponse> findAllOrder(@RequestParam(value = "page", required = false, defaultValue = "1") int page, @RequestParam(value = "size", required = false, defaultValue = "10") int size) {
        var response=orderService.findAllOrder(page, size);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                APIResponse.builder().data(response).message("Orders retrieves successfully").build()
        );
    }
    @GetMapping("/{id}")
    public ResponseEntity<APIResponse> findOrderById(@PathVariable Integer id) {
        var response=orderService.findOrderById(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                APIResponse.builder().data(response).message("Order retrieves successfully").build()
        );
    }

}
