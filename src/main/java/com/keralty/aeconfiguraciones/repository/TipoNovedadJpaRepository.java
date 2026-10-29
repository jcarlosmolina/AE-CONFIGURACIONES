package com.keralty.aeconfiguraciones.repository;

import com.keralty.aeconfiguraciones.persistence.Novedad;
import com.keralty.aeconfiguraciones.persistence.TipoNovedad;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoNovedadJpaRepository extends JpaRepository<TipoNovedad, Long>, TipoNovedadJpaRepositoryCustom {

    List<TipoNovedad> findByNovedades(Novedad novedades);
}
