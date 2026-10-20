package com.keralty.aeconfiguraciones.repository;

import com.keralty.aeconfiguraciones.exception.SystemException;
import com.keralty.aeconfiguraciones.persistence.LogPriorizacionSol;
import com.keralty.aeconfiguraciones.persistence.PriorizacionSolicitudes;
import com.keralty.aeconfiguraciones.sqlutils.SQLParam;
import com.keralty.aeconfiguraciones.sqlutils.SQLPopulation;
import com.keralty.aeconfiguraciones.sqlutils.SQLSelect;
import java.util.Collection;
import java.util.List;

public interface PriorizacionSolicitudesJpaRepositoryCustom {

    List<PriorizacionSolicitudes> solveQuery(SQLSelect query);
    
    List<PriorizacionSolicitudes> solveQuery(SQLSelect query, int pageSize, int pageNumber);
    
    int solveCountQuery(SQLSelect query);

    Collection<LogPriorizacionSol> assocOperator000(PriorizacionSolicitudes instance) throws SystemException;

    Collection<LogPriorizacionSol> assocOperator000Range1(PriorizacionSolicitudes instance) throws SystemException;
    
    SQLPopulation assocOperator000Range1Query(PriorizacionSolicitudes instance) throws SystemException;

    public List<PriorizacionSolicitudes> executeQuery(String query);

    public List<PriorizacionSolicitudes> executeQuery(String query, List<SQLParam> params);

    void refreshEntity(Object object);
}
