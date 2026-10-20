package com.keralty.aeconfiguraciones.repository;

import com.keralty.aeconfiguraciones.persistence.CausaExclusion;
import com.keralty.aeconfiguraciones.persistence.Causal;
import com.keralty.aeconfiguraciones.persistence.ConfiguracionANS;
import com.keralty.aeconfiguraciones.persistence.Dominio;
import com.keralty.aeconfiguraciones.persistence.Novedad;
import com.keralty.aeconfiguraciones.persistence.SolicitudNovedad;
import com.keralty.aeconfiguraciones.persistence.TipoNovedad;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NovedadJpaRepository extends JpaRepository<Novedad, Long>, NovedadJpaRepositoryCustom {

    List<Novedad> findByDominios(Dominio dominios);

    List<Novedad> findByCausales(Causal causales);

    List<Novedad> findBySolicitudes(SolicitudNovedad solicitudes);

    List<Novedad> findByCausasExclusion(CausaExclusion causasExclusion);

    List<Novedad> findByConfiguracionesANS(ConfiguracionANS configuracionesANS);

    List<Novedad> findByTipoNovedad(TipoNovedad tipoNovedad);
}
