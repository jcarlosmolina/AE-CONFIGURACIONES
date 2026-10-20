package com.keralty.aeconfiguraciones.repository;

import com.keralty.aeconfiguraciones.persistence.BeneficiarioC;
import com.keralty.aeconfiguraciones.persistence.EstadoBeneficiario;
import com.keralty.aeconfiguraciones.persistence.EstadoBeneficiarioAE;
import com.keralty.aeconfiguraciones.persistence.Usuario;
import com.keralty.aeconfiguraciones.persistence.UsuarioNovedad;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstadoBeneficiarioJpaRepository extends JpaRepository<EstadoBeneficiario, Long>, EstadoBeneficiarioJpaRepositoryCustom {

    List<EstadoBeneficiario> findByEstadoBeneficiarioAE(EstadoBeneficiarioAE estadoBeneficiarioAE);

    List<EstadoBeneficiario> findByUsuarioS(Usuario usuarioS);

    List<EstadoBeneficiario> findByBeneficiariosC(BeneficiarioC beneficiariosC);

    List<EstadoBeneficiario> findByUsuariosNovedad(UsuarioNovedad usuariosNovedad);
}
