package com.keralty.aeconfiguraciones.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.keralty.aeconfiguraciones.exception.ModelException;
import com.keralty.aeconfiguraciones.exception.PreconditionException;
import com.keralty.aeconfiguraciones.exception.SystemException;
import com.keralty.aeconfiguraciones.global.Constants;
import com.keralty.aeconfiguraciones.global.PreguntaConstants;
import com.keralty.aeconfiguraciones.global.ValorConstants;
import com.keralty.aeconfiguraciones.persistence.AbstractAppEntity;
import com.keralty.aeconfiguraciones.persistence.oid.PreguntaOid;
import com.keralty.aeconfiguraciones.persistence.oid.ValorOid;
import com.keralty.aeconfiguraciones.persistence.Pregunta;
import com.keralty.aeconfiguraciones.persistence.Valor;
import com.keralty.aeconfiguraciones.repository.ValorJpaRepository;
import com.keralty.aeconfiguraciones.sqlutils.SQLSelect;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereComparison;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereConjunction;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereDisjunction;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereNegation;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereOperandLiteral;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereOperandPathAttribute;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereOperandSubQuery;
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
public class ValorService extends AbstractService {

    ValorJpaRepository repository;

    ValorService valorSrv;

    PreguntaService preguntaSrv;

    @Inject
    public ValorService(
            ValorService valorSrv,
            PreguntaService preguntaSrv,
            ValorJpaRepository repository) {
        this.valorSrv = valorSrv;
        this.preguntaSrv = preguntaSrv;
        this.repository = repository;
    }

    public List<Map<String, Object>> solveQuery(QueryRequest request) {
        return solveGenericQuery(request, ValorConstants.CLASS_NAME);
    }

    private List<Valor> solveValorTypedQuery(QueryRequest request) {
        return repository.solveQuery(processQueryRequest(request, ValorConstants.CLASS_NAME), request.getPageSize(), request.getPageNumber());
    }
    
    private int solveValorTypedCountQuery(QueryRequest request) {
        return repository.solveCountQuery(processCountQueryRequest(request, ValorConstants.CLASS_NAME));
    }

    /*
     * Returns the population of class Valor
     *
     * @return Population of class Valor
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<Valor> get() {
        List<Valor> results = new java.util.ArrayList<>();
        repository.findAll().forEach(results::add);
        return results;
    }
    
    /*
     * Gets the preloaded list of objects of class Valor
     *
     * @param queryRequest Request object that dictates the search conditions to 
     * retrieve objects
     *
     * @return Preloaded list of objects of class Valor
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<PreloadViewModel> getPreload(QueryRequest queryRequest) {
        List<PreloadViewModel> results = new ArrayList<>();
        List<Valor> instances = get(queryRequest);
        if(ValorConstants.DSICVALOR.equals(queryRequest.getDisplaySetName())) {
            results = instances.stream().map(x -> new PreloadViewModel(x.getOid().toJson(), x.buildICValor())).collect(Collectors.toList());
        }
        return results;
    }
    /*
     * Returns the population of class Valor that matches certain search criteria
     *
     * @param queryRequest Search criteria
     *
     * @return Population of class Valor matching the search criteria
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<Valor> get(QueryRequest queryRequest) {
        return solveValorTypedQuery(queryRequest);
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
            return new PopulationResponseViewModel(solveValorTypedQuery(queryRequest).stream()
                    .map(instance -> buildDynamicObject(instance, queryRequest.getDisplaySetItems()))
                    .collect(Collectors.toList()), queryRequest.getPageSize() != 0 ? solveValorTypedCountQuery(queryRequest) : 0);
        } else {
            return new PreloadResponseViewModel(solveValorTypedQuery(queryRequest).stream()
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
        return new PopulationResponseViewModel(solveValorTypedQuery(queryRequest).stream()
                .map(instance -> buildDynamicObject(instance, displaySetItems))
                .collect(Collectors.toList()));
    }

    /*
     * Returns an instance of Valor by ID
     *
     * @param valorIdentificador Identification field valorIdentificador
     *
     * @return Instance of Valor
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public Valor getById(Long valorIdentificador) {
        return getByOID(new ValorOid(valorIdentificador));
    }
    
    /*
     * Returns an instance of Valor by OID
     *
     * @param oid Object IDentifier
     *
     * @return Instance of Valor
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public Valor getByOID(ValorOid oid) {
        Optional<Valor> instance = getInstanceByOid(oid);
        return instance.isPresent() ? instance.get() : null;
    }
    
    /*
     * Returns an instance of Valor by OID applying horizontal visibility constraints
     *
     * @param oid Object IDentifier
     *
     * @return Instance of Valor
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public Valor getByOIDWithHV(ValorOid oid) {
        Optional<Valor> instance = getInstanceByOid(oid);
    	return instance.isPresent() && instance.get().checkHorizontalVisibility(getAgentInstance()) 
    		 ? instance.get() 
    		 : null;
    }
    
    private Optional<Valor> getInstanceByOid(ValorOid oid) {
        return oid != null && oid.getValorIdentificador() != null
               ? repository.findById(oid.getValorIdentificador())
               : Optional.empty();
    }

    /*
     * Returns an instance of Valor by OID in JSON format
     *
     * @param jsonOid Object IDentifier in JSON format
     *
     * @return Instance of Valor
     */
    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public Valor getByJsonOid(String jsonOid) {
        ValorOid oid = new ValorOid();
        oid.fromJsonString(jsonOid);
        return getByOIDWithHV(oid);
    }

    /*
     * Returns the list of instances of Valor related to a given instance through 
     * a given path
     *
     * @param relatedInstance Related instance
     *
     * @param relatedInstancePath Related instance path
     *
     * @return List of instances of Valor
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<Valor> getByRelated(AbstractAppEntity relatedInstance, String relatedInstancePath) {
        QueryRequest queryRequest = new QueryRequest(new FilterRequest(Constants.FILTER_BY_RELATED,
                Arrays.asList(new FilterVariableRequest(Constants.FILTER_BY_RELATED_FVAR_PATH, relatedInstancePath),
                        new FilterVariableRequest(Constants.FILTER_BY_RELATED_FVAR_OID, relatedInstance.getOid().toJson()))));
        return solveValorTypedQuery(queryRequest);
    }

    /*
     * Returns the list of instances of Valor related to a given instance through
     * a given path
     *
     * @param relatedInstance Related instance
     *
     * @param relatedInstancePath Related instance path
     *
     * @return List of instances of Valor
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<Valor> getInstancesByRelated(AbstractAppEntity relatedInstance, String relatedInstancePath) {
        return getByRelated(relatedInstance, relatedInstancePath);
    }

    /*
     * Returns a set of displayset items for an instance of Valor
     *
     * @param oid Object IDentifier
     *
     * @param displaySetItems List of comma-separated displayset items
     *
     * @return Set of displayset items for an instance of Valor
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public InstanceResponseViewModel getValorDynamic(ValorOid oid, String displaySetItems) {
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
    public String getSuppInfo(ValorOid oid, String dsName) {
        Valor instance = getByOIDWithHV(oid);
        return (instance != null ? getSuppInfo(instance, dsName): "");
    }

    private String getSuppInfo(Valor instance, String dsName) {
        if (instance == null || instance.isNull()) return "";
        if (ValorConstants.DSICVALOR.equals(dsName))
            return instance.buildICValor();
        return dsName.isEmpty() ? instance.getOid().toJson() : "";
    }

    public static PreloadViewModel buildPreloadViewModel(Valor instance, String dsName) {
        if (ValorConstants.DSICVALOR.equals(dsName))
            return new PreloadViewModel(instance.getOid().toJson(), instance.buildICValor());
        return new PreloadViewModel(instance.getOid().toJson(), "");
    }


    @Override
    protected void applyQueryFilters(FilterRequest filterRequest, SQLSelect sql) throws SystemException {
        if (ValorConstants.FILTER_NAME_IFVALORESPREGUNTA.equalsIgnoreCase(filterRequest.getName()))
            iFValoresPregunta(filterRequest, sql);
    }

    @Override
    protected void applyNavigationalFilters(FilterRequest filterRequest, SQLSelect sql) throws SystemException {
        if ("Pregunta_InsValorPregunta_p_evcValor_NavFilter0".equalsIgnoreCase(filterRequest.getName()))
            clas1431633788928040FiltNav1(filterRequest, sql);
        if ("Pregunta_DelValorPregunta_p_evcValor_NavFilter0".equalsIgnoreCase(filterRequest.getName()))
            clas1431633788928040FiltNav2(filterRequest, sql);
    }

    @Override
    protected void applyServiceSIFilters(FilterRequest filterRequest, SQLSelect sql) {
        if ("SuppInfo_IC_Valor".equals(filterRequest.getName()))
            suppInfo4ICValor(filterRequest.getFilterVariable("searchText").toString(), sql);
    }


    private void iFValoresPregunta(FilterRequest filterRequest, SQLSelect sql) throws SystemException{
        SQLWhereConjunction conjunction = new SQLWhereConjunction();
        Pregunta voPregunta = filterRequest.hasFilterVariable(ValorConstants.VAR_NAME_IFVALORESPREGUNTA_VOPREGUNTA) ? preguntaSrv.getByOID(new PreguntaOid().fromJsonString(toJson(filterRequest.getFilterVariable(ValorConstants.VAR_NAME_IFVALORESPREGUNTA_VOPREGUNTA)))) : null;

        // EXIST (Preguntas) WHERE (Preguntas = voPregunta) = true
        if (voPregunta != null) {
    // 'exist' Association operator
    SQLWhereOperandSubQuery operandSubQueryAssocOperator002 = new SQLWhereOperandSubQuery(repository.assocOperator002Range1Query(voPregunta, sql));
    conjunction.addOperand(operandSubQueryAssocOperator002);

        }        

        sql.addConjunction(conjunction);
    }

    private void clas1431633788928040FiltNav1(FilterRequest filterRequest, SQLSelect sql) throws SystemException{
        SQLWhereConjunction conjunction = new SQLWhereConjunction();
        Pregunta preguntaInsValorPreguntapthisPregunta = filterRequest.hasFilterVariable(PreguntaConstants.ARG_NAME_INSVALORPREGUNTA_PREGUNTAINSVALORPREGUNTAPTHISPREGUNTA) ? preguntaSrv.getByOID(new PreguntaOid().fromJsonString(toJson(filterRequest.getFilterVariable(PreguntaConstants.ARG_NAME_INSVALORPREGUNTA_PREGUNTAINSVALORPREGUNTAPTHISPREGUNTA)))) : null;

        // EXIST (Preguntas) WHERE (Preguntas = pthisPregunta) = false
        if (preguntaInsValorPreguntapthisPregunta != null) {
        {
            // 'not' Logical operator
            SQLWhereNegation comparison1 = new SQLWhereNegation(sql);
        
            // Operand 1
            // 'exist' Association operator
            SQLWhereOperandSubQuery operandSubQueryAssocOperator000 = new SQLWhereOperandSubQuery(repository.assocOperator000Range1Query(preguntaInsValorPreguntapthisPregunta, sql));
            comparison1.addOperand(operandSubQueryAssocOperator000);
            
            conjunction.addOperand(comparison1);
        }

        }        
        sql.addConjunction(conjunction);
    }

    private void clas1431633788928040FiltNav2(FilterRequest filterRequest, SQLSelect sql) throws SystemException{
        SQLWhereConjunction conjunction = new SQLWhereConjunction();
        Pregunta preguntaDelValorPreguntapthisPregunta = filterRequest.hasFilterVariable(PreguntaConstants.ARG_NAME_DELVALORPREGUNTA_PREGUNTADELVALORPREGUNTAPTHISPREGUNTA) ? preguntaSrv.getByOID(new PreguntaOid().fromJsonString(toJson(filterRequest.getFilterVariable(PreguntaConstants.ARG_NAME_DELVALORPREGUNTA_PREGUNTADELVALORPREGUNTAPTHISPREGUNTA)))) : null;

        // EXIST (Preguntas) WHERE (Preguntas = pthisPregunta) = true
        if (preguntaDelValorPreguntapthisPregunta != null) {
    // 'exist' Association operator
    SQLWhereOperandSubQuery operandSubQueryAssocOperator001 = new SQLWhereOperandSubQuery(repository.assocOperator001Range1Query(preguntaDelValorPreguntapthisPregunta, sql));
    conjunction.addOperand(operandSubQueryAssocOperator001);

        }        
        sql.addConjunction(conjunction);
    }

    // IIU Methods


    /*
     * Solves the query needed for PIUValor
     *
     * @param queryRequest Query request
     * @return List of viewmodels for Valor using displayset AutoDS that 
     * match the search criteria
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<com.keralty.aeconfiguraciones.viewmodel.valor.ds.AutoDS> piuPIUValor(QueryRequest queryRequest) {
        return solveValorTypedQuery(queryRequest).stream()
                .map(com.keralty.aeconfiguraciones.viewmodel.valor.ds.AutoDS::new)
                .collect(Collectors.toList());
    }

    /*
     * Solves the COUNT query needed for PIUValor
     *
     * @param queryRequest Query request
     * @returns Count of instances for PIUValor
     */
    public int getCount4PIUValor(QueryRequest queryRequest) {
        return solveValorTypedQuery(queryRequest).size();
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
            com.keralty.aeconfiguraciones.viewmodel.valor.siu.CrearRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = valorSrv.crearTxn(request);
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
            com.keralty.aeconfiguraciones.viewmodel.valor.siu.CrearRequest request) throws ModelException {
        return valorSrv.crearImplementation(request);
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
            com.keralty.aeconfiguraciones.viewmodel.valor.siu.CrearRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        String valorcrearpatrValorPosible = request.getValorcrearpatrValorPosible();

        request.checkArguments();
        Valor instance = new Valor();
        instance.setValorIdentificador(Long.valueOf(0));
        instance.setValorValorPosible(valorcrearpatrValorPosible);

        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICValor());

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
            com.keralty.aeconfiguraciones.viewmodel.valor.siu.EliminarRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = valorSrv.eliminarTxn(request);
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
            com.keralty.aeconfiguraciones.viewmodel.valor.siu.EliminarRequest request) throws ModelException {
        request.setValoreliminarpthisValorInstance(valorSrv.getByOIDWithHV(request.getValoreliminarpthisValor()));
        return valorSrv.eliminarImplementation(request);
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
            com.keralty.aeconfiguraciones.viewmodel.valor.siu.EliminarRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Valor valoreliminarpthisValor = request.getValoreliminarpthisValorInstance();

        request.checkArguments();

        eliminarPreconditions(request);
        Valor instance = valoreliminarpthisValor;


        repository.delete(instance);
        
        response.setInstance(instance);
        response.setInstanceSuppInfo(instance.buildICValor());

        return response;
    }

    /*
     * Checks service eliminar preconditions individually
     *
     * @param request Service request viewmodel
     *
     * @return Service preconditions evaluation result
     */
    public ServiceResponse eliminarCheckPreconditions(com.keralty.aeconfiguraciones.viewmodel.valor.siu.EliminarRequest request) {
        ServiceResponse response = new ServiceResponse();
        request.setValoreliminarpthisValorInstance(valorSrv.getByOIDWithHV(request.getValoreliminarpthisValor()));
        try {
            if("Clas_1431634313216388Pre_1".equals(request.getPreconditionId()))
                checkClas1431634313216388Pre1(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    private void eliminarPreconditions(
            com.keralty.aeconfiguraciones.viewmodel.valor.siu.EliminarRequest request) 
            throws ModelException {
        checkClas1431634313216388Pre1(request);
    }

    private void checkClas1431634313216388Pre1 (
            com.keralty.aeconfiguraciones.viewmodel.valor.siu.EliminarRequest request) 
            throws ModelException {
        // EXIST(Preguntas) = FALSE

        Valor valoreliminarpthisValor = request.getValoreliminarpthisValorInstance();
        try {
            boolean preconditionHolds = repository.assocOperator003(valoreliminarpthisValor).equals(false);
            if (!preconditionHolds) throw new PreconditionException("No se puede eliminar el valor, porque existen preguntas que lo tienen indicado como posible valor");
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
            com.keralty.aeconfiguraciones.viewmodel.valor.siu.ModificarRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = valorSrv.modificarTxn(request);
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
            com.keralty.aeconfiguraciones.viewmodel.valor.siu.ModificarRequest request) throws ModelException {
        request.setValormodificarpthisValorInstance(valorSrv.getByOIDWithHV(request.getValormodificarpthisValor()));
        return valorSrv.modificarImplementation(request);
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
            com.keralty.aeconfiguraciones.viewmodel.valor.siu.ModificarRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Valor valormodificarpthisValor = request.getValormodificarpthisValorInstance();
        String valormodificarpValorPosible = request.getValormodificarpValorPosible();

        request.checkArguments();
        Valor instance = valormodificarpthisValor;
        instance.setValorValorPosible(valormodificarpValorPosible);


        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICValor());

        return response;
    }

    // Service: InsValorPregunta

    /*
     * Entry point for service InsValorPregunta
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse insValorPregunta(
            com.keralty.aeconfiguraciones.viewmodel.valor.siu.InsValorPreguntaRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = valorSrv.insValorPreguntaTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service InsValorPregunta
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse insValorPreguntaTxn(
            com.keralty.aeconfiguraciones.viewmodel.valor.siu.InsValorPreguntaRequest request) throws ModelException {
        request.setValorInsValorPreguntapthisValorInstance(valorSrv.getByOIDWithHV(request.getValorInsValorPreguntapthisValor()));
        request.setValorInsValorPreguntapevcPreguntaInstance(preguntaSrv.getByOIDWithHV(request.getValorInsValorPreguntapevcPregunta()));
        return valorSrv.insValorPreguntaImplementation(request, true);
    }

    /*
     * Implements service InsValorPregunta
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse insValorPreguntaImplementation(
            com.keralty.aeconfiguraciones.viewmodel.valor.siu.InsValorPreguntaRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Valor valorInsValorPreguntapthisValor = request.getValorInsValorPreguntapthisValorInstance();
        Pregunta valorInsValorPreguntapevcPregunta = request.getValorInsValorPreguntapevcPreguntaInstance();

        request.checkArguments();
        Valor instance = valorInsValorPreguntapthisValor;

        instance.add2Preguntas(valorInsValorPreguntapevcPregunta);
        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.pregunta.siu.InsValorPreguntaRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.pregunta.siu.InsValorPreguntaRequest();
            relatedRequest.setPreguntaInsValorPreguntapthisPreguntaInstance(valorInsValorPreguntapevcPregunta);
            relatedRequest.setPreguntaInsValorPreguntapevcValorInstance(valorInsValorPreguntapthisValor);
            preguntaSrv.insValorPreguntaImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICValor());

        return response;
    }

    // Service: DelValorPregunta

    /*
     * Entry point for service DelValorPregunta
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse delValorPregunta(
            com.keralty.aeconfiguraciones.viewmodel.valor.siu.DelValorPreguntaRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = valorSrv.delValorPreguntaTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service DelValorPregunta
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse delValorPreguntaTxn(
            com.keralty.aeconfiguraciones.viewmodel.valor.siu.DelValorPreguntaRequest request) throws ModelException {
        request.setValorDelValorPreguntapthisValorInstance(valorSrv.getByOIDWithHV(request.getValorDelValorPreguntapthisValor()));
        request.setValorDelValorPreguntapevcPreguntaInstance(preguntaSrv.getByOIDWithHV(request.getValorDelValorPreguntapevcPregunta()));
        return valorSrv.delValorPreguntaImplementation(request, true);
    }

    /*
     * Implements service DelValorPregunta
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse delValorPreguntaImplementation(
            com.keralty.aeconfiguraciones.viewmodel.valor.siu.DelValorPreguntaRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Valor valorDelValorPreguntapthisValor = request.getValorDelValorPreguntapthisValorInstance();
        Pregunta valorDelValorPreguntapevcPregunta = request.getValorDelValorPreguntapevcPreguntaInstance();

        request.checkArguments();
        Valor instance = valorDelValorPreguntapthisValor;

        instance.removeFromPreguntas(valorDelValorPreguntapevcPregunta);
        valorDelValorPreguntapevcPregunta.removeFromValores(instance);
        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.pregunta.siu.DelValorPreguntaRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.pregunta.siu.DelValorPreguntaRequest();
            relatedRequest.setPreguntaDelValorPreguntapthisPreguntaInstance(valorDelValorPreguntapevcPregunta);
            relatedRequest.setPreguntaDelValorPreguntapevcValorInstance(valorDelValorPreguntapthisValor);
            preguntaSrv.delValorPreguntaImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICValor());

        return response;
    }


    private void suppInfo4ICValor(String searchText, SQLSelect select) {
        try {
            SQLWhereDisjunction disjunction = new SQLWhereDisjunction();

            SQLWhereComparison comparison1 = new SQLWhereComparison(select);
            comparison1.setOperator("LIKE");
            comparison1.setFirstOperand(new SQLWhereOperandPathAttribute(ValorConstants.ATTR_NAME_VALORVALORPOSIBLE, Constants.Type.STRING.getTypeName()));
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
        select.getOrderByPart().addItem(Constants.normalizePath(ValorConstants.CLASS_NAME, ValorConstants.ATTR_FIELD_VALORIDENTIFICADOR, false), true);
    }
    
    @Override
    protected AbstractAppEntity buildRelatedInstance(String relatedInstanceJsonOid, String relatedInstancePathFirstItem, String remainingPath) {
        AbstractAppEntity relatedInstance = null;
        if (relatedInstancePathFirstItem.isEmpty()) {
            relatedInstance = buildValorRelatedInstance(relatedInstanceJsonOid);
        }
        if (ValorConstants.ROLE_NAME_PREGUNTAS.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildValorRelatedInstance4Pregunta(relatedInstanceJsonOid, remainingPath);
        }
        return relatedInstance;
    }
    
    private AbstractAppEntity buildValorRelatedInstance(String relatedInstanceJsonOid) {
    	ObjectMapper mapper = new ObjectMapper();
    	try {
    		return new Valor(mapper.readValue(relatedInstanceJsonOid, ValorOid.class));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            throw new SystemException(e.getMessage());
    	}
    }

    private AbstractAppEntity buildValorRelatedInstance4Pregunta(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new Pregunta(mapper.readValue(relatedInstanceJsonOid, PreguntaOid.class)): Pregunta.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            throw new SystemException(e.getMessage());
    	}
    }

    @Override
    protected void applyFilterByOid(String jsonOid, SQLSelect sql) {
        try {
            ValorOid oid = new ValorOid().fromJsonString(jsonOid);
            SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
            comparison1.setOperator("=");
            comparison1.setFirstOperand(new SQLWhereOperandPathAttribute(ValorConstants.ATTR_FIELD_VALORIDENTIFICADOR, Constants.Type.AUTONUMERIC.getTypeName()));
            comparison1.setSecondOperand(new SQLWhereOperandLiteral(oid.getValorIdentificador(), Constants.Type.AUTONUMERIC.getTypeName()));
            sql.addComparison(comparison1);
        } catch (Exception e) {
            LoggerFactory.getLogger(ValorService.class).error(e.getMessage());
            throw new SystemException(e.getMessage());
        }
    }

}
