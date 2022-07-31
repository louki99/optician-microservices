package com.technostack.aio.product.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Product {

    @Id
    private String id;
    private String reference;
    private String reference_intern;
    private String name;
    private String description;
    private String content;
    private BigDecimal price_selling;
    private BigDecimal price_ttc;
    private BigDecimal price_ht;
    private String code_barre;
    private String code_famille;
    private Boolean is_locked;
    private Boolean deliverable;
    private Boolean following_stock;
}
