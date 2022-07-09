package com.technostack.account.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseAuth {

        private String access_token;
        private int expires_in;
        private int refresh_expires_in;
        private String refresh_token;
        private String  token_type  ="Bearer";
        private  int  not_before_policy =0;
        private String session_state;
        private String scope;
}
