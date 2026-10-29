package com.keralty.aeconfiguraciones.repository;

import com.keralty.aeconfiguraciones.persistence.Direccion;
import com.keralty.aeconfiguraciones.sqlutils.SQLParam;
import com.keralty.aeconfiguraciones.sqlutils.SQLSelect;
import java.util.List;

public interface DireccionJpaRepositoryCustom {

    List<Direccion> solveQuery(SQLSelect query);
    
    List<Direccion> solveQuery(SQLSelect query, int pageSize, int pageNumber);
    
    int solveCountQuery(SQLSelect query);

    public List<Direccion> executeQuery(String query);

    public List<Direccion> executeQuery(String query, List<SQLParam> params);

    void refreshEntity(Object object);
}
