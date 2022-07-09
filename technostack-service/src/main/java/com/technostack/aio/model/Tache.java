package com.technostack.aio.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "ad_tache")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Tache extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 100)
    @Column(unique = true)
    private String code;

    @Size(max = 100)
    private String intitule;

    @NotNull
    @Size(max = 100)
    private String name;

    private String sequence;
}
