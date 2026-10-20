package com.keralty.aeconfiguraciones.repository;

import com.keralty.aeconfiguraciones.persistence.BeneficiarioC;
import com.keralty.aeconfiguraciones.persistence.ContratoC;
import com.keralty.aeconfiguraciones.persistence.FamiliaC;
import com.keralty.aeconfiguraciones.persistence.Municipio;
import com.keralty.aeconfiguraciones.persistence.oid.FamiliaCOid;
import com.keralty.aeconfiguraciones.persistence.PersonaC;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FamiliaCJpaRepository extends JpaRepository<FamiliaC, FamiliaCOid>, FamiliaCJpaRepositoryCustom {

    List<FamiliaC> findByPersonaCTitular(PersonaC personaCTitular);

    List<FamiliaC> findByBeneficiariosC(BeneficiarioC beneficiariosC);

    List<FamiliaC> findByContratoC(ContratoC contratoC);

    List<FamiliaC> findByMunicipioC(Municipio municipioC);
}
