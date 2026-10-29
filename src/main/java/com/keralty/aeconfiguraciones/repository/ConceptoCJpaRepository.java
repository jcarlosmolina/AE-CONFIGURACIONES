package com.keralty.aeconfiguraciones.repository;

import com.keralty.aeconfiguraciones.persistence.BeneficiarioC;
import com.keralty.aeconfiguraciones.persistence.ConceptoC;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConceptoCJpaRepository extends JpaRepository<ConceptoC, Long>, ConceptoCJpaRepositoryCustom {

    List<ConceptoC> findByBeneficiarioC(BeneficiarioC beneficiarioC);
}
