package com.technostack.aio.model;


import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GeoArea {

    @Id
    @Column(name = "geo_area_id")
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

    private String upGeoAreaType;

    @ManyToOne
    @JoinColumn(name="geoareatype_id", nullable=false)
    private GeoAreaType geoareatype;

}
