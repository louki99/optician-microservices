package com.technostack.aio.controller;


import com.technostack.aio.dto.request.RequestFunction;
import com.technostack.aio.dto.response.ResponseFunction;
import com.technostack.aio.exception.ExceptionHandling;
import com.technostack.aio.exception.domain.DomainTypeNotFoundException;
import com.technostack.aio.exception.domain.FunctionNotFoundException;
import com.technostack.aio.exception.domain.ProfileNotFoundException;
import com.technostack.aio.exception.domain.TacheNotFoundException;
import com.technostack.aio.model.DomainType;
import com.technostack.aio.model.Function;
import com.technostack.aio.model.Profile;
import com.technostack.aio.model.Tache;
import com.technostack.aio.repository.DomainTypeRepository;
import com.technostack.aio.repository.FunctionRepository;
import com.technostack.aio.repository.TacheRepository;
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

    @Autowired
    private TacheRepository tacheRepository;

    @GetMapping("/")
    public Page<Function> all(Pageable pageable) {
        return functionRepository.findAll(pageable);
    }

    @PostMapping("/")
    public ResponseFunction create(@Valid @RequestBody RequestFunction request) throws
            DomainTypeNotFoundException, TacheNotFoundException {

        Tache tache = tacheRepository.findById(request.getTache_id())
                .orElseThrow(()-> new TacheNotFoundException("Tache not exist in database"));

        return domainTypeRepository.findById(request.getDomain_type_id()).map(domainType -> {
            Function function =
                    Function
                            .builder()
                                .code(UUID.randomUUID().toString())
                                .name(request.getName())
                                .os(request.getOs())
                                .domainType(domainType)
                                .tache(tache)
                            .build();
            return ResponseFunction.mapToResponse(functionRepository.save(function));
        }).orElseThrow(() -> new DomainTypeNotFoundException("domain type : " + request.getDomain_type_id() + " not found"));
    }

    @PutMapping("/")
    public Function update(@RequestParam Long functionId, @Valid @RequestBody RequestFunction requestFunction) throws
            FunctionNotFoundException, DomainTypeNotFoundException {

        DomainType domainType = domainTypeRepository.findById(requestFunction.getDomain_type_id())
                .orElseThrow(()->new DomainTypeNotFoundException("domain type : " + requestFunction.getDomain_type_id() + " not found"));

        return functionRepository.findById(functionId).map(function -> {
            function.setName(requestFunction.getName());
            function.setDomainType(domainType);
            function.setOs(requestFunction.getOs());
            return functionRepository.save(function);
        }).orElseThrow(() -> new FunctionNotFoundException("FunctionId : " + functionId + " not found"));
    }
}
