package com.keralty.aeconfiguraciones.repository;

import com.keralty.aeconfiguraciones.persistence.Direccion;
import com.keralty.aeconfiguraciones.persistence.DireccionC;
import com.keralty.aeconfiguraciones.persistence.DirSucursalC;
import com.keralty.aeconfiguraciones.persistence.Pais;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaisJpaRepository extends JpaRepository<Pais, String>, PaisJpaRepositoryCustom {

    List<Pais> findByDireccionesC(DireccionC direccionesC);

    List<Pais> findByDirecciones(Direccion direcciones);

    List<Pais> findByDirSucursalC(DirSucursalC dirSucursalC);
}
