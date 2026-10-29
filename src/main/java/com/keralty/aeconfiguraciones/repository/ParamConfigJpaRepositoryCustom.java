package com.keralty.aeconfiguraciones.repository;

import com.keralty.aeconfiguraciones.persistence.ParamConfig;
import com.keralty.aeconfiguraciones.sqlutils.SQLParam;
import com.keralty.aeconfiguraciones.sqlutils.SQLSelect;
import java.util.List;

public interface ParamConfigJpaRepositoryCustom {

    List<ParamConfig> solveQuery(SQLSelect query);
    
    List<ParamConfig> solveQuery(SQLSelect query, int pageSize, int pageNumber);
    
    int solveCountQuery(SQLSelect query);

    public List<ParamConfig> executeQuery(String query);

    public List<ParamConfig> executeQuery(String query, List<SQLParam> params);

    void refreshEntity(Object object);
}
