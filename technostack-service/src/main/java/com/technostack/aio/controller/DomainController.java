package com.technostack.aio.controller;


import com.technostack.aio.dto.request.RequestDomain;
import com.technostack.aio.exception.ExceptionHandling;
import com.technostack.aio.exception.domain.DomainNotFoundException;
import com.technostack.aio.exception.domain.DomainTypeNotFoundException;
import com.technostack.aio.model.Domain;
import com.technostack.aio.model.DomainType;
import com.technostack.aio.repository.DomainRepository;
import com.technostack.aio.repository.DomainTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/api/domains")
public class DomainController extends ExceptionHandling {

    @Autowired
    private DomainRepository domainRepository;

    @Autowired
    private DomainTypeRepository domainTypeRepository;

    @GetMapping("/")
    public Page<Domain> getAllDomains(Pageable pageable) {
        return domainRepository.findAll(pageable);
    }

    @PostMapping("/")
    public Domain createDomain(@Valid @RequestBody RequestDomain requestDomain) throws
            DomainNotFoundException {
        Domain domain =
                Domain.builder()
                        .code(UUID.randomUUID().toString())
                        .name(requestDomain.getName())
                        .build();
        return domainRepository.save(domain);
    }


    @PutMapping("/{domainId}")
    public Domain updatePost(@PathVariable Long domainId, @Valid @RequestBody RequestDomain requestDomain) throws
            DomainNotFoundException {
        return domainRepository.findById(domainId).map(post -> {
            post.setName(requestDomain.getName());
            return domainRepository.save(post);
        }).orElseThrow(() -> new DomainNotFoundException("DomainId " + domainId + " not found"));
    }

    @PostMapping("type/{domainId}")
    public DomainType createDomainType(@PathVariable (value = "domainId") Long domainId,
                                    @Valid @RequestBody DomainType requestDomainType) throws
            DomainTypeNotFoundException {


        return domainRepository.findById(domainId).map(domain -> {
            DomainType domainType =
                    DomainType
                            .builder()
                            .domain(domain)
                            .code(UUID.randomUUID().toString())
                            .name(requestDomainType.getName())
                            .build();
            return domainTypeRepository.save(domainType);
        }).orElseThrow(() -> new DomainTypeNotFoundException("domain " + domainId + " not found"));
    }
}
