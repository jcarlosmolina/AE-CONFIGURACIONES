package com.keralty.aeconfiguraciones.repository;

import com.keralty.aeconfiguraciones.persistence.Dominio;
import com.keralty.aeconfiguraciones.persistence.oid.ReportesDominioOid;
import com.keralty.aeconfiguraciones.persistence.PlantillaReporte;
import com.keralty.aeconfiguraciones.persistence.ReportesDominio;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportesDominioJpaRepository extends JpaRepository<ReportesDominio, ReportesDominioOid>, ReportesDominioJpaRepositoryCustom {

    List<ReportesDominio> findByDominio(Dominio dominio);

    List<ReportesDominio> findByPlantillaReporte(PlantillaReporte plantillaReporte);
}
