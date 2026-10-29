package com.keralty.aeconfiguraciones.repository;

import com.keralty.aeconfiguraciones.persistence.Contratante;
import com.keralty.aeconfiguraciones.persistence.ContratoC;
import com.keralty.aeconfiguraciones.persistence.Departamento;
import com.keralty.aeconfiguraciones.persistence.Direccion;
import com.keralty.aeconfiguraciones.persistence.DireccionC;
import com.keralty.aeconfiguraciones.persistence.DirSucursalC;
import com.keralty.aeconfiguraciones.persistence.FamiliaC;
import com.keralty.aeconfiguraciones.persistence.InstitucionMatNeo;
import com.keralty.aeconfiguraciones.persistence.Municipio;
import com.keralty.aeconfiguraciones.persistence.Persona;
import com.keralty.aeconfiguraciones.persistence.PersonaC;
import com.keralty.aeconfiguraciones.persistence.Titular;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MunicipioJpaRepository extends JpaRepository<Municipio, Long>, MunicipioJpaRepositoryCustom {

    List<Municipio> findByPersonas(Persona personas);

    List<Municipio> findByPersonasC(PersonaC personasC);

    List<Municipio> findByDireccionesC(DireccionC direccionesC);

    List<Municipio> findByDirecciones(Direccion direcciones);

    List<Municipio> findByContratantes(Contratante contratantes);

    List<Municipio> findByContratanteRes(Contratante contratanteRes);

    List<Municipio> findByContratosC(ContratoC contratosC);

    List<Municipio> findByDirSucursalC(DirSucursalC dirSucursalC);

    List<Municipio> findByFamiliaC(FamiliaC familiaC);

    List<Municipio> findByTitulares(Titular titulares);

    List<Municipio> findByInstitucionMatNeo(InstitucionMatNeo institucionMatNeo);

    List<Municipio> findByDepartamento(Departamento departamento);
}
