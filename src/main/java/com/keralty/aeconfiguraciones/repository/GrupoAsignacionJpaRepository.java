package com.keralty.aeconfiguraciones.repository;

import com.keralty.aeconfiguraciones.persistence.Dominio;
import com.keralty.aeconfiguraciones.persistence.Funcionario;
import com.keralty.aeconfiguraciones.persistence.GrupoAsignacion;
import com.keralty.aeconfiguraciones.persistence.GrupoAsignacionNovedad;
import com.keralty.aeconfiguraciones.persistence.LogGrupoAsignacion;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GrupoAsignacionJpaRepository extends JpaRepository<GrupoAsignacion, Long>, GrupoAsignacionJpaRepositoryCustom {

    List<GrupoAsignacion> findByDominioAsesores(Dominio dominioAsesores);

    List<GrupoAsignacion> findByDominioAreaMedica(Dominio dominioAreaMedica);

    List<GrupoAsignacion> findByLogGrupoAsignacion(LogGrupoAsignacion logGrupoAsignacion);

    List<GrupoAsignacion> findByDominioCausalUsuario(GrupoAsignacionNovedad dominioCausalUsuario);

    List<GrupoAsignacion> findByFuncionario(Funcionario funcionario);
}
