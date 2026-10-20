package com.keralty.aeconfiguraciones.repository;

import com.keralty.aeconfiguraciones.exception.SystemException;
import com.keralty.aeconfiguraciones.persistence.Pais;
import com.keralty.aeconfiguraciones.sqlutils.SQLExists;
import com.keralty.aeconfiguraciones.sqlutils.SQLParam;
import com.keralty.aeconfiguraciones.sqlutils.SQLSelect;
import java.util.List;

public interface PaisJpaRepositoryCustom {

    List<Pais> solveQuery(SQLSelect query);
    
    List<Pais> solveQuery(SQLSelect query, int pageSize, int pageNumber);
    
    int solveCountQuery(SQLSelect query);

    Boolean assocOperator000(Pais instance) throws SystemException;

    Boolean assocOperator000Range1(Pais instance) throws SystemException;
    
    SQLExists assocOperator000Range1Query(Pais instance) throws SystemException;

    public List<Pais> executeQuery(String query);

    public List<Pais> executeQuery(String query, List<SQLParam> params);

    void refreshEntity(Object object);
}
