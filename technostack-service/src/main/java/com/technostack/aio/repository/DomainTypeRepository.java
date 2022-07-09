package com.technostack.aio.repository;

import com.technostack.aio.model.DomainType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DomainTypeRepository extends JpaRepository<DomainType,Long> {

    Page<DomainType> findByDomainId(Long domainId, Pageable pageable);
    Optional<DomainType> findByIdAndDomainId(Long id, Long domainId);
}
