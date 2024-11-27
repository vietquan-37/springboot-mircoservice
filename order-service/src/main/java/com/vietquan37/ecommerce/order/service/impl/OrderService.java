package com.vietquan37.ecommerce.order.service.impl;

import com.vietquan37.ecommerce.customer.CustomerClient;
import com.vietquan37.ecommerce.order.entity.Order;
import com.vietquan37.ecommerce.order.exception.BusinessException;
import com.vietquan37.ecommerce.order.kafka.OrderConfirmation;
import com.vietquan37.ecommerce.order.kafka.OrderProducer;
import com.vietquan37.ecommerce.order.mapper.OrderMapper;
import com.vietquan37.ecommerce.order.payload.request.OrderDTO;
import com.vietquan37.ecommerce.order.payload.response.OrderResponse;
import com.vietquan37.ecommerce.order.payload.response.PageResponse;
import com.vietquan37.ecommerce.order.repository.OrderRepository;
import com.vietquan37.ecommerce.order.service.IOrderService;
import com.vietquan37.ecommerce.orderline.OrderLineDTO;
import com.vietquan37.ecommerce.orderline.OrderLineService;
import com.vietquan37.ecommerce.product.ProductClient;
import com.vietquan37.ecommerce.product.PurchaseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService implements IOrderService {
    private final OrderRepository orderRepository;
    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderMapper orderMapper;
    private final OrderLineService orderLineService;
    private final OrderProducer orderProducer;

    @Override
    public Integer createOrder(OrderDTO dto) {
        //check customer -->OpenFeign
        var customer = customerClient.findCustomerById(dto.customerId())
                .orElseThrow(() -> new BusinessException("Cannot create order:: Not found customer with id " + dto.customerId()));
        //purchase products --> using restTemplate
        var purchaseProduct = productClient.purchaseProducts(dto.products());
        //persist order
        var order = orderRepository.save(orderMapper.mapDTO(dto));
        for (PurchaseDTO purchaseDTO : dto.products()) {
            orderLineService.createOrderLine(new OrderLineDTO(
                    purchaseDTO.productId(),
                    purchaseDTO.quantity(),
                    order.getId(),
                    purchaseDTO.price()

            ));
        }
        //start payment process
        //sending to notification to kafka
        orderProducer.sendOrderConfirmation(new OrderConfirmation(
                dto.reference(),
                dto.amount(),
                dto.paymentMethod(),
                customer,
                purchaseProduct
        ));
        return order.getId();
    }

    @Override
    public PageResponse<OrderResponse> findAllOrder(int page, int size) {
        if (page<=0||size<=0){
            throw new BusinessException("Invalid page or number of items");
        }
        Sort sort=Sort.by(Sort.Direction.DESC, "createAt");
        Pageable pageable = PageRequest.of(page-1, size);
        Page<Order>orders=orderRepository.findAll(pageable);
        return PageResponse.<OrderResponse>builder()
                .currentPage(page)
                .totalElements(orders.getTotalElements())
                .totalPages(orders.getTotalPages())
                .pageSize(size)
                .data(orders.map(orderMapper::mapResponse).stream().toList())
                .build();
    }

    @Override
    public OrderResponse findOrderById(Integer id) {
        var order = orderRepository.findById(id).orElseThrow(()->new BusinessException("Cannot find order with id " + id));
        return orderMapper.mapResponse(order);
    }

    ;
}
