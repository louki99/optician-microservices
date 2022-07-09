package com.technostack.aio.product.dto;


import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Media {

    private String name;
    private String path;
    private String target;
}
