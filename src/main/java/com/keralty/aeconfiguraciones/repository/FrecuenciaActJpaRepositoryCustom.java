package com.keralty.aeconfiguraciones.repository;

import com.keralty.aeconfiguraciones.persistence.FrecuenciaAct;
import com.keralty.aeconfiguraciones.sqlutils.SQLParam;
import com.keralty.aeconfiguraciones.sqlutils.SQLSelect;
import java.util.List;

public interface FrecuenciaActJpaRepositoryCustom {

    List<FrecuenciaAct> solveQuery(SQLSelect query);
    
    List<FrecuenciaAct> solveQuery(SQLSelect query, int pageSize, int pageNumber);
    
    int solveCountQuery(SQLSelect query);

    public List<FrecuenciaAct> executeQuery(String query);

    public List<FrecuenciaAct> executeQuery(String query, List<SQLParam> params);

    void refreshEntity(Object object);
}
