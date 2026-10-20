package com.keralty.aeconfiguraciones.repository;

import com.keralty.aeconfiguraciones.persistence.Pregunta;
import com.keralty.aeconfiguraciones.persistence.PreguntaP;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PreguntaPJpaRepository extends JpaRepository<PreguntaP, Long>, PreguntaPJpaRepositoryCustom {

    List<PreguntaP> findByPregunta(Pregunta pregunta);
}
