package com.vietquan37.ecommerce.order.payload.response;

import lombok.*;

import java.util.Collections;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PageResponse<T> {
    private int currentPage;
    private int totalPages;
    private int pageSize;
    private long totalElements;
    @Builder.Default
    private List<T> data = Collections.emptyList();
}