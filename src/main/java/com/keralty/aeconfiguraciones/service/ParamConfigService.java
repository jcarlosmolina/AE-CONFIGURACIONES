package com.keralty.aeconfiguraciones.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.keralty.aeconfiguraciones.exception.InstanceExistsException;
import com.keralty.aeconfiguraciones.exception.MinCardinalityException;
import com.keralty.aeconfiguraciones.exception.ModelException;
import com.keralty.aeconfiguraciones.exception.PreconditionException;
import com.keralty.aeconfiguraciones.exception.SystemException;
import com.keralty.aeconfiguraciones.global.Constants;
import com.keralty.aeconfiguraciones.global.FuncionarioConstants;
import com.keralty.aeconfiguraciones.global.ParamConfigConstants;
import com.keralty.aeconfiguraciones.global.STDFunctions;
import com.keralty.aeconfiguraciones.global.UserFunctions;
import com.keralty.aeconfiguraciones.persistence.AbstractAppEntity;
import com.keralty.aeconfiguraciones.persistence.Funcionario;
import com.keralty.aeconfiguraciones.persistence.LogParam;
import com.keralty.aeconfiguraciones.persistence.oid.LogParamOid;
import com.keralty.aeconfiguraciones.persistence.oid.ParamConfigOid;
import com.keralty.aeconfiguraciones.persistence.ParamConfig;
import com.keralty.aeconfiguraciones.repository.ParamConfigJpaRepository;
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
public class ParamConfigService extends AbstractService {

    ParamConfigJpaRepository repository;

    ParamConfigService paramConfigSrv;

    LogParamService logParamSrv;

    ValorService valorSrv;

    UserFunctions userFunctionsSrv;

    @Inject
    public ParamConfigService(
            ParamConfigService paramConfigSrv,
            LogParamService logParamSrv,
            ValorService valorSrv,
            UserFunctions userFunctionsSrv,
            ParamConfigJpaRepository repository) {
        this.paramConfigSrv = paramConfigSrv;
        this.logParamSrv = logParamSrv;
        this.valorSrv = valorSrv;
        this.userFunctionsSrv = userFunctionsSrv;
        this.repository = repository;
    }

    public List<Map<String, Object>> solveQuery(QueryRequest request) {
        return solveGenericQuery(request, ParamConfigConstants.CLASS_NAME);
    }

    private List<ParamConfig> solveParamConfigTypedQuery(QueryRequest request) {
        return repository.solveQuery(processQueryRequest(request, ParamConfigConstants.CLASS_NAME), request.getPageSize(), request.getPageNumber());
    }
    
    private int solveParamConfigTypedCountQuery(QueryRequest request) {
        return repository.solveCountQuery(processCountQueryRequest(request, ParamConfigConstants.CLASS_NAME));
    }

    /*
     * Returns the population of class ParamConfig
     *
     * @return Population of class ParamConfig
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<ParamConfig> get() {
        List<ParamConfig> results = new java.util.ArrayList<>();
        repository.findAll().forEach(results::add);
        return results;
    }
    
    /*
     * Gets the preloaded list of objects of class ParamConfig
     *
     * @param queryRequest Request object that dictates the search conditions to 
     * retrieve objects
     *
     * @return Preloaded list of objects of class ParamConfig
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<PreloadViewModel> getPreload(QueryRequest queryRequest) {
        List<PreloadViewModel> results = new ArrayList<>();
        List<ParamConfig> instances = get(queryRequest);
        if(ParamConfigConstants.DSICPARAMCONFIG.equals(queryRequest.getDisplaySetName())) {
            results = instances.stream().map(x -> new PreloadViewModel(x.getOid().toJson(), x.buildICParamConfig())).collect(Collectors.toList());
        }
        if(ParamConfigConstants.DSDSPARAMCONFIGPIU.equals(queryRequest.getDisplaySetName())) {
            results = instances.stream().map(x -> new PreloadViewModel(x.getOid().toJson(), x.buildDSParamConfigPIU())).collect(Collectors.toList());
        }
        if(ParamConfigConstants.DSDSPARAMCONFIGIIUMAESTRO.equals(queryRequest.getDisplaySetName())) {
            results = instances.stream().map(x -> new PreloadViewModel(x.getOid().toJson(), x.buildDSParamConfigIIUMaestro())).collect(Collectors.toList());
        }
        return results;
    }
    /*
     * Returns the population of class ParamConfig that matches certain search criteria
     *
     * @param queryRequest Search criteria
     *
     * @return Population of class ParamConfig matching the search criteria
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<ParamConfig> get(QueryRequest queryRequest) {
        return solveParamConfigTypedQuery(queryRequest);
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
            return new PopulationResponseViewModel(solveParamConfigTypedQuery(queryRequest).stream()
                    .map(instance -> buildDynamicObject(instance, queryRequest.getDisplaySetItems()))
                    .collect(Collectors.toList()), queryRequest.getPageSize() != 0 ? solveParamConfigTypedCountQuery(queryRequest) : 0);
        } else {
            return new PreloadResponseViewModel(solveParamConfigTypedQuery(queryRequest).stream()
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
        return new PopulationResponseViewModel(solveParamConfigTypedQuery(queryRequest).stream()
                .map(instance -> buildDynamicObject(instance, displaySetItems))
                .collect(Collectors.toList()));
    }

    /*
     * Returns an instance of ParamConfig by ID
     *
     * @param paramConfigCodigo Identification field paramConfigCodigo
     *
     * @return Instance of ParamConfig
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ParamConfig getById(String paramConfigCodigo) {
        return getByOID(new ParamConfigOid(paramConfigCodigo));
    }
    
    /*
     * Returns an instance of ParamConfig by OID
     *
     * @param oid Object IDentifier
     *
     * @return Instance of ParamConfig
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ParamConfig getByOID(ParamConfigOid oid) {
        Optional<ParamConfig> instance = getInstanceByOid(oid);
        return instance.isPresent() ? instance.get() : null;
    }
    
    /*
     * Returns an instance of ParamConfig by OID applying horizontal visibility constraints
     *
     * @param oid Object IDentifier
     *
     * @return Instance of ParamConfig
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public ParamConfig getByOIDWithHV(ParamConfigOid oid) {
        Optional<ParamConfig> instance = getInstanceByOid(oid);
    	return instance.isPresent() && instance.get().checkHorizontalVisibility(getAgentInstance()) 
    		 ? instance.get() 
    		 : null;
    }
    
    private Optional<ParamConfig> getInstanceByOid(ParamConfigOid oid) {
        return oid != null && oid.getParamConfigCodigo() != null
               ? repository.findById(oid.getParamConfigCodigo())
               : Optional.empty();
    }

    /*
     * Returns an instance of ParamConfig by OID in JSON format
     *
     * @param jsonOid Object IDentifier in JSON format
     *
     * @return Instance of ParamConfig
     */
    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public ParamConfig getByJsonOid(String jsonOid) {
        ParamConfigOid oid = new ParamConfigOid();
        oid.fromJsonString(jsonOid);
        return getByOIDWithHV(oid);
    }

    /*
     * Returns the list of instances of ParamConfig related to a given instance through 
     * a given path
     *
     * @param relatedInstance Related instance
     *
     * @param relatedInstancePath Related instance path
     *
     * @return List of instances of ParamConfig
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<ParamConfig> getByRelated(AbstractAppEntity relatedInstance, String relatedInstancePath) {
        QueryRequest queryRequest = new QueryRequest(new FilterRequest(Constants.FILTER_BY_RELATED,
                Arrays.asList(new FilterVariableRequest(Constants.FILTER_BY_RELATED_FVAR_PATH, relatedInstancePath),
                        new FilterVariableRequest(Constants.FILTER_BY_RELATED_FVAR_OID, relatedInstance.getOid().toJson()))));
        return solveParamConfigTypedQuery(queryRequest);
    }

    /*
     * Returns the list of instances of ParamConfig related to a given instance through
     * a given path
     *
     * @param relatedInstance Related instance
     *
     * @param relatedInstancePath Related instance path
     *
     * @return List of instances of ParamConfig
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<ParamConfig> getInstancesByRelated(AbstractAppEntity relatedInstance, String relatedInstancePath) {
        return getByRelated(relatedInstance, relatedInstancePath);
    }

    /*
     * Returns a set of displayset items for an instance of ParamConfig
     *
     * @param oid Object IDentifier
     *
     * @param displaySetItems List of comma-separated displayset items
     *
     * @return Set of displayset items for an instance of ParamConfig
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public InstanceResponseViewModel getParamConfigDynamic(ParamConfigOid oid, String displaySetItems) {
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
    public String getSuppInfo(ParamConfigOid oid, String dsName) {
        ParamConfig instance = getByOIDWithHV(oid);
        return (instance != null ? getSuppInfo(instance, dsName): "");
    }

    private String getSuppInfo(ParamConfig instance, String dsName) {
        if (instance == null || instance.isNull()) return "";
        if (ParamConfigConstants.DSICPARAMCONFIG.equals(dsName))
            return instance.buildICParamConfig();
        return dsName.isEmpty() ? instance.getOid().toJson() : "";
    }

    public static PreloadViewModel buildPreloadViewModel(ParamConfig instance, String dsName) {
        if (ParamConfigConstants.DSICPARAMCONFIG.equals(dsName))
            return new PreloadViewModel(instance.getOid().toJson(), instance.buildICParamConfig());
        if (ParamConfigConstants.DSDSPARAMCONFIGPIU.equals(dsName))
            return new PreloadViewModel(instance.getOid().toJson(), instance.buildDSParamConfigPIU());
        if (ParamConfigConstants.DSDSPARAMCONFIGIIUMAESTRO.equals(dsName))
            return new PreloadViewModel(instance.getOid().toJson(), instance.buildDSParamConfigIIUMaestro());
        return new PreloadViewModel(instance.getOid().toJson(), "");
    }


    @Override
    protected void applyQueryFilters(FilterRequest filterRequest, SQLSelect sql) throws SystemException {
        if (ParamConfigConstants.FILTER_NAME_IFPARAMCONFIGPPORCODIGO.equalsIgnoreCase(filterRequest.getName()))
            iFParamConfigpPorCodigo(filterRequest, sql);
    }

    @Override
    protected void applyServiceSIFilters(FilterRequest filterRequest, SQLSelect sql) {
        if ("SuppInfo_IC_ParamConfig".equals(filterRequest.getName()))
            suppInfo4ICParamConfig(filterRequest.getFilterVariable("searchText").toString(), sql);
    }


    private void iFParamConfigpPorCodigo(FilterRequest filterRequest, SQLSelect sql) {
        SQLWhereConjunction conjunction = new SQLWhereConjunction();
        String vCodigo = filterRequest.hasFilterVariable(ParamConfigConstants.VAR_NAME_IFPARAMCONFIGPPORCODIGO_VCODIGO) ? (String) filterRequest.getFilterVariable(ParamConfigConstants.VAR_NAME_IFPARAMCONFIGPPORCODIGO_VCODIGO, FilterVariableRequest.STRING) : null;

        // Codigo = vCodigo
        if (vCodigo != null) {
            // '=' Comparison
            SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
            comparison1.setOperator("=");
            
            // Operand 1
            SQLWhereOperandPathAttribute operand112 = new SQLWhereOperandPathAttribute(ParamConfigConstants.ATTR_NAME_PARAMCONFIGCODIGO, Constants.Type.STRING.getTypeName());
            comparison1.setFirstOperand(operand112);
            
            // Operand 2
            comparison1.setSecondOperand(new SQLWhereOperandLiteral(vCodigo, Constants.Type.STRING.getTypeName()));
            conjunction.addOperand(comparison1);

        }        

        sql.addConjunction(conjunction);
    }

    // IIU Methods

    /*
     * Returns a viewmodel for class ParamConfig with displayset DSParamConfigIIUMaestro for the instance with a given OID
     * @param oid Class oid
     * @return Viewmodel for class class ParamConfig with displayset DSParamConfigIIUMaestro
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public com.keralty.aeconfiguraciones.viewmodel.paramconfig.ds.DSParamConfigIIUMaestro iIUParamConfigMaestro(ParamConfigOid oid) {
        return new com.keralty.aeconfiguraciones.viewmodel.paramconfig.ds.DSParamConfigIIUMaestro(getByOIDWithHV(oid));
    }


    /*
     * Solves the query needed for PIUParamConfig
     *
     * @param queryRequest Query request
     * @return List of viewmodels for ParamConfig using displayset DSParamConfigPIU that 
     * match the search criteria
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<com.keralty.aeconfiguraciones.viewmodel.paramconfig.ds.DSParamConfigPIU> piuPIUParamConfig(QueryRequest queryRequest) {
        return solveParamConfigTypedQuery(queryRequest).stream()
                .map(com.keralty.aeconfiguraciones.viewmodel.paramconfig.ds.DSParamConfigPIU::new)
                .collect(Collectors.toList());
    }

    /*
     * Solves the COUNT query needed for PIUParamConfig
     *
     * @param queryRequest Query request
     * @returns Count of instances for PIUParamConfig
     */
    public int getCount4PIUParamConfig(QueryRequest queryRequest) {
        return solveParamConfigTypedQuery(queryRequest).size();
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
            com.keralty.aeconfiguraciones.viewmodel.paramconfig.siu.CrearRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = paramConfigSrv.crearTxn(request);
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
            com.keralty.aeconfiguraciones.viewmodel.paramconfig.siu.CrearRequest request) throws ModelException {
        return paramConfigSrv.crearImplementation(request);
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
            com.keralty.aeconfiguraciones.viewmodel.paramconfig.siu.CrearRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        String paramConfigcrearpatrCodigo = request.getParamConfigcrearpatrCodigo();
        String paramConfigcrearpatrDescripcion = request.getParamConfigcrearpatrDescripcion();
        String paramConfigcrearpatrValor = request.getParamConfigcrearpatrValor();
        Boolean paramConfigcrearpatrEsNumerico = request.getParamConfigcrearpatrEsNumerico();

        request.checkArguments();
        ParamConfig instance = new ParamConfig();
        instance.setParamConfigCodigo(paramConfigcrearpatrCodigo);
        instance.setParamConfigDescripcion(paramConfigcrearpatrDescripcion);
        instance.setParamConfigValor(paramConfigcrearpatrValor);
        instance.setParamConfigEsNumerico(paramConfigcrearpatrEsNumerico);
        if (paramConfigSrv.getByOID(instance.getOid()) != null) {
            throw new InstanceExistsException(ParamConfigConstants.CLASS_ALIAS);
        }

        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICParamConfig());

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
            com.keralty.aeconfiguraciones.viewmodel.paramconfig.siu.EliminarRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = paramConfigSrv.eliminarTxn(request);
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
            com.keralty.aeconfiguraciones.viewmodel.paramconfig.siu.EliminarRequest request) throws ModelException {
        request.setParamConfigeliminarpthisParamConfigInstance(paramConfigSrv.getByOIDWithHV(request.getParamConfigeliminarpthisParamConfig()));
        return paramConfigSrv.eliminarImplementation(request);
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
            com.keralty.aeconfiguraciones.viewmodel.paramconfig.siu.EliminarRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        ParamConfig paramConfigeliminarpthisParamConfig = request.getParamConfigeliminarpthisParamConfigInstance();

        request.checkArguments();
        ParamConfig instance = paramConfigeliminarpthisParamConfig;
        if(paramConfigeliminarpthisParamConfig.getLogs().size() > 0) {
            throw new MinCardinalityException(ParamConfigConstants.CLASS_ALIAS, ParamConfigConstants.ROLE_ALIAS_LOGS);
        }


        repository.delete(instance);
        
        response.setInstance(instance);
        response.setInstanceSuppInfo(instance.buildICParamConfig());

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
            com.keralty.aeconfiguraciones.viewmodel.paramconfig.siu.ModificarRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = paramConfigSrv.modificarTxn(request);
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
            com.keralty.aeconfiguraciones.viewmodel.paramconfig.siu.ModificarRequest request) throws ModelException {
        request.setParamConfigmodificarpthisParamConfigInstance(paramConfigSrv.getByOIDWithHV(request.getParamConfigmodificarpthisParamConfig()));
        return paramConfigSrv.modificarImplementation(request);
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
            com.keralty.aeconfiguraciones.viewmodel.paramconfig.siu.ModificarRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        ParamConfig paramConfigmodificarpthisParamConfig = request.getParamConfigmodificarpthisParamConfigInstance();
        String paramConfigmodificarpValor = request.getParamConfigmodificarpValor();

        request.checkArguments();
        ParamConfig instance = paramConfigmodificarpthisParamConfig;
        instance.setParamConfigValor(paramConfigmodificarpValor);


        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICParamConfig());

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
            com.keralty.aeconfiguraciones.viewmodel.paramconfig.siu.TMODIFICARRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = paramConfigSrv.tMODIFICARTxn(request);
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
            com.keralty.aeconfiguraciones.viewmodel.paramconfig.siu.TMODIFICARRequest request) throws ModelException {
        request.setParamConfigTMODIFICARpthisParamConfigInstance(paramConfigSrv.getByOIDWithHV(request.getParamConfigTMODIFICARpthisParamConfig()));
        return paramConfigSrv.tMODIFICARImplementation(request);
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
            com.keralty.aeconfiguraciones.viewmodel.paramconfig.siu.TMODIFICARRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        String paramConfigTMODIFICARptpValor = request.getParamConfigTMODIFICARptpValor();
        ParamConfig paramConfigTMODIFICARpthisParamConfig = request.getParamConfigTMODIFICARpthisParamConfigInstance();

        request.checkArguments();

        tMODIFICARPreconditions(request);
        ParamConfig instance = paramConfigTMODIFICARpthisParamConfig;

        com.keralty.aeconfiguraciones.viewmodel.logparam.siu.CrearRequest step1Request = new com.keralty.aeconfiguraciones.viewmodel.logparam.siu.CrearRequest();
        step1Request.setLogParamcrearpagrParamConfigInstance(paramConfigTMODIFICARpthisParamConfig);
        step1Request.setLogParamcrearpatrFecha(STDFunctions.systemDateFun());
        step1Request.setLogParamcrearpatrUsuario(((Funcionario) getAgentInstance(FuncionarioConstants.CLASS_NAME)).getFuncionarioUsuario());
        step1Request.setLogParamcrearpatrValorAnterior(paramConfigTMODIFICARpthisParamConfig.getParamConfigValor());
        step1Request.setLogParamcrearpatrNuevoValor(paramConfigTMODIFICARptpValor);
        step1Request.setLogParamcrearpatrNombreUsuario(((Funcionario) getAgentInstance(FuncionarioConstants.CLASS_NAME)).getFuncionariodrvNombreCompleto());
        logParamSrv.crearImplementation(step1Request);

        com.keralty.aeconfiguraciones.viewmodel.paramconfig.siu.ModificarRequest step2Request = new com.keralty.aeconfiguraciones.viewmodel.paramconfig.siu.ModificarRequest();
        step2Request.setParamConfigmodificarpthisParamConfigInstance(paramConfigTMODIFICARpthisParamConfig);
        step2Request.setParamConfigmodificarpValor(paramConfigTMODIFICARptpValor);
        paramConfigSrv.modificarImplementation(step2Request);

        response.setInstance(getByOID(instance.getOid()));
        response.setInstanceSuppInfo(instance.buildICParamConfig());

        return response;
    }

    /*
     * Checks service TMODIFICAR preconditions individually
     *
     * @param request Service request viewmodel
     *
     * @return Service preconditions evaluation result
     */
    public ServiceResponse tMODIFICARCheckPreconditions(com.keralty.aeconfiguraciones.viewmodel.paramconfig.siu.TMODIFICARRequest request) {
        ServiceResponse response = new ServiceResponse();
        request.setParamConfigTMODIFICARpthisParamConfigInstance(paramConfigSrv.getByOIDWithHV(request.getParamConfigTMODIFICARpthisParamConfig()));
        try {
            if("Clas_1431630118912680Pre_1".equals(request.getPreconditionId()))
                checkClas1431630118912680Pre1(request);
            if("Clas_1431630118912680Pre_2".equals(request.getPreconditionId()))
                checkClas1431630118912680Pre2(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    private void tMODIFICARPreconditions(
            com.keralty.aeconfiguraciones.viewmodel.paramconfig.siu.TMODIFICARRequest request) 
            throws ModelException {
        checkClas1431630118912680Pre1(request);
        checkClas1431630118912680Pre2(request);
    }

    private void checkClas1431630118912680Pre1 (
            com.keralty.aeconfiguraciones.viewmodel.paramconfig.siu.TMODIFICARRequest request) 
            throws ModelException {
        // EsNumerico = false OR fuSoloNumeros(ptpValor) = true

        String paramConfigTMODIFICARptpValor = request.getParamConfigTMODIFICARptpValor();
        ParamConfig paramConfigTMODIFICARpthisParamConfig = request.getParamConfigTMODIFICARpthisParamConfigInstance();
        boolean preconditionHolds = paramConfigTMODIFICARpthisParamConfig.getParamConfigEsNumerico().equals(false) || userFunctionsSrv.fuSoloNumerosFun(paramConfigTMODIFICARptpValor).equals(true);
        if (!preconditionHolds) throw new PreconditionException("El parámetro de configuración debe tener un valor numérico");
    }

    private void checkClas1431630118912680Pre2 (
            com.keralty.aeconfiguraciones.viewmodel.paramconfig.siu.TMODIFICARRequest request) 
            throws ModelException {
        // Codigo <> "TAMANYOMAXADJUNTOS" OR fugetValorParametroConf( "TAMANYOMAXADJUNTOSSIS" ) = NULL OR StrToInt( ptpValor ) <= StrToInt( fugetValorParametroConf( "TAMANYOMAXADJUNTOSSIS" ) )

        String paramConfigTMODIFICARptpValor = request.getParamConfigTMODIFICARptpValor();
        ParamConfig paramConfigTMODIFICARpthisParamConfig = request.getParamConfigTMODIFICARpthisParamConfigInstance();
        boolean preconditionHolds = !(paramConfigTMODIFICARpthisParamConfig.getParamConfigCodigo()).equals("TAMANYOMAX_ADJUNTOS") || userFunctionsSrv.fugetValorParametroConfFun("TAMANYOMAX_ADJUNTOS_SIS") == null || STDFunctions.strToIntFun(paramConfigTMODIFICARptpValor) <= STDFunctions.strToIntFun(userFunctionsSrv.fugetValorParametroConfFun("TAMANYOMAX_ADJUNTOS_SIS"));
        if (!preconditionHolds) throw new PreconditionException("El valor indicado supera el máximo permitido");
    }


    private void suppInfo4ICParamConfig(String searchText, SQLSelect select) {
        try {
            SQLWhereDisjunction disjunction = new SQLWhereDisjunction();

            SQLWhereComparison comparison1 = new SQLWhereComparison(select);
            comparison1.setOperator("LIKE");
            comparison1.setFirstOperand(new SQLWhereOperandPathAttribute(ParamConfigConstants.ATTR_NAME_PARAMCONFIGDESCRIPCION, Constants.Type.STRING.getTypeName()));
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
        select.getOrderByPart().addItem(Constants.normalizePath(ParamConfigConstants.CLASS_NAME, ParamConfigConstants.ATTR_FIELD_PARAMCONFIGCODIGO, false), true);
    }
    
    @Override
    protected AbstractAppEntity buildRelatedInstance(String relatedInstanceJsonOid, String relatedInstancePathFirstItem, String remainingPath) {
        AbstractAppEntity relatedInstance = null;
        if (relatedInstancePathFirstItem.isEmpty()) {
            relatedInstance = buildParamConfigRelatedInstance(relatedInstanceJsonOid);
        }
        if (ParamConfigConstants.ROLE_NAME_LOGS.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildParamConfigRelatedInstance4LogParam(relatedInstanceJsonOid, remainingPath);
        }
        return relatedInstance;
    }
    
    private AbstractAppEntity buildParamConfigRelatedInstance(String relatedInstanceJsonOid) {
    	ObjectMapper mapper = new ObjectMapper();
    	try {
    		return new ParamConfig(mapper.readValue(relatedInstanceJsonOid, ParamConfigOid.class));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            throw new SystemException(e.getMessage());
    	}
    }

    private AbstractAppEntity buildParamConfigRelatedInstance4LogParam(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new LogParam(mapper.readValue(relatedInstanceJsonOid, LogParamOid.class)): LogParam.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            throw new SystemException(e.getMessage());
    	}
    }

    @Override
    protected void applyFilterByOid(String jsonOid, SQLSelect sql) {
        try {
            ParamConfigOid oid = new ParamConfigOid().fromJsonString(jsonOid);
            SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
            comparison1.setOperator("=");
            comparison1.setFirstOperand(new SQLWhereOperandPathAttribute(ParamConfigConstants.ATTR_FIELD_PARAMCONFIGCODIGO, Constants.Type.STRING.getTypeName()));
            comparison1.setSecondOperand(new SQLWhereOperandLiteral(oid.getParamConfigCodigo(), Constants.Type.STRING.getTypeName()));
            sql.addComparison(comparison1);
        } catch (Exception e) {
            LoggerFactory.getLogger(ParamConfigService.class).error(e.getMessage());
            throw new SystemException(e.getMessage());
        }
    }

}
