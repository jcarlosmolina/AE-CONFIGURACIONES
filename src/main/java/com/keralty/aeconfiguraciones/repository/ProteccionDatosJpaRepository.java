package com.keralty.aeconfiguraciones.repository;

import com.keralty.aeconfiguraciones.persistence.Productos;
import com.keralty.aeconfiguraciones.persistence.ProteccionDatos;
import com.keralty.aeconfiguraciones.persistence.Solicitud;
import com.keralty.aeconfiguraciones.persistence.SolicitudNovedad;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProteccionDatosJpaRepository extends JpaRepository<ProteccionDatos, Long>, ProteccionDatosJpaRepositoryCustom {

    List<ProteccionDatos> findBySolicitudNovedad(SolicitudNovedad solicitudNovedad);

    List<ProteccionDatos> findByProductoT(Productos productoT);

    List<ProteccionDatos> findBySolicitud(Solicitud solicitud);
}
