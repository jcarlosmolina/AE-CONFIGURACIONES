package com.keralty.aeconfiguraciones.repository;

import com.keralty.aeconfiguraciones.persistence.UsuarioSistemaExterno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioSistemaExternoJpaRepository extends JpaRepository<UsuarioSistemaExterno, String>, UsuarioSistemaExternoJpaRepositoryCustom {
}
