package com.keralty.aeconfiguraciones.repository;

import com.keralty.aeconfiguraciones.persistence.FechaIniEPS;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FechaIniEPSJpaRepository extends JpaRepository<FechaIniEPS, Long>, FechaIniEPSJpaRepositoryCustom {
}
