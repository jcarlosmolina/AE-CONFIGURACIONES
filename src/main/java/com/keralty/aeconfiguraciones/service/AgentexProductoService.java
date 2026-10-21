package com.keralty.aeconfiguraciones.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.keralty.aeconfiguraciones.exception.ModelException;
import com.keralty.aeconfiguraciones.exception.SystemException;
import com.keralty.aeconfiguraciones.global.AgentexProductoConstants;
import com.keralty.aeconfiguraciones.global.AsesorConstants;
import com.keralty.aeconfiguraciones.global.Constants;
import com.keralty.aeconfiguraciones.global.FuncionarioConstants;
import com.keralty.aeconfiguraciones.global.ProductosConstants;
import com.keralty.aeconfiguraciones.persistence.AbstractAppEntity;
import com.keralty.aeconfiguraciones.persistence.AgentexProducto;
import com.keralty.aeconfiguraciones.persistence.Asesor;
import com.keralty.aeconfiguraciones.persistence.Dominio;
import com.keralty.aeconfiguraciones.persistence.oid.AgentexProductoOid;
import com.keralty.aeconfiguraciones.persistence.oid.AsesorOid;
import com.keralty.aeconfiguraciones.persistence.oid.DominioOid;
import com.keralty.aeconfiguraciones.persistence.oid.ProductosOid;
import com.keralty.aeconfiguraciones.persistence.Productos;
import com.keralty.aeconfiguraciones.repository.AgentexProductoJpaRepository;
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
import com.keralty.aeconfiguraciones.viewmodel.PreloadViewModel;
import com.keralty.aeconfiguraciones.viewmodel.QueryRequest;
import com.keralty.aeconfiguraciones.viewmodel.QueryResponse;
import com.keralty.aeconfiguraciones.viewmodel.ServiceResponse;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.LoggerFactory;

@Transactional
@ApplicationScoped
public class AgentexProductoService extends AbstractService {

    AgentexProductoJpaRepository repository;

    AgentexProductoService agentexProductoSrv;

    ProductosService productosSrv;

    DominioService dominioSrv;

    @Inject
    public AgentexProductoService(
            AgentexProductoService agentexProductoSrv,
            ProductosService productosSrv,
            DominioService dominioSrv,
            AgentexProductoJpaRepository repository) {
        this.agentexProductoSrv = agentexProductoSrv;
        this.productosSrv = productosSrv;
        this.dominioSrv = dominioSrv;
        this.repository = repository;
    }

    public List<Map<String, Object>> solveQuery(QueryRequest request) {
        return solveGenericQuery(request, AgentexProductoConstants.CLASS_NAME);
    }

    private List<AgentexProducto> solveAgentexProductoTypedQuery(QueryRequest request) {
        return repository.solveQuery(processQueryRequest(request, AgentexProductoConstants.CLASS_NAME), request.getPageSize(), request.getPageNumber());
    }
    
    private int solveAgentexProductoTypedCountQuery(QueryRequest request) {
        return repository.solveCountQuery(processCountQueryRequest(request, AgentexProductoConstants.CLASS_NAME));
    }

    /*
     * Returns the population of class AgentexProducto
     *
     * @return Population of class AgentexProducto
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<AgentexProducto> get() {
        List<AgentexProducto> results = new java.util.ArrayList<>();
        repository.findAll().forEach(results::add);
        return results;
    }
    
    /*
     * Gets the preloaded list of objects of class AgentexProducto
     *
     * @param queryRequest Request object that dictates the search conditions to 
     * retrieve objects
     *
     * @return Preloaded list of objects of class AgentexProducto
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<PreloadViewModel> getPreload(QueryRequest queryRequest) {
        List<PreloadViewModel> results = new ArrayList<>();
        List<AgentexProducto> instances = get(queryRequest);
        if(AgentexProductoConstants.DSICAGENTEXPRODUCTO.equals(queryRequest.getDisplaySetName())) {
            results = instances.stream().map(x -> new PreloadViewModel(x.getOid().toJson(), x.buildICAgentexProducto())).collect(Collectors.toList());
        }
        if(AgentexProductoConstants.DSDSAGENTEXPRODUCTOPIU.equals(queryRequest.getDisplaySetName())) {
            results = instances.stream().map(x -> new PreloadViewModel(x.getOid().toJson(), x.buildDSAgentexProductoPIU())).collect(Collectors.toList());
        }
        return results;
    }
    /*
     * Returns the population of class AgentexProducto that matches certain search criteria
     *
     * @param queryRequest Search criteria
     *
     * @return Population of class AgentexProducto matching the search criteria
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<AgentexProducto> get(QueryRequest queryRequest) {
        return solveAgentexProductoTypedQuery(queryRequest);
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
            return new PopulationResponseViewModel(solveAgentexProductoTypedQuery(queryRequest).stream()
                    .map(instance -> buildDynamicObject(instance, queryRequest.getDisplaySetItems()))
                    .collect(Collectors.toList()), queryRequest.getPageSize() != 0 ? solveAgentexProductoTypedCountQuery(queryRequest) : 0);
        } else {
            return new PreloadResponseViewModel(solveAgentexProductoTypedQuery(queryRequest).stream()
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
        return new PopulationResponseViewModel(solveAgentexProductoTypedQuery(queryRequest).stream()
                .map(instance -> buildDynamicObject(instance, displaySetItems))
                .collect(Collectors.toList()));
    }

    /*
     * Returns an instance of AgentexProducto by ID
     *
     * @param funcionarioAsesorId Identification field funcionarioAsesorId
     *
     * @param agentexProductoId Identification field agentexProductoId
     *
     * @return Instance of AgentexProducto
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public AgentexProducto getById(Long funcionarioAsesorId, Long agentexProductoId) {
        return getByOID(new AgentexProductoOid(funcionarioAsesorId, agentexProductoId));
    }
    
    /*
     * Returns an instance of AgentexProducto by OID
     *
     * @param oid Object IDentifier
     *
     * @return Instance of AgentexProducto
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public AgentexProducto getByOID(AgentexProductoOid oid) {
        Optional<AgentexProducto> instance = getInstanceByOid(oid);
        return instance.isPresent() ? instance.get() : null;
    }
    
    /*
     * Returns an instance of AgentexProducto by OID applying horizontal visibility constraints
     *
     * @param oid Object IDentifier
     *
     * @return Instance of AgentexProducto
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public AgentexProducto getByOIDWithHV(AgentexProductoOid oid) {
        Optional<AgentexProducto> instance = getInstanceByOid(oid);
    	return instance.isPresent() && instance.get().checkHorizontalVisibility(getAgentInstance()) 
    		 ? instance.get() 
    		 : null;
    }
    
    private Optional<AgentexProducto> getInstanceByOid(AgentexProductoOid oid) {
        return oid != null && oid.getFuncionarioAsesorId() != null
               ? repository.findById(oid)
               : Optional.empty();
    }

    /*
     * Returns an instance of AgentexProducto by OID in JSON format
     *
     * @param jsonOid Object IDentifier in JSON format
     *
     * @return Instance of AgentexProducto
     */
    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public AgentexProducto getByJsonOid(String jsonOid) {
        AgentexProductoOid oid = new AgentexProductoOid();
        oid.fromJsonString(jsonOid);
        return getByOIDWithHV(oid);
    }

    /*
     * Returns the list of instances of AgentexProducto related to a given instance through 
     * a given path
     *
     * @param relatedInstance Related instance
     *
     * @param relatedInstancePath Related instance path
     *
     * @return List of instances of AgentexProducto
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<AgentexProducto> getByRelated(AbstractAppEntity relatedInstance, String relatedInstancePath) {
        QueryRequest queryRequest = new QueryRequest(new FilterRequest(Constants.FILTER_BY_RELATED,
                Arrays.asList(new FilterVariableRequest(Constants.FILTER_BY_RELATED_FVAR_PATH, relatedInstancePath),
                        new FilterVariableRequest(Constants.FILTER_BY_RELATED_FVAR_OID, relatedInstance.getOid().toJson()))));
        return solveAgentexProductoTypedQuery(queryRequest);
    }

    /*
     * Returns the list of instances of AgentexProducto related to a given instance through
     * a given path
     *
     * @param relatedInstance Related instance
     *
     * @param relatedInstancePath Related instance path
     *
     * @return List of instances of AgentexProducto
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<AgentexProducto> getInstancesByRelated(AbstractAppEntity relatedInstance, String relatedInstancePath) {
        return getByRelated(relatedInstance, relatedInstancePath);
    }

    /*
     * Returns a set of displayset items for an instance of AgentexProducto
     *
     * @param oid Object IDentifier
     *
     * @param displaySetItems List of comma-separated displayset items
     *
     * @return Set of displayset items for an instance of AgentexProducto
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public InstanceResponseViewModel getAgentexProductoDynamic(AgentexProductoOid oid, String displaySetItems) {
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
    public String getSuppInfo(AgentexProductoOid oid, String dsName) {
        AgentexProducto instance = getByOIDWithHV(oid);
        return (instance != null ? getSuppInfo(instance, dsName): "");
    }

    private String getSuppInfo(AgentexProducto instance, String dsName) {
        if (instance == null || instance.isNull()) return "";
        if (AgentexProductoConstants.DSICAGENTEXPRODUCTO.equals(dsName))
            return instance.buildICAgentexProducto();
        return dsName.isEmpty() ? instance.getOid().toJson() : "";
    }

    public static PreloadViewModel buildPreloadViewModel(AgentexProducto instance, String dsName) {
        if (AgentexProductoConstants.DSICAGENTEXPRODUCTO.equals(dsName))
            return new PreloadViewModel(instance.getOid().toJson(), instance.buildICAgentexProducto());
        if (AgentexProductoConstants.DSDSAGENTEXPRODUCTOPIU.equals(dsName))
            return new PreloadViewModel(instance.getOid().toJson(), instance.buildDSAgentexProductoPIU());
        return new PreloadViewModel(instance.getOid().toJson(), "");
    }


    @Override
    protected void applyServiceSIFilters(FilterRequest filterRequest, SQLSelect sql) {
        if ("SuppInfo_IC_AgentexProducto".equals(filterRequest.getName()))
            suppInfo4ICAgentexProducto(filterRequest.getFilterVariable("searchText").toString(), sql);
    }



    // IIU Methods


    /*
     * Solves the query needed for PIUAgentexProducto
     *
     * @param queryRequest Query request
     * @return List of viewmodels for AgentexProducto using displayset DSAgentexProductoPIU that 
     * match the search criteria
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<com.keralty.aeconfiguraciones.viewmodel.agentexproducto.ds.DSAgentexProductoPIU> piuPIUAgentexProducto(QueryRequest queryRequest) {
        return solveAgentexProductoTypedQuery(queryRequest).stream()
                .map(com.keralty.aeconfiguraciones.viewmodel.agentexproducto.ds.DSAgentexProductoPIU::new)
                .collect(Collectors.toList());
    }

    /*
     * Solves the COUNT query needed for PIUAgentexProducto
     *
     * @param queryRequest Query request
     * @returns Count of instances for PIUAgentexProducto
     */
    public int getCount4PIUAgentexProducto(QueryRequest queryRequest) {
        return solveAgentexProductoTypedQuery(queryRequest).size();
    }

    // Service: CambProductoDeAsesorxProducto

    /*
     * Entry point for service CambProductoDeAsesorxProducto
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse cambProductoDeAsesorxProducto(
            com.keralty.aeconfiguraciones.viewmodel.agentexproducto.siu.CambProductoDeAsesorxProductoRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = agentexProductoSrv.cambProductoDeAsesorxProductoTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service CambProductoDeAsesorxProducto
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse cambProductoDeAsesorxProductoTxn(
            com.keralty.aeconfiguraciones.viewmodel.agentexproducto.siu.CambProductoDeAsesorxProductoRequest request) throws ModelException {
        request.setAgentexProductoCambProductoDeAsesorxProductopthisAgentexProductoInstance(agentexProductoSrv.getByOIDWithHV(request.getAgentexProductoCambProductoDeAsesorxProductopthisAgentexProducto()));
        request.setAgentexProductoCambProductoDeAsesorxProductopevcProductosInstance(productosSrv.getByOIDWithHV(request.getAgentexProductoCambProductoDeAsesorxProductopevcProductos()));
        return agentexProductoSrv.cambProductoDeAsesorxProductoImplementation(request, true);
    }

    /*
     * Implements service CambProductoDeAsesorxProducto
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse cambProductoDeAsesorxProductoImplementation(
            com.keralty.aeconfiguraciones.viewmodel.agentexproducto.siu.CambProductoDeAsesorxProductoRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        AgentexProducto agentexProductoCambProductoDeAsesorxProductopthisAgentexProducto = request.getAgentexProductoCambProductoDeAsesorxProductopthisAgentexProductoInstance();
        Productos agentexProductoCambProductoDeAsesorxProductopevcProductos = request.getAgentexProductoCambProductoDeAsesorxProductopevcProductosInstance();

        request.checkArguments();
        AgentexProducto instance = agentexProductoCambProductoDeAsesorxProductopthisAgentexProducto;

        instance.add2ProductoT(agentexProductoCambProductoDeAsesorxProductopevcProductos);
        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.productos.siu.CambProductoDeAsesorxProductoRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.productos.siu.CambProductoDeAsesorxProductoRequest();
            relatedRequest.setProductosCambProductoDeAsesorxProductopthisProductoInstance(agentexProductoCambProductoDeAsesorxProductopevcProductos);
            relatedRequest.setProductosCambProductoDeAsesorxProductopevcAgentexProductoInstance(agentexProductoCambProductoDeAsesorxProductopthisAgentexProducto);
            productosSrv.cambProductoDeAsesorxProductoImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICAgentexProducto());

        return response;
    }

    // Service: InsDominio

    /*
     * Entry point for service InsDominio
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse insDominio(
            com.keralty.aeconfiguraciones.viewmodel.agentexproducto.siu.InsDominioRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = agentexProductoSrv.insDominioTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service InsDominio
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse insDominioTxn(
            com.keralty.aeconfiguraciones.viewmodel.agentexproducto.siu.InsDominioRequest request) throws ModelException {
        request.setAgentexProductoInsDominiopthisAgentexProductoInstance(agentexProductoSrv.getByOIDWithHV(request.getAgentexProductoInsDominiopthisAgentexProducto()));
        request.setAgentexProductoInsDominiopevcDominioInstance(dominioSrv.getByOIDWithHV(request.getAgentexProductoInsDominiopevcDominio()));
        return agentexProductoSrv.insDominioImplementation(request, true);
    }

    /*
     * Implements service InsDominio
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse insDominioImplementation(
            com.keralty.aeconfiguraciones.viewmodel.agentexproducto.siu.InsDominioRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        AgentexProducto agentexProductoInsDominiopthisAgentexProducto = request.getAgentexProductoInsDominiopthisAgentexProductoInstance();
        Dominio agentexProductoInsDominiopevcDominio = request.getAgentexProductoInsDominiopevcDominioInstance();

        request.checkArguments();
        AgentexProducto instance = agentexProductoInsDominiopthisAgentexProducto;

        instance.add2Dominios(agentexProductoInsDominiopevcDominio);
        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.dominio.siu.InsDominioRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.dominio.siu.InsDominioRequest();
            relatedRequest.setDominioInsDominiopthisDominioInstance(agentexProductoInsDominiopevcDominio);
            relatedRequest.setDominioInsDominiopevcAgentexProductoInstance(agentexProductoInsDominiopthisAgentexProducto);
            dominioSrv.insDominioImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICAgentexProducto());

        return response;
    }

    // Service: DelDominio

    /*
     * Entry point for service DelDominio
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse delDominio(
            com.keralty.aeconfiguraciones.viewmodel.agentexproducto.siu.DelDominioRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = agentexProductoSrv.delDominioTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service DelDominio
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse delDominioTxn(
            com.keralty.aeconfiguraciones.viewmodel.agentexproducto.siu.DelDominioRequest request) throws ModelException {
        request.setAgentexProductoDelDominiopthisAgentexProductoInstance(agentexProductoSrv.getByOIDWithHV(request.getAgentexProductoDelDominiopthisAgentexProducto()));
        request.setAgentexProductoDelDominiopevcDominioInstance(dominioSrv.getByOIDWithHV(request.getAgentexProductoDelDominiopevcDominio()));
        return agentexProductoSrv.delDominioImplementation(request, true);
    }

    /*
     * Implements service DelDominio
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse delDominioImplementation(
            com.keralty.aeconfiguraciones.viewmodel.agentexproducto.siu.DelDominioRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        AgentexProducto agentexProductoDelDominiopthisAgentexProducto = request.getAgentexProductoDelDominiopthisAgentexProductoInstance();
        Dominio agentexProductoDelDominiopevcDominio = request.getAgentexProductoDelDominiopevcDominioInstance();

        request.checkArguments();
        AgentexProducto instance = agentexProductoDelDominiopthisAgentexProducto;

        instance.removeFromDominios(agentexProductoDelDominiopevcDominio);
        agentexProductoDelDominiopevcDominio.removeFromAgentexProducto(instance);
        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.dominio.siu.DelDominioRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.dominio.siu.DelDominioRequest();
            relatedRequest.setDominioDelDominiopthisDominioInstance(agentexProductoDelDominiopevcDominio);
            relatedRequest.setDominioDelDominiopevcAgentexProductoInstance(agentexProductoDelDominiopthisAgentexProducto);
            dominioSrv.delDominioImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICAgentexProducto());

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
        // Asesor = agent OR Asesor.CodJefe = Agent.Usuario
        Asesor agentInstance = getAgentAsesor();
    {
        // 'or' Logical operator
        SQLWhereDisjunction comparison1 = new SQLWhereDisjunction();
    
        // Operand 1
            // '=' Comparison
            SQLWhereComparison comparison2 = new SQLWhereComparison(sql);
            comparison2.setOperator("=");
            
            // Operand 1
            SQLWhereOperandPathInstance operand113 = new SQLWhereOperandPathInstance(AgentexProductoConstants.ROLE_NAME_ASESOR, AsesorConstants.CLASS_NAME);
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
            SQLWhereOperandPathAttribute operand115 = new SQLWhereOperandPathAttribute(AgentexProductoConstants.ROLE_NAME_ASESOR + "." + FuncionarioConstants.ATTR_NAME_FUNCIONARIOCODJEFE, Constants.Type.STRING.getTypeName());
            comparison4.setFirstOperand(operand115);
            
            // Operand 2
            comparison4.setSecondOperand(new SQLWhereOperandLiteral(agentInstance.getFuncionarioUsuario(), Constants.Type.STRING.getTypeName()));
            comparison1.addOperand(comparison4);
        
        disjunction.addOperand(comparison1);
    }

    }


    private void suppInfo4ICAgentexProducto(String searchText, SQLSelect select) {
        try {
            SQLWhereDisjunction disjunction = new SQLWhereDisjunction();

            SQLWhereComparison comparison1 = new SQLWhereComparison(select);
            comparison1.setOperator("LIKE");
            comparison1.setFirstOperand(new SQLWhereOperandPathAttribute(AgentexProductoConstants.ROLE_NAME_ASESOR + "." + FuncionarioConstants.ATTR_NAME_FUNCIONARIODRVNOMBRECOMPLETO, Constants.Type.STRING.getTypeName()));
            comparison1.setSecondOperand(new SQLWhereOperandLiteral(searchText, Constants.Type.STRING.getTypeName()));
            disjunction.addComparison(comparison1);

            SQLWhereComparison comparison2 = new SQLWhereComparison(select);
            comparison2.setOperator("LIKE");
            comparison2.setFirstOperand(new SQLWhereOperandPathAttribute(AgentexProductoConstants.ROLE_NAME_PRODUCTOT + "." + ProductosConstants.ATTR_NAME_PRODUCTOSDESCRIPCION, Constants.Type.STRING.getTypeName()));
            comparison2.setSecondOperand(new SQLWhereOperandLiteral(searchText, Constants.Type.STRING.getTypeName()));
            disjunction.addComparison(comparison2);

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
        select.getOrderByPart().addItem(Constants.normalizePath(AgentexProductoConstants.CLASS_NAME, AgentexProductoConstants.ROLE_NAME_ASESOR + "." + FuncionarioConstants.ATTR_FIELD_FUNCIONARIOID, false), true);
        select.getOrderByPart().addItem(Constants.normalizePath(AgentexProductoConstants.CLASS_NAME, AgentexProductoConstants.ATTR_FIELD_AGENTEXPRODUCTOID, false), true);
    }
    
    @Override
    protected AbstractAppEntity buildRelatedInstance(String relatedInstanceJsonOid, String relatedInstancePathFirstItem, String remainingPath) {
        AbstractAppEntity relatedInstance = null;
        if (relatedInstancePathFirstItem.isEmpty()) {
            relatedInstance = buildAgentexProductoRelatedInstance(relatedInstanceJsonOid);
        }
        if (AgentexProductoConstants.ROLE_NAME_PRODUCTOT.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildAgentexProductoRelatedInstance4Productos(relatedInstanceJsonOid, remainingPath);
        }
        if (AgentexProductoConstants.ROLE_NAME_ASESOR.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildAgentexProductoRelatedInstance4Asesor(relatedInstanceJsonOid, remainingPath);
        }
        if (AgentexProductoConstants.ROLE_NAME_DOMINIOS.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildAgentexProductoRelatedInstance4Dominio(relatedInstanceJsonOid, remainingPath);
        }
        return relatedInstance;
    }
    
    private AbstractAppEntity buildAgentexProductoRelatedInstance(String relatedInstanceJsonOid) {
    	ObjectMapper mapper = new ObjectMapper();
    	try {
    		return new AgentexProducto(mapper.readValue(relatedInstanceJsonOid, AgentexProductoOid.class));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    private AbstractAppEntity buildAgentexProductoRelatedInstance4Productos(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new Productos(mapper.readValue(relatedInstanceJsonOid, ProductosOid.class)): Productos.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    private AbstractAppEntity buildAgentexProductoRelatedInstance4Asesor(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new Asesor(mapper.readValue(relatedInstanceJsonOid, AsesorOid.class)): Asesor.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    private AbstractAppEntity buildAgentexProductoRelatedInstance4Dominio(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new Dominio(mapper.readValue(relatedInstanceJsonOid, DominioOid.class)): Dominio.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    @Override
    protected void applyFilterByOid(String jsonOid, SQLSelect sql) {
        try {
            AgentexProductoOid oid = new AgentexProductoOid().fromJsonString(jsonOid);
            SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
            comparison1.setOperator("=");
            comparison1.setFirstOperand(new SQLWhereOperandPathAttribute(AgentexProductoConstants.ROLE_NAME_ASESOR + "." + FuncionarioConstants.ATTR_FIELD_FUNCIONARIOID, Constants.Type.AUTONUMERIC.getTypeName()));
            comparison1.setSecondOperand(new SQLWhereOperandLiteral(oid.getFuncionarioAsesorId(), Constants.Type.AUTONUMERIC.getTypeName()));
            sql.addComparison(comparison1);
            SQLWhereComparison comparison2 = new SQLWhereComparison(sql);
            comparison2.setOperator("=");
            comparison2.setFirstOperand(new SQLWhereOperandPathAttribute(AgentexProductoConstants.ATTR_FIELD_AGENTEXPRODUCTOID, Constants.Type.AUTONUMERIC.getTypeName()));
            comparison2.setSecondOperand(new SQLWhereOperandLiteral(oid.getAgentexProductoId(), Constants.Type.AUTONUMERIC.getTypeName()));
            sql.addComparison(comparison2);
        } catch (Exception e) {
            LoggerFactory.getLogger(AgentexProductoService.class).error(e.getMessage());
        }
    }

}
