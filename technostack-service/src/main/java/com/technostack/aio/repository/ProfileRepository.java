package com.technostack.aio.repository;

import com.technostack.aio.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile,Long> {

    Profile findByCode(String code);
}
