package com.technostack.account.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Access {

    private  Boolean manageGroupMembership =true;
    private  Boolean view=true;
    private  Boolean mapRoles=true;
    private  Boolean impersonate= true;
    private  Boolean manage=true;
}
