package com.keralty.aeconfiguraciones.repository;

import com.keralty.aeconfiguraciones.persistence.Solicitud;
import com.keralty.aeconfiguraciones.persistence.TipoFactura;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoFacturaJpaRepository extends JpaRepository<TipoFactura, String>, TipoFacturaJpaRepositoryCustom {

    List<TipoFactura> findBySolicitudes(Solicitud solicitudes);
}
