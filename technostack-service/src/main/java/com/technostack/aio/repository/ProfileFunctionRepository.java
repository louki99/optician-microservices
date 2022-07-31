package com.technostack.aio.repository;

import com.technostack.aio.model.ProfileFunction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileFunctionRepository extends JpaRepository<ProfileFunction,Long> {

}
