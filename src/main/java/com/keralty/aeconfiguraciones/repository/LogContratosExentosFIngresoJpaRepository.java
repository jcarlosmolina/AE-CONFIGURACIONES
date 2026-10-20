package com.keralty.aeconfiguraciones.repository;

import com.keralty.aeconfiguraciones.persistence.LogContratosExentosFIngreso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogContratosExentosFIngresoJpaRepository extends JpaRepository<LogContratosExentosFIngreso, Long>, LogContratosExentosFIngresoJpaRepositoryCustom {
}
