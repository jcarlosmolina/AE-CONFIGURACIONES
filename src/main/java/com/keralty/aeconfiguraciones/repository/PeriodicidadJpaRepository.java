package com.keralty.aeconfiguraciones.repository;

import com.keralty.aeconfiguraciones.persistence.AnexoC;
import com.keralty.aeconfiguraciones.persistence.AnexoMat;
import com.keralty.aeconfiguraciones.persistence.AnexoNeo;
import com.keralty.aeconfiguraciones.persistence.ContratoC;
import com.keralty.aeconfiguraciones.persistence.Dominio;
import com.keralty.aeconfiguraciones.persistence.Periodicidad;
import com.keralty.aeconfiguraciones.persistence.Solicitud;
import com.keralty.aeconfiguraciones.persistence.SolicitudNovedad;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PeriodicidadJpaRepository extends JpaRepository<Periodicidad, Long>, PeriodicidadJpaRepositoryCustom {

    List<Periodicidad> findByDominios(Dominio dominios);

    List<Periodicidad> findBySolicitudes(Solicitud solicitudes);

    List<Periodicidad> findByAnexosMat(AnexoMat anexosMat);

    List<Periodicidad> findByAnexosNeo(AnexoNeo anexosNeo);

    List<Periodicidad> findByContratosC(ContratoC contratosC);

    List<Periodicidad> findByAnexosC(AnexoC anexosC);

    List<Periodicidad> findBySolicitudesNovedad(SolicitudNovedad solicitudesNovedad);

    List<Periodicidad> findBySolicitudesNovedadNueva(SolicitudNovedad solicitudesNovedadNueva);
}
