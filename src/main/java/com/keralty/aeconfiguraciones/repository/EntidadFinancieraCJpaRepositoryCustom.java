package com.keralty.aeconfiguraciones.repository;

import com.keralty.aeconfiguraciones.persistence.EntidadFinancieraC;
import com.keralty.aeconfiguraciones.sqlutils.SQLParam;
import com.keralty.aeconfiguraciones.sqlutils.SQLSelect;
import java.util.List;

public interface EntidadFinancieraCJpaRepositoryCustom {

    List<EntidadFinancieraC> solveQuery(SQLSelect query);
    
    List<EntidadFinancieraC> solveQuery(SQLSelect query, int pageSize, int pageNumber);
    
    int solveCountQuery(SQLSelect query);

    public List<EntidadFinancieraC> executeQuery(String query);

    public List<EntidadFinancieraC> executeQuery(String query, List<SQLParam> params);

    void refreshEntity(Object object);
}
