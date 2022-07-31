package com.technostack.aio.dto.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestProfileTache {

    private Long profileId;

    private Long tacheId;

    private Boolean hold;
}
