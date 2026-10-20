package com.keralty.aeconfiguraciones.repository;

import com.keralty.aeconfiguraciones.persistence.ContSinDocSol;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContSinDocSolJpaRepository extends JpaRepository<ContSinDocSol, Long>, ContSinDocSolJpaRepositoryCustom {
}
