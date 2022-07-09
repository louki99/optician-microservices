package com.technostack.account.config;

import feign.RequestTemplate;

public class FeignInterceptor {

    public void apply(RequestTemplate requestTemplate) {
        requestTemplate.header("Access-Control-Allow-Origin", "*")
                .header("Content-Type", "application/json");
    }
}


