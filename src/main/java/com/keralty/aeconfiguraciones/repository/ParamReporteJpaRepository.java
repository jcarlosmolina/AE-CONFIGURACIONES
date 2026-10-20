package com.keralty.aeconfiguraciones.repository;

import com.keralty.aeconfiguraciones.persistence.ParamReporte;
import com.keralty.aeconfiguraciones.persistence.ReportesProcAlm;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParamReporteJpaRepository extends JpaRepository<ParamReporte, Long>, ParamReporteJpaRepositoryCustom {

    List<ParamReporte> findByReporte(ReportesProcAlm reporte);
}
