package com.technostack.account.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private Boolean enabled =true;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private List<Credentials> credentials;
    private List<String> requiredActions;
    private List<String> groups;
    private Attributes attributes;

    private List<String> realmRoles;

    private Access access;
    private int notBefore= 0;
    private Boolean totp= false;
    private Boolean emailVerified= false;

}
