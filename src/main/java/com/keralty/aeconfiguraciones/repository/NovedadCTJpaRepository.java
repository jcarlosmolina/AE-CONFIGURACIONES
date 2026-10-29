package com.keralty.aeconfiguraciones.repository;

import com.keralty.aeconfiguraciones.persistence.Departamento;
import com.keralty.aeconfiguraciones.persistence.FamiliaNovedad;
import com.keralty.aeconfiguraciones.persistence.Municipio;
import com.keralty.aeconfiguraciones.persistence.NovedadCT;
import com.keralty.aeconfiguraciones.persistence.Pais;
import com.keralty.aeconfiguraciones.persistence.TipoIdentificacion;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NovedadCTJpaRepository extends JpaRepository<NovedadCT, Long>, NovedadCTJpaRepositoryCustom {

    List<NovedadCT> findByFamiliaNovedad(FamiliaNovedad familiaNovedad);

    List<NovedadCT> findByTipoIdentificacion(TipoIdentificacion tipoIdentificacion);

    List<NovedadCT> findByMunicipioNac(Municipio municipioNac);

    List<NovedadCT> findByMunicipioRes(Municipio municipioRes);

    List<NovedadCT> findByDepartamentoNac(Departamento departamentoNac);

    List<NovedadCT> findByDepartamentoRes(Departamento departamentoRes);

    List<NovedadCT> findByPais(Pais pais);
}
