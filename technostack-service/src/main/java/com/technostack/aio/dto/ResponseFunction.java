package com.technostack.aio.dto;

import com.technostack.aio.enumerations.OS;
import com.technostack.aio.model.DomainType;
import com.technostack.aio.model.Function;
import com.technostack.aio.model.Tache;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseFunction {

    private String function_code;
    private String function_name;
    private DomainType domain_type;
    private OS os;

    public static ResponseFunction mapToResponse(Function function) {

        return
                ResponseFunction
                        .builder()
                        .function_code(function.getCode())
                        .function_name(function.getName())
                        .domain_type(function.getDomainType())
                        .os(function.getOs())
                        .build();
    }
}
