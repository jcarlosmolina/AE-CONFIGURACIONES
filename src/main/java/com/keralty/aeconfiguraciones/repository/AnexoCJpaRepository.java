package com.keralty.aeconfiguraciones.repository;

import com.keralty.aeconfiguraciones.persistence.AnexoC;
import com.keralty.aeconfiguraciones.persistence.BeneficiarioC;
import com.keralty.aeconfiguraciones.persistence.Periodicidad;
import com.keralty.aeconfiguraciones.persistence.TipoIdentificacion;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnexoCJpaRepository extends JpaRepository<AnexoC, Long>, AnexoCJpaRepositoryCustom {

    List<AnexoC> findByBeneficiarioC(BeneficiarioC beneficiarioC);

    List<AnexoC> findByPeriodicidadC(Periodicidad periodicidadC);

    List<AnexoC> findByTipoIdentificacionC(TipoIdentificacion tipoIdentificacionC);
}
