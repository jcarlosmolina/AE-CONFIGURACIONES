package com.keralty.aeconfiguraciones.repository;

import com.keralty.aeconfiguraciones.persistence.EstadoCivil;
import com.keralty.aeconfiguraciones.sqlutils.SQLParam;
import com.keralty.aeconfiguraciones.sqlutils.SQLSelect;
import java.util.List;

public interface EstadoCivilJpaRepositoryCustom {

    List<EstadoCivil> solveQuery(SQLSelect query);
    
    List<EstadoCivil> solveQuery(SQLSelect query, int pageSize, int pageNumber);
    
    int solveCountQuery(SQLSelect query);

    public List<EstadoCivil> executeQuery(String query);

    public List<EstadoCivil> executeQuery(String query, List<SQLParam> params);

    void refreshEntity(Object object);
}
