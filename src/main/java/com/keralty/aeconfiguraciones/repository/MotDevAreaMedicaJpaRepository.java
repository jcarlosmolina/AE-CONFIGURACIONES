package com.keralty.aeconfiguraciones.repository;

import com.keralty.aeconfiguraciones.persistence.MotDevAreaMedica;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MotDevAreaMedicaJpaRepository extends JpaRepository<MotDevAreaMedica, Long>, MotDevAreaMedicaJpaRepositoryCustom {
}
