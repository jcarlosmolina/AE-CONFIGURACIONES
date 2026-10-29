package com.keralty.aeconfiguraciones.repository;

import com.keralty.aeconfiguraciones.exception.SystemException;
import com.keralty.aeconfiguraciones.persistence.Regional;
import com.keralty.aeconfiguraciones.sqlutils.SQLExists;
import com.keralty.aeconfiguraciones.sqlutils.SQLParam;
import com.keralty.aeconfiguraciones.sqlutils.SQLSelect;
import java.util.List;

public interface RegionalJpaRepositoryCustom {

    List<Regional> solveQuery(SQLSelect query);
    
    List<Regional> solveQuery(SQLSelect query, int pageSize, int pageNumber);
    
    int solveCountQuery(SQLSelect query);

    Boolean assocOperator000(Regional instance) throws SystemException;

    Boolean assocOperator000Range1(Regional instance) throws SystemException;
    
    SQLExists assocOperator000Range1Query(Regional instance) throws SystemException;

    Boolean assocOperator001(Regional instance) throws SystemException;

    Boolean assocOperator001Range1(Regional instance) throws SystemException;
    
    SQLExists assocOperator001Range1Query(Regional instance) throws SystemException;

    public List<Regional> executeQuery(String query);

    public List<Regional> executeQuery(String query, List<SQLParam> params);

    void refreshEntity(Object object);
}
