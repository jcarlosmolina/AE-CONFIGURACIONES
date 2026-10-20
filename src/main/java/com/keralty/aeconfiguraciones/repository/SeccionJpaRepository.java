package com.keralty.aeconfiguraciones.repository;

import com.keralty.aeconfiguraciones.persistence.CuestionarioMed;
import com.keralty.aeconfiguraciones.persistence.Pregunta;
import com.keralty.aeconfiguraciones.persistence.Seccion;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeccionJpaRepository extends JpaRepository<Seccion, Long>, SeccionJpaRepositoryCustom {

    List<Seccion> findByPreguntas(Pregunta preguntas);

    List<Seccion> findByCuestionario(CuestionarioMed cuestionario);
}
