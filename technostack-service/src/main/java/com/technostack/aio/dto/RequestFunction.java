package com.technostack.aio.dto;

import com.technostack.aio.enumerations.OS;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequestFunction {

    private String name;
    private Long domain_type_id;
    private OS os;
}
