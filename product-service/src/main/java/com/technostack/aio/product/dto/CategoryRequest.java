package com.technostack.aio.product.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.technostack.aio.product.model.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategoryRequest {
    private String name;
    private String description;
    private Category parent;
    private Boolean is_active;
}
