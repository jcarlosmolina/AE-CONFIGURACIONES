package com.keralty.aeconfiguraciones.repository;

import com.keralty.aeconfiguraciones.persistence.ContratoC;
import com.keralty.aeconfiguraciones.persistence.DetalleTarifa;
import com.keralty.aeconfiguraciones.persistence.InstitucionMatNeo;
import com.keralty.aeconfiguraciones.persistence.Planes;
import com.keralty.aeconfiguraciones.persistence.Productos;
import com.keralty.aeconfiguraciones.persistence.Tarifa;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TarifaJpaRepository extends JpaRepository<Tarifa, Long>, TarifaJpaRepositoryCustom {

    List<Tarifa> findByContratosC(ContratoC contratosC);

    List<Tarifa> findByDetallesTarifa(DetalleTarifa detallesTarifa);

    List<Tarifa> findByInstitucionMatNeo(InstitucionMatNeo institucionMatNeo);

    List<Tarifa> findByProductoT(Productos productoT);

    List<Tarifa> findByPlanT(Planes planT);
}
