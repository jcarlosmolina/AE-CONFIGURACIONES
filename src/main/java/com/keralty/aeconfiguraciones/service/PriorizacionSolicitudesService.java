package com.keralty.aeconfiguraciones.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.keralty.aeconfiguraciones.exception.InstanceExistsException;
import com.keralty.aeconfiguraciones.exception.MinCardinalityException;
import com.keralty.aeconfiguraciones.exception.ModelException;
import com.keralty.aeconfiguraciones.exception.SystemException;
import com.keralty.aeconfiguraciones.global.Constants;
import com.keralty.aeconfiguraciones.global.PriorizacionSolicitudesConstants;
import com.keralty.aeconfiguraciones.persistence.AbstractAppEntity;
import com.keralty.aeconfiguraciones.persistence.LogPriorizacionSol;
import com.keralty.aeconfiguraciones.persistence.oid.LogPriorizacionSolOid;
import com.keralty.aeconfiguraciones.persistence.oid.PriorizacionSolicitudesOid;
import com.keralty.aeconfiguraciones.persistence.PriorizacionSolicitudes;
import com.keralty.aeconfiguraciones.repository.PriorizacionSolicitudesJpaRepository;
import com.keralty.aeconfiguraciones.sqlutils.SQLSelect;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereComparison;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereConjunction;
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
public class PriorizacionSolicitudesService extends AbstractService {

    PriorizacionSolicitudesJpaRepository repository;

    PriorizacionSolicitudesService priorizacionSolicitudesSrv;

    LogPriorizacionSolService logPriorizacionSolSrv;

    @Inject
    public PriorizacionSolicitudesService(
            PriorizacionSolicitudesService priorizacionSolicitudesSrv,
            LogPriorizacionSolService logPriorizacionSolSrv,
            PriorizacionSolicitudesJpaRepository repository) {
        this.priorizacionSolicitudesSrv = priorizacionSolicitudesSrv;
        this.logPriorizacionSolSrv = logPriorizacionSolSrv;
        this.repository = repository;
    }

    public List<Map<String, Object>> solveQuery(QueryRequest request) {
        return solveGenericQuery(request, PriorizacionSolicitudesConstants.CLASS_NAME);
    }

    private List<PriorizacionSolicitudes> solvePriorizacionSolicitudesTypedQuery(QueryRequest request) {
        return repository.solveQuery(processQueryRequest(request, PriorizacionSolicitudesConstants.CLASS_NAME), request.getPageSize(), request.getPageNumber());
    }
    
    private int solvePriorizacionSolicitudesTypedCountQuery(QueryRequest request) {
        return repository.solveCountQuery(processCountQueryRequest(request, PriorizacionSolicitudesConstants.CLASS_NAME));
    }

    /*
     * Returns the population of class PriorizacionSolicitudes
     *
     * @return Population of class PriorizacionSolicitudes
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<PriorizacionSolicitudes> get() {
        List<PriorizacionSolicitudes> results = new java.util.ArrayList<>();
        repository.findAll().forEach(results::add);
        return results;
    }
    /*
     * Returns the population of class PriorizacionSolicitudes that matches certain search criteria
     *
     * @param queryRequest Search criteria
     *
     * @return Population of class PriorizacionSolicitudes matching the search criteria
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<PriorizacionSolicitudes> get(QueryRequest queryRequest) {
        return solvePriorizacionSolicitudesTypedQuery(queryRequest);
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
            return new PopulationResponseViewModel(solvePriorizacionSolicitudesTypedQuery(queryRequest).stream()
                    .map(instance -> buildDynamicObject(instance, queryRequest.getDisplaySetItems()))
                    .collect(Collectors.toList()), queryRequest.getPageSize() != 0 ? solvePriorizacionSolicitudesTypedCountQuery(queryRequest) : 0);
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
        return new PopulationResponseViewModel(solvePriorizacionSolicitudesTypedQuery(queryRequest).stream()
                .map(instance -> buildDynamicObject(instance, displaySetItems))
                .collect(Collectors.toList()));
    }

    /*
     * Returns an instance of PriorizacionSolicitudes by ID
     *
     * @param priorizacionSolicitudesEstadoSolicitud Identification field priorizacionSolicitudesEstadoSolicitud
     *
     * @param priorizacionSolicitudesEstadoDoc Identification field priorizacionSolicitudesEstadoDoc
     *
     * @return Instance of PriorizacionSolicitudes
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public PriorizacionSolicitudes getById(Long priorizacionSolicitudesEstadoSolicitud, Long priorizacionSolicitudesEstadoDoc) {
        return getByOID(new PriorizacionSolicitudesOid(priorizacionSolicitudesEstadoSolicitud, priorizacionSolicitudesEstadoDoc));
    }
    
    /*
     * Returns an instance of PriorizacionSolicitudes by OID
     *
     * @param oid Object IDentifier
     *
     * @return Instance of PriorizacionSolicitudes
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public PriorizacionSolicitudes getByOID(PriorizacionSolicitudesOid oid) {
        Optional<PriorizacionSolicitudes> instance = getInstanceByOid(oid);
        return instance.isPresent() ? instance.get() : null;
    }
    
    /*
     * Returns an instance of PriorizacionSolicitudes by OID applying horizontal visibility constraints
     *
     * @param oid Object IDentifier
     *
     * @return Instance of PriorizacionSolicitudes
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public PriorizacionSolicitudes getByOIDWithHV(PriorizacionSolicitudesOid oid) {
        Optional<PriorizacionSolicitudes> instance = getInstanceByOid(oid);
    	return instance.isPresent() && instance.get().checkHorizontalVisibility(getAgentInstance()) 
    		 ? instance.get() 
    		 : null;
    }
    
    private Optional<PriorizacionSolicitudes> getInstanceByOid(PriorizacionSolicitudesOid oid) {
        return oid != null && oid.getPriorizacionSolicitudesEstadoSolicitud() != null
               ? repository.findById(oid)
               : Optional.empty();
    }

    /*
     * Returns an instance of PriorizacionSolicitudes by OID in JSON format
     *
     * @param jsonOid Object IDentifier in JSON format
     *
     * @return Instance of PriorizacionSolicitudes
     */
    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public PriorizacionSolicitudes getByJsonOid(String jsonOid) {
        PriorizacionSolicitudesOid oid = new PriorizacionSolicitudesOid();
        oid.fromJsonString(jsonOid);
        return getByOIDWithHV(oid);
    }

    /*
     * Returns the list of instances of PriorizacionSolicitudes related to a given instance through 
     * a given path
     *
     * @param relatedInstance Related instance
     *
     * @param relatedInstancePath Related instance path
     *
     * @return List of instances of PriorizacionSolicitudes
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<PriorizacionSolicitudes> getByRelated(AbstractAppEntity relatedInstance, String relatedInstancePath) {
        QueryRequest queryRequest = new QueryRequest(new FilterRequest(Constants.FILTER_BY_RELATED,
                Arrays.asList(new FilterVariableRequest(Constants.FILTER_BY_RELATED_FVAR_PATH, relatedInstancePath),
                        new FilterVariableRequest(Constants.FILTER_BY_RELATED_FVAR_OID, relatedInstance.getOid().toJson()))));
        return solvePriorizacionSolicitudesTypedQuery(queryRequest);
    }

    /*
     * Returns the list of instances of PriorizacionSolicitudes related to a given instance through
     * a given path
     *
     * @param relatedInstance Related instance
     *
     * @param relatedInstancePath Related instance path
     *
     * @return List of instances of PriorizacionSolicitudes
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<PriorizacionSolicitudes> getInstancesByRelated(AbstractAppEntity relatedInstance, String relatedInstancePath) {
        return getByRelated(relatedInstance, relatedInstancePath);
    }

    /*
     * Returns a set of displayset items for an instance of PriorizacionSolicitudes
     *
     * @param oid Object IDentifier
     *
     * @param displaySetItems List of comma-separated displayset items
     *
     * @return Set of displayset items for an instance of PriorizacionSolicitudes
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public InstanceResponseViewModel getPriorizacionSolicitudesDynamic(PriorizacionSolicitudesOid oid, String displaySetItems) {
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
    public String getSuppInfo(PriorizacionSolicitudesOid oid, String dsName) {
        PriorizacionSolicitudes instance = getByOIDWithHV(oid);
        return (instance != null ? getSuppInfo(instance, dsName): "");
    }

    private String getSuppInfo(PriorizacionSolicitudes instance, String dsName) {
        if (instance == null || instance.isNull()) return "";
        return dsName.isEmpty() ? instance.getOid().toJson() : "";
    }


    @Override
    protected void applyQueryFilters(FilterRequest filterRequest, SQLSelect sql) throws SystemException {
        if (PriorizacionSolicitudesConstants.FILTER_NAME_FPRIORIZACIONSOLICITUDES.equalsIgnoreCase(filterRequest.getName()))
            fPriorizacionSolicitudes(filterRequest, sql);
    }


    private void fPriorizacionSolicitudes(FilterRequest filterRequest, SQLSelect sql) {
        SQLWhereConjunction conjunction = new SQLWhereConjunction();
        Long vfEstadoSol = filterRequest.hasFilterVariable(PriorizacionSolicitudesConstants.VAR_NAME_FPRIORIZACIONSOLICITUDES_VFESTADOSOL) ? (Long) filterRequest.getFilterVariable(PriorizacionSolicitudesConstants.VAR_NAME_FPRIORIZACIONSOLICITUDES_VFESTADOSOL, FilterVariableRequest.INT) : null;
        Long vfEstadoDoc = filterRequest.hasFilterVariable(PriorizacionSolicitudesConstants.VAR_NAME_FPRIORIZACIONSOLICITUDES_VFESTADODOC) ? (Long) filterRequest.getFilterVariable(PriorizacionSolicitudesConstants.VAR_NAME_FPRIORIZACIONSOLICITUDES_VFESTADODOC, FilterVariableRequest.NAT) : null;
        Long vfPriordad = filterRequest.hasFilterVariable(PriorizacionSolicitudesConstants.VAR_NAME_FPRIORIZACIONSOLICITUDES_VFPRIORDAD) ? (Long) filterRequest.getFilterVariable(PriorizacionSolicitudesConstants.VAR_NAME_FPRIORIZACIONSOLICITUDES_VFPRIORDAD, FilterVariableRequest.NAT) : null;

        // EstadoSolicitud = vfEstadoSol
        if (vfEstadoSol != null) {
            // '=' Comparison
            SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
            comparison1.setOperator("=");
            
            // Operand 1
            SQLWhereOperandPathAttribute operand112 = new SQLWhereOperandPathAttribute(PriorizacionSolicitudesConstants.ATTR_NAME_PRIORIZACIONSOLICITUDESESTADOSOLICITUD, Constants.Type.INT.getTypeName());
            comparison1.setFirstOperand(operand112);
            
            // Operand 2
            comparison1.setSecondOperand(new SQLWhereOperandLiteral(vfEstadoSol, Constants.Type.INT.getTypeName()));
            conjunction.addOperand(comparison1);

        }        

        // EstadoDoc = vfEstadoDoc
        if (vfEstadoDoc != null) {
            // '=' Comparison
            SQLWhereComparison comparison2 = new SQLWhereComparison(sql);
            comparison2.setOperator("=");
            
            // Operand 1
            SQLWhereOperandPathAttribute operand113 = new SQLWhereOperandPathAttribute(PriorizacionSolicitudesConstants.ATTR_NAME_PRIORIZACIONSOLICITUDESESTADODOC, Constants.Type.NAT.getTypeName());
            comparison2.setFirstOperand(operand113);
            
            // Operand 2
            comparison2.setSecondOperand(new SQLWhereOperandLiteral(vfEstadoDoc, Constants.Type.NAT.getTypeName()));
            conjunction.addOperand(comparison2);

        }        

        // Prioridad = vfPriordad
        if (vfPriordad != null) {
            // '=' Comparison
            SQLWhereComparison comparison3 = new SQLWhereComparison(sql);
            comparison3.setOperator("=");
            
            // Operand 1
            SQLWhereOperandPathAttribute operand114 = new SQLWhereOperandPathAttribute(PriorizacionSolicitudesConstants.ATTR_NAME_PRIORIZACIONSOLICITUDESPRIORIDAD, Constants.Type.NAT.getTypeName());
            comparison3.setFirstOperand(operand114);
            
            // Operand 2
            comparison3.setSecondOperand(new SQLWhereOperandLiteral(vfPriordad, Constants.Type.NAT.getTypeName()));
            conjunction.addOperand(comparison3);

        }        

        sql.addConjunction(conjunction);
    }

    // IIU Methods


    /*
     * Solves the query needed for PIUPriorizacionSolicitudes
     *
     * @param queryRequest Query request
     * @return List of viewmodels for PriorizacionSolicitudes using displayset DSPriorizacionSolicitudes that 
     * match the search criteria
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<com.keralty.aeconfiguraciones.viewmodel.priorizacionsolicitudes.ds.DSPriorizacionSolicitudes> piuPIUPriorizacionSolicitudes(QueryRequest queryRequest) {
        return solvePriorizacionSolicitudesTypedQuery(queryRequest).stream()
                .map(com.keralty.aeconfiguraciones.viewmodel.priorizacionsolicitudes.ds.DSPriorizacionSolicitudes::new)
                .collect(Collectors.toList());
    }

    /*
     * Solves the COUNT query needed for PIUPriorizacionSolicitudes
     *
     * @param queryRequest Query request
     * @returns Count of instances for PIUPriorizacionSolicitudes
     */
    public int getCount4PIUPriorizacionSolicitudes(QueryRequest queryRequest) {
        return solvePriorizacionSolicitudesTypedQuery(queryRequest).size();
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
            com.keralty.aeconfiguraciones.viewmodel.priorizacionsolicitudes.siu.CrearRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = priorizacionSolicitudesSrv.crearTxn(request);
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
            com.keralty.aeconfiguraciones.viewmodel.priorizacionsolicitudes.siu.CrearRequest request) throws ModelException {
        return priorizacionSolicitudesSrv.crearImplementation(request);
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
            com.keralty.aeconfiguraciones.viewmodel.priorizacionsolicitudes.siu.CrearRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Long priorizacionSolicitudescrearpatrEstadoSolicitud = request.getPriorizacionSolicitudescrearpatrEstadoSolicitud();
        Long priorizacionSolicitudescrearpatrEstadoDoc = request.getPriorizacionSolicitudescrearpatrEstadoDoc();
        Long priorizacionSolicitudescrearpatrPrioridad = request.getPriorizacionSolicitudescrearpatrPrioridad();

        request.checkArguments();
        PriorizacionSolicitudes instance = new PriorizacionSolicitudes();
        instance.setPriorizacionSolicitudesEstadoSolicitud(priorizacionSolicitudescrearpatrEstadoSolicitud);
        instance.setPriorizacionSolicitudesEstadoDoc(priorizacionSolicitudescrearpatrEstadoDoc);
        instance.setPriorizacionSolicitudesPrioridad(priorizacionSolicitudescrearpatrPrioridad);
        if (priorizacionSolicitudesSrv.getByOID(instance.getOid()) != null) {
            throw new InstanceExistsException(PriorizacionSolicitudesConstants.CLASS_ALIAS);
        }

        instance.setOid(instance.getOid());

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
            com.keralty.aeconfiguraciones.viewmodel.priorizacionsolicitudes.siu.EliminarRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = priorizacionSolicitudesSrv.eliminarTxn(request);
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
            com.keralty.aeconfiguraciones.viewmodel.priorizacionsolicitudes.siu.EliminarRequest request) throws ModelException {
        request.setPriorizacionSolicitudeseliminarpthisPriorizacionSolicitudesInstance(priorizacionSolicitudesSrv.getByOIDWithHV(request.getPriorizacionSolicitudeseliminarpthisPriorizacionSolicitudes()));
        return priorizacionSolicitudesSrv.eliminarImplementation(request);
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
            com.keralty.aeconfiguraciones.viewmodel.priorizacionsolicitudes.siu.EliminarRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        PriorizacionSolicitudes priorizacionSolicitudeseliminarpthisPriorizacionSolicitudes = request.getPriorizacionSolicitudeseliminarpthisPriorizacionSolicitudesInstance();

        request.checkArguments();
        PriorizacionSolicitudes instance = priorizacionSolicitudeseliminarpthisPriorizacionSolicitudes;
        if(priorizacionSolicitudeseliminarpthisPriorizacionSolicitudes.getLogPriorizacionSol().size() > 0) {
            throw new MinCardinalityException(PriorizacionSolicitudesConstants.CLASS_ALIAS, PriorizacionSolicitudesConstants.ROLE_ALIAS_LOGPRIORIZACIONSOL);
        }


        repository.delete(instance);
        
        response.setInstance(instance);

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
            com.keralty.aeconfiguraciones.viewmodel.priorizacionsolicitudes.siu.ModificarRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = priorizacionSolicitudesSrv.modificarTxn(request);
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
            com.keralty.aeconfiguraciones.viewmodel.priorizacionsolicitudes.siu.ModificarRequest request) throws ModelException {
        request.setPriorizacionSolicitudesmodificarpthisPriorizacionSolicitudesInstance(priorizacionSolicitudesSrv.getByOIDWithHV(request.getPriorizacionSolicitudesmodificarpthisPriorizacionSolicitudes()));
        return priorizacionSolicitudesSrv.modificarImplementation(request);
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
            com.keralty.aeconfiguraciones.viewmodel.priorizacionsolicitudes.siu.ModificarRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        PriorizacionSolicitudes priorizacionSolicitudesmodificarpthisPriorizacionSolicitudes = request.getPriorizacionSolicitudesmodificarpthisPriorizacionSolicitudesInstance();
        Long priorizacionSolicitudesmodificarpPrioridad = request.getPriorizacionSolicitudesmodificarpPrioridad();

        request.checkArguments();
        PriorizacionSolicitudes instance = priorizacionSolicitudesmodificarpthisPriorizacionSolicitudes;
        instance.setPriorizacionSolicitudesPrioridad(priorizacionSolicitudesmodificarpPrioridad);


        response.setInstance(repository.save(instance));

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
            com.keralty.aeconfiguraciones.viewmodel.priorizacionsolicitudes.siu.InsPriorizacionSolicitudesRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = priorizacionSolicitudesSrv.insPriorizacionSolicitudesTxn(request);
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
            com.keralty.aeconfiguraciones.viewmodel.priorizacionsolicitudes.siu.InsPriorizacionSolicitudesRequest request) throws ModelException {
        request.setPriorizacionSolicitudesInsPriorizacionSolicitudespthisPriorizacionSolicitudesInstance(priorizacionSolicitudesSrv.getByOIDWithHV(request.getPriorizacionSolicitudesInsPriorizacionSolicitudespthisPriorizacionSolicitudes()));
        request.setPriorizacionSolicitudesInsPriorizacionSolicitudespevcLogPriorizacionSolInstance(logPriorizacionSolSrv.getByOIDWithHV(request.getPriorizacionSolicitudesInsPriorizacionSolicitudespevcLogPriorizacionSol()));
        return priorizacionSolicitudesSrv.insPriorizacionSolicitudesImplementation(request, true);
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
            com.keralty.aeconfiguraciones.viewmodel.priorizacionsolicitudes.siu.InsPriorizacionSolicitudesRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        PriorizacionSolicitudes priorizacionSolicitudesInsPriorizacionSolicitudespthisPriorizacionSolicitudes = request.getPriorizacionSolicitudesInsPriorizacionSolicitudespthisPriorizacionSolicitudesInstance();
        LogPriorizacionSol priorizacionSolicitudesInsPriorizacionSolicitudespevcLogPriorizacionSol = request.getPriorizacionSolicitudesInsPriorizacionSolicitudespevcLogPriorizacionSolInstance();

        request.checkArguments();
        PriorizacionSolicitudes instance = priorizacionSolicitudesInsPriorizacionSolicitudespthisPriorizacionSolicitudes;

        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.logpriorizacionsol.siu.InsPriorizacionSolicitudesRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.logpriorizacionsol.siu.InsPriorizacionSolicitudesRequest();
            relatedRequest.setLogPriorizacionSolInsPriorizacionSolicitudespthisLogPriorizacionSolInstance(priorizacionSolicitudesInsPriorizacionSolicitudespevcLogPriorizacionSol);
            relatedRequest.setLogPriorizacionSolInsPriorizacionSolicitudespevcPriorizacionSolicitudesInstance(priorizacionSolicitudesInsPriorizacionSolicitudespthisPriorizacionSolicitudes);
            logPriorizacionSolSrv.insPriorizacionSolicitudesImplementation(relatedRequest, false);
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
            com.keralty.aeconfiguraciones.viewmodel.priorizacionsolicitudes.siu.DelPriorizacionSolicitudesRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = priorizacionSolicitudesSrv.delPriorizacionSolicitudesTxn(request);
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
            com.keralty.aeconfiguraciones.viewmodel.priorizacionsolicitudes.siu.DelPriorizacionSolicitudesRequest request) throws ModelException {
        request.setPriorizacionSolicitudesDelPriorizacionSolicitudespthisPriorizacionSolicitudesInstance(priorizacionSolicitudesSrv.getByOIDWithHV(request.getPriorizacionSolicitudesDelPriorizacionSolicitudespthisPriorizacionSolicitudes()));
        request.setPriorizacionSolicitudesDelPriorizacionSolicitudespevcLogPriorizacionSolInstance(logPriorizacionSolSrv.getByOIDWithHV(request.getPriorizacionSolicitudesDelPriorizacionSolicitudespevcLogPriorizacionSol()));
        return priorizacionSolicitudesSrv.delPriorizacionSolicitudesImplementation(request, true);
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
            com.keralty.aeconfiguraciones.viewmodel.priorizacionsolicitudes.siu.DelPriorizacionSolicitudesRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        PriorizacionSolicitudes priorizacionSolicitudesDelPriorizacionSolicitudespthisPriorizacionSolicitudes = request.getPriorizacionSolicitudesDelPriorizacionSolicitudespthisPriorizacionSolicitudesInstance();
        LogPriorizacionSol priorizacionSolicitudesDelPriorizacionSolicitudespevcLogPriorizacionSol = request.getPriorizacionSolicitudesDelPriorizacionSolicitudespevcLogPriorizacionSolInstance();

        request.checkArguments();
        PriorizacionSolicitudes instance = priorizacionSolicitudesDelPriorizacionSolicitudespthisPriorizacionSolicitudes;

        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.logpriorizacionsol.siu.DelPriorizacionSolicitudesRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.logpriorizacionsol.siu.DelPriorizacionSolicitudesRequest();
            relatedRequest.setLogPriorizacionSolDelPriorizacionSolicitudespthisLogPriorizacionSolInstance(priorizacionSolicitudesDelPriorizacionSolicitudespevcLogPriorizacionSol);
            relatedRequest.setLogPriorizacionSolDelPriorizacionSolicitudespevcPriorizacionSolicitudesInstance(priorizacionSolicitudesDelPriorizacionSolicitudespthisPriorizacionSolicitudes);
            logPriorizacionSolSrv.delPriorizacionSolicitudesImplementation(relatedRequest, false);
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
            com.keralty.aeconfiguraciones.viewmodel.priorizacionsolicitudes.siu.TCREARRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = priorizacionSolicitudesSrv.tCREARTxn(request);
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
            com.keralty.aeconfiguraciones.viewmodel.priorizacionsolicitudes.siu.TCREARRequest request) throws ModelException {
        return priorizacionSolicitudesSrv.tCREARImplementation(request);
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
            com.keralty.aeconfiguraciones.viewmodel.priorizacionsolicitudes.siu.TCREARRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Long priorizacionSolicitudesTCREARptpatrEstadoSolicitud = request.getPriorizacionSolicitudesTCREARptpatrEstadoSolicitud();
        Long priorizacionSolicitudesTCREARptpatrEstadoDoc = request.getPriorizacionSolicitudesTCREARptpatrEstadoDoc();
        Long priorizacionSolicitudesTCREARptpatrPrioridad = request.getPriorizacionSolicitudesTCREARptpatrPrioridad();

        request.checkArguments();
        PriorizacionSolicitudes instance;

        com.keralty.aeconfiguraciones.viewmodel.priorizacionsolicitudes.siu.CrearRequest step1Request = new com.keralty.aeconfiguraciones.viewmodel.priorizacionsolicitudes.siu.CrearRequest();
        step1Request.setPriorizacionSolicitudescrearpatrEstadoSolicitud(priorizacionSolicitudesTCREARptpatrEstadoSolicitud);
        step1Request.setPriorizacionSolicitudescrearpatrEstadoDoc(priorizacionSolicitudesTCREARptpatrEstadoDoc);
        step1Request.setPriorizacionSolicitudescrearpatrPrioridad(priorizacionSolicitudesTCREARptpatrPrioridad);
        instance = (PriorizacionSolicitudes) priorizacionSolicitudesSrv.crearImplementation(step1Request).getInstance();

        com.keralty.aeconfiguraciones.viewmodel.logpriorizacionsol.siu.TCREARRequest step2Request = new com.keralty.aeconfiguraciones.viewmodel.logpriorizacionsol.siu.TCREARRequest();
        step2Request.setLogPriorizacionSolTCREARptpagrPriorizacionSolicitudeInstance(instance);
        step2Request.setLogPriorizacionSolTCREARptpatrEstadoSolicitud(instance.getPriorizacionSolicitudesEstadoSolicitud());
        step2Request.setLogPriorizacionSolTCREARptpatrEstadoDoc(instance.getPriorizacionSolicitudesEstadoDoc());
        step2Request.setLogPriorizacionSolTCREARptpatrPrioridad(instance.getPriorizacionSolicitudesPrioridad());
        logPriorizacionSolSrv.tCREARImplementation(step2Request);

        response.setInstance(getByOID(instance.getOid()));

        return response;
    }

    // Service: TELIMINAR

    /*
     * Entry point for service TELIMINAR
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse tELIMINAR(
            com.keralty.aeconfiguraciones.viewmodel.priorizacionsolicitudes.siu.TELIMINARRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = priorizacionSolicitudesSrv.tELIMINARTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service TELIMINAR
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse tELIMINARTxn(
            com.keralty.aeconfiguraciones.viewmodel.priorizacionsolicitudes.siu.TELIMINARRequest request) throws ModelException, SystemException {
        request.setPriorizacionSolicitudesTELIMINARpthisPriorizacionSolicitudesInstance(priorizacionSolicitudesSrv.getByOIDWithHV(request.getPriorizacionSolicitudesTELIMINARpthisPriorizacionSolicitudes()));
        return priorizacionSolicitudesSrv.tELIMINARImplementation(request);
    }

    /*
     * Implements service TELIMINAR
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse tELIMINARImplementation(
            com.keralty.aeconfiguraciones.viewmodel.priorizacionsolicitudes.siu.TELIMINARRequest request) throws ModelException, SystemException {
        ServiceResponse response = new ServiceResponse();
        PriorizacionSolicitudes priorizacionSolicitudesTELIMINARpthisPriorizacionSolicitudes = request.getPriorizacionSolicitudesTELIMINARpthisPriorizacionSolicitudesInstance();

        request.checkArguments();
        PriorizacionSolicitudes instance = priorizacionSolicitudesTELIMINARpthisPriorizacionSolicitudes;

        for(LogPriorizacionSol step1Entity : repository.assocOperator000(priorizacionSolicitudesTELIMINARpthisPriorizacionSolicitudes)) {
            com.keralty.aeconfiguraciones.viewmodel.logpriorizacionsol.siu.EliminarRequest step1Request = new com.keralty.aeconfiguraciones.viewmodel.logpriorizacionsol.siu.EliminarRequest();
            step1Request.setLogPriorizacionSoleliminarpthisLogPriorizacionSolInstance(step1Entity);
            logPriorizacionSolSrv.eliminarImplementation(step1Request);
        }

        com.keralty.aeconfiguraciones.viewmodel.priorizacionsolicitudes.siu.EliminarRequest step2Request = new com.keralty.aeconfiguraciones.viewmodel.priorizacionsolicitudes.siu.EliminarRequest();
        step2Request.setPriorizacionSolicitudeseliminarpthisPriorizacionSolicitudesInstance(priorizacionSolicitudesTELIMINARpthisPriorizacionSolicitudes);
        priorizacionSolicitudesSrv.eliminarImplementation(step2Request);

        response.setInstance(getByOID(instance.getOid()));

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
            com.keralty.aeconfiguraciones.viewmodel.priorizacionsolicitudes.siu.TMODIFICARRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = priorizacionSolicitudesSrv.tMODIFICARTxn(request);
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
            com.keralty.aeconfiguraciones.viewmodel.priorizacionsolicitudes.siu.TMODIFICARRequest request) throws ModelException {
        request.setPriorizacionSolicitudesTMODIFICARpthisPriorizacionSolicitudesInstance(priorizacionSolicitudesSrv.getByOIDWithHV(request.getPriorizacionSolicitudesTMODIFICARpthisPriorizacionSolicitudes()));
        return priorizacionSolicitudesSrv.tMODIFICARImplementation(request);
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
            com.keralty.aeconfiguraciones.viewmodel.priorizacionsolicitudes.siu.TMODIFICARRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Long priorizacionSolicitudesTMODIFICARptpPrioridad = request.getPriorizacionSolicitudesTMODIFICARptpPrioridad();
        PriorizacionSolicitudes priorizacionSolicitudesTMODIFICARpthisPriorizacionSolicitudes = request.getPriorizacionSolicitudesTMODIFICARpthisPriorizacionSolicitudesInstance();

        request.checkArguments();
        PriorizacionSolicitudes instance = priorizacionSolicitudesTMODIFICARpthisPriorizacionSolicitudes;

        /* Action 1: IF (Prioridad <> ptpPrioridad) */
        boolean ifCondition1 = !(priorizacionSolicitudesTMODIFICARpthisPriorizacionSolicitudes.getPriorizacionSolicitudesPrioridad()).equals(priorizacionSolicitudesTMODIFICARptpPrioridad);
        if (ifCondition1) {
            com.keralty.aeconfiguraciones.viewmodel.logpriorizacionsol.siu.TCREARRequest step10Request = new com.keralty.aeconfiguraciones.viewmodel.logpriorizacionsol.siu.TCREARRequest();
            step10Request.setLogPriorizacionSolTCREARptpagrPriorizacionSolicitudeInstance(priorizacionSolicitudesTMODIFICARpthisPriorizacionSolicitudes);
            step10Request.setLogPriorizacionSolTCREARptpatrEstadoSolicitud(priorizacionSolicitudesTMODIFICARpthisPriorizacionSolicitudes.getPriorizacionSolicitudesEstadoSolicitud());
            step10Request.setLogPriorizacionSolTCREARptpatrEstadoDoc(priorizacionSolicitudesTMODIFICARpthisPriorizacionSolicitudes.getPriorizacionSolicitudesEstadoDoc());
            step10Request.setLogPriorizacionSolTCREARptpatrPrioridad(priorizacionSolicitudesTMODIFICARptpPrioridad);
            logPriorizacionSolSrv.tCREARImplementation(step10Request);
        }

        com.keralty.aeconfiguraciones.viewmodel.priorizacionsolicitudes.siu.ModificarRequest step2Request = new com.keralty.aeconfiguraciones.viewmodel.priorizacionsolicitudes.siu.ModificarRequest();
        step2Request.setPriorizacionSolicitudesmodificarpthisPriorizacionSolicitudesInstance(priorizacionSolicitudesTMODIFICARpthisPriorizacionSolicitudes);
        step2Request.setPriorizacionSolicitudesmodificarpPrioridad(priorizacionSolicitudesTMODIFICARptpPrioridad);
        priorizacionSolicitudesSrv.modificarImplementation(step2Request);

        response.setInstance(getByOID(instance.getOid()));

        return response;
    }

    @Override
    protected void applyOrderCriteria(QueryRequest queryRequest, SQLSelect select) {
        applyOCOCPorEstados(queryRequest, select);
        applyDSItemSort(queryRequest, select);
        applyDefaultOrderCriteria(queryRequest, select);
    }

    private void applyDefaultOrderCriteria(QueryRequest queryRequest, SQLSelect select) {
        if (queryRequest.getOrderCriteriaName() != null && !queryRequest.getOrderCriteriaName().isBlank())
            return;
        if (queryRequest.getDsItemSort() != null && !queryRequest.getDsItemSort().isBlank())
            return;
        select.getOrderByPart().addItem(Constants.normalizePath(PriorizacionSolicitudesConstants.CLASS_NAME, PriorizacionSolicitudesConstants.ATTR_FIELD_PRIORIZACIONSOLICITUDESESTADOSOLICITUD, false), true);
        select.getOrderByPart().addItem(Constants.normalizePath(PriorizacionSolicitudesConstants.CLASS_NAME, PriorizacionSolicitudesConstants.ATTR_FIELD_PRIORIZACIONSOLICITUDESESTADODOC, false), true);
    }


    private void applyOCOCPorEstados(QueryRequest queryRequest, SQLSelect select) {
        if ("OC_PorEstados".equalsIgnoreCase(queryRequest.getOrderCriteriaName())) {
            select.getOrderByPart().addItem(Constants.normalizePath(PriorizacionSolicitudesConstants.CLASS_NAME, PriorizacionSolicitudesConstants.ATTR_NAME_PRIORIZACIONSOLICITUDESESTADOSOLICITUD, false), true);
            select.getOrderByPart().addItem(Constants.normalizePath(PriorizacionSolicitudesConstants.CLASS_NAME, PriorizacionSolicitudesConstants.ATTR_NAME_PRIORIZACIONSOLICITUDESESTADODOC, false), true);
        }
    }
    
    @Override
    protected AbstractAppEntity buildRelatedInstance(String relatedInstanceJsonOid, String relatedInstancePathFirstItem, String remainingPath) {
        AbstractAppEntity relatedInstance = null;
        if (relatedInstancePathFirstItem.isEmpty()) {
            relatedInstance = buildPriorizacionSolicitudesRelatedInstance(relatedInstanceJsonOid);
        }
        if (PriorizacionSolicitudesConstants.ROLE_NAME_LOGPRIORIZACIONSOL.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildPriorizacionSolicitudesRelatedInstance4LogPriorizacionSol(relatedInstanceJsonOid, remainingPath);
        }
        return relatedInstance;
    }
    
    private AbstractAppEntity buildPriorizacionSolicitudesRelatedInstance(String relatedInstanceJsonOid) {
    	ObjectMapper mapper = new ObjectMapper();
    	try {
    		return new PriorizacionSolicitudes(mapper.readValue(relatedInstanceJsonOid, PriorizacionSolicitudesOid.class));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            throw new SystemException(e.getMessage());
    	}
    }

    private AbstractAppEntity buildPriorizacionSolicitudesRelatedInstance4LogPriorizacionSol(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new LogPriorizacionSol(mapper.readValue(relatedInstanceJsonOid, LogPriorizacionSolOid.class)): LogPriorizacionSol.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            throw new SystemException(e.getMessage());
    	}
    }

    @Override
    protected void applyFilterByOid(String jsonOid, SQLSelect sql) {
        try {
            PriorizacionSolicitudesOid oid = new PriorizacionSolicitudesOid().fromJsonString(jsonOid);
            SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
            comparison1.setOperator("=");
            comparison1.setFirstOperand(new SQLWhereOperandPathAttribute(PriorizacionSolicitudesConstants.ATTR_FIELD_PRIORIZACIONSOLICITUDESESTADOSOLICITUD, Constants.Type.INT.getTypeName()));
            comparison1.setSecondOperand(new SQLWhereOperandLiteral(oid.getPriorizacionSolicitudesEstadoSolicitud(), Constants.Type.INT.getTypeName()));
            sql.addComparison(comparison1);
            SQLWhereComparison comparison2 = new SQLWhereComparison(sql);
            comparison2.setOperator("=");
            comparison2.setFirstOperand(new SQLWhereOperandPathAttribute(PriorizacionSolicitudesConstants.ATTR_FIELD_PRIORIZACIONSOLICITUDESESTADODOC, Constants.Type.NAT.getTypeName()));
            comparison2.setSecondOperand(new SQLWhereOperandLiteral(oid.getPriorizacionSolicitudesEstadoDoc(), Constants.Type.NAT.getTypeName()));
            sql.addComparison(comparison2);
        } catch (Exception e) {
            LoggerFactory.getLogger(PriorizacionSolicitudesService.class).error(e.getMessage());
            throw new SystemException(e.getMessage());
        }
    }

}
