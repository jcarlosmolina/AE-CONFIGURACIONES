package com.keralty.aeconfiguraciones.repository;

import com.keralty.aeconfiguraciones.exception.SystemException;
import com.keralty.aeconfiguraciones.persistence.Causal;
import com.keralty.aeconfiguraciones.persistence.Dominio;
import com.keralty.aeconfiguraciones.persistence.GrupoAsignacionNovedad;
import com.keralty.aeconfiguraciones.sqlutils.SQLCount;
import com.keralty.aeconfiguraciones.sqlutils.SQLExists;
import com.keralty.aeconfiguraciones.sqlutils.SQLParam;
import com.keralty.aeconfiguraciones.sqlutils.SQLSelect;
import java.util.List;

public interface GrupoAsignacionNovedadJpaRepositoryCustom {

    List<GrupoAsignacionNovedad> solveQuery(SQLSelect query);
    
    List<GrupoAsignacionNovedad> solveQuery(SQLSelect query, int pageSize, int pageNumber);
    
    int solveCountQuery(SQLSelect query);

    Long assocOperator000(Dominio grupoAsignacionNovedadTCREARptpagrDominio, Causal grupoAsignacionNovedadTCREARptpagrCausal) throws SystemException;

    Long assocOperator000Range1(Dominio grupoAsignacionNovedadTCREARptpagrDominio, Causal grupoAsignacionNovedadTCREARptpagrCausal) throws SystemException;
    
    SQLCount assocOperator000Range1Query(Dominio grupoAsignacionNovedadTCREARptpagrDominio, Causal grupoAsignacionNovedadTCREARptpagrCausal, SQLSelect superQuery) throws SystemException;

    Boolean assocOperator002(GrupoAsignacionNovedad instance) throws SystemException;

    Boolean assocOperator002Range1(GrupoAsignacionNovedad instance) throws SystemException;
    
    SQLExists assocOperator002Range1Query(GrupoAsignacionNovedad instance) throws SystemException;

    public List<GrupoAsignacionNovedad> executeQuery(String query);

    public List<GrupoAsignacionNovedad> executeQuery(String query, List<SQLParam> params);

    void refreshEntity(Object object);
}
