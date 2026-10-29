package com.keralty.aeconfiguraciones.repository;

import com.keralty.aeconfiguraciones.persistence.BeneficiarioC;
import com.keralty.aeconfiguraciones.persistence.ContratoC;
import com.keralty.aeconfiguraciones.persistence.Departamento;
import com.keralty.aeconfiguraciones.persistence.DireccionC;
import com.keralty.aeconfiguraciones.persistence.DirSucursalC;
import com.keralty.aeconfiguraciones.persistence.EstadoCivil;
import com.keralty.aeconfiguraciones.persistence.EstadoPersona;
import com.keralty.aeconfiguraciones.persistence.FamiliaC;
import com.keralty.aeconfiguraciones.persistence.Municipio;
import com.keralty.aeconfiguraciones.persistence.PersonaC;
import com.keralty.aeconfiguraciones.persistence.TipoEntidadC;
import com.keralty.aeconfiguraciones.persistence.TipoIdentificacion;
import com.keralty.aeconfiguraciones.persistence.TipoInstitucionC;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaCJpaRepository extends JpaRepository<PersonaC, Long>, PersonaCJpaRepositoryCustom {

    List<PersonaC> findByDireccionesC(DireccionC direccionesC);

    List<PersonaC> findByDirSucursalesC(DirSucursalC dirSucursalesC);

    List<PersonaC> findByTipoInstitucionC(TipoInstitucionC tipoInstitucionC);

    List<PersonaC> findByTipoEntidadC(TipoEntidadC tipoEntidadC);

    List<PersonaC> findByEstadoCivilC(EstadoCivil estadoCivilC);

    List<PersonaC> findByEstadoPersonaC(EstadoPersona estadoPersonaC);

    List<PersonaC> findByTipoIdentificacionC(TipoIdentificacion tipoIdentificacionC);

    List<PersonaC> findByMunicipioCNac(Municipio municipioCNac);

    List<PersonaC> findByDepartamentoCNac(Departamento departamentoCNac);

    List<PersonaC> findByContratosC(ContratoC contratosC);

    List<PersonaC> findByFamiliasC(FamiliaC familiasC);

    List<PersonaC> findByBeneficiariosC(BeneficiarioC beneficiariosC);
}
