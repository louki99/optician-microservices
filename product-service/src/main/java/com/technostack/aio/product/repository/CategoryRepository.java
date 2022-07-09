package com.technostack.aio.product.repository;

import com.technostack.aio.product.model.Category;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CategoryRepository extends MongoRepository<Category, String> {
}
