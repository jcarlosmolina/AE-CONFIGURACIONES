package com.keralty.aeconfiguraciones.repository;

import com.keralty.aeconfiguraciones.exception.SystemException;
import com.keralty.aeconfiguraciones.persistence.ParamReporte;
import com.keralty.aeconfiguraciones.persistence.ReportesProcAlm;
import com.keralty.aeconfiguraciones.sqlutils.SQLCount;
import com.keralty.aeconfiguraciones.sqlutils.SQLParam;
import com.keralty.aeconfiguraciones.sqlutils.SQLPopulation;
import com.keralty.aeconfiguraciones.sqlutils.SQLSelect;
import java.util.Collection;
import java.util.List;

public interface ReportesProcAlmJpaRepositoryCustom {

    List<ReportesProcAlm> solveQuery(SQLSelect query);
    
    List<ReportesProcAlm> solveQuery(SQLSelect query, int pageSize, int pageNumber);
    
    int solveCountQuery(SQLSelect query);

    Long assocOperator000() throws SystemException;

    Long assocOperator000Range1() throws SystemException;
    
    SQLCount assocOperator000Range1Query(SQLSelect superQuery) throws SystemException;

    Long assocOperator001(ReportesProcAlm instance) throws SystemException;

    Long assocOperator001Range1(ReportesProcAlm instance) throws SystemException;
    
    SQLCount assocOperator001Range1Query(ReportesProcAlm instance) throws SystemException;

    Collection<ParamReporte> assocOperator002(ReportesProcAlm instance, Long reportesProcAlmTCAMBIARORDENPARAMSptOrdenActual) throws SystemException;

    Collection<ParamReporte> assocOperator002Range1(ReportesProcAlm instance, Long reportesProcAlmTCAMBIARORDENPARAMSptOrdenActual) throws SystemException;
    
    SQLPopulation assocOperator002Range1Query(ReportesProcAlm instance, Long reportesProcAlmTCAMBIARORDENPARAMSptOrdenActual) throws SystemException;

    public List<ReportesProcAlm> executeQuery(String query);

    public List<ReportesProcAlm> executeQuery(String query, List<SQLParam> params);

    void refreshEntity(Object object);
}
