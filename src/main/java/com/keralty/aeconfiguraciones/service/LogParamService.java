package com.keralty.aeconfiguraciones.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.keralty.aeconfiguraciones.exception.ModelException;
import com.keralty.aeconfiguraciones.global.Constants;
import com.keralty.aeconfiguraciones.global.LogParamConstants;
import com.keralty.aeconfiguraciones.persistence.AbstractAppEntity;
import com.keralty.aeconfiguraciones.persistence.LogParam;
import com.keralty.aeconfiguraciones.persistence.oid.LogParamOid;
import com.keralty.aeconfiguraciones.persistence.oid.ParamConfigOid;
import com.keralty.aeconfiguraciones.persistence.ParamConfig;
import com.keralty.aeconfiguraciones.repository.LogParamJpaRepository;
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
import java.sql.Date;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.LoggerFactory;

@Transactional
@ApplicationScoped
public class LogParamService extends AbstractService {

    LogParamJpaRepository repository;

    LogParamService logParamSrv;

    ParamConfigService paramConfigSrv;

    @Inject
    public LogParamService(
            LogParamService logParamSrv,
            ParamConfigService paramConfigSrv,
            LogParamJpaRepository repository) {
        this.logParamSrv = logParamSrv;
        this.paramConfigSrv = paramConfigSrv;
        this.repository = repository;
    }

    public List<Map<String, Object>> solveQuery(QueryRequest request) {
        return solveGenericQuery(request, LogParamConstants.CLASS_NAME);
    }

    private List<LogParam> solveLogParamTypedQuery(QueryRequest request) {
        return repository.solveQuery(processQueryRequest(request, LogParamConstants.CLASS_NAME), request.getPageSize(), request.getPageNumber());
    }
    
    private int solveLogParamTypedCountQuery(QueryRequest request) {
        return repository.solveCountQuery(processCountQueryRequest(request, LogParamConstants.CLASS_NAME));
    }

    /*
     * Returns the population of class LogParam
     *
     * @return Population of class LogParam
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<LogParam> get() {
        List<LogParam> results = new java.util.ArrayList<>();
        repository.findAll().forEach(results::add);
        return results;
    }
    /*
     * Returns the population of class LogParam that matches certain search criteria
     *
     * @param queryRequest Search criteria
     *
     * @return Population of class LogParam matching the search criteria
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<LogParam> get(QueryRequest queryRequest) {
        return solveLogParamTypedQuery(queryRequest);
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
            return new PopulationResponseViewModel(solveLogParamTypedQuery(queryRequest).stream()
                    .map(instance -> buildDynamicObject(instance, queryRequest.getDisplaySetItems()))
                    .collect(Collectors.toList()), queryRequest.getPageSize() != 0 ? solveLogParamTypedCountQuery(queryRequest) : 0);
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
        return new PopulationResponseViewModel(solveLogParamTypedQuery(queryRequest).stream()
                .map(instance -> buildDynamicObject(instance, displaySetItems))
                .collect(Collectors.toList()));
    }

    /*
     * Returns an instance of LogParam by ID
     *
     * @param logParamCodigo Identification field logParamCodigo
     *
     * @return Instance of LogParam
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public LogParam getById(Long logParamCodigo) {
        return getByOID(new LogParamOid(logParamCodigo));
    }
    
    /*
     * Returns an instance of LogParam by OID
     *
     * @param oid Object IDentifier
     *
     * @return Instance of LogParam
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public LogParam getByOID(LogParamOid oid) {
        Optional<LogParam> instance = getInstanceByOid(oid);
        return instance.isPresent() ? instance.get() : null;
    }
    
    /*
     * Returns an instance of LogParam by OID applying horizontal visibility constraints
     *
     * @param oid Object IDentifier
     *
     * @return Instance of LogParam
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public LogParam getByOIDWithHV(LogParamOid oid) {
        Optional<LogParam> instance = getInstanceByOid(oid);
    	return instance.isPresent() && instance.get().checkHorizontalVisibility(getAgentInstance()) 
    		 ? instance.get() 
    		 : null;
    }
    
    private Optional<LogParam> getInstanceByOid(LogParamOid oid) {
        return oid != null && oid.getLogParamCodigo() != null
               ? repository.findById(oid.getLogParamCodigo())
               : Optional.empty();
    }

    /*
     * Returns an instance of LogParam by OID in JSON format
     *
     * @param jsonOid Object IDentifier in JSON format
     *
     * @return Instance of LogParam
     */
    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public LogParam getByJsonOid(String jsonOid) {
        LogParamOid oid = new LogParamOid();
        oid.fromJsonString(jsonOid);
        return getByOIDWithHV(oid);
    }

    /*
     * Returns the list of instances of LogParam related to a given instance through 
     * a given path
     *
     * @param relatedInstance Related instance
     *
     * @param relatedInstancePath Related instance path
     *
     * @return List of instances of LogParam
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<LogParam> getByRelated(AbstractAppEntity relatedInstance, String relatedInstancePath) {
        QueryRequest queryRequest = new QueryRequest(new FilterRequest(Constants.FILTER_BY_RELATED,
                Arrays.asList(new FilterVariableRequest(Constants.FILTER_BY_RELATED_FVAR_PATH, relatedInstancePath),
                        new FilterVariableRequest(Constants.FILTER_BY_RELATED_FVAR_OID, relatedInstance.getOid().toJson()))));
        return solveLogParamTypedQuery(queryRequest);
    }

    /*
     * Returns the list of instances of LogParam related to a given instance through
     * a given path
     *
     * @param relatedInstance Related instance
     *
     * @param relatedInstancePath Related instance path
     *
     * @return List of instances of LogParam
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<LogParam> getInstancesByRelated(AbstractAppEntity relatedInstance, String relatedInstancePath) {
        return getByRelated(relatedInstance, relatedInstancePath);
    }

    /*
     * Returns a set of displayset items for an instance of LogParam
     *
     * @param oid Object IDentifier
     *
     * @param displaySetItems List of comma-separated displayset items
     *
     * @return Set of displayset items for an instance of LogParam
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public InstanceResponseViewModel getLogParamDynamic(LogParamOid oid, String displaySetItems) {
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
    public String getSuppInfo(LogParamOid oid, String dsName) {
        LogParam instance = getByOIDWithHV(oid);
        return (instance != null ? getSuppInfo(instance, dsName): "");
    }

    private String getSuppInfo(LogParam instance, String dsName) {
        if (instance == null || instance.isNull()) return "";
        return dsName.isEmpty() ? instance.getOid().toJson() : "";
    }




    // IIU Methods


    /*
     * Solves the query needed for PIULogParam
     *
     * @param queryRequest Query request
     * @return List of viewmodels for LogParam using displayset DSLogParamPIU that 
     * match the search criteria
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<com.keralty.aeconfiguraciones.viewmodel.logparam.ds.DSLogParamPIU> piuPIULogParam(QueryRequest queryRequest) {
        return solveLogParamTypedQuery(queryRequest).stream()
                .map(com.keralty.aeconfiguraciones.viewmodel.logparam.ds.DSLogParamPIU::new)
                .collect(Collectors.toList());
    }

    /*
     * Solves the COUNT query needed for PIULogParam
     *
     * @param queryRequest Query request
     * @returns Count of instances for PIULogParam
     */
    public int getCount4PIULogParam(QueryRequest queryRequest) {
        return solveLogParamTypedQuery(queryRequest).size();
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
            com.keralty.aeconfiguraciones.viewmodel.logparam.siu.CrearRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = logParamSrv.crearTxn(request);
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
            com.keralty.aeconfiguraciones.viewmodel.logparam.siu.CrearRequest request) throws ModelException {
        request.setLogParamcrearpagrParamConfigInstance(paramConfigSrv.getByOIDWithHV(request.getLogParamcrearpagrParamConfig()));
        return logParamSrv.crearImplementation(request);
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
            com.keralty.aeconfiguraciones.viewmodel.logparam.siu.CrearRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        ParamConfig logParamcrearpagrParamConfig = request.getLogParamcrearpagrParamConfigInstance();
        Date logParamcrearpatrFecha = request.getLogParamcrearpatrFecha();
        String logParamcrearpatrUsuario = request.getLogParamcrearpatrUsuario();
        String logParamcrearpatrValorAnterior = request.getLogParamcrearpatrValorAnterior();
        String logParamcrearpatrNuevoValor = request.getLogParamcrearpatrNuevoValor();
        String logParamcrearpatrNombreUsuario = request.getLogParamcrearpatrNombreUsuario();

        request.checkArguments();
        LogParam instance = new LogParam();
        instance.add2ParamConfig(logParamcrearpagrParamConfig);

        instance.setLogParamCodigo(Long.valueOf(0));
        instance.setLogParamFecha(logParamcrearpatrFecha);
        instance.setLogParamUsuario(logParamcrearpatrUsuario);
        instance.setLogParamValorAnterior(logParamcrearpatrValorAnterior);
        instance.setLogParamNuevoValor(logParamcrearpatrNuevoValor);
        instance.setLogParamNombreUsuario(logParamcrearpatrNombreUsuario);

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
            com.keralty.aeconfiguraciones.viewmodel.logparam.siu.EliminarRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = logParamSrv.eliminarTxn(request);
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
            com.keralty.aeconfiguraciones.viewmodel.logparam.siu.EliminarRequest request) throws ModelException {
        request.setLogParameliminarpthisLogParamInstance(logParamSrv.getByOIDWithHV(request.getLogParameliminarpthisLogParam()));
        return logParamSrv.eliminarImplementation(request);
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
            com.keralty.aeconfiguraciones.viewmodel.logparam.siu.EliminarRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        LogParam logParameliminarpthisLogParam = request.getLogParameliminarpthisLogParamInstance();

        request.checkArguments();
        LogParam instance = logParameliminarpthisLogParam;


        repository.delete(instance);
        
        response.setInstance(instance);

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
        select.getOrderByPart().addItem(Constants.normalizePath(LogParamConstants.CLASS_NAME, LogParamConstants.ATTR_FIELD_LOGPARAMCODIGO, false), true);
    }
    
    @Override
    protected AbstractAppEntity buildRelatedInstance(String relatedInstanceJsonOid, String relatedInstancePathFirstItem, String remainingPath) {
        AbstractAppEntity relatedInstance = null;
        if (relatedInstancePathFirstItem.isEmpty()) {
            relatedInstance = buildLogParamRelatedInstance(relatedInstanceJsonOid);
        }
        if (LogParamConstants.ROLE_NAME_PARAMCONFIG.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildLogParamRelatedInstance4ParamConfig(relatedInstanceJsonOid, remainingPath);
        }
        return relatedInstance;
    }
    
    private AbstractAppEntity buildLogParamRelatedInstance(String relatedInstanceJsonOid) {
    	ObjectMapper mapper = new ObjectMapper();
    	try {
    		return new LogParam(mapper.readValue(relatedInstanceJsonOid, LogParamOid.class));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    private AbstractAppEntity buildLogParamRelatedInstance4ParamConfig(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new ParamConfig(mapper.readValue(relatedInstanceJsonOid, ParamConfigOid.class)): ParamConfig.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    @Override
    protected void applyFilterByOid(String jsonOid, SQLSelect sql) {
        try {
            LogParamOid oid = new LogParamOid().fromJsonString(jsonOid);
            SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
            comparison1.setOperator("=");
            comparison1.setFirstOperand(new SQLWhereOperandPathAttribute(LogParamConstants.ATTR_FIELD_LOGPARAMCODIGO, Constants.Type.AUTONUMERIC.getTypeName()));
            comparison1.setSecondOperand(new SQLWhereOperandLiteral(oid.getLogParamCodigo(), Constants.Type.AUTONUMERIC.getTypeName()));
            sql.addComparison(comparison1);
        } catch (Exception e) {
            LoggerFactory.getLogger(LogParamService.class).error(e.getMessage());
        }
    }

}
