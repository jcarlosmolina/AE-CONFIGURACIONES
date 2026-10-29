package com.keralty.aeconfiguraciones.repository;

import com.keralty.aeconfiguraciones.exception.SystemException;
import com.keralty.aeconfiguraciones.persistence.Planes;
import com.keralty.aeconfiguraciones.persistence.TipoNovedad;
import com.keralty.aeconfiguraciones.sqlutils.SQLExists;
import com.keralty.aeconfiguraciones.sqlutils.SQLParam;
import com.keralty.aeconfiguraciones.sqlutils.SQLSelect;
import java.util.List;

public interface TipoNovedadJpaRepositoryCustom {

    List<TipoNovedad> solveQuery(SQLSelect query);
    
    List<TipoNovedad> solveQuery(SQLSelect query, int pageSize, int pageNumber);
    
    int solveCountQuery(SQLSelect query);

    Boolean assocOperator000(Planes solicitudNovedadTCREARptpagrPlan) throws SystemException;

    Boolean assocOperator000Range1(Planes solicitudNovedadTCREARptpagrPlan) throws SystemException;
    
    SQLExists assocOperator000Range1Query(Planes solicitudNovedadTCREARptpagrPlan, SQLSelect superQuery) throws SystemException;

    Boolean assocOperator001(Planes solicitudNovedadTCREAREXCEPCIONptpagrPlan) throws SystemException;

    Boolean assocOperator001Range1(Planes solicitudNovedadTCREAREXCEPCIONptpagrPlan) throws SystemException;
    
    SQLExists assocOperator001Range1Query(Planes solicitudNovedadTCREAREXCEPCIONptpagrPlan, SQLSelect superQuery) throws SystemException;

    public List<TipoNovedad> executeQuery(String query);

    public List<TipoNovedad> executeQuery(String query, List<SQLParam> params);

    void refreshEntity(Object object);
}
