package com.keralty.aeconfiguraciones.repository;

import com.keralty.aeconfiguraciones.persistence.Causal;
import com.keralty.aeconfiguraciones.persistence.ConfiguracionANS;
import com.keralty.aeconfiguraciones.persistence.GrupoAsignacionNovedad;
import com.keralty.aeconfiguraciones.persistence.Novedad;
import com.keralty.aeconfiguraciones.persistence.SolicitudNovedad;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CausalJpaRepository extends JpaRepository<Causal, Long>, CausalJpaRepositoryCustom {

    List<Causal> findBySolicitudes(SolicitudNovedad solicitudes);

    List<Causal> findByGrupoAsignacionNovedad(GrupoAsignacionNovedad grupoAsignacionNovedad);

    List<Causal> findByConfiguracionesANS(ConfiguracionANS configuracionesANS);

    List<Causal> findByNovedad(Novedad novedad);
}
