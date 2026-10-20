package com.keralty.aeconfiguraciones.repository;

import com.keralty.aeconfiguraciones.persistence.ContSinDocSol;
import com.keralty.aeconfiguraciones.sqlutils.SQLParam;
import com.keralty.aeconfiguraciones.sqlutils.SQLSelect;
import java.util.List;

public interface ContSinDocSolJpaRepositoryCustom {

    List<ContSinDocSol> solveQuery(SQLSelect query);
    
    List<ContSinDocSol> solveQuery(SQLSelect query, int pageSize, int pageNumber);
    
    int solveCountQuery(SQLSelect query);

    public List<ContSinDocSol> executeQuery(String query);

    public List<ContSinDocSol> executeQuery(String query, List<SQLParam> params);

    void refreshEntity(Object object);
}
