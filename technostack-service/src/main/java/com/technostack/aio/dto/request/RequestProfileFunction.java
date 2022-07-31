package com.technostack.aio.dto.request;


import com.technostack.aio.model.Function;
import com.technostack.aio.model.Profile;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestProfileFunction {

    private Long profileId;

    private Long functionId;

    private Boolean hold;
}
