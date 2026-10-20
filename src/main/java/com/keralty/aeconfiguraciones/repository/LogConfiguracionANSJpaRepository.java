package com.keralty.aeconfiguraciones.repository;

import com.keralty.aeconfiguraciones.persistence.ConfiguracionANS;
import com.keralty.aeconfiguraciones.persistence.LogConfiguracionANS;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogConfiguracionANSJpaRepository extends JpaRepository<LogConfiguracionANS, Long>, LogConfiguracionANSJpaRepositoryCustom {

    List<LogConfiguracionANS> findByConfiguracionANS(ConfiguracionANS configuracionANS);
}
