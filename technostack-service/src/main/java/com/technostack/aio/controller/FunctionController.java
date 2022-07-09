package com.technostack.aio.controller;


import com.technostack.aio.dto.RequestDomain;
import com.technostack.aio.dto.RequestFunction;
import com.technostack.aio.dto.ResponseFunction;
import com.technostack.aio.exception.ExceptionHandling;
import com.technostack.aio.exception.domain.DomainNotFoundException;
import com.technostack.aio.exception.domain.DomainTypeNotFoundException;
import com.technostack.aio.model.Domain;
import com.technostack.aio.model.DomainType;
import com.technostack.aio.model.Function;
import com.technostack.aio.repository.DomainRepository;
import com.technostack.aio.repository.DomainTypeRepository;
import com.technostack.aio.repository.FunctionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/api/administration/functions")
public class FunctionController extends ExceptionHandling {

    @Autowired
    private FunctionRepository functionRepository;

    @Autowired
    private DomainTypeRepository domainTypeRepository;

    @GetMapping("/")
    public Page<Function> all(Pageable pageable) {
        return functionRepository.findAll(pageable);
    }

    @PostMapping("/")
    public ResponseFunction create(@Valid @RequestBody RequestFunction requestFunction) throws
            DomainTypeNotFoundException {

        return domainTypeRepository.findById(requestFunction.getDomain_type_id()).map(domainType -> {
            Function function =
                    Function
                            .builder()
                                .code(UUID.randomUUID().toString())
                                .name(requestFunction.getName())
                                .os(requestFunction.getOs())
                                .domainType(domainType)
                            .build();
            return ResponseFunction.mapToResponse(functionRepository.save(function));
        }).orElseThrow(() -> new DomainTypeNotFoundException("domain type : " + requestFunction.getDomain_type_id() + " not found"));
    }


    @PutMapping("/{functionId}")
    public ResponseFunction update(@PathVariable Long domainId, @Valid @RequestBody RequestDomain requestDomain) throws
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
