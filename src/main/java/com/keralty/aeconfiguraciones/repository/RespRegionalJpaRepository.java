package com.keralty.aeconfiguraciones.repository;

import com.keralty.aeconfiguraciones.persistence.Regional;
import com.keralty.aeconfiguraciones.persistence.RespRegional;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RespRegionalJpaRepository extends JpaRepository<RespRegional, Long>, RespRegionalJpaRepositoryCustom {

    List<RespRegional> findByRegional(Regional regional);
}
