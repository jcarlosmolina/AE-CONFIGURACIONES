package com.keralty.aeconfiguraciones.repository;

import com.keralty.aeconfiguraciones.persistence.Causal;
import com.keralty.aeconfiguraciones.persistence.Dominio;
import com.keralty.aeconfiguraciones.persistence.GrupoAsignacion;
import com.keralty.aeconfiguraciones.persistence.GrupoAsignacionNovedad;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GrupoAsignacionNovedadJpaRepository extends JpaRepository<GrupoAsignacionNovedad, Long>, GrupoAsignacionNovedadJpaRepositoryCustom {

    List<GrupoAsignacionNovedad> findByDominio(Dominio dominio);

    List<GrupoAsignacionNovedad> findByCausal(Causal causal);

    List<GrupoAsignacionNovedad> findByGrupoUsuarios(GrupoAsignacion grupoUsuarios);
}
