package com.keralty.aeconfiguraciones.repository;

import com.keralty.aeconfiguraciones.exception.SystemException;
import com.keralty.aeconfiguraciones.persistence.Causal;
import com.keralty.aeconfiguraciones.persistence.ConfiguracionANS;
import com.keralty.aeconfiguraciones.persistence.Dominio;
import com.keralty.aeconfiguraciones.persistence.LogConfiguracionANS;
import com.keralty.aeconfiguraciones.persistence.Novedad;
import com.keralty.aeconfiguraciones.sqlutils.SQLCount;
import com.keralty.aeconfiguraciones.sqlutils.SQLExists;
import com.keralty.aeconfiguraciones.sqlutils.SQLParam;
import com.keralty.aeconfiguraciones.sqlutils.SQLPopulation;
import com.keralty.aeconfiguraciones.sqlutils.SQLSelect;
import java.util.Collection;
import java.util.List;

public interface ConfiguracionANSJpaRepositoryCustom {

    List<ConfiguracionANS> solveQuery(SQLSelect query);
    
    List<ConfiguracionANS> solveQuery(SQLSelect query, int pageSize, int pageNumber);
    
    int solveCountQuery(SQLSelect query);

    Boolean assocOperator000(ConfiguracionANS instance) throws SystemException;

    Boolean assocOperator000Range1(ConfiguracionANS instance) throws SystemException;
    
    SQLExists assocOperator000Range1Query(ConfiguracionANS instance) throws SystemException;

    Long assocOperator001(Dominio configuracionANSTCREARptpagrDominio, Long configuracionANSTCREARptpatrTramite) throws SystemException;

    Long assocOperator001Range1(Dominio configuracionANSTCREARptpagrDominio, Long configuracionANSTCREARptpatrTramite) throws SystemException;
    
    SQLCount assocOperator001Range1Query(Dominio configuracionANSTCREARptpagrDominio, Long configuracionANSTCREARptpatrTramite, SQLSelect superQuery) throws SystemException;

    Long assocOperator002(Dominio configuracionANSTCREARptpagrDominio, Novedad configuracionANSTCREARptpagrNovedad) throws SystemException;

    Collection<ConfiguracionANS> assocOperator002Range1(Dominio configuracionANSTCREARptpagrDominio, Novedad configuracionANSTCREARptpagrNovedad) throws SystemException;
    
    SQLPopulation assocOperator002Range1Query(Dominio configuracionANSTCREARptpagrDominio, Novedad configuracionANSTCREARptpagrNovedad) throws SystemException;

    Long assocOperator003(Dominio configuracionANSTCREARptpagrDominio, Causal configuracionANSTCREARptpagrCausal) throws SystemException;

    Long assocOperator003Range1(Dominio configuracionANSTCREARptpagrDominio, Causal configuracionANSTCREARptpagrCausal) throws SystemException;
    
    SQLCount assocOperator003Range1Query(Dominio configuracionANSTCREARptpagrDominio, Causal configuracionANSTCREARptpagrCausal, SQLSelect superQuery) throws SystemException;

    Collection<LogConfiguracionANS> assocOperator004(ConfiguracionANS instance) throws SystemException;

    Collection<LogConfiguracionANS> assocOperator004Range1(ConfiguracionANS instance) throws SystemException;
    
    SQLPopulation assocOperator004Range1Query(ConfiguracionANS instance) throws SystemException;

    public List<ConfiguracionANS> executeQuery(String query);

    public List<ConfiguracionANS> executeQuery(String query, List<SQLParam> params);

    void refreshEntity(Object object);
}
