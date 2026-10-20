package com.keralty.aeconfiguraciones.repository;

import com.keralty.aeconfiguraciones.exception.SystemException;
import com.keralty.aeconfiguraciones.persistence.TipoSucursalC;
import com.keralty.aeconfiguraciones.sqlutils.SQLExists;
import com.keralty.aeconfiguraciones.sqlutils.SQLParam;
import com.keralty.aeconfiguraciones.sqlutils.SQLSelect;
import java.util.List;

public interface TipoSucursalCJpaRepositoryCustom {

    List<TipoSucursalC> solveQuery(SQLSelect query);
    
    List<TipoSucursalC> solveQuery(SQLSelect query, int pageSize, int pageNumber);
    
    int solveCountQuery(SQLSelect query);

    Boolean assocOperator000(TipoSucursalC instance) throws SystemException;

    Boolean assocOperator000Range1(TipoSucursalC instance) throws SystemException;
    
    SQLExists assocOperator000Range1Query(TipoSucursalC instance) throws SystemException;

    public List<TipoSucursalC> executeQuery(String query);

    public List<TipoSucursalC> executeQuery(String query, List<SQLParam> params);

    void refreshEntity(Object object);
}
