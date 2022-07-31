package com.technostack.aio.repository;


import com.technostack.aio.model.CategoryProductFamily;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryProductFamilyRepository extends JpaRepository<CategoryProductFamily,Long> {

}
