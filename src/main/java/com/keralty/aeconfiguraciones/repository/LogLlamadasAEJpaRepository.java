package com.keralty.aeconfiguraciones.repository;

import com.keralty.aeconfiguraciones.persistence.LogLlamadasAE;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogLlamadasAEJpaRepository extends JpaRepository<LogLlamadasAE, Long>, LogLlamadasAEJpaRepositoryCustom {
}
