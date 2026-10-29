package com.keralty.aeconfiguraciones.repository;

import com.keralty.aeconfiguraciones.persistence.PreguntaP;
import com.keralty.aeconfiguraciones.persistence.SeccionP;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeccionPJpaRepository extends JpaRepository<SeccionP, Long>, SeccionPJpaRepositoryCustom {

    List<SeccionP> findByPreguntas(PreguntaP preguntas);
}
