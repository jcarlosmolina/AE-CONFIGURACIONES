package com.keralty.aeconfiguraciones.repository;

import com.keralty.aeconfiguraciones.persistence.EntidadFinancieraC;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntidadFinancieraCJpaRepository extends JpaRepository<EntidadFinancieraC, Long>, EntidadFinancieraCJpaRepositoryCustom {
}
