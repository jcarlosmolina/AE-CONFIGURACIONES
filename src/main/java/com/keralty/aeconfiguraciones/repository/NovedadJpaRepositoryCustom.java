package com.keralty.aeconfiguraciones.repository;

import com.keralty.aeconfiguraciones.exception.SystemException;
import com.keralty.aeconfiguraciones.persistence.Causal;
import com.keralty.aeconfiguraciones.persistence.Dominio;
import com.keralty.aeconfiguraciones.persistence.Novedad;
import com.keralty.aeconfiguraciones.persistence.Planes;
import com.keralty.aeconfiguraciones.sqlutils.SQLCount;
import com.keralty.aeconfiguraciones.sqlutils.SQLExists;
import com.keralty.aeconfiguraciones.sqlutils.SQLParam;
import com.keralty.aeconfiguraciones.sqlutils.SQLPopulation;
import com.keralty.aeconfiguraciones.sqlutils.SQLSelect;
import java.util.Collection;
import java.util.List;

public interface NovedadJpaRepositoryCustom {

    List<Novedad> solveQuery(SQLSelect query);
    
    List<Novedad> solveQuery(SQLSelect query, int pageSize, int pageNumber);
    
    int solveCountQuery(SQLSelect query);

    Long assocOperator000(Novedad instance) throws SystemException;

    Long assocOperator000Range1(Novedad instance) throws SystemException;
    
    SQLCount assocOperator000Range1Query(Novedad instance) throws SystemException;

    Long assocOperator001(Novedad instance) throws SystemException;

    Long assocOperator001Range1(Novedad instance) throws SystemException;
    
    SQLCount assocOperator001Range1Query(Novedad instance) throws SystemException;

    Collection<Causal> assocOperator002(Novedad instance) throws SystemException;

    Collection<Causal> assocOperator002Range1(Novedad instance) throws SystemException;
    
    SQLPopulation assocOperator002Range1Query(Novedad instance) throws SystemException;

    Boolean assocOperator003(Planes solicitudNovedadTCREARptpagrPlan) throws SystemException;

    Boolean assocOperator003Range1(Planes solicitudNovedadTCREARptpagrPlan) throws SystemException;
    
    SQLExists assocOperator003Range1Query(Planes solicitudNovedadTCREARptpagrPlan, SQLSelect superQuery) throws SystemException;

    Boolean assocOperator004(Planes solicitudNovedadTCREAREXCEPCIONptpagrPlan) throws SystemException;

    Boolean assocOperator004Range1(Planes solicitudNovedadTCREAREXCEPCIONptpagrPlan) throws SystemException;
    
    SQLExists assocOperator004Range1Query(Planes solicitudNovedadTCREAREXCEPCIONptpagrPlan, SQLSelect superQuery) throws SystemException;

    Boolean assocOperator005(Dominio grupoAsignacionNovedadTCREARptpagrDominio) throws SystemException;

    Boolean assocOperator005Range1(Dominio grupoAsignacionNovedadTCREARptpagrDominio) throws SystemException;
    
    SQLExists assocOperator005Range1Query(Dominio grupoAsignacionNovedadTCREARptpagrDominio, SQLSelect superQuery) throws SystemException;

    Boolean assocOperator006(Dominio configuracionANSTCREARptpagrDominio) throws SystemException;

    Boolean assocOperator006Range1(Dominio configuracionANSTCREARptpagrDominio) throws SystemException;
    
    SQLExists assocOperator006Range1Query(Dominio configuracionANSTCREARptpagrDominio, SQLSelect superQuery) throws SystemException;

    public List<Novedad> executeQuery(String query);

    public List<Novedad> executeQuery(String query, List<SQLParam> params);

    void refreshEntity(Object object);
}
