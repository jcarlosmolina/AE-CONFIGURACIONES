package com.keralty.aeconfiguraciones.repository;

import com.keralty.aeconfiguraciones.persistence.LogContratoCombo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogContratoComboJpaRepository extends JpaRepository<LogContratoCombo, Long>, LogContratoComboJpaRepositoryCustom {
}
