package com.keralty.aeconfiguraciones.repository;

import com.keralty.aeconfiguraciones.persistence.ContratosExentosFIngreso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContratosExentosFIngresoJpaRepository extends JpaRepository<ContratosExentosFIngreso, Long>, ContratosExentosFIngresoJpaRepositoryCustom {
}
