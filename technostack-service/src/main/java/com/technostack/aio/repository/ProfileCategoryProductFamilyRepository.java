package com.technostack.aio.repository;


import com.technostack.aio.model.CategoryProductFamily;
import com.technostack.aio.model.ProfileCategoryProductFamily;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileCategoryProductFamilyRepository
        extends JpaRepository<ProfileCategoryProductFamily,Long> {

}
