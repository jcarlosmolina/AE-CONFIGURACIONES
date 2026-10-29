package com.keralty.aeconfiguraciones.repository;

import com.keralty.aeconfiguraciones.persistence.Aesolicitudes;
import com.keralty.aeconfiguraciones.persistence.AgentexProducto;
import com.keralty.aeconfiguraciones.persistence.ContratoC;
import com.keralty.aeconfiguraciones.persistence.Dominio;
import com.keralty.aeconfiguraciones.persistence.GrupoAsociado;
import com.keralty.aeconfiguraciones.persistence.Planes;
import com.keralty.aeconfiguraciones.persistence.Productos;
import com.keralty.aeconfiguraciones.persistence.ProteccionDatos;
import com.keralty.aeconfiguraciones.persistence.Tarifa;
import com.keralty.aeconfiguraciones.persistence.TipoIVA;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductosJpaRepository extends JpaRepository<Productos, Long>, ProductosJpaRepositoryCustom {

    List<Productos> findByPlanes(Planes planes);

    List<Productos> findByDominios(Dominio dominios);

    List<Productos> findByTarifas(Tarifa tarifas);

    List<Productos> findByGruposAsociados(GrupoAsociado gruposAsociados);

    List<Productos> findByTiposIVA(TipoIVA tiposIVA);

    List<Productos> findByAESOLICITUDES(Aesolicitudes aESOLICITUDES);

    List<Productos> findByProteccionesDatos(ProteccionDatos proteccionesDatos);

    List<Productos> findByContratosC(ContratoC contratosC);

    List<Productos> findByAgentexProducto(AgentexProducto agentexProducto);
}
