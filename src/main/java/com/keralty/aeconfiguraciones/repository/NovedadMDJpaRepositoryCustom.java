package com.keralty.aeconfiguraciones.repository;

import com.keralty.aeconfiguraciones.persistence.NovedadMD;
import com.keralty.aeconfiguraciones.sqlutils.SQLParam;
import com.keralty.aeconfiguraciones.sqlutils.SQLSelect;
import java.util.List;

public interface NovedadMDJpaRepositoryCustom {

    List<NovedadMD> solveQuery(SQLSelect query);
    
    List<NovedadMD> solveQuery(SQLSelect query, int pageSize, int pageNumber);
    
    int solveCountQuery(SQLSelect query);

    public List<NovedadMD> executeQuery(String query);

    public List<NovedadMD> executeQuery(String query, List<SQLParam> params);

    void refreshEntity(Object object);
}
