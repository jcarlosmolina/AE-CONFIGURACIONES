package com.keralty.aeconfiguraciones.repository;

import com.keralty.aeconfiguraciones.exception.SystemException;
import com.keralty.aeconfiguraciones.persistence.AgentexProducto;
import com.keralty.aeconfiguraciones.persistence.ContratoC;
import com.keralty.aeconfiguraciones.persistence.Productos;
import com.keralty.aeconfiguraciones.sqlutils.SQLExists;
import com.keralty.aeconfiguraciones.sqlutils.SQLParam;
import com.keralty.aeconfiguraciones.sqlutils.SQLPopulation;
import com.keralty.aeconfiguraciones.sqlutils.SQLSelect;
import java.sql.Date;
import java.util.Collection;
import java.util.List;

public interface ProductosJpaRepositoryCustom {

    List<Productos> solveQuery(SQLSelect query);
    
    List<Productos> solveQuery(SQLSelect query, int pageSize, int pageNumber);
    
    int solveCountQuery(SQLSelect query);

    Boolean assocOperator000(Productos instance) throws SystemException;

    Boolean assocOperator000Range1(Productos instance) throws SystemException;
    
    SQLExists assocOperator000Range1Query(Productos instance) throws SystemException;

    Boolean assocOperator001(Productos instance) throws SystemException;

    Boolean assocOperator001Range1(Productos instance) throws SystemException;
    
    SQLExists assocOperator001Range1Query(Productos instance) throws SystemException;

    Boolean assocOperator002(Productos instance) throws SystemException;

    Boolean assocOperator002Range1(Productos instance) throws SystemException;
    
    SQLExists assocOperator002Range1Query(Productos instance) throws SystemException;

    Boolean assocOperator003(Productos instance) throws SystemException;

    Boolean assocOperator003Range1(Productos instance) throws SystemException;
    
    SQLExists assocOperator003Range1Query(Productos instance) throws SystemException;

    Boolean assocOperator004(Productos instance) throws SystemException;

    Boolean assocOperator004Range1(Productos instance) throws SystemException;
    
    SQLExists assocOperator004Range1Query(Productos instance) throws SystemException;

    Boolean assocOperator005(Productos instance) throws SystemException;

    Boolean assocOperator005Range1(Productos instance) throws SystemException;
    
    SQLExists assocOperator005Range1Query(Productos instance) throws SystemException;

    Boolean assocOperator006(ContratoC productosdummyVerificarContratoIncpContratoC) throws SystemException;

    Boolean assocOperator006Range1(ContratoC productosdummyVerificarContratoIncpContratoC) throws SystemException;
    
    SQLExists assocOperator006Range1Query(ContratoC productosdummyVerificarContratoIncpContratoC, SQLSelect superQuery) throws SystemException;

    Boolean assocOperator007(ContratoC productosdummyVerificarContratoIncpContratoC, String productosdummyVerificarContratoIncpNumFamilia) throws SystemException;

    Boolean assocOperator007Range1(ContratoC productosdummyVerificarContratoIncpContratoC, String productosdummyVerificarContratoIncpNumFamilia) throws SystemException;
    
    SQLExists assocOperator007Range1Query(ContratoC productosdummyVerificarContratoIncpContratoC, String productosdummyVerificarContratoIncpNumFamilia, SQLSelect superQuery) throws SystemException;

    Boolean assocOperator008(ContratoC productosdummyVerificarContratoIncpContratoC, String productosdummyVerificarContratoIncpNumFamilia) throws SystemException;

    Boolean assocOperator008Range1(ContratoC productosdummyVerificarContratoIncpContratoC, String productosdummyVerificarContratoIncpNumFamilia) throws SystemException;
    
    SQLExists assocOperator008Range1Query(ContratoC productosdummyVerificarContratoIncpContratoC, String productosdummyVerificarContratoIncpNumFamilia, SQLSelect superQuery) throws SystemException;

    Boolean assocOperator009(ContratoC productosdummyVerificarContratoIncpContratoC, String productosdummyVerificarContratoIncpNumFamilia, Date productosdummyVerificarContratoIncpFechaInicioContrato) throws SystemException;

    Boolean assocOperator009Range1(ContratoC productosdummyVerificarContratoIncpContratoC, String productosdummyVerificarContratoIncpNumFamilia, Date productosdummyVerificarContratoIncpFechaInicioContrato) throws SystemException;
    
    SQLExists assocOperator009Range1Query(ContratoC productosdummyVerificarContratoIncpContratoC, String productosdummyVerificarContratoIncpNumFamilia, Date productosdummyVerificarContratoIncpFechaInicioContrato, SQLSelect superQuery) throws SystemException;

    Boolean assocOperator010(Productos instance) throws SystemException;

    Collection<AgentexProducto> assocOperator010Range1(Productos instance) throws SystemException;
    
    SQLPopulation assocOperator010Range1Query(Productos instance) throws SystemException;

    Boolean assocOperator011(Productos instance) throws SystemException;

    Collection<AgentexProducto> assocOperator011Range1(Productos instance) throws SystemException;
    
    SQLPopulation assocOperator011Range1Query(Productos instance) throws SystemException;

    Boolean assocOperator012(Productos instance) throws SystemException;

    Collection<AgentexProducto> assocOperator012Range1(Productos instance) throws SystemException;
    
    SQLPopulation assocOperator012Range1Query(Productos instance) throws SystemException;

    Boolean assocOperator013(Productos instance) throws SystemException;

    Collection<AgentexProducto> assocOperator013Range1(Productos instance) throws SystemException;
    
    SQLPopulation assocOperator013Range1Query(Productos instance) throws SystemException;

    public List<Productos> executeQuery(String query);

    public List<Productos> executeQuery(String query, List<SQLParam> params);

    void refreshEntity(Object object);
}
