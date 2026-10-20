package com.keralty.aeconfiguraciones.repository;

import com.keralty.aeconfiguraciones.persistence.AnexoC;
import com.keralty.aeconfiguraciones.persistence.Asesor;
import com.keralty.aeconfiguraciones.persistence.Contratante;
import com.keralty.aeconfiguraciones.persistence.FamiliaNovedad;
import com.keralty.aeconfiguraciones.persistence.InstitucionMatNeo;
import com.keralty.aeconfiguraciones.persistence.Persona;
import com.keralty.aeconfiguraciones.persistence.PersonaC;
import com.keralty.aeconfiguraciones.persistence.SolicitudNovedad;
import com.keralty.aeconfiguraciones.persistence.TipoIdentificacion;
import com.keralty.aeconfiguraciones.persistence.Titular;
import com.keralty.aeconfiguraciones.persistence.Usuario;
import com.keralty.aeconfiguraciones.persistence.UsuarioNovedad;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoIdentificacionJpaRepository extends JpaRepository<TipoIdentificacion, Long>, TipoIdentificacionJpaRepositoryCustom {

    List<TipoIdentificacion> findByAsesores(Asesor asesores);

    List<TipoIdentificacion> findByPersonas(Persona personas);

    List<TipoIdentificacion> findByPersonasC(PersonaC personasC);

    List<TipoIdentificacion> findByUsuarioS(Usuario usuarioS);

    List<TipoIdentificacion> findByContratantes(Contratante contratantes);

    List<TipoIdentificacion> findByAnexosC(AnexoC anexosC);

    List<TipoIdentificacion> findByTitulares(Titular titulares);

    List<TipoIdentificacion> findByInstitucionMatNeo(InstitucionMatNeo institucionMatNeo);

    List<TipoIdentificacion> findBySolicitudesNovedad(SolicitudNovedad solicitudesNovedad);

    List<TipoIdentificacion> findByFamiliasNovedad(FamiliaNovedad familiasNovedad);

    List<TipoIdentificacion> findByUsuariosNovedad(UsuarioNovedad usuariosNovedad);

    List<TipoIdentificacion> findByUsuarioNovedad(UsuarioNovedad usuarioNovedad);
}
