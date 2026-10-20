package com.keralty.aeconfiguraciones.repository;

import com.keralty.aeconfiguraciones.exception.SystemException;
import com.keralty.aeconfiguraciones.persistence.InstitucionMatNeo;
import com.keralty.aeconfiguraciones.sqlutils.SQLCount;
import com.keralty.aeconfiguraciones.sqlutils.SQLParam;
import com.keralty.aeconfiguraciones.sqlutils.SQLSelect;
import java.util.List;

public interface InstitucionMatNeoJpaRepositoryCustom {

    List<InstitucionMatNeo> solveQuery(SQLSelect query);
    
    List<InstitucionMatNeo> solveQuery(SQLSelect query, int pageSize, int pageNumber);
    
    int solveCountQuery(SQLSelect query);

    Long assocOperator000(InstitucionMatNeo instance) throws SystemException;

    Long assocOperator000Range1(InstitucionMatNeo instance) throws SystemException;
    
    SQLCount assocOperator000Range1Query(InstitucionMatNeo instance) throws SystemException;

    Long assocOperator001(InstitucionMatNeo instance) throws SystemException;

    Long assocOperator001Range1(InstitucionMatNeo instance) throws SystemException;
    
    SQLCount assocOperator001Range1Query(InstitucionMatNeo instance) throws SystemException;

    public List<InstitucionMatNeo> executeQuery(String query);

    public List<InstitucionMatNeo> executeQuery(String query, List<SQLParam> params);

    void refreshEntity(Object object);
}
