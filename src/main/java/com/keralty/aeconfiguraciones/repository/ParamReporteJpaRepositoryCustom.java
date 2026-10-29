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

public interface ParamReporteJpaRepositoryCustom {

    List<ParamReporte> solveQuery(SQLSelect query);
    
    List<ParamReporte> solveQuery(SQLSelect query, int pageSize, int pageNumber);
    
    int solveCountQuery(SQLSelect query);

    Long assocOperator000(ParamReporte instance) throws SystemException;

    Long assocOperator000Range1(ParamReporte instance) throws SystemException;
    
    SQLCount assocOperator000Range1Query(ParamReporte instance) throws SystemException;

    Long assocOperator001(ParamReporte instance) throws SystemException;

    Long assocOperator001Range1(ParamReporte instance) throws SystemException;
    
    SQLCount assocOperator001Range1Query(ParamReporte instance) throws SystemException;

    Long assocOperator003(ReportesProcAlm paramReporteTNEWptpagrReporte) throws SystemException;

    Long assocOperator003Range1(ReportesProcAlm paramReporteTNEWptpagrReporte) throws SystemException;
    
    SQLCount assocOperator003Range1Query(ReportesProcAlm paramReporteTNEWptpagrReporte, SQLSelect superQuery) throws SystemException;

    Collection<ParamReporte> assocOperator004(ParamReporte instance) throws SystemException;

    Collection<ParamReporte> assocOperator004Range1(ParamReporte instance) throws SystemException;
    
    SQLPopulation assocOperator004Range1Query(ParamReporte instance) throws SystemException;

    public List<ParamReporte> executeQuery(String query);

    public List<ParamReporte> executeQuery(String query, List<SQLParam> params);

    void refreshEntity(Object object);
}
