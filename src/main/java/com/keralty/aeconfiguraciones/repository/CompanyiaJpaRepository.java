package com.keralty.aeconfiguraciones.repository;

import com.keralty.aeconfiguraciones.persistence.BeneficiarioC;
import com.keralty.aeconfiguraciones.persistence.Companyia;
import com.keralty.aeconfiguraciones.persistence.Usuario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyiaJpaRepository extends JpaRepository<Companyia, Long>, CompanyiaJpaRepositoryCustom {

    List<Companyia> findByAtributosUsuario(Usuario atributosUsuario);

    List<Companyia> findByBeneficiariosC(BeneficiarioC beneficiariosC);
}
