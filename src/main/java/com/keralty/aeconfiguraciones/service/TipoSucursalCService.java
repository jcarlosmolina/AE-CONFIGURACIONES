package com.keralty.aeconfiguraciones.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.keralty.aeconfiguraciones.exception.InstanceExistsException;
import com.keralty.aeconfiguraciones.exception.ModelException;
import com.keralty.aeconfiguraciones.exception.PreconditionException;
import com.keralty.aeconfiguraciones.exception.SystemException;
import com.keralty.aeconfiguraciones.global.Constants;
import com.keralty.aeconfiguraciones.global.TipoSucursalCConstants;
import com.keralty.aeconfiguraciones.persistence.AbstractAppEntity;
import com.keralty.aeconfiguraciones.persistence.DirSucursalC;
import com.keralty.aeconfiguraciones.persistence.oid.DirSucursalCOid;
import com.keralty.aeconfiguraciones.persistence.oid.TipoSucursalCOid;
import com.keralty.aeconfiguraciones.persistence.TipoSucursalC;
import com.keralty.aeconfiguraciones.repository.TipoSucursalCJpaRepository;
import com.keralty.aeconfiguraciones.sqlutils.SQLSelect;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereComparison;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereOperandLiteral;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereOperandPathAttribute;
import com.keralty.aeconfiguraciones.viewmodel.FilterRequest;
import com.keralty.aeconfiguraciones.viewmodel.FilterVariableRequest;
import com.keralty.aeconfiguraciones.viewmodel.InstanceResponseViewModel;
import com.keralty.aeconfiguraciones.viewmodel.PopulationResponseViewModel;
import com.keralty.aeconfiguraciones.viewmodel.PreloadResponseViewModel;
import com.keralty.aeconfiguraciones.viewmodel.QueryRequest;
import com.keralty.aeconfiguraciones.viewmodel.QueryResponse;
import com.keralty.aeconfiguraciones.viewmodel.ServiceResponse;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.LoggerFactory;

@Transactional
@ApplicationScoped
public class TipoSucursalCService extends AbstractService {

    TipoSucursalCJpaRepository repository;

    TipoSucursalCService tipoSucursalCSrv;

    DirSucursalCService dirSucursalCSrv;

    @Inject
    public TipoSucursalCService(
            TipoSucursalCService tipoSucursalCSrv,
            DirSucursalCService dirSucursalCSrv,
            TipoSucursalCJpaRepository repository) {
        this.tipoSucursalCSrv = tipoSucursalCSrv;
        this.dirSucursalCSrv = dirSucursalCSrv;
        this.repository = repository;
    }

    public List<Map<String, Object>> solveQuery(QueryRequest request) {
        return solveGenericQuery(request, TipoSucursalCConstants.CLASS_NAME);
    }

    private List<TipoSucursalC> solveTipoSucursalCTypedQuery(QueryRequest request) {
        return repository.solveQuery(processQueryRequest(request, TipoSucursalCConstants.CLASS_NAME), request.getPageSize(), request.getPageNumber());
    }
    
    private int solveTipoSucursalCTypedCountQuery(QueryRequest request) {
        return repository.solveCountQuery(processCountQueryRequest(request, TipoSucursalCConstants.CLASS_NAME));
    }

    /*
     * Returns the population of class TipoSucursalC
     *
     * @return Population of class TipoSucursalC
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<TipoSucursalC> get() {
        List<TipoSucursalC> results = new java.util.ArrayList<>();
        repository.findAll().forEach(results::add);
        return results;
    }
    /*
     * Returns the population of class TipoSucursalC that matches certain search criteria
     *
     * @param queryRequest Search criteria
     *
     * @return Population of class TipoSucursalC matching the search criteria
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<TipoSucursalC> get(QueryRequest queryRequest) {
        return solveTipoSucursalCTypedQuery(queryRequest);
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
            return new PopulationResponseViewModel(solveTipoSucursalCTypedQuery(queryRequest).stream()
                    .map(instance -> buildDynamicObject(instance, queryRequest.getDisplaySetItems()))
                    .collect(Collectors.toList()), queryRequest.getPageSize() != 0 ? solveTipoSucursalCTypedCountQuery(queryRequest) : 0);
        } else {
            return new PreloadResponseViewModel();
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
        return new PopulationResponseViewModel(solveTipoSucursalCTypedQuery(queryRequest).stream()
                .map(instance -> buildDynamicObject(instance, displaySetItems))
                .collect(Collectors.toList()));
    }

    /*
     * Returns an instance of TipoSucursalC by ID
     *
     * @param tipoSucursalCCodigo Identification field tipoSucursalCCodigo
     *
     * @return Instance of TipoSucursalC
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public TipoSucursalC getById(Long tipoSucursalCCodigo) {
        return getByOID(new TipoSucursalCOid(tipoSucursalCCodigo));
    }
    
    /*
     * Returns an instance of TipoSucursalC by OID
     *
     * @param oid Object IDentifier
     *
     * @return Instance of TipoSucursalC
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public TipoSucursalC getByOID(TipoSucursalCOid oid) {
        Optional<TipoSucursalC> instance = getInstanceByOid(oid);
        return instance.isPresent() ? instance.get() : null;
    }
    
    /*
     * Returns an instance of TipoSucursalC by OID applying horizontal visibility constraints
     *
     * @param oid Object IDentifier
     *
     * @return Instance of TipoSucursalC
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public TipoSucursalC getByOIDWithHV(TipoSucursalCOid oid) {
        Optional<TipoSucursalC> instance = getInstanceByOid(oid);
    	return instance.isPresent() && instance.get().checkHorizontalVisibility(getAgentInstance()) 
    		 ? instance.get() 
    		 : null;
    }
    
    private Optional<TipoSucursalC> getInstanceByOid(TipoSucursalCOid oid) {
        return oid != null && oid.getTipoSucursalCCodigo() != null
               ? repository.findById(oid.getTipoSucursalCCodigo())
               : Optional.empty();
    }

    /*
     * Returns an instance of TipoSucursalC by OID in JSON format
     *
     * @param jsonOid Object IDentifier in JSON format
     *
     * @return Instance of TipoSucursalC
     */
    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public TipoSucursalC getByJsonOid(String jsonOid) {
        TipoSucursalCOid oid = new TipoSucursalCOid();
        oid.fromJsonString(jsonOid);
        return getByOIDWithHV(oid);
    }

    /*
     * Returns the list of instances of TipoSucursalC related to a given instance through 
     * a given path
     *
     * @param relatedInstance Related instance
     *
     * @param relatedInstancePath Related instance path
     *
     * @return List of instances of TipoSucursalC
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<TipoSucursalC> getByRelated(AbstractAppEntity relatedInstance, String relatedInstancePath) {
        QueryRequest queryRequest = new QueryRequest(new FilterRequest(Constants.FILTER_BY_RELATED,
                Arrays.asList(new FilterVariableRequest(Constants.FILTER_BY_RELATED_FVAR_PATH, relatedInstancePath),
                        new FilterVariableRequest(Constants.FILTER_BY_RELATED_FVAR_OID, relatedInstance.getOid().toJson()))));
        return solveTipoSucursalCTypedQuery(queryRequest);
    }

    /*
     * Returns the list of instances of TipoSucursalC related to a given instance through
     * a given path
     *
     * @param relatedInstance Related instance
     *
     * @param relatedInstancePath Related instance path
     *
     * @return List of instances of TipoSucursalC
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<TipoSucursalC> getInstancesByRelated(AbstractAppEntity relatedInstance, String relatedInstancePath) {
        return getByRelated(relatedInstance, relatedInstancePath);
    }

    /*
     * Returns a set of displayset items for an instance of TipoSucursalC
     *
     * @param oid Object IDentifier
     *
     * @param displaySetItems List of comma-separated displayset items
     *
     * @return Set of displayset items for an instance of TipoSucursalC
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public InstanceResponseViewModel getTipoSucursalCDynamic(TipoSucursalCOid oid, String displaySetItems) {
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
    public String getSuppInfo(TipoSucursalCOid oid, String dsName) {
        TipoSucursalC instance = getByOIDWithHV(oid);
        return (instance != null ? getSuppInfo(instance, dsName): "");
    }

    private String getSuppInfo(TipoSucursalC instance, String dsName) {
        if (instance == null || instance.isNull()) return "";
        return dsName.isEmpty() ? instance.getOid().toJson() : "";
    }




    // IIU Methods


    /*
     * Solves the query needed for PIUTipoSucursalC
     *
     * @param queryRequest Query request
     * @return List of viewmodels for TipoSucursalC using displayset AutoDS that 
     * match the search criteria
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<com.keralty.aeconfiguraciones.viewmodel.tiposucursalc.ds.AutoDS> piuPIUTipoSucursalC(QueryRequest queryRequest) {
        return solveTipoSucursalCTypedQuery(queryRequest).stream()
                .map(com.keralty.aeconfiguraciones.viewmodel.tiposucursalc.ds.AutoDS::new)
                .collect(Collectors.toList());
    }

    /*
     * Solves the COUNT query needed for PIUTipoSucursalC
     *
     * @param queryRequest Query request
     * @returns Count of instances for PIUTipoSucursalC
     */
    public int getCount4PIUTipoSucursalC(QueryRequest queryRequest) {
        return solveTipoSucursalCTypedQuery(queryRequest).size();
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
            com.keralty.aeconfiguraciones.viewmodel.tiposucursalc.siu.CrearRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = tipoSucursalCSrv.crearTxn(request);
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
            com.keralty.aeconfiguraciones.viewmodel.tiposucursalc.siu.CrearRequest request) throws ModelException {
        return tipoSucursalCSrv.crearImplementation(request);
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
            com.keralty.aeconfiguraciones.viewmodel.tiposucursalc.siu.CrearRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Long tipoSucursalCcrearpatrCodigo = request.getTipoSucursalCcrearpatrCodigo();
        String tipoSucursalCcrearpatrDescripcion = request.getTipoSucursalCcrearpatrDescripcion();

        request.checkArguments();
        TipoSucursalC instance = new TipoSucursalC();
        instance.setTipoSucursalCCodigo(tipoSucursalCcrearpatrCodigo);
        instance.setTipoSucursalCDescripcion(tipoSucursalCcrearpatrDescripcion);
        if (tipoSucursalCSrv.getByOID(instance.getOid()) != null) {
            throw new InstanceExistsException(TipoSucursalCConstants.CLASS_ALIAS);
        }

        response.setInstance(repository.save(instance));

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
            com.keralty.aeconfiguraciones.viewmodel.tiposucursalc.siu.EliminarRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = tipoSucursalCSrv.eliminarTxn(request);
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
            com.keralty.aeconfiguraciones.viewmodel.tiposucursalc.siu.EliminarRequest request) throws ModelException {
        request.setTipoSucursalCeliminarpthisTipoSucursalCInstance(tipoSucursalCSrv.getByOIDWithHV(request.getTipoSucursalCeliminarpthisTipoSucursalC()));
        return tipoSucursalCSrv.eliminarImplementation(request);
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
            com.keralty.aeconfiguraciones.viewmodel.tiposucursalc.siu.EliminarRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        TipoSucursalC tipoSucursalCeliminarpthisTipoSucursalC = request.getTipoSucursalCeliminarpthisTipoSucursalCInstance();

        request.checkArguments();

        eliminarPreconditions(request);
        TipoSucursalC instance = tipoSucursalCeliminarpthisTipoSucursalC;


        repository.delete(instance);
        
        response.setInstance(instance);

        return response;
    }

    /*
     * Checks service eliminar preconditions individually
     *
     * @param request Service request viewmodel
     *
     * @return Service preconditions evaluation result
     */
    public ServiceResponse eliminarCheckPreconditions(com.keralty.aeconfiguraciones.viewmodel.tiposucursalc.siu.EliminarRequest request) {
        ServiceResponse response = new ServiceResponse();
        request.setTipoSucursalCeliminarpthisTipoSucursalCInstance(tipoSucursalCSrv.getByOIDWithHV(request.getTipoSucursalCeliminarpthisTipoSucursalC()));
        try {
            if("Clas_1437649993728293Pre_1".equals(request.getPreconditionId()))
                checkClas1437649993728293Pre1(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    private void eliminarPreconditions(
            com.keralty.aeconfiguraciones.viewmodel.tiposucursalc.siu.EliminarRequest request) 
            throws ModelException {
        checkClas1437649993728293Pre1(request);
    }

    private void checkClas1437649993728293Pre1 (
            com.keralty.aeconfiguraciones.viewmodel.tiposucursalc.siu.EliminarRequest request) 
            throws ModelException {
        // EXIST( DirSucursalesC ) = false

        TipoSucursalC tipoSucursalCeliminarpthisTipoSucursalC = request.getTipoSucursalCeliminarpthisTipoSucursalCInstance();
        try {
            boolean preconditionHolds = repository.assocOperator000(tipoSucursalCeliminarpthisTipoSucursalC).equals(false);
            if (!preconditionHolds) throw new PreconditionException("No se puede eliminar el tipo de sucursal porque ya existen sucursales de este tipo");
        } catch (SystemException e) {
            LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            throw new SystemException(e.getMessage());
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
            com.keralty.aeconfiguraciones.viewmodel.tiposucursalc.siu.ModificarRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = tipoSucursalCSrv.modificarTxn(request);
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
            com.keralty.aeconfiguraciones.viewmodel.tiposucursalc.siu.ModificarRequest request) throws ModelException {
        request.setTipoSucursalCmodificarpthisTipoSucursalCInstance(tipoSucursalCSrv.getByOIDWithHV(request.getTipoSucursalCmodificarpthisTipoSucursalC()));
        return tipoSucursalCSrv.modificarImplementation(request);
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
            com.keralty.aeconfiguraciones.viewmodel.tiposucursalc.siu.ModificarRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        TipoSucursalC tipoSucursalCmodificarpthisTipoSucursalC = request.getTipoSucursalCmodificarpthisTipoSucursalCInstance();
        String tipoSucursalCmodificarpDescripcion = request.getTipoSucursalCmodificarpDescripcion();

        request.checkArguments();
        TipoSucursalC instance = tipoSucursalCmodificarpthisTipoSucursalC;
        instance.setTipoSucursalCDescripcion(tipoSucursalCmodificarpDescripcion);


        response.setInstance(repository.save(instance));

        return response;
    }

    // Service: InsTiposSucursalC

    /*
     * Entry point for service InsTiposSucursalC
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse insTiposSucursalC(
            com.keralty.aeconfiguraciones.viewmodel.tiposucursalc.siu.InsTiposSucursalCRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = tipoSucursalCSrv.insTiposSucursalCTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service InsTiposSucursalC
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse insTiposSucursalCTxn(
            com.keralty.aeconfiguraciones.viewmodel.tiposucursalc.siu.InsTiposSucursalCRequest request) throws ModelException {
        request.setTipoSucursalCInsTiposSucursalCpthisTipoSucursalCInstance(tipoSucursalCSrv.getByOIDWithHV(request.getTipoSucursalCInsTiposSucursalCpthisTipoSucursalC()));
        request.setTipoSucursalCInsTiposSucursalCpevcDirSucursalCInstance(dirSucursalCSrv.getByOIDWithHV(request.getTipoSucursalCInsTiposSucursalCpevcDirSucursalC()));
        return tipoSucursalCSrv.insTiposSucursalCImplementation(request, true);
    }

    /*
     * Implements service InsTiposSucursalC
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse insTiposSucursalCImplementation(
            com.keralty.aeconfiguraciones.viewmodel.tiposucursalc.siu.InsTiposSucursalCRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        TipoSucursalC tipoSucursalCInsTiposSucursalCpthisTipoSucursalC = request.getTipoSucursalCInsTiposSucursalCpthisTipoSucursalCInstance();
        DirSucursalC tipoSucursalCInsTiposSucursalCpevcDirSucursalC = request.getTipoSucursalCInsTiposSucursalCpevcDirSucursalCInstance();

        request.checkArguments();
        TipoSucursalC instance = tipoSucursalCInsTiposSucursalCpthisTipoSucursalC;

        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.dirsucursalc.siu.InsTiposSucursalCRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.dirsucursalc.siu.InsTiposSucursalCRequest();
            relatedRequest.setDirSucursalCInsTiposSucursalCpthisDirSucursalCInstance(tipoSucursalCInsTiposSucursalCpevcDirSucursalC);
            relatedRequest.setDirSucursalCInsTiposSucursalCpevcTipoSucursalCInstance(tipoSucursalCInsTiposSucursalCpthisTipoSucursalC);
            dirSucursalCSrv.insTiposSucursalCImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));

        return response;
    }

    // Service: DelTiposSucursalC

    /*
     * Entry point for service DelTiposSucursalC
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse delTiposSucursalC(
            com.keralty.aeconfiguraciones.viewmodel.tiposucursalc.siu.DelTiposSucursalCRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = tipoSucursalCSrv.delTiposSucursalCTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service DelTiposSucursalC
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse delTiposSucursalCTxn(
            com.keralty.aeconfiguraciones.viewmodel.tiposucursalc.siu.DelTiposSucursalCRequest request) throws ModelException {
        request.setTipoSucursalCDelTiposSucursalCpthisTipoSucursalCInstance(tipoSucursalCSrv.getByOIDWithHV(request.getTipoSucursalCDelTiposSucursalCpthisTipoSucursalC()));
        request.setTipoSucursalCDelTiposSucursalCpevcDirSucursalCInstance(dirSucursalCSrv.getByOIDWithHV(request.getTipoSucursalCDelTiposSucursalCpevcDirSucursalC()));
        return tipoSucursalCSrv.delTiposSucursalCImplementation(request, true);
    }

    /*
     * Implements service DelTiposSucursalC
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse delTiposSucursalCImplementation(
            com.keralty.aeconfiguraciones.viewmodel.tiposucursalc.siu.DelTiposSucursalCRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        TipoSucursalC tipoSucursalCDelTiposSucursalCpthisTipoSucursalC = request.getTipoSucursalCDelTiposSucursalCpthisTipoSucursalCInstance();
        DirSucursalC tipoSucursalCDelTiposSucursalCpevcDirSucursalC = request.getTipoSucursalCDelTiposSucursalCpevcDirSucursalCInstance();

        request.checkArguments();
        TipoSucursalC instance = tipoSucursalCDelTiposSucursalCpthisTipoSucursalC;

        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.dirsucursalc.siu.DelTiposSucursalCRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.dirsucursalc.siu.DelTiposSucursalCRequest();
            relatedRequest.setDirSucursalCDelTiposSucursalCpthisDirSucursalCInstance(tipoSucursalCDelTiposSucursalCpevcDirSucursalC);
            relatedRequest.setDirSucursalCDelTiposSucursalCpevcTipoSucursalCInstance(tipoSucursalCDelTiposSucursalCpthisTipoSucursalC);
            dirSucursalCSrv.delTiposSucursalCImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));

        return response;
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
        select.getOrderByPart().addItem(Constants.normalizePath(TipoSucursalCConstants.CLASS_NAME, TipoSucursalCConstants.ATTR_FIELD_TIPOSUCURSALCCODIGO, false), true);
    }
    
    @Override
    protected AbstractAppEntity buildRelatedInstance(String relatedInstanceJsonOid, String relatedInstancePathFirstItem, String remainingPath) {
        AbstractAppEntity relatedInstance = null;
        if (relatedInstancePathFirstItem.isEmpty()) {
            relatedInstance = buildTipoSucursalCRelatedInstance(relatedInstanceJsonOid);
        }
        if (TipoSucursalCConstants.ROLE_NAME_DIRSUCURSALESC.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildTipoSucursalCRelatedInstance4DirSucursalC(relatedInstanceJsonOid, remainingPath);
        }
        return relatedInstance;
    }
    
    private AbstractAppEntity buildTipoSucursalCRelatedInstance(String relatedInstanceJsonOid) {
    	ObjectMapper mapper = new ObjectMapper();
    	try {
    		return new TipoSucursalC(mapper.readValue(relatedInstanceJsonOid, TipoSucursalCOid.class));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            throw new SystemException(e.getMessage());
    	}
    }

    private AbstractAppEntity buildTipoSucursalCRelatedInstance4DirSucursalC(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new DirSucursalC(mapper.readValue(relatedInstanceJsonOid, DirSucursalCOid.class)): DirSucursalC.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            throw new SystemException(e.getMessage());
    	}
    }

    @Override
    protected void applyFilterByOid(String jsonOid, SQLSelect sql) {
        try {
            TipoSucursalCOid oid = new TipoSucursalCOid().fromJsonString(jsonOid);
            SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
            comparison1.setOperator("=");
            comparison1.setFirstOperand(new SQLWhereOperandPathAttribute(TipoSucursalCConstants.ATTR_FIELD_TIPOSUCURSALCCODIGO, Constants.Type.INT.getTypeName()));
            comparison1.setSecondOperand(new SQLWhereOperandLiteral(oid.getTipoSucursalCCodigo(), Constants.Type.INT.getTypeName()));
            sql.addComparison(comparison1);
        } catch (Exception e) {
            LoggerFactory.getLogger(TipoSucursalCService.class).error(e.getMessage());
            throw new SystemException(e.getMessage());
        }
    }

}
