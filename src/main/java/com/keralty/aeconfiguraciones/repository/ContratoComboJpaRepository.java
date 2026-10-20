package com.keralty.aeconfiguraciones.repository;

import com.keralty.aeconfiguraciones.persistence.ContratoCombo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContratoComboJpaRepository extends JpaRepository<ContratoCombo, Long>, ContratoComboJpaRepositoryCustom {
}
