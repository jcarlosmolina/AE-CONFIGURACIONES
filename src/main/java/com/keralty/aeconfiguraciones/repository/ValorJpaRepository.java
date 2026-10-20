package com.keralty.aeconfiguraciones.repository;

import com.keralty.aeconfiguraciones.persistence.Pregunta;
import com.keralty.aeconfiguraciones.persistence.Valor;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ValorJpaRepository extends JpaRepository<Valor, Long>, ValorJpaRepositoryCustom {

    List<Valor> findByPreguntas(Pregunta preguntas);
}
