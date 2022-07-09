package com.technostack.aio.product.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.json.JsonObject;
import org.bson.types.ObjectId;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {

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
    private List<ObjectId> category;
}
