package com.keralty.aeconfiguraciones.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.keralty.aeconfiguraciones.exception.InstanceExistsException;
import com.keralty.aeconfiguraciones.exception.MinCardinalityException;
import com.keralty.aeconfiguraciones.exception.ModelException;
import com.keralty.aeconfiguraciones.exception.PreconditionException;
import com.keralty.aeconfiguraciones.exception.SystemException;
import com.keralty.aeconfiguraciones.global.Constants;
import com.keralty.aeconfiguraciones.global.EstadoBeneficiarioConstants;
import com.keralty.aeconfiguraciones.persistence.AbstractAppEntity;
import com.keralty.aeconfiguraciones.persistence.BeneficiarioC;
import com.keralty.aeconfiguraciones.persistence.EstadoBeneficiario;
import com.keralty.aeconfiguraciones.persistence.EstadoBeneficiarioAE;
import com.keralty.aeconfiguraciones.persistence.oid.BeneficiarioCOid;
import com.keralty.aeconfiguraciones.persistence.oid.EstadoBeneficiarioAEOid;
import com.keralty.aeconfiguraciones.persistence.oid.EstadoBeneficiarioOid;
import com.keralty.aeconfiguraciones.persistence.oid.UsuarioNovedadOid;
import com.keralty.aeconfiguraciones.persistence.oid.UsuarioOid;
import com.keralty.aeconfiguraciones.persistence.oid.UsuarioRegOid;
import com.keralty.aeconfiguraciones.persistence.Usuario;
import com.keralty.aeconfiguraciones.persistence.UsuarioNovedad;
import com.keralty.aeconfiguraciones.persistence.UsuarioReg;
import com.keralty.aeconfiguraciones.repository.EstadoBeneficiarioJpaRepository;
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
public class EstadoBeneficiarioService extends AbstractService {

    EstadoBeneficiarioJpaRepository repository;

    EstadoBeneficiarioService estadoBeneficiarioSrv;

    EstadoBeneficiarioAEService estadoBeneficiarioAESrv;

    UsuarioService usuarioSrv;

    BeneficiarioCService beneficiarioCSrv;

    UsuarioNovedadService usuarioNovedadSrv;

    UsuarioRegService usuarioRegSrv;

    @Inject
    public EstadoBeneficiarioService(
            EstadoBeneficiarioService estadoBeneficiarioSrv,
            EstadoBeneficiarioAEService estadoBeneficiarioAESrv,
            UsuarioService usuarioSrv,
            BeneficiarioCService beneficiarioCSrv,
            UsuarioNovedadService usuarioNovedadSrv,
            UsuarioRegService usuarioRegSrv,
            EstadoBeneficiarioJpaRepository repository) {
        this.estadoBeneficiarioSrv = estadoBeneficiarioSrv;
        this.estadoBeneficiarioAESrv = estadoBeneficiarioAESrv;
        this.usuarioSrv = usuarioSrv;
        this.beneficiarioCSrv = beneficiarioCSrv;
        this.usuarioNovedadSrv = usuarioNovedadSrv;
        this.usuarioRegSrv = usuarioRegSrv;
        this.repository = repository;
    }

    public List<Map<String, Object>> solveQuery(QueryRequest request) {
        return solveGenericQuery(request, EstadoBeneficiarioConstants.CLASS_NAME);
    }

    private List<EstadoBeneficiario> solveEstadoBeneficiarioTypedQuery(QueryRequest request) {
        return repository.solveQuery(processQueryRequest(request, EstadoBeneficiarioConstants.CLASS_NAME), request.getPageSize(), request.getPageNumber());
    }
    
    private int solveEstadoBeneficiarioTypedCountQuery(QueryRequest request) {
        return repository.solveCountQuery(processCountQueryRequest(request, EstadoBeneficiarioConstants.CLASS_NAME));
    }

    /*
     * Returns the population of class EstadoBeneficiario
     *
     * @return Population of class EstadoBeneficiario
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<EstadoBeneficiario> get() {
        List<EstadoBeneficiario> results = new java.util.ArrayList<>();
        repository.findAll().forEach(results::add);
        return results;
    }
    
    /*
     * Gets the preloaded list of objects of class EstadoBeneficiario
     *
     * @param queryRequest Request object that dictates the search conditions to 
     * retrieve objects
     *
     * @return Preloaded list of objects of class EstadoBeneficiario
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<PreloadViewModel> getPreload(QueryRequest queryRequest) {
        List<PreloadViewModel> results = new ArrayList<>();
        List<EstadoBeneficiario> instances = get(queryRequest);
        if(EstadoBeneficiarioConstants.DSICESTADOBENEFICIARIO.equals(queryRequest.getDisplaySetName())) {
            results = instances.stream().map(x -> new PreloadViewModel(x.getOid().toJson(), x.buildICEstadoBeneficiario())).collect(Collectors.toList());
        }
        return results;
    }
    /*
     * Returns the population of class EstadoBeneficiario that matches certain search criteria
     *
     * @param queryRequest Search criteria
     *
     * @return Population of class EstadoBeneficiario matching the search criteria
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<EstadoBeneficiario> get(QueryRequest queryRequest) {
        return solveEstadoBeneficiarioTypedQuery(queryRequest);
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
            return new PopulationResponseViewModel(solveEstadoBeneficiarioTypedQuery(queryRequest).stream()
                    .map(instance -> buildDynamicObject(instance, queryRequest.getDisplaySetItems()))
                    .collect(Collectors.toList()), queryRequest.getPageSize() != 0 ? solveEstadoBeneficiarioTypedCountQuery(queryRequest) : 0);
        } else {
            return new PreloadResponseViewModel(solveEstadoBeneficiarioTypedQuery(queryRequest).stream()
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
        return new PopulationResponseViewModel(solveEstadoBeneficiarioTypedQuery(queryRequest).stream()
                .map(instance -> buildDynamicObject(instance, displaySetItems))
                .collect(Collectors.toList()));
    }

    /*
     * Returns an instance of EstadoBeneficiario by ID
     *
     * @param estadoBeneficiarioCodigo Identification field estadoBeneficiarioCodigo
     *
     * @return Instance of EstadoBeneficiario
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public EstadoBeneficiario getById(Long estadoBeneficiarioCodigo) {
        return getByOID(new EstadoBeneficiarioOid(estadoBeneficiarioCodigo));
    }
    
    /*
     * Returns an instance of EstadoBeneficiario by OID
     *
     * @param oid Object IDentifier
     *
     * @return Instance of EstadoBeneficiario
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public EstadoBeneficiario getByOID(EstadoBeneficiarioOid oid) {
        Optional<EstadoBeneficiario> instance = getInstanceByOid(oid);
        return instance.isPresent() ? instance.get() : null;
    }
    
    /*
     * Returns an instance of EstadoBeneficiario by OID applying horizontal visibility constraints
     *
     * @param oid Object IDentifier
     *
     * @return Instance of EstadoBeneficiario
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public EstadoBeneficiario getByOIDWithHV(EstadoBeneficiarioOid oid) {
        Optional<EstadoBeneficiario> instance = getInstanceByOid(oid);
    	return instance.isPresent() && instance.get().checkHorizontalVisibility(getAgentInstance()) 
    		 ? instance.get() 
    		 : null;
    }
    
    private Optional<EstadoBeneficiario> getInstanceByOid(EstadoBeneficiarioOid oid) {
        return oid != null && oid.getEstadoBeneficiarioCodigo() != null
               ? repository.findById(oid.getEstadoBeneficiarioCodigo())
               : Optional.empty();
    }

    /*
     * Returns an instance of EstadoBeneficiario by OID in JSON format
     *
     * @param jsonOid Object IDentifier in JSON format
     *
     * @return Instance of EstadoBeneficiario
     */
    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public EstadoBeneficiario getByJsonOid(String jsonOid) {
        EstadoBeneficiarioOid oid = new EstadoBeneficiarioOid();
        oid.fromJsonString(jsonOid);
        return getByOIDWithHV(oid);
    }

    /*
     * Returns the list of instances of EstadoBeneficiario related to a given instance through 
     * a given path
     *
     * @param relatedInstance Related instance
     *
     * @param relatedInstancePath Related instance path
     *
     * @return List of instances of EstadoBeneficiario
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<EstadoBeneficiario> getByRelated(AbstractAppEntity relatedInstance, String relatedInstancePath) {
        QueryRequest queryRequest = new QueryRequest(new FilterRequest(Constants.FILTER_BY_RELATED,
                Arrays.asList(new FilterVariableRequest(Constants.FILTER_BY_RELATED_FVAR_PATH, relatedInstancePath),
                        new FilterVariableRequest(Constants.FILTER_BY_RELATED_FVAR_OID, relatedInstance.getOid().toJson()))));
        return solveEstadoBeneficiarioTypedQuery(queryRequest);
    }

    /*
     * Returns the list of instances of EstadoBeneficiario related to a given instance through
     * a given path
     *
     * @param relatedInstance Related instance
     *
     * @param relatedInstancePath Related instance path
     *
     * @return List of instances of EstadoBeneficiario
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<EstadoBeneficiario> getInstancesByRelated(AbstractAppEntity relatedInstance, String relatedInstancePath) {
        return getByRelated(relatedInstance, relatedInstancePath);
    }

    /*
     * Returns a set of displayset items for an instance of EstadoBeneficiario
     *
     * @param oid Object IDentifier
     *
     * @param displaySetItems List of comma-separated displayset items
     *
     * @return Set of displayset items for an instance of EstadoBeneficiario
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public InstanceResponseViewModel getEstadoBeneficiarioDynamic(EstadoBeneficiarioOid oid, String displaySetItems) {
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
    public String getSuppInfo(EstadoBeneficiarioOid oid, String dsName) {
        EstadoBeneficiario instance = getByOIDWithHV(oid);
        return (instance != null ? getSuppInfo(instance, dsName): "");
    }

    private String getSuppInfo(EstadoBeneficiario instance, String dsName) {
        if (instance == null || instance.isNull()) return "";
        if (EstadoBeneficiarioConstants.DSICESTADOBENEFICIARIO.equals(dsName))
            return instance.buildICEstadoBeneficiario();
        return dsName.isEmpty() ? instance.getOid().toJson() : "";
    }

    public static PreloadViewModel buildPreloadViewModel(EstadoBeneficiario instance, String dsName) {
        if (EstadoBeneficiarioConstants.DSICESTADOBENEFICIARIO.equals(dsName))
            return new PreloadViewModel(instance.getOid().toJson(), instance.buildICEstadoBeneficiario());
        return new PreloadViewModel(instance.getOid().toJson(), "");
    }


    @Override
    protected void applyQueryFilters(FilterRequest filterRequest, SQLSelect sql) throws SystemException {
        if (EstadoBeneficiarioConstants.FILTER_NAME_IFESTADOBENEFICIARIOCODIGO.equalsIgnoreCase(filterRequest.getName()))
            iFEstadoBeneficiarioCodigo(filterRequest, sql);
        if (EstadoBeneficiarioConstants.FILTER_NAME_ICESTADOBENEFICIARIOPORNOMBRE.equalsIgnoreCase(filterRequest.getName()))
            iCEstadoBeneficiarioPorNombre(filterRequest, sql);
    }

    @Override
    protected void applyServiceSIFilters(FilterRequest filterRequest, SQLSelect sql) {
        if ("SuppInfo_IC_EstadoBeneficiario".equals(filterRequest.getName()))
            suppInfo4ICEstadoBeneficiario(filterRequest.getFilterVariable("searchText").toString(), sql);
    }


    private void iFEstadoBeneficiarioCodigo(FilterRequest filterRequest, SQLSelect sql) {
        SQLWhereConjunction conjunction = new SQLWhereConjunction();
        Long vCodigo = filterRequest.hasFilterVariable(EstadoBeneficiarioConstants.VAR_NAME_IFESTADOBENEFICIARIOCODIGO_VCODIGO) ? (Long) filterRequest.getFilterVariable(EstadoBeneficiarioConstants.VAR_NAME_IFESTADOBENEFICIARIOCODIGO_VCODIGO, FilterVariableRequest.INT) : null;

        // Codigo = vCodigo
        if (vCodigo != null) {
            // '=' Comparison
            SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
            comparison1.setOperator("=");
            
            // Operand 1
            SQLWhereOperandPathAttribute operand112 = new SQLWhereOperandPathAttribute(EstadoBeneficiarioConstants.ATTR_NAME_ESTADOBENEFICIARIOCODIGO, Constants.Type.INT.getTypeName());
            comparison1.setFirstOperand(operand112);
            
            // Operand 2
            comparison1.setSecondOperand(new SQLWhereOperandLiteral(vCodigo, Constants.Type.INT.getTypeName()));
            conjunction.addOperand(comparison1);

        }        

        sql.addConjunction(conjunction);
    }

    private void iCEstadoBeneficiarioPorNombre(FilterRequest filterRequest, SQLSelect sql) {
        SQLWhereConjunction conjunction = new SQLWhereConjunction();
        String vNombre = filterRequest.hasFilterVariable(EstadoBeneficiarioConstants.VAR_NAME_ICESTADOBENEFICIARIOPORNOMBRE_VNOMBRE) ? (String) filterRequest.getFilterVariable(EstadoBeneficiarioConstants.VAR_NAME_ICESTADOBENEFICIARIOPORNOMBRE_VNOMBRE, FilterVariableRequest.STRING) : null;

        // Descripcion = vNombre
        if (vNombre != null) {
            // '=' Comparison
            SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
            comparison1.setOperator("=");
            
            // Operand 1
            SQLWhereOperandPathAttribute operand112 = new SQLWhereOperandPathAttribute(EstadoBeneficiarioConstants.ATTR_NAME_ESTADOBENEFICIARIODESCRIPCION, Constants.Type.STRING.getTypeName());
            comparison1.setFirstOperand(operand112);
            
            // Operand 2
            comparison1.setSecondOperand(new SQLWhereOperandLiteral(vNombre, Constants.Type.STRING.getTypeName()));
            conjunction.addOperand(comparison1);

        }        

        sql.addConjunction(conjunction);
    }

    // IIU Methods


    /*
     * Solves the query needed for PIUEstadoBeneficiario
     *
     * @param queryRequest Query request
     * @return List of viewmodels for EstadoBeneficiario using displayset AutoDS that 
     * match the search criteria
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<com.keralty.aeconfiguraciones.viewmodel.estadobeneficiario.ds.AutoDS> piuPIUEstadoBeneficiario(QueryRequest queryRequest) {
        return solveEstadoBeneficiarioTypedQuery(queryRequest).stream()
                .map(com.keralty.aeconfiguraciones.viewmodel.estadobeneficiario.ds.AutoDS::new)
                .collect(Collectors.toList());
    }

    /*
     * Solves the COUNT query needed for PIUEstadoBeneficiario
     *
     * @param queryRequest Query request
     * @returns Count of instances for PIUEstadoBeneficiario
     */
    public int getCount4PIUEstadoBeneficiario(QueryRequest queryRequest) {
        return solveEstadoBeneficiarioTypedQuery(queryRequest).size();
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
            com.keralty.aeconfiguraciones.viewmodel.estadobeneficiario.siu.CrearRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = estadoBeneficiarioSrv.crearTxn(request);
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
            com.keralty.aeconfiguraciones.viewmodel.estadobeneficiario.siu.CrearRequest request) throws ModelException {
        return estadoBeneficiarioSrv.crearImplementation(request);
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
            com.keralty.aeconfiguraciones.viewmodel.estadobeneficiario.siu.CrearRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Long estadoBeneficiariocrearpatrCodigo = request.getEstadoBeneficiariocrearpatrCodigo();
        String estadoBeneficiariocrearpatrDescripcion = request.getEstadoBeneficiariocrearpatrDescripcion();

        request.checkArguments();
        EstadoBeneficiario instance = new EstadoBeneficiario();
        instance.setEstadoBeneficiarioEsActivo(Boolean.FALSE);
        instance.setEstadoBeneficiarioConfirmado(Boolean.FALSE);
        instance.setEstadoBeneficiarioEsError(Boolean.FALSE);
        instance.setEstadoBeneficiarioCodigo(estadoBeneficiariocrearpatrCodigo);
        instance.setEstadoBeneficiarioDescripcion(estadoBeneficiariocrearpatrDescripcion);
        if (estadoBeneficiarioSrv.getByOID(instance.getOid()) != null) {
            throw new InstanceExistsException(EstadoBeneficiarioConstants.CLASS_ALIAS);
        }

        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICEstadoBeneficiario());

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
            com.keralty.aeconfiguraciones.viewmodel.estadobeneficiario.siu.EliminarRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = estadoBeneficiarioSrv.eliminarTxn(request);
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
            com.keralty.aeconfiguraciones.viewmodel.estadobeneficiario.siu.EliminarRequest request) throws ModelException {
        request.setEstadoBeneficiarioeliminarpthisEstadoBeneficiarioInstance(estadoBeneficiarioSrv.getByOIDWithHV(request.getEstadoBeneficiarioeliminarpthisEstadoBeneficiario()));
        return estadoBeneficiarioSrv.eliminarImplementation(request);
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
            com.keralty.aeconfiguraciones.viewmodel.estadobeneficiario.siu.EliminarRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        EstadoBeneficiario estadoBeneficiarioeliminarpthisEstadoBeneficiario = request.getEstadoBeneficiarioeliminarpthisEstadoBeneficiarioInstance();

        request.checkArguments();

        eliminarPreconditions(request);
        EstadoBeneficiario instance = estadoBeneficiarioeliminarpthisEstadoBeneficiario;
        if(estadoBeneficiarioeliminarpthisEstadoBeneficiario.getEstadoBeneficiarioAE().size() > 0) {
            throw new MinCardinalityException(EstadoBeneficiarioConstants.CLASS_ALIAS, EstadoBeneficiarioConstants.ROLE_ALIAS_ESTADOBENEFICIARIOAE);
        }
        if(estadoBeneficiarioeliminarpthisEstadoBeneficiario.getUsuarioS().size() > 0) {
            throw new MinCardinalityException(EstadoBeneficiarioConstants.CLASS_ALIAS, EstadoBeneficiarioConstants.ROLE_ALIAS_USUARIOS);
        }
        if(estadoBeneficiarioeliminarpthisEstadoBeneficiario.getBeneficiariosC().size() > 0) {
            throw new MinCardinalityException(EstadoBeneficiarioConstants.CLASS_ALIAS, EstadoBeneficiarioConstants.ROLE_ALIAS_BENEFICIARIOSC);
        }
        if(estadoBeneficiarioeliminarpthisEstadoBeneficiario.getUsuariosReg().size() > 0) {
            throw new MinCardinalityException(EstadoBeneficiarioConstants.CLASS_ALIAS, EstadoBeneficiarioConstants.ROLE_ALIAS_USUARIOSREG);
        }
        if(estadoBeneficiarioeliminarpthisEstadoBeneficiario.getUsuariosNovedad().size() > 0) {
            throw new MinCardinalityException(EstadoBeneficiarioConstants.CLASS_ALIAS, EstadoBeneficiarioConstants.ROLE_ALIAS_USUARIOSNOVEDAD);
        }


        repository.delete(instance);
        
        response.setInstance(instance);
        response.setInstanceSuppInfo(instance.buildICEstadoBeneficiario());

        return response;
    }

    /*
     * Checks service eliminar preconditions individually
     *
     * @param request Service request viewmodel
     *
     * @return Service preconditions evaluation result
     */
    public ServiceResponse eliminarCheckPreconditions(com.keralty.aeconfiguraciones.viewmodel.estadobeneficiario.siu.EliminarRequest request) {
        ServiceResponse response = new ServiceResponse();
        request.setEstadoBeneficiarioeliminarpthisEstadoBeneficiarioInstance(estadoBeneficiarioSrv.getByOIDWithHV(request.getEstadoBeneficiarioeliminarpthisEstadoBeneficiario()));
        try {
            if("Clas_1431710597120636Pre_1".equals(request.getPreconditionId()))
                checkClas1431710597120636Pre1(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    private void eliminarPreconditions(
            com.keralty.aeconfiguraciones.viewmodel.estadobeneficiario.siu.EliminarRequest request) 
            throws ModelException {
        checkClas1431710597120636Pre1(request);
    }

    private void checkClas1431710597120636Pre1 (
            com.keralty.aeconfiguraciones.viewmodel.estadobeneficiario.siu.EliminarRequest request) 
            throws ModelException {
        // EXIST( EstadoBeneficiarioAE ) = false

        EstadoBeneficiario estadoBeneficiarioeliminarpthisEstadoBeneficiario = request.getEstadoBeneficiarioeliminarpthisEstadoBeneficiarioInstance();
        try {
            boolean preconditionHolds = repository.assocOperator000(estadoBeneficiarioeliminarpthisEstadoBeneficiario).equals(false);
            if (!preconditionHolds) throw new PreconditionException("No se puede borrar el estado porque ya tiene establecido un mapeo con un estado de beneficiario en Afiliación Electrónica");
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
            com.keralty.aeconfiguraciones.viewmodel.estadobeneficiario.siu.SincronizarRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = estadoBeneficiarioSrv.sincronizarTxn(request);
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
            com.keralty.aeconfiguraciones.viewmodel.estadobeneficiario.siu.SincronizarRequest request) throws ModelException {
        request.setEstadoBeneficiariosincronizarpthisEstadoBeneficiarioInstance(estadoBeneficiarioSrv.getByOIDWithHV(request.getEstadoBeneficiariosincronizarpthisEstadoBeneficiario()));
        return estadoBeneficiarioSrv.sincronizarImplementation(request);
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
            com.keralty.aeconfiguraciones.viewmodel.estadobeneficiario.siu.SincronizarRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        EstadoBeneficiario estadoBeneficiariosincronizarpthisEstadoBeneficiario = request.getEstadoBeneficiariosincronizarpthisEstadoBeneficiarioInstance();
        String estadoBeneficiariosincronizarpDescripcion = request.getEstadoBeneficiariosincronizarpDescripcion();

        request.checkArguments();
        EstadoBeneficiario instance = estadoBeneficiariosincronizarpthisEstadoBeneficiario;
        instance.setEstadoBeneficiarioDescripcion(estadoBeneficiariosincronizarpDescripcion);


        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICEstadoBeneficiario());

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
            com.keralty.aeconfiguraciones.viewmodel.estadobeneficiario.siu.CambEstadoBeneficiarioCORERequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = estadoBeneficiarioSrv.cambEstadoBeneficiarioCORETxn(request);
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
            com.keralty.aeconfiguraciones.viewmodel.estadobeneficiario.siu.CambEstadoBeneficiarioCORERequest request) throws ModelException {
        request.setEstadoBeneficiarioCambEstadoBeneficiarioCOREpthisEstadoBeneficiarioInstance(estadoBeneficiarioSrv.getByOIDWithHV(request.getEstadoBeneficiarioCambEstadoBeneficiarioCOREpthisEstadoBeneficiario()));
        request.setEstadoBeneficiarioCambEstadoBeneficiarioCOREpevcEstadoBeneficiarioAEInstance(estadoBeneficiarioAESrv.getByOIDWithHV(request.getEstadoBeneficiarioCambEstadoBeneficiarioCOREpevcEstadoBeneficiarioAE()));
        return estadoBeneficiarioSrv.cambEstadoBeneficiarioCOREImplementation(request, true);
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
            com.keralty.aeconfiguraciones.viewmodel.estadobeneficiario.siu.CambEstadoBeneficiarioCORERequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        EstadoBeneficiario estadoBeneficiarioCambEstadoBeneficiarioCOREpthisEstadoBeneficiario = request.getEstadoBeneficiarioCambEstadoBeneficiarioCOREpthisEstadoBeneficiarioInstance();
        EstadoBeneficiarioAE estadoBeneficiarioCambEstadoBeneficiarioCOREpevcEstadoBeneficiarioAE = request.getEstadoBeneficiarioCambEstadoBeneficiarioCOREpevcEstadoBeneficiarioAEInstance();

        request.checkArguments();
        EstadoBeneficiario instance = estadoBeneficiarioCambEstadoBeneficiarioCOREpthisEstadoBeneficiario;

        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.estadobeneficiarioae.siu.CambEstadoBeneficiarioCORERequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.estadobeneficiarioae.siu.CambEstadoBeneficiarioCORERequest();
            relatedRequest.setEstadoBeneficiarioAECambEstadoBeneficiarioCOREpthisEstadoBeneficiarioAEInstance(estadoBeneficiarioCambEstadoBeneficiarioCOREpevcEstadoBeneficiarioAE);
            relatedRequest.setEstadoBeneficiarioAECambEstadoBeneficiarioCOREpevcEstadoBeneficiarioInstance(estadoBeneficiarioCambEstadoBeneficiarioCOREpthisEstadoBeneficiario);
            estadoBeneficiarioAESrv.cambEstadoBeneficiarioCOREImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICEstadoBeneficiario());

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
            com.keralty.aeconfiguraciones.viewmodel.estadobeneficiario.siu.ModificarRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = estadoBeneficiarioSrv.modificarTxn(request);
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
            com.keralty.aeconfiguraciones.viewmodel.estadobeneficiario.siu.ModificarRequest request) throws ModelException {
        request.setEstadoBeneficiariomodificarpthisEstadoBeneficiarioInstance(estadoBeneficiarioSrv.getByOIDWithHV(request.getEstadoBeneficiariomodificarpthisEstadoBeneficiario()));
        return estadoBeneficiarioSrv.modificarImplementation(request);
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
            com.keralty.aeconfiguraciones.viewmodel.estadobeneficiario.siu.ModificarRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        EstadoBeneficiario estadoBeneficiariomodificarpthisEstadoBeneficiario = request.getEstadoBeneficiariomodificarpthisEstadoBeneficiarioInstance();
        Boolean estadoBeneficiariomodificarpEsActivo = request.getEstadoBeneficiariomodificarpEsActivo();
        Boolean estadoBeneficiariomodificarpConfirmado = request.getEstadoBeneficiariomodificarpConfirmado();
        Boolean estadoBeneficiariomodificarpEsError = request.getEstadoBeneficiariomodificarpEsError();

        request.checkArguments();
        EstadoBeneficiario instance = estadoBeneficiariomodificarpthisEstadoBeneficiario;
        instance.setEstadoBeneficiarioEsActivo(estadoBeneficiariomodificarpEsActivo);
        instance.setEstadoBeneficiarioConfirmado(estadoBeneficiariomodificarpConfirmado);
        instance.setEstadoBeneficiarioEsError(estadoBeneficiariomodificarpEsError);


        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICEstadoBeneficiario());

        return response;
    }

    // Service: InsEstadoBeneficiarioCORE

    /*
     * Entry point for service InsEstadoBeneficiarioCORE
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse insEstadoBeneficiarioCORE(
            com.keralty.aeconfiguraciones.viewmodel.estadobeneficiario.siu.InsEstadoBeneficiarioCORERequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = estadoBeneficiarioSrv.insEstadoBeneficiarioCORETxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service InsEstadoBeneficiarioCORE
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse insEstadoBeneficiarioCORETxn(
            com.keralty.aeconfiguraciones.viewmodel.estadobeneficiario.siu.InsEstadoBeneficiarioCORERequest request) throws ModelException {
        request.setEstadoBeneficiarioInsEstadoBeneficiarioCOREpthisEstadoBeneficiarioInstance(estadoBeneficiarioSrv.getByOIDWithHV(request.getEstadoBeneficiarioInsEstadoBeneficiarioCOREpthisEstadoBeneficiario()));
        request.setEstadoBeneficiarioInsEstadoBeneficiarioCOREpevcUsuarioInstance(usuarioSrv.getByOIDWithHV(request.getEstadoBeneficiarioInsEstadoBeneficiarioCOREpevcUsuario()));
        return estadoBeneficiarioSrv.insEstadoBeneficiarioCOREImplementation(request, true);
    }

    /*
     * Implements service InsEstadoBeneficiarioCORE
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse insEstadoBeneficiarioCOREImplementation(
            com.keralty.aeconfiguraciones.viewmodel.estadobeneficiario.siu.InsEstadoBeneficiarioCORERequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        EstadoBeneficiario estadoBeneficiarioInsEstadoBeneficiarioCOREpthisEstadoBeneficiario = request.getEstadoBeneficiarioInsEstadoBeneficiarioCOREpthisEstadoBeneficiarioInstance();
        Usuario estadoBeneficiarioInsEstadoBeneficiarioCOREpevcUsuario = request.getEstadoBeneficiarioInsEstadoBeneficiarioCOREpevcUsuarioInstance();

        request.checkArguments();
        EstadoBeneficiario instance = estadoBeneficiarioInsEstadoBeneficiarioCOREpthisEstadoBeneficiario;

        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.usuario.siu.InsEstadoBeneficiarioCORERequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.usuario.siu.InsEstadoBeneficiarioCORERequest();
            relatedRequest.setUsuarioInsEstadoBeneficiarioCOREpthisUsuarioInstance(estadoBeneficiarioInsEstadoBeneficiarioCOREpevcUsuario);
            relatedRequest.setUsuarioInsEstadoBeneficiarioCOREpevcEstadoBeneficiarioInstance(estadoBeneficiarioInsEstadoBeneficiarioCOREpthisEstadoBeneficiario);
            usuarioSrv.insEstadoBeneficiarioCOREImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICEstadoBeneficiario());

        return response;
    }

    // Service: DelEstadoBeneficiarioCORE

    /*
     * Entry point for service DelEstadoBeneficiarioCORE
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse delEstadoBeneficiarioCORE(
            com.keralty.aeconfiguraciones.viewmodel.estadobeneficiario.siu.DelEstadoBeneficiarioCORERequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = estadoBeneficiarioSrv.delEstadoBeneficiarioCORETxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service DelEstadoBeneficiarioCORE
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse delEstadoBeneficiarioCORETxn(
            com.keralty.aeconfiguraciones.viewmodel.estadobeneficiario.siu.DelEstadoBeneficiarioCORERequest request) throws ModelException {
        request.setEstadoBeneficiarioDelEstadoBeneficiarioCOREpthisEstadoBeneficiarioInstance(estadoBeneficiarioSrv.getByOIDWithHV(request.getEstadoBeneficiarioDelEstadoBeneficiarioCOREpthisEstadoBeneficiario()));
        request.setEstadoBeneficiarioDelEstadoBeneficiarioCOREpevcUsuarioInstance(usuarioSrv.getByOIDWithHV(request.getEstadoBeneficiarioDelEstadoBeneficiarioCOREpevcUsuario()));
        return estadoBeneficiarioSrv.delEstadoBeneficiarioCOREImplementation(request, true);
    }

    /*
     * Implements service DelEstadoBeneficiarioCORE
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse delEstadoBeneficiarioCOREImplementation(
            com.keralty.aeconfiguraciones.viewmodel.estadobeneficiario.siu.DelEstadoBeneficiarioCORERequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        EstadoBeneficiario estadoBeneficiarioDelEstadoBeneficiarioCOREpthisEstadoBeneficiario = request.getEstadoBeneficiarioDelEstadoBeneficiarioCOREpthisEstadoBeneficiarioInstance();
        Usuario estadoBeneficiarioDelEstadoBeneficiarioCOREpevcUsuario = request.getEstadoBeneficiarioDelEstadoBeneficiarioCOREpevcUsuarioInstance();

        request.checkArguments();
        EstadoBeneficiario instance = estadoBeneficiarioDelEstadoBeneficiarioCOREpthisEstadoBeneficiario;

        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.usuario.siu.DelEstadoBeneficiarioCORERequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.usuario.siu.DelEstadoBeneficiarioCORERequest();
            relatedRequest.setUsuarioDelEstadoBeneficiarioCOREpthisUsuarioInstance(estadoBeneficiarioDelEstadoBeneficiarioCOREpevcUsuario);
            relatedRequest.setUsuarioDelEstadoBeneficiarioCOREpevcEstadoBeneficiarioInstance(estadoBeneficiarioDelEstadoBeneficiarioCOREpthisEstadoBeneficiario);
            usuarioSrv.delEstadoBeneficiarioCOREImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICEstadoBeneficiario());

        return response;
    }

    // Service: InsEstadoBeneficiarioC

    /*
     * Entry point for service InsEstadoBeneficiarioC
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse insEstadoBeneficiarioC(
            com.keralty.aeconfiguraciones.viewmodel.estadobeneficiario.siu.InsEstadoBeneficiarioCRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = estadoBeneficiarioSrv.insEstadoBeneficiarioCTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service InsEstadoBeneficiarioC
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse insEstadoBeneficiarioCTxn(
            com.keralty.aeconfiguraciones.viewmodel.estadobeneficiario.siu.InsEstadoBeneficiarioCRequest request) throws ModelException {
        request.setEstadoBeneficiarioInsEstadoBeneficiarioCpthisEstadoBeneficiarioInstance(estadoBeneficiarioSrv.getByOIDWithHV(request.getEstadoBeneficiarioInsEstadoBeneficiarioCpthisEstadoBeneficiario()));
        request.setEstadoBeneficiarioInsEstadoBeneficiarioCpevcBeneficiarioCInstance(beneficiarioCSrv.getByOIDWithHV(request.getEstadoBeneficiarioInsEstadoBeneficiarioCpevcBeneficiarioC()));
        return estadoBeneficiarioSrv.insEstadoBeneficiarioCImplementation(request, true);
    }

    /*
     * Implements service InsEstadoBeneficiarioC
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse insEstadoBeneficiarioCImplementation(
            com.keralty.aeconfiguraciones.viewmodel.estadobeneficiario.siu.InsEstadoBeneficiarioCRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        EstadoBeneficiario estadoBeneficiarioInsEstadoBeneficiarioCpthisEstadoBeneficiario = request.getEstadoBeneficiarioInsEstadoBeneficiarioCpthisEstadoBeneficiarioInstance();
        BeneficiarioC estadoBeneficiarioInsEstadoBeneficiarioCpevcBeneficiarioC = request.getEstadoBeneficiarioInsEstadoBeneficiarioCpevcBeneficiarioCInstance();

        request.checkArguments();
        EstadoBeneficiario instance = estadoBeneficiarioInsEstadoBeneficiarioCpthisEstadoBeneficiario;

        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.beneficiarioc.siu.InsEstadoBeneficiarioCRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.beneficiarioc.siu.InsEstadoBeneficiarioCRequest();
            relatedRequest.setBeneficiarioCInsEstadoBeneficiarioCpthisBeneficiarioCInstance(estadoBeneficiarioInsEstadoBeneficiarioCpevcBeneficiarioC);
            relatedRequest.setBeneficiarioCInsEstadoBeneficiarioCpevcEstadoBeneficiarioInstance(estadoBeneficiarioInsEstadoBeneficiarioCpthisEstadoBeneficiario);
            beneficiarioCSrv.insEstadoBeneficiarioCImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICEstadoBeneficiario());

        return response;
    }

    // Service: DelEstadoBeneficiarioC

    /*
     * Entry point for service DelEstadoBeneficiarioC
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse delEstadoBeneficiarioC(
            com.keralty.aeconfiguraciones.viewmodel.estadobeneficiario.siu.DelEstadoBeneficiarioCRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = estadoBeneficiarioSrv.delEstadoBeneficiarioCTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service DelEstadoBeneficiarioC
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse delEstadoBeneficiarioCTxn(
            com.keralty.aeconfiguraciones.viewmodel.estadobeneficiario.siu.DelEstadoBeneficiarioCRequest request) throws ModelException {
        request.setEstadoBeneficiarioDelEstadoBeneficiarioCpthisEstadoBeneficiarioInstance(estadoBeneficiarioSrv.getByOIDWithHV(request.getEstadoBeneficiarioDelEstadoBeneficiarioCpthisEstadoBeneficiario()));
        request.setEstadoBeneficiarioDelEstadoBeneficiarioCpevcBeneficiarioCInstance(beneficiarioCSrv.getByOIDWithHV(request.getEstadoBeneficiarioDelEstadoBeneficiarioCpevcBeneficiarioC()));
        return estadoBeneficiarioSrv.delEstadoBeneficiarioCImplementation(request, true);
    }

    /*
     * Implements service DelEstadoBeneficiarioC
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse delEstadoBeneficiarioCImplementation(
            com.keralty.aeconfiguraciones.viewmodel.estadobeneficiario.siu.DelEstadoBeneficiarioCRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        EstadoBeneficiario estadoBeneficiarioDelEstadoBeneficiarioCpthisEstadoBeneficiario = request.getEstadoBeneficiarioDelEstadoBeneficiarioCpthisEstadoBeneficiarioInstance();
        BeneficiarioC estadoBeneficiarioDelEstadoBeneficiarioCpevcBeneficiarioC = request.getEstadoBeneficiarioDelEstadoBeneficiarioCpevcBeneficiarioCInstance();

        request.checkArguments();
        EstadoBeneficiario instance = estadoBeneficiarioDelEstadoBeneficiarioCpthisEstadoBeneficiario;

        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.beneficiarioc.siu.DelEstadoBeneficiarioCRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.beneficiarioc.siu.DelEstadoBeneficiarioCRequest();
            relatedRequest.setBeneficiarioCDelEstadoBeneficiarioCpthisBeneficiarioCInstance(estadoBeneficiarioDelEstadoBeneficiarioCpevcBeneficiarioC);
            relatedRequest.setBeneficiarioCDelEstadoBeneficiarioCpevcEstadoBeneficiarioInstance(estadoBeneficiarioDelEstadoBeneficiarioCpthisEstadoBeneficiario);
            beneficiarioCSrv.delEstadoBeneficiarioCImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICEstadoBeneficiario());

        return response;
    }

    // Service: InsEstadoBeneficiario

    /*
     * Entry point for service InsEstadoBeneficiario
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse insEstadoBeneficiario(
            com.keralty.aeconfiguraciones.viewmodel.estadobeneficiario.siu.InsEstadoBeneficiarioRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = estadoBeneficiarioSrv.insEstadoBeneficiarioTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service InsEstadoBeneficiario
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse insEstadoBeneficiarioTxn(
            com.keralty.aeconfiguraciones.viewmodel.estadobeneficiario.siu.InsEstadoBeneficiarioRequest request) throws ModelException {
        request.setEstadoBeneficiarioInsEstadoBeneficiariopthisEstadoBeneficiarioInstance(estadoBeneficiarioSrv.getByOIDWithHV(request.getEstadoBeneficiarioInsEstadoBeneficiariopthisEstadoBeneficiario()));
        request.setEstadoBeneficiarioInsEstadoBeneficiariopevcUsuarioNovedadInstance(usuarioNovedadSrv.getByOIDWithHV(request.getEstadoBeneficiarioInsEstadoBeneficiariopevcUsuarioNovedad()));
        return estadoBeneficiarioSrv.insEstadoBeneficiarioImplementation(request, true);
    }

    /*
     * Implements service InsEstadoBeneficiario
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse insEstadoBeneficiarioImplementation(
            com.keralty.aeconfiguraciones.viewmodel.estadobeneficiario.siu.InsEstadoBeneficiarioRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        EstadoBeneficiario estadoBeneficiarioInsEstadoBeneficiariopthisEstadoBeneficiario = request.getEstadoBeneficiarioInsEstadoBeneficiariopthisEstadoBeneficiarioInstance();
        UsuarioNovedad estadoBeneficiarioInsEstadoBeneficiariopevcUsuarioNovedad = request.getEstadoBeneficiarioInsEstadoBeneficiariopevcUsuarioNovedadInstance();

        request.checkArguments();
        EstadoBeneficiario instance = estadoBeneficiarioInsEstadoBeneficiariopthisEstadoBeneficiario;

        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.usuarionovedad.siu.InsEstadoBeneficiarioRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.usuarionovedad.siu.InsEstadoBeneficiarioRequest();
            relatedRequest.setUsuarioNovedadInsEstadoBeneficiariopthisUsuarioNovedadInstance(estadoBeneficiarioInsEstadoBeneficiariopevcUsuarioNovedad);
            relatedRequest.setUsuarioNovedadInsEstadoBeneficiariopevcEstadoBeneficiarioInstance(estadoBeneficiarioInsEstadoBeneficiariopthisEstadoBeneficiario);
            usuarioNovedadSrv.insEstadoBeneficiarioImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICEstadoBeneficiario());

        return response;
    }

    // Service: DelEstadoBeneficiario

    /*
     * Entry point for service DelEstadoBeneficiario
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse delEstadoBeneficiario(
            com.keralty.aeconfiguraciones.viewmodel.estadobeneficiario.siu.DelEstadoBeneficiarioRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = estadoBeneficiarioSrv.delEstadoBeneficiarioTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service DelEstadoBeneficiario
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse delEstadoBeneficiarioTxn(
            com.keralty.aeconfiguraciones.viewmodel.estadobeneficiario.siu.DelEstadoBeneficiarioRequest request) throws ModelException {
        request.setEstadoBeneficiarioDelEstadoBeneficiariopthisEstadoBeneficiarioInstance(estadoBeneficiarioSrv.getByOIDWithHV(request.getEstadoBeneficiarioDelEstadoBeneficiariopthisEstadoBeneficiario()));
        request.setEstadoBeneficiarioDelEstadoBeneficiariopevcUsuarioNovedadInstance(usuarioNovedadSrv.getByOIDWithHV(request.getEstadoBeneficiarioDelEstadoBeneficiariopevcUsuarioNovedad()));
        return estadoBeneficiarioSrv.delEstadoBeneficiarioImplementation(request, true);
    }

    /*
     * Implements service DelEstadoBeneficiario
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse delEstadoBeneficiarioImplementation(
            com.keralty.aeconfiguraciones.viewmodel.estadobeneficiario.siu.DelEstadoBeneficiarioRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        EstadoBeneficiario estadoBeneficiarioDelEstadoBeneficiariopthisEstadoBeneficiario = request.getEstadoBeneficiarioDelEstadoBeneficiariopthisEstadoBeneficiarioInstance();
        UsuarioNovedad estadoBeneficiarioDelEstadoBeneficiariopevcUsuarioNovedad = request.getEstadoBeneficiarioDelEstadoBeneficiariopevcUsuarioNovedadInstance();

        request.checkArguments();
        EstadoBeneficiario instance = estadoBeneficiarioDelEstadoBeneficiariopthisEstadoBeneficiario;

        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.usuarionovedad.siu.DelEstadoBeneficiarioRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.usuarionovedad.siu.DelEstadoBeneficiarioRequest();
            relatedRequest.setUsuarioNovedadDelEstadoBeneficiariopthisUsuarioNovedadInstance(estadoBeneficiarioDelEstadoBeneficiariopevcUsuarioNovedad);
            relatedRequest.setUsuarioNovedadDelEstadoBeneficiariopevcEstadoBeneficiarioInstance(estadoBeneficiarioDelEstadoBeneficiariopthisEstadoBeneficiario);
            usuarioNovedadSrv.delEstadoBeneficiarioImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICEstadoBeneficiario());

        return response;
    }


    private void suppInfo4ICEstadoBeneficiario(String searchText, SQLSelect select) {
        try {
            SQLWhereDisjunction disjunction = new SQLWhereDisjunction();

            SQLWhereComparison comparison1 = new SQLWhereComparison(select);
            comparison1.setOperator("LIKE");
            comparison1.setFirstOperand(new SQLWhereOperandPathAttribute(EstadoBeneficiarioConstants.ATTR_NAME_ESTADOBENEFICIARIODESCRIPCION, Constants.Type.STRING.getTypeName()));
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
        applyOCOCEstadoBeneficiario(queryRequest, select);
        applyDSItemSort(queryRequest, select);
        applyDefaultOrderCriteria(queryRequest, select);
    }

    private void applyDefaultOrderCriteria(QueryRequest queryRequest, SQLSelect select) {
        if (queryRequest.getOrderCriteriaName() != null && !queryRequest.getOrderCriteriaName().isBlank())
            return;
        if (queryRequest.getDsItemSort() != null && !queryRequest.getDsItemSort().isBlank())
            return;
        select.getOrderByPart().addItem(Constants.normalizePath(EstadoBeneficiarioConstants.CLASS_NAME, EstadoBeneficiarioConstants.ATTR_FIELD_ESTADOBENEFICIARIOCODIGO, false), true);
    }


    private void applyOCOCEstadoBeneficiario(QueryRequest queryRequest, SQLSelect select) {
        if ("OC_EstadoBeneficiario".equalsIgnoreCase(queryRequest.getOrderCriteriaName())) {
            select.getOrderByPart().addItem(Constants.normalizePath(EstadoBeneficiarioConstants.CLASS_NAME, EstadoBeneficiarioConstants.ATTR_NAME_ESTADOBENEFICIARIODESCRIPCION, false), true);
        }
    }
    
    @Override
    protected AbstractAppEntity buildRelatedInstance(String relatedInstanceJsonOid, String relatedInstancePathFirstItem, String remainingPath) {
        AbstractAppEntity relatedInstance = null;
        if (relatedInstancePathFirstItem.isEmpty()) {
            relatedInstance = buildEstadoBeneficiarioRelatedInstance(relatedInstanceJsonOid);
        }
        if (EstadoBeneficiarioConstants.ROLE_NAME_ESTADOBENEFICIARIOAE.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildEstadoBeneficiarioRelatedInstance4EstadoBeneficiarioAE(relatedInstanceJsonOid, remainingPath);
        }
        if (EstadoBeneficiarioConstants.ROLE_NAME_USUARIOS.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildEstadoBeneficiarioRelatedInstance4Usuario(relatedInstanceJsonOid, remainingPath);
        }
        if (EstadoBeneficiarioConstants.ROLE_NAME_BENEFICIARIOSC.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildEstadoBeneficiarioRelatedInstance4BeneficiarioC(relatedInstanceJsonOid, remainingPath);
        }
        if (EstadoBeneficiarioConstants.ROLE_NAME_USUARIOSREG.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildEstadoBeneficiarioRelatedInstance4UsuarioReg(relatedInstanceJsonOid, remainingPath);
        }
        if (EstadoBeneficiarioConstants.ROLE_NAME_USUARIOSNOVEDAD.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildEstadoBeneficiarioRelatedInstance4UsuarioNovedad(relatedInstanceJsonOid, remainingPath);
        }
        return relatedInstance;
    }
    
    private AbstractAppEntity buildEstadoBeneficiarioRelatedInstance(String relatedInstanceJsonOid) {
    	ObjectMapper mapper = new ObjectMapper();
    	try {
    		return new EstadoBeneficiario(mapper.readValue(relatedInstanceJsonOid, EstadoBeneficiarioOid.class));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    private AbstractAppEntity buildEstadoBeneficiarioRelatedInstance4EstadoBeneficiarioAE(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new EstadoBeneficiarioAE(mapper.readValue(relatedInstanceJsonOid, EstadoBeneficiarioAEOid.class)): EstadoBeneficiarioAE.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    private AbstractAppEntity buildEstadoBeneficiarioRelatedInstance4Usuario(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new Usuario(mapper.readValue(relatedInstanceJsonOid, UsuarioOid.class)): Usuario.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    private AbstractAppEntity buildEstadoBeneficiarioRelatedInstance4BeneficiarioC(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new BeneficiarioC(mapper.readValue(relatedInstanceJsonOid, BeneficiarioCOid.class)): BeneficiarioC.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    private AbstractAppEntity buildEstadoBeneficiarioRelatedInstance4UsuarioReg(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new UsuarioReg(mapper.readValue(relatedInstanceJsonOid, UsuarioRegOid.class)): UsuarioReg.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    private AbstractAppEntity buildEstadoBeneficiarioRelatedInstance4UsuarioNovedad(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new UsuarioNovedad(mapper.readValue(relatedInstanceJsonOid, UsuarioNovedadOid.class)): UsuarioNovedad.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    @Override
    protected void applyFilterByOid(String jsonOid, SQLSelect sql) {
        try {
            EstadoBeneficiarioOid oid = new EstadoBeneficiarioOid().fromJsonString(jsonOid);
            SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
            comparison1.setOperator("=");
            comparison1.setFirstOperand(new SQLWhereOperandPathAttribute(EstadoBeneficiarioConstants.ATTR_FIELD_ESTADOBENEFICIARIOCODIGO, Constants.Type.INT.getTypeName()));
            comparison1.setSecondOperand(new SQLWhereOperandLiteral(oid.getEstadoBeneficiarioCodigo(), Constants.Type.INT.getTypeName()));
            sql.addComparison(comparison1);
        } catch (Exception e) {
            LoggerFactory.getLogger(EstadoBeneficiarioService.class).error(e.getMessage());
        }
    }

}
