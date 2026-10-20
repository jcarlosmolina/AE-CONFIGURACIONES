package com.keralty.aeconfiguraciones.repository;

import com.keralty.aeconfiguraciones.persistence.Causal;
import com.keralty.aeconfiguraciones.persistence.EstadoContrato;
import com.keralty.aeconfiguraciones.persistence.FamiliaNovedad;
import com.keralty.aeconfiguraciones.persistence.Funcionario;
import com.keralty.aeconfiguraciones.persistence.GrupoAsociado;
import com.keralty.aeconfiguraciones.persistence.MotivoExcepcionSolNov;
import com.keralty.aeconfiguraciones.persistence.Novedad;
import com.keralty.aeconfiguraciones.persistence.Periodicidad;
import com.keralty.aeconfiguraciones.persistence.Planes;
import com.keralty.aeconfiguraciones.persistence.ProteccionDatos;
import com.keralty.aeconfiguraciones.persistence.SolicitudNovedad;
import com.keralty.aeconfiguraciones.persistence.TipoIdentificacion;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SolicitudNovedadJpaRepository extends JpaRepository<SolicitudNovedad, Long>, SolicitudNovedadJpaRepositoryCustom {

    List<SolicitudNovedad> findByFamilias(FamiliaNovedad familias);

    List<SolicitudNovedad> findByPlanT(Planes planT);

    List<SolicitudNovedad> findByNovedad(Novedad novedad);

    List<SolicitudNovedad> findByCausal(Causal causal);

    List<SolicitudNovedad> findByProteccionDatos(ProteccionDatos proteccionDatos);

    List<SolicitudNovedad> findByTipoIdentContratante(TipoIdentificacion tipoIdentContratante);

    List<SolicitudNovedad> findByEstadoContrato(EstadoContrato estadoContrato);

    List<SolicitudNovedad> findByGrupoAsociado(GrupoAsociado grupoAsociado);

    List<SolicitudNovedad> findByPeriodicidad(Periodicidad periodicidad);

    List<SolicitudNovedad> findByAgente(Funcionario agente);

    List<SolicitudNovedad> findByPeriodicidadNueva(Periodicidad periodicidadNueva);

    List<SolicitudNovedad> findByMotivoExcepcion(MotivoExcepcionSolNov motivoExcepcion);

    List<SolicitudNovedad> findByUsrAfiliacion(Funcionario usrAfiliacion);
}
