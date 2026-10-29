package com.keralty.aeconfiguraciones.repository;

import com.keralty.aeconfiguraciones.persistence.Contratante;
import com.keralty.aeconfiguraciones.sqlutils.SQLParam;
import com.keralty.aeconfiguraciones.sqlutils.SQLSelect;
import java.util.List;

public interface ContratanteJpaRepositoryCustom {

    List<Contratante> solveQuery(SQLSelect query);
    
    List<Contratante> solveQuery(SQLSelect query, int pageSize, int pageNumber);
    
    int solveCountQuery(SQLSelect query);

    public List<Contratante> executeQuery(String query);

    public List<Contratante> executeQuery(String query, List<SQLParam> params);

    void refreshEntity(Object object);
}
