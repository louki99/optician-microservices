package com.technostack.aio.controller;


import com.technostack.aio.dto.request.RequestProfile;
import com.technostack.aio.exception.ExceptionHandling;
import com.technostack.aio.exception.domain.DomainNotFoundException;
import com.technostack.aio.exception.domain.DomainTypeNotFoundException;
import com.technostack.aio.exception.domain.FunctionNotFoundException;
import com.technostack.aio.model.*;
import com.technostack.aio.repository.DomainRepository;
import com.technostack.aio.repository.DomainTypeRepository;
import com.technostack.aio.repository.FunctionRepository;
import com.technostack.aio.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/api/administration/profile")
public class ProfileController extends ExceptionHandling {

    @Autowired
    private  ProfileRepository profileRepository;

    @Autowired
    private DomainRepository domainRepository;

    @Autowired
    private DomainTypeRepository domainTypeRepository;


    @Autowired
    private FunctionRepository functionRepository;



    @GetMapping("/")
    public Page<Profile> getAllProfiles(Pageable pageable) {
        return profileRepository.findAll(pageable);
    }

    @PostMapping("/")
    public Profile createProfile(@Valid @RequestBody RequestProfile requestProfile) throws
            DomainNotFoundException,
            DomainTypeNotFoundException {

        Domain domain = domainRepository.findById(requestProfile.getDomain_id())
                .orElseThrow(()-> new DomainNotFoundException("domain not found"));

        DomainType domainType = domainTypeRepository.findById(requestProfile.getDomain_type_id())
                .orElseThrow(()-> new DomainTypeNotFoundException("domain type not found"));

        Profile profile =
                Profile
                        .builder()
                        .name(requestProfile.getName())
                        .code(UUID.randomUUID().toString())
                        .domain(domain)
                        .domainType(domainType)
                        .build();

        return profileRepository.save(profile);
    }

    @PutMapping("/")
    public Profile update(@RequestParam Long profileId, @Valid @RequestBody RequestProfile requestProfile) throws
            DomainNotFoundException,
            DomainTypeNotFoundException,
            FunctionNotFoundException
            {

        Domain domain = domainRepository.findById(requestProfile.getDomain_id())
                .orElseThrow(()-> new DomainNotFoundException("domain not found"));

        DomainType domainType = domainTypeRepository.findById(requestProfile.getDomain_type_id())
                .orElseThrow(()-> new DomainTypeNotFoundException("domain type not found"));

        return profileRepository.findById(profileId).map(profile -> {
            profile.setName(requestProfile.getName());
            profile.setDomain(domain);
            profile.setDomainType(domainType);
            return profileRepository.save(profile);
        }).orElseThrow(() -> new FunctionNotFoundException("Profile Id : " + profileId + " not found"));
    }

}
