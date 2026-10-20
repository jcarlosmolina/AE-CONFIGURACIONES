package com.keralty.aeconfiguraciones.repository;

import com.keralty.aeconfiguraciones.exception.SystemException;
import com.keralty.aeconfiguraciones.persistence.Dominio;
import com.keralty.aeconfiguraciones.persistence.LogDatosDominio;
import com.keralty.aeconfiguraciones.persistence.LogGruposUsuarios;
import com.keralty.aeconfiguraciones.persistence.LogIncompatibilidades;
import com.keralty.aeconfiguraciones.persistence.ParentescoDom;
import com.keralty.aeconfiguraciones.persistence.Periodicidad;
import com.keralty.aeconfiguraciones.persistence.Planes;
import com.keralty.aeconfiguraciones.persistence.PlantillaEmail;
import com.keralty.aeconfiguraciones.persistence.PlantillaReporte;
import com.keralty.aeconfiguraciones.persistence.Productos;
import com.keralty.aeconfiguraciones.persistence.Prorrateo;
import com.keralty.aeconfiguraciones.persistence.Rol;
import com.keralty.aeconfiguraciones.sqlutils.SQLCount;
import com.keralty.aeconfiguraciones.sqlutils.SQLExists;
import com.keralty.aeconfiguraciones.sqlutils.SQLParam;
import com.keralty.aeconfiguraciones.sqlutils.SQLPopulation;
import com.keralty.aeconfiguraciones.sqlutils.SQLSelect;
import java.util.Collection;
import java.util.List;

public interface DominioJpaRepositoryCustom {

    List<Dominio> solveQuery(SQLSelect query);
    
    List<Dominio> solveQuery(SQLSelect query, int pageSize, int pageNumber);
    
    int solveCountQuery(SQLSelect query);

    Boolean assocOperator000(Rol voRol) throws SystemException;

    Boolean assocOperator000Range1(Rol voRol) throws SystemException;
    
    SQLExists assocOperator000Range1Query(Rol voRol, SQLSelect superQuery) throws SystemException;

    Boolean assocOperator001(Productos dominiocrearpagrProductoT, Planes dominiocrearpagrPlanT) throws SystemException;

    Boolean assocOperator001Range1(Productos dominiocrearpagrProductoT, Planes dominiocrearpagrPlanT) throws SystemException;
    
    SQLExists assocOperator001Range1Query(Productos dominiocrearpagrProductoT, Planes dominiocrearpagrPlanT, SQLSelect superQuery) throws SystemException;

    Boolean assocOperator002(Planes dominiocrearpagrPlanT, Productos dominiocrearpagrProductoT) throws SystemException;

    Boolean assocOperator002Range1(Planes dominiocrearpagrPlanT, Productos dominiocrearpagrProductoT) throws SystemException;
    
    SQLExists assocOperator002Range1Query(Planes dominiocrearpagrPlanT, Productos dominiocrearpagrProductoT, SQLSelect superQuery) throws SystemException;

    Long assocOperator003(Dominio instance, PlantillaReporte dominioTADDREPORTEptpagrPlantillaReporte) throws SystemException;

    Long assocOperator003Range1(Dominio instance, PlantillaReporte dominioTADDREPORTEptpagrPlantillaReporte) throws SystemException;
    
    SQLCount assocOperator003Range1Query(Dominio instance, PlantillaReporte dominioTADDREPORTEptpagrPlantillaReporte) throws SystemException;

    Long assocOperator004(Dominio instance, PlantillaReporte dominioTADDREPORTEptpagrPlantillaReporte) throws SystemException;

    Long assocOperator004Range1(Dominio instance, PlantillaReporte dominioTADDREPORTEptpagrPlantillaReporte) throws SystemException;
    
    SQLCount assocOperator004Range1Query(Dominio instance, PlantillaReporte dominioTADDREPORTEptpagrPlantillaReporte) throws SystemException;

    Long assocOperator005(Dominio instance, Dominio dominioInsIncompatiblepevcDominio) throws SystemException;

    Long assocOperator005Range1(Dominio instance, Dominio dominioInsIncompatiblepevcDominio) throws SystemException;
    
    SQLCount assocOperator005Range1Query(Dominio instance, Dominio dominioInsIncompatiblepevcDominio) throws SystemException;

    Collection<ParentescoDom> assocOperator006(Dominio instance) throws SystemException;

    Collection<ParentescoDom> assocOperator006Range1(Dominio instance) throws SystemException;
    
    SQLPopulation assocOperator006Range1Query(Dominio instance) throws SystemException;

    Collection<Periodicidad> assocOperator007(Dominio instance, Dominio dominioTELIMINARpthisDominio) throws SystemException;

    Collection<Periodicidad> assocOperator007Range1(Dominio instance) throws SystemException;
    
    SQLPopulation assocOperator007Range1Query(Dominio instance) throws SystemException;

    Collection<Rol> assocOperator008(Dominio instance, Dominio dominioTELIMINARpthisDominio) throws SystemException;

    Collection<Rol> assocOperator008Range1(Dominio instance) throws SystemException;
    
    SQLPopulation assocOperator008Range1Query(Dominio instance) throws SystemException;

    Collection<Prorrateo> assocOperator009(Dominio instance) throws SystemException;

    Collection<Prorrateo> assocOperator009Range1(Dominio instance) throws SystemException;
    
    SQLPopulation assocOperator009Range1Query(Dominio instance) throws SystemException;

    Collection<LogDatosDominio> assocOperator010(Dominio instance) throws SystemException;

    Collection<LogDatosDominio> assocOperator010Range1(Dominio instance) throws SystemException;
    
    SQLPopulation assocOperator010Range1Query(Dominio instance) throws SystemException;

    Collection<LogGruposUsuarios> assocOperator011(Dominio instance) throws SystemException;

    Collection<LogGruposUsuarios> assocOperator011Range1(Dominio instance) throws SystemException;
    
    SQLPopulation assocOperator011Range1Query(Dominio instance) throws SystemException;

    Collection<LogIncompatibilidades> assocOperator012(Dominio instance) throws SystemException;

    Collection<LogIncompatibilidades> assocOperator012Range1(Dominio instance) throws SystemException;
    
    SQLPopulation assocOperator012Range1Query(Dominio instance) throws SystemException;

    Collection<PlantillaEmail> assocOperator013(Dominio instance, Dominio dominioTINSPLANTILLApthisDominio, PlantillaEmail dominioTINSPLANTILLAptPlantilla) throws SystemException;

    Collection<PlantillaEmail> assocOperator013Range1(Dominio instance, PlantillaEmail dominioTINSPLANTILLAptPlantilla) throws SystemException;
    
    SQLPopulation assocOperator013Range1Query(Dominio instance, PlantillaEmail dominioTINSPLANTILLAptPlantilla) throws SystemException;

    Boolean assocOperator015(Dominio instance) throws SystemException;

    Boolean assocOperator015Range1(Dominio instance) throws SystemException;
    
    SQLExists assocOperator015Range1Query(Dominio instance) throws SystemException;

    Boolean assocOperator017(Dominio instance) throws SystemException;

    Boolean assocOperator017Range1(Dominio instance) throws SystemException;
    
    SQLExists assocOperator017Range1Query(Dominio instance) throws SystemException;

    public List<Dominio> executeQuery(String query);

    public List<Dominio> executeQuery(String query, List<SQLParam> params);

    void refreshEntity(Object object);
}
