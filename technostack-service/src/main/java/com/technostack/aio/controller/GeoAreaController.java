package com.technostack.aio.controller;


import com.technostack.aio.dto.request.RequestGeoArea;
import com.technostack.aio.exception.ExceptionHandling;
import com.technostack.aio.exception.domain.GeoAreaTypeNotFoundException;
import com.technostack.aio.model.GeoArea;
import com.technostack.aio.model.GeoAreaType;
import com.technostack.aio.repository.GeoAreaRepository;
import com.technostack.aio.repository.GeoAreaTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/api/administration/geo-area")
public class GeoAreaController extends ExceptionHandling {

    @Autowired
    GeoAreaRepository repo;

    @Autowired
    GeoAreaTypeRepository geoAreaTypeRepository;


    @GetMapping("/")
    public Page<GeoArea> all(Pageable pageable) {
        return repo.findAll(pageable);
    }

    @PostMapping("/")
    public GeoArea create(@Valid @RequestBody RequestGeoArea request)
            throws GeoAreaTypeNotFoundException {

        GeoAreaType geoAreaType =
                geoAreaTypeRepository.findById(request.getGeoAreaTypeId())
                        .orElseThrow(()->
                                new GeoAreaTypeNotFoundException("geo area type not found"));

        GeoArea geoArea =
                GeoArea
                        .builder()
                        .name(request.getName())
                        .code(UUID.randomUUID().toString())
                        .geoareatype(geoAreaType)
                        .upGeoAreaType(request.getUpGeoAreaType())
                        .build();

        return repo.save(geoArea);
    }

    @PutMapping("/")
    public GeoArea update(@RequestParam Long geoAreaId, @Valid @RequestBody RequestGeoArea request) throws
            Exception {

        GeoAreaType geoAreaType =
                geoAreaTypeRepository.findById(request.getGeoAreaTypeId())
                        .orElseThrow(()->
                                new GeoAreaTypeNotFoundException("geo area type not found"));

        return repo.findById(geoAreaId).map(geoArea -> {
            geoArea.setName(request.getName());
            geoArea.setUpGeoAreaType(request.getUpGeoAreaType());
            geoArea.setGeoareatype(geoAreaType);

            return repo.save(geoArea);
        }).orElseThrow(() -> new Exception("geo-area id : " + geoAreaId + " not found"));
    }
}
