package com.technostack.aio.product.model;

import com.technostack.aio.product.dto.Media;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.List;

@Document(value = "product")
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
    private List<Media> medias;
    private Boolean is_locked;
    private Boolean deliverable;
    private Boolean following_stock;

    private List<Category> category;


}
