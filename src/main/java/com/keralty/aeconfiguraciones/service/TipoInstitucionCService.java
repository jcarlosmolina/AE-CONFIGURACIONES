package com.keralty.aeconfiguraciones.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.keralty.aeconfiguraciones.exception.InstanceExistsException;
import com.keralty.aeconfiguraciones.exception.MinCardinalityException;
import com.keralty.aeconfiguraciones.exception.ModelException;
import com.keralty.aeconfiguraciones.exception.PreconditionException;
import com.keralty.aeconfiguraciones.exception.SystemException;
import com.keralty.aeconfiguraciones.global.Constants;
import com.keralty.aeconfiguraciones.global.TipoInstitucionCConstants;
import com.keralty.aeconfiguraciones.persistence.AbstractAppEntity;
import com.keralty.aeconfiguraciones.persistence.oid.PersonaCOid;
import com.keralty.aeconfiguraciones.persistence.oid.TipoInstitucionCOid;
import com.keralty.aeconfiguraciones.persistence.PersonaC;
import com.keralty.aeconfiguraciones.persistence.TipoInstitucionC;
import com.keralty.aeconfiguraciones.repository.TipoInstitucionCJpaRepository;
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
public class TipoInstitucionCService extends AbstractService {

    TipoInstitucionCJpaRepository repository;

    TipoInstitucionCService tipoInstitucionCSrv;

    PersonaCService personaCSrv;

    @Inject
    public TipoInstitucionCService(
            TipoInstitucionCService tipoInstitucionCSrv,
            PersonaCService personaCSrv,
            TipoInstitucionCJpaRepository repository) {
        this.tipoInstitucionCSrv = tipoInstitucionCSrv;
        this.personaCSrv = personaCSrv;
        this.repository = repository;
    }

    public List<Map<String, Object>> solveQuery(QueryRequest request) {
        return solveGenericQuery(request, TipoInstitucionCConstants.CLASS_NAME);
    }

    private List<TipoInstitucionC> solveTipoInstitucionCTypedQuery(QueryRequest request) {
        return repository.solveQuery(processQueryRequest(request, TipoInstitucionCConstants.CLASS_NAME), request.getPageSize(), request.getPageNumber());
    }
    
    private int solveTipoInstitucionCTypedCountQuery(QueryRequest request) {
        return repository.solveCountQuery(processCountQueryRequest(request, TipoInstitucionCConstants.CLASS_NAME));
    }

    /*
     * Returns the population of class TipoInstitucionC
     *
     * @return Population of class TipoInstitucionC
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<TipoInstitucionC> get() {
        List<TipoInstitucionC> results = new java.util.ArrayList<>();
        repository.findAll().forEach(results::add);
        return results;
    }
    
    /*
     * Gets the preloaded list of objects of class TipoInstitucionC
     *
     * @param queryRequest Request object that dictates the search conditions to 
     * retrieve objects
     *
     * @return Preloaded list of objects of class TipoInstitucionC
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<PreloadViewModel> getPreload(QueryRequest queryRequest) {
        List<PreloadViewModel> results = new ArrayList<>();
        List<TipoInstitucionC> instances = get(queryRequest);
        if(TipoInstitucionCConstants.DSICTIPOINSTITUCION.equals(queryRequest.getDisplaySetName())) {
            results = instances.stream().map(x -> new PreloadViewModel(x.getOid().toJson(), x.buildICTipoInstitucion())).collect(Collectors.toList());
        }
        return results;
    }
    /*
     * Returns the population of class TipoInstitucionC that matches certain search criteria
     *
     * @param queryRequest Search criteria
     *
     * @return Population of class TipoInstitucionC matching the search criteria
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<TipoInstitucionC> get(QueryRequest queryRequest) {
        return solveTipoInstitucionCTypedQuery(queryRequest);
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
            return new PopulationResponseViewModel(solveTipoInstitucionCTypedQuery(queryRequest).stream()
                    .map(instance -> buildDynamicObject(instance, queryRequest.getDisplaySetItems()))
                    .collect(Collectors.toList()), queryRequest.getPageSize() != 0 ? solveTipoInstitucionCTypedCountQuery(queryRequest) : 0);
        } else {
            return new PreloadResponseViewModel(solveTipoInstitucionCTypedQuery(queryRequest).stream()
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
        return new PopulationResponseViewModel(solveTipoInstitucionCTypedQuery(queryRequest).stream()
                .map(instance -> buildDynamicObject(instance, displaySetItems))
                .collect(Collectors.toList()));
    }

    /*
     * Returns an instance of TipoInstitucionC by ID
     *
     * @param tipoInstitucionCCodigo Identification field tipoInstitucionCCodigo
     *
     * @return Instance of TipoInstitucionC
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public TipoInstitucionC getById(Long tipoInstitucionCCodigo) {
        return getByOID(new TipoInstitucionCOid(tipoInstitucionCCodigo));
    }
    
    /*
     * Returns an instance of TipoInstitucionC by OID
     *
     * @param oid Object IDentifier
     *
     * @return Instance of TipoInstitucionC
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public TipoInstitucionC getByOID(TipoInstitucionCOid oid) {
        Optional<TipoInstitucionC> instance = getInstanceByOid(oid);
        return instance.isPresent() ? instance.get() : null;
    }
    
    /*
     * Returns an instance of TipoInstitucionC by OID applying horizontal visibility constraints
     *
     * @param oid Object IDentifier
     *
     * @return Instance of TipoInstitucionC
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public TipoInstitucionC getByOIDWithHV(TipoInstitucionCOid oid) {
        Optional<TipoInstitucionC> instance = getInstanceByOid(oid);
    	return instance.isPresent() && instance.get().checkHorizontalVisibility(getAgentInstance()) 
    		 ? instance.get() 
    		 : null;
    }
    
    private Optional<TipoInstitucionC> getInstanceByOid(TipoInstitucionCOid oid) {
        return oid != null && oid.getTipoInstitucionCCodigo() != null
               ? repository.findById(oid.getTipoInstitucionCCodigo())
               : Optional.empty();
    }

    /*
     * Returns an instance of TipoInstitucionC by OID in JSON format
     *
     * @param jsonOid Object IDentifier in JSON format
     *
     * @return Instance of TipoInstitucionC
     */
    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public TipoInstitucionC getByJsonOid(String jsonOid) {
        TipoInstitucionCOid oid = new TipoInstitucionCOid();
        oid.fromJsonString(jsonOid);
        return getByOIDWithHV(oid);
    }

    /*
     * Returns the list of instances of TipoInstitucionC related to a given instance through 
     * a given path
     *
     * @param relatedInstance Related instance
     *
     * @param relatedInstancePath Related instance path
     *
     * @return List of instances of TipoInstitucionC
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<TipoInstitucionC> getByRelated(AbstractAppEntity relatedInstance, String relatedInstancePath) {
        QueryRequest queryRequest = new QueryRequest(new FilterRequest(Constants.FILTER_BY_RELATED,
                Arrays.asList(new FilterVariableRequest(Constants.FILTER_BY_RELATED_FVAR_PATH, relatedInstancePath),
                        new FilterVariableRequest(Constants.FILTER_BY_RELATED_FVAR_OID, relatedInstance.getOid().toJson()))));
        return solveTipoInstitucionCTypedQuery(queryRequest);
    }

    /*
     * Returns the list of instances of TipoInstitucionC related to a given instance through
     * a given path
     *
     * @param relatedInstance Related instance
     *
     * @param relatedInstancePath Related instance path
     *
     * @return List of instances of TipoInstitucionC
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<TipoInstitucionC> getInstancesByRelated(AbstractAppEntity relatedInstance, String relatedInstancePath) {
        return getByRelated(relatedInstance, relatedInstancePath);
    }

    /*
     * Returns a set of displayset items for an instance of TipoInstitucionC
     *
     * @param oid Object IDentifier
     *
     * @param displaySetItems List of comma-separated displayset items
     *
     * @return Set of displayset items for an instance of TipoInstitucionC
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public InstanceResponseViewModel getTipoInstitucionCDynamic(TipoInstitucionCOid oid, String displaySetItems) {
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
    public String getSuppInfo(TipoInstitucionCOid oid, String dsName) {
        TipoInstitucionC instance = getByOIDWithHV(oid);
        return (instance != null ? getSuppInfo(instance, dsName): "");
    }

    private String getSuppInfo(TipoInstitucionC instance, String dsName) {
        if (instance == null || instance.isNull()) return "";
        if (TipoInstitucionCConstants.DSICTIPOINSTITUCION.equals(dsName))
            return instance.buildICTipoInstitucion();
        return dsName.isEmpty() ? instance.getOid().toJson() : "";
    }

    public static PreloadViewModel buildPreloadViewModel(TipoInstitucionC instance, String dsName) {
        if (TipoInstitucionCConstants.DSICTIPOINSTITUCION.equals(dsName))
            return new PreloadViewModel(instance.getOid().toJson(), instance.buildICTipoInstitucion());
        return new PreloadViewModel(instance.getOid().toJson(), "");
    }


    @Override
    protected void applyServiceSIFilters(FilterRequest filterRequest, SQLSelect sql) {
        if ("SuppInfo_IC_TipoInstitucion".equals(filterRequest.getName()))
            suppInfo4ICTipoInstitucion(filterRequest.getFilterVariable("searchText").toString(), sql);
    }



    // IIU Methods


    /*
     * Solves the query needed for PIUTipoInstitucion
     *
     * @param queryRequest Query request
     * @return List of viewmodels for TipoInstitucionC using displayset AutoDS that 
     * match the search criteria
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<com.keralty.aeconfiguraciones.viewmodel.tipoinstitucionc.ds.AutoDS> piuPIUTipoInstitucion(QueryRequest queryRequest) {
        return solveTipoInstitucionCTypedQuery(queryRequest).stream()
                .map(com.keralty.aeconfiguraciones.viewmodel.tipoinstitucionc.ds.AutoDS::new)
                .collect(Collectors.toList());
    }

    /*
     * Solves the COUNT query needed for PIUTipoInstitucion
     *
     * @param queryRequest Query request
     * @returns Count of instances for PIUTipoInstitucion
     */
    public int getCount4PIUTipoInstitucion(QueryRequest queryRequest) {
        return solveTipoInstitucionCTypedQuery(queryRequest).size();
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
            com.keralty.aeconfiguraciones.viewmodel.tipoinstitucionc.siu.CrearRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = tipoInstitucionCSrv.crearTxn(request);
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
            com.keralty.aeconfiguraciones.viewmodel.tipoinstitucionc.siu.CrearRequest request) throws ModelException {
        return tipoInstitucionCSrv.crearImplementation(request);
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
            com.keralty.aeconfiguraciones.viewmodel.tipoinstitucionc.siu.CrearRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Long tipoInstitucionCcrearpatrCodigo = request.getTipoInstitucionCcrearpatrCodigo();
        String tipoInstitucionCcrearpatrNombre = request.getTipoInstitucionCcrearpatrNombre();

        request.checkArguments();
        TipoInstitucionC instance = new TipoInstitucionC();
        instance.setTipoInstitucionCCodigo(tipoInstitucionCcrearpatrCodigo);
        instance.setTipoInstitucionCNombre(tipoInstitucionCcrearpatrNombre);
        if (tipoInstitucionCSrv.getByOID(instance.getOid()) != null) {
            throw new InstanceExistsException(TipoInstitucionCConstants.CLASS_ALIAS);
        }

        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICTipoInstitucion());

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
            com.keralty.aeconfiguraciones.viewmodel.tipoinstitucionc.siu.EliminarRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = tipoInstitucionCSrv.eliminarTxn(request);
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
            com.keralty.aeconfiguraciones.viewmodel.tipoinstitucionc.siu.EliminarRequest request) throws ModelException {
        request.setTipoInstitucionCeliminarpthisTipoInstitucionInstance(tipoInstitucionCSrv.getByOIDWithHV(request.getTipoInstitucionCeliminarpthisTipoInstitucion()));
        return tipoInstitucionCSrv.eliminarImplementation(request);
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
            com.keralty.aeconfiguraciones.viewmodel.tipoinstitucionc.siu.EliminarRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        TipoInstitucionC tipoInstitucionCeliminarpthisTipoInstitucion = request.getTipoInstitucionCeliminarpthisTipoInstitucionInstance();

        request.checkArguments();

        eliminarPreconditions(request);
        TipoInstitucionC instance = tipoInstitucionCeliminarpthisTipoInstitucion;
        if(tipoInstitucionCeliminarpthisTipoInstitucion.getPersonasC().size() > 0) {
            throw new MinCardinalityException(TipoInstitucionCConstants.CLASS_ALIAS, TipoInstitucionCConstants.ROLE_ALIAS_PERSONASC);
        }


        repository.delete(instance);
        
        response.setInstance(instance);
        response.setInstanceSuppInfo(instance.buildICTipoInstitucion());

        return response;
    }

    /*
     * Checks service eliminar preconditions individually
     *
     * @param request Service request viewmodel
     *
     * @return Service preconditions evaluation result
     */
    public ServiceResponse eliminarCheckPreconditions(com.keralty.aeconfiguraciones.viewmodel.tipoinstitucionc.siu.EliminarRequest request) {
        ServiceResponse response = new ServiceResponse();
        request.setTipoInstitucionCeliminarpthisTipoInstitucionInstance(tipoInstitucionCSrv.getByOIDWithHV(request.getTipoInstitucionCeliminarpthisTipoInstitucion()));
        try {
            if("Clas_1431947444224120Pre_1".equals(request.getPreconditionId()))
                checkClas1431947444224120Pre1(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    private void eliminarPreconditions(
            com.keralty.aeconfiguraciones.viewmodel.tipoinstitucionc.siu.EliminarRequest request) 
            throws ModelException {
        checkClas1431947444224120Pre1(request);
    }

    private void checkClas1431947444224120Pre1 (
            com.keralty.aeconfiguraciones.viewmodel.tipoinstitucionc.siu.EliminarRequest request) 
            throws ModelException {
        // EXIST(PersonasC)=false

        TipoInstitucionC tipoInstitucionCeliminarpthisTipoInstitucion = request.getTipoInstitucionCeliminarpthisTipoInstitucionInstance();
        try {
            boolean preconditionHolds = repository.assocOperator000(tipoInstitucionCeliminarpthisTipoInstitucion).equals(false);
            if (!preconditionHolds) throw new PreconditionException("No se puede eliminar el tipo de institución porque existen personas jurídicas de este tipo");
        } catch (SystemException e) {
            LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
        }
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
            com.keralty.aeconfiguraciones.viewmodel.tipoinstitucionc.siu.ModificarRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = tipoInstitucionCSrv.modificarTxn(request);
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
            com.keralty.aeconfiguraciones.viewmodel.tipoinstitucionc.siu.ModificarRequest request) throws ModelException {
        request.setTipoInstitucionCmodificarpthisTipoInstitucionCInstance(tipoInstitucionCSrv.getByOIDWithHV(request.getTipoInstitucionCmodificarpthisTipoInstitucionC()));
        return tipoInstitucionCSrv.modificarImplementation(request);
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
            com.keralty.aeconfiguraciones.viewmodel.tipoinstitucionc.siu.ModificarRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        TipoInstitucionC tipoInstitucionCmodificarpthisTipoInstitucionC = request.getTipoInstitucionCmodificarpthisTipoInstitucionCInstance();
        String tipoInstitucionCmodificarpNombre = request.getTipoInstitucionCmodificarpNombre();

        request.checkArguments();
        TipoInstitucionC instance = tipoInstitucionCmodificarpthisTipoInstitucionC;
        instance.setTipoInstitucionCNombre(tipoInstitucionCmodificarpNombre);


        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICTipoInstitucion());

        return response;
    }

    // Service: InsTipoInstitucionCPersonaC

    /*
     * Entry point for service InsTipoInstitucionCPersonaC
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse insTipoInstitucionCPersonaC(
            com.keralty.aeconfiguraciones.viewmodel.tipoinstitucionc.siu.InsTipoInstitucionCPersonaCRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = tipoInstitucionCSrv.insTipoInstitucionCPersonaCTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service InsTipoInstitucionCPersonaC
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse insTipoInstitucionCPersonaCTxn(
            com.keralty.aeconfiguraciones.viewmodel.tipoinstitucionc.siu.InsTipoInstitucionCPersonaCRequest request) throws ModelException {
        request.setTipoInstitucionCInsTipoInstitucionCPersonaCpthisTipoInstitucionCInstance(tipoInstitucionCSrv.getByOIDWithHV(request.getTipoInstitucionCInsTipoInstitucionCPersonaCpthisTipoInstitucionC()));
        request.setTipoInstitucionCInsTipoInstitucionCPersonaCpevcPersonaCInstance(personaCSrv.getByOIDWithHV(request.getTipoInstitucionCInsTipoInstitucionCPersonaCpevcPersonaC()));
        return tipoInstitucionCSrv.insTipoInstitucionCPersonaCImplementation(request, true);
    }

    /*
     * Implements service InsTipoInstitucionCPersonaC
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse insTipoInstitucionCPersonaCImplementation(
            com.keralty.aeconfiguraciones.viewmodel.tipoinstitucionc.siu.InsTipoInstitucionCPersonaCRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        TipoInstitucionC tipoInstitucionCInsTipoInstitucionCPersonaCpthisTipoInstitucionC = request.getTipoInstitucionCInsTipoInstitucionCPersonaCpthisTipoInstitucionCInstance();
        PersonaC tipoInstitucionCInsTipoInstitucionCPersonaCpevcPersonaC = request.getTipoInstitucionCInsTipoInstitucionCPersonaCpevcPersonaCInstance();

        request.checkArguments();
        TipoInstitucionC instance = tipoInstitucionCInsTipoInstitucionCPersonaCpthisTipoInstitucionC;

        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.personac.siu.InsTipoInstitucionCPersonaCRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.personac.siu.InsTipoInstitucionCPersonaCRequest();
            relatedRequest.setPersonaCInsTipoInstitucionCPersonaCpthisPersonaCInstance(tipoInstitucionCInsTipoInstitucionCPersonaCpevcPersonaC);
            relatedRequest.setPersonaCInsTipoInstitucionCPersonaCpevcTipoInstitucionCInstance(tipoInstitucionCInsTipoInstitucionCPersonaCpthisTipoInstitucionC);
            personaCSrv.insTipoInstitucionCPersonaCImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICTipoInstitucion());

        return response;
    }

    // Service: DelTipoInstitucionCPersonaC

    /*
     * Entry point for service DelTipoInstitucionCPersonaC
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse delTipoInstitucionCPersonaC(
            com.keralty.aeconfiguraciones.viewmodel.tipoinstitucionc.siu.DelTipoInstitucionCPersonaCRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = tipoInstitucionCSrv.delTipoInstitucionCPersonaCTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service DelTipoInstitucionCPersonaC
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse delTipoInstitucionCPersonaCTxn(
            com.keralty.aeconfiguraciones.viewmodel.tipoinstitucionc.siu.DelTipoInstitucionCPersonaCRequest request) throws ModelException {
        request.setTipoInstitucionCDelTipoInstitucionCPersonaCpthisTipoInstitucionCInstance(tipoInstitucionCSrv.getByOIDWithHV(request.getTipoInstitucionCDelTipoInstitucionCPersonaCpthisTipoInstitucionC()));
        request.setTipoInstitucionCDelTipoInstitucionCPersonaCpevcPersonaCInstance(personaCSrv.getByOIDWithHV(request.getTipoInstitucionCDelTipoInstitucionCPersonaCpevcPersonaC()));
        return tipoInstitucionCSrv.delTipoInstitucionCPersonaCImplementation(request, true);
    }

    /*
     * Implements service DelTipoInstitucionCPersonaC
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse delTipoInstitucionCPersonaCImplementation(
            com.keralty.aeconfiguraciones.viewmodel.tipoinstitucionc.siu.DelTipoInstitucionCPersonaCRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        TipoInstitucionC tipoInstitucionCDelTipoInstitucionCPersonaCpthisTipoInstitucionC = request.getTipoInstitucionCDelTipoInstitucionCPersonaCpthisTipoInstitucionCInstance();
        PersonaC tipoInstitucionCDelTipoInstitucionCPersonaCpevcPersonaC = request.getTipoInstitucionCDelTipoInstitucionCPersonaCpevcPersonaCInstance();

        request.checkArguments();
        TipoInstitucionC instance = tipoInstitucionCDelTipoInstitucionCPersonaCpthisTipoInstitucionC;

        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.personac.siu.DelTipoInstitucionCPersonaCRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.personac.siu.DelTipoInstitucionCPersonaCRequest();
            relatedRequest.setPersonaCDelTipoInstitucionCPersonaCpthisPersonaCInstance(tipoInstitucionCDelTipoInstitucionCPersonaCpevcPersonaC);
            relatedRequest.setPersonaCDelTipoInstitucionCPersonaCpevcTipoInstitucionCInstance(tipoInstitucionCDelTipoInstitucionCPersonaCpthisTipoInstitucionC);
            personaCSrv.delTipoInstitucionCPersonaCImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICTipoInstitucion());

        return response;
    }


    private void suppInfo4ICTipoInstitucion(String searchText, SQLSelect select) {
        try {
            SQLWhereDisjunction disjunction = new SQLWhereDisjunction();

            SQLWhereComparison comparison1 = new SQLWhereComparison(select);
            comparison1.setOperator("LIKE");
            comparison1.setFirstOperand(new SQLWhereOperandPathAttribute(TipoInstitucionCConstants.ATTR_NAME_TIPOINSTITUCIONCNOMBRE, Constants.Type.STRING.getTypeName()));
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
        applyDSItemSort(queryRequest, select);
        applyDefaultOrderCriteria(queryRequest, select);
    }

    private void applyDefaultOrderCriteria(QueryRequest queryRequest, SQLSelect select) {
        if (queryRequest.getOrderCriteriaName() != null && !queryRequest.getOrderCriteriaName().isBlank())
            return;
        if (queryRequest.getDsItemSort() != null && !queryRequest.getDsItemSort().isBlank())
            return;
        select.getOrderByPart().addItem(Constants.normalizePath(TipoInstitucionCConstants.CLASS_NAME, TipoInstitucionCConstants.ATTR_FIELD_TIPOINSTITUCIONCCODIGO, false), true);
    }
    
    @Override
    protected AbstractAppEntity buildRelatedInstance(String relatedInstanceJsonOid, String relatedInstancePathFirstItem, String remainingPath) {
        AbstractAppEntity relatedInstance = null;
        if (relatedInstancePathFirstItem.isEmpty()) {
            relatedInstance = buildTipoInstitucionCRelatedInstance(relatedInstanceJsonOid);
        }
        if (TipoInstitucionCConstants.ROLE_NAME_PERSONASC.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildTipoInstitucionCRelatedInstance4PersonaC(relatedInstanceJsonOid, remainingPath);
        }
        return relatedInstance;
    }
    
    private AbstractAppEntity buildTipoInstitucionCRelatedInstance(String relatedInstanceJsonOid) {
    	ObjectMapper mapper = new ObjectMapper();
    	try {
    		return new TipoInstitucionC(mapper.readValue(relatedInstanceJsonOid, TipoInstitucionCOid.class));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    private AbstractAppEntity buildTipoInstitucionCRelatedInstance4PersonaC(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new PersonaC(mapper.readValue(relatedInstanceJsonOid, PersonaCOid.class)): PersonaC.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    @Override
    protected void applyFilterByOid(String jsonOid, SQLSelect sql) {
        try {
            TipoInstitucionCOid oid = new TipoInstitucionCOid().fromJsonString(jsonOid);
            SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
            comparison1.setOperator("=");
            comparison1.setFirstOperand(new SQLWhereOperandPathAttribute(TipoInstitucionCConstants.ATTR_FIELD_TIPOINSTITUCIONCCODIGO, Constants.Type.INT.getTypeName()));
            comparison1.setSecondOperand(new SQLWhereOperandLiteral(oid.getTipoInstitucionCCodigo(), Constants.Type.INT.getTypeName()));
            sql.addComparison(comparison1);
        } catch (Exception e) {
            LoggerFactory.getLogger(TipoInstitucionCService.class).error(e.getMessage());
        }
    }

}
