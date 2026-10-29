package com.keralty.aeconfiguraciones.repository;

import com.keralty.aeconfiguraciones.persistence.LogPriorizacionSol;
import com.keralty.aeconfiguraciones.persistence.oid.PriorizacionSolicitudesOid;
import com.keralty.aeconfiguraciones.persistence.PriorizacionSolicitudes;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriorizacionSolicitudesJpaRepository extends JpaRepository<PriorizacionSolicitudes, PriorizacionSolicitudesOid>, PriorizacionSolicitudesJpaRepositoryCustom {

    List<PriorizacionSolicitudes> findByLogPriorizacionSol(LogPriorizacionSol logPriorizacionSol);
}
