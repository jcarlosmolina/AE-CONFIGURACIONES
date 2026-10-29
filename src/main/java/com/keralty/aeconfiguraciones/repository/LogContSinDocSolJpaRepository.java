package com.keralty.aeconfiguraciones.repository;

import com.keralty.aeconfiguraciones.persistence.LogContSinDocSol;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogContSinDocSolJpaRepository extends JpaRepository<LogContSinDocSol, Long>, LogContSinDocSolJpaRepositoryCustom {
}
