package com.technostack.aio.repository;

import com.technostack.aio.model.Tache;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TacheRepository extends JpaRepository<Tache,Long> {

    Boolean deleteByCode(String code);
}
