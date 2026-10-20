package com.keralty.aeconfiguraciones.repository;

import com.keralty.aeconfiguraciones.persistence.Direccion;
import com.keralty.aeconfiguraciones.persistence.DireccionC;
import com.keralty.aeconfiguraciones.persistence.TipoDireccion;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoDireccionJpaRepository extends JpaRepository<TipoDireccion, Long>, TipoDireccionJpaRepositoryCustom {

    List<TipoDireccion> findByDireccionesC(DireccionC direccionesC);

    List<TipoDireccion> findByDirecciones(Direccion direcciones);
}
