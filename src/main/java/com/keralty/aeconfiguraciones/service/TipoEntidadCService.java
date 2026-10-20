package com.keralty.aeconfiguraciones.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.keralty.aeconfiguraciones.exception.InstanceExistsException;
import com.keralty.aeconfiguraciones.exception.MinCardinalityException;
import com.keralty.aeconfiguraciones.exception.ModelException;
import com.keralty.aeconfiguraciones.exception.PreconditionException;
import com.keralty.aeconfiguraciones.exception.SystemException;
import com.keralty.aeconfiguraciones.global.Constants;
import com.keralty.aeconfiguraciones.global.TipoEntidadCConstants;
import com.keralty.aeconfiguraciones.persistence.AbstractAppEntity;
import com.keralty.aeconfiguraciones.persistence.oid.PersonaCOid;
import com.keralty.aeconfiguraciones.persistence.oid.TipoEntidadCOid;
import com.keralty.aeconfiguraciones.persistence.PersonaC;
import com.keralty.aeconfiguraciones.persistence.TipoEntidadC;
import com.keralty.aeconfiguraciones.repository.TipoEntidadCJpaRepository;
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
public class TipoEntidadCService extends AbstractService {

    TipoEntidadCJpaRepository repository;

    TipoEntidadCService tipoEntidadCSrv;

    PersonaCService personaCSrv;

    @Inject
    public TipoEntidadCService(
            TipoEntidadCService tipoEntidadCSrv,
            PersonaCService personaCSrv,
            TipoEntidadCJpaRepository repository) {
        this.tipoEntidadCSrv = tipoEntidadCSrv;
        this.personaCSrv = personaCSrv;
        this.repository = repository;
    }

    public List<Map<String, Object>> solveQuery(QueryRequest request) {
        return solveGenericQuery(request, TipoEntidadCConstants.CLASS_NAME);
    }

    private List<TipoEntidadC> solveTipoEntidadCTypedQuery(QueryRequest request) {
        return repository.solveQuery(processQueryRequest(request, TipoEntidadCConstants.CLASS_NAME), request.getPageSize(), request.getPageNumber());
    }
    
    private int solveTipoEntidadCTypedCountQuery(QueryRequest request) {
        return repository.solveCountQuery(processCountQueryRequest(request, TipoEntidadCConstants.CLASS_NAME));
    }

    /*
     * Returns the population of class TipoEntidadC
     *
     * @return Population of class TipoEntidadC
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<TipoEntidadC> get() {
        List<TipoEntidadC> results = new java.util.ArrayList<>();
        repository.findAll().forEach(results::add);
        return results;
    }
    
    /*
     * Gets the preloaded list of objects of class TipoEntidadC
     *
     * @param queryRequest Request object that dictates the search conditions to 
     * retrieve objects
     *
     * @return Preloaded list of objects of class TipoEntidadC
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<PreloadViewModel> getPreload(QueryRequest queryRequest) {
        List<PreloadViewModel> results = new ArrayList<>();
        List<TipoEntidadC> instances = get(queryRequest);
        if(TipoEntidadCConstants.DSICTIPOENTIDAD.equals(queryRequest.getDisplaySetName())) {
            results = instances.stream().map(x -> new PreloadViewModel(x.getOid().toJson(), x.buildICTipoEntidad())).collect(Collectors.toList());
        }
        return results;
    }
    /*
     * Returns the population of class TipoEntidadC that matches certain search criteria
     *
     * @param queryRequest Search criteria
     *
     * @return Population of class TipoEntidadC matching the search criteria
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<TipoEntidadC> get(QueryRequest queryRequest) {
        return solveTipoEntidadCTypedQuery(queryRequest);
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
            return new PopulationResponseViewModel(solveTipoEntidadCTypedQuery(queryRequest).stream()
                    .map(instance -> buildDynamicObject(instance, queryRequest.getDisplaySetItems()))
                    .collect(Collectors.toList()), queryRequest.getPageSize() != 0 ? solveTipoEntidadCTypedCountQuery(queryRequest) : 0);
        } else {
            return new PreloadResponseViewModel(solveTipoEntidadCTypedQuery(queryRequest).stream()
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
        return new PopulationResponseViewModel(solveTipoEntidadCTypedQuery(queryRequest).stream()
                .map(instance -> buildDynamicObject(instance, displaySetItems))
                .collect(Collectors.toList()));
    }

    /*
     * Returns an instance of TipoEntidadC by ID
     *
     * @param tipoEntidadCCodigo Identification field tipoEntidadCCodigo
     *
     * @return Instance of TipoEntidadC
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public TipoEntidadC getById(Long tipoEntidadCCodigo) {
        return getByOID(new TipoEntidadCOid(tipoEntidadCCodigo));
    }
    
    /*
     * Returns an instance of TipoEntidadC by OID
     *
     * @param oid Object IDentifier
     *
     * @return Instance of TipoEntidadC
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public TipoEntidadC getByOID(TipoEntidadCOid oid) {
        Optional<TipoEntidadC> instance = getInstanceByOid(oid);
        return instance.isPresent() ? instance.get() : null;
    }
    
    /*
     * Returns an instance of TipoEntidadC by OID applying horizontal visibility constraints
     *
     * @param oid Object IDentifier
     *
     * @return Instance of TipoEntidadC
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public TipoEntidadC getByOIDWithHV(TipoEntidadCOid oid) {
        Optional<TipoEntidadC> instance = getInstanceByOid(oid);
    	return instance.isPresent() && instance.get().checkHorizontalVisibility(getAgentInstance()) 
    		 ? instance.get() 
    		 : null;
    }
    
    private Optional<TipoEntidadC> getInstanceByOid(TipoEntidadCOid oid) {
        return oid != null && oid.getTipoEntidadCCodigo() != null
               ? repository.findById(oid.getTipoEntidadCCodigo())
               : Optional.empty();
    }

    /*
     * Returns an instance of TipoEntidadC by OID in JSON format
     *
     * @param jsonOid Object IDentifier in JSON format
     *
     * @return Instance of TipoEntidadC
     */
    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public TipoEntidadC getByJsonOid(String jsonOid) {
        TipoEntidadCOid oid = new TipoEntidadCOid();
        oid.fromJsonString(jsonOid);
        return getByOIDWithHV(oid);
    }

    /*
     * Returns the list of instances of TipoEntidadC related to a given instance through 
     * a given path
     *
     * @param relatedInstance Related instance
     *
     * @param relatedInstancePath Related instance path
     *
     * @return List of instances of TipoEntidadC
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<TipoEntidadC> getByRelated(AbstractAppEntity relatedInstance, String relatedInstancePath) {
        QueryRequest queryRequest = new QueryRequest(new FilterRequest(Constants.FILTER_BY_RELATED,
                Arrays.asList(new FilterVariableRequest(Constants.FILTER_BY_RELATED_FVAR_PATH, relatedInstancePath),
                        new FilterVariableRequest(Constants.FILTER_BY_RELATED_FVAR_OID, relatedInstance.getOid().toJson()))));
        return solveTipoEntidadCTypedQuery(queryRequest);
    }

    /*
     * Returns the list of instances of TipoEntidadC related to a given instance through
     * a given path
     *
     * @param relatedInstance Related instance
     *
     * @param relatedInstancePath Related instance path
     *
     * @return List of instances of TipoEntidadC
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<TipoEntidadC> getInstancesByRelated(AbstractAppEntity relatedInstance, String relatedInstancePath) {
        return getByRelated(relatedInstance, relatedInstancePath);
    }

    /*
     * Returns a set of displayset items for an instance of TipoEntidadC
     *
     * @param oid Object IDentifier
     *
     * @param displaySetItems List of comma-separated displayset items
     *
     * @return Set of displayset items for an instance of TipoEntidadC
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public InstanceResponseViewModel getTipoEntidadCDynamic(TipoEntidadCOid oid, String displaySetItems) {
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
    public String getSuppInfo(TipoEntidadCOid oid, String dsName) {
        TipoEntidadC instance = getByOIDWithHV(oid);
        return (instance != null ? getSuppInfo(instance, dsName): "");
    }

    private String getSuppInfo(TipoEntidadC instance, String dsName) {
        if (instance == null || instance.isNull()) return "";
        if (TipoEntidadCConstants.DSICTIPOENTIDAD.equals(dsName))
            return instance.buildICTipoEntidad();
        return dsName.isEmpty() ? instance.getOid().toJson() : "";
    }

    public static PreloadViewModel buildPreloadViewModel(TipoEntidadC instance, String dsName) {
        if (TipoEntidadCConstants.DSICTIPOENTIDAD.equals(dsName))
            return new PreloadViewModel(instance.getOid().toJson(), instance.buildICTipoEntidad());
        return new PreloadViewModel(instance.getOid().toJson(), "");
    }


    @Override
    protected void applyServiceSIFilters(FilterRequest filterRequest, SQLSelect sql) {
        if ("SuppInfo_IC_TipoEntidad".equals(filterRequest.getName()))
            suppInfo4ICTipoEntidad(filterRequest.getFilterVariable("searchText").toString(), sql);
    }



    // IIU Methods


    /*
     * Solves the query needed for PIUTipoEntidad
     *
     * @param queryRequest Query request
     * @return List of viewmodels for TipoEntidadC using displayset AutoDS that 
     * match the search criteria
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<com.keralty.aeconfiguraciones.viewmodel.tipoentidadc.ds.AutoDS> piuPIUTipoEntidad(QueryRequest queryRequest) {
        return solveTipoEntidadCTypedQuery(queryRequest).stream()
                .map(com.keralty.aeconfiguraciones.viewmodel.tipoentidadc.ds.AutoDS::new)
                .collect(Collectors.toList());
    }

    /*
     * Solves the COUNT query needed for PIUTipoEntidad
     *
     * @param queryRequest Query request
     * @returns Count of instances for PIUTipoEntidad
     */
    public int getCount4PIUTipoEntidad(QueryRequest queryRequest) {
        return solveTipoEntidadCTypedQuery(queryRequest).size();
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
            com.keralty.aeconfiguraciones.viewmodel.tipoentidadc.siu.CrearRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = tipoEntidadCSrv.crearTxn(request);
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
            com.keralty.aeconfiguraciones.viewmodel.tipoentidadc.siu.CrearRequest request) throws ModelException {
        return tipoEntidadCSrv.crearImplementation(request);
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
            com.keralty.aeconfiguraciones.viewmodel.tipoentidadc.siu.CrearRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Long tipoEntidadCcrearpatrCodigo = request.getTipoEntidadCcrearpatrCodigo();
        String tipoEntidadCcrearpatrDescripcion = request.getTipoEntidadCcrearpatrDescripcion();

        request.checkArguments();
        TipoEntidadC instance = new TipoEntidadC();
        instance.setTipoEntidadCCodigo(tipoEntidadCcrearpatrCodigo);
        instance.setTipoEntidadCDescripcion(tipoEntidadCcrearpatrDescripcion);
        if (tipoEntidadCSrv.getByOID(instance.getOid()) != null) {
            throw new InstanceExistsException(TipoEntidadCConstants.CLASS_ALIAS);
        }

        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICTipoEntidad());

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
            com.keralty.aeconfiguraciones.viewmodel.tipoentidadc.siu.EliminarRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = tipoEntidadCSrv.eliminarTxn(request);
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
            com.keralty.aeconfiguraciones.viewmodel.tipoentidadc.siu.EliminarRequest request) throws ModelException {
        request.setTipoEntidadCeliminarpthisTipoEntidadInstance(tipoEntidadCSrv.getByOIDWithHV(request.getTipoEntidadCeliminarpthisTipoEntidad()));
        return tipoEntidadCSrv.eliminarImplementation(request);
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
            com.keralty.aeconfiguraciones.viewmodel.tipoentidadc.siu.EliminarRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        TipoEntidadC tipoEntidadCeliminarpthisTipoEntidad = request.getTipoEntidadCeliminarpthisTipoEntidadInstance();

        request.checkArguments();

        eliminarPreconditions(request);
        TipoEntidadC instance = tipoEntidadCeliminarpthisTipoEntidad;
        if(tipoEntidadCeliminarpthisTipoEntidad.getPersonasC().size() > 0) {
            throw new MinCardinalityException(TipoEntidadCConstants.CLASS_ALIAS, TipoEntidadCConstants.ROLE_ALIAS_PERSONASC);
        }


        repository.delete(instance);
        
        response.setInstance(instance);
        response.setInstanceSuppInfo(instance.buildICTipoEntidad());

        return response;
    }

    /*
     * Checks service eliminar preconditions individually
     *
     * @param request Service request viewmodel
     *
     * @return Service preconditions evaluation result
     */
    public ServiceResponse eliminarCheckPreconditions(com.keralty.aeconfiguraciones.viewmodel.tipoentidadc.siu.EliminarRequest request) {
        ServiceResponse response = new ServiceResponse();
        request.setTipoEntidadCeliminarpthisTipoEntidadInstance(tipoEntidadCSrv.getByOIDWithHV(request.getTipoEntidadCeliminarpthisTipoEntidad()));
        try {
            if("Clas_1431947444224551Pre_1".equals(request.getPreconditionId()))
                checkClas1431947444224551Pre1(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    private void eliminarPreconditions(
            com.keralty.aeconfiguraciones.viewmodel.tipoentidadc.siu.EliminarRequest request) 
            throws ModelException {
        checkClas1431947444224551Pre1(request);
    }

    private void checkClas1431947444224551Pre1 (
            com.keralty.aeconfiguraciones.viewmodel.tipoentidadc.siu.EliminarRequest request) 
            throws ModelException {
        // EXIST(PersonasC)=false

        TipoEntidadC tipoEntidadCeliminarpthisTipoEntidad = request.getTipoEntidadCeliminarpthisTipoEntidadInstance();
        try {
            boolean preconditionHolds = repository.assocOperator000(tipoEntidadCeliminarpthisTipoEntidad).equals(false);
            if (!preconditionHolds) throw new PreconditionException("No se puede eliminar el tipo de entidad porque existen personas que lo tienen indicado");
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
            com.keralty.aeconfiguraciones.viewmodel.tipoentidadc.siu.ModificarRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = tipoEntidadCSrv.modificarTxn(request);
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
            com.keralty.aeconfiguraciones.viewmodel.tipoentidadc.siu.ModificarRequest request) throws ModelException {
        request.setTipoEntidadCmodificarpthisTipoEntidadCInstance(tipoEntidadCSrv.getByOIDWithHV(request.getTipoEntidadCmodificarpthisTipoEntidadC()));
        return tipoEntidadCSrv.modificarImplementation(request);
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
            com.keralty.aeconfiguraciones.viewmodel.tipoentidadc.siu.ModificarRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        TipoEntidadC tipoEntidadCmodificarpthisTipoEntidadC = request.getTipoEntidadCmodificarpthisTipoEntidadCInstance();
        String tipoEntidadCmodificarpDescripcion = request.getTipoEntidadCmodificarpDescripcion();

        request.checkArguments();
        TipoEntidadC instance = tipoEntidadCmodificarpthisTipoEntidadC;
        instance.setTipoEntidadCDescripcion(tipoEntidadCmodificarpDescripcion);


        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICTipoEntidad());

        return response;
    }

    // Service: InsTipoEntidadCPersonaC

    /*
     * Entry point for service InsTipoEntidadCPersonaC
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse insTipoEntidadCPersonaC(
            com.keralty.aeconfiguraciones.viewmodel.tipoentidadc.siu.InsTipoEntidadCPersonaCRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = tipoEntidadCSrv.insTipoEntidadCPersonaCTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service InsTipoEntidadCPersonaC
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse insTipoEntidadCPersonaCTxn(
            com.keralty.aeconfiguraciones.viewmodel.tipoentidadc.siu.InsTipoEntidadCPersonaCRequest request) throws ModelException {
        request.setTipoEntidadCInsTipoEntidadCPersonaCpthisTipoEntidadCInstance(tipoEntidadCSrv.getByOIDWithHV(request.getTipoEntidadCInsTipoEntidadCPersonaCpthisTipoEntidadC()));
        request.setTipoEntidadCInsTipoEntidadCPersonaCpevcPersonaCInstance(personaCSrv.getByOIDWithHV(request.getTipoEntidadCInsTipoEntidadCPersonaCpevcPersonaC()));
        return tipoEntidadCSrv.insTipoEntidadCPersonaCImplementation(request, true);
    }

    /*
     * Implements service InsTipoEntidadCPersonaC
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse insTipoEntidadCPersonaCImplementation(
            com.keralty.aeconfiguraciones.viewmodel.tipoentidadc.siu.InsTipoEntidadCPersonaCRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        TipoEntidadC tipoEntidadCInsTipoEntidadCPersonaCpthisTipoEntidadC = request.getTipoEntidadCInsTipoEntidadCPersonaCpthisTipoEntidadCInstance();
        PersonaC tipoEntidadCInsTipoEntidadCPersonaCpevcPersonaC = request.getTipoEntidadCInsTipoEntidadCPersonaCpevcPersonaCInstance();

        request.checkArguments();
        TipoEntidadC instance = tipoEntidadCInsTipoEntidadCPersonaCpthisTipoEntidadC;

        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.personac.siu.InsTipoEntidadCPersonaCRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.personac.siu.InsTipoEntidadCPersonaCRequest();
            relatedRequest.setPersonaCInsTipoEntidadCPersonaCpthisPersonaCInstance(tipoEntidadCInsTipoEntidadCPersonaCpevcPersonaC);
            relatedRequest.setPersonaCInsTipoEntidadCPersonaCpevcTipoEntidadCInstance(tipoEntidadCInsTipoEntidadCPersonaCpthisTipoEntidadC);
            personaCSrv.insTipoEntidadCPersonaCImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICTipoEntidad());

        return response;
    }

    // Service: DelTipoEntidadCPersonaC

    /*
     * Entry point for service DelTipoEntidadCPersonaC
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse delTipoEntidadCPersonaC(
            com.keralty.aeconfiguraciones.viewmodel.tipoentidadc.siu.DelTipoEntidadCPersonaCRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = tipoEntidadCSrv.delTipoEntidadCPersonaCTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service DelTipoEntidadCPersonaC
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse delTipoEntidadCPersonaCTxn(
            com.keralty.aeconfiguraciones.viewmodel.tipoentidadc.siu.DelTipoEntidadCPersonaCRequest request) throws ModelException {
        request.setTipoEntidadCDelTipoEntidadCPersonaCpthisTipoEntidadCInstance(tipoEntidadCSrv.getByOIDWithHV(request.getTipoEntidadCDelTipoEntidadCPersonaCpthisTipoEntidadC()));
        request.setTipoEntidadCDelTipoEntidadCPersonaCpevcPersonaCInstance(personaCSrv.getByOIDWithHV(request.getTipoEntidadCDelTipoEntidadCPersonaCpevcPersonaC()));
        return tipoEntidadCSrv.delTipoEntidadCPersonaCImplementation(request, true);
    }

    /*
     * Implements service DelTipoEntidadCPersonaC
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse delTipoEntidadCPersonaCImplementation(
            com.keralty.aeconfiguraciones.viewmodel.tipoentidadc.siu.DelTipoEntidadCPersonaCRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        TipoEntidadC tipoEntidadCDelTipoEntidadCPersonaCpthisTipoEntidadC = request.getTipoEntidadCDelTipoEntidadCPersonaCpthisTipoEntidadCInstance();
        PersonaC tipoEntidadCDelTipoEntidadCPersonaCpevcPersonaC = request.getTipoEntidadCDelTipoEntidadCPersonaCpevcPersonaCInstance();

        request.checkArguments();
        TipoEntidadC instance = tipoEntidadCDelTipoEntidadCPersonaCpthisTipoEntidadC;

        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.personac.siu.DelTipoEntidadCPersonaCRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.personac.siu.DelTipoEntidadCPersonaCRequest();
            relatedRequest.setPersonaCDelTipoEntidadCPersonaCpthisPersonaCInstance(tipoEntidadCDelTipoEntidadCPersonaCpevcPersonaC);
            relatedRequest.setPersonaCDelTipoEntidadCPersonaCpevcTipoEntidadCInstance(tipoEntidadCDelTipoEntidadCPersonaCpthisTipoEntidadC);
            personaCSrv.delTipoEntidadCPersonaCImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICTipoEntidad());

        return response;
    }


    private void suppInfo4ICTipoEntidad(String searchText, SQLSelect select) {
        try {
            SQLWhereDisjunction disjunction = new SQLWhereDisjunction();

            SQLWhereComparison comparison1 = new SQLWhereComparison(select);
            comparison1.setOperator("LIKE");
            comparison1.setFirstOperand(new SQLWhereOperandPathAttribute(TipoEntidadCConstants.ATTR_NAME_TIPOENTIDADCDESCRIPCION, Constants.Type.STRING.getTypeName()));
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
        select.getOrderByPart().addItem(Constants.normalizePath(TipoEntidadCConstants.CLASS_NAME, TipoEntidadCConstants.ATTR_FIELD_TIPOENTIDADCCODIGO, false), true);
    }
    
    @Override
    protected AbstractAppEntity buildRelatedInstance(String relatedInstanceJsonOid, String relatedInstancePathFirstItem, String remainingPath) {
        AbstractAppEntity relatedInstance = null;
        if (relatedInstancePathFirstItem.isEmpty()) {
            relatedInstance = buildTipoEntidadCRelatedInstance(relatedInstanceJsonOid);
        }
        if (TipoEntidadCConstants.ROLE_NAME_PERSONASC.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildTipoEntidadCRelatedInstance4PersonaC(relatedInstanceJsonOid, remainingPath);
        }
        return relatedInstance;
    }
    
    private AbstractAppEntity buildTipoEntidadCRelatedInstance(String relatedInstanceJsonOid) {
    	ObjectMapper mapper = new ObjectMapper();
    	try {
    		return new TipoEntidadC(mapper.readValue(relatedInstanceJsonOid, TipoEntidadCOid.class));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    private AbstractAppEntity buildTipoEntidadCRelatedInstance4PersonaC(String relatedInstanceJsonOid, String remainingPath) {
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
            TipoEntidadCOid oid = new TipoEntidadCOid().fromJsonString(jsonOid);
            SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
            comparison1.setOperator("=");
            comparison1.setFirstOperand(new SQLWhereOperandPathAttribute(TipoEntidadCConstants.ATTR_FIELD_TIPOENTIDADCCODIGO, Constants.Type.INT.getTypeName()));
            comparison1.setSecondOperand(new SQLWhereOperandLiteral(oid.getTipoEntidadCCodigo(), Constants.Type.INT.getTypeName()));
            sql.addComparison(comparison1);
        } catch (Exception e) {
            LoggerFactory.getLogger(TipoEntidadCService.class).error(e.getMessage());
        }
    }

}
