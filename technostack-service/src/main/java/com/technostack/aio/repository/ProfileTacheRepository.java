package com.technostack.aio.repository;

import com.technostack.aio.model.ProfileFunction;
import com.technostack.aio.model.ProfileTache;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileTacheRepository extends JpaRepository<ProfileTache,Long> {

}
