package com.keralty.aeconfiguraciones.repository;

import com.keralty.aeconfiguraciones.persistence.oid.PreexistenciaCOid;
import com.keralty.aeconfiguraciones.persistence.PersonaC;
import com.keralty.aeconfiguraciones.persistence.PreexistenciaC;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PreexistenciaCJpaRepository extends JpaRepository<PreexistenciaC, PreexistenciaCOid>, PreexistenciaCJpaRepositoryCustom {

    List<PreexistenciaC> findByPersonaC(PersonaC personaC);
}
