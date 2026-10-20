package com.keralty.aeconfiguraciones.repository;

import com.keralty.aeconfiguraciones.persistence.FechaIniEPS;
import com.keralty.aeconfiguraciones.sqlutils.SQLParam;
import com.keralty.aeconfiguraciones.sqlutils.SQLSelect;
import java.util.List;

public interface FechaIniEPSJpaRepositoryCustom {

    List<FechaIniEPS> solveQuery(SQLSelect query);
    
    List<FechaIniEPS> solveQuery(SQLSelect query, int pageSize, int pageNumber);
    
    int solveCountQuery(SQLSelect query);

    public List<FechaIniEPS> executeQuery(String query);

    public List<FechaIniEPS> executeQuery(String query, List<SQLParam> params);

    void refreshEntity(Object object);
}
