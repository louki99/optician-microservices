package com.technostack.account.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RequestAuth {

    private String username;
    private String password;
    private String grant_type;

}
