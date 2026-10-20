package com.keralty.aeconfiguraciones.repository;

import com.keralty.aeconfiguraciones.persistence.Afiliaciones;
import com.keralty.aeconfiguraciones.persistence.Planes;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AfiliacionesJpaRepository extends JpaRepository<Afiliaciones, Long>, AfiliacionesJpaRepositoryCustom {

    List<Afiliaciones> findByPlanes(Planes planes);
}
