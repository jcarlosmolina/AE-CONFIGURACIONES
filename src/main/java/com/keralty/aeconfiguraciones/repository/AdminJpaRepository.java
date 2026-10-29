package com.keralty.aeconfiguraciones.repository;

import com.keralty.aeconfiguraciones.persistence.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminJpaRepository extends JpaRepository<Admin, Long>, AdminJpaRepositoryCustom {
}
