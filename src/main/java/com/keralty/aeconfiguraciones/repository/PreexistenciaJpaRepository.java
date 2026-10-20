package com.keralty.aeconfiguraciones.repository;

import com.keralty.aeconfiguraciones.persistence.oid.PreexistenciaOid;
import com.keralty.aeconfiguraciones.persistence.Preexistencia;
import com.keralty.aeconfiguraciones.persistence.Usuario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PreexistenciaJpaRepository extends JpaRepository<Preexistencia, PreexistenciaOid>, PreexistenciaJpaRepositoryCustom {

    List<Preexistencia> findByUsuario(Usuario usuario);
}
