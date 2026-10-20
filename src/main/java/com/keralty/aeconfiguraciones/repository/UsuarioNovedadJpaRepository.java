package com.keralty.aeconfiguraciones.repository;

import com.keralty.aeconfiguraciones.persistence.EstadoBeneficiario;
import com.keralty.aeconfiguraciones.persistence.FamiliaNovedad;
import com.keralty.aeconfiguraciones.persistence.Parentesco;
import com.keralty.aeconfiguraciones.persistence.TipoIdentificacion;
import com.keralty.aeconfiguraciones.persistence.UsuarioNovedad;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioNovedadJpaRepository extends JpaRepository<UsuarioNovedad, Long>, UsuarioNovedadJpaRepositoryCustom {

    List<UsuarioNovedad> findByFamiliaNovedad(FamiliaNovedad familiaNovedad);

    List<UsuarioNovedad> findByTipoIdentificacion(TipoIdentificacion tipoIdentificacion);

    List<UsuarioNovedad> findByParentesco(Parentesco parentesco);

    List<UsuarioNovedad> findByEstadoBeneficiario(EstadoBeneficiario estadoBeneficiario);

    List<UsuarioNovedad> findByParentescoNuevo(Parentesco parentescoNuevo);

    List<UsuarioNovedad> findByTipoIdentificacionCD(TipoIdentificacion tipoIdentificacionCD);
}
