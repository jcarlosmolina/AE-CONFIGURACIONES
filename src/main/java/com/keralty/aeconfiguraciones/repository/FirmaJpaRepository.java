package com.keralty.aeconfiguraciones.repository;

import com.keralty.aeconfiguraciones.persistence.FamiliaNovedad;
import com.keralty.aeconfiguraciones.persistence.Firma;
import com.keralty.aeconfiguraciones.persistence.RespRegional;
import com.keralty.aeconfiguraciones.persistence.Solicitud;
import com.keralty.aeconfiguraciones.persistence.SolicitudNovedad;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FirmaJpaRepository extends JpaRepository<Firma, Long>, FirmaJpaRepositoryCustom {

    List<Firma> findBySolicitudNovedad(SolicitudNovedad solicitudNovedad);

    List<Firma> findByFamiliaNovedad(FamiliaNovedad familiaNovedad);

    List<Firma> findBySolicitud(Solicitud solicitud);

    List<Firma> findBySolicitudSegunda(Solicitud solicitudSegunda);

    List<Firma> findByRespRegional(RespRegional respRegional);

    List<Firma> findBySolicitudContratante(Solicitud solicitudContratante);
}
