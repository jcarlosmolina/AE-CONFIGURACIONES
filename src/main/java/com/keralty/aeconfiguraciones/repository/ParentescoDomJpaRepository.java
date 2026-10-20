package com.keralty.aeconfiguraciones.repository;

import com.keralty.aeconfiguraciones.persistence.Dominio;
import com.keralty.aeconfiguraciones.persistence.oid.ParentescoDomOid;
import com.keralty.aeconfiguraciones.persistence.Parentesco;
import com.keralty.aeconfiguraciones.persistence.ParentescoDom;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParentescoDomJpaRepository extends JpaRepository<ParentescoDom, ParentescoDomOid>, ParentescoDomJpaRepositoryCustom {

    List<ParentescoDom> findByDominio(Dominio dominio);

    List<ParentescoDom> findByParentesco(Parentesco parentesco);
}
