package com.keralty.aeconfiguraciones.repository;

import com.keralty.aeconfiguraciones.persistence.DetalleTarifa;
import com.keralty.aeconfiguraciones.persistence.Tarifa;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetalleTarifaJpaRepository extends JpaRepository<DetalleTarifa, Long>, DetalleTarifaJpaRepositoryCustom {

    List<DetalleTarifa> findByTarifa(Tarifa tarifa);
}
