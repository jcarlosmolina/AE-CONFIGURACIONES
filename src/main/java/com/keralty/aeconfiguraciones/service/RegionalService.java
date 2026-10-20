package com.keralty.aeconfiguraciones.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.keralty.aeconfiguraciones.exception.InstanceExistsException;
import com.keralty.aeconfiguraciones.exception.MinCardinalityException;
import com.keralty.aeconfiguraciones.exception.ModelException;
import com.keralty.aeconfiguraciones.exception.PreconditionException;
import com.keralty.aeconfiguraciones.exception.SystemException;
import com.keralty.aeconfiguraciones.global.Constants;
import com.keralty.aeconfiguraciones.global.RegionalConstants;
import com.keralty.aeconfiguraciones.persistence.AbstractAppEntity;
import com.keralty.aeconfiguraciones.persistence.DirSucursalC;
import com.keralty.aeconfiguraciones.persistence.oid.DirSucursalCOid;
import com.keralty.aeconfiguraciones.persistence.oid.RegionalOid;
import com.keralty.aeconfiguraciones.persistence.oid.RespRegionalOid;
import com.keralty.aeconfiguraciones.persistence.oid.SucursalOid;
import com.keralty.aeconfiguraciones.persistence.Regional;
import com.keralty.aeconfiguraciones.persistence.RespRegional;
import com.keralty.aeconfiguraciones.persistence.Sucursal;
import com.keralty.aeconfiguraciones.repository.RegionalJpaRepository;
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
public class RegionalService extends AbstractService {

    RegionalJpaRepository repository;

    RegionalService regionalSrv;

    SucursalService sucursalSrv;

    DirSucursalCService dirSucursalCSrv;

    @Inject
    public RegionalService(
            RegionalService regionalSrv,
            SucursalService sucursalSrv,
            DirSucursalCService dirSucursalCSrv,
            RegionalJpaRepository repository) {
        this.regionalSrv = regionalSrv;
        this.sucursalSrv = sucursalSrv;
        this.dirSucursalCSrv = dirSucursalCSrv;
        this.repository = repository;
    }

    public List<Map<String, Object>> solveQuery(QueryRequest request) {
        return solveGenericQuery(request, RegionalConstants.CLASS_NAME);
    }

    private List<Regional> solveRegionalTypedQuery(QueryRequest request) {
        return repository.solveQuery(processQueryRequest(request, RegionalConstants.CLASS_NAME), request.getPageSize(), request.getPageNumber());
    }
    
    private int solveRegionalTypedCountQuery(QueryRequest request) {
        return repository.solveCountQuery(processCountQueryRequest(request, RegionalConstants.CLASS_NAME));
    }

    /*
     * Returns the population of class Regional
     *
     * @return Population of class Regional
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<Regional> get() {
        List<Regional> results = new java.util.ArrayList<>();
        repository.findAll().forEach(results::add);
        return results;
    }
    
    /*
     * Gets the preloaded list of objects of class Regional
     *
     * @param queryRequest Request object that dictates the search conditions to 
     * retrieve objects
     *
     * @return Preloaded list of objects of class Regional
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<PreloadViewModel> getPreload(QueryRequest queryRequest) {
        List<PreloadViewModel> results = new ArrayList<>();
        List<Regional> instances = get(queryRequest);
        if(RegionalConstants.DSICREGIONAL.equals(queryRequest.getDisplaySetName())) {
            results = instances.stream().map(x -> new PreloadViewModel(x.getOid().toJson(), x.buildICRegional())).collect(Collectors.toList());
        }
        return results;
    }
    /*
     * Returns the population of class Regional that matches certain search criteria
     *
     * @param queryRequest Search criteria
     *
     * @return Population of class Regional matching the search criteria
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<Regional> get(QueryRequest queryRequest) {
        return solveRegionalTypedQuery(queryRequest);
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
            return new PopulationResponseViewModel(solveRegionalTypedQuery(queryRequest).stream()
                    .map(instance -> buildDynamicObject(instance, queryRequest.getDisplaySetItems()))
                    .collect(Collectors.toList()), queryRequest.getPageSize() != 0 ? solveRegionalTypedCountQuery(queryRequest) : 0);
        } else {
            return new PreloadResponseViewModel(solveRegionalTypedQuery(queryRequest).stream()
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
        return new PopulationResponseViewModel(solveRegionalTypedQuery(queryRequest).stream()
                .map(instance -> buildDynamicObject(instance, displaySetItems))
                .collect(Collectors.toList()));
    }

    /*
     * Returns an instance of Regional by ID
     *
     * @param regionalCodigo Identification field regionalCodigo
     *
     * @return Instance of Regional
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public Regional getById(Long regionalCodigo) {
        return getByOID(new RegionalOid(regionalCodigo));
    }
    
    /*
     * Returns an instance of Regional by OID
     *
     * @param oid Object IDentifier
     *
     * @return Instance of Regional
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public Regional getByOID(RegionalOid oid) {
        Optional<Regional> instance = getInstanceByOid(oid);
        return instance.isPresent() ? instance.get() : null;
    }
    
    /*
     * Returns an instance of Regional by OID applying horizontal visibility constraints
     *
     * @param oid Object IDentifier
     *
     * @return Instance of Regional
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public Regional getByOIDWithHV(RegionalOid oid) {
        Optional<Regional> instance = getInstanceByOid(oid);
    	return instance.isPresent() && instance.get().checkHorizontalVisibility(getAgentInstance()) 
    		 ? instance.get() 
    		 : null;
    }
    
    private Optional<Regional> getInstanceByOid(RegionalOid oid) {
        return oid != null && oid.getRegionalCodigo() != null
               ? repository.findById(oid.getRegionalCodigo())
               : Optional.empty();
    }

    /*
     * Returns an instance of Regional by OID in JSON format
     *
     * @param jsonOid Object IDentifier in JSON format
     *
     * @return Instance of Regional
     */
    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public Regional getByJsonOid(String jsonOid) {
        RegionalOid oid = new RegionalOid();
        oid.fromJsonString(jsonOid);
        return getByOIDWithHV(oid);
    }

    /*
     * Returns the list of instances of Regional related to a given instance through 
     * a given path
     *
     * @param relatedInstance Related instance
     *
     * @param relatedInstancePath Related instance path
     *
     * @return List of instances of Regional
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<Regional> getByRelated(AbstractAppEntity relatedInstance, String relatedInstancePath) {
        QueryRequest queryRequest = new QueryRequest(new FilterRequest(Constants.FILTER_BY_RELATED,
                Arrays.asList(new FilterVariableRequest(Constants.FILTER_BY_RELATED_FVAR_PATH, relatedInstancePath),
                        new FilterVariableRequest(Constants.FILTER_BY_RELATED_FVAR_OID, relatedInstance.getOid().toJson()))));
        return solveRegionalTypedQuery(queryRequest);
    }

    /*
     * Returns the list of instances of Regional related to a given instance through
     * a given path
     *
     * @param relatedInstance Related instance
     *
     * @param relatedInstancePath Related instance path
     *
     * @return List of instances of Regional
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<Regional> getInstancesByRelated(AbstractAppEntity relatedInstance, String relatedInstancePath) {
        return getByRelated(relatedInstance, relatedInstancePath);
    }

    /*
     * Returns a set of displayset items for an instance of Regional
     *
     * @param oid Object IDentifier
     *
     * @param displaySetItems List of comma-separated displayset items
     *
     * @return Set of displayset items for an instance of Regional
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public InstanceResponseViewModel getRegionalDynamic(RegionalOid oid, String displaySetItems) {
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
    public String getSuppInfo(RegionalOid oid, String dsName) {
        Regional instance = getByOIDWithHV(oid);
        return (instance != null ? getSuppInfo(instance, dsName): "");
    }

    private String getSuppInfo(Regional instance, String dsName) {
        if (instance == null || instance.isNull()) return "";
        if (RegionalConstants.DSICREGIONAL.equals(dsName))
            return instance.buildICRegional();
        return dsName.isEmpty() ? instance.getOid().toJson() : "";
    }

    public static PreloadViewModel buildPreloadViewModel(Regional instance, String dsName) {
        if (RegionalConstants.DSICREGIONAL.equals(dsName))
            return new PreloadViewModel(instance.getOid().toJson(), instance.buildICRegional());
        return new PreloadViewModel(instance.getOid().toJson(), "");
    }


    @Override
    protected void applyServiceSIFilters(FilterRequest filterRequest, SQLSelect sql) {
        if ("SuppInfo_IC_Regional".equals(filterRequest.getName()))
            suppInfo4ICRegional(filterRequest.getFilterVariable("searchText").toString(), sql);
    }



    // IIU Methods


    /*
     * Solves the query needed for PIURegional
     *
     * @param queryRequest Query request
     * @return List of viewmodels for Regional using displayset AutoDS that 
     * match the search criteria
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<com.keralty.aeconfiguraciones.viewmodel.regional.ds.AutoDS> piuPIURegional(QueryRequest queryRequest) {
        return solveRegionalTypedQuery(queryRequest).stream()
                .map(com.keralty.aeconfiguraciones.viewmodel.regional.ds.AutoDS::new)
                .collect(Collectors.toList());
    }

    /*
     * Solves the COUNT query needed for PIURegional
     *
     * @param queryRequest Query request
     * @returns Count of instances for PIURegional
     */
    public int getCount4PIURegional(QueryRequest queryRequest) {
        return solveRegionalTypedQuery(queryRequest).size();
    }

    /*
     * Solves the query needed for PIURegionalObs
     *
     * @param queryRequest Query request
     * @return List of viewmodels for Regional using displayset AutoDS that 
     * match the search criteria
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<com.keralty.aeconfiguraciones.viewmodel.regional.ds.AutoDS> piuPIURegionalObs(QueryRequest queryRequest) {
        return solveRegionalTypedQuery(queryRequest).stream()
                .map(com.keralty.aeconfiguraciones.viewmodel.regional.ds.AutoDS::new)
                .collect(Collectors.toList());
    }

    /*
     * Solves the COUNT query needed for PIURegionalObs
     *
     * @param queryRequest Query request
     * @returns Count of instances for PIURegionalObs
     */
    public int getCount4PIURegionalObs(QueryRequest queryRequest) {
        return solveRegionalTypedQuery(queryRequest).size();
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
            com.keralty.aeconfiguraciones.viewmodel.regional.siu.CrearRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = regionalSrv.crearTxn(request);
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
            com.keralty.aeconfiguraciones.viewmodel.regional.siu.CrearRequest request) throws ModelException {
        request.setRegionalcrearpagrRespRegionalInstance(respRegionalSrv.getByOIDWithHV(request.getRegionalcrearpagrRespRegional()));
        return regionalSrv.crearImplementation(request);
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
            com.keralty.aeconfiguraciones.viewmodel.regional.siu.CrearRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Long regionalcrearpatrCodigo = request.getRegionalcrearpatrCodigo();
        String regionalcrearpatrDescripcion = request.getRegionalcrearpatrDescripcion();

        request.checkArguments();
        Regional instance = new Regional();

        instance.setRegionalCodigo(regionalcrearpatrCodigo);
        instance.setRegionalDescripcion(regionalcrearpatrDescripcion);
        if (regionalSrv.getByOID(instance.getOid()) != null) {
            throw new InstanceExistsException(RegionalConstants.CLASS_ALIAS);
        }

        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICRegional());

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
            com.keralty.aeconfiguraciones.viewmodel.regional.siu.EliminarRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = regionalSrv.eliminarTxn(request);
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
            com.keralty.aeconfiguraciones.viewmodel.regional.siu.EliminarRequest request) throws ModelException {
        request.setRegionaleliminarpthisRegionalInstance(regionalSrv.getByOIDWithHV(request.getRegionaleliminarpthisRegional()));
        return regionalSrv.eliminarImplementation(request);
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
            com.keralty.aeconfiguraciones.viewmodel.regional.siu.EliminarRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Regional regionaleliminarpthisRegional = request.getRegionaleliminarpthisRegionalInstance();

        request.checkArguments();

        eliminarPreconditions(request);
        Regional instance = regionaleliminarpthisRegional;
        if(regionaleliminarpthisRegional.getRespRegional() != null && !regionaleliminarpthisRegional.getRespRegional().isNull()) {
            throw new MinCardinalityException(RegionalConstants.CLASS_ALIAS, RegionalConstants.ROLE_ALIAS_RESPREGIONAL);
        }
        if(regionaleliminarpthisRegional.getSucursales().size() > 0) {
            throw new MinCardinalityException(RegionalConstants.CLASS_ALIAS, RegionalConstants.ROLE_ALIAS_SUCURSALES);
        }
        if(regionaleliminarpthisRegional.getDirSucursalC().size() > 0) {
            throw new MinCardinalityException(RegionalConstants.CLASS_ALIAS, RegionalConstants.ROLE_ALIAS_DIRSUCURSALC);
        }


        repository.delete(instance);
        
        response.setInstance(instance);
        response.setInstanceSuppInfo(instance.buildICRegional());

        return response;
    }

    /*
     * Checks service eliminar preconditions individually
     *
     * @param request Service request viewmodel
     *
     * @return Service preconditions evaluation result
     */
    public ServiceResponse eliminarCheckPreconditions(com.keralty.aeconfiguraciones.viewmodel.regional.siu.EliminarRequest request) {
        ServiceResponse response = new ServiceResponse();
        request.setRegionaleliminarpthisRegionalInstance(regionalSrv.getByOIDWithHV(request.getRegionaleliminarpthisRegional()));
        try {
            if("Clas_1431872733184951Pre_1".equals(request.getPreconditionId()))
                checkClas1431872733184951Pre1(request);
            if("Clas_1431872733184951Pre_2".equals(request.getPreconditionId()))
                checkClas1431872733184951Pre2(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    private void eliminarPreconditions(
            com.keralty.aeconfiguraciones.viewmodel.regional.siu.EliminarRequest request) 
            throws ModelException {
        checkClas1431872733184951Pre1(request);
        checkClas1431872733184951Pre2(request);
    }

    private void checkClas1431872733184951Pre1 (
            com.keralty.aeconfiguraciones.viewmodel.regional.siu.EliminarRequest request) 
            throws ModelException {
        // EXIST( RespRegional ) = false

        Regional regionaleliminarpthisRegional = request.getRegionaleliminarpthisRegionalInstance();
        try {
            boolean preconditionHolds = repository.assocOperator000(regionaleliminarpthisRegional).equals(false);
            if (!preconditionHolds) throw new PreconditionException("No se puede borrar el regional porque ya tiene asignado un responsable en Afiliación Electrónica");
        } catch (SystemException e) {
            LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
        }
    }

    private void checkClas1431872733184951Pre2 (
            com.keralty.aeconfiguraciones.viewmodel.regional.siu.EliminarRequest request) 
            throws ModelException {
        // EXIST( Sucursales ) = false

        Regional regionaleliminarpthisRegional = request.getRegionaleliminarpthisRegionalInstance();
        try {
            boolean preconditionHolds = repository.assocOperator001(regionaleliminarpthisRegional).equals(false);
            if (!preconditionHolds) throw new PreconditionException("No se puede borrar el regional porque existen sucursales registradas que pertenecen a este regional");
        } catch (SystemException e) {
            LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
        }
    }

    // Service: sincronizar

    /*
     * Entry point for service sincronizar
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse sincronizar(
            com.keralty.aeconfiguraciones.viewmodel.regional.siu.SincronizarRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = regionalSrv.sincronizarTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service sincronizar
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse sincronizarTxn(
            com.keralty.aeconfiguraciones.viewmodel.regional.siu.SincronizarRequest request) throws ModelException {
        request.setRegionalsincronizarpthisRegionalInstance(regionalSrv.getByOIDWithHV(request.getRegionalsincronizarpthisRegional()));
        return regionalSrv.sincronizarImplementation(request);
    }

    /*
     * Implements service sincronizar
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse sincronizarImplementation(
            com.keralty.aeconfiguraciones.viewmodel.regional.siu.SincronizarRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Regional regionalsincronizarpthisRegional = request.getRegionalsincronizarpthisRegionalInstance();
        String regionalsincronizarpDescripcion = request.getRegionalsincronizarpDescripcion();

        request.checkArguments();
        Regional instance = regionalsincronizarpthisRegional;
        instance.setRegionalDescripcion(regionalsincronizarpDescripcion);


        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICRegional());

        return response;
    }

    // Service: InsRegionalResponsable

    /*
     * Entry point for service InsRegionalResponsable
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse insRegionalResponsable(
            com.keralty.aeconfiguraciones.viewmodel.regional.siu.InsRegionalResponsableRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = regionalSrv.insRegionalResponsableTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service InsRegionalResponsable
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse insRegionalResponsableTxn(
            com.keralty.aeconfiguraciones.viewmodel.regional.siu.InsRegionalResponsableRequest request) throws ModelException {
        request.setRegionalInsRegionalResponsablepthisRegionalInstance(regionalSrv.getByOIDWithHV(request.getRegionalInsRegionalResponsablepthisRegional()));
        request.setRegionalInsRegionalResponsablepevcRespRegionalInstance(respRegionalSrv.getByOIDWithHV(request.getRegionalInsRegionalResponsablepevcRespRegional()));
        return regionalSrv.insRegionalResponsableImplementation(request, true);
    }

    /*
     * Implements service InsRegionalResponsable
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse insRegionalResponsableImplementation(
            com.keralty.aeconfiguraciones.viewmodel.regional.siu.InsRegionalResponsableRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Regional regionalInsRegionalResponsablepthisRegional = request.getRegionalInsRegionalResponsablepthisRegionalInstance();
        RespRegional regionalInsRegionalResponsablepevcRespRegional = request.getRegionalInsRegionalResponsablepevcRespRegionalInstance();

        request.checkArguments();
        Regional instance = regionalInsRegionalResponsablepthisRegional;

        instance.add2RespRegional(regionalInsRegionalResponsablepevcRespRegional);
        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.respregional.siu.InsRegionalResponsableRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.respregional.siu.InsRegionalResponsableRequest();
            relatedRequest.setRespRegionalInsRegionalResponsablepthisRespRegionalInstance(regionalInsRegionalResponsablepevcRespRegional);
            relatedRequest.setRespRegionalInsRegionalResponsablepevcRegionalInstance(regionalInsRegionalResponsablepthisRegional);
            respRegionalSrv.insRegionalResponsableImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICRegional());

        return response;
    }

    // Service: CambRegionalSucursal

    /*
     * Entry point for service CambRegionalSucursal
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse cambRegionalSucursal(
            com.keralty.aeconfiguraciones.viewmodel.regional.siu.CambRegionalSucursalRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = regionalSrv.cambRegionalSucursalTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service CambRegionalSucursal
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse cambRegionalSucursalTxn(
            com.keralty.aeconfiguraciones.viewmodel.regional.siu.CambRegionalSucursalRequest request) throws ModelException {
        request.setRegionalCambRegionalSucursalpthisRegionalInstance(regionalSrv.getByOIDWithHV(request.getRegionalCambRegionalSucursalpthisRegional()));
        request.setRegionalCambRegionalSucursalpevcSucursalInstance(sucursalSrv.getByOIDWithHV(request.getRegionalCambRegionalSucursalpevcSucursal()));
        return regionalSrv.cambRegionalSucursalImplementation(request, true);
    }

    /*
     * Implements service CambRegionalSucursal
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse cambRegionalSucursalImplementation(
            com.keralty.aeconfiguraciones.viewmodel.regional.siu.CambRegionalSucursalRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Regional regionalCambRegionalSucursalpthisRegional = request.getRegionalCambRegionalSucursalpthisRegionalInstance();
        Sucursal regionalCambRegionalSucursalpevcSucursal = request.getRegionalCambRegionalSucursalpevcSucursalInstance();

        request.checkArguments();
        Regional instance = regionalCambRegionalSucursalpthisRegional;

        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.sucursal.siu.CambRegionalSucursalRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.sucursal.siu.CambRegionalSucursalRequest();
            relatedRequest.setSucursalCambRegionalSucursalpthisSucursalInstance(regionalCambRegionalSucursalpevcSucursal);
            relatedRequest.setSucursalCambRegionalSucursalpevcRegionalInstance(regionalCambRegionalSucursalpthisRegional);
            sucursalSrv.cambRegionalSucursalImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICRegional());

        return response;
    }

    // Service: DelRegionalResponsable

    /*
     * Entry point for service DelRegionalResponsable
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse delRegionalResponsable(
            com.keralty.aeconfiguraciones.viewmodel.regional.siu.DelRegionalResponsableRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = regionalSrv.delRegionalResponsableTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service DelRegionalResponsable
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse delRegionalResponsableTxn(
            com.keralty.aeconfiguraciones.viewmodel.regional.siu.DelRegionalResponsableRequest request) throws ModelException {
        request.setRegionalDelRegionalResponsablepthisRegionalInstance(regionalSrv.getByOIDWithHV(request.getRegionalDelRegionalResponsablepthisRegional()));
        request.setRegionalDelRegionalResponsablepevcRespRegionalInstance(respRegionalSrv.getByOIDWithHV(request.getRegionalDelRegionalResponsablepevcRespRegional()));
        return regionalSrv.delRegionalResponsableImplementation(request, true);
    }

    /*
     * Implements service DelRegionalResponsable
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse delRegionalResponsableImplementation(
            com.keralty.aeconfiguraciones.viewmodel.regional.siu.DelRegionalResponsableRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Regional regionalDelRegionalResponsablepthisRegional = request.getRegionalDelRegionalResponsablepthisRegionalInstance();
        RespRegional regionalDelRegionalResponsablepevcRespRegional = request.getRegionalDelRegionalResponsablepevcRespRegionalInstance();

        request.checkArguments();
        Regional instance = regionalDelRegionalResponsablepthisRegional;

        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.respregional.siu.DelRegionalResponsableRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.respregional.siu.DelRegionalResponsableRequest();
            relatedRequest.setRespRegionalDelRegionalResponsablepthisRespRegionalInstance(regionalDelRegionalResponsablepevcRespRegional);
            relatedRequest.setRespRegionalDelRegionalResponsablepevcRegionalInstance(regionalDelRegionalResponsablepthisRegional);
            respRegionalSrv.delRegionalResponsableImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICRegional());

        return response;
    }

    // Service: InsRegionalDirSucursal

    /*
     * Entry point for service InsRegionalDirSucursal
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse insRegionalDirSucursal(
            com.keralty.aeconfiguraciones.viewmodel.regional.siu.InsRegionalDirSucursalRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = regionalSrv.insRegionalDirSucursalTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service InsRegionalDirSucursal
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse insRegionalDirSucursalTxn(
            com.keralty.aeconfiguraciones.viewmodel.regional.siu.InsRegionalDirSucursalRequest request) throws ModelException {
        request.setRegionalInsRegionalDirSucursalpthisRegionalInstance(regionalSrv.getByOIDWithHV(request.getRegionalInsRegionalDirSucursalpthisRegional()));
        request.setRegionalInsRegionalDirSucursalpevcDirSucursalCInstance(dirSucursalCSrv.getByOIDWithHV(request.getRegionalInsRegionalDirSucursalpevcDirSucursalC()));
        return regionalSrv.insRegionalDirSucursalImplementation(request, true);
    }

    /*
     * Implements service InsRegionalDirSucursal
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse insRegionalDirSucursalImplementation(
            com.keralty.aeconfiguraciones.viewmodel.regional.siu.InsRegionalDirSucursalRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Regional regionalInsRegionalDirSucursalpthisRegional = request.getRegionalInsRegionalDirSucursalpthisRegionalInstance();
        DirSucursalC regionalInsRegionalDirSucursalpevcDirSucursalC = request.getRegionalInsRegionalDirSucursalpevcDirSucursalCInstance();

        request.checkArguments();
        Regional instance = regionalInsRegionalDirSucursalpthisRegional;

        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.dirsucursalc.siu.InsRegionalDirSucursalRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.dirsucursalc.siu.InsRegionalDirSucursalRequest();
            relatedRequest.setDirSucursalCInsRegionalDirSucursalpthisDirSucursalCInstance(regionalInsRegionalDirSucursalpevcDirSucursalC);
            relatedRequest.setDirSucursalCInsRegionalDirSucursalpevcRegionalInstance(regionalInsRegionalDirSucursalpthisRegional);
            dirSucursalCSrv.insRegionalDirSucursalImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICRegional());

        return response;
    }

    // Service: DelRegionalDirSucursal

    /*
     * Entry point for service DelRegionalDirSucursal
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse delRegionalDirSucursal(
            com.keralty.aeconfiguraciones.viewmodel.regional.siu.DelRegionalDirSucursalRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = regionalSrv.delRegionalDirSucursalTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service DelRegionalDirSucursal
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse delRegionalDirSucursalTxn(
            com.keralty.aeconfiguraciones.viewmodel.regional.siu.DelRegionalDirSucursalRequest request) throws ModelException {
        request.setRegionalDelRegionalDirSucursalpthisRegionalInstance(regionalSrv.getByOIDWithHV(request.getRegionalDelRegionalDirSucursalpthisRegional()));
        request.setRegionalDelRegionalDirSucursalpevcDirSucursalCInstance(dirSucursalCSrv.getByOIDWithHV(request.getRegionalDelRegionalDirSucursalpevcDirSucursalC()));
        return regionalSrv.delRegionalDirSucursalImplementation(request, true);
    }

    /*
     * Implements service DelRegionalDirSucursal
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse delRegionalDirSucursalImplementation(
            com.keralty.aeconfiguraciones.viewmodel.regional.siu.DelRegionalDirSucursalRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Regional regionalDelRegionalDirSucursalpthisRegional = request.getRegionalDelRegionalDirSucursalpthisRegionalInstance();
        DirSucursalC regionalDelRegionalDirSucursalpevcDirSucursalC = request.getRegionalDelRegionalDirSucursalpevcDirSucursalCInstance();

        request.checkArguments();
        Regional instance = regionalDelRegionalDirSucursalpthisRegional;

        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.dirsucursalc.siu.DelRegionalDirSucursalRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.dirsucursalc.siu.DelRegionalDirSucursalRequest();
            relatedRequest.setDirSucursalCDelRegionalDirSucursalpthisDirSucursalCInstance(regionalDelRegionalDirSucursalpevcDirSucursalC);
            relatedRequest.setDirSucursalCDelRegionalDirSucursalpevcRegionalInstance(regionalDelRegionalDirSucursalpthisRegional);
            dirSucursalCSrv.delRegionalDirSucursalImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICRegional());

        return response;
    }


    private void suppInfo4ICRegional(String searchText, SQLSelect select) {
        try {
            SQLWhereDisjunction disjunction = new SQLWhereDisjunction();

            SQLWhereComparison comparison1 = new SQLWhereComparison(select);
            comparison1.setOperator("LIKE");
            comparison1.setFirstOperand(new SQLWhereOperandPathAttribute(RegionalConstants.ATTR_NAME_REGIONALDESCRIPCION, Constants.Type.STRING.getTypeName()));
            comparison1.setSecondOperand(new SQLWhereOperandLiteral(searchText, Constants.Type.STRING.getTypeName()));
            disjunction.addComparison(comparison1);

            SQLWhereConjunction conjunction = new SQLWhereConjunction();
            conjunction.addDisjunction(disjunction);
            select.addConjunction(conjunction);
        } catch (Exception e) {
            LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
        }
    }

    @Override
    protected void applyOrderCriteria(QueryRequest queryRequest, SQLSelect select) {
        applyOCOCRegional(queryRequest, select);
        applyDSItemSort(queryRequest, select);
        applyDefaultOrderCriteria(queryRequest, select);
    }

    private void applyDefaultOrderCriteria(QueryRequest queryRequest, SQLSelect select) {
        if (queryRequest.getOrderCriteriaName() != null && !queryRequest.getOrderCriteriaName().isBlank())
            return;
        if (queryRequest.getDsItemSort() != null && !queryRequest.getDsItemSort().isBlank())
            return;
        select.getOrderByPart().addItem(Constants.normalizePath(RegionalConstants.CLASS_NAME, RegionalConstants.ATTR_FIELD_REGIONALCODIGO, false), true);
    }


    private void applyOCOCRegional(QueryRequest queryRequest, SQLSelect select) {
        if ("OC_Regional".equalsIgnoreCase(queryRequest.getOrderCriteriaName())) {
            select.getOrderByPart().addItem(Constants.normalizePath(RegionalConstants.CLASS_NAME, RegionalConstants.ATTR_NAME_REGIONALDESCRIPCION, false), true);
        }
    }
    
    @Override
    protected AbstractAppEntity buildRelatedInstance(String relatedInstanceJsonOid, String relatedInstancePathFirstItem, String remainingPath) {
        AbstractAppEntity relatedInstance = null;
        if (relatedInstancePathFirstItem.isEmpty()) {
            relatedInstance = buildRegionalRelatedInstance(relatedInstanceJsonOid);
        }
        if (RegionalConstants.ROLE_NAME_RESPREGIONAL.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildRegionalRelatedInstance4RespRegional(relatedInstanceJsonOid, remainingPath);
        }
        if (RegionalConstants.ROLE_NAME_SUCURSALES.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildRegionalRelatedInstance4Sucursal(relatedInstanceJsonOid, remainingPath);
        }
        if (RegionalConstants.ROLE_NAME_DIRSUCURSALC.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildRegionalRelatedInstance4DirSucursalC(relatedInstanceJsonOid, remainingPath);
        }
        return relatedInstance;
    }
    
    private AbstractAppEntity buildRegionalRelatedInstance(String relatedInstanceJsonOid) {
    	ObjectMapper mapper = new ObjectMapper();
    	try {
    		return new Regional(mapper.readValue(relatedInstanceJsonOid, RegionalOid.class));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    private AbstractAppEntity buildRegionalRelatedInstance4RespRegional(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new RespRegional(mapper.readValue(relatedInstanceJsonOid, RespRegionalOid.class)): RespRegional.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    private AbstractAppEntity buildRegionalRelatedInstance4Sucursal(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new Sucursal(mapper.readValue(relatedInstanceJsonOid, SucursalOid.class)): Sucursal.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    private AbstractAppEntity buildRegionalRelatedInstance4DirSucursalC(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new DirSucursalC(mapper.readValue(relatedInstanceJsonOid, DirSucursalCOid.class)): DirSucursalC.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    @Override
    protected void applyFilterByOid(String jsonOid, SQLSelect sql) {
        try {
            RegionalOid oid = new RegionalOid().fromJsonString(jsonOid);
            SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
            comparison1.setOperator("=");
            comparison1.setFirstOperand(new SQLWhereOperandPathAttribute(RegionalConstants.ATTR_FIELD_REGIONALCODIGO, Constants.Type.INT.getTypeName()));
            comparison1.setSecondOperand(new SQLWhereOperandLiteral(oid.getRegionalCodigo(), Constants.Type.INT.getTypeName()));
            sql.addComparison(comparison1);
        } catch (Exception e) {
            LoggerFactory.getLogger(RegionalService.class).error(e.getMessage());
        }
    }

}
