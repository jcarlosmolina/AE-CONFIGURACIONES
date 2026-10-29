package com.keralty.aeconfiguraciones.repository;

import com.keralty.aeconfiguraciones.exception.SystemException;
import com.keralty.aeconfiguraciones.persistence.TipoEntidadC;
import com.keralty.aeconfiguraciones.sqlutils.SQLExists;
import com.keralty.aeconfiguraciones.sqlutils.SQLParam;
import com.keralty.aeconfiguraciones.sqlutils.SQLSelect;
import java.util.List;

public interface TipoEntidadCJpaRepositoryCustom {

    List<TipoEntidadC> solveQuery(SQLSelect query);
    
    List<TipoEntidadC> solveQuery(SQLSelect query, int pageSize, int pageNumber);
    
    int solveCountQuery(SQLSelect query);

    Boolean assocOperator000(TipoEntidadC instance) throws SystemException;

    Boolean assocOperator000Range1(TipoEntidadC instance) throws SystemException;
    
    SQLExists assocOperator000Range1Query(TipoEntidadC instance) throws SystemException;

    public List<TipoEntidadC> executeQuery(String query);

    public List<TipoEntidadC> executeQuery(String query, List<SQLParam> params);

    void refreshEntity(Object object);
}
