package com.technostack.aio.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryProductFamily extends  AuditModel {


    @Id
    @Column(name = "category_product_family_id")
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

    @OneToMany(mappedBy = "categoryProductFamily")
    Set<ProfileCategoryProductFamily> categoryProductFamily;
}
