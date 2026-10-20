package com.keralty.aeconfiguraciones.repository;

import com.keralty.aeconfiguraciones.persistence.Planes;
import com.keralty.aeconfiguraciones.persistence.Productos;
import com.keralty.aeconfiguraciones.persistence.TipoIVA;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoIVAJpaRepository extends JpaRepository<TipoIVA, Long>, TipoIVAJpaRepositoryCustom {

    List<TipoIVA> findByProductoT(Productos productoT);

    List<TipoIVA> findByPlanT(Planes planT);
}
