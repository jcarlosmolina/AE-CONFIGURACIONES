package com.keralty.aeconfiguraciones.repository;

import com.keralty.aeconfiguraciones.persistence.ContratoC;
import com.keralty.aeconfiguraciones.persistence.Funcionario;
import com.keralty.aeconfiguraciones.persistence.Regional;
import com.keralty.aeconfiguraciones.persistence.Sucursal;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SucursalJpaRepository extends JpaRepository<Sucursal, Long>, SucursalJpaRepositoryCustom {

    List<Sucursal> findByAgentes(Funcionario agentes);

    List<Sucursal> findByContratosC(ContratoC contratosC);

    List<Sucursal> findByRegional(Regional regional);
}
