package com.technostack.aio.controller;


import com.technostack.aio.dto.request.RequestProfileCatFamily;
import com.technostack.aio.exception.ExceptionHandling;
import com.technostack.aio.exception.domain.ProfileNotFoundException;
import com.technostack.aio.model.CategoryProductFamily;
import com.technostack.aio.model.Profile;
import com.technostack.aio.model.ProfileCategoryProductFamily;
import com.technostack.aio.repository.CategoryProductFamilyRepository;
import com.technostack.aio.repository.ProfileCategoryProductFamilyRepository;
import com.technostack.aio.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/administration/profile-category-productfamily/")
public class ProfileCategoryProductFamilyController extends ExceptionHandling {

    @Autowired
    ProfileCategoryProductFamilyRepository repo;

    @Autowired
    ProfileRepository repoProfile;

    @Autowired
    CategoryProductFamilyRepository repoCatProductFamily;


    @PostMapping()
    public ResponseEntity<String> createProfileCategoryProductFamily(@RequestBody RequestProfileCatFamily request)
            throws ProfileNotFoundException {

        Profile profile = repoProfile.findById(request.getProfileId())
                .orElseThrow(()-> new ProfileNotFoundException("profile not exist in database"));

        CategoryProductFamily categoryProductFamily = repoCatProductFamily.findById(request.getCategoryProductFamily())
                .orElseThrow(()-> new ProfileNotFoundException("category-product-family not exist in database"));


        ProfileCategoryProductFamily profileCategoryProductFamily =
                ProfileCategoryProductFamily
                        .builder()
                        .profile(profile)
                        .categoryProductFamily(categoryProductFamily)
                        .build();
        repo.save(profileCategoryProductFamily);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body("operation succss fully");
    }
}
