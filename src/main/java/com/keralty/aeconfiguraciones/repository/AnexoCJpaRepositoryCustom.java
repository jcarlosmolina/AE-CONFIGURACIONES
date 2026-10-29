package com.keralty.aeconfiguraciones.repository;

import com.keralty.aeconfiguraciones.persistence.AnexoC;
import com.keralty.aeconfiguraciones.sqlutils.SQLParam;
import com.keralty.aeconfiguraciones.sqlutils.SQLSelect;
import java.util.List;

public interface AnexoCJpaRepositoryCustom {

    List<AnexoC> solveQuery(SQLSelect query);
    
    List<AnexoC> solveQuery(SQLSelect query, int pageSize, int pageNumber);
    
    int solveCountQuery(SQLSelect query);

    public List<AnexoC> executeQuery(String query);

    public List<AnexoC> executeQuery(String query, List<SQLParam> params);

    void refreshEntity(Object object);
}
