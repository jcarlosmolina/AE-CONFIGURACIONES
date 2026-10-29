package com.keralty.aeconfiguraciones.repository;

import com.keralty.aeconfiguraciones.persistence.DirSucursalC;
import com.keralty.aeconfiguraciones.sqlutils.SQLParam;
import com.keralty.aeconfiguraciones.sqlutils.SQLSelect;
import java.util.List;

public interface DirSucursalCJpaRepositoryCustom {

    List<DirSucursalC> solveQuery(SQLSelect query);
    
    List<DirSucursalC> solveQuery(SQLSelect query, int pageSize, int pageNumber);
    
    int solveCountQuery(SQLSelect query);

    public List<DirSucursalC> executeQuery(String query);

    public List<DirSucursalC> executeQuery(String query, List<SQLParam> params);

    void refreshEntity(Object object);
}
