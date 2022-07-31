package com.technostack.aio.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestProfile {

    private String name;
    private Long domain_id;
    private Long domain_type_id;
}
