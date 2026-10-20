package com.keralty.aeconfiguraciones.repository;

import com.keralty.aeconfiguraciones.persistence.Aesolicitudes;
import com.keralty.aeconfiguraciones.persistence.Afiliaciones;
import com.keralty.aeconfiguraciones.persistence.ContratoC;
import com.keralty.aeconfiguraciones.persistence.Dominio;
import com.keralty.aeconfiguraciones.persistence.GrupoAsociado;
import com.keralty.aeconfiguraciones.persistence.Planes;
import com.keralty.aeconfiguraciones.persistence.Productos;
import com.keralty.aeconfiguraciones.persistence.SolicitudNovedad;
import com.keralty.aeconfiguraciones.persistence.Tarifa;
import com.keralty.aeconfiguraciones.persistence.TipoIVA;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanesJpaRepository extends JpaRepository<Planes, Long>, PlanesJpaRepositoryCustom {

    List<Planes> findByDominios(Dominio dominios);

    List<Planes> findByTarifas(Tarifa tarifas);

    List<Planes> findByGruposAsociados(GrupoAsociado gruposAsociados);

    List<Planes> findByTiposIVA(TipoIVA tiposIVA);

    List<Planes> findByAESOLICITUDES(Aesolicitudes aESOLICITUDES);

    List<Planes> findByContratosC(ContratoC contratosC);

    List<Planes> findByAfiliaciones(Afiliaciones afiliaciones);

    List<Planes> findBySolicitudesNovedad(SolicitudNovedad solicitudesNovedad);

    List<Planes> findByProductoT(Productos productoT);
}
