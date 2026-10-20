package com.keralty.aeconfiguraciones.repository;

import com.keralty.aeconfiguraciones.exception.SystemException;
import com.keralty.aeconfiguraciones.persistence.TipoInstitucionC;
import com.keralty.aeconfiguraciones.sqlutils.SQLExists;
import com.keralty.aeconfiguraciones.sqlutils.SQLParam;
import com.keralty.aeconfiguraciones.sqlutils.SQLSelect;
import java.util.List;

public interface TipoInstitucionCJpaRepositoryCustom {

    List<TipoInstitucionC> solveQuery(SQLSelect query);
    
    List<TipoInstitucionC> solveQuery(SQLSelect query, int pageSize, int pageNumber);
    
    int solveCountQuery(SQLSelect query);

    Boolean assocOperator000(TipoInstitucionC instance) throws SystemException;

    Boolean assocOperator000Range1(TipoInstitucionC instance) throws SystemException;
    
    SQLExists assocOperator000Range1Query(TipoInstitucionC instance) throws SystemException;

    public List<TipoInstitucionC> executeQuery(String query);

    public List<TipoInstitucionC> executeQuery(String query, List<SQLParam> params);

    void refreshEntity(Object object);
}
