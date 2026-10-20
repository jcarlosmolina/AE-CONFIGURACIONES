package com.keralty.aeconfiguraciones.repository;

import com.keralty.aeconfiguraciones.persistence.FamiliaNovedad;
import com.keralty.aeconfiguraciones.persistence.SolicitudNovedad;
import com.keralty.aeconfiguraciones.persistence.TipoIdentificacion;
import com.keralty.aeconfiguraciones.persistence.UsuarioNovedad;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FamiliaNovedadJpaRepository extends JpaRepository<FamiliaNovedad, Long>, FamiliaNovedadJpaRepositoryCustom {

    List<FamiliaNovedad> findByUsuarioNovedad(UsuarioNovedad usuarioNovedad);

    List<FamiliaNovedad> findBySolicitud(SolicitudNovedad solicitud);

    List<FamiliaNovedad> findByTipoIdentificacion(TipoIdentificacion tipoIdentificacion);
}
