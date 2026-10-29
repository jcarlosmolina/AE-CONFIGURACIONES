package com.keralty.aeconfiguraciones.repository;

import com.keralty.aeconfiguraciones.persistence.LogParam;
import com.keralty.aeconfiguraciones.sqlutils.SQLParam;
import com.keralty.aeconfiguraciones.sqlutils.SQLSelect;
import java.util.List;

public interface LogParamJpaRepositoryCustom {

    List<LogParam> solveQuery(SQLSelect query);
    
    List<LogParam> solveQuery(SQLSelect query, int pageSize, int pageNumber);
    
    int solveCountQuery(SQLSelect query);

    public List<LogParam> executeQuery(String query);

    public List<LogParam> executeQuery(String query, List<SQLParam> params);

    void refreshEntity(Object object);
}
