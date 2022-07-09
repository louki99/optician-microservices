package com.technostack.aio.repository;

import com.technostack.aio.model.Function;
import com.technostack.aio.model.Tache;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FunctionRepository extends JpaRepository<Function,Long> {

}
