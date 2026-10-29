package com.keralty.aeconfiguraciones.repository;

import com.keralty.aeconfiguraciones.persistence.LogContSinDocSol;
import com.keralty.aeconfiguraciones.sqlutils.SQLParam;
import com.keralty.aeconfiguraciones.sqlutils.SQLSelect;
import java.util.List;

public interface LogContSinDocSolJpaRepositoryCustom {

    List<LogContSinDocSol> solveQuery(SQLSelect query);
    
    List<LogContSinDocSol> solveQuery(SQLSelect query, int pageSize, int pageNumber);
    
    int solveCountQuery(SQLSelect query);

    public List<LogContSinDocSol> executeQuery(String query);

    public List<LogContSinDocSol> executeQuery(String query, List<SQLParam> params);

    void refreshEntity(Object object);
}
