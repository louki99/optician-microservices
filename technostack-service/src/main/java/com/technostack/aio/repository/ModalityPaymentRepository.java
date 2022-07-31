package com.technostack.aio.repository;

import com.technostack.aio.model.Domain;
import com.technostack.aio.model.PaymentModality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModalityPaymentRepository extends JpaRepository<PaymentModality, Long> {

    Domain findByCode(String code);

    Boolean deleteByCode(String code);
}
