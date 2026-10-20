package com.keralty.aeconfiguraciones.repository;

import com.keralty.aeconfiguraciones.exception.SystemException;
import com.keralty.aeconfiguraciones.persistence.Municipio;
import com.keralty.aeconfiguraciones.persistence.Usuario;
import com.keralty.aeconfiguraciones.sqlutils.SQLExists;
import com.keralty.aeconfiguraciones.sqlutils.SQLParam;
import com.keralty.aeconfiguraciones.sqlutils.SQLSelect;
import java.util.List;

public interface MunicipioJpaRepositoryCustom {

    List<Municipio> solveQuery(SQLSelect query);
    
    List<Municipio> solveQuery(SQLSelect query, int pageSize, int pageNumber);
    
    int solveCountQuery(SQLSelect query);

    Boolean assocOperator000(Municipio instance) throws SystemException;

    Boolean assocOperator000Range1(Municipio instance) throws SystemException;
    
    SQLExists assocOperator000Range1Query(Municipio instance) throws SystemException;

    Boolean assocOperator001(Municipio instance) throws SystemException;

    Boolean assocOperator001Range1(Municipio instance) throws SystemException;
    
    SQLExists assocOperator001Range1Query(Municipio instance) throws SystemException;

    Boolean assocOperator002(Municipio instance) throws SystemException;

    Boolean assocOperator002Range1(Municipio instance) throws SystemException;
    
    SQLExists assocOperator002Range1Query(Municipio instance) throws SystemException;

    Boolean assocOperator004(Municipio instance) throws SystemException;

    Boolean assocOperator004Range1(Municipio instance) throws SystemException;
    
    SQLExists assocOperator004Range1Query(Municipio instance) throws SystemException;

    Boolean assocOperator005(Usuario usuarioTMODUSUARIOpthisUsuario) throws SystemException;

    Boolean assocOperator005Range1(Usuario usuarioTMODUSUARIOpthisUsuario) throws SystemException;
    
    SQLExists assocOperator005Range1Query(Usuario usuarioTMODUSUARIOpthisUsuario, SQLSelect superQuery) throws SystemException;

    public List<Municipio> executeQuery(String query);

    public List<Municipio> executeQuery(String query, List<SQLParam> params);

    void refreshEntity(Object object);
}
