package com.technostack.aio.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestProfile {

    private String name;
    private Long domain_id;
    private Long domain_type_id;
}
