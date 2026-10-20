package com.keralty.aeconfiguraciones.repository;

import com.keralty.aeconfiguraciones.exception.SystemException;
import com.keralty.aeconfiguraciones.persistence.GrupoAsignacion;
import com.keralty.aeconfiguraciones.persistence.LogGrupoAsignacion;
import com.keralty.aeconfiguraciones.sqlutils.SQLCount;
import com.keralty.aeconfiguraciones.sqlutils.SQLParam;
import com.keralty.aeconfiguraciones.sqlutils.SQLPopulation;
import com.keralty.aeconfiguraciones.sqlutils.SQLSelect;
import java.util.Collection;
import java.util.List;

public interface GrupoAsignacionJpaRepositoryCustom {

    List<GrupoAsignacion> solveQuery(SQLSelect query);
    
    List<GrupoAsignacion> solveQuery(SQLSelect query, int pageSize, int pageNumber);
    
    int solveCountQuery(SQLSelect query);

    Long assocOperator000(GrupoAsignacion instance) throws SystemException;

    Long assocOperator000Range1(GrupoAsignacion instance) throws SystemException;
    
    SQLCount assocOperator000Range1Query(GrupoAsignacion instance) throws SystemException;

    Long assocOperator001(GrupoAsignacion instance) throws SystemException;

    Long assocOperator001Range1(GrupoAsignacion instance) throws SystemException;
    
    SQLCount assocOperator001Range1Query(GrupoAsignacion instance) throws SystemException;

    Collection<LogGrupoAsignacion> assocOperator002(GrupoAsignacion instance) throws SystemException;

    Collection<LogGrupoAsignacion> assocOperator002Range1(GrupoAsignacion instance) throws SystemException;
    
    SQLPopulation assocOperator002Range1Query(GrupoAsignacion instance) throws SystemException;

    public List<GrupoAsignacion> executeQuery(String query);

    public List<GrupoAsignacion> executeQuery(String query, List<SQLParam> params);

    void refreshEntity(Object object);
}
