package com.keralty.aeconfiguraciones.repository;

import com.keralty.aeconfiguraciones.exception.SystemException;
import com.keralty.aeconfiguraciones.persistence.Dominio;
import com.keralty.aeconfiguraciones.persistence.Prorrateo;
import com.keralty.aeconfiguraciones.sqlutils.SQLExists;
import com.keralty.aeconfiguraciones.sqlutils.SQLParam;
import com.keralty.aeconfiguraciones.sqlutils.SQLSelect;
import java.util.List;

public interface ProrrateoJpaRepositoryCustom {

    List<Prorrateo> solveQuery(SQLSelect query);
    
    List<Prorrateo> solveQuery(SQLSelect query, int pageSize, int pageNumber);
    
    int solveCountQuery(SQLSelect query);
    
    Long nextProrrateoidProrrateo(Long dominioNumero);

    Boolean assocOperator000(Dominio prorrateocrearpagrDominio, Long prorrateocrearpatrDia) throws SystemException;

    Boolean assocOperator000Range1(Dominio prorrateocrearpagrDominio, Long prorrateocrearpatrDia) throws SystemException;
    
    SQLExists assocOperator000Range1Query(Dominio prorrateocrearpagrDominio, Long prorrateocrearpatrDia, SQLSelect superQuery) throws SystemException;

    Boolean assocOperator001(Prorrateo instance, Long prorrateomodificarpDia) throws SystemException;

    Boolean assocOperator001Range1(Prorrateo instance, Long prorrateomodificarpDia) throws SystemException;
    
    SQLExists assocOperator001Range1Query(Prorrateo instance, Long prorrateomodificarpDia) throws SystemException;

    public List<Prorrateo> executeQuery(String query);

    public List<Prorrateo> executeQuery(String query, List<SQLParam> params);

    void refreshEntity(Object object);
}
