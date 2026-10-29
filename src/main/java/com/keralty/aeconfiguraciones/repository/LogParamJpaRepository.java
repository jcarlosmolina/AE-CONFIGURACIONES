package com.keralty.aeconfiguraciones.repository;

import com.keralty.aeconfiguraciones.persistence.LogParam;
import com.keralty.aeconfiguraciones.persistence.ParamConfig;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogParamJpaRepository extends JpaRepository<LogParam, Long>, LogParamJpaRepositoryCustom {

    List<LogParam> findByParamConfig(ParamConfig paramConfig);
}
