package com.keralty.aeconfiguraciones.repository;

import com.keralty.aeconfiguraciones.persistence.Dominio;
import com.keralty.aeconfiguraciones.persistence.LogIncompatibilidades;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogIncompatibilidadesJpaRepository extends JpaRepository<LogIncompatibilidades, Long>, LogIncompatibilidadesJpaRepositoryCustom {

    List<LogIncompatibilidades> findByDominio(Dominio dominio);
}
