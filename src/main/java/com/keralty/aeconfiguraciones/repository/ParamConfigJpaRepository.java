package com.keralty.aeconfiguraciones.repository;

import com.keralty.aeconfiguraciones.persistence.LogParam;
import com.keralty.aeconfiguraciones.persistence.ParamConfig;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParamConfigJpaRepository extends JpaRepository<ParamConfig, String>, ParamConfigJpaRepositoryCustom {

    List<ParamConfig> findByLogs(LogParam logs);
}
