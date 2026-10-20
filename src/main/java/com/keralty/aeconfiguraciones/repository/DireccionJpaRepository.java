package com.keralty.aeconfiguraciones.repository;

import com.keralty.aeconfiguraciones.persistence.Departamento;
import com.keralty.aeconfiguraciones.persistence.Direccion;
import com.keralty.aeconfiguraciones.persistence.Municipio;
import com.keralty.aeconfiguraciones.persistence.Pais;
import com.keralty.aeconfiguraciones.persistence.Persona;
import com.keralty.aeconfiguraciones.persistence.Solicitud;
import com.keralty.aeconfiguraciones.persistence.TipoDireccion;
import com.keralty.aeconfiguraciones.persistence.Titular;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DireccionJpaRepository extends JpaRepository<Direccion, Long>, DireccionJpaRepositoryCustom {

    List<Direccion> findByPersona(Persona persona);

    List<Direccion> findByTipoDireccion(TipoDireccion tipoDireccion);

    List<Direccion> findByMunicipio(Municipio municipio);

    List<Direccion> findByDepartamento(Departamento departamento);

    List<Direccion> findByPais(Pais pais);

    List<Direccion> findByContrato(Solicitud contrato);

    List<Direccion> findByTitular(Titular titular);
}
