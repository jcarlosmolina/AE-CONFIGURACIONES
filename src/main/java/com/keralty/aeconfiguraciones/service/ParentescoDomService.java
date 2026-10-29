package com.keralty.aeconfiguraciones.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.keralty.aeconfiguraciones.exception.ModelException;
import com.keralty.aeconfiguraciones.exception.PreconditionException;
import com.keralty.aeconfiguraciones.exception.SystemException;
import com.keralty.aeconfiguraciones.global.Constants;
import com.keralty.aeconfiguraciones.global.DominioConstants;
import com.keralty.aeconfiguraciones.global.ParentescoConstants;
import com.keralty.aeconfiguraciones.global.ParentescoDomConstants;
import com.keralty.aeconfiguraciones.persistence.AbstractAppEntity;
import com.keralty.aeconfiguraciones.persistence.Dominio;
import com.keralty.aeconfiguraciones.persistence.oid.DominioOid;
import com.keralty.aeconfiguraciones.persistence.oid.ParentescoDomOid;
import com.keralty.aeconfiguraciones.persistence.oid.ParentescoOid;
import com.keralty.aeconfiguraciones.persistence.Parentesco;
import com.keralty.aeconfiguraciones.persistence.ParentescoDom;
import com.keralty.aeconfiguraciones.repository.ParentescoDomJpaRepository;
import com.keralty.aeconfiguraciones.sqlutils.SQLSelect;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereComparison;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereConjunction;
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
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.LoggerFactory;

@Transactional
@ApplicationScoped
public class ParentescoDomService extends AbstractService {

    ParentescoDomJpaRepository repository;

    ParentescoDomService parentescoDomSrv;

    ParentescoService parentescoSrv;

    DominioService dominioSrv;

    @Inject
    public ParentescoDomService(
            ParentescoDomService parentescoDomSrv,
            ParentescoService parentescoSrv,
            DominioService dominioSrv,
            ParentescoDomJpaRepository repository) {
        this.parentescoDomSrv = parentescoDomSrv;
        this.parentescoSrv = parentescoSrv;
        this.dominioSrv = dominioSrv;
        this.repository = repository;
    }

    public List<Map<String, Object>> solveQuery(QueryRequest request) {
        return solveGenericQuery(request, ParentescoDomConstants.CLASS_NAME);
    }

    private List<ParentescoDom> solveParentescoDomTypedQuery(QueryRequest request) {
        return repository.solveQuery(processQueryRequest(request, ParentescoDomConstants.CLASS_NAME), request.getPageSize(), request.getPageNumber());
    }
    
    private int solveParentescoDomTypedCountQuery(QueryRequest request) {
        return repository.solveCountQuery(processCountQueryRequest(request, ParentescoDomConstants.CLASS_NAME));
    }

    /*
     * Returns the population of class ParentescoDom
     *
     * @return Population of class ParentescoDom
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<ParentescoDom> get() {
        List<ParentescoDom> results = new java.util.ArrayList<>();
        repository.findAll().forEach(results::add);
        return results;
    }
    /*
     * Returns the population of class ParentescoDom that matches certain search criteria
     *
     * @param queryRequest Search criteria
     *
     * @return Population of class ParentescoDom matching the search criteria
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<ParentescoDom> get(QueryRequest queryRequest) {
        return solveParentescoDomTypedQuery(queryRequest);
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
            return new PopulationResponseViewModel(solveParentescoDomTypedQuery(queryRequest).stream()
                    .map(instance -> buildDynamicObject(instance, queryRequest.getDisplaySetItems()))
                    .collect(Collectors.toList()), queryRequest.getPageSize() != 0 ? solveParentescoDomTypedCountQuery(queryRequest) : 0);
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
        return new PopulationResponseViewModel(solveParentescoDomTypedQuery(queryRequest).stream()
                .map(instance -> buildDynamicObject(instance, displaySetItems))
                .collect(Collectors.toList()));
    }

    /*
     * Returns an instance of ParentescoDom by ID
     *
     * @param dominioNumero Identification field dominioNumero
     *
     * @param parentescoDomIdentificador Identification field parentescoDomIdentificador
     *
     * @return Instance of ParentescoDom
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ParentescoDom getById(Long dominioNumero, Long parentescoDomIdentificador) {
        return getByOID(new ParentescoDomOid(dominioNumero, parentescoDomIdentificador));
    }
    
    /*
     * Returns an instance of ParentescoDom by OID
     *
     * @param oid Object IDentifier
     *
     * @return Instance of ParentescoDom
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ParentescoDom getByOID(ParentescoDomOid oid) {
        Optional<ParentescoDom> instance = getInstanceByOid(oid);
        return instance.isPresent() ? instance.get() : null;
    }
    
    /*
     * Returns an instance of ParentescoDom by OID applying horizontal visibility constraints
     *
     * @param oid Object IDentifier
     *
     * @return Instance of ParentescoDom
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public ParentescoDom getByOIDWithHV(ParentescoDomOid oid) {
        Optional<ParentescoDom> instance = getInstanceByOid(oid);
    	return instance.isPresent() && instance.get().checkHorizontalVisibility(getAgentInstance()) 
    		 ? instance.get() 
    		 : null;
    }
    
    private Optional<ParentescoDom> getInstanceByOid(ParentescoDomOid oid) {
        return oid != null && oid.getDominioNumero() != null
               ? repository.findById(oid)
               : Optional.empty();
    }

    /*
     * Returns an instance of ParentescoDom by OID in JSON format
     *
     * @param jsonOid Object IDentifier in JSON format
     *
     * @return Instance of ParentescoDom
     */
    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public ParentescoDom getByJsonOid(String jsonOid) {
        ParentescoDomOid oid = new ParentescoDomOid();
        oid.fromJsonString(jsonOid);
        return getByOIDWithHV(oid);
    }

    /*
     * Returns the list of instances of ParentescoDom related to a given instance through 
     * a given path
     *
     * @param relatedInstance Related instance
     *
     * @param relatedInstancePath Related instance path
     *
     * @return List of instances of ParentescoDom
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<ParentescoDom> getByRelated(AbstractAppEntity relatedInstance, String relatedInstancePath) {
        QueryRequest queryRequest = new QueryRequest(new FilterRequest(Constants.FILTER_BY_RELATED,
                Arrays.asList(new FilterVariableRequest(Constants.FILTER_BY_RELATED_FVAR_PATH, relatedInstancePath),
                        new FilterVariableRequest(Constants.FILTER_BY_RELATED_FVAR_OID, relatedInstance.getOid().toJson()))));
        return solveParentescoDomTypedQuery(queryRequest);
    }

    /*
     * Returns the list of instances of ParentescoDom related to a given instance through
     * a given path
     *
     * @param relatedInstance Related instance
     *
     * @param relatedInstancePath Related instance path
     *
     * @return List of instances of ParentescoDom
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<ParentescoDom> getInstancesByRelated(AbstractAppEntity relatedInstance, String relatedInstancePath) {
        return getByRelated(relatedInstance, relatedInstancePath);
    }

    /*
     * Returns a set of displayset items for an instance of ParentescoDom
     *
     * @param oid Object IDentifier
     *
     * @param displaySetItems List of comma-separated displayset items
     *
     * @return Set of displayset items for an instance of ParentescoDom
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public InstanceResponseViewModel getParentescoDomDynamic(ParentescoDomOid oid, String displaySetItems) {
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
    public String getSuppInfo(ParentescoDomOid oid, String dsName) {
        ParentescoDom instance = getByOIDWithHV(oid);
        return (instance != null ? getSuppInfo(instance, dsName): "");
    }

    private String getSuppInfo(ParentescoDom instance, String dsName) {
        if (instance == null || instance.isNull()) return "";
        return dsName.isEmpty() ? instance.getOid().toJson() : "";
    }


    @Override
    protected void applyQueryFilters(FilterRequest filterRequest, SQLSelect sql) throws SystemException {
        if (ParentescoDomConstants.FILTER_NAME_IFPARENTESCODOM.equalsIgnoreCase(filterRequest.getName()))
            iFParentescoDom(filterRequest, sql);
    }


    private void iFParentescoDom(FilterRequest filterRequest, SQLSelect sql) {
        SQLWhereConjunction conjunction = new SQLWhereConjunction();
        Parentesco voParentesco = filterRequest.hasFilterVariable(ParentescoDomConstants.VAR_NAME_IFPARENTESCODOM_VOPARENTESCO) ? parentescoSrv.getByOID(new ParentescoOid().fromJsonString(toJson(filterRequest.getFilterVariable(ParentescoDomConstants.VAR_NAME_IFPARENTESCODOM_VOPARENTESCO)))) : null;
        Dominio voDominio = filterRequest.hasFilterVariable(ParentescoDomConstants.VAR_NAME_IFPARENTESCODOM_VODOMINIO) ? dominioSrv.getByOID(new DominioOid().fromJsonString(toJson(filterRequest.getFilterVariable(ParentescoDomConstants.VAR_NAME_IFPARENTESCODOM_VODOMINIO)))) : null;

        // Parentesco = voParentesco
        if (voParentesco != null) {
            // '=' Comparison
            SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
            comparison1.setOperator("=");
            
            // Operand 1
            SQLWhereOperandPathInstance operand112 = new SQLWhereOperandPathInstance(ParentescoDomConstants.ROLE_NAME_PARENTESCO, ParentescoConstants.CLASS_NAME);
            comparison1.setFirstOperand(operand112);
            
            // Operand 2
            SQLWhereOperandOVParameter operand223 = new SQLWhereOperandOVParameter(ParentescoDomConstants.VAR_NAME_IFPARENTESCODOM_VOPARENTESCO, ParentescoConstants.CLASS_NAME, ParentescoConstants.CLASS_NAME, voParentesco.getOid(), ParentescoDomConstants.VAR_NAME_IFPARENTESCODOM_VOPARENTESCO);
            comparison1.setSecondOperand(operand223);
            conjunction.addOperand(comparison1);

        }        

        // Dominio = voDominio
        if (voDominio != null) {
            // '=' Comparison
            SQLWhereComparison comparison2 = new SQLWhereComparison(sql);
            comparison2.setOperator("=");
            
            // Operand 1
            SQLWhereOperandPathInstance operand113 = new SQLWhereOperandPathInstance(ParentescoDomConstants.ROLE_NAME_DOMINIO, DominioConstants.CLASS_NAME);
            comparison2.setFirstOperand(operand113);
            
            // Operand 2
            SQLWhereOperandOVParameter operand224 = new SQLWhereOperandOVParameter(ParentescoDomConstants.VAR_NAME_IFPARENTESCODOM_VODOMINIO, DominioConstants.CLASS_NAME, DominioConstants.CLASS_NAME, voDominio.getOid(), ParentescoDomConstants.VAR_NAME_IFPARENTESCODOM_VODOMINIO);
            comparison2.setSecondOperand(operand224);
            conjunction.addOperand(comparison2);

        }        

        sql.addConjunction(conjunction);
    }

    // IIU Methods


    /*
     * Solves the query needed for PIUParentescoDom
     *
     * @param queryRequest Query request
     * @return List of viewmodels for ParentescoDom using displayset DSParentescoDomPIU that 
     * match the search criteria
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<com.keralty.aeconfiguraciones.viewmodel.parentescodom.ds.DSParentescoDomPIU> piuPIUParentescoDom(QueryRequest queryRequest) {
        return solveParentescoDomTypedQuery(queryRequest).stream()
                .map(com.keralty.aeconfiguraciones.viewmodel.parentescodom.ds.DSParentescoDomPIU::new)
                .collect(Collectors.toList());
    }

    /*
     * Solves the COUNT query needed for PIUParentescoDom
     *
     * @param queryRequest Query request
     * @returns Count of instances for PIUParentescoDom
     */
    public int getCount4PIUParentescoDom(QueryRequest queryRequest) {
        return solveParentescoDomTypedQuery(queryRequest).size();
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
            com.keralty.aeconfiguraciones.viewmodel.parentescodom.siu.CrearRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = parentescoDomSrv.crearTxn(request);
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
            com.keralty.aeconfiguraciones.viewmodel.parentescodom.siu.CrearRequest request) throws ModelException {
        request.setParentescoDomcrearpagrParentescoInstance(parentescoSrv.getByOIDWithHV(new ParentescoOid().fromJsonString(request.getParentescoDomcrearpagrParentesco())));
        request.setParentescoDomcrearpagrDominioInstance(dominioSrv.getByOIDWithHV(request.getParentescoDomcrearpagrDominio()));
        return parentescoDomSrv.crearImplementation(request);
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
            com.keralty.aeconfiguraciones.viewmodel.parentescodom.siu.CrearRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Parentesco parentescoDomcrearpagrParentesco = request.getParentescoDomcrearpagrParentescoInstance();
        Dominio parentescoDomcrearpagrDominio = request.getParentescoDomcrearpagrDominioInstance();
        Long parentescoDomcrearpatrEdadMinimaA = request.getParentescoDomcrearpatrEdadMinimaA();
        Long parentescoDomcrearpatrEdadMaximaA = request.getParentescoDomcrearpatrEdadMaximaA();
        Long parentescoDomcrearpatrEdadMinimaD = request.getParentescoDomcrearpatrEdadMinimaD();
        Long parentescoDomcrearpatrEdadMaximaP = request.getParentescoDomcrearpatrEdadMaximaP();

        request.checkArguments();

        crearPreconditions(request);
        ParentescoDom instance = new ParentescoDom();
        instance.add2Parentesco(parentescoDomcrearpagrParentesco);

        instance.add2Dominio(parentescoDomcrearpagrDominio);

        instance.setParentescoDomIdentificador(Long.valueOf(0));
        instance.setParentescoDomIdentificador(repository.nextParentescoDomIdentificador(instance.getDominioNumero()));
        instance.setParentescoDomEdadMinimaA(parentescoDomcrearpatrEdadMinimaA);
        instance.setParentescoDomEdadMaximaA(parentescoDomcrearpatrEdadMaximaA);
        instance.setParentescoDomEdadMinimaD(parentescoDomcrearpatrEdadMinimaD);
        instance.setParentescoDomEdadMaximaP(parentescoDomcrearpatrEdadMaximaP);

        instance.setOid(instance.getOid());

        response.setInstance(repository.save(instance));

        return response;
    }

    /*
     * Checks service crear preconditions individually
     *
     * @param request Service request viewmodel
     *
     * @return Service preconditions evaluation result
     */
    public ServiceResponse crearCheckPreconditions(com.keralty.aeconfiguraciones.viewmodel.parentescodom.siu.CrearRequest request) {
        ServiceResponse response = new ServiceResponse();
        request.setParentescoDomcrearpagrParentescoInstance(parentescoSrv.getByOIDWithHV(new ParentescoOid().fromJsonString(request.getParentescoDomcrearpagrParentesco())));
        request.setParentescoDomcrearpagrDominioInstance(dominioSrv.getByOIDWithHV(request.getParentescoDomcrearpagrDominio()));
        try {
            if("Clas_1434710704128231Pre_1".equals(request.getPreconditionId()))
                checkClas1434710704128231Pre1(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    private void crearPreconditions(
            com.keralty.aeconfiguraciones.viewmodel.parentescodom.siu.CrearRequest request) 
            throws ModelException {
        checkClas1434710704128231Pre1(request);
    }

    private void checkClas1434710704128231Pre1 (
            com.keralty.aeconfiguraciones.viewmodel.parentescodom.siu.CrearRequest request) 
            throws ModelException {
        // EXIST( pagrDominio.ParentescosDom ) WHERE (pagrDominio.ParentescosDom.Parentesco = pagrParentesco) = false

        Dominio parentescoDomcrearpagrDominio = request.getParentescoDomcrearpagrDominioInstance();
        Parentesco parentescoDomcrearpagrParentesco = request.getParentescoDomcrearpagrParentescoInstance();
        try {
            boolean preconditionHolds = repository.assocOperator000(parentescoDomcrearpagrDominio, parentescoDomcrearpagrParentesco).equals(false);
            if (!preconditionHolds) throw new PreconditionException("El parentesco ya existe para el dominio");
        } catch (SystemException e) {
            LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            throw new SystemException(e.getMessage());
        }
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
            com.keralty.aeconfiguraciones.viewmodel.parentescodom.siu.EliminarRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = parentescoDomSrv.eliminarTxn(request);
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
            com.keralty.aeconfiguraciones.viewmodel.parentescodom.siu.EliminarRequest request) throws ModelException {
        request.setParentescoDomeliminarpthisParentescoDomInstance(parentescoDomSrv.getByOIDWithHV(request.getParentescoDomeliminarpthisParentescoDom()));
        return parentescoDomSrv.eliminarImplementation(request);
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
            com.keralty.aeconfiguraciones.viewmodel.parentescodom.siu.EliminarRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        ParentescoDom parentescoDomeliminarpthisParentescoDom = request.getParentescoDomeliminarpthisParentescoDomInstance();

        request.checkArguments();
        ParentescoDom instance = parentescoDomeliminarpthisParentescoDom;


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
            com.keralty.aeconfiguraciones.viewmodel.parentescodom.siu.ModificarRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = parentescoDomSrv.modificarTxn(request);
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
            com.keralty.aeconfiguraciones.viewmodel.parentescodom.siu.ModificarRequest request) throws ModelException {
        request.setParentescoDommodificarpthisParentescoDomInstance(parentescoDomSrv.getByOIDWithHV(request.getParentescoDommodificarpthisParentescoDom()));
        return parentescoDomSrv.modificarImplementation(request);
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
            com.keralty.aeconfiguraciones.viewmodel.parentescodom.siu.ModificarRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        ParentescoDom parentescoDommodificarpthisParentescoDom = request.getParentescoDommodificarpthisParentescoDomInstance();
        Long parentescoDommodificarpEdadMinimaA = request.getParentescoDommodificarpEdadMinimaA();
        Long parentescoDommodificarpEdadMaximaA = request.getParentescoDommodificarpEdadMaximaA();
        Long parentescoDommodificarpEdadMinimaD = request.getParentescoDommodificarpEdadMinimaD();
        Long parentescoDommodificarpEdadMaximaP = request.getParentescoDommodificarpEdadMaximaP();

        request.checkArguments();
        ParentescoDom instance = parentescoDommodificarpthisParentescoDom;
        instance.setParentescoDomEdadMinimaA(parentescoDommodificarpEdadMinimaA);
        instance.setParentescoDomEdadMaximaA(parentescoDommodificarpEdadMaximaA);
        instance.setParentescoDomEdadMinimaD(parentescoDommodificarpEdadMinimaD);
        instance.setParentescoDomEdadMaximaP(parentescoDommodificarpEdadMaximaP);


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
        select.getOrderByPart().addItem(Constants.normalizePath(ParentescoDomConstants.CLASS_NAME, ParentescoDomConstants.ROLE_NAME_DOMINIO + "." + DominioConstants.ATTR_FIELD_DOMINIONUMERO, false), true);
        select.getOrderByPart().addItem(Constants.normalizePath(ParentescoDomConstants.CLASS_NAME, ParentescoDomConstants.ATTR_FIELD_PARENTESCODOMIDENTIFICADOR, false), true);
    }
    
    @Override
    protected AbstractAppEntity buildRelatedInstance(String relatedInstanceJsonOid, String relatedInstancePathFirstItem, String remainingPath) {
        AbstractAppEntity relatedInstance = null;
        if (relatedInstancePathFirstItem.isEmpty()) {
            relatedInstance = buildParentescoDomRelatedInstance(relatedInstanceJsonOid);
        }
        if (ParentescoDomConstants.ROLE_NAME_DOMINIO.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildParentescoDomRelatedInstance4Dominio(relatedInstanceJsonOid, remainingPath);
        }
        if (ParentescoDomConstants.ROLE_NAME_PARENTESCO.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildParentescoDomRelatedInstance4Parentesco(relatedInstanceJsonOid, remainingPath);
        }
        return relatedInstance;
    }
    
    private AbstractAppEntity buildParentescoDomRelatedInstance(String relatedInstanceJsonOid) {
    	ObjectMapper mapper = new ObjectMapper();
    	try {
    		return new ParentescoDom(mapper.readValue(relatedInstanceJsonOid, ParentescoDomOid.class));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            throw new SystemException(e.getMessage());
    	}
    }

    private AbstractAppEntity buildParentescoDomRelatedInstance4Dominio(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new Dominio(mapper.readValue(relatedInstanceJsonOid, DominioOid.class)): Dominio.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            throw new SystemException(e.getMessage());
    	}
    }

    private AbstractAppEntity buildParentescoDomRelatedInstance4Parentesco(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new Parentesco(mapper.readValue(relatedInstanceJsonOid, ParentescoOid.class)): Parentesco.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            throw new SystemException(e.getMessage());
    	}
    }

    @Override
    protected void applyFilterByOid(String jsonOid, SQLSelect sql) {
        try {
            ParentescoDomOid oid = new ParentescoDomOid().fromJsonString(jsonOid);
            SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
            comparison1.setOperator("=");
            comparison1.setFirstOperand(new SQLWhereOperandPathAttribute(ParentescoDomConstants.ROLE_NAME_DOMINIO + "." + DominioConstants.ATTR_FIELD_DOMINIONUMERO, Constants.Type.AUTONUMERIC.getTypeName()));
            comparison1.setSecondOperand(new SQLWhereOperandLiteral(oid.getDominioNumero(), Constants.Type.AUTONUMERIC.getTypeName()));
            sql.addComparison(comparison1);
            SQLWhereComparison comparison2 = new SQLWhereComparison(sql);
            comparison2.setOperator("=");
            comparison2.setFirstOperand(new SQLWhereOperandPathAttribute(ParentescoDomConstants.ATTR_FIELD_PARENTESCODOMIDENTIFICADOR, Constants.Type.AUTONUMERIC.getTypeName()));
            comparison2.setSecondOperand(new SQLWhereOperandLiteral(oid.getParentescoDomIdentificador(), Constants.Type.AUTONUMERIC.getTypeName()));
            sql.addComparison(comparison2);
        } catch (Exception e) {
            LoggerFactory.getLogger(ParentescoDomService.class).error(e.getMessage());
            throw new SystemException(e.getMessage());
        }
    }

}
