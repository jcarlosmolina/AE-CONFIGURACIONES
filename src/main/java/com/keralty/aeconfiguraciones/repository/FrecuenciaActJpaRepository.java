package com.keralty.aeconfiguraciones.repository;

import com.keralty.aeconfiguraciones.persistence.FrecuenciaAct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FrecuenciaActJpaRepository extends JpaRepository<FrecuenciaAct, Long>, FrecuenciaActJpaRepositoryCustom {
}
