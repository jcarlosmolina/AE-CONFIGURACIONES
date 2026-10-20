package com.keralty.aeconfiguraciones.service;

import com.keralty.aeconfiguraciones.exception.InstanceExistsException;
import com.keralty.aeconfiguraciones.exception.ModelException;
import com.keralty.aeconfiguraciones.global.Constants;
import com.keralty.aeconfiguraciones.global.EntidadFinancieraCConstants;
import com.keralty.aeconfiguraciones.persistence.EntidadFinancieraC;
import com.keralty.aeconfiguraciones.persistence.oid.EntidadFinancieraCOid;
import com.keralty.aeconfiguraciones.repository.EntidadFinancieraCJpaRepository;
import com.keralty.aeconfiguraciones.sqlutils.SQLSelect;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereComparison;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereOperandLiteral;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereOperandPathAttribute;
import com.keralty.aeconfiguraciones.viewmodel.InstanceResponseViewModel;
import com.keralty.aeconfiguraciones.viewmodel.PopulationResponseViewModel;
import com.keralty.aeconfiguraciones.viewmodel.PreloadResponseViewModel;
import com.keralty.aeconfiguraciones.viewmodel.QueryRequest;
import com.keralty.aeconfiguraciones.viewmodel.QueryResponse;
import com.keralty.aeconfiguraciones.viewmodel.ServiceResponse;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.LoggerFactory;

@Transactional
@ApplicationScoped
public class EntidadFinancieraCService extends AbstractService {

    EntidadFinancieraCJpaRepository repository;

    EntidadFinancieraCService entidadFinancieraCSrv;

    @Inject
    public EntidadFinancieraCService(
            EntidadFinancieraCService entidadFinancieraCSrv,
            EntidadFinancieraCJpaRepository repository) {
        this.entidadFinancieraCSrv = entidadFinancieraCSrv;
        this.repository = repository;
    }

    public List<Map<String, Object>> solveQuery(QueryRequest request) {
        return solveGenericQuery(request, EntidadFinancieraCConstants.CLASS_NAME);
    }

    private List<EntidadFinancieraC> solveEntidadFinancieraCTypedQuery(QueryRequest request) {
        return repository.solveQuery(processQueryRequest(request, EntidadFinancieraCConstants.CLASS_NAME), request.getPageSize(), request.getPageNumber());
    }
    
    private int solveEntidadFinancieraCTypedCountQuery(QueryRequest request) {
        return repository.solveCountQuery(processCountQueryRequest(request, EntidadFinancieraCConstants.CLASS_NAME));
    }

    /*
     * Returns the population of class EntidadFinancieraC
     *
     * @return Population of class EntidadFinancieraC
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<EntidadFinancieraC> get() {
        List<EntidadFinancieraC> results = new java.util.ArrayList<>();
        repository.findAll().forEach(results::add);
        return results;
    }
    /*
     * Returns the population of class EntidadFinancieraC that matches certain search criteria
     *
     * @param queryRequest Search criteria
     *
     * @return Population of class EntidadFinancieraC matching the search criteria
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<EntidadFinancieraC> get(QueryRequest queryRequest) {
        return solveEntidadFinancieraCTypedQuery(queryRequest);
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
            return new PopulationResponseViewModel(solveEntidadFinancieraCTypedQuery(queryRequest).stream()
                    .map(instance -> buildDynamicObject(instance, queryRequest.getDisplaySetItems()))
                    .collect(Collectors.toList()), queryRequest.getPageSize() != 0 ? solveEntidadFinancieraCTypedCountQuery(queryRequest) : 0);
        } else {
            return new PreloadResponseViewModel();
        }
    }

    /*
     * Returns an instance of EntidadFinancieraC by ID
     *
     * @param entidadFinancieraCCodigo Identification field entidadFinancieraCCodigo
     *
     * @return Instance of EntidadFinancieraC
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public EntidadFinancieraC getById(Long entidadFinancieraCCodigo) {
        return getByOID(new EntidadFinancieraCOid(entidadFinancieraCCodigo));
    }
    
    /*
     * Returns an instance of EntidadFinancieraC by OID
     *
     * @param oid Object IDentifier
     *
     * @return Instance of EntidadFinancieraC
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public EntidadFinancieraC getByOID(EntidadFinancieraCOid oid) {
        Optional<EntidadFinancieraC> instance = getInstanceByOid(oid);
        return instance.isPresent() ? instance.get() : null;
    }
    
    /*
     * Returns an instance of EntidadFinancieraC by OID applying horizontal visibility constraints
     *
     * @param oid Object IDentifier
     *
     * @return Instance of EntidadFinancieraC
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public EntidadFinancieraC getByOIDWithHV(EntidadFinancieraCOid oid) {
        Optional<EntidadFinancieraC> instance = getInstanceByOid(oid);
    	return instance.isPresent() && instance.get().checkHorizontalVisibility(getAgentInstance()) 
    		 ? instance.get() 
    		 : null;
    }
    
    private Optional<EntidadFinancieraC> getInstanceByOid(EntidadFinancieraCOid oid) {
        return oid != null && oid.getEntidadFinancieraCCodigo() != null
               ? repository.findById(oid.getEntidadFinancieraCCodigo())
               : Optional.empty();
    }

    /*
     * Returns an instance of EntidadFinancieraC by OID in JSON format
     *
     * @param jsonOid Object IDentifier in JSON format
     *
     * @return Instance of EntidadFinancieraC
     */
    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public EntidadFinancieraC getByJsonOid(String jsonOid) {
        EntidadFinancieraCOid oid = new EntidadFinancieraCOid();
        oid.fromJsonString(jsonOid);
        return getByOIDWithHV(oid);
    }

    /*
     * Returns a set of displayset items for an instance of EntidadFinancieraC
     *
     * @param oid Object IDentifier
     *
     * @param displaySetItems List of comma-separated displayset items
     *
     * @return Set of displayset items for an instance of EntidadFinancieraC
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public InstanceResponseViewModel getEntidadFinancieraCDynamic(EntidadFinancieraCOid oid, String displaySetItems) {
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
    public String getSuppInfo(EntidadFinancieraCOid oid, String dsName) {
        EntidadFinancieraC instance = getByOIDWithHV(oid);
        return (instance != null ? getSuppInfo(instance, dsName): "");
    }

    private String getSuppInfo(EntidadFinancieraC instance, String dsName) {
        if (instance == null || instance.isNull()) return "";
        return dsName.isEmpty() ? instance.getOid().toJson() : "";
    }




    // IIU Methods


    /*
     * Solves the query needed for PIUEntidadFinanciera
     *
     * @param queryRequest Query request
     * @return List of viewmodels for EntidadFinancieraC using displayset AutoDS that 
     * match the search criteria
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<com.keralty.aeconfiguraciones.viewmodel.entidadfinancierac.ds.AutoDS> piuPIUEntidadFinanciera(QueryRequest queryRequest) {
        return solveEntidadFinancieraCTypedQuery(queryRequest).stream()
                .map(com.keralty.aeconfiguraciones.viewmodel.entidadfinancierac.ds.AutoDS::new)
                .collect(Collectors.toList());
    }

    /*
     * Solves the COUNT query needed for PIUEntidadFinanciera
     *
     * @param queryRequest Query request
     * @returns Count of instances for PIUEntidadFinanciera
     */
    public int getCount4PIUEntidadFinanciera(QueryRequest queryRequest) {
        return solveEntidadFinancieraCTypedQuery(queryRequest).size();
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
            com.keralty.aeconfiguraciones.viewmodel.entidadfinancierac.siu.CrearRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = entidadFinancieraCSrv.crearTxn(request);
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
            com.keralty.aeconfiguraciones.viewmodel.entidadfinancierac.siu.CrearRequest request) throws ModelException {
        return entidadFinancieraCSrv.crearImplementation(request);
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
            com.keralty.aeconfiguraciones.viewmodel.entidadfinancierac.siu.CrearRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Long entidadFinancieraCcrearpatrCodigo = request.getEntidadFinancieraCcrearpatrCodigo();
        String entidadFinancieraCcrearpatrDescripcion = request.getEntidadFinancieraCcrearpatrDescripcion();

        request.checkArguments();
        EntidadFinancieraC instance = new EntidadFinancieraC();
        instance.setEntidadFinancieraCCodigo(entidadFinancieraCcrearpatrCodigo);
        instance.setEntidadFinancieraCDescripcion(entidadFinancieraCcrearpatrDescripcion);
        if (entidadFinancieraCSrv.getByOID(instance.getOid()) != null) {
            throw new InstanceExistsException(EntidadFinancieraCConstants.CLASS_ALIAS);
        }

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
            com.keralty.aeconfiguraciones.viewmodel.entidadfinancierac.siu.EliminarRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = entidadFinancieraCSrv.eliminarTxn(request);
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
            com.keralty.aeconfiguraciones.viewmodel.entidadfinancierac.siu.EliminarRequest request) throws ModelException {
        request.setEntidadFinancieraCeliminarpthisEntidadFinancieraInstance(entidadFinancieraCSrv.getByOIDWithHV(request.getEntidadFinancieraCeliminarpthisEntidadFinanciera()));
        return entidadFinancieraCSrv.eliminarImplementation(request);
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
            com.keralty.aeconfiguraciones.viewmodel.entidadfinancierac.siu.EliminarRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        EntidadFinancieraC entidadFinancieraCeliminarpthisEntidadFinanciera = request.getEntidadFinancieraCeliminarpthisEntidadFinancieraInstance();

        request.checkArguments();
        EntidadFinancieraC instance = entidadFinancieraCeliminarpthisEntidadFinanciera;


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
            com.keralty.aeconfiguraciones.viewmodel.entidadfinancierac.siu.ModificarRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = entidadFinancieraCSrv.modificarTxn(request);
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
            com.keralty.aeconfiguraciones.viewmodel.entidadfinancierac.siu.ModificarRequest request) throws ModelException {
        request.setEntidadFinancieraCmodificarpthisEntidadFinancieraCInstance(entidadFinancieraCSrv.getByOIDWithHV(request.getEntidadFinancieraCmodificarpthisEntidadFinancieraC()));
        return entidadFinancieraCSrv.modificarImplementation(request);
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
            com.keralty.aeconfiguraciones.viewmodel.entidadfinancierac.siu.ModificarRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        EntidadFinancieraC entidadFinancieraCmodificarpthisEntidadFinancieraC = request.getEntidadFinancieraCmodificarpthisEntidadFinancieraCInstance();
        String entidadFinancieraCmodificarpDescripcion = request.getEntidadFinancieraCmodificarpDescripcion();

        request.checkArguments();
        EntidadFinancieraC instance = entidadFinancieraCmodificarpthisEntidadFinancieraC;
        instance.setEntidadFinancieraCDescripcion(entidadFinancieraCmodificarpDescripcion);


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
        select.getOrderByPart().addItem(Constants.normalizePath(EntidadFinancieraCConstants.CLASS_NAME, EntidadFinancieraCConstants.ATTR_FIELD_ENTIDADFINANCIERACCODIGO, false), true);
    }

    @Override
    protected void applyFilterByOid(String jsonOid, SQLSelect sql) {
        try {
            EntidadFinancieraCOid oid = new EntidadFinancieraCOid().fromJsonString(jsonOid);
            SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
            comparison1.setOperator("=");
            comparison1.setFirstOperand(new SQLWhereOperandPathAttribute(EntidadFinancieraCConstants.ATTR_FIELD_ENTIDADFINANCIERACCODIGO, Constants.Type.INT.getTypeName()));
            comparison1.setSecondOperand(new SQLWhereOperandLiteral(oid.getEntidadFinancieraCCodigo(), Constants.Type.INT.getTypeName()));
            sql.addComparison(comparison1);
        } catch (Exception e) {
            LoggerFactory.getLogger(EntidadFinancieraCService.class).error(e.getMessage());
        }
    }

}
