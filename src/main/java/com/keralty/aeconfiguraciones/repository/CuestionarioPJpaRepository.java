package com.keralty.aeconfiguraciones.repository;

import com.keralty.aeconfiguraciones.persistence.CuestionarioP;
import com.keralty.aeconfiguraciones.persistence.Usuario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CuestionarioPJpaRepository extends JpaRepository<CuestionarioP, Long>, CuestionarioPJpaRepositoryCustom {

    List<CuestionarioP> findByUsuario(Usuario usuario);
}
