package com.keralty.aeconfiguraciones.repository;

import com.keralty.aeconfiguraciones.persistence.EstadoBeneficiario;
import com.keralty.aeconfiguraciones.persistence.EstadoBeneficiarioAE;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstadoBeneficiarioAEJpaRepository extends JpaRepository<EstadoBeneficiarioAE, Long>, EstadoBeneficiarioAEJpaRepositoryCustom {

    List<EstadoBeneficiarioAE> findByEstadoBeneficiarioCORE(EstadoBeneficiario estadoBeneficiarioCORE);
}
