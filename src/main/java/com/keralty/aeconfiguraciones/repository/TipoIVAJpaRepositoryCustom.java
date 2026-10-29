package com.keralty.aeconfiguraciones.repository;

import com.keralty.aeconfiguraciones.exception.SystemException;
import com.keralty.aeconfiguraciones.persistence.Planes;
import com.keralty.aeconfiguraciones.persistence.Productos;
import com.keralty.aeconfiguraciones.persistence.TipoIVA;
import com.keralty.aeconfiguraciones.sqlutils.SQLCount;
import com.keralty.aeconfiguraciones.sqlutils.SQLParam;
import com.keralty.aeconfiguraciones.sqlutils.SQLSelect;
import java.sql.Date;
import java.util.List;

public interface TipoIVAJpaRepositoryCustom {

    List<TipoIVA> solveQuery(SQLSelect query);
    
    List<TipoIVA> solveQuery(SQLSelect query, int pageSize, int pageNumber);
    
    int solveCountQuery(SQLSelect query);

    Long assocOperator000(Productos tipoIVAcrearpagrProductoT, Planes tipoIVAcrearpagrPlanT, Date tipoIVAcrearpatrFechaInicio, Date tipoIVAcrearpatrFechaFin) throws SystemException;

    Long assocOperator000Range1(Productos tipoIVAcrearpagrProductoT, Planes tipoIVAcrearpagrPlanT, Date tipoIVAcrearpatrFechaInicio, Date tipoIVAcrearpatrFechaFin) throws SystemException;
    
    SQLCount assocOperator000Range1Query(Productos tipoIVAcrearpagrProductoT, Planes tipoIVAcrearpagrPlanT, Date tipoIVAcrearpatrFechaInicio, Date tipoIVAcrearpatrFechaFin, SQLSelect superQuery) throws SystemException;

    Long assocOperator001(Productos tipoIVAcrearpagrProductoT, Planes tipoIVAcrearpagrPlanT, Date tipoIVAcrearpatrFechaInicio, Date tipoIVAcrearpatrFechaFin) throws SystemException;

    Long assocOperator001Range1(Productos tipoIVAcrearpagrProductoT, Planes tipoIVAcrearpagrPlanT, Date tipoIVAcrearpatrFechaInicio, Date tipoIVAcrearpatrFechaFin) throws SystemException;
    
    SQLCount assocOperator001Range1Query(Productos tipoIVAcrearpagrProductoT, Planes tipoIVAcrearpagrPlanT, Date tipoIVAcrearpatrFechaInicio, Date tipoIVAcrearpatrFechaFin, SQLSelect superQuery) throws SystemException;

    Long assocOperator002(TipoIVA instance, TipoIVA tipoIVAmodificarpthisTipoIVA, Date tipoIVAmodificarpFechaInicio, Date tipoIVAmodificarpFechaFin) throws SystemException;

    Long assocOperator002Range1(TipoIVA instance, TipoIVA tipoIVAmodificarpthisTipoIVA, Date tipoIVAmodificarpFechaInicio, Date tipoIVAmodificarpFechaFin) throws SystemException;
    
    SQLCount assocOperator002Range1Query(TipoIVA instance, TipoIVA tipoIVAmodificarpthisTipoIVA, Date tipoIVAmodificarpFechaInicio, Date tipoIVAmodificarpFechaFin) throws SystemException;

    Long assocOperator003(TipoIVA instance, TipoIVA tipoIVAmodificarpthisTipoIVA, Date tipoIVAmodificarpFechaInicio, Date tipoIVAmodificarpFechaFin) throws SystemException;

    Long assocOperator003Range1(TipoIVA instance, TipoIVA tipoIVAmodificarpthisTipoIVA, Date tipoIVAmodificarpFechaInicio, Date tipoIVAmodificarpFechaFin) throws SystemException;
    
    SQLCount assocOperator003Range1Query(TipoIVA instance, TipoIVA tipoIVAmodificarpthisTipoIVA, Date tipoIVAmodificarpFechaInicio, Date tipoIVAmodificarpFechaFin) throws SystemException;

    public List<TipoIVA> executeQuery(String query);

    public List<TipoIVA> executeQuery(String query, List<SQLParam> params);

    void refreshEntity(Object object);
}
