package com.technostack.account.domain;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Attributes {

    private List<String> local;
}
