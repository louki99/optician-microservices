package com.technostack.aio.product.service;

import com.technostack.aio.product.dto.CategoryRequest;
import com.technostack.aio.product.dto.CategoryResponse;
import com.technostack.aio.product.model.Category;
import com.technostack.aio.product.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public void createCategory(CategoryRequest productRequest) {
        Category product = Category.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .is_active(productRequest.getIs_active())
                .parent(productRequest.getParent())
                .build();

        categoryRepository.save(product);
        log.info("Category {} is saved", product.get_id());
    }

    public List<CategoryResponse> getAllProducts() {
        List<Category> categories = categoryRepository.findAll();

        return categories.stream().map(this::mapToProductResponse).toList();
    }

    private CategoryResponse mapToProductResponse(Category category) {
        return CategoryResponse.builder()
                .id(category.get_id())
                .name(category.getName())
                .description(category.getDescription())
                .parent(category.getParent())
                .icon(category.getIcon())
                .build();
    }
}
