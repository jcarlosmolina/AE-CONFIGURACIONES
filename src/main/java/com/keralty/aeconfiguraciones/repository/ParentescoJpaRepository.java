package com.keralty.aeconfiguraciones.repository;

import com.keralty.aeconfiguraciones.persistence.BeneficiarioC;
import com.keralty.aeconfiguraciones.persistence.Parentesco;
import com.keralty.aeconfiguraciones.persistence.ParentescoDom;
import com.keralty.aeconfiguraciones.persistence.Usuario;
import com.keralty.aeconfiguraciones.persistence.UsuarioNovedad;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParentescoJpaRepository extends JpaRepository<Parentesco, Long>, ParentescoJpaRepositoryCustom {

    List<Parentesco> findByAtributosUsuario(Usuario atributosUsuario);

    List<Parentesco> findByBeneficiariosC(BeneficiarioC beneficiariosC);

    List<Parentesco> findByParentescosDom(ParentescoDom parentescosDom);

    List<Parentesco> findByUsuariosNovedad(UsuarioNovedad usuariosNovedad);

    List<Parentesco> findByUsuarioNovedad(UsuarioNovedad usuarioNovedad);
}
