package com.keralty.aeconfiguraciones.repository;

import com.keralty.aeconfiguraciones.persistence.EstadoPersona;
import com.keralty.aeconfiguraciones.sqlutils.SQLParam;
import com.keralty.aeconfiguraciones.sqlutils.SQLSelect;
import java.util.List;

public interface EstadoPersonaJpaRepositoryCustom {

    List<EstadoPersona> solveQuery(SQLSelect query);
    
    List<EstadoPersona> solveQuery(SQLSelect query, int pageSize, int pageNumber);
    
    int solveCountQuery(SQLSelect query);

    public List<EstadoPersona> executeQuery(String query);

    public List<EstadoPersona> executeQuery(String query, List<SQLParam> params);

    void refreshEntity(Object object);
}
