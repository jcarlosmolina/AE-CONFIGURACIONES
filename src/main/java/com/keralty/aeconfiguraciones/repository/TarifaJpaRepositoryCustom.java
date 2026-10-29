package com.keralty.aeconfiguraciones.repository;

import com.keralty.aeconfiguraciones.exception.SystemException;
import com.keralty.aeconfiguraciones.persistence.DetalleTarifa;
import com.keralty.aeconfiguraciones.persistence.Tarifa;
import com.keralty.aeconfiguraciones.sqlutils.SQLExists;
import com.keralty.aeconfiguraciones.sqlutils.SQLParam;
import com.keralty.aeconfiguraciones.sqlutils.SQLPopulation;
import com.keralty.aeconfiguraciones.sqlutils.SQLSelect;
import java.util.Collection;
import java.util.List;

public interface TarifaJpaRepositoryCustom {

    List<Tarifa> solveQuery(SQLSelect query);
    
    List<Tarifa> solveQuery(SQLSelect query, int pageSize, int pageNumber);
    
    int solveCountQuery(SQLSelect query);

    Boolean assocOperator000(Long vfAnyo) throws SystemException;

    Boolean assocOperator000Range1(Long vfAnyo) throws SystemException;
    
    SQLExists assocOperator000Range1Query(Long vfAnyo, SQLSelect superQuery) throws SystemException;

    Boolean assocOperator001(Tarifa instance) throws SystemException;

    Boolean assocOperator001Range1(Tarifa instance) throws SystemException;
    
    SQLExists assocOperator001Range1Query(Tarifa instance) throws SystemException;

    Collection<DetalleTarifa> assocOperator002(Tarifa instance) throws SystemException;

    Collection<DetalleTarifa> assocOperator002Range1(Tarifa instance) throws SystemException;
    
    SQLPopulation assocOperator002Range1Query(Tarifa instance) throws SystemException;

    public List<Tarifa> executeQuery(String query);

    public List<Tarifa> executeQuery(String query, List<SQLParam> params);

    void refreshEntity(Object object);
}
