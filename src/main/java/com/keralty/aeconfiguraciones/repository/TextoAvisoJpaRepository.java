package com.keralty.aeconfiguraciones.repository;

import com.keralty.aeconfiguraciones.persistence.TextoAviso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TextoAvisoJpaRepository extends JpaRepository<TextoAviso, String>, TextoAvisoJpaRepositoryCustom {
}
