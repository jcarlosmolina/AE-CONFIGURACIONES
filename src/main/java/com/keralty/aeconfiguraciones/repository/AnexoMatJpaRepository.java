package com.keralty.aeconfiguraciones.repository;

import com.keralty.aeconfiguraciones.persistence.AnexoMat;
import com.keralty.aeconfiguraciones.persistence.InstitucionMatNeo;
import com.keralty.aeconfiguraciones.persistence.Periodicidad;
import com.keralty.aeconfiguraciones.persistence.Usuario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnexoMatJpaRepository extends JpaRepository<AnexoMat, Long>, AnexoMatJpaRepositoryCustom {

    List<AnexoMat> findByUsuario(Usuario usuario);

    List<AnexoMat> findByPeriodicidad(Periodicidad periodicidad);

    List<AnexoMat> findByInstitucionMaternal(InstitucionMatNeo institucionMaternal);
}
