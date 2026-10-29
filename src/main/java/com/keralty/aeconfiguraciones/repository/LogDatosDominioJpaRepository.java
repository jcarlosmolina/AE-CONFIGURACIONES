package com.keralty.aeconfiguraciones.repository;

import com.keralty.aeconfiguraciones.persistence.Dominio;
import com.keralty.aeconfiguraciones.persistence.LogDatosDominio;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogDatosDominioJpaRepository extends JpaRepository<LogDatosDominio, Long>, LogDatosDominioJpaRepositoryCustom {

    List<LogDatosDominio> findByDominio(Dominio dominio);
}
