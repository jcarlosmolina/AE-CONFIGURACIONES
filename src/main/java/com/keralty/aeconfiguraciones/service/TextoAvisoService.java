package com.keralty.aeconfiguraciones.service;

import com.keralty.aeconfiguraciones.exception.InstanceExistsException;
import com.keralty.aeconfiguraciones.exception.ModelException;
import com.keralty.aeconfiguraciones.global.Constants;
import com.keralty.aeconfiguraciones.global.TextoAvisoConstants;
import com.keralty.aeconfiguraciones.persistence.oid.TextoAvisoOid;
import com.keralty.aeconfiguraciones.persistence.TextoAviso;
import com.keralty.aeconfiguraciones.repository.TextoAvisoJpaRepository;
import com.keralty.aeconfiguraciones.sqlutils.SQLSelect;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereComparison;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereConjunction;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereDisjunction;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereOperandLiteral;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereOperandPathAttribute;
import com.keralty.aeconfiguraciones.viewmodel.FilterRequest;
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
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.LoggerFactory;

@Transactional
@ApplicationScoped
public class TextoAvisoService extends AbstractService {

    TextoAvisoJpaRepository repository;

    TextoAvisoService textoAvisoSrv;

    @Inject
    public TextoAvisoService(
            TextoAvisoService textoAvisoSrv,
            TextoAvisoJpaRepository repository) {
        this.textoAvisoSrv = textoAvisoSrv;
        this.repository = repository;
    }

    public List<Map<String, Object>> solveQuery(QueryRequest request) {
        return solveGenericQuery(request, TextoAvisoConstants.CLASS_NAME);
    }

    private List<TextoAviso> solveTextoAvisoTypedQuery(QueryRequest request) {
        return repository.solveQuery(processQueryRequest(request, TextoAvisoConstants.CLASS_NAME), request.getPageSize(), request.getPageNumber());
    }
    
    private int solveTextoAvisoTypedCountQuery(QueryRequest request) {
        return repository.solveCountQuery(processCountQueryRequest(request, TextoAvisoConstants.CLASS_NAME));
    }

    /*
     * Returns the population of class TextoAviso
     *
     * @return Population of class TextoAviso
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<TextoAviso> get() {
        List<TextoAviso> results = new java.util.ArrayList<>();
        repository.findAll().forEach(results::add);
        return results;
    }
    
    /*
     * Gets the preloaded list of objects of class TextoAviso
     *
     * @param queryRequest Request object that dictates the search conditions to 
     * retrieve objects
     *
     * @return Preloaded list of objects of class TextoAviso
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<PreloadViewModel> getPreload(QueryRequest queryRequest) {
        List<PreloadViewModel> results = new ArrayList<>();
        List<TextoAviso> instances = get(queryRequest);
        if(TextoAvisoConstants.DSDSTEXTOAVISO.equals(queryRequest.getDisplaySetName())) {
            results = instances.stream().map(x -> new PreloadViewModel(x.getOid().toJson(), x.buildDSTextoAviso())).collect(Collectors.toList());
        }
        if(TextoAvisoConstants.DSICTEXTOAVISO.equals(queryRequest.getDisplaySetName())) {
            results = instances.stream().map(x -> new PreloadViewModel(x.getOid().toJson(), x.buildICTextoAviso())).collect(Collectors.toList());
        }
        return results;
    }
    /*
     * Returns the population of class TextoAviso that matches certain search criteria
     *
     * @param queryRequest Search criteria
     *
     * @return Population of class TextoAviso matching the search criteria
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<TextoAviso> get(QueryRequest queryRequest) {
        return solveTextoAvisoTypedQuery(queryRequest);
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
            return new PopulationResponseViewModel(solveTextoAvisoTypedQuery(queryRequest).stream()
                    .map(instance -> buildDynamicObject(instance, queryRequest.getDisplaySetItems()))
                    .collect(Collectors.toList()), queryRequest.getPageSize() != 0 ? solveTextoAvisoTypedCountQuery(queryRequest) : 0);
        } else {
            return new PreloadResponseViewModel(solveTextoAvisoTypedQuery(queryRequest).stream()
                    .map(instance -> buildPreloadViewModel(instance, queryRequest.getDisplaySetName()))
                    .collect(Collectors.toList()));
        }
    }

    /*
     * Returns an instance of TextoAviso by ID
     *
     * @param textoAvisoIdentificador Identification field textoAvisoIdentificador
     *
     * @return Instance of TextoAviso
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public TextoAviso getById(String textoAvisoIdentificador) {
        return getByOID(new TextoAvisoOid(textoAvisoIdentificador));
    }
    
    /*
     * Returns an instance of TextoAviso by OID
     *
     * @param oid Object IDentifier
     *
     * @return Instance of TextoAviso
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public TextoAviso getByOID(TextoAvisoOid oid) {
        Optional<TextoAviso> instance = getInstanceByOid(oid);
        return instance.isPresent() ? instance.get() : null;
    }
    
    /*
     * Returns an instance of TextoAviso by OID applying horizontal visibility constraints
     *
     * @param oid Object IDentifier
     *
     * @return Instance of TextoAviso
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public TextoAviso getByOIDWithHV(TextoAvisoOid oid) {
        Optional<TextoAviso> instance = getInstanceByOid(oid);
    	return instance.isPresent() && instance.get().checkHorizontalVisibility(getAgentInstance()) 
    		 ? instance.get() 
    		 : null;
    }
    
    private Optional<TextoAviso> getInstanceByOid(TextoAvisoOid oid) {
        return oid != null && oid.getTextoAvisoIdentificador() != null
               ? repository.findById(oid.getTextoAvisoIdentificador())
               : Optional.empty();
    }

    /*
     * Returns an instance of TextoAviso by OID in JSON format
     *
     * @param jsonOid Object IDentifier in JSON format
     *
     * @return Instance of TextoAviso
     */
    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public TextoAviso getByJsonOid(String jsonOid) {
        TextoAvisoOid oid = new TextoAvisoOid();
        oid.fromJsonString(jsonOid);
        return getByOIDWithHV(oid);
    }

    /*
     * Returns a set of displayset items for an instance of TextoAviso
     *
     * @param oid Object IDentifier
     *
     * @param displaySetItems List of comma-separated displayset items
     *
     * @return Set of displayset items for an instance of TextoAviso
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public InstanceResponseViewModel getTextoAvisoDynamic(TextoAvisoOid oid, String displaySetItems) {
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
    public String getSuppInfo(TextoAvisoOid oid, String dsName) {
        TextoAviso instance = getByOIDWithHV(oid);
        return (instance != null ? getSuppInfo(instance, dsName): "");
    }

    private String getSuppInfo(TextoAviso instance, String dsName) {
        if (instance == null || instance.isNull()) return "";
        if (TextoAvisoConstants.DSICTEXTOAVISO.equals(dsName))
            return instance.buildICTextoAviso();
        return dsName.isEmpty() ? instance.getOid().toJson() : "";
    }

    public static PreloadViewModel buildPreloadViewModel(TextoAviso instance, String dsName) {
        if (TextoAvisoConstants.DSDSTEXTOAVISO.equals(dsName))
            return new PreloadViewModel(instance.getOid().toJson(), instance.buildDSTextoAviso());
        if (TextoAvisoConstants.DSICTEXTOAVISO.equals(dsName))
            return new PreloadViewModel(instance.getOid().toJson(), instance.buildICTextoAviso());
        return new PreloadViewModel(instance.getOid().toJson(), "");
    }


    @Override
    protected void applyServiceSIFilters(FilterRequest filterRequest, SQLSelect sql) {
        if ("SuppInfo_IC_TextoAviso".equals(filterRequest.getName()))
            suppInfo4ICTextoAviso(filterRequest.getFilterVariable("searchText").toString(), sql);
    }



    // IIU Methods


    /*
     * Solves the query needed for PIUTextoAviso
     *
     * @param queryRequest Query request
     * @return List of viewmodels for TextoAviso using displayset DSTextoAviso that 
     * match the search criteria
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<com.keralty.aeconfiguraciones.viewmodel.textoaviso.ds.DSTextoAviso> piuPIUTextoAviso(QueryRequest queryRequest) {
        return solveTextoAvisoTypedQuery(queryRequest).stream()
                .map(com.keralty.aeconfiguraciones.viewmodel.textoaviso.ds.DSTextoAviso::new)
                .collect(Collectors.toList());
    }

    /*
     * Solves the COUNT query needed for PIUTextoAviso
     *
     * @param queryRequest Query request
     * @returns Count of instances for PIUTextoAviso
     */
    public int getCount4PIUTextoAviso(QueryRequest queryRequest) {
        return solveTextoAvisoTypedQuery(queryRequest).size();
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
            com.keralty.aeconfiguraciones.viewmodel.textoaviso.siu.CrearRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = textoAvisoSrv.crearTxn(request);
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
            com.keralty.aeconfiguraciones.viewmodel.textoaviso.siu.CrearRequest request) throws ModelException {
        return textoAvisoSrv.crearImplementation(request);
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
            com.keralty.aeconfiguraciones.viewmodel.textoaviso.siu.CrearRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        String textoAvisocrearpatrIdentificador = request.getTextoAvisocrearpatrIdentificador();
        String textoAvisocrearpatrDescripcion = request.getTextoAvisocrearpatrDescripcion();
        String textoAvisocrearpatrValor = request.getTextoAvisocrearpatrValor();

        request.checkArguments();
        TextoAviso instance = new TextoAviso();
        instance.setTextoAvisoIdentificador(textoAvisocrearpatrIdentificador);
        instance.setTextoAvisoDescripcion(textoAvisocrearpatrDescripcion);
        instance.setTextoAvisoValor(textoAvisocrearpatrValor);
        if (textoAvisoSrv.getByOID(instance.getOid()) != null) {
            throw new InstanceExistsException(TextoAvisoConstants.CLASS_ALIAS);
        }

        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICTextoAviso());

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
            com.keralty.aeconfiguraciones.viewmodel.textoaviso.siu.EliminarRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = textoAvisoSrv.eliminarTxn(request);
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
            com.keralty.aeconfiguraciones.viewmodel.textoaviso.siu.EliminarRequest request) throws ModelException {
        request.setTextoAvisoeliminarpthisTextoAvisoInstance(textoAvisoSrv.getByOIDWithHV(request.getTextoAvisoeliminarpthisTextoAviso()));
        return textoAvisoSrv.eliminarImplementation(request);
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
            com.keralty.aeconfiguraciones.viewmodel.textoaviso.siu.EliminarRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        TextoAviso textoAvisoeliminarpthisTextoAviso = request.getTextoAvisoeliminarpthisTextoAvisoInstance();

        request.checkArguments();
        TextoAviso instance = textoAvisoeliminarpthisTextoAviso;


        repository.delete(instance);
        
        response.setInstance(instance);
        response.setInstanceSuppInfo(instance.buildICTextoAviso());

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
            com.keralty.aeconfiguraciones.viewmodel.textoaviso.siu.ModificarRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = textoAvisoSrv.modificarTxn(request);
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
            com.keralty.aeconfiguraciones.viewmodel.textoaviso.siu.ModificarRequest request) throws ModelException {
        request.setTextoAvisomodificarpthisTextoAvisoInstance(textoAvisoSrv.getByOIDWithHV(request.getTextoAvisomodificarpthisTextoAviso()));
        return textoAvisoSrv.modificarImplementation(request);
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
            com.keralty.aeconfiguraciones.viewmodel.textoaviso.siu.ModificarRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        TextoAviso textoAvisomodificarpthisTextoAviso = request.getTextoAvisomodificarpthisTextoAvisoInstance();
        String textoAvisomodificarpDescripcion = request.getTextoAvisomodificarpDescripcion();
        String textoAvisomodificarpValor = request.getTextoAvisomodificarpValor();

        request.checkArguments();
        TextoAviso instance = textoAvisomodificarpthisTextoAviso;
        instance.setTextoAvisoDescripcion(textoAvisomodificarpDescripcion);
        instance.setTextoAvisoValor(textoAvisomodificarpValor);


        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICTextoAviso());

        return response;
    }


    private void suppInfo4ICTextoAviso(String searchText, SQLSelect select) {
        try {
            SQLWhereDisjunction disjunction = new SQLWhereDisjunction();

            SQLWhereComparison comparison1 = new SQLWhereComparison(select);
            comparison1.setOperator("LIKE");
            comparison1.setFirstOperand(new SQLWhereOperandPathAttribute(TextoAvisoConstants.ATTR_NAME_TEXTOAVISOIDENTIFICADOR, Constants.Type.STRING.getTypeName()));
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
        applyDSItemSort(queryRequest, select);
        applyDefaultOrderCriteria(queryRequest, select);
    }

    private void applyDefaultOrderCriteria(QueryRequest queryRequest, SQLSelect select) {
        if (queryRequest.getOrderCriteriaName() != null && !queryRequest.getOrderCriteriaName().isBlank())
            return;
        if (queryRequest.getDsItemSort() != null && !queryRequest.getDsItemSort().isBlank())
            return;
        select.getOrderByPart().addItem(Constants.normalizePath(TextoAvisoConstants.CLASS_NAME, TextoAvisoConstants.ATTR_FIELD_TEXTOAVISOIDENTIFICADOR, false), true);
    }

    @Override
    protected void applyFilterByOid(String jsonOid, SQLSelect sql) {
        try {
            TextoAvisoOid oid = new TextoAvisoOid().fromJsonString(jsonOid);
            SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
            comparison1.setOperator("=");
            comparison1.setFirstOperand(new SQLWhereOperandPathAttribute(TextoAvisoConstants.ATTR_FIELD_TEXTOAVISOIDENTIFICADOR, Constants.Type.STRING.getTypeName()));
            comparison1.setSecondOperand(new SQLWhereOperandLiteral(oid.getTextoAvisoIdentificador(), Constants.Type.STRING.getTypeName()));
            sql.addComparison(comparison1);
        } catch (Exception e) {
            LoggerFactory.getLogger(TextoAvisoService.class).error(e.getMessage());
        }
    }

}
