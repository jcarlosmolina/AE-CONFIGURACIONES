package com.keralty.aeconfiguraciones.repository;

import com.keralty.aeconfiguraciones.persistence.LogPriorizacionSol;
import com.keralty.aeconfiguraciones.persistence.PriorizacionSolicitudes;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogPriorizacionSolJpaRepository extends JpaRepository<LogPriorizacionSol, Long>, LogPriorizacionSolJpaRepositoryCustom {

    List<LogPriorizacionSol> findByPriSolicitud(PriorizacionSolicitudes priSolicitud);
}
