package com.keralty.aeconfiguraciones.repository;

import com.keralty.aeconfiguraciones.persistence.Funcionario;
import com.keralty.aeconfiguraciones.persistence.GrupoAsignacion;
import com.keralty.aeconfiguraciones.persistence.ReportesProcAlm;
import com.keralty.aeconfiguraciones.persistence.Rol;
import com.keralty.aeconfiguraciones.persistence.Solicitud;
import com.keralty.aeconfiguraciones.persistence.SolicitudNovedad;
import com.keralty.aeconfiguraciones.persistence.Sucursal;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuncionarioJpaRepository extends JpaRepository<Funcionario, Long>, FuncionarioJpaRepositoryCustom {

    List<Funcionario> findBySolicitudes(Solicitud solicitudes);

    List<Funcionario> findBySolicitudesAfiliacion(Solicitud solicitudesAfiliacion);

    List<Funcionario> findByReportes(ReportesProcAlm reportes);

    List<Funcionario> findBySolicitudesNovedad(SolicitudNovedad solicitudesNovedad);

    List<Funcionario> findBySolNovAifliaciones(SolicitudNovedad solNovAifliaciones);

    List<Funcionario> findByGrupoAsignacion(GrupoAsignacion grupoAsignacion);

    List<Funcionario> findByRol(Rol rol);

    List<Funcionario> findBySucursal(Sucursal sucursal);
}
