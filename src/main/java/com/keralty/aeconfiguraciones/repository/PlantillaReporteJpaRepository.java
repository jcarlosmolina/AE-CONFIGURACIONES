package com.keralty.aeconfiguraciones.repository;

import com.keralty.aeconfiguraciones.persistence.PlantillaReporte;
import com.keralty.aeconfiguraciones.persistence.ReportesDominio;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlantillaReporteJpaRepository extends JpaRepository<PlantillaReporte, Long>, PlantillaReporteJpaRepositoryCustom {

    List<PlantillaReporte> findByReportesDominio(ReportesDominio reportesDominio);
}
