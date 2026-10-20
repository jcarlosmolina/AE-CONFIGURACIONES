package com.keralty.aeconfiguraciones.repository;

import com.keralty.aeconfiguraciones.persistence.Pregunta;
import com.keralty.aeconfiguraciones.persistence.PreguntaP;
import com.keralty.aeconfiguraciones.persistence.Seccion;
import com.keralty.aeconfiguraciones.persistence.Valor;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PreguntaJpaRepository extends JpaRepository<Pregunta, Long>, PreguntaJpaRepositoryCustom {

    List<Pregunta> findByValores(Valor valores);

    List<Pregunta> findByPreguntasPersona(PreguntaP preguntasPersona);

    List<Pregunta> findBySeccion(Seccion seccion);
}
