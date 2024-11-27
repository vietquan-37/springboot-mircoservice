package com.vietquan37.ecommerce.controller;


import com.vietquan37.ecommerce.payload.request.CustomerDTO;
import com.vietquan37.ecommerce.payload.request.CustomerUpdateDTO;
import com.vietquan37.ecommerce.payload.response.APIResponse;
import com.vietquan37.ecommerce.service.ICustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {
    private final ICustomerService customerService;

    @PostMapping
    public ResponseEntity<APIResponse> createCustomer(@Valid @RequestBody CustomerDTO dto) {
        var response = customerService.createCustomer(dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(APIResponse.builder().data(response).message("Create customer successfully").build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<APIResponse> updateCustomer(@PathVariable String id, @Valid @RequestBody CustomerUpdateDTO dto) {
        customerService.updateCustomer(id, dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(APIResponse.builder().message("Updating customer successfully").build());
    }
    @GetMapping
    public ResponseEntity<APIResponse> findAllCustomer(@RequestParam(value = "page", required = false, defaultValue = "1") int page, @RequestParam(value = "size", required = false, defaultValue = "10") int size) {
       var response= customerService.findAllCustomers(page,size);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(APIResponse.builder().data(response).message("Retrieving customer successfully").build());
    }
    @GetMapping("/{id}")
    public ResponseEntity<APIResponse> findAllCustomer(@PathVariable String id) {
        var response= customerService.findCustomerById(id);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(APIResponse.builder().data(response).message("Retrieving customer successfully").build());
    }
}
