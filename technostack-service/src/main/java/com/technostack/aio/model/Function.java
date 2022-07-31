package com.technostack.aio.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import com.technostack.aio.enumerations.OS;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.domain.Page;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.awt.print.Book;
import java.util.Set;

@Entity
@Table(name = "ad_function")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Function extends  AuditModel {


    @Id
    @Column(name = "function_id")
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
    @JoinColumn(name = "domain_type_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private DomainType domainType;

    private OS os;

    @OneToMany(mappedBy = "function")
    Set<ProfileFunction> profileFunctions;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tache_id")
    private Tache tache;

}
