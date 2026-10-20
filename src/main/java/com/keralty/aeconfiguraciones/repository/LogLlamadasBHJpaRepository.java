package com.keralty.aeconfiguraciones.repository;

import com.keralty.aeconfiguraciones.persistence.LogLlamadasBH;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogLlamadasBHJpaRepository extends JpaRepository<LogLlamadasBH, Long>, LogLlamadasBHJpaRepositoryCustom {
}
