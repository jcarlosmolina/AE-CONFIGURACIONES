package com.keralty.aeconfiguraciones.repository;

import com.keralty.aeconfiguraciones.persistence.MesaDeApoyo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MesaDeApoyoJpaRepository extends JpaRepository<MesaDeApoyo, Long>, MesaDeApoyoJpaRepositoryCustom {
}
