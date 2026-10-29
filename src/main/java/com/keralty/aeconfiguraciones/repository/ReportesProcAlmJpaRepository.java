package com.keralty.aeconfiguraciones.repository;

import com.keralty.aeconfiguraciones.persistence.Funcionario;
import com.keralty.aeconfiguraciones.persistence.ParamReporte;
import com.keralty.aeconfiguraciones.persistence.ReportesProcAlm;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportesProcAlmJpaRepository extends JpaRepository<ReportesProcAlm, Long>, ReportesProcAlmJpaRepositoryCustom {

    List<ReportesProcAlm> findByParametros(ParamReporte parametros);

    List<ReportesProcAlm> findByFuncionarios(Funcionario funcionarios);
}
