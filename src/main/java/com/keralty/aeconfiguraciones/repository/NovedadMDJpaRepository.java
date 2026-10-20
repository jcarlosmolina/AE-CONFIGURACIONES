package com.keralty.aeconfiguraciones.repository;

import com.keralty.aeconfiguraciones.persistence.Departamento;
import com.keralty.aeconfiguraciones.persistence.FamiliaNovedad;
import com.keralty.aeconfiguraciones.persistence.Municipio;
import com.keralty.aeconfiguraciones.persistence.NovedadMD;
import com.keralty.aeconfiguraciones.persistence.Pais;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NovedadMDJpaRepository extends JpaRepository<NovedadMD, Long>, NovedadMDJpaRepositoryCustom {

    List<NovedadMD> findByFamiliaNovedad(FamiliaNovedad familiaNovedad);

    List<NovedadMD> findByPais(Pais pais);

    List<NovedadMD> findByDepartamento(Departamento departamento);

    List<NovedadMD> findByMunicipio(Municipio municipio);
}
