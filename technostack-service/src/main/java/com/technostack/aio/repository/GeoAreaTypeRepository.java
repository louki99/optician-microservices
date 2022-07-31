package com.technostack.aio.repository;

import com.technostack.aio.model.GeoAreaType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GeoAreaTypeRepository  extends JpaRepository<GeoAreaType,Long> {
}
