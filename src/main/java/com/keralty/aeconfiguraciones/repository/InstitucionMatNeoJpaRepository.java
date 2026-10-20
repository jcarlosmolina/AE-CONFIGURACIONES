package com.keralty.aeconfiguraciones.repository;

import com.keralty.aeconfiguraciones.persistence.AnexoMat;
import com.keralty.aeconfiguraciones.persistence.AnexoNeo;
import com.keralty.aeconfiguraciones.persistence.InstitucionMatNeo;
import com.keralty.aeconfiguraciones.persistence.Municipio;
import com.keralty.aeconfiguraciones.persistence.Tarifa;
import com.keralty.aeconfiguraciones.persistence.TipoIdentificacion;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstitucionMatNeoJpaRepository extends JpaRepository<InstitucionMatNeo, String>, InstitucionMatNeoJpaRepositoryCustom {

    List<InstitucionMatNeo> findByAnexoMat(AnexoMat anexoMat);

    List<InstitucionMatNeo> findByAnexoNeo(AnexoNeo anexoNeo);

    List<InstitucionMatNeo> findByTipoIdentificacion(TipoIdentificacion tipoIdentificacion);

    List<InstitucionMatNeo> findByTarifa(Tarifa tarifa);

    List<InstitucionMatNeo> findByMunicipio(Municipio municipio);
}
