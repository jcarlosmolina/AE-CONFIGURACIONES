package com.keralty.aeconfiguraciones.repository;

import com.keralty.aeconfiguraciones.persistence.LogPriorizacionSol;
import com.keralty.aeconfiguraciones.sqlutils.SQLParam;
import com.keralty.aeconfiguraciones.sqlutils.SQLSelect;
import java.util.List;

public interface LogPriorizacionSolJpaRepositoryCustom {

    List<LogPriorizacionSol> solveQuery(SQLSelect query);
    
    List<LogPriorizacionSol> solveQuery(SQLSelect query, int pageSize, int pageNumber);
    
    int solveCountQuery(SQLSelect query);

    public List<LogPriorizacionSol> executeQuery(String query);

    public List<LogPriorizacionSol> executeQuery(String query, List<SQLParam> params);

    void refreshEntity(Object object);
}
