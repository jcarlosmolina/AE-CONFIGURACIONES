package com.keralty.aeconfiguraciones.repository;

import com.keralty.aeconfiguraciones.persistence.DirSucursalC;
import com.keralty.aeconfiguraciones.persistence.TipoSucursalC;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoSucursalCJpaRepository extends JpaRepository<TipoSucursalC, Long>, TipoSucursalCJpaRepositoryCustom {

    List<TipoSucursalC> findByDirSucursalesC(DirSucursalC dirSucursalesC);
}
