package com.keralty.aeconfiguraciones.repository;

import com.keralty.aeconfiguraciones.persistence.AnexoMat;
import com.keralty.aeconfiguraciones.persistence.AnexoNeo;
import com.keralty.aeconfiguraciones.persistence.Companyia;
import com.keralty.aeconfiguraciones.persistence.Eps;
import com.keralty.aeconfiguraciones.persistence.EstadoBeneficiario;
import com.keralty.aeconfiguraciones.persistence.oid.UsuarioOid;
import com.keralty.aeconfiguraciones.persistence.Parentesco;
import com.keralty.aeconfiguraciones.persistence.Persona;
import com.keralty.aeconfiguraciones.persistence.Solicitud;
import com.keralty.aeconfiguraciones.persistence.TipoIdentificacion;
import com.keralty.aeconfiguraciones.persistence.Usuario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioJpaRepository extends JpaRepository<Usuario, UsuarioOid>, UsuarioJpaRepositoryCustom {

    List<Usuario> findByAnexoMat(AnexoMat anexoMat);

    List<Usuario> findByAnexoNeo(AnexoNeo anexoNeo);

    List<Usuario> findBySolicitud(Solicitud solicitud);

    List<Usuario> findByUsuarioPersona(Persona usuarioPersona);

    List<Usuario> findByEps(Eps eps);

    List<Usuario> findByParentesco(Parentesco parentesco);

    List<Usuario> findByCompanyia(Companyia companyia);

    List<Usuario> findByTipoIdentificacion(TipoIdentificacion tipoIdentificacion);

    List<Usuario> findByEstadoBeneficiario(EstadoBeneficiario estadoBeneficiario);
}
