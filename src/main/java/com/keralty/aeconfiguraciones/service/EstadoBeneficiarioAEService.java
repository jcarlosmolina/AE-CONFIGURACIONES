package com.keralty.aeconfiguraciones.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.keralty.aeconfiguraciones.exception.ModelException;
import com.keralty.aeconfiguraciones.exception.SystemException;
import com.keralty.aeconfiguraciones.global.Constants;
import com.keralty.aeconfiguraciones.global.EstadoBeneficiarioAEConstants;
import com.keralty.aeconfiguraciones.persistence.AbstractAppEntity;
import com.keralty.aeconfiguraciones.persistence.EstadoBeneficiario;
import com.keralty.aeconfiguraciones.persistence.EstadoBeneficiarioAE;
import com.keralty.aeconfiguraciones.persistence.oid.EstadoBeneficiarioAEOid;
import com.keralty.aeconfiguraciones.persistence.oid.EstadoBeneficiarioOid;
import com.keralty.aeconfiguraciones.repository.EstadoBeneficiarioAEJpaRepository;
import com.keralty.aeconfiguraciones.sqlutils.SQLSelect;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereComparison;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereConjunction;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereDisjunction;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereOperandLiteral;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereOperandPathAttribute;
import com.keralty.aeconfiguraciones.utils.Utilities;
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
public class EstadoBeneficiarioAEService extends AbstractService {

    EstadoBeneficiarioAEJpaRepository repository;

    EstadoBeneficiarioAEService estadoBeneficiarioAESrv;

    EstadoBeneficiarioService estadoBeneficiarioSrv;

    @Inject
    public EstadoBeneficiarioAEService(
            EstadoBeneficiarioAEService estadoBeneficiarioAESrv,
            EstadoBeneficiarioService estadoBeneficiarioSrv,
            EstadoBeneficiarioAEJpaRepository repository) {
        this.estadoBeneficiarioAESrv = estadoBeneficiarioAESrv;
        this.estadoBeneficiarioSrv = estadoBeneficiarioSrv;
        this.repository = repository;
    }

    public List<Map<String, Object>> solveQuery(QueryRequest request) {
        return solveGenericQuery(request, EstadoBeneficiarioAEConstants.CLASS_NAME);
    }

    private List<EstadoBeneficiarioAE> solveEstadoBeneficiarioAETypedQuery(QueryRequest request) {
        return repository.solveQuery(processQueryRequest(request, EstadoBeneficiarioAEConstants.CLASS_NAME), request.getPageSize(), request.getPageNumber());
    }
    
    private int solveEstadoBeneficiarioAETypedCountQuery(QueryRequest request) {
        return repository.solveCountQuery(processCountQueryRequest(request, EstadoBeneficiarioAEConstants.CLASS_NAME));
    }

    /*
     * Returns the population of class EstadoBeneficiarioAE
     *
     * @return Population of class EstadoBeneficiarioAE
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<EstadoBeneficiarioAE> get() {
        List<EstadoBeneficiarioAE> results = new java.util.ArrayList<>();
        repository.findAll().forEach(results::add);
        return results;
    }
    
    /*
     * Gets the preloaded list of objects of class EstadoBeneficiarioAE
     *
     * @param queryRequest Request object that dictates the search conditions to 
     * retrieve objects
     *
     * @return Preloaded list of objects of class EstadoBeneficiarioAE
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<PreloadViewModel> getPreload(QueryRequest queryRequest) {
        List<PreloadViewModel> results = new ArrayList<>();
        List<EstadoBeneficiarioAE> instances = get(queryRequest);
        if(EstadoBeneficiarioAEConstants.DSICESTADOBENEFICIARIOAE.equals(queryRequest.getDisplaySetName())) {
            results = instances.stream().map(x -> new PreloadViewModel(x.getOid().toJson(), x.buildICEstadoBeneficiarioAE())).collect(Collectors.toList());
        }
        if(EstadoBeneficiarioAEConstants.DSDSESTADOBENEFICIARIOAEPIU.equals(queryRequest.getDisplaySetName())) {
            results = instances.stream().map(x -> new PreloadViewModel(x.getOid().toJson(), x.buildDSEstadoBeneficiarioAEPIU())).collect(Collectors.toList());
        }
        return results;
    }
    /*
     * Returns the population of class EstadoBeneficiarioAE that matches certain search criteria
     *
     * @param queryRequest Search criteria
     *
     * @return Population of class EstadoBeneficiarioAE matching the search criteria
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<EstadoBeneficiarioAE> get(QueryRequest queryRequest) {
        return solveEstadoBeneficiarioAETypedQuery(queryRequest);
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
            return new PopulationResponseViewModel(solveEstadoBeneficiarioAETypedQuery(queryRequest).stream()
                    .map(instance -> buildDynamicObject(instance, queryRequest.getDisplaySetItems()))
                    .collect(Collectors.toList()), queryRequest.getPageSize() != 0 ? solveEstadoBeneficiarioAETypedCountQuery(queryRequest) : 0);
        } else {
            return new PreloadResponseViewModel(solveEstadoBeneficiarioAETypedQuery(queryRequest).stream()
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
        return new PopulationResponseViewModel(solveEstadoBeneficiarioAETypedQuery(queryRequest).stream()
                .map(instance -> buildDynamicObject(instance, displaySetItems))
                .collect(Collectors.toList()));
    }

    /*
     * Returns an instance of EstadoBeneficiarioAE by ID
     *
     * @param estadoBeneficiarioAECodigo Identification field estadoBeneficiarioAECodigo
     *
     * @return Instance of EstadoBeneficiarioAE
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public EstadoBeneficiarioAE getById(Long estadoBeneficiarioAECodigo) {
        return getByOID(new EstadoBeneficiarioAEOid(estadoBeneficiarioAECodigo));
    }
    
    /*
     * Returns an instance of EstadoBeneficiarioAE by OID
     *
     * @param oid Object IDentifier
     *
     * @return Instance of EstadoBeneficiarioAE
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public EstadoBeneficiarioAE getByOID(EstadoBeneficiarioAEOid oid) {
        Optional<EstadoBeneficiarioAE> instance = getInstanceByOid(oid);
        return instance.isPresent() ? instance.get() : null;
    }
    
    /*
     * Returns an instance of EstadoBeneficiarioAE by OID applying horizontal visibility constraints
     *
     * @param oid Object IDentifier
     *
     * @return Instance of EstadoBeneficiarioAE
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public EstadoBeneficiarioAE getByOIDWithHV(EstadoBeneficiarioAEOid oid) {
        Optional<EstadoBeneficiarioAE> instance = getInstanceByOid(oid);
    	return instance.isPresent() && instance.get().checkHorizontalVisibility(getAgentInstance()) 
    		 ? instance.get() 
    		 : null;
    }
    
    private Optional<EstadoBeneficiarioAE> getInstanceByOid(EstadoBeneficiarioAEOid oid) {
        return oid != null && oid.getEstadoBeneficiarioAECodigo() != null
               ? repository.findById(oid.getEstadoBeneficiarioAECodigo())
               : Optional.empty();
    }

    /*
     * Returns an instance of EstadoBeneficiarioAE by OID in JSON format
     *
     * @param jsonOid Object IDentifier in JSON format
     *
     * @return Instance of EstadoBeneficiarioAE
     */
    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public EstadoBeneficiarioAE getByJsonOid(String jsonOid) {
        EstadoBeneficiarioAEOid oid = new EstadoBeneficiarioAEOid();
        oid.fromJsonString(jsonOid);
        return getByOIDWithHV(oid);
    }

    /*
     * Returns the list of instances of EstadoBeneficiarioAE related to a given instance through 
     * a given path
     *
     * @param relatedInstance Related instance
     *
     * @param relatedInstancePath Related instance path
     *
     * @return List of instances of EstadoBeneficiarioAE
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<EstadoBeneficiarioAE> getByRelated(AbstractAppEntity relatedInstance, String relatedInstancePath) {
        QueryRequest queryRequest = new QueryRequest(new FilterRequest(Constants.FILTER_BY_RELATED,
                Arrays.asList(new FilterVariableRequest(Constants.FILTER_BY_RELATED_FVAR_PATH, relatedInstancePath),
                        new FilterVariableRequest(Constants.FILTER_BY_RELATED_FVAR_OID, relatedInstance.getOid().toJson()))));
        return solveEstadoBeneficiarioAETypedQuery(queryRequest);
    }

    /*
     * Returns the list of instances of EstadoBeneficiarioAE related to a given instance through
     * a given path
     *
     * @param relatedInstance Related instance
     *
     * @param relatedInstancePath Related instance path
     *
     * @return List of instances of EstadoBeneficiarioAE
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<EstadoBeneficiarioAE> getInstancesByRelated(AbstractAppEntity relatedInstance, String relatedInstancePath) {
        return getByRelated(relatedInstance, relatedInstancePath);
    }

    /*
     * Returns a set of displayset items for an instance of EstadoBeneficiarioAE
     *
     * @param oid Object IDentifier
     *
     * @param displaySetItems List of comma-separated displayset items
     *
     * @return Set of displayset items for an instance of EstadoBeneficiarioAE
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public InstanceResponseViewModel getEstadoBeneficiarioAEDynamic(EstadoBeneficiarioAEOid oid, String displaySetItems) {
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
    public String getSuppInfo(EstadoBeneficiarioAEOid oid, String dsName) {
        EstadoBeneficiarioAE instance = getByOIDWithHV(oid);
        return (instance != null ? getSuppInfo(instance, dsName): "");
    }

    private String getSuppInfo(EstadoBeneficiarioAE instance, String dsName) {
        if (instance == null || instance.isNull()) return "";
        if (EstadoBeneficiarioAEConstants.DSICESTADOBENEFICIARIOAE.equals(dsName))
            return instance.buildICEstadoBeneficiarioAE();
        return dsName.isEmpty() ? instance.getOid().toJson() : "";
    }

    public static PreloadViewModel buildPreloadViewModel(EstadoBeneficiarioAE instance, String dsName) {
        if (EstadoBeneficiarioAEConstants.DSICESTADOBENEFICIARIOAE.equals(dsName))
            return new PreloadViewModel(instance.getOid().toJson(), instance.buildICEstadoBeneficiarioAE());
        if (EstadoBeneficiarioAEConstants.DSDSESTADOBENEFICIARIOAEPIU.equals(dsName))
            return new PreloadViewModel(instance.getOid().toJson(), instance.buildDSEstadoBeneficiarioAEPIU());
        return new PreloadViewModel(instance.getOid().toJson(), "");
    }


    @Override
    protected void applyServiceSIFilters(FilterRequest filterRequest, SQLSelect sql) {
        if ("SuppInfo_IC_EstadoBeneficiarioAE".equals(filterRequest.getName()))
            suppInfo4ICEstadoBeneficiarioAE(filterRequest.getFilterVariable("searchText").toString(), sql);
    }



    // IIU Methods


    /*
     * Solves the query needed for PIUEstadoBeneficiarioAE
     *
     * @param queryRequest Query request
     * @return List of viewmodels for EstadoBeneficiarioAE using displayset DSEstadoBeneficiarioAEPIU that 
     * match the search criteria
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<com.keralty.aeconfiguraciones.viewmodel.estadobeneficiarioae.ds.DSEstadoBeneficiarioAEPIU> piuPIUEstadoBeneficiarioAE(QueryRequest queryRequest) {
        return solveEstadoBeneficiarioAETypedQuery(queryRequest).stream()
                .map(com.keralty.aeconfiguraciones.viewmodel.estadobeneficiarioae.ds.DSEstadoBeneficiarioAEPIU::new)
                .collect(Collectors.toList());
    }

    /*
     * Solves the COUNT query needed for PIUEstadoBeneficiarioAE
     *
     * @param queryRequest Query request
     * @returns Count of instances for PIUEstadoBeneficiarioAE
     */
    public int getCount4PIUEstadoBeneficiarioAE(QueryRequest queryRequest) {
        return solveEstadoBeneficiarioAETypedQuery(queryRequest).size();
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
            com.keralty.aeconfiguraciones.viewmodel.estadobeneficiarioae.siu.CrearRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = estadoBeneficiarioAESrv.crearTxn(request);
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
            com.keralty.aeconfiguraciones.viewmodel.estadobeneficiarioae.siu.CrearRequest request) throws ModelException {
        request.setEstadoBeneficiarioAEcrearpagrEstadoBeneficiarioCOREInstance(estadoBeneficiarioSrv.getByOIDWithHV(new EstadoBeneficiarioOid().fromJsonString(request.getEstadoBeneficiarioAEcrearpagrEstadoBeneficiarioCORE())));
        return estadoBeneficiarioAESrv.crearImplementation(request);
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
            com.keralty.aeconfiguraciones.viewmodel.estadobeneficiarioae.siu.CrearRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        EstadoBeneficiario estadoBeneficiarioAEcrearpagrEstadoBeneficiarioCORE = request.getEstadoBeneficiarioAEcrearpagrEstadoBeneficiarioCOREInstance();
        String estadoBeneficiarioAEcrearpatrDescripcion = request.getEstadoBeneficiarioAEcrearpatrDescripcion();

        request.checkArguments();
        EstadoBeneficiarioAE instance = new EstadoBeneficiarioAE();
        instance.add2EstadoBeneficiarioCORE(estadoBeneficiarioAEcrearpagrEstadoBeneficiarioCORE);

        instance.setEstadoBeneficiarioAECodigo(Long.valueOf(0));
        instance.setEstadoBeneficiarioAEDescripcion(estadoBeneficiarioAEcrearpatrDescripcion);

        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICEstadoBeneficiarioAE());

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
            com.keralty.aeconfiguraciones.viewmodel.estadobeneficiarioae.siu.EliminarRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = estadoBeneficiarioAESrv.eliminarTxn(request);
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
            com.keralty.aeconfiguraciones.viewmodel.estadobeneficiarioae.siu.EliminarRequest request) throws ModelException {
        request.setEstadoBeneficiarioAEeliminarpthisEstadoBeneficiarioAEInstance(estadoBeneficiarioAESrv.getByOIDWithHV(request.getEstadoBeneficiarioAEeliminarpthisEstadoBeneficiarioAE()));
        return estadoBeneficiarioAESrv.eliminarImplementation(request);
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
            com.keralty.aeconfiguraciones.viewmodel.estadobeneficiarioae.siu.EliminarRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        EstadoBeneficiarioAE estadoBeneficiarioAEeliminarpthisEstadoBeneficiarioAE = request.getEstadoBeneficiarioAEeliminarpthisEstadoBeneficiarioAEInstance();

        request.checkArguments();
        EstadoBeneficiarioAE instance = estadoBeneficiarioAEeliminarpthisEstadoBeneficiarioAE;


        repository.delete(instance);
        
        response.setInstance(instance);
        response.setInstanceSuppInfo(instance.buildICEstadoBeneficiarioAE());

        return response;
    }

    // Service: modificar

    /*
     * Entry point for service modificar
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse modificar(
            com.keralty.aeconfiguraciones.viewmodel.estadobeneficiarioae.siu.ModificarRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = estadoBeneficiarioAESrv.modificarTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service modificar
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse modificarTxn(
            com.keralty.aeconfiguraciones.viewmodel.estadobeneficiarioae.siu.ModificarRequest request) throws ModelException {
        request.setEstadoBeneficiarioAEmodificarpthisEstadoBeneficiarioAEInstance(estadoBeneficiarioAESrv.getByOIDWithHV(request.getEstadoBeneficiarioAEmodificarpthisEstadoBeneficiarioAE()));
        return estadoBeneficiarioAESrv.modificarImplementation(request);
    }

    /*
     * Implements service modificar
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse modificarImplementation(
            com.keralty.aeconfiguraciones.viewmodel.estadobeneficiarioae.siu.ModificarRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        EstadoBeneficiarioAE estadoBeneficiarioAEmodificarpthisEstadoBeneficiarioAE = request.getEstadoBeneficiarioAEmodificarpthisEstadoBeneficiarioAEInstance();
        String estadoBeneficiarioAEmodificarpDescripcion = request.getEstadoBeneficiarioAEmodificarpDescripcion();

        request.checkArguments();
        EstadoBeneficiarioAE instance = estadoBeneficiarioAEmodificarpthisEstadoBeneficiarioAE;
        instance.setEstadoBeneficiarioAEDescripcion(estadoBeneficiarioAEmodificarpDescripcion);


        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICEstadoBeneficiarioAE());

        return response;
    }

    // Service: CambEstadoBeneficiarioCORE

    /*
     * Entry point for service CambEstadoBeneficiarioCORE
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse cambEstadoBeneficiarioCORE(
            com.keralty.aeconfiguraciones.viewmodel.estadobeneficiarioae.siu.CambEstadoBeneficiarioCORERequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = estadoBeneficiarioAESrv.cambEstadoBeneficiarioCORETxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service CambEstadoBeneficiarioCORE
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse cambEstadoBeneficiarioCORETxn(
            com.keralty.aeconfiguraciones.viewmodel.estadobeneficiarioae.siu.CambEstadoBeneficiarioCORERequest request) throws ModelException {
        request.setEstadoBeneficiarioAECambEstadoBeneficiarioCOREpthisEstadoBeneficiarioAEInstance(estadoBeneficiarioAESrv.getByOIDWithHV(request.getEstadoBeneficiarioAECambEstadoBeneficiarioCOREpthisEstadoBeneficiarioAE()));
        request.setEstadoBeneficiarioAECambEstadoBeneficiarioCOREpevcEstadoBeneficiarioInstance(estadoBeneficiarioSrv.getByOIDWithHV(request.getEstadoBeneficiarioAECambEstadoBeneficiarioCOREpevcEstadoBeneficiario()));
        return estadoBeneficiarioAESrv.cambEstadoBeneficiarioCOREImplementation(request, true);
    }

    /*
     * Implements service CambEstadoBeneficiarioCORE
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse cambEstadoBeneficiarioCOREImplementation(
            com.keralty.aeconfiguraciones.viewmodel.estadobeneficiarioae.siu.CambEstadoBeneficiarioCORERequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        EstadoBeneficiarioAE estadoBeneficiarioAECambEstadoBeneficiarioCOREpthisEstadoBeneficiarioAE = request.getEstadoBeneficiarioAECambEstadoBeneficiarioCOREpthisEstadoBeneficiarioAEInstance();
        EstadoBeneficiario estadoBeneficiarioAECambEstadoBeneficiarioCOREpevcEstadoBeneficiario = request.getEstadoBeneficiarioAECambEstadoBeneficiarioCOREpevcEstadoBeneficiarioInstance();

        request.checkArguments();
        EstadoBeneficiarioAE instance = estadoBeneficiarioAECambEstadoBeneficiarioCOREpthisEstadoBeneficiarioAE;

        instance.add2EstadoBeneficiarioCORE(estadoBeneficiarioAECambEstadoBeneficiarioCOREpevcEstadoBeneficiario);
        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.estadobeneficiario.siu.CambEstadoBeneficiarioCORERequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.estadobeneficiario.siu.CambEstadoBeneficiarioCORERequest();
            relatedRequest.setEstadoBeneficiarioCambEstadoBeneficiarioCOREpthisEstadoBeneficiarioInstance(estadoBeneficiarioAECambEstadoBeneficiarioCOREpevcEstadoBeneficiario);
            relatedRequest.setEstadoBeneficiarioCambEstadoBeneficiarioCOREpevcEstadoBeneficiarioAEInstance(estadoBeneficiarioAECambEstadoBeneficiarioCOREpthisEstadoBeneficiarioAE);
            estadoBeneficiarioSrv.cambEstadoBeneficiarioCOREImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICEstadoBeneficiarioAE());

        return response;
    }

    // Service: TMODIFICAR

    /*
     * Entry point for service TMODIFICAR
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse tMODIFICAR(
            com.keralty.aeconfiguraciones.viewmodel.estadobeneficiarioae.siu.TMODIFICARRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = estadoBeneficiarioAESrv.tMODIFICARTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service TMODIFICAR
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse tMODIFICARTxn(
            com.keralty.aeconfiguraciones.viewmodel.estadobeneficiarioae.siu.TMODIFICARRequest request) throws ModelException {
        request.setEstadoBeneficiarioAETMODIFICARpthisEstadoBeneficiarioAEInstance(estadoBeneficiarioAESrv.getByOIDWithHV(request.getEstadoBeneficiarioAETMODIFICARpthisEstadoBeneficiarioAE()));
        request.setEstadoBeneficiarioAETMODIFICARptpevcEstadoBeneficiarioInstance(estadoBeneficiarioSrv.getByOIDWithHV(new EstadoBeneficiarioOid().fromJsonString(request.getEstadoBeneficiarioAETMODIFICARptpevcEstadoBeneficiario())));
        return estadoBeneficiarioAESrv.tMODIFICARImplementation(request);
    }

    /*
     * Implements service TMODIFICAR
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse tMODIFICARImplementation(
            com.keralty.aeconfiguraciones.viewmodel.estadobeneficiarioae.siu.TMODIFICARRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        String estadoBeneficiarioAETMODIFICARptpDescripcion = request.getEstadoBeneficiarioAETMODIFICARptpDescripcion();
        EstadoBeneficiario estadoBeneficiarioAETMODIFICARptpevcEstadoBeneficiario = request.getEstadoBeneficiarioAETMODIFICARptpevcEstadoBeneficiarioInstance();
        EstadoBeneficiarioAE estadoBeneficiarioAETMODIFICARpthisEstadoBeneficiarioAE = request.getEstadoBeneficiarioAETMODIFICARpthisEstadoBeneficiarioAEInstance();

        request.checkArguments();
        EstadoBeneficiarioAE instance = estadoBeneficiarioAETMODIFICARpthisEstadoBeneficiarioAE;

        com.keralty.aeconfiguraciones.viewmodel.estadobeneficiarioae.siu.ModificarRequest step1Request = new com.keralty.aeconfiguraciones.viewmodel.estadobeneficiarioae.siu.ModificarRequest();
        step1Request.setEstadoBeneficiarioAEmodificarpthisEstadoBeneficiarioAEInstance(estadoBeneficiarioAETMODIFICARpthisEstadoBeneficiarioAE);
        step1Request.setEstadoBeneficiarioAEmodificarpDescripcion(estadoBeneficiarioAETMODIFICARptpDescripcion);
        estadoBeneficiarioAESrv.modificarImplementation(step1Request);

        boolean guard2 = !Utilities.isEqual(estadoBeneficiarioAETMODIFICARpthisEstadoBeneficiarioAE.getEstadoBeneficiarioCORE(), estadoBeneficiarioAETMODIFICARptpevcEstadoBeneficiario);

        if (guard2) {
            com.keralty.aeconfiguraciones.viewmodel.estadobeneficiarioae.siu.CambEstadoBeneficiarioCORERequest step2Request = new com.keralty.aeconfiguraciones.viewmodel.estadobeneficiarioae.siu.CambEstadoBeneficiarioCORERequest();
            step2Request.setEstadoBeneficiarioAECambEstadoBeneficiarioCOREpthisEstadoBeneficiarioAEInstance(estadoBeneficiarioAETMODIFICARpthisEstadoBeneficiarioAE);
            step2Request.setEstadoBeneficiarioAECambEstadoBeneficiarioCOREpevcEstadoBeneficiarioInstance(estadoBeneficiarioAETMODIFICARptpevcEstadoBeneficiario);
            estadoBeneficiarioAESrv.cambEstadoBeneficiarioCOREImplementation(step2Request, true);
        }

        response.setInstance(getByOID(instance.getOid()));
        response.setInstanceSuppInfo(instance.buildICEstadoBeneficiarioAE());

        return response;
    }


    private void suppInfo4ICEstadoBeneficiarioAE(String searchText, SQLSelect select) {
        try {
            SQLWhereDisjunction disjunction = new SQLWhereDisjunction();

            SQLWhereComparison comparison1 = new SQLWhereComparison(select);
            comparison1.setOperator("LIKE");
            comparison1.setFirstOperand(new SQLWhereOperandPathAttribute(EstadoBeneficiarioAEConstants.ATTR_NAME_ESTADOBENEFICIARIOAEDESCRIPCION, Constants.Type.STRING.getTypeName()));
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
        applyOCOCEstadoBeneficiarioAE(queryRequest, select);
        applyDSItemSort(queryRequest, select);
        applyDefaultOrderCriteria(queryRequest, select);
    }

    private void applyDefaultOrderCriteria(QueryRequest queryRequest, SQLSelect select) {
        if (queryRequest.getOrderCriteriaName() != null && !queryRequest.getOrderCriteriaName().isBlank())
            return;
        if (queryRequest.getDsItemSort() != null && !queryRequest.getDsItemSort().isBlank())
            return;
        select.getOrderByPart().addItem(Constants.normalizePath(EstadoBeneficiarioAEConstants.CLASS_NAME, EstadoBeneficiarioAEConstants.ATTR_FIELD_ESTADOBENEFICIARIOAECODIGO, false), true);
    }


    private void applyOCOCEstadoBeneficiarioAE(QueryRequest queryRequest, SQLSelect select) {
        if ("OC_EstadoBeneficiarioAE".equalsIgnoreCase(queryRequest.getOrderCriteriaName())) {
            select.getOrderByPart().addItem(Constants.normalizePath(EstadoBeneficiarioAEConstants.CLASS_NAME, EstadoBeneficiarioAEConstants.ATTR_NAME_ESTADOBENEFICIARIOAEDESCRIPCION, false), true);
        }
    }
    
    @Override
    protected AbstractAppEntity buildRelatedInstance(String relatedInstanceJsonOid, String relatedInstancePathFirstItem, String remainingPath) {
        AbstractAppEntity relatedInstance = null;
        if (relatedInstancePathFirstItem.isEmpty()) {
            relatedInstance = buildEstadoBeneficiarioAERelatedInstance(relatedInstanceJsonOid);
        }
        if (EstadoBeneficiarioAEConstants.ROLE_NAME_ESTADOBENEFICIARIOCORE.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildEstadoBeneficiarioAERelatedInstance4EstadoBeneficiario(relatedInstanceJsonOid, remainingPath);
        }
        return relatedInstance;
    }
    
    private AbstractAppEntity buildEstadoBeneficiarioAERelatedInstance(String relatedInstanceJsonOid) {
    	ObjectMapper mapper = new ObjectMapper();
    	try {
    		return new EstadoBeneficiarioAE(mapper.readValue(relatedInstanceJsonOid, EstadoBeneficiarioAEOid.class));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            throw new SystemException(e.getMessage());
    	}
    }

    private AbstractAppEntity buildEstadoBeneficiarioAERelatedInstance4EstadoBeneficiario(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new EstadoBeneficiario(mapper.readValue(relatedInstanceJsonOid, EstadoBeneficiarioOid.class)): EstadoBeneficiario.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            throw new SystemException(e.getMessage());
    	}
    }

    @Override
    protected void applyFilterByOid(String jsonOid, SQLSelect sql) {
        try {
            EstadoBeneficiarioAEOid oid = new EstadoBeneficiarioAEOid().fromJsonString(jsonOid);
            SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
            comparison1.setOperator("=");
            comparison1.setFirstOperand(new SQLWhereOperandPathAttribute(EstadoBeneficiarioAEConstants.ATTR_FIELD_ESTADOBENEFICIARIOAECODIGO, Constants.Type.AUTONUMERIC.getTypeName()));
            comparison1.setSecondOperand(new SQLWhereOperandLiteral(oid.getEstadoBeneficiarioAECodigo(), Constants.Type.AUTONUMERIC.getTypeName()));
            sql.addComparison(comparison1);
        } catch (Exception e) {
            LoggerFactory.getLogger(EstadoBeneficiarioAEService.class).error(e.getMessage());
            throw new SystemException(e.getMessage());
        }
    }

}
