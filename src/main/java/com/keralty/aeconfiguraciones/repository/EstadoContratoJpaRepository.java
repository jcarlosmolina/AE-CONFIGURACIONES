package com.keralty.aeconfiguraciones.repository;

import com.keralty.aeconfiguraciones.persistence.Aesolicitudes;
import com.keralty.aeconfiguraciones.persistence.ContratoC;
import com.keralty.aeconfiguraciones.persistence.EstadoContrato;
import com.keralty.aeconfiguraciones.persistence.SolicitudNovedad;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstadoContratoJpaRepository extends JpaRepository<EstadoContrato, Long>, EstadoContratoJpaRepositoryCustom {

    List<EstadoContrato> findByAESOLICITUDES(Aesolicitudes aESOLICITUDES);

    List<EstadoContrato> findByContratosC(ContratoC contratosC);

    List<EstadoContrato> findBySolicitudesNovedad(SolicitudNovedad solicitudesNovedad);
}
