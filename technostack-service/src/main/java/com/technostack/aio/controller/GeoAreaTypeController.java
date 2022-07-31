package com.technostack.aio.controller;


import com.technostack.aio.dto.request.RequestFunction;
import com.technostack.aio.dto.response.ResponseFunction;
import com.technostack.aio.exception.ExceptionHandling;
import com.technostack.aio.exception.domain.DomainTypeNotFoundException;
import com.technostack.aio.exception.domain.FunctionNotFoundException;
import com.technostack.aio.exception.domain.TacheNotFoundException;
import com.technostack.aio.model.DomainType;
import com.technostack.aio.model.Function;
import com.technostack.aio.model.GeoAreaType;
import com.technostack.aio.model.Tache;
import com.technostack.aio.repository.DomainTypeRepository;
import com.technostack.aio.repository.FunctionRepository;
import com.technostack.aio.repository.GeoAreaTypeRepository;
import com.technostack.aio.repository.TacheRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/api/administration/geo-area-type")
public class GeoAreaTypeController extends ExceptionHandling {

    @Autowired
    private GeoAreaTypeRepository repo;

    @GetMapping("/")
    public Page<GeoAreaType> all(Pageable pageable) {
        return repo.findAll(pageable);
    }

    @PostMapping("/")
    public GeoAreaType create(@Valid @RequestBody GeoAreaType request){

        GeoAreaType geoAreaType =
                GeoAreaType
                        .builder()
                        .name(request.getName())
                        .code(UUID.randomUUID().toString())
                        .hold(true)
                        .child(request.getChild())
                        .up(request.getUp())
                        .build();

        return repo.save(geoAreaType);
    }

    @PutMapping("/")
    public GeoAreaType update(@RequestParam Long geoAreaTypeId,
                              @Valid
                              @RequestBody GeoAreaType request) throws
            Exception {

        return repo.findById(geoAreaTypeId).map(geoAreaType -> {
            geoAreaType.setName(request.getName());
            geoAreaType.setChild(request.getChild());
            geoAreaType.setUp(request.getUp());
            if(request.getHold())
                geoAreaType.setHold(request.getHold());
            return repo.save(geoAreaType);
        }).orElseThrow(() -> new Exception("geo-area-type id : " + geoAreaTypeId + " not found"));
    }
}
