package com.keralty.aeconfiguraciones.repository;

import com.keralty.aeconfiguraciones.exception.SystemException;
import com.keralty.aeconfiguraciones.persistence.Pregunta;
import com.keralty.aeconfiguraciones.persistence.Seccion;
import com.keralty.aeconfiguraciones.sqlutils.SQLExists;
import com.keralty.aeconfiguraciones.sqlutils.SQLParam;
import com.keralty.aeconfiguraciones.sqlutils.SQLPopulation;
import com.keralty.aeconfiguraciones.sqlutils.SQLSelect;
import java.util.Collection;
import java.util.List;

public interface SeccionJpaRepositoryCustom {

    List<Seccion> solveQuery(SQLSelect query);
    
    List<Seccion> solveQuery(SQLSelect query, int pageSize, int pageNumber);
    
    int solveCountQuery(SQLSelect query);

    Boolean assocOperator000(Seccion instance) throws SystemException;

    Boolean assocOperator000Range1(Seccion instance) throws SystemException;
    
    SQLExists assocOperator000Range1Query(Seccion instance) throws SystemException;

    Collection<Seccion> assocOperator001(Seccion instance, Long seccionTCREARptpatrNumOrden) throws SystemException;

    Collection<Seccion> assocOperator001Range1(Seccion instance, Long seccionTCREARptpatrNumOrden) throws SystemException;
    
    SQLPopulation assocOperator001Range1Query(Seccion instance, Long seccionTCREARptpatrNumOrden) throws SystemException;

    Collection<Seccion> assocOperator002(Seccion instance, Long seccionTMODIFICARptpNumOrden) throws SystemException;

    Collection<Seccion> assocOperator002Range1(Seccion instance, Long seccionTMODIFICARptpNumOrden) throws SystemException;
    
    SQLPopulation assocOperator002Range1Query(Seccion instance, Long seccionTMODIFICARptpNumOrden) throws SystemException;

    Collection<Seccion> assocOperator003(Seccion instance, Long seccionTMODIFICARptpNumOrden) throws SystemException;

    Collection<Seccion> assocOperator003Range1(Seccion instance, Long seccionTMODIFICARptpNumOrden) throws SystemException;
    
    SQLPopulation assocOperator003Range1Query(Seccion instance, Long seccionTMODIFICARptpNumOrden) throws SystemException;

    Collection<Seccion> assocOperator004(Seccion instance) throws SystemException;

    Collection<Seccion> assocOperator004Range1(Seccion instance) throws SystemException;
    
    SQLPopulation assocOperator004Range1Query(Seccion instance) throws SystemException;

    Collection<Pregunta> assocOperator005(Seccion instance, Long seccionTCAMBIARORDENPREGSptOrdenDesde, Long seccionTCAMBIARORDENPREGSptOrdenHasta) throws SystemException;

    Collection<Pregunta> assocOperator005Range1(Seccion instance, Long seccionTCAMBIARORDENPREGSptOrdenDesde, Long seccionTCAMBIARORDENPREGSptOrdenHasta) throws SystemException;
    
    SQLPopulation assocOperator005Range1Query(Seccion instance, Long seccionTCAMBIARORDENPREGSptOrdenDesde, Long seccionTCAMBIARORDENPREGSptOrdenHasta) throws SystemException;

    Collection<Pregunta> assocOperator006(Seccion instance, Long seccionTCAMBIARORDENPREGSptOrdenDesde, Long seccionTCAMBIARORDENPREGSptOrdenHasta) throws SystemException;

    Collection<Pregunta> assocOperator006Range1(Seccion instance, Long seccionTCAMBIARORDENPREGSptOrdenDesde, Long seccionTCAMBIARORDENPREGSptOrdenHasta) throws SystemException;
    
    SQLPopulation assocOperator006Range1Query(Seccion instance, Long seccionTCAMBIARORDENPREGSptOrdenDesde, Long seccionTCAMBIARORDENPREGSptOrdenHasta) throws SystemException;

    public List<Seccion> executeQuery(String query);

    public List<Seccion> executeQuery(String query, List<SQLParam> params);

    void refreshEntity(Object object);
}
