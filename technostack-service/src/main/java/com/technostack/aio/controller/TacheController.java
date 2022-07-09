package com.technostack.aio.controller;


import com.technostack.aio.dto.RequestDomain;
import com.technostack.aio.dto.RequestProfile;
import com.technostack.aio.dto.RequestTache;
import com.technostack.aio.dto.ResponseTache;
import com.technostack.aio.exception.ExceptionHandling;
import com.technostack.aio.exception.domain.DomainNotFoundException;
import com.technostack.aio.exception.domain.DomainTypeNotFoundException;
import com.technostack.aio.exception.domain.TacheNotFoundException;
import com.technostack.aio.model.Domain;
import com.technostack.aio.model.DomainType;
import com.technostack.aio.model.Profile;
import com.technostack.aio.model.Tache;
import com.technostack.aio.repository.DomainRepository;
import com.technostack.aio.repository.DomainTypeRepository;
import com.technostack.aio.repository.ProfileRepository;
import com.technostack.aio.repository.TacheRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/api/administration/tache")
public class TacheController extends ExceptionHandling {

    @Autowired
    private TacheRepository tacheRepository;


    @GetMapping("/")
    public Page<Tache> paginate(Pageable pageable) {
        return tacheRepository.findAll(pageable);
    }

    @PostMapping("/")
    public ResponseTache create(@Valid @RequestBody RequestTache requestTache){

        Tache tache =
                Tache.builder()
                        .intitule(requestTache.getIntitule())
                        .name(requestTache.getName())
                        .sequence(requestTache.getSequence())
                        .code(UUID.randomUUID().toString())
                        .build();

        return ResponseTache.mapToResponse(tacheRepository.save(tache));
    }

    @PutMapping("/{profileId}")
    public Tache update(@PathVariable Long profileId, @Valid @RequestBody RequestTache requestTache) throws
            TacheNotFoundException {
        return tacheRepository.findById(profileId).map(profile -> {
            profile.setSequence(requestTache.getSequence());
            profile.setIntitule(requestTache.getIntitule());
            profile.setName(requestTache.getName());
            return tacheRepository.save(profile);
        }).orElseThrow(() -> new TacheNotFoundException("TacheId " + profileId + " not found"));
    }

    @DeleteMapping("/{profileId}")
    public void delete(@PathVariable Long profileId) throws
            TacheNotFoundException {
         tacheRepository.findById(profileId).map(profile -> {
            return tacheRepository.deleteByCode(profile.getCode());
         }).orElseThrow(() -> new TacheNotFoundException("TacheId " + profileId + " not found"));
    }
}
