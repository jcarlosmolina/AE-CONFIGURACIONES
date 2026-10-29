package com.keralty.aeconfiguraciones.repository;

import com.keralty.aeconfiguraciones.persistence.ConfiguracionFestivos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConfiguracionFestivosJpaRepository extends JpaRepository<ConfiguracionFestivos, Long>, ConfiguracionFestivosJpaRepositoryCustom {
}
