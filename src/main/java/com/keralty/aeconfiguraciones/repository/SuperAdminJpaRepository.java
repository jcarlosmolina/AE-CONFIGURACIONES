package com.keralty.aeconfiguraciones.repository;

import com.keralty.aeconfiguraciones.persistence.SuperAdmin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SuperAdminJpaRepository extends JpaRepository<SuperAdmin, Long>, SuperAdminJpaRepositoryCustom {
}
