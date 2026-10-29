package com.keralty.aeconfiguraciones.repository;

import com.keralty.aeconfiguraciones.persistence.Contratante;
import com.keralty.aeconfiguraciones.persistence.Departamento;
import com.keralty.aeconfiguraciones.persistence.Municipio;
import com.keralty.aeconfiguraciones.persistence.Solicitud;
import com.keralty.aeconfiguraciones.persistence.TipoIdentificacion;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContratanteJpaRepository extends JpaRepository<Contratante, Long>, ContratanteJpaRepositoryCustom {

    List<Contratante> findBySolicitud(Solicitud solicitud);

    List<Contratante> findByTipoIdentificacion(TipoIdentificacion tipoIdentificacion);

    List<Contratante> findByDepartamentoNac(Departamento departamentoNac);

    List<Contratante> findByMunicipioNac(Municipio municipioNac);

    List<Contratante> findByDepartamentoRes(Departamento departamentoRes);

    List<Contratante> findByMunicipioRes(Municipio municipioRes);
}
