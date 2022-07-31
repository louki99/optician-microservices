package com.technostack.aio.controller;


import com.technostack.aio.exception.ExceptionHandling;
import com.technostack.aio.exception.domain.TacheNotFoundException;
import com.technostack.aio.model.PaymentModality;
import com.technostack.aio.repository.ModalityPaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/api/administration/modality-payment")
public class PaymentModalityController extends ExceptionHandling {

    @Autowired
    private ModalityPaymentRepository repo;


    @GetMapping("/")
    public Page<PaymentModality> paginate(Pageable pageable) {
        return repo.findAll(pageable);
    }

    @PostMapping("/")
    public PaymentModality create(@Valid @RequestBody PaymentModality request){

        PaymentModality paymentModality =
                PaymentModality.builder()
                        .name(request.getName())
                        .code(UUID.randomUUID().toString())
                        .build();

        return repo.save(paymentModality);
    }

    @PutMapping("/{modalityPaymentId}")
    public PaymentModality update(@PathVariable Long modalityPaymentId, @Valid @RequestBody PaymentModality request) throws
            TacheNotFoundException {
        return repo.findById(modalityPaymentId).map(modality -> {
            modality.setName(request.getName());
            return repo.save(modality);
        }).orElseThrow(() -> new TacheNotFoundException("Modality Payment id : " + modalityPaymentId + " not found"));
    }

    @DeleteMapping("/{modalityPaymentId}")
    public void delete(@PathVariable Long modalityPaymentId) throws
            TacheNotFoundException {
         repo.findById(modalityPaymentId).map(modality -> {
            return repo.deleteByCode(modality.getCode());
         }).orElseThrow(() -> new TacheNotFoundException("TacheId " + modalityPaymentId + " not found"));
    }
}
