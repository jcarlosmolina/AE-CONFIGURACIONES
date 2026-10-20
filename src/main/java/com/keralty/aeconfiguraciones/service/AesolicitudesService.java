package com.keralty.aeconfiguraciones.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.keralty.aeconfiguraciones.exception.ModelException;
import com.keralty.aeconfiguraciones.exception.SystemException;
import com.keralty.aeconfiguraciones.global.AesolicitudesConstants;
import com.keralty.aeconfiguraciones.global.AsesorConstants;
import com.keralty.aeconfiguraciones.global.Constants;
import com.keralty.aeconfiguraciones.global.FuncionarioConstants;
import com.keralty.aeconfiguraciones.global.SolicitudConstants;
import com.keralty.aeconfiguraciones.global.UserFunctions;
import com.keralty.aeconfiguraciones.persistence.AbstractAppEntity;
import com.keralty.aeconfiguraciones.persistence.Aesolicitudes;
import com.keralty.aeconfiguraciones.persistence.Asesor;
import com.keralty.aeconfiguraciones.persistence.EstadoContrato;
import com.keralty.aeconfiguraciones.persistence.oid.AesolicitudesOid;
import com.keralty.aeconfiguraciones.persistence.oid.EstadoContratoOid;
import com.keralty.aeconfiguraciones.persistence.oid.PlanesOid;
import com.keralty.aeconfiguraciones.persistence.oid.ProductosOid;
import com.keralty.aeconfiguraciones.persistence.oid.SolicitudOid;
import com.keralty.aeconfiguraciones.persistence.Planes;
import com.keralty.aeconfiguraciones.persistence.Productos;
import com.keralty.aeconfiguraciones.persistence.Solicitud;
import com.keralty.aeconfiguraciones.repository.AesolicitudesJpaRepository;
import com.keralty.aeconfiguraciones.sqlutils.SQLSelect;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereComparison;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereConjunction;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereDisjunction;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereOperandLiteral;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereOperandOVParameter;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereOperandPathAttribute;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereOperandPathInstance;
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
public class AesolicitudesService extends AbstractService {

    AesolicitudesJpaRepository repository;

    AesolicitudesService aesolicitudesSrv;

    EstadoContratoService estadoContratoSrv;

    SolicitudService solicitudSrv;

    ProductosService productosSrv;

    PlanesService planesSrv;

    UserFunctions userFunctionsSrv;

    @Inject
    public AesolicitudesService(
            AesolicitudesService aesolicitudesSrv,
            EstadoContratoService estadoContratoSrv,
            SolicitudService solicitudSrv,
            ProductosService productosSrv,
            PlanesService planesSrv,
            UserFunctions userFunctionsSrv,
            AesolicitudesJpaRepository repository) {
        this.aesolicitudesSrv = aesolicitudesSrv;
        this.estadoContratoSrv = estadoContratoSrv;
        this.solicitudSrv = solicitudSrv;
        this.productosSrv = productosSrv;
        this.planesSrv = planesSrv;
        this.userFunctionsSrv = userFunctionsSrv;
        this.repository = repository;
    }

    public List<Map<String, Object>> solveQuery(QueryRequest request) {
        return solveGenericQuery(request, AesolicitudesConstants.CLASS_NAME);
    }

    private List<Aesolicitudes> solveAesolicitudesTypedQuery(QueryRequest request) {
        return repository.solveQuery(processQueryRequest(request, AesolicitudesConstants.CLASS_NAME), request.getPageSize(), request.getPageNumber());
    }
    
    private int solveAesolicitudesTypedCountQuery(QueryRequest request) {
        return repository.solveCountQuery(processCountQueryRequest(request, AesolicitudesConstants.CLASS_NAME));
    }

    /*
     * Returns the population of class Aesolicitudes
     *
     * @return Population of class Aesolicitudes
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<Aesolicitudes> get() {
        List<Aesolicitudes> results = new java.util.ArrayList<>();
        repository.findAll().forEach(results::add);
        return results;
    }
    /*
     * Returns the population of class Aesolicitudes that matches certain search criteria
     *
     * @param queryRequest Search criteria
     *
     * @return Population of class Aesolicitudes matching the search criteria
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<Aesolicitudes> get(QueryRequest queryRequest) {
        return solveAesolicitudesTypedQuery(queryRequest);
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
            return new PopulationResponseViewModel(solveAesolicitudesTypedQuery(queryRequest).stream()
                    .map(instance -> buildDynamicObject(instance, queryRequest.getDisplaySetItems()))
                    .collect(Collectors.toList()), queryRequest.getPageSize() != 0 ? solveAesolicitudesTypedCountQuery(queryRequest) : 0);
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
        return new PopulationResponseViewModel(solveAesolicitudesTypedQuery(queryRequest).stream()
                .map(instance -> buildDynamicObject(instance, displaySetItems))
                .collect(Collectors.toList()));
    }

    /*
     * Returns an instance of Aesolicitudes by ID
     *
     * @param aesolicitudesidsolicitud Identification field aesolicitudesidsolicitud
     *
     * @return Instance of Aesolicitudes
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public Aesolicitudes getById(Long aesolicitudesidsolicitud) {
        return getByOID(new AesolicitudesOid(aesolicitudesidsolicitud));
    }
    
    /*
     * Returns an instance of Aesolicitudes by OID
     *
     * @param oid Object IDentifier
     *
     * @return Instance of Aesolicitudes
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public Aesolicitudes getByOID(AesolicitudesOid oid) {
        Optional<Aesolicitudes> instance = getInstanceByOid(oid);
        return instance.isPresent() ? instance.get() : null;
    }
    
    /*
     * Returns an instance of Aesolicitudes by OID applying horizontal visibility constraints
     *
     * @param oid Object IDentifier
     *
     * @return Instance of Aesolicitudes
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public Aesolicitudes getByOIDWithHV(AesolicitudesOid oid) {
        Optional<Aesolicitudes> instance = getInstanceByOid(oid);
    	return instance.isPresent() && instance.get().checkHorizontalVisibility(getAgentInstance()) 
    		 ? instance.get() 
    		 : null;
    }
    
    private Optional<Aesolicitudes> getInstanceByOid(AesolicitudesOid oid) {
        return oid != null && oid.getAesolicitudesidsolicitud() != null
               ? repository.findById(oid.getAesolicitudesidsolicitud())
               : Optional.empty();
    }

    /*
     * Returns an instance of Aesolicitudes by OID in JSON format
     *
     * @param jsonOid Object IDentifier in JSON format
     *
     * @return Instance of Aesolicitudes
     */
    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public Aesolicitudes getByJsonOid(String jsonOid) {
        AesolicitudesOid oid = new AesolicitudesOid();
        oid.fromJsonString(jsonOid);
        return getByOIDWithHV(oid);
    }

    /*
     * Returns the list of instances of Aesolicitudes related to a given instance through 
     * a given path
     *
     * @param relatedInstance Related instance
     *
     * @param relatedInstancePath Related instance path
     *
     * @return List of instances of Aesolicitudes
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<Aesolicitudes> getByRelated(AbstractAppEntity relatedInstance, String relatedInstancePath) {
        QueryRequest queryRequest = new QueryRequest(new FilterRequest(Constants.FILTER_BY_RELATED,
                Arrays.asList(new FilterVariableRequest(Constants.FILTER_BY_RELATED_FVAR_PATH, relatedInstancePath),
                        new FilterVariableRequest(Constants.FILTER_BY_RELATED_FVAR_OID, relatedInstance.getOid().toJson()))));
        return solveAesolicitudesTypedQuery(queryRequest);
    }

    /*
     * Returns the list of instances of Aesolicitudes related to a given instance through
     * a given path
     *
     * @param relatedInstance Related instance
     *
     * @param relatedInstancePath Related instance path
     *
     * @return List of instances of Aesolicitudes
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<Aesolicitudes> getInstancesByRelated(AbstractAppEntity relatedInstance, String relatedInstancePath) {
        return getByRelated(relatedInstance, relatedInstancePath);
    }

    /*
     * Returns a set of displayset items for an instance of Aesolicitudes
     *
     * @param oid Object IDentifier
     *
     * @param displaySetItems List of comma-separated displayset items
     *
     * @return Set of displayset items for an instance of Aesolicitudes
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public InstanceResponseViewModel getAesolicitudesDynamic(AesolicitudesOid oid, String displaySetItems) {
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
    public String getSuppInfo(AesolicitudesOid oid, String dsName) {
        Aesolicitudes instance = getByOIDWithHV(oid);
        return (instance != null ? getSuppInfo(instance, dsName): "");
    }

    private String getSuppInfo(Aesolicitudes instance, String dsName) {
        if (instance == null || instance.isNull()) return "";
        return dsName.isEmpty() ? instance.getOid().toJson() : "";
    }




    // IIU Methods


    /*
     * Solves the query needed for PIUAesolicitudesObs
     *
     * @param queryRequest Query request
     * @return List of viewmodels for Aesolicitudes using displayset DSAesolicitudes that 
     * match the search criteria
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<com.keralty.aeconfiguraciones.viewmodel.aesolicitudes.ds.DSAesolicitudes> piuPIUAesolicitudesObs(QueryRequest queryRequest) {
        return solveAesolicitudesTypedQuery(queryRequest).stream()
                .map(com.keralty.aeconfiguraciones.viewmodel.aesolicitudes.ds.DSAesolicitudes::new)
                .collect(Collectors.toList());
    }

    /*
     * Solves the COUNT query needed for PIUAesolicitudesObs
     *
     * @param queryRequest Query request
     * @returns Count of instances for PIUAesolicitudesObs
     */
    public int getCount4PIUAesolicitudesObs(QueryRequest queryRequest) {
        return solveAesolicitudesTypedQuery(queryRequest).size();
    }

    // Service: InsESTADOSOLICITUD

    /*
     * Entry point for service InsESTADOSOLICITUD
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse insESTADOSOLICITUD(
            com.keralty.aeconfiguraciones.viewmodel.aesolicitudes.siu.InsESTADOSOLICITUDRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = aesolicitudesSrv.insESTADOSOLICITUDTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service InsESTADOSOLICITUD
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse insESTADOSOLICITUDTxn(
            com.keralty.aeconfiguraciones.viewmodel.aesolicitudes.siu.InsESTADOSOLICITUDRequest request) throws ModelException {
        request.setAesolicitudesInsESTADOSOLICITUDpthisAESOLICITUDESInstance(aesolicitudesSrv.getByOIDWithHV(request.getAesolicitudesInsESTADOSOLICITUDpthisAESOLICITUDES()));
        request.setAesolicitudesInsESTADOSOLICITUDpevcEstadoContratoInstance(estadoContratoSrv.getByOIDWithHV(request.getAesolicitudesInsESTADOSOLICITUDpevcEstadoContrato()));
        return aesolicitudesSrv.insESTADOSOLICITUDImplementation(request, true);
    }

    /*
     * Implements service InsESTADOSOLICITUD
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse insESTADOSOLICITUDImplementation(
            com.keralty.aeconfiguraciones.viewmodel.aesolicitudes.siu.InsESTADOSOLICITUDRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Aesolicitudes aesolicitudesInsESTADOSOLICITUDpthisAESOLICITUDES = request.getAesolicitudesInsESTADOSOLICITUDpthisAESOLICITUDESInstance();
        EstadoContrato aesolicitudesInsESTADOSOLICITUDpevcEstadoContrato = request.getAesolicitudesInsESTADOSOLICITUDpevcEstadoContratoInstance();

        request.checkArguments();
        Aesolicitudes instance = aesolicitudesInsESTADOSOLICITUDpthisAESOLICITUDES;

        instance.add2ESTADOSOLICITUD(aesolicitudesInsESTADOSOLICITUDpevcEstadoContrato);
        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.estadocontrato.siu.InsESTADOSOLICITUDRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.estadocontrato.siu.InsESTADOSOLICITUDRequest();
            relatedRequest.setEstadoContratoInsESTADOSOLICITUDpthisEstadoContratoInstance(aesolicitudesInsESTADOSOLICITUDpevcEstadoContrato);
            relatedRequest.setEstadoContratoInsESTADOSOLICITUDpevcAesolicitudesInstance(aesolicitudesInsESTADOSOLICITUDpthisAESOLICITUDES);
            estadoContratoSrv.insESTADOSOLICITUDImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));

        return response;
    }

    // Service: DelESTADOSOLICITUD

    /*
     * Entry point for service DelESTADOSOLICITUD
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse delESTADOSOLICITUD(
            com.keralty.aeconfiguraciones.viewmodel.aesolicitudes.siu.DelESTADOSOLICITUDRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = aesolicitudesSrv.delESTADOSOLICITUDTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service DelESTADOSOLICITUD
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse delESTADOSOLICITUDTxn(
            com.keralty.aeconfiguraciones.viewmodel.aesolicitudes.siu.DelESTADOSOLICITUDRequest request) throws ModelException {
        request.setAesolicitudesDelESTADOSOLICITUDpthisAESOLICITUDESInstance(aesolicitudesSrv.getByOIDWithHV(request.getAesolicitudesDelESTADOSOLICITUDpthisAESOLICITUDES()));
        request.setAesolicitudesDelESTADOSOLICITUDpevcEstadoContratoInstance(estadoContratoSrv.getByOIDWithHV(request.getAesolicitudesDelESTADOSOLICITUDpevcEstadoContrato()));
        return aesolicitudesSrv.delESTADOSOLICITUDImplementation(request, true);
    }

    /*
     * Implements service DelESTADOSOLICITUD
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse delESTADOSOLICITUDImplementation(
            com.keralty.aeconfiguraciones.viewmodel.aesolicitudes.siu.DelESTADOSOLICITUDRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Aesolicitudes aesolicitudesDelESTADOSOLICITUDpthisAESOLICITUDES = request.getAesolicitudesDelESTADOSOLICITUDpthisAESOLICITUDESInstance();
        EstadoContrato aesolicitudesDelESTADOSOLICITUDpevcEstadoContrato = request.getAesolicitudesDelESTADOSOLICITUDpevcEstadoContratoInstance();

        request.checkArguments();
        Aesolicitudes instance = aesolicitudesDelESTADOSOLICITUDpthisAESOLICITUDES;

        instance.add2ESTADOSOLICITUD(null);
        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.estadocontrato.siu.DelESTADOSOLICITUDRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.estadocontrato.siu.DelESTADOSOLICITUDRequest();
            relatedRequest.setEstadoContratoDelESTADOSOLICITUDpthisEstadoContratoInstance(aesolicitudesDelESTADOSOLICITUDpevcEstadoContrato);
            relatedRequest.setEstadoContratoDelESTADOSOLICITUDpevcAesolicitudesInstance(aesolicitudesDelESTADOSOLICITUDpthisAESOLICITUDES);
            estadoContratoSrv.delESTADOSOLICITUDImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));

        return response;
    }


    @Override
    protected void applyHVFilter(SQLSelect sql) throws SystemException {
        SQLWhereConjunction conjunction = new SQLWhereConjunction();
        SQLWhereDisjunction disjunction = new SQLWhereDisjunction();
        if (getAgentRoles().contains(AsesorConstants.CLASS_NAME)) applyHVFilter4Asesor(sql, disjunction);
        conjunction.addDisjunction(disjunction);
        sql.addConjunction(conjunction);
    }

    private void applyHVFilter4Asesor(SQLSelect sql, SQLWhereDisjunction disjunction) {
        // Solicitud.Agente = agent OR Solicitud.Agente.CodJefe = Agent.Usuario
        Asesor agentInstance = getAgentAsesor();
    {
        // 'or' Logical operator
        SQLWhereDisjunction comparison1 = new SQLWhereDisjunction();
    
        // Operand 1
            // '=' Comparison
            SQLWhereComparison comparison2 = new SQLWhereComparison(sql);
            comparison2.setOperator("=");
            
            // Operand 1
            SQLWhereOperandPathInstance operand113 = new SQLWhereOperandPathInstance(AesolicitudesConstants.ROLE_NAME_SOLICITUD + "." + SolicitudConstants.ROLE_NAME_AGENTE, FuncionarioConstants.CLASS_NAME);
            comparison2.setFirstOperand(operand113);
            
            // Operand 2
            SQLWhereOperandOVParameter operand224 = new SQLWhereOperandOVParameter(Constants.AGENTKEYWORD, AsesorConstants.CLASS_NAME, AsesorConstants.CLASS_NAME, agentInstance.getOid(), Constants.AGENTKEYWORD);
            comparison2.setSecondOperand(operand224);
            comparison1.addOperand(comparison2);
    
        // Operand 2
            // '=' Comparison
            SQLWhereComparison comparison4 = new SQLWhereComparison(sql);
            comparison4.setOperator("=");
            
            // Operand 1
            SQLWhereOperandPathAttribute operand115 = new SQLWhereOperandPathAttribute(AesolicitudesConstants.ROLE_NAME_SOLICITUD + "." + SolicitudConstants.ROLE_NAME_AGENTE + "." + FuncionarioConstants.ATTR_NAME_FUNCIONARIOCODJEFE, Constants.Type.STRING.getTypeName());
            comparison4.setFirstOperand(operand115);
            
            // Operand 2
            comparison4.setSecondOperand(new SQLWhereOperandLiteral(agentInstance.getFuncionarioUsuario(), Constants.Type.STRING.getTypeName()));
            comparison1.addOperand(comparison4);
        
        disjunction.addOperand(comparison1);
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
        select.getOrderByPart().addItem(Constants.normalizePath(AesolicitudesConstants.CLASS_NAME, AesolicitudesConstants.ATTR_FIELD_AESOLICITUDESIDSOLICITUD, false), true);
    }
    
    @Override
    protected AbstractAppEntity buildRelatedInstance(String relatedInstanceJsonOid, String relatedInstancePathFirstItem, String remainingPath) {
        AbstractAppEntity relatedInstance = null;
        if (relatedInstancePathFirstItem.isEmpty()) {
            relatedInstance = buildAesolicitudesRelatedInstance(relatedInstanceJsonOid);
        }
        if (AesolicitudesConstants.ROLE_NAME_SOLICITUD.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildAesolicitudesRelatedInstance4Solicitud(relatedInstanceJsonOid, remainingPath);
        }
        if (AesolicitudesConstants.ROLE_NAME_PRODUCTOT.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildAesolicitudesRelatedInstance4Productos(relatedInstanceJsonOid, remainingPath);
        }
        if (AesolicitudesConstants.ROLE_NAME_PLANT.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildAesolicitudesRelatedInstance4Planes(relatedInstanceJsonOid, remainingPath);
        }
        if (AesolicitudesConstants.ROLE_NAME_ESTADOSOLICITUD.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildAesolicitudesRelatedInstance4EstadoContrato(relatedInstanceJsonOid, remainingPath);
        }
        return relatedInstance;
    }
    
    private AbstractAppEntity buildAesolicitudesRelatedInstance(String relatedInstanceJsonOid) {
    	ObjectMapper mapper = new ObjectMapper();
    	try {
    		return new Aesolicitudes(mapper.readValue(relatedInstanceJsonOid, AesolicitudesOid.class));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    private AbstractAppEntity buildAesolicitudesRelatedInstance4Solicitud(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new Solicitud(mapper.readValue(relatedInstanceJsonOid, SolicitudOid.class)): Solicitud.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    private AbstractAppEntity buildAesolicitudesRelatedInstance4Productos(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new Productos(mapper.readValue(relatedInstanceJsonOid, ProductosOid.class)): Productos.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    private AbstractAppEntity buildAesolicitudesRelatedInstance4Planes(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new Planes(mapper.readValue(relatedInstanceJsonOid, PlanesOid.class)): Planes.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    private AbstractAppEntity buildAesolicitudesRelatedInstance4EstadoContrato(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new EstadoContrato(mapper.readValue(relatedInstanceJsonOid, EstadoContratoOid.class)): EstadoContrato.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    @Override
    protected void applyFilterByOid(String jsonOid, SQLSelect sql) {
        try {
            AesolicitudesOid oid = new AesolicitudesOid().fromJsonString(jsonOid);
            SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
            comparison1.setOperator("=");
            comparison1.setFirstOperand(new SQLWhereOperandPathAttribute(AesolicitudesConstants.ATTR_FIELD_AESOLICITUDESIDSOLICITUD, Constants.Type.AUTONUMERIC.getTypeName()));
            comparison1.setSecondOperand(new SQLWhereOperandLiteral(oid.getAesolicitudesidsolicitud(), Constants.Type.AUTONUMERIC.getTypeName()));
            sql.addComparison(comparison1);
        } catch (Exception e) {
            LoggerFactory.getLogger(AesolicitudesService.class).error(e.getMessage());
        }
    }

}
