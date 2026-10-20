package com.keralty.aeconfiguraciones.repository;

import com.keralty.aeconfiguraciones.persistence.CuestionarioMed;
import com.keralty.aeconfiguraciones.persistence.Seccion;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CuestionarioMedJpaRepository extends JpaRepository<CuestionarioMed, Long>, CuestionarioMedJpaRepositoryCustom {

    List<CuestionarioMed> findBySecciones(Seccion secciones);
}
