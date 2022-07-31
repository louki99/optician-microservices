package com.technostack.aio.controller;

import com.technostack.aio.dto.request.RequestProfileFunction;
import com.technostack.aio.exception.domain.ProfileNotFoundException;
import com.technostack.aio.model.Function;
import com.technostack.aio.model.Profile;
import com.technostack.aio.model.ProfileFunction;
import com.technostack.aio.repository.FunctionRepository;
import com.technostack.aio.repository.ProfileFunctionRepository;
import com.technostack.aio.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/administration/profile-function")
public class ProfileFunctionController {


    @Autowired
    ProfileFunctionRepository profileFunctionRepository;

    @Autowired
    ProfileRepository profileRepository;

    @Autowired
    FunctionRepository functionRepository;

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> create(@RequestBody RequestProfileFunction request) throws ProfileNotFoundException {

        Profile profile = profileRepository.findById(request.getProfileId())
                .orElseThrow(()-> new ProfileNotFoundException("profile not exist in database"));

        Function function = functionRepository.findById(request.getFunctionId())
                .orElseThrow(()-> new ProfileNotFoundException("function the not exist in database"));

        ProfileFunction profileFunction =
                ProfileFunction
                        .builder()
                        .profile(profile)
                        .function(function)
                        .hold(request.getHold())
                        .build();

        profileFunctionRepository.save(profileFunction);

        return ResponseEntity.status(HttpStatus.CREATED)
                    .header("Linking","link profile with function")
                    .body("link profile with function success");
    }
}