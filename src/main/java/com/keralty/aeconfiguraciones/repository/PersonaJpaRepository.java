package com.keralty.aeconfiguraciones.repository;

import com.keralty.aeconfiguraciones.persistence.Departamento;
import com.keralty.aeconfiguraciones.persistence.Direccion;
import com.keralty.aeconfiguraciones.persistence.EstadoCivil;
import com.keralty.aeconfiguraciones.persistence.EstadoPersona;
import com.keralty.aeconfiguraciones.persistence.Municipio;
import com.keralty.aeconfiguraciones.persistence.Persona;
import com.keralty.aeconfiguraciones.persistence.Solicitud;
import com.keralty.aeconfiguraciones.persistence.TipoIdentificacion;
import com.keralty.aeconfiguraciones.persistence.Usuario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaJpaRepository extends JpaRepository<Persona, Long>, PersonaJpaRepositoryCustom {

    List<Persona> findBySolicitudesContratante(Solicitud solicitudesContratante);

    List<Persona> findBySolicitudesTitular(Solicitud solicitudesTitular);

    List<Persona> findByDirecciones(Direccion direcciones);

    List<Persona> findByUsuarios(Usuario usuarios);

    List<Persona> findByTipoIdentificacion(TipoIdentificacion tipoIdentificacion);

    List<Persona> findByDepartamentoNacimiento(Departamento departamentoNacimiento);

    List<Persona> findByMunicipioNacimiento(Municipio municipioNacimiento);

    List<Persona> findByEstadoCivil(EstadoCivil estadoCivil);

    List<Persona> findByEstadoPersona(EstadoPersona estadoPersona);
}
