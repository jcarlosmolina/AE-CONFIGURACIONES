package com.keralty.aeconfiguraciones.repository;

import com.keralty.aeconfiguraciones.persistence.LogDatosDominio;
import com.keralty.aeconfiguraciones.sqlutils.SQLParam;
import com.keralty.aeconfiguraciones.sqlutils.SQLSelect;
import java.util.List;

public interface LogDatosDominioJpaRepositoryCustom {

    List<LogDatosDominio> solveQuery(SQLSelect query);
    
    List<LogDatosDominio> solveQuery(SQLSelect query, int pageSize, int pageNumber);
    
    int solveCountQuery(SQLSelect query);

    public List<LogDatosDominio> executeQuery(String query);

    public List<LogDatosDominio> executeQuery(String query, List<SQLParam> params);

    void refreshEntity(Object object);
}
