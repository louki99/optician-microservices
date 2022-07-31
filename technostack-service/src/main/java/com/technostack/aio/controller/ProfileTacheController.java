package com.technostack.aio.controller;

import com.technostack.aio.dto.request.RequestProfileTache;
import com.technostack.aio.exception.domain.ProfileNotFoundException;
import com.technostack.aio.exception.domain.TacheNotFoundException;
import com.technostack.aio.model.Profile;
import com.technostack.aio.model.ProfileTache;
import com.technostack.aio.model.Tache;
import com.technostack.aio.repository.ProfileRepository;
import com.technostack.aio.repository.ProfileTacheRepository;
import com.technostack.aio.repository.TacheRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/administration/profile-tache")
public class ProfileTacheController {

    @Autowired
    ProfileTacheRepository profileTacheRepository;

    @Autowired
    ProfileRepository profileRepository;

    @Autowired
    TacheRepository tacheRepository;

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> create(@RequestBody RequestProfileTache request)
            throws ProfileNotFoundException, TacheNotFoundException {

        Profile profile = profileRepository.findById(request.getProfileId())
                .orElseThrow(()-> new ProfileNotFoundException("profile not exist in database"));

        Tache tache = tacheRepository.findById(request.getTacheId())
                .orElseThrow(()-> new TacheNotFoundException("tache the not exist in database"));

        ProfileTache profileTache =
                ProfileTache
                        .builder()
                        .profile(profile)
                        .tache(tache)
                        .hold(request.getHold())
                        .build();

        profileTacheRepository.save(profileTache);

        return ResponseEntity.status(HttpStatus.CREATED)
                    .body("link profile with tache success");
    }
}