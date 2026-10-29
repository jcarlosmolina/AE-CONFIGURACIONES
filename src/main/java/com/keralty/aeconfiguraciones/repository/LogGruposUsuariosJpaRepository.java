package com.keralty.aeconfiguraciones.repository;

import com.keralty.aeconfiguraciones.persistence.Dominio;
import com.keralty.aeconfiguraciones.persistence.LogGruposUsuarios;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogGruposUsuariosJpaRepository extends JpaRepository<LogGruposUsuarios, Long>, LogGruposUsuariosJpaRepositoryCustom {

    List<LogGruposUsuarios> findByDominio(Dominio dominio);
}
