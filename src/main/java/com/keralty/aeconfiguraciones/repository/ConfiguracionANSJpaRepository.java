package com.keralty.aeconfiguraciones.repository;

import com.keralty.aeconfiguraciones.persistence.Causal;
import com.keralty.aeconfiguraciones.persistence.ConfiguracionANS;
import com.keralty.aeconfiguraciones.persistence.Dominio;
import com.keralty.aeconfiguraciones.persistence.LogConfiguracionANS;
import com.keralty.aeconfiguraciones.persistence.Novedad;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConfiguracionANSJpaRepository extends JpaRepository<ConfiguracionANS, Long>, ConfiguracionANSJpaRepositoryCustom {

    List<ConfiguracionANS> findByLogConfiguracionANS(LogConfiguracionANS logConfiguracionANS);

    List<ConfiguracionANS> findByDominio(Dominio dominio);

    List<ConfiguracionANS> findByNovedad(Novedad novedad);

    List<ConfiguracionANS> findByCausal(Causal causal);
}
