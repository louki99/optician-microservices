package com.technostack.aio.controller;


import com.technostack.aio.exception.ExceptionHandling;
import com.technostack.aio.model.CategoryProductFamily;
import com.technostack.aio.repository.CategoryProductFamilyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/administration/")
public class CategoryProductFamilyController extends ExceptionHandling {

    @Autowired
    private CategoryProductFamilyRepository categoryProductFamilyRepository;

    @PostMapping("category-productfamily")
    public CategoryProductFamily createCategoryProductFamily(@RequestBody CategoryProductFamily request) {
        CategoryProductFamily categoryProductFamily =
                CategoryProductFamily
                        .builder()
                        .code(UUID.randomUUID().toString())
                        .name(request.getName())
                        .build();
        return categoryProductFamilyRepository.save(categoryProductFamily);
    }
}
