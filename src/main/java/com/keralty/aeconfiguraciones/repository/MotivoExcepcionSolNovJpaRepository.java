package com.keralty.aeconfiguraciones.repository;

import com.keralty.aeconfiguraciones.persistence.MotivoExcepcionSolNov;
import com.keralty.aeconfiguraciones.persistence.SolicitudNovedad;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MotivoExcepcionSolNovJpaRepository extends JpaRepository<MotivoExcepcionSolNov, Long>, MotivoExcepcionSolNovJpaRepositoryCustom {

    List<MotivoExcepcionSolNov> findBySolicitudes(SolicitudNovedad solicitudes);
}
