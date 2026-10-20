package com.keralty.aeconfiguraciones.repository;

import com.keralty.aeconfiguraciones.persistence.Departamento;
import com.keralty.aeconfiguraciones.persistence.DirSucursalC;
import com.keralty.aeconfiguraciones.persistence.Municipio;
import com.keralty.aeconfiguraciones.persistence.Pais;
import com.keralty.aeconfiguraciones.persistence.PersonaC;
import com.keralty.aeconfiguraciones.persistence.Regional;
import com.keralty.aeconfiguraciones.persistence.TipoSucursalC;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DirSucursalCJpaRepository extends JpaRepository<DirSucursalC, Long>, DirSucursalCJpaRepositoryCustom {

    List<DirSucursalC> findByPersonaC(PersonaC personaC);

    List<DirSucursalC> findByTiposSucursalC(TipoSucursalC tiposSucursalC);

    List<DirSucursalC> findByMunicipioC(Municipio municipioC);

    List<DirSucursalC> findByDepartamentoC(Departamento departamentoC);

    List<DirSucursalC> findByPaisC(Pais paisC);

    List<DirSucursalC> findByRegionalC(Regional regionalC);
}
