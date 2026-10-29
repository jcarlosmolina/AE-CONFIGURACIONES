package com.keralty.aeconfiguraciones.repository;

import com.keralty.aeconfiguraciones.persistence.Gestor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GestorJpaRepository extends JpaRepository<Gestor, Long>, GestorJpaRepositoryCustom {
}
