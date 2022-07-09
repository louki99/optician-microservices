package com.technostack.aio.product.service;

import com.technostack.aio.product.dto.ProductResponse;
import com.technostack.aio.product.model.Product;
import com.technostack.aio.product.repository.ProductRepository;
import com.technostack.aio.product.dto.ProductRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;

    public void createProduct(ProductRequest productRequest) {
        Product product = Product.builder()
                .name(productRequest.getName())
                .content(productRequest.getContent())
                .description(productRequest.getDescription())
                .code_famille(productRequest.getCode_famille())
                .is_locked(productRequest.getIs_locked())
                .deliverable(productRequest.getDeliverable())
                .following_stock(productRequest.getFollowing_stock())
                .medias(productRequest.getMedias())
                .description(productRequest.getDescription())
                .price_selling(productRequest.getPrice_selling())
                .price_ht(productRequest.getPrice_ht())
                .price_ttc(productRequest.getPrice_ttc())
                .code_barre(productRequest.getCode_barre())
                .category(productRequest.getCategory())
                .build();

        productRepository.save(product);
        log.info("Product {} is saved", product.getId());
    }

    public List<ProductResponse> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(this::mapToProductResponse).toList();
    }

    //TODO you need to fitch data categories by ids
    private ProductResponse mapToProductResponse(Product product) {
        return ProductResponse.builder()
                .name(product.getName())
                .content(product.getContent())
                .description(product.getDescription())
                .code_famille(product.getCode_famille())
                .is_locked(product.getIs_locked())
                .deliverable(product.getDeliverable())
                .following_stock(product.getFollowing_stock())
                .medias(product.getMedias())
                .description(product.getDescription())
                .price_selling(product.getPrice_selling())
                .price_ht(product.getPrice_ht())
                .price_ht(product.getPrice_ht())
                .code_barre(product.getCode_barre())
                .build();
    }
}
