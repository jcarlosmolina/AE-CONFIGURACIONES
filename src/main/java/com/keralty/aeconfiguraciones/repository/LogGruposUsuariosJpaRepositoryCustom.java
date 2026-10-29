package com.keralty.aeconfiguraciones.repository;

import com.keralty.aeconfiguraciones.persistence.LogGruposUsuarios;
import com.keralty.aeconfiguraciones.sqlutils.SQLParam;
import com.keralty.aeconfiguraciones.sqlutils.SQLSelect;
import java.util.List;

public interface LogGruposUsuariosJpaRepositoryCustom {

    List<LogGruposUsuarios> solveQuery(SQLSelect query);
    
    List<LogGruposUsuarios> solveQuery(SQLSelect query, int pageSize, int pageNumber);
    
    int solveCountQuery(SQLSelect query);

    public List<LogGruposUsuarios> executeQuery(String query);

    public List<LogGruposUsuarios> executeQuery(String query, List<SQLParam> params);

    void refreshEntity(Object object);
}
