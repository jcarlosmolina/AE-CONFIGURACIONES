package com.keralty.aeconfiguraciones.repository;

import com.keralty.aeconfiguraciones.persistence.PersonaC;
import com.keralty.aeconfiguraciones.persistence.TipoInstitucionC;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoInstitucionCJpaRepository extends JpaRepository<TipoInstitucionC, Long>, TipoInstitucionCJpaRepositoryCustom {

    List<TipoInstitucionC> findByPersonasC(PersonaC personasC);
}
