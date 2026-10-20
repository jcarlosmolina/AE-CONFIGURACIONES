package com.keralty.aeconfiguraciones.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.keralty.aeconfiguraciones.exception.ModelException;
import com.keralty.aeconfiguraciones.global.Constants;
import com.keralty.aeconfiguraciones.global.FuncionarioConstants;
import com.keralty.aeconfiguraciones.global.LogPriorizacionSolConstants;
import com.keralty.aeconfiguraciones.global.STDFunctions;
import com.keralty.aeconfiguraciones.persistence.AbstractAppEntity;
import com.keralty.aeconfiguraciones.persistence.Funcionario;
import com.keralty.aeconfiguraciones.persistence.LogPriorizacionSol;
import com.keralty.aeconfiguraciones.persistence.oid.LogPriorizacionSolOid;
import com.keralty.aeconfiguraciones.persistence.oid.PriorizacionSolicitudesOid;
import com.keralty.aeconfiguraciones.persistence.PriorizacionSolicitudes;
import com.keralty.aeconfiguraciones.repository.LogPriorizacionSolJpaRepository;
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
public class LogPriorizacionSolService extends AbstractService {

    LogPriorizacionSolJpaRepository repository;

    LogPriorizacionSolService logPriorizacionSolSrv;

    PriorizacionSolicitudesService priorizacionSolicitudesSrv;

    @Inject
    public LogPriorizacionSolService(
            LogPriorizacionSolService logPriorizacionSolSrv,
            PriorizacionSolicitudesService priorizacionSolicitudesSrv,
            LogPriorizacionSolJpaRepository repository) {
        this.logPriorizacionSolSrv = logPriorizacionSolSrv;
        this.priorizacionSolicitudesSrv = priorizacionSolicitudesSrv;
        this.repository = repository;
    }

    public List<Map<String, Object>> solveQuery(QueryRequest request) {
        return solveGenericQuery(request, LogPriorizacionSolConstants.CLASS_NAME);
    }

    private List<LogPriorizacionSol> solveLogPriorizacionSolTypedQuery(QueryRequest request) {
        return repository.solveQuery(processQueryRequest(request, LogPriorizacionSolConstants.CLASS_NAME), request.getPageSize(), request.getPageNumber());
    }
    
    private int solveLogPriorizacionSolTypedCountQuery(QueryRequest request) {
        return repository.solveCountQuery(processCountQueryRequest(request, LogPriorizacionSolConstants.CLASS_NAME));
    }

    /*
     * Returns the population of class LogPriorizacionSol
     *
     * @return Population of class LogPriorizacionSol
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<LogPriorizacionSol> get() {
        List<LogPriorizacionSol> results = new java.util.ArrayList<>();
        repository.findAll().forEach(results::add);
        return results;
    }
    /*
     * Returns the population of class LogPriorizacionSol that matches certain search criteria
     *
     * @param queryRequest Search criteria
     *
     * @return Population of class LogPriorizacionSol matching the search criteria
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<LogPriorizacionSol> get(QueryRequest queryRequest) {
        return solveLogPriorizacionSolTypedQuery(queryRequest);
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
            return new PopulationResponseViewModel(solveLogPriorizacionSolTypedQuery(queryRequest).stream()
                    .map(instance -> buildDynamicObject(instance, queryRequest.getDisplaySetItems()))
                    .collect(Collectors.toList()), queryRequest.getPageSize() != 0 ? solveLogPriorizacionSolTypedCountQuery(queryRequest) : 0);
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
        return new PopulationResponseViewModel(solveLogPriorizacionSolTypedQuery(queryRequest).stream()
                .map(instance -> buildDynamicObject(instance, displaySetItems))
                .collect(Collectors.toList()));
    }

    /*
     * Returns an instance of LogPriorizacionSol by ID
     *
     * @param logPriorizacionSolId Identification field logPriorizacionSolId
     *
     * @return Instance of LogPriorizacionSol
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public LogPriorizacionSol getById(Long logPriorizacionSolId) {
        return getByOID(new LogPriorizacionSolOid(logPriorizacionSolId));
    }
    
    /*
     * Returns an instance of LogPriorizacionSol by OID
     *
     * @param oid Object IDentifier
     *
     * @return Instance of LogPriorizacionSol
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public LogPriorizacionSol getByOID(LogPriorizacionSolOid oid) {
        Optional<LogPriorizacionSol> instance = getInstanceByOid(oid);
        return instance.isPresent() ? instance.get() : null;
    }
    
    /*
     * Returns an instance of LogPriorizacionSol by OID applying horizontal visibility constraints
     *
     * @param oid Object IDentifier
     *
     * @return Instance of LogPriorizacionSol
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public LogPriorizacionSol getByOIDWithHV(LogPriorizacionSolOid oid) {
        Optional<LogPriorizacionSol> instance = getInstanceByOid(oid);
    	return instance.isPresent() && instance.get().checkHorizontalVisibility(getAgentInstance()) 
    		 ? instance.get() 
    		 : null;
    }
    
    private Optional<LogPriorizacionSol> getInstanceByOid(LogPriorizacionSolOid oid) {
        return oid != null && oid.getLogPriorizacionSolId() != null
               ? repository.findById(oid.getLogPriorizacionSolId())
               : Optional.empty();
    }

    /*
     * Returns an instance of LogPriorizacionSol by OID in JSON format
     *
     * @param jsonOid Object IDentifier in JSON format
     *
     * @return Instance of LogPriorizacionSol
     */
    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public LogPriorizacionSol getByJsonOid(String jsonOid) {
        LogPriorizacionSolOid oid = new LogPriorizacionSolOid();
        oid.fromJsonString(jsonOid);
        return getByOIDWithHV(oid);
    }

    /*
     * Returns the list of instances of LogPriorizacionSol related to a given instance through 
     * a given path
     *
     * @param relatedInstance Related instance
     *
     * @param relatedInstancePath Related instance path
     *
     * @return List of instances of LogPriorizacionSol
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<LogPriorizacionSol> getByRelated(AbstractAppEntity relatedInstance, String relatedInstancePath) {
        QueryRequest queryRequest = new QueryRequest(new FilterRequest(Constants.FILTER_BY_RELATED,
                Arrays.asList(new FilterVariableRequest(Constants.FILTER_BY_RELATED_FVAR_PATH, relatedInstancePath),
                        new FilterVariableRequest(Constants.FILTER_BY_RELATED_FVAR_OID, relatedInstance.getOid().toJson()))));
        return solveLogPriorizacionSolTypedQuery(queryRequest);
    }

    /*
     * Returns the list of instances of LogPriorizacionSol related to a given instance through
     * a given path
     *
     * @param relatedInstance Related instance
     *
     * @param relatedInstancePath Related instance path
     *
     * @return List of instances of LogPriorizacionSol
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<LogPriorizacionSol> getInstancesByRelated(AbstractAppEntity relatedInstance, String relatedInstancePath) {
        return getByRelated(relatedInstance, relatedInstancePath);
    }

    /*
     * Returns a set of displayset items for an instance of LogPriorizacionSol
     *
     * @param oid Object IDentifier
     *
     * @param displaySetItems List of comma-separated displayset items
     *
     * @return Set of displayset items for an instance of LogPriorizacionSol
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public InstanceResponseViewModel getLogPriorizacionSolDynamic(LogPriorizacionSolOid oid, String displaySetItems) {
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
    public String getSuppInfo(LogPriorizacionSolOid oid, String dsName) {
        LogPriorizacionSol instance = getByOIDWithHV(oid);
        return (instance != null ? getSuppInfo(instance, dsName): "");
    }

    private String getSuppInfo(LogPriorizacionSol instance, String dsName) {
        if (instance == null || instance.isNull()) return "";
        return dsName.isEmpty() ? instance.getOid().toJson() : "";
    }




    // IIU Methods


    /*
     * Solves the query needed for PIULogPriorizacionSol
     *
     * @param queryRequest Query request
     * @return List of viewmodels for LogPriorizacionSol using displayset DSLogPriorizacionSol that 
     * match the search criteria
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<com.keralty.aeconfiguraciones.viewmodel.logpriorizacionsol.ds.DSLogPriorizacionSol> piuPIULogPriorizacionSol(QueryRequest queryRequest) {
        return solveLogPriorizacionSolTypedQuery(queryRequest).stream()
                .map(com.keralty.aeconfiguraciones.viewmodel.logpriorizacionsol.ds.DSLogPriorizacionSol::new)
                .collect(Collectors.toList());
    }

    /*
     * Solves the COUNT query needed for PIULogPriorizacionSol
     *
     * @param queryRequest Query request
     * @returns Count of instances for PIULogPriorizacionSol
     */
    public int getCount4PIULogPriorizacionSol(QueryRequest queryRequest) {
        return solveLogPriorizacionSolTypedQuery(queryRequest).size();
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
            com.keralty.aeconfiguraciones.viewmodel.logpriorizacionsol.siu.CrearRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = logPriorizacionSolSrv.crearTxn(request);
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
            com.keralty.aeconfiguraciones.viewmodel.logpriorizacionsol.siu.CrearRequest request) throws ModelException {
        request.setLogPriorizacionSolcrearpagrPriSolicitudInstance(priorizacionSolicitudesSrv.getByOIDWithHV(request.getLogPriorizacionSolcrearpagrPriSolicitud()));
        return logPriorizacionSolSrv.crearImplementation(request);
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
            com.keralty.aeconfiguraciones.viewmodel.logpriorizacionsol.siu.CrearRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        PriorizacionSolicitudes logPriorizacionSolcrearpagrPriSolicitud = request.getLogPriorizacionSolcrearpagrPriSolicitudInstance();
        Long logPriorizacionSolcrearpatrEstadoSolicitud = request.getLogPriorizacionSolcrearpatrEstadoSolicitud();
        Long logPriorizacionSolcrearpatrEstadoDoc = request.getLogPriorizacionSolcrearpatrEstadoDoc();
        Long logPriorizacionSolcrearpatrPrioridad = request.getLogPriorizacionSolcrearpatrPrioridad();
        String logPriorizacionSolcrearpatrLoginFuncionario = request.getLogPriorizacionSolcrearpatrLoginFuncionario();

        request.checkArguments();
        LogPriorizacionSol instance = new LogPriorizacionSol();
        if (logPriorizacionSolcrearpagrPriSolicitud != null && !logPriorizacionSolcrearpagrPriSolicitud.isNull()) {
            instance.add2PriSolicitud(logPriorizacionSolcrearpagrPriSolicitud);
        }

        instance.setLogPriorizacionSolId(Long.valueOf(0));
        instance.setLogPriorizacionSolFecha(STDFunctions.systemDateTimeFun());
        instance.setLogPriorizacionSolEstadoSolicitud(logPriorizacionSolcrearpatrEstadoSolicitud);
        instance.setLogPriorizacionSolEstadoDoc(logPriorizacionSolcrearpatrEstadoDoc);
        instance.setLogPriorizacionSolPrioridad(logPriorizacionSolcrearpatrPrioridad);
        instance.setLogPriorizacionSolLoginFuncionario(logPriorizacionSolcrearpatrLoginFuncionario);

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
            com.keralty.aeconfiguraciones.viewmodel.logpriorizacionsol.siu.EliminarRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = logPriorizacionSolSrv.eliminarTxn(request);
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
            com.keralty.aeconfiguraciones.viewmodel.logpriorizacionsol.siu.EliminarRequest request) throws ModelException {
        request.setLogPriorizacionSoleliminarpthisLogPriorizacionSolInstance(logPriorizacionSolSrv.getByOIDWithHV(request.getLogPriorizacionSoleliminarpthisLogPriorizacionSol()));
        return logPriorizacionSolSrv.eliminarImplementation(request);
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
            com.keralty.aeconfiguraciones.viewmodel.logpriorizacionsol.siu.EliminarRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        LogPriorizacionSol logPriorizacionSoleliminarpthisLogPriorizacionSol = request.getLogPriorizacionSoleliminarpthisLogPriorizacionSolInstance();

        request.checkArguments();
        LogPriorizacionSol instance = logPriorizacionSoleliminarpthisLogPriorizacionSol;


        repository.delete(instance);
        
        response.setInstance(instance);

        return response;
    }

    // Service: InsPriorizacionSolicitudes

    /*
     * Entry point for service InsPriorizacionSolicitudes
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse insPriorizacionSolicitudes(
            com.keralty.aeconfiguraciones.viewmodel.logpriorizacionsol.siu.InsPriorizacionSolicitudesRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = logPriorizacionSolSrv.insPriorizacionSolicitudesTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service InsPriorizacionSolicitudes
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse insPriorizacionSolicitudesTxn(
            com.keralty.aeconfiguraciones.viewmodel.logpriorizacionsol.siu.InsPriorizacionSolicitudesRequest request) throws ModelException {
        request.setLogPriorizacionSolInsPriorizacionSolicitudespthisLogPriorizacionSolInstance(logPriorizacionSolSrv.getByOIDWithHV(request.getLogPriorizacionSolInsPriorizacionSolicitudespthisLogPriorizacionSol()));
        request.setLogPriorizacionSolInsPriorizacionSolicitudespevcPriorizacionSolicitudesInstance(priorizacionSolicitudesSrv.getByOIDWithHV(request.getLogPriorizacionSolInsPriorizacionSolicitudespevcPriorizacionSolicitudes()));
        return logPriorizacionSolSrv.insPriorizacionSolicitudesImplementation(request, true);
    }

    /*
     * Implements service InsPriorizacionSolicitudes
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse insPriorizacionSolicitudesImplementation(
            com.keralty.aeconfiguraciones.viewmodel.logpriorizacionsol.siu.InsPriorizacionSolicitudesRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        LogPriorizacionSol logPriorizacionSolInsPriorizacionSolicitudespthisLogPriorizacionSol = request.getLogPriorizacionSolInsPriorizacionSolicitudespthisLogPriorizacionSolInstance();
        PriorizacionSolicitudes logPriorizacionSolInsPriorizacionSolicitudespevcPriorizacionSolicitudes = request.getLogPriorizacionSolInsPriorizacionSolicitudespevcPriorizacionSolicitudesInstance();

        request.checkArguments();
        LogPriorizacionSol instance = logPriorizacionSolInsPriorizacionSolicitudespthisLogPriorizacionSol;

        instance.add2PriSolicitud(logPriorizacionSolInsPriorizacionSolicitudespevcPriorizacionSolicitudes);
        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.priorizacionsolicitudes.siu.InsPriorizacionSolicitudesRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.priorizacionsolicitudes.siu.InsPriorizacionSolicitudesRequest();
            relatedRequest.setPriorizacionSolicitudesInsPriorizacionSolicitudespthisPriorizacionSolicitudesInstance(logPriorizacionSolInsPriorizacionSolicitudespevcPriorizacionSolicitudes);
            relatedRequest.setPriorizacionSolicitudesInsPriorizacionSolicitudespevcLogPriorizacionSolInstance(logPriorizacionSolInsPriorizacionSolicitudespthisLogPriorizacionSol);
            priorizacionSolicitudesSrv.insPriorizacionSolicitudesImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));

        return response;
    }

    // Service: DelPriorizacionSolicitudes

    /*
     * Entry point for service DelPriorizacionSolicitudes
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse delPriorizacionSolicitudes(
            com.keralty.aeconfiguraciones.viewmodel.logpriorizacionsol.siu.DelPriorizacionSolicitudesRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = logPriorizacionSolSrv.delPriorizacionSolicitudesTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service DelPriorizacionSolicitudes
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse delPriorizacionSolicitudesTxn(
            com.keralty.aeconfiguraciones.viewmodel.logpriorizacionsol.siu.DelPriorizacionSolicitudesRequest request) throws ModelException {
        request.setLogPriorizacionSolDelPriorizacionSolicitudespthisLogPriorizacionSolInstance(logPriorizacionSolSrv.getByOIDWithHV(request.getLogPriorizacionSolDelPriorizacionSolicitudespthisLogPriorizacionSol()));
        request.setLogPriorizacionSolDelPriorizacionSolicitudespevcPriorizacionSolicitudesInstance(priorizacionSolicitudesSrv.getByOIDWithHV(request.getLogPriorizacionSolDelPriorizacionSolicitudespevcPriorizacionSolicitudes()));
        return logPriorizacionSolSrv.delPriorizacionSolicitudesImplementation(request, true);
    }

    /*
     * Implements service DelPriorizacionSolicitudes
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse delPriorizacionSolicitudesImplementation(
            com.keralty.aeconfiguraciones.viewmodel.logpriorizacionsol.siu.DelPriorizacionSolicitudesRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        LogPriorizacionSol logPriorizacionSolDelPriorizacionSolicitudespthisLogPriorizacionSol = request.getLogPriorizacionSolDelPriorizacionSolicitudespthisLogPriorizacionSolInstance();
        PriorizacionSolicitudes logPriorizacionSolDelPriorizacionSolicitudespevcPriorizacionSolicitudes = request.getLogPriorizacionSolDelPriorizacionSolicitudespevcPriorizacionSolicitudesInstance();

        request.checkArguments();
        LogPriorizacionSol instance = logPriorizacionSolDelPriorizacionSolicitudespthisLogPriorizacionSol;

        instance.add2PriSolicitud(null);
        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.priorizacionsolicitudes.siu.DelPriorizacionSolicitudesRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.priorizacionsolicitudes.siu.DelPriorizacionSolicitudesRequest();
            relatedRequest.setPriorizacionSolicitudesDelPriorizacionSolicitudespthisPriorizacionSolicitudesInstance(logPriorizacionSolDelPriorizacionSolicitudespevcPriorizacionSolicitudes);
            relatedRequest.setPriorizacionSolicitudesDelPriorizacionSolicitudespevcLogPriorizacionSolInstance(logPriorizacionSolDelPriorizacionSolicitudespthisLogPriorizacionSol);
            priorizacionSolicitudesSrv.delPriorizacionSolicitudesImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));

        return response;
    }

    // Service: TCREAR

    /*
     * Entry point for service TCREAR
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse tCREAR(
            com.keralty.aeconfiguraciones.viewmodel.logpriorizacionsol.siu.TCREARRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = logPriorizacionSolSrv.tCREARTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service TCREAR
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse tCREARTxn(
            com.keralty.aeconfiguraciones.viewmodel.logpriorizacionsol.siu.TCREARRequest request) throws ModelException {
        request.setLogPriorizacionSolTCREARptpagrPriorizacionSolicitudeInstance(priorizacionSolicitudesSrv.getByOIDWithHV(request.getLogPriorizacionSolTCREARptpagrPriorizacionSolicitude()));
        return logPriorizacionSolSrv.tCREARImplementation(request);
    }

    /*
     * Implements service TCREAR
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse tCREARImplementation(
            com.keralty.aeconfiguraciones.viewmodel.logpriorizacionsol.siu.TCREARRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        PriorizacionSolicitudes logPriorizacionSolTCREARptpagrPriorizacionSolicitude = request.getLogPriorizacionSolTCREARptpagrPriorizacionSolicitudeInstance();
        Long logPriorizacionSolTCREARptpatrEstadoSolicitud = request.getLogPriorizacionSolTCREARptpatrEstadoSolicitud();
        Long logPriorizacionSolTCREARptpatrEstadoDoc = request.getLogPriorizacionSolTCREARptpatrEstadoDoc();
        Long logPriorizacionSolTCREARptpatrPrioridad = request.getLogPriorizacionSolTCREARptpatrPrioridad();

        request.checkArguments();
        LogPriorizacionSol instance;

        /* Action 1: IF (AGENTFuncionario <> NULL) */
        boolean ifCondition1 = (((Funcionario) getAgentInstance(FuncionarioConstants.CLASS_NAME)) != null && !((Funcionario) getAgentInstance(FuncionarioConstants.CLASS_NAME)).isNull());
        if (ifCondition1) {
            com.keralty.aeconfiguraciones.viewmodel.logpriorizacionsol.siu.CrearRequest step10Request = new com.keralty.aeconfiguraciones.viewmodel.logpriorizacionsol.siu.CrearRequest();
            step10Request.setLogPriorizacionSolcrearpagrPriSolicitudInstance(logPriorizacionSolTCREARptpagrPriorizacionSolicitude);
            step10Request.setLogPriorizacionSolcrearpatrEstadoSolicitud(logPriorizacionSolTCREARptpatrEstadoSolicitud);
            step10Request.setLogPriorizacionSolcrearpatrEstadoDoc(logPriorizacionSolTCREARptpatrEstadoDoc);
            step10Request.setLogPriorizacionSolcrearpatrPrioridad(logPriorizacionSolTCREARptpatrPrioridad);
            step10Request.setLogPriorizacionSolcrearpatrLoginFuncionario(((Funcionario) getAgentInstance(FuncionarioConstants.CLASS_NAME)).getFuncionarioUsuarioLDAP());
            instance = (LogPriorizacionSol) logPriorizacionSolSrv.crearImplementation(step10Request).getInstance();
        } else {
            com.keralty.aeconfiguraciones.viewmodel.logpriorizacionsol.siu.CrearRequest step11Request = new com.keralty.aeconfiguraciones.viewmodel.logpriorizacionsol.siu.CrearRequest();
            step11Request.setLogPriorizacionSolcrearpagrPriSolicitudInstance(logPriorizacionSolTCREARptpagrPriorizacionSolicitude);
            step11Request.setLogPriorizacionSolcrearpatrEstadoSolicitud(logPriorizacionSolTCREARptpatrEstadoSolicitud);
            step11Request.setLogPriorizacionSolcrearpatrEstadoDoc(logPriorizacionSolTCREARptpatrEstadoDoc);
            step11Request.setLogPriorizacionSolcrearpatrPrioridad(logPriorizacionSolTCREARptpatrPrioridad);
            step11Request.setLogPriorizacionSolcrearpatrLoginFuncionario("usuicafile");
            instance = (LogPriorizacionSol) logPriorizacionSolSrv.crearImplementation(step11Request).getInstance();
        }

        response.setInstance(getByOID(instance.getOid()));

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
        select.getOrderByPart().addItem(Constants.normalizePath(LogPriorizacionSolConstants.CLASS_NAME, LogPriorizacionSolConstants.ATTR_FIELD_LOGPRIORIZACIONSOLID, false), true);
    }
    
    @Override
    protected AbstractAppEntity buildRelatedInstance(String relatedInstanceJsonOid, String relatedInstancePathFirstItem, String remainingPath) {
        AbstractAppEntity relatedInstance = null;
        if (relatedInstancePathFirstItem.isEmpty()) {
            relatedInstance = buildLogPriorizacionSolRelatedInstance(relatedInstanceJsonOid);
        }
        if (LogPriorizacionSolConstants.ROLE_NAME_PRISOLICITUD.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildLogPriorizacionSolRelatedInstance4PriorizacionSolicitudes(relatedInstanceJsonOid, remainingPath);
        }
        return relatedInstance;
    }
    
    private AbstractAppEntity buildLogPriorizacionSolRelatedInstance(String relatedInstanceJsonOid) {
    	ObjectMapper mapper = new ObjectMapper();
    	try {
    		return new LogPriorizacionSol(mapper.readValue(relatedInstanceJsonOid, LogPriorizacionSolOid.class));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    private AbstractAppEntity buildLogPriorizacionSolRelatedInstance4PriorizacionSolicitudes(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new PriorizacionSolicitudes(mapper.readValue(relatedInstanceJsonOid, PriorizacionSolicitudesOid.class)): PriorizacionSolicitudes.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    @Override
    protected void applyFilterByOid(String jsonOid, SQLSelect sql) {
        try {
            LogPriorizacionSolOid oid = new LogPriorizacionSolOid().fromJsonString(jsonOid);
            SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
            comparison1.setOperator("=");
            comparison1.setFirstOperand(new SQLWhereOperandPathAttribute(LogPriorizacionSolConstants.ATTR_FIELD_LOGPRIORIZACIONSOLID, Constants.Type.AUTONUMERIC.getTypeName()));
            comparison1.setSecondOperand(new SQLWhereOperandLiteral(oid.getLogPriorizacionSolId(), Constants.Type.AUTONUMERIC.getTypeName()));
            sql.addComparison(comparison1);
        } catch (Exception e) {
            LoggerFactory.getLogger(LogPriorizacionSolService.class).error(e.getMessage());
        }
    }

}
