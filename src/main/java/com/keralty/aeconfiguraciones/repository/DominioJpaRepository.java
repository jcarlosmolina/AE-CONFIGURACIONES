package com.keralty.aeconfiguraciones.repository;

import com.keralty.aeconfiguraciones.persistence.AgentexProducto;
import com.keralty.aeconfiguraciones.persistence.ConfiguracionANS;
import com.keralty.aeconfiguraciones.persistence.Dominio;
import com.keralty.aeconfiguraciones.persistence.FormaPago;
import com.keralty.aeconfiguraciones.persistence.GrupoAsignacion;
import com.keralty.aeconfiguraciones.persistence.GrupoAsignacionNovedad;
import com.keralty.aeconfiguraciones.persistence.LogDatosDominio;
import com.keralty.aeconfiguraciones.persistence.LogGruposUsuarios;
import com.keralty.aeconfiguraciones.persistence.LogIncompatibilidades;
import com.keralty.aeconfiguraciones.persistence.Novedad;
import com.keralty.aeconfiguraciones.persistence.ParentescoDom;
import com.keralty.aeconfiguraciones.persistence.Periodicidad;
import com.keralty.aeconfiguraciones.persistence.Planes;
import com.keralty.aeconfiguraciones.persistence.PlantillaEmail;
import com.keralty.aeconfiguraciones.persistence.Productos;
import com.keralty.aeconfiguraciones.persistence.Prorrateo;
import com.keralty.aeconfiguraciones.persistence.ReportesDominio;
import com.keralty.aeconfiguraciones.persistence.Rol;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DominioJpaRepository extends JpaRepository<Dominio, Long>, DominioJpaRepositoryCustom {

    List<Dominio> findByDominiosConIncompatibilidad(Dominio dominiosConIncompatibilidad);

    List<Dominio> findByParentescosDom(ParentescoDom parentescosDom);

    List<Dominio> findByAgentexProducto(AgentexProducto agentexProducto);

    List<Dominio> findByProrrateos(Prorrateo prorrateos);

    List<Dominio> findByLogGruposUsuarios(LogGruposUsuarios logGruposUsuarios);

    List<Dominio> findByGrupoAsignacionNovedad(GrupoAsignacionNovedad grupoAsignacionNovedad);

    List<Dominio> findByConfiguracionesANS(ConfiguracionANS configuracionesANS);

    List<Dominio> findByReportesDominio(ReportesDominio reportesDominio);

    List<Dominio> findByLogIncompatibilidades(LogIncompatibilidades logIncompatibilidades);

    List<Dominio> findByLogDatosDominio(LogDatosDominio logDatosDominio);

    List<Dominio> findByPlanT(Planes planT);

    List<Dominio> findByProductoT(Productos productoT);

    List<Dominio> findByPeriodicidades(Periodicidad periodicidades);

    List<Dominio> findByRoles(Rol roles);

    List<Dominio> findByFormasPago(FormaPago formasPago);

    List<Dominio> findByPlantillas(PlantillaEmail plantillas);

    List<Dominio> findByNovedades(Novedad novedades);

    List<Dominio> findByGrupoAsesores(GrupoAsignacion grupoAsesores);

    List<Dominio> findByGrupoAreaMedica(GrupoAsignacion grupoAreaMedica);

    List<Dominio> findByDominiosIncompatibles(Dominio dominiosIncompatibles);
}
