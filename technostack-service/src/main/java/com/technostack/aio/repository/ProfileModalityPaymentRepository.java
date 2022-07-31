package com.technostack.aio.repository;

import com.technostack.aio.model.ProfileModalityPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileModalityPaymentRepository
        extends JpaRepository<ProfileModalityPayment,Long> {

}
