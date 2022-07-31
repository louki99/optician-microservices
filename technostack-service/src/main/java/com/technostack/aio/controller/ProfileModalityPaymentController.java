package com.technostack.aio.controller;

import com.technostack.aio.dto.request.RequestProfileFunction;
import com.technostack.aio.dto.request.RequestProfileModalityPayment;
import com.technostack.aio.exception.domain.ModalityPaymentNotFoundException;
import com.technostack.aio.exception.domain.ProfileNotFoundException;
import com.technostack.aio.model.*;
import com.technostack.aio.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/administration/profile-modality-payment")
public class ProfileModalityPaymentController {


    @Autowired
    ProfileModalityPaymentRepository repo;

    @Autowired
    ProfileRepository profileRepository;

    @Autowired
    ModalityPaymentRepository modalityPaymentRepository;

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> create(@RequestBody RequestProfileModalityPayment request)
            throws ProfileNotFoundException,
                   ModalityPaymentNotFoundException {

        Profile profile = profileRepository.findById(request.getProfileId())
                .orElseThrow(()-> new ProfileNotFoundException("profile not exist in database"));

        PaymentModality paymentModality = modalityPaymentRepository.findById(request.getModalityPaymentId())
                .orElseThrow(()-> new ModalityPaymentNotFoundException("payment modality the not exist in database"));

        ProfileModalityPayment profileModalityPayment  =
                ProfileModalityPayment
                        .builder()
                        .profile(profile)
                        .paymentModality(paymentModality)
                        .hold(request.getHold())
                        .build();

        repo.save(profileModalityPayment);

        return ResponseEntity.status(HttpStatus.ACCEPTED)
                    .body("success!!");
    }
}