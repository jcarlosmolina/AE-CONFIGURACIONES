package com.keralty.aeconfiguraciones.repository;

import com.keralty.aeconfiguraciones.persistence.TextoAviso;
import com.keralty.aeconfiguraciones.sqlutils.SQLParam;
import com.keralty.aeconfiguraciones.sqlutils.SQLSelect;
import java.util.List;

public interface TextoAvisoJpaRepositoryCustom {

    List<TextoAviso> solveQuery(SQLSelect query);
    
    List<TextoAviso> solveQuery(SQLSelect query, int pageSize, int pageNumber);
    
    int solveCountQuery(SQLSelect query);

    public List<TextoAviso> executeQuery(String query);

    public List<TextoAviso> executeQuery(String query, List<SQLParam> params);

    void refreshEntity(Object object);
}
