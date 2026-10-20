package com.keralty.aeconfiguraciones.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.keralty.aeconfiguraciones.exception.InstanceExistsException;
import com.keralty.aeconfiguraciones.exception.MinCardinalityException;
import com.keralty.aeconfiguraciones.exception.ModelException;
import com.keralty.aeconfiguraciones.exception.PreconditionException;
import com.keralty.aeconfiguraciones.exception.SystemException;
import com.keralty.aeconfiguraciones.global.Constants;
import com.keralty.aeconfiguraciones.global.PaisConstants;
import com.keralty.aeconfiguraciones.persistence.AbstractAppEntity;
import com.keralty.aeconfiguraciones.persistence.Direccion;
import com.keralty.aeconfiguraciones.persistence.DireccionC;
import com.keralty.aeconfiguraciones.persistence.DirSucursalC;
import com.keralty.aeconfiguraciones.persistence.NovedadCT;
import com.keralty.aeconfiguraciones.persistence.NovedadMD;
import com.keralty.aeconfiguraciones.persistence.oid.DireccionCOid;
import com.keralty.aeconfiguraciones.persistence.oid.DireccionOid;
import com.keralty.aeconfiguraciones.persistence.oid.DirSucursalCOid;
import com.keralty.aeconfiguraciones.persistence.oid.NovedadCTOid;
import com.keralty.aeconfiguraciones.persistence.oid.NovedadMDOid;
import com.keralty.aeconfiguraciones.persistence.oid.PaisOid;
import com.keralty.aeconfiguraciones.persistence.Pais;
import com.keralty.aeconfiguraciones.repository.PaisJpaRepository;
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
public class PaisService extends AbstractService {

    PaisJpaRepository repository;

    PaisService paisSrv;

    DireccionService direccionSrv;

    DireccionCService direccionCSrv;

    DirSucursalCService dirSucursalCSrv;

    NovedadCTService novedadCTSrv;

    NovedadMDService novedadMDSrv;

    @Inject
    public PaisService(
            PaisService paisSrv,
            DireccionService direccionSrv,
            DireccionCService direccionCSrv,
            DirSucursalCService dirSucursalCSrv,
            NovedadCTService novedadCTSrv,
            NovedadMDService novedadMDSrv,
            PaisJpaRepository repository) {
        this.paisSrv = paisSrv;
        this.direccionSrv = direccionSrv;
        this.direccionCSrv = direccionCSrv;
        this.dirSucursalCSrv = dirSucursalCSrv;
        this.novedadCTSrv = novedadCTSrv;
        this.novedadMDSrv = novedadMDSrv;
        this.repository = repository;
    }

    public List<Map<String, Object>> solveQuery(QueryRequest request) {
        return solveGenericQuery(request, PaisConstants.CLASS_NAME);
    }

    private List<Pais> solvePaisTypedQuery(QueryRequest request) {
        return repository.solveQuery(processQueryRequest(request, PaisConstants.CLASS_NAME), request.getPageSize(), request.getPageNumber());
    }
    
    private int solvePaisTypedCountQuery(QueryRequest request) {
        return repository.solveCountQuery(processCountQueryRequest(request, PaisConstants.CLASS_NAME));
    }

    /*
     * Returns the population of class Pais
     *
     * @return Population of class Pais
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<Pais> get() {
        List<Pais> results = new java.util.ArrayList<>();
        repository.findAll().forEach(results::add);
        return results;
    }
    
    /*
     * Gets the preloaded list of objects of class Pais
     *
     * @param queryRequest Request object that dictates the search conditions to 
     * retrieve objects
     *
     * @return Preloaded list of objects of class Pais
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<PreloadViewModel> getPreload(QueryRequest queryRequest) {
        List<PreloadViewModel> results = new ArrayList<>();
        List<Pais> instances = get(queryRequest);
        if(PaisConstants.DSICPAIS.equals(queryRequest.getDisplaySetName())) {
            results = instances.stream().map(x -> new PreloadViewModel(x.getOid().toJson(), x.buildICPais())).collect(Collectors.toList());
        }
        return results;
    }
    /*
     * Returns the population of class Pais that matches certain search criteria
     *
     * @param queryRequest Search criteria
     *
     * @return Population of class Pais matching the search criteria
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<Pais> get(QueryRequest queryRequest) {
        return solvePaisTypedQuery(queryRequest);
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
            return new PopulationResponseViewModel(solvePaisTypedQuery(queryRequest).stream()
                    .map(instance -> buildDynamicObject(instance, queryRequest.getDisplaySetItems()))
                    .collect(Collectors.toList()), queryRequest.getPageSize() != 0 ? solvePaisTypedCountQuery(queryRequest) : 0);
        } else {
            return new PreloadResponseViewModel(solvePaisTypedQuery(queryRequest).stream()
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
        return new PopulationResponseViewModel(solvePaisTypedQuery(queryRequest).stream()
                .map(instance -> buildDynamicObject(instance, displaySetItems))
                .collect(Collectors.toList()));
    }

    /*
     * Returns an instance of Pais by ID
     *
     * @param paisCodigo Identification field paisCodigo
     *
     * @return Instance of Pais
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public Pais getById(String paisCodigo) {
        return getByOID(new PaisOid(paisCodigo));
    }
    
    /*
     * Returns an instance of Pais by OID
     *
     * @param oid Object IDentifier
     *
     * @return Instance of Pais
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public Pais getByOID(PaisOid oid) {
        Optional<Pais> instance = getInstanceByOid(oid);
        return instance.isPresent() ? instance.get() : null;
    }
    
    /*
     * Returns an instance of Pais by OID applying horizontal visibility constraints
     *
     * @param oid Object IDentifier
     *
     * @return Instance of Pais
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public Pais getByOIDWithHV(PaisOid oid) {
        Optional<Pais> instance = getInstanceByOid(oid);
    	return instance.isPresent() && instance.get().checkHorizontalVisibility(getAgentInstance()) 
    		 ? instance.get() 
    		 : null;
    }
    
    private Optional<Pais> getInstanceByOid(PaisOid oid) {
        return oid != null && oid.getPaisCodigo() != null
               ? repository.findById(oid.getPaisCodigo())
               : Optional.empty();
    }

    /*
     * Returns an instance of Pais by OID in JSON format
     *
     * @param jsonOid Object IDentifier in JSON format
     *
     * @return Instance of Pais
     */
    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public Pais getByJsonOid(String jsonOid) {
        PaisOid oid = new PaisOid();
        oid.fromJsonString(jsonOid);
        return getByOIDWithHV(oid);
    }

    /*
     * Returns the list of instances of Pais related to a given instance through 
     * a given path
     *
     * @param relatedInstance Related instance
     *
     * @param relatedInstancePath Related instance path
     *
     * @return List of instances of Pais
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<Pais> getByRelated(AbstractAppEntity relatedInstance, String relatedInstancePath) {
        QueryRequest queryRequest = new QueryRequest(new FilterRequest(Constants.FILTER_BY_RELATED,
                Arrays.asList(new FilterVariableRequest(Constants.FILTER_BY_RELATED_FVAR_PATH, relatedInstancePath),
                        new FilterVariableRequest(Constants.FILTER_BY_RELATED_FVAR_OID, relatedInstance.getOid().toJson()))));
        return solvePaisTypedQuery(queryRequest);
    }

    /*
     * Returns the list of instances of Pais related to a given instance through
     * a given path
     *
     * @param relatedInstance Related instance
     *
     * @param relatedInstancePath Related instance path
     *
     * @return List of instances of Pais
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<Pais> getInstancesByRelated(AbstractAppEntity relatedInstance, String relatedInstancePath) {
        return getByRelated(relatedInstance, relatedInstancePath);
    }

    /*
     * Returns a set of displayset items for an instance of Pais
     *
     * @param oid Object IDentifier
     *
     * @param displaySetItems List of comma-separated displayset items
     *
     * @return Set of displayset items for an instance of Pais
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public InstanceResponseViewModel getPaisDynamic(PaisOid oid, String displaySetItems) {
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
    public String getSuppInfo(PaisOid oid, String dsName) {
        Pais instance = getByOIDWithHV(oid);
        return (instance != null ? getSuppInfo(instance, dsName): "");
    }

    private String getSuppInfo(Pais instance, String dsName) {
        if (instance == null || instance.isNull()) return "";
        if (PaisConstants.DSICPAIS.equals(dsName))
            return instance.buildICPais();
        return dsName.isEmpty() ? instance.getOid().toJson() : "";
    }

    public static PreloadViewModel buildPreloadViewModel(Pais instance, String dsName) {
        if (PaisConstants.DSICPAIS.equals(dsName))
            return new PreloadViewModel(instance.getOid().toJson(), instance.buildICPais());
        return new PreloadViewModel(instance.getOid().toJson(), "");
    }


    @Override
    protected void applyQueryFilters(FilterRequest filterRequest, SQLSelect sql) throws SystemException {
        if (PaisConstants.FILTER_NAME_IFPAISCODIGO.equalsIgnoreCase(filterRequest.getName()))
            iFPaisCodigo(filterRequest, sql);
    }

    @Override
    protected void applyServiceSIFilters(FilterRequest filterRequest, SQLSelect sql) {
        if ("SuppInfo_IC_Pais".equals(filterRequest.getName()))
            suppInfo4ICPais(filterRequest.getFilterVariable("searchText").toString(), sql);
    }


    private void iFPaisCodigo(FilterRequest filterRequest, SQLSelect sql) {
        SQLWhereConjunction conjunction = new SQLWhereConjunction();
        String vCodigo = filterRequest.hasFilterVariable(PaisConstants.VAR_NAME_IFPAISCODIGO_VCODIGO) ? (String) filterRequest.getFilterVariable(PaisConstants.VAR_NAME_IFPAISCODIGO_VCODIGO, FilterVariableRequest.STRING) : null;

        // Codigo = vCodigo
        if (vCodigo != null) {
            // '=' Comparison
            SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
            comparison1.setOperator("=");
            
            // Operand 1
            SQLWhereOperandPathAttribute operand112 = new SQLWhereOperandPathAttribute(PaisConstants.ATTR_NAME_PAISCODIGO, Constants.Type.STRING.getTypeName());
            comparison1.setFirstOperand(operand112);
            
            // Operand 2
            comparison1.setSecondOperand(new SQLWhereOperandLiteral(vCodigo, Constants.Type.STRING.getTypeName()));
            conjunction.addOperand(comparison1);

        }        

        sql.addConjunction(conjunction);
    }

    // IIU Methods


    /*
     * Solves the query needed for PIUPais
     *
     * @param queryRequest Query request
     * @return List of viewmodels for Pais using displayset AutoDS that 
     * match the search criteria
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<com.keralty.aeconfiguraciones.viewmodel.pais.ds.AutoDS> piuPIUPais(QueryRequest queryRequest) {
        return solvePaisTypedQuery(queryRequest).stream()
                .map(com.keralty.aeconfiguraciones.viewmodel.pais.ds.AutoDS::new)
                .collect(Collectors.toList());
    }

    /*
     * Solves the COUNT query needed for PIUPais
     *
     * @param queryRequest Query request
     * @returns Count of instances for PIUPais
     */
    public int getCount4PIUPais(QueryRequest queryRequest) {
        return solvePaisTypedQuery(queryRequest).size();
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
            com.keralty.aeconfiguraciones.viewmodel.pais.siu.CrearRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = paisSrv.crearTxn(request);
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
            com.keralty.aeconfiguraciones.viewmodel.pais.siu.CrearRequest request) throws ModelException {
        return paisSrv.crearImplementation(request);
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
            com.keralty.aeconfiguraciones.viewmodel.pais.siu.CrearRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        String paiscrearpatrCodigo = request.getPaiscrearpatrCodigo();
        String paiscrearpatrDescripcion = request.getPaiscrearpatrDescripcion();

        request.checkArguments();
        Pais instance = new Pais();
        instance.setPaisCodigo(paiscrearpatrCodigo);
        instance.setPaisDescripcion(paiscrearpatrDescripcion);
        if (paisSrv.getByOID(instance.getOid()) != null) {
            throw new InstanceExistsException(PaisConstants.CLASS_ALIAS);
        }

        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICPais());

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
            com.keralty.aeconfiguraciones.viewmodel.pais.siu.EliminarRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = paisSrv.eliminarTxn(request);
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
            com.keralty.aeconfiguraciones.viewmodel.pais.siu.EliminarRequest request) throws ModelException {
        request.setPaiseliminarpthisPaisInstance(paisSrv.getByOIDWithHV(request.getPaiseliminarpthisPais()));
        return paisSrv.eliminarImplementation(request);
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
            com.keralty.aeconfiguraciones.viewmodel.pais.siu.EliminarRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Pais paiseliminarpthisPais = request.getPaiseliminarpthisPaisInstance();

        request.checkArguments();

        eliminarPreconditions(request);
        Pais instance = paiseliminarpthisPais;
        if(paiseliminarpthisPais.getDireccionesC().size() > 0) {
            throw new MinCardinalityException(PaisConstants.CLASS_ALIAS, PaisConstants.ROLE_ALIAS_DIRECCIONESC);
        }
        if(paiseliminarpthisPais.getDirecciones().size() > 0) {
            throw new MinCardinalityException(PaisConstants.CLASS_ALIAS, PaisConstants.ROLE_ALIAS_DIRECCIONES);
        }
        if(paiseliminarpthisPais.getDirSucursalC().size() > 0) {
            throw new MinCardinalityException(PaisConstants.CLASS_ALIAS, PaisConstants.ROLE_ALIAS_DIRSUCURSALC);
        }
        if(paiseliminarpthisPais.getNovedadCT().size() > 0) {
            throw new MinCardinalityException(PaisConstants.CLASS_ALIAS, PaisConstants.ROLE_ALIAS_NOVEDADCT);
        }
        if(paiseliminarpthisPais.getNovedadesMD().size() > 0) {
            throw new MinCardinalityException(PaisConstants.CLASS_ALIAS, PaisConstants.ROLE_ALIAS_NOVEDADESMD);
        }


        repository.delete(instance);
        
        response.setInstance(instance);
        response.setInstanceSuppInfo(instance.buildICPais());

        return response;
    }

    /*
     * Checks service eliminar preconditions individually
     *
     * @param request Service request viewmodel
     *
     * @return Service preconditions evaluation result
     */
    public ServiceResponse eliminarCheckPreconditions(com.keralty.aeconfiguraciones.viewmodel.pais.siu.EliminarRequest request) {
        ServiceResponse response = new ServiceResponse();
        request.setPaiseliminarpthisPaisInstance(paisSrv.getByOIDWithHV(request.getPaiseliminarpthisPais()));
        try {
            if("Clas_1432206704640584Pre_1".equals(request.getPreconditionId()))
                checkClas1432206704640584Pre1(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    private void eliminarPreconditions(
            com.keralty.aeconfiguraciones.viewmodel.pais.siu.EliminarRequest request) 
            throws ModelException {
        checkClas1432206704640584Pre1(request);
    }

    private void checkClas1432206704640584Pre1 (
            com.keralty.aeconfiguraciones.viewmodel.pais.siu.EliminarRequest request) 
            throws ModelException {
        // EXIST(Direcciones) = FALSE

        Pais paiseliminarpthisPais = request.getPaiseliminarpthisPaisInstance();
        try {
            boolean preconditionHolds = repository.assocOperator000(paiseliminarpthisPais).equals(false);
            if (!preconditionHolds) throw new PreconditionException("No se puede eliminar el país porque existen direcciones registradas en este país");
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
            com.keralty.aeconfiguraciones.viewmodel.pais.siu.SincronizarRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = paisSrv.sincronizarTxn(request);
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
            com.keralty.aeconfiguraciones.viewmodel.pais.siu.SincronizarRequest request) throws ModelException {
        request.setPaissincronizarpthisPaisInstance(paisSrv.getByOIDWithHV(request.getPaissincronizarpthisPais()));
        return paisSrv.sincronizarImplementation(request);
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
            com.keralty.aeconfiguraciones.viewmodel.pais.siu.SincronizarRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Pais paissincronizarpthisPais = request.getPaissincronizarpthisPaisInstance();
        String paissincronizarpDescripcion = request.getPaissincronizarpDescripcion();

        request.checkArguments();
        Pais instance = paissincronizarpthisPais;
        instance.setPaisDescripcion(paissincronizarpDescripcion);


        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICPais());

        return response;
    }

    // Service: InsPaisDireccion

    /*
     * Entry point for service InsPaisDireccion
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse insPaisDireccion(
            com.keralty.aeconfiguraciones.viewmodel.pais.siu.InsPaisDireccionRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = paisSrv.insPaisDireccionTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service InsPaisDireccion
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse insPaisDireccionTxn(
            com.keralty.aeconfiguraciones.viewmodel.pais.siu.InsPaisDireccionRequest request) throws ModelException {
        request.setPaisInsPaisDireccionpthisPaisInstance(paisSrv.getByOIDWithHV(request.getPaisInsPaisDireccionpthisPais()));
        request.setPaisInsPaisDireccionpevcDireccionInstance(direccionSrv.getByOIDWithHV(request.getPaisInsPaisDireccionpevcDireccion()));
        return paisSrv.insPaisDireccionImplementation(request, true);
    }

    /*
     * Implements service InsPaisDireccion
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse insPaisDireccionImplementation(
            com.keralty.aeconfiguraciones.viewmodel.pais.siu.InsPaisDireccionRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Pais paisInsPaisDireccionpthisPais = request.getPaisInsPaisDireccionpthisPaisInstance();
        Direccion paisInsPaisDireccionpevcDireccion = request.getPaisInsPaisDireccionpevcDireccionInstance();

        request.checkArguments();
        Pais instance = paisInsPaisDireccionpthisPais;

        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.direccion.siu.InsPaisDireccionRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.direccion.siu.InsPaisDireccionRequest();
            relatedRequest.setDireccionInsPaisDireccionpthisDireccionInstance(paisInsPaisDireccionpevcDireccion);
            relatedRequest.setDireccionInsPaisDireccionpevcPaisInstance(paisInsPaisDireccionpthisPais);
            direccionSrv.insPaisDireccionImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICPais());

        return response;
    }

    // Service: DelPaisDireccion

    /*
     * Entry point for service DelPaisDireccion
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse delPaisDireccion(
            com.keralty.aeconfiguraciones.viewmodel.pais.siu.DelPaisDireccionRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = paisSrv.delPaisDireccionTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service DelPaisDireccion
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse delPaisDireccionTxn(
            com.keralty.aeconfiguraciones.viewmodel.pais.siu.DelPaisDireccionRequest request) throws ModelException {
        request.setPaisDelPaisDireccionpthisPaisInstance(paisSrv.getByOIDWithHV(request.getPaisDelPaisDireccionpthisPais()));
        request.setPaisDelPaisDireccionpevcDireccionInstance(direccionSrv.getByOIDWithHV(request.getPaisDelPaisDireccionpevcDireccion()));
        return paisSrv.delPaisDireccionImplementation(request, true);
    }

    /*
     * Implements service DelPaisDireccion
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse delPaisDireccionImplementation(
            com.keralty.aeconfiguraciones.viewmodel.pais.siu.DelPaisDireccionRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Pais paisDelPaisDireccionpthisPais = request.getPaisDelPaisDireccionpthisPaisInstance();
        Direccion paisDelPaisDireccionpevcDireccion = request.getPaisDelPaisDireccionpevcDireccionInstance();

        request.checkArguments();
        Pais instance = paisDelPaisDireccionpthisPais;

        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.direccion.siu.DelPaisDireccionRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.direccion.siu.DelPaisDireccionRequest();
            relatedRequest.setDireccionDelPaisDireccionpthisDireccionInstance(paisDelPaisDireccionpevcDireccion);
            relatedRequest.setDireccionDelPaisDireccionpevcPaisInstance(paisDelPaisDireccionpthisPais);
            direccionSrv.delPaisDireccionImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICPais());

        return response;
    }

    // Service: InsPaisC

    /*
     * Entry point for service InsPaisC
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse insPaisC(
            com.keralty.aeconfiguraciones.viewmodel.pais.siu.InsPaisCRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = paisSrv.insPaisCTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service InsPaisC
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse insPaisCTxn(
            com.keralty.aeconfiguraciones.viewmodel.pais.siu.InsPaisCRequest request) throws ModelException {
        request.setPaisInsPaisCpthisPaisInstance(paisSrv.getByOIDWithHV(request.getPaisInsPaisCpthisPais()));
        request.setPaisInsPaisCpevcDireccionCInstance(direccionCSrv.getByOIDWithHV(request.getPaisInsPaisCpevcDireccionC()));
        return paisSrv.insPaisCImplementation(request, true);
    }

    /*
     * Implements service InsPaisC
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse insPaisCImplementation(
            com.keralty.aeconfiguraciones.viewmodel.pais.siu.InsPaisCRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Pais paisInsPaisCpthisPais = request.getPaisInsPaisCpthisPaisInstance();
        DireccionC paisInsPaisCpevcDireccionC = request.getPaisInsPaisCpevcDireccionCInstance();

        request.checkArguments();
        Pais instance = paisInsPaisCpthisPais;

        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.direccionc.siu.InsPaisCRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.direccionc.siu.InsPaisCRequest();
            relatedRequest.setDireccionCInsPaisCpthisDireccionCInstance(paisInsPaisCpevcDireccionC);
            relatedRequest.setDireccionCInsPaisCpevcPaisInstance(paisInsPaisCpthisPais);
            direccionCSrv.insPaisCImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICPais());

        return response;
    }

    // Service: DelPaisC

    /*
     * Entry point for service DelPaisC
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse delPaisC(
            com.keralty.aeconfiguraciones.viewmodel.pais.siu.DelPaisCRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = paisSrv.delPaisCTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service DelPaisC
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse delPaisCTxn(
            com.keralty.aeconfiguraciones.viewmodel.pais.siu.DelPaisCRequest request) throws ModelException {
        request.setPaisDelPaisCpthisPaisInstance(paisSrv.getByOIDWithHV(request.getPaisDelPaisCpthisPais()));
        request.setPaisDelPaisCpevcDireccionCInstance(direccionCSrv.getByOIDWithHV(request.getPaisDelPaisCpevcDireccionC()));
        return paisSrv.delPaisCImplementation(request, true);
    }

    /*
     * Implements service DelPaisC
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse delPaisCImplementation(
            com.keralty.aeconfiguraciones.viewmodel.pais.siu.DelPaisCRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Pais paisDelPaisCpthisPais = request.getPaisDelPaisCpthisPaisInstance();
        DireccionC paisDelPaisCpevcDireccionC = request.getPaisDelPaisCpevcDireccionCInstance();

        request.checkArguments();
        Pais instance = paisDelPaisCpthisPais;

        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.direccionc.siu.DelPaisCRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.direccionc.siu.DelPaisCRequest();
            relatedRequest.setDireccionCDelPaisCpthisDireccionCInstance(paisDelPaisCpevcDireccionC);
            relatedRequest.setDireccionCDelPaisCpevcPaisInstance(paisDelPaisCpthisPais);
            direccionCSrv.delPaisCImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICPais());

        return response;
    }

    // Service: InsPaisDirSucursalC

    /*
     * Entry point for service InsPaisDirSucursalC
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse insPaisDirSucursalC(
            com.keralty.aeconfiguraciones.viewmodel.pais.siu.InsPaisDirSucursalCRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = paisSrv.insPaisDirSucursalCTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service InsPaisDirSucursalC
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse insPaisDirSucursalCTxn(
            com.keralty.aeconfiguraciones.viewmodel.pais.siu.InsPaisDirSucursalCRequest request) throws ModelException {
        request.setPaisInsPaisDirSucursalCpthisPaisInstance(paisSrv.getByOIDWithHV(request.getPaisInsPaisDirSucursalCpthisPais()));
        request.setPaisInsPaisDirSucursalCpevcDirSucursalCInstance(dirSucursalCSrv.getByOIDWithHV(request.getPaisInsPaisDirSucursalCpevcDirSucursalC()));
        return paisSrv.insPaisDirSucursalCImplementation(request, true);
    }

    /*
     * Implements service InsPaisDirSucursalC
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse insPaisDirSucursalCImplementation(
            com.keralty.aeconfiguraciones.viewmodel.pais.siu.InsPaisDirSucursalCRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Pais paisInsPaisDirSucursalCpthisPais = request.getPaisInsPaisDirSucursalCpthisPaisInstance();
        DirSucursalC paisInsPaisDirSucursalCpevcDirSucursalC = request.getPaisInsPaisDirSucursalCpevcDirSucursalCInstance();

        request.checkArguments();
        Pais instance = paisInsPaisDirSucursalCpthisPais;

        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.dirsucursalc.siu.InsPaisDirSucursalCRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.dirsucursalc.siu.InsPaisDirSucursalCRequest();
            relatedRequest.setDirSucursalCInsPaisDirSucursalCpthisDirSucursalCInstance(paisInsPaisDirSucursalCpevcDirSucursalC);
            relatedRequest.setDirSucursalCInsPaisDirSucursalCpevcPaisInstance(paisInsPaisDirSucursalCpthisPais);
            dirSucursalCSrv.insPaisDirSucursalCImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICPais());

        return response;
    }

    // Service: DelPaisDirSucursalC

    /*
     * Entry point for service DelPaisDirSucursalC
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse delPaisDirSucursalC(
            com.keralty.aeconfiguraciones.viewmodel.pais.siu.DelPaisDirSucursalCRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = paisSrv.delPaisDirSucursalCTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service DelPaisDirSucursalC
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse delPaisDirSucursalCTxn(
            com.keralty.aeconfiguraciones.viewmodel.pais.siu.DelPaisDirSucursalCRequest request) throws ModelException {
        request.setPaisDelPaisDirSucursalCpthisPaisInstance(paisSrv.getByOIDWithHV(request.getPaisDelPaisDirSucursalCpthisPais()));
        request.setPaisDelPaisDirSucursalCpevcDirSucursalCInstance(dirSucursalCSrv.getByOIDWithHV(request.getPaisDelPaisDirSucursalCpevcDirSucursalC()));
        return paisSrv.delPaisDirSucursalCImplementation(request, true);
    }

    /*
     * Implements service DelPaisDirSucursalC
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse delPaisDirSucursalCImplementation(
            com.keralty.aeconfiguraciones.viewmodel.pais.siu.DelPaisDirSucursalCRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Pais paisDelPaisDirSucursalCpthisPais = request.getPaisDelPaisDirSucursalCpthisPaisInstance();
        DirSucursalC paisDelPaisDirSucursalCpevcDirSucursalC = request.getPaisDelPaisDirSucursalCpevcDirSucursalCInstance();

        request.checkArguments();
        Pais instance = paisDelPaisDirSucursalCpthisPais;

        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.dirsucursalc.siu.DelPaisDirSucursalCRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.dirsucursalc.siu.DelPaisDirSucursalCRequest();
            relatedRequest.setDirSucursalCDelPaisDirSucursalCpthisDirSucursalCInstance(paisDelPaisDirSucursalCpevcDirSucursalC);
            relatedRequest.setDirSucursalCDelPaisDirSucursalCpevcPaisInstance(paisDelPaisDirSucursalCpthisPais);
            dirSucursalCSrv.delPaisDirSucursalCImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICPais());

        return response;
    }


    private void suppInfo4ICPais(String searchText, SQLSelect select) {
        try {
            SQLWhereDisjunction disjunction = new SQLWhereDisjunction();

            SQLWhereComparison comparison1 = new SQLWhereComparison(select);
            comparison1.setOperator("LIKE");
            comparison1.setFirstOperand(new SQLWhereOperandPathAttribute(PaisConstants.ATTR_NAME_PAISDESCRIPCION, Constants.Type.STRING.getTypeName()));
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
        applyOCOCPais(queryRequest, select);
        applyDSItemSort(queryRequest, select);
        applyDefaultOrderCriteria(queryRequest, select);
    }

    private void applyDefaultOrderCriteria(QueryRequest queryRequest, SQLSelect select) {
        if (queryRequest.getOrderCriteriaName() != null && !queryRequest.getOrderCriteriaName().isBlank())
            return;
        if (queryRequest.getDsItemSort() != null && !queryRequest.getDsItemSort().isBlank())
            return;
        select.getOrderByPart().addItem(Constants.normalizePath(PaisConstants.CLASS_NAME, PaisConstants.ATTR_FIELD_PAISCODIGO, false), true);
    }


    private void applyOCOCPais(QueryRequest queryRequest, SQLSelect select) {
        if ("OC_Pais".equalsIgnoreCase(queryRequest.getOrderCriteriaName())) {
            select.getOrderByPart().addItem(Constants.normalizePath(PaisConstants.CLASS_NAME, PaisConstants.ATTR_NAME_PAISDESCRIPCION, false), true);
        }
    }
    
    @Override
    protected AbstractAppEntity buildRelatedInstance(String relatedInstanceJsonOid, String relatedInstancePathFirstItem, String remainingPath) {
        AbstractAppEntity relatedInstance = null;
        if (relatedInstancePathFirstItem.isEmpty()) {
            relatedInstance = buildPaisRelatedInstance(relatedInstanceJsonOid);
        }
        if (PaisConstants.ROLE_NAME_DIRECCIONESC.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildPaisRelatedInstance4DireccionC(relatedInstanceJsonOid, remainingPath);
        }
        if (PaisConstants.ROLE_NAME_DIRECCIONES.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildPaisRelatedInstance4Direccion(relatedInstanceJsonOid, remainingPath);
        }
        if (PaisConstants.ROLE_NAME_DIRSUCURSALC.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildPaisRelatedInstance4DirSucursalC(relatedInstanceJsonOid, remainingPath);
        }
        if (PaisConstants.ROLE_NAME_NOVEDADCT.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildPaisRelatedInstance4NovedadCT(relatedInstanceJsonOid, remainingPath);
        }
        if (PaisConstants.ROLE_NAME_NOVEDADESMD.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildPaisRelatedInstance4NovedadMD(relatedInstanceJsonOid, remainingPath);
        }
        return relatedInstance;
    }
    
    private AbstractAppEntity buildPaisRelatedInstance(String relatedInstanceJsonOid) {
    	ObjectMapper mapper = new ObjectMapper();
    	try {
    		return new Pais(mapper.readValue(relatedInstanceJsonOid, PaisOid.class));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    private AbstractAppEntity buildPaisRelatedInstance4DireccionC(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new DireccionC(mapper.readValue(relatedInstanceJsonOid, DireccionCOid.class)): DireccionC.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    private AbstractAppEntity buildPaisRelatedInstance4Direccion(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new Direccion(mapper.readValue(relatedInstanceJsonOid, DireccionOid.class)): Direccion.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    private AbstractAppEntity buildPaisRelatedInstance4DirSucursalC(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new DirSucursalC(mapper.readValue(relatedInstanceJsonOid, DirSucursalCOid.class)): DirSucursalC.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    private AbstractAppEntity buildPaisRelatedInstance4NovedadCT(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new NovedadCT(mapper.readValue(relatedInstanceJsonOid, NovedadCTOid.class)): NovedadCT.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    private AbstractAppEntity buildPaisRelatedInstance4NovedadMD(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new NovedadMD(mapper.readValue(relatedInstanceJsonOid, NovedadMDOid.class)): NovedadMD.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    @Override
    protected void applyFilterByOid(String jsonOid, SQLSelect sql) {
        try {
            PaisOid oid = new PaisOid().fromJsonString(jsonOid);
            SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
            comparison1.setOperator("=");
            comparison1.setFirstOperand(new SQLWhereOperandPathAttribute(PaisConstants.ATTR_FIELD_PAISCODIGO, Constants.Type.STRING.getTypeName()));
            comparison1.setSecondOperand(new SQLWhereOperandLiteral(oid.getPaisCodigo(), Constants.Type.STRING.getTypeName()));
            sql.addComparison(comparison1);
        } catch (Exception e) {
            LoggerFactory.getLogger(PaisService.class).error(e.getMessage());
        }
    }

}
