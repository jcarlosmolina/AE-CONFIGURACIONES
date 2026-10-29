package com.keralty.aeconfiguraciones.repository;

import com.keralty.aeconfiguraciones.persistence.SeccionP;
import com.keralty.aeconfiguraciones.sqlutils.SQLParam;
import com.keralty.aeconfiguraciones.sqlutils.SQLSelect;
import java.util.List;

public interface SeccionPJpaRepositoryCustom {

    List<SeccionP> solveQuery(SQLSelect query);
    
    List<SeccionP> solveQuery(SQLSelect query, int pageSize, int pageNumber);
    
    int solveCountQuery(SQLSelect query);

    public List<SeccionP> executeQuery(String query);

    public List<SeccionP> executeQuery(String query, List<SQLParam> params);

    void refreshEntity(Object object);
}
