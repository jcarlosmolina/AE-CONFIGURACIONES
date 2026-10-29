package com.keralty.aeconfiguraciones.repository;

import com.keralty.aeconfiguraciones.persistence.LogContratosExentosFIngreso;
import com.keralty.aeconfiguraciones.sqlutils.SQLParam;
import com.keralty.aeconfiguraciones.sqlutils.SQLSelect;
import java.util.List;

public interface LogContratosExentosFIngresoJpaRepositoryCustom {

    List<LogContratosExentosFIngreso> solveQuery(SQLSelect query);
    
    List<LogContratosExentosFIngreso> solveQuery(SQLSelect query, int pageSize, int pageNumber);
    
    int solveCountQuery(SQLSelect query);

    public List<LogContratosExentosFIngreso> executeQuery(String query);

    public List<LogContratosExentosFIngreso> executeQuery(String query, List<SQLParam> params);

    void refreshEntity(Object object);
}
