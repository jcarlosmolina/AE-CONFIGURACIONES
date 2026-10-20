package com.keralty.aeconfiguraciones.repository;

import com.keralty.aeconfiguraciones.persistence.EstadoCivil;
import com.keralty.aeconfiguraciones.persistence.Persona;
import com.keralty.aeconfiguraciones.persistence.PersonaC;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstadoCivilJpaRepository extends JpaRepository<EstadoCivil, Long>, EstadoCivilJpaRepositoryCustom {

    List<EstadoCivil> findByPersonas(Persona personas);

    List<EstadoCivil> findByPersonasC(PersonaC personasC);
}
