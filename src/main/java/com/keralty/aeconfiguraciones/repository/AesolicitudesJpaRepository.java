package com.keralty.aeconfiguraciones.repository;

import com.keralty.aeconfiguraciones.persistence.Aesolicitudes;
import com.keralty.aeconfiguraciones.persistence.EstadoContrato;
import com.keralty.aeconfiguraciones.persistence.Planes;
import com.keralty.aeconfiguraciones.persistence.Productos;
import com.keralty.aeconfiguraciones.persistence.Solicitud;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AesolicitudesJpaRepository extends JpaRepository<Aesolicitudes, Long>, AesolicitudesJpaRepositoryCustom {

    List<Aesolicitudes> findBySolicitud(Solicitud solicitud);

    List<Aesolicitudes> findByProductoT(Productos productoT);

    List<Aesolicitudes> findByPlanT(Planes planT);

    List<Aesolicitudes> findByESTADOSOLICITUD(EstadoContrato eSTADOSOLICITUD);
}
