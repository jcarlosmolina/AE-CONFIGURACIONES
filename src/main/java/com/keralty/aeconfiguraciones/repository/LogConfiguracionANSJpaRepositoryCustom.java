package com.keralty.aeconfiguraciones.repository;

import com.keralty.aeconfiguraciones.persistence.LogConfiguracionANS;
import com.keralty.aeconfiguraciones.sqlutils.SQLParam;
import com.keralty.aeconfiguraciones.sqlutils.SQLSelect;
import java.util.List;

public interface LogConfiguracionANSJpaRepositoryCustom {

    List<LogConfiguracionANS> solveQuery(SQLSelect query);
    
    List<LogConfiguracionANS> solveQuery(SQLSelect query, int pageSize, int pageNumber);
    
    int solveCountQuery(SQLSelect query);

    public List<LogConfiguracionANS> executeQuery(String query);

    public List<LogConfiguracionANS> executeQuery(String query, List<SQLParam> params);

    void refreshEntity(Object object);
}
