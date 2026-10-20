package com.keralty.aeconfiguraciones.repository;

import com.keralty.aeconfiguraciones.persistence.DirSucursalC;
import com.keralty.aeconfiguraciones.persistence.Regional;
import com.keralty.aeconfiguraciones.persistence.RespRegional;
import com.keralty.aeconfiguraciones.persistence.Sucursal;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegionalJpaRepository extends JpaRepository<Regional, Long>, RegionalJpaRepositoryCustom {

    List<Regional> findByRespRegional(RespRegional respRegional);

    List<Regional> findBySucursales(Sucursal sucursales);

    List<Regional> findByDirSucursalC(DirSucursalC dirSucursalC);
}
