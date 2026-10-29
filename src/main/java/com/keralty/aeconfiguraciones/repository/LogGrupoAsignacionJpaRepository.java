package com.keralty.aeconfiguraciones.repository;

import com.keralty.aeconfiguraciones.persistence.GrupoAsignacion;
import com.keralty.aeconfiguraciones.persistence.LogGrupoAsignacion;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogGrupoAsignacionJpaRepository extends JpaRepository<LogGrupoAsignacion, Long>, LogGrupoAsignacionJpaRepositoryCustom {

    List<LogGrupoAsignacion> findByGrupoAsignacion(GrupoAsignacion grupoAsignacion);
}
