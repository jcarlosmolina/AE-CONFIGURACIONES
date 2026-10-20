package com.keralty.aeconfiguraciones.repository;

import com.keralty.aeconfiguraciones.persistence.NovedadCT;
import com.keralty.aeconfiguraciones.sqlutils.SQLParam;
import com.keralty.aeconfiguraciones.sqlutils.SQLSelect;
import java.util.List;

public interface NovedadCTJpaRepositoryCustom {

    List<NovedadCT> solveQuery(SQLSelect query);
    
    List<NovedadCT> solveQuery(SQLSelect query, int pageSize, int pageNumber);
    
    int solveCountQuery(SQLSelect query);

    public List<NovedadCT> executeQuery(String query);

    public List<NovedadCT> executeQuery(String query, List<SQLParam> params);

    void refreshEntity(Object object);
}
