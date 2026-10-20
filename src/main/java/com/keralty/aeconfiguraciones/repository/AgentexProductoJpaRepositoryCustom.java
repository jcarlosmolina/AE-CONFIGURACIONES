package com.keralty.aeconfiguraciones.repository;

import com.keralty.aeconfiguraciones.persistence.AgentexProducto;
import com.keralty.aeconfiguraciones.sqlutils.SQLParam;
import com.keralty.aeconfiguraciones.sqlutils.SQLSelect;
import java.util.List;

public interface AgentexProductoJpaRepositoryCustom {

    List<AgentexProducto> solveQuery(SQLSelect query);
    
    List<AgentexProducto> solveQuery(SQLSelect query, int pageSize, int pageNumber);
    
    int solveCountQuery(SQLSelect query);
    
    Long nextAgentexProductoId(Long funcionarioAsesorId);

    public List<AgentexProducto> executeQuery(String query);

    public List<AgentexProducto> executeQuery(String query, List<SQLParam> params);

    void refreshEntity(Object object);
}
