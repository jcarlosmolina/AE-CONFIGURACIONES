package com.keralty.aeconfiguraciones.repository;

import com.keralty.aeconfiguraciones.persistence.Dominio;
import com.keralty.aeconfiguraciones.persistence.oid.ProrrateoOid;
import com.keralty.aeconfiguraciones.persistence.Prorrateo;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProrrateoJpaRepository extends JpaRepository<Prorrateo, ProrrateoOid>, ProrrateoJpaRepositoryCustom {

    List<Prorrateo> findByDominio(Dominio dominio);
}
