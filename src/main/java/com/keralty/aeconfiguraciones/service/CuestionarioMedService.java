package com.keralty.aeconfiguraciones.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.keralty.aeconfiguraciones.exception.MinCardinalityException;
import com.keralty.aeconfiguraciones.exception.ModelException;
import com.keralty.aeconfiguraciones.exception.SystemException;
import com.keralty.aeconfiguraciones.global.Constants;
import com.keralty.aeconfiguraciones.global.CuestionarioMedConstants;
import com.keralty.aeconfiguraciones.persistence.AbstractAppEntity;
import com.keralty.aeconfiguraciones.persistence.CuestionarioMed;
import com.keralty.aeconfiguraciones.persistence.oid.CuestionarioMedOid;
import com.keralty.aeconfiguraciones.persistence.oid.SeccionOid;
import com.keralty.aeconfiguraciones.persistence.Seccion;
import com.keralty.aeconfiguraciones.repository.CuestionarioMedJpaRepository;
import com.keralty.aeconfiguraciones.sqlutils.SQLSelect;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereComparison;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereConjunction;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereDisjunction;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereOperandLiteral;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereOperandPathAttribute;
import com.keralty.aeconfiguraciones.viewmodel.FilterRequest;
import com.keralty.aeconfiguraciones.viewmodel.FilterVariableRequest;
import com.keralty.aeconfiguraciones.viewmodel.InstanceResponseViewModel;
import com.keralty.aeconfiguraciones.viewmodel.PopulationResponseViewModel;
import com.keralty.aeconfiguraciones.viewmodel.PreloadResponseViewModel;
import com.keralty.aeconfiguraciones.viewmodel.PreloadViewModel;
import com.keralty.aeconfiguraciones.viewmodel.QueryRequest;
import com.keralty.aeconfiguraciones.viewmodel.QueryResponse;
import com.keralty.aeconfiguraciones.viewmodel.ServiceResponse;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.LoggerFactory;

@Transactional
@ApplicationScoped
public class CuestionarioMedService extends AbstractService {

    CuestionarioMedJpaRepository repository;

    CuestionarioMedService cuestionarioMedSrv;

    SeccionService seccionSrv;

    @Inject
    public CuestionarioMedService(
            CuestionarioMedService cuestionarioMedSrv,
            SeccionService seccionSrv,
            CuestionarioMedJpaRepository repository) {
        this.cuestionarioMedSrv = cuestionarioMedSrv;
        this.seccionSrv = seccionSrv;
        this.repository = repository;
    }

    public List<Map<String, Object>> solveQuery(QueryRequest request) {
        return solveGenericQuery(request, CuestionarioMedConstants.CLASS_NAME);
    }

    private List<CuestionarioMed> solveCuestionarioMedTypedQuery(QueryRequest request) {
        return repository.solveQuery(processQueryRequest(request, CuestionarioMedConstants.CLASS_NAME), request.getPageSize(), request.getPageNumber());
    }
    
    private int solveCuestionarioMedTypedCountQuery(QueryRequest request) {
        return repository.solveCountQuery(processCountQueryRequest(request, CuestionarioMedConstants.CLASS_NAME));
    }

    /*
     * Returns the population of class CuestionarioMed
     *
     * @return Population of class CuestionarioMed
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<CuestionarioMed> get() {
        List<CuestionarioMed> results = new java.util.ArrayList<>();
        repository.findAll().forEach(results::add);
        return results;
    }
    
    /*
     * Gets the preloaded list of objects of class CuestionarioMed
     *
     * @param queryRequest Request object that dictates the search conditions to 
     * retrieve objects
     *
     * @return Preloaded list of objects of class CuestionarioMed
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<PreloadViewModel> getPreload(QueryRequest queryRequest) {
        List<PreloadViewModel> results = new ArrayList<>();
        List<CuestionarioMed> instances = get(queryRequest);
        if(CuestionarioMedConstants.DSICCUESTIONARIOMED.equals(queryRequest.getDisplaySetName())) {
            results = instances.stream().map(x -> new PreloadViewModel(x.getOid().toJson(), x.buildICCuestionarioMed())).collect(Collectors.toList());
        }
        if(CuestionarioMedConstants.DSDSCUESTIONARIOMEDPIU.equals(queryRequest.getDisplaySetName())) {
            results = instances.stream().map(x -> new PreloadViewModel(x.getOid().toJson(), x.buildDSCuestionarioMedPIU())).collect(Collectors.toList());
        }
        if(CuestionarioMedConstants.DSDSCUESTIONARIOMEDIIU.equals(queryRequest.getDisplaySetName())) {
            results = instances.stream().map(x -> new PreloadViewModel(x.getOid().toJson(), x.buildDSCuestionarioMedIIU())).collect(Collectors.toList());
        }
        return results;
    }
    /*
     * Returns the population of class CuestionarioMed that matches certain search criteria
     *
     * @param queryRequest Search criteria
     *
     * @return Population of class CuestionarioMed matching the search criteria
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<CuestionarioMed> get(QueryRequest queryRequest) {
        return solveCuestionarioMedTypedQuery(queryRequest);
    }
    
    /*
     * Returns the list of instances that matches certain search criteria
     *
     * @param queryRequest Request object that dictates the search conditions to 
     * retrieve objects
     *
     * @return List of instances that matches certain search criteria
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public QueryResponse getDynamic(QueryRequest queryRequest) {
        if (queryRequest.getDisplaySetName() == null || queryRequest.getDisplaySetName().isEmpty()) {
            return new PopulationResponseViewModel(solveCuestionarioMedTypedQuery(queryRequest).stream()
                    .map(instance -> buildDynamicObject(instance, queryRequest.getDisplaySetItems()))
                    .collect(Collectors.toList()), queryRequest.getPageSize() != 0 ? solveCuestionarioMedTypedCountQuery(queryRequest) : 0);
        } else {
            return new PreloadResponseViewModel(solveCuestionarioMedTypedQuery(queryRequest).stream()
                    .map(instance -> buildPreloadViewModel(instance, queryRequest.getDisplaySetName()))
                    .collect(Collectors.toList()));
        }
    }

    /*
     * Returns the population of a class with a certain structure
     *
     * @param relatedInstanceJsonOid Json Oid of the related instance
     *
     * @param relatedInstancePath Path to the related instance
     *
     * @param displaySetItems DisplaySet items for each returned instance
     *
     * @return List of instances
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public PopulationResponseViewModel getByRelatedDynamic(String relatedInstanceJsonOid, String relatedInstancePath, String displaySetItems) {
        QueryRequest queryRequest = new QueryRequest(new FilterRequest(Constants.FILTER_BY_RELATED,
                Arrays.asList(new FilterVariableRequest(Constants.FILTER_BY_RELATED_FVAR_PATH, relatedInstancePath),
                        new FilterVariableRequest(Constants.FILTER_BY_RELATED_FVAR_OID, relatedInstanceJsonOid))));
        return new PopulationResponseViewModel(solveCuestionarioMedTypedQuery(queryRequest).stream()
                .map(instance -> buildDynamicObject(instance, displaySetItems))
                .collect(Collectors.toList()));
    }

    /*
     * Returns an instance of CuestionarioMed by ID
     *
     * @param cuestionarioMedNumero Identification field cuestionarioMedNumero
     *
     * @return Instance of CuestionarioMed
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public CuestionarioMed getById(Long cuestionarioMedNumero) {
        return getByOID(new CuestionarioMedOid(cuestionarioMedNumero));
    }
    
    /*
     * Returns an instance of CuestionarioMed by OID
     *
     * @param oid Object IDentifier
     *
     * @return Instance of CuestionarioMed
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public CuestionarioMed getByOID(CuestionarioMedOid oid) {
        Optional<CuestionarioMed> instance = getInstanceByOid(oid);
        return instance.isPresent() ? instance.get() : null;
    }
    
    /*
     * Returns an instance of CuestionarioMed by OID applying horizontal visibility constraints
     *
     * @param oid Object IDentifier
     *
     * @return Instance of CuestionarioMed
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public CuestionarioMed getByOIDWithHV(CuestionarioMedOid oid) {
        Optional<CuestionarioMed> instance = getInstanceByOid(oid);
    	return instance.isPresent() && instance.get().checkHorizontalVisibility(getAgentInstance()) 
    		 ? instance.get() 
    		 : null;
    }
    
    private Optional<CuestionarioMed> getInstanceByOid(CuestionarioMedOid oid) {
        return oid != null && oid.getCuestionarioMedNumero() != null
               ? repository.findById(oid.getCuestionarioMedNumero())
               : Optional.empty();
    }

    /*
     * Returns an instance of CuestionarioMed by OID in JSON format
     *
     * @param jsonOid Object IDentifier in JSON format
     *
     * @return Instance of CuestionarioMed
     */
    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public CuestionarioMed getByJsonOid(String jsonOid) {
        CuestionarioMedOid oid = new CuestionarioMedOid();
        oid.fromJsonString(jsonOid);
        return getByOIDWithHV(oid);
    }

    /*
     * Returns the list of instances of CuestionarioMed related to a given instance through 
     * a given path
     *
     * @param relatedInstance Related instance
     *
     * @param relatedInstancePath Related instance path
     *
     * @return List of instances of CuestionarioMed
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<CuestionarioMed> getByRelated(AbstractAppEntity relatedInstance, String relatedInstancePath) {
        QueryRequest queryRequest = new QueryRequest(new FilterRequest(Constants.FILTER_BY_RELATED,
                Arrays.asList(new FilterVariableRequest(Constants.FILTER_BY_RELATED_FVAR_PATH, relatedInstancePath),
                        new FilterVariableRequest(Constants.FILTER_BY_RELATED_FVAR_OID, relatedInstance.getOid().toJson()))));
        return solveCuestionarioMedTypedQuery(queryRequest);
    }

    /*
     * Returns the list of instances of CuestionarioMed related to a given instance through
     * a given path
     *
     * @param relatedInstance Related instance
     *
     * @param relatedInstancePath Related instance path
     *
     * @return List of instances of CuestionarioMed
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<CuestionarioMed> getInstancesByRelated(AbstractAppEntity relatedInstance, String relatedInstancePath) {
        return getByRelated(relatedInstance, relatedInstancePath);
    }

    /*
     * Returns a set of displayset items for an instance of CuestionarioMed
     *
     * @param oid Object IDentifier
     *
     * @param displaySetItems List of comma-separated displayset items
     *
     * @return Set of displayset items for an instance of CuestionarioMed
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public InstanceResponseViewModel getCuestionarioMedDynamic(CuestionarioMedOid oid, String displaySetItems) {
        return new InstanceResponseViewModel(getByOIDWithHV(oid), displaySetItems);
    }

    /*
     * Returns the supplementary info for a given instance
     *
     * @param oid Instance OID
     *
     * @param dsName DisplaySet name
     *
     * @return Supplementary info for a given instance
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public String getSuppInfo(CuestionarioMedOid oid, String dsName) {
        CuestionarioMed instance = getByOIDWithHV(oid);
        return (instance != null ? getSuppInfo(instance, dsName): "");
    }

    private String getSuppInfo(CuestionarioMed instance, String dsName) {
        if (instance == null || instance.isNull()) return "";
        if (CuestionarioMedConstants.DSICCUESTIONARIOMED.equals(dsName))
            return instance.buildICCuestionarioMed();
        return dsName.isEmpty() ? instance.getOid().toJson() : "";
    }

    public static PreloadViewModel buildPreloadViewModel(CuestionarioMed instance, String dsName) {
        if (CuestionarioMedConstants.DSICCUESTIONARIOMED.equals(dsName))
            return new PreloadViewModel(instance.getOid().toJson(), instance.buildICCuestionarioMed());
        if (CuestionarioMedConstants.DSDSCUESTIONARIOMEDPIU.equals(dsName))
            return new PreloadViewModel(instance.getOid().toJson(), instance.buildDSCuestionarioMedPIU());
        if (CuestionarioMedConstants.DSDSCUESTIONARIOMEDIIU.equals(dsName))
            return new PreloadViewModel(instance.getOid().toJson(), instance.buildDSCuestionarioMedIIU());
        return new PreloadViewModel(instance.getOid().toJson(), "");
    }


    @Override
    protected void applyServiceSIFilters(FilterRequest filterRequest, SQLSelect sql) {
        if ("SuppInfo_IC_CuestionarioMed".equals(filterRequest.getName()))
            suppInfo4ICCuestionarioMed(filterRequest.getFilterVariable("searchText").toString(), sql);
    }



    // IIU Methods

    /*
     * Returns a viewmodel for class CuestionarioMed with displayset DSCuestionarioMedIIU for the instance with a given OID
     * @param oid Class oid
     * @return Viewmodel for class class CuestionarioMed with displayset DSCuestionarioMedIIU
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public com.keralty.aeconfiguraciones.viewmodel.cuestionariomed.ds.DSCuestionarioMedIIU iIUCuestionarioMed(CuestionarioMedOid oid) {
        return new com.keralty.aeconfiguraciones.viewmodel.cuestionariomed.ds.DSCuestionarioMedIIU(getByOIDWithHV(oid));
    }


    /*
     * Solves the query needed for PIUCuestionarioMed
     *
     * @param queryRequest Query request
     * @return List of viewmodels for CuestionarioMed using displayset DSCuestionarioMedPIU that 
     * match the search criteria
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<com.keralty.aeconfiguraciones.viewmodel.cuestionariomed.ds.DSCuestionarioMedPIU> piuPIUCuestionarioMed(QueryRequest queryRequest) {
        return solveCuestionarioMedTypedQuery(queryRequest).stream()
                .map(com.keralty.aeconfiguraciones.viewmodel.cuestionariomed.ds.DSCuestionarioMedPIU::new)
                .collect(Collectors.toList());
    }

    /*
     * Solves the COUNT query needed for PIUCuestionarioMed
     *
     * @param queryRequest Query request
     * @returns Count of instances for PIUCuestionarioMed
     */
    public int getCount4PIUCuestionarioMed(QueryRequest queryRequest) {
        return solveCuestionarioMedTypedQuery(queryRequest).size();
    }

    // Service: crear

    /*
     * Entry point for service crear
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse crear(
            com.keralty.aeconfiguraciones.viewmodel.cuestionariomed.siu.CrearRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = cuestionarioMedSrv.crearTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service crear
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse crearTxn(
            com.keralty.aeconfiguraciones.viewmodel.cuestionariomed.siu.CrearRequest request) throws ModelException {
        return cuestionarioMedSrv.crearImplementation(request);
    }

    /*
     * Implements service crear
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse crearImplementation(
            com.keralty.aeconfiguraciones.viewmodel.cuestionariomed.siu.CrearRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        String cuestionarioMedcrearpatrNombre = request.getCuestionarioMedcrearpatrNombre();

        request.checkArguments();
        CuestionarioMed instance = new CuestionarioMed();
        instance.setCuestionarioMedNumero(Long.valueOf(0));
        instance.setCuestionarioMedNombre(cuestionarioMedcrearpatrNombre);

        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICCuestionarioMed());

        return response;
    }

    // Service: eliminar

    /*
     * Entry point for service eliminar
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse eliminar(
            com.keralty.aeconfiguraciones.viewmodel.cuestionariomed.siu.EliminarRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = cuestionarioMedSrv.eliminarTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service eliminar
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse eliminarTxn(
            com.keralty.aeconfiguraciones.viewmodel.cuestionariomed.siu.EliminarRequest request) throws ModelException {
        request.setCuestionarioMedeliminarpthisCuestionarioMedInstance(cuestionarioMedSrv.getByOIDWithHV(request.getCuestionarioMedeliminarpthisCuestionarioMed()));
        return cuestionarioMedSrv.eliminarImplementation(request);
    }

    /*
     * Implements service eliminar
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse eliminarImplementation(
            com.keralty.aeconfiguraciones.viewmodel.cuestionariomed.siu.EliminarRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        CuestionarioMed cuestionarioMedeliminarpthisCuestionarioMed = request.getCuestionarioMedeliminarpthisCuestionarioMedInstance();

        request.checkArguments();
        CuestionarioMed instance = cuestionarioMedeliminarpthisCuestionarioMed;
        if(cuestionarioMedeliminarpthisCuestionarioMed.getSecciones().size() > 0) {
            throw new MinCardinalityException(CuestionarioMedConstants.CLASS_ALIAS, CuestionarioMedConstants.ROLE_ALIAS_SECCIONES);
        }


        repository.delete(instance);
        
        response.setInstance(instance);
        response.setInstanceSuppInfo(instance.buildICCuestionarioMed());

        return response;
    }


    private void suppInfo4ICCuestionarioMed(String searchText, SQLSelect select) {
        try {
            SQLWhereDisjunction disjunction = new SQLWhereDisjunction();

            SQLWhereComparison comparison1 = new SQLWhereComparison(select);
            comparison1.setOperator("LIKE");
            comparison1.setFirstOperand(new SQLWhereOperandPathAttribute(CuestionarioMedConstants.ATTR_NAME_CUESTIONARIOMEDNOMBRE, Constants.Type.STRING.getTypeName()));
            comparison1.setSecondOperand(new SQLWhereOperandLiteral(searchText, Constants.Type.STRING.getTypeName()));
            disjunction.addComparison(comparison1);

            SQLWhereConjunction conjunction = new SQLWhereConjunction();
            conjunction.addDisjunction(disjunction);
            select.addConjunction(conjunction);
        } catch (Exception e) {
            LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            throw new SystemException(e.getMessage());
        }
    }

    @Override
    protected void applyOrderCriteria(QueryRequest queryRequest, SQLSelect select) {
        applyDSItemSort(queryRequest, select);
        applyDefaultOrderCriteria(queryRequest, select);
    }

    private void applyDefaultOrderCriteria(QueryRequest queryRequest, SQLSelect select) {
        if (queryRequest.getOrderCriteriaName() != null && !queryRequest.getOrderCriteriaName().isBlank())
            return;
        if (queryRequest.getDsItemSort() != null && !queryRequest.getDsItemSort().isBlank())
            return;
        select.getOrderByPart().addItem(Constants.normalizePath(CuestionarioMedConstants.CLASS_NAME, CuestionarioMedConstants.ATTR_FIELD_CUESTIONARIOMEDNUMERO, false), true);
    }
    
    @Override
    protected AbstractAppEntity buildRelatedInstance(String relatedInstanceJsonOid, String relatedInstancePathFirstItem, String remainingPath) {
        AbstractAppEntity relatedInstance = null;
        if (relatedInstancePathFirstItem.isEmpty()) {
            relatedInstance = buildCuestionarioMedRelatedInstance(relatedInstanceJsonOid);
        }
        if (CuestionarioMedConstants.ROLE_NAME_SECCIONES.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildCuestionarioMedRelatedInstance4Seccion(relatedInstanceJsonOid, remainingPath);
        }
        return relatedInstance;
    }
    
    private AbstractAppEntity buildCuestionarioMedRelatedInstance(String relatedInstanceJsonOid) {
    	ObjectMapper mapper = new ObjectMapper();
    	try {
    		return new CuestionarioMed(mapper.readValue(relatedInstanceJsonOid, CuestionarioMedOid.class));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            throw new SystemException(e.getMessage());
    	}
    }

    private AbstractAppEntity buildCuestionarioMedRelatedInstance4Seccion(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new Seccion(mapper.readValue(relatedInstanceJsonOid, SeccionOid.class)): Seccion.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            throw new SystemException(e.getMessage());
    	}
    }

    @Override
    protected void applyFilterByOid(String jsonOid, SQLSelect sql) {
        try {
            CuestionarioMedOid oid = new CuestionarioMedOid().fromJsonString(jsonOid);
            SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
            comparison1.setOperator("=");
            comparison1.setFirstOperand(new SQLWhereOperandPathAttribute(CuestionarioMedConstants.ATTR_FIELD_CUESTIONARIOMEDNUMERO, Constants.Type.AUTONUMERIC.getTypeName()));
            comparison1.setSecondOperand(new SQLWhereOperandLiteral(oid.getCuestionarioMedNumero(), Constants.Type.AUTONUMERIC.getTypeName()));
            sql.addComparison(comparison1);
        } catch (Exception e) {
            LoggerFactory.getLogger(CuestionarioMedService.class).error(e.getMessage());
            throw new SystemException(e.getMessage());
        }
    }

}
