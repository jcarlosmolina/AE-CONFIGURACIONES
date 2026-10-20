package com.keralty.aeconfiguraciones.repository;

import com.keralty.aeconfiguraciones.persistence.BeneficiarioC;
import com.keralty.aeconfiguraciones.persistence.Eps;
import com.keralty.aeconfiguraciones.persistence.Usuario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EpsJpaRepository extends JpaRepository<Eps, Long>, EpsJpaRepositoryCustom {

    List<Eps> findByAtributosUsuario(Usuario atributosUsuario);

    List<Eps> findByBeneficiariosC(BeneficiarioC beneficiariosC);
}
