package com.keralty.aeconfiguraciones.repository;

import com.keralty.aeconfiguraciones.persistence.AnexoC;
import com.keralty.aeconfiguraciones.persistence.BeneficiarioC;
import com.keralty.aeconfiguraciones.persistence.Companyia;
import com.keralty.aeconfiguraciones.persistence.ContratoC;
import com.keralty.aeconfiguraciones.persistence.Eps;
import com.keralty.aeconfiguraciones.persistence.EstadoBeneficiario;
import com.keralty.aeconfiguraciones.persistence.FamiliaC;
import com.keralty.aeconfiguraciones.persistence.oid.BeneficiarioCOid;
import com.keralty.aeconfiguraciones.persistence.Parentesco;
import com.keralty.aeconfiguraciones.persistence.PersonaC;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BeneficiarioCJpaRepository extends JpaRepository<BeneficiarioC, BeneficiarioCOid>, BeneficiarioCJpaRepositoryCustom {

    List<BeneficiarioC> findByPersonaC(PersonaC personaC);

    List<BeneficiarioC> findByAnexosC(AnexoC anexosC);

    List<BeneficiarioC> findByContratoC(ContratoC contratoC);

    List<BeneficiarioC> findByFamiliasC(FamiliaC familiasC);

    List<BeneficiarioC> findByEPSC(Eps ePSC);

    List<BeneficiarioC> findByCompanyiaMPC(Companyia companyiaMPC);

    List<BeneficiarioC> findByParentescoC(Parentesco parentescoC);

    List<BeneficiarioC> findByEstadoBeneficiarioC(EstadoBeneficiario estadoBeneficiarioC);
}
