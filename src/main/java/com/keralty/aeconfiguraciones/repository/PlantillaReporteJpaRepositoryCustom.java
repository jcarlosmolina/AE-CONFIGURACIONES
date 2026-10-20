package com.keralty.aeconfiguraciones.repository;

import com.keralty.aeconfiguraciones.exception.SystemException;
import com.keralty.aeconfiguraciones.persistence.Dominio;
import com.keralty.aeconfiguraciones.persistence.PlantillaReporte;
import com.keralty.aeconfiguraciones.sqlutils.SQLCount;
import com.keralty.aeconfiguraciones.sqlutils.SQLParam;
import com.keralty.aeconfiguraciones.sqlutils.SQLSelect;
import java.util.List;

public interface PlantillaReporteJpaRepositoryCustom {

    List<PlantillaReporte> solveQuery(SQLSelect query);
    
    List<PlantillaReporte> solveQuery(SQLSelect query, int pageSize, int pageNumber);
    
    int solveCountQuery(SQLSelect query);

    Long assocOperator000(Dominio dominioTADDREPORTEpthisDominio) throws SystemException;

    Long assocOperator000Range1(Dominio dominioTADDREPORTEpthisDominio) throws SystemException;
    
    SQLCount assocOperator000Range1Query(Dominio dominioTADDREPORTEpthisDominio, SQLSelect superQuery) throws SystemException;

    Long assocOperator001(PlantillaReporte instance) throws SystemException;

    Long assocOperator001Range1(PlantillaReporte instance) throws SystemException;
    
    SQLCount assocOperator001Range1Query(PlantillaReporte instance) throws SystemException;

    Long assocOperator002(PlantillaReporte instance) throws SystemException;

    Long assocOperator002Range1(PlantillaReporte instance) throws SystemException;
    
    SQLCount assocOperator002Range1Query(PlantillaReporte instance) throws SystemException;

    public List<PlantillaReporte> executeQuery(String query);

    public List<PlantillaReporte> executeQuery(String query, List<SQLParam> params);

    void refreshEntity(Object object);
}
