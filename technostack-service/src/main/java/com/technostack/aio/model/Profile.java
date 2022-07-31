package com.technostack.aio.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "ad_profile")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Profile extends  AuditModel {

    @Id
    @Column(name = "profile_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 100)
    @Column(unique = true)
    private String code;

    @NotNull
    @Size(max = 250)
    @Min(3)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "domain_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Domain domain;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "domain_type_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private DomainType domainType;

    @OneToMany(mappedBy = "profile")
    Set<ProfileFunction> profileFunctions;

    @OneToMany(mappedBy = "profile")
    Set<ProfileCategoryProductFamily> categoryProductFamilies;


    @OneToMany(mappedBy = "profile")
    Set<ProfileModalityPayment> profileModalityPayments;
}
