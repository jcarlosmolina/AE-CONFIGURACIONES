package com.keralty.aeconfiguraciones.repository;

import com.keralty.aeconfiguraciones.persistence.ContratosExentosFIngreso;
import com.keralty.aeconfiguraciones.sqlutils.SQLParam;
import com.keralty.aeconfiguraciones.sqlutils.SQLSelect;
import java.util.List;

public interface ContratosExentosFIngresoJpaRepositoryCustom {

    List<ContratosExentosFIngreso> solveQuery(SQLSelect query);
    
    List<ContratosExentosFIngreso> solveQuery(SQLSelect query, int pageSize, int pageNumber);
    
    int solveCountQuery(SQLSelect query);

    public List<ContratosExentosFIngreso> executeQuery(String query);

    public List<ContratosExentosFIngreso> executeQuery(String query, List<SQLParam> params);

    void refreshEntity(Object object);
}
