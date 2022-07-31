package com.technostack.aio.dto.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestProfileCatFamily {

    private Long profileId;

    private Long categoryProductFamily;
}
