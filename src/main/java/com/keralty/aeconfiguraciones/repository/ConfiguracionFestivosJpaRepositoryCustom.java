package com.keralty.aeconfiguraciones.repository;

import com.keralty.aeconfiguraciones.persistence.ConfiguracionFestivos;
import com.keralty.aeconfiguraciones.sqlutils.SQLParam;
import com.keralty.aeconfiguraciones.sqlutils.SQLSelect;
import java.util.List;

public interface ConfiguracionFestivosJpaRepositoryCustom {

    List<ConfiguracionFestivos> solveQuery(SQLSelect query);
    
    List<ConfiguracionFestivos> solveQuery(SQLSelect query, int pageSize, int pageNumber);
    
    int solveCountQuery(SQLSelect query);

    public List<ConfiguracionFestivos> executeQuery(String query);

    public List<ConfiguracionFestivos> executeQuery(String query, List<SQLParam> params);

    void refreshEntity(Object object);
}
