package com.keralty.aeconfiguraciones.repository;

import com.keralty.aeconfiguraciones.persistence.ContratoC;
import com.keralty.aeconfiguraciones.persistence.GrupoAsociado;
import com.keralty.aeconfiguraciones.persistence.Planes;
import com.keralty.aeconfiguraciones.persistence.Productos;
import com.keralty.aeconfiguraciones.persistence.Solicitud;
import com.keralty.aeconfiguraciones.persistence.SolicitudNovedad;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GrupoAsociadoJpaRepository extends JpaRepository<GrupoAsociado, Long>, GrupoAsociadoJpaRepositoryCustom {

    List<GrupoAsociado> findBySolicitudes(Solicitud solicitudes);

    List<GrupoAsociado> findByContratosC(ContratoC contratosC);

    List<GrupoAsociado> findBySolicitudesNovedad(SolicitudNovedad solicitudesNovedad);

    List<GrupoAsociado> findByProductoT(Productos productoT);

    List<GrupoAsociado> findByPlanT(Planes planT);
}
