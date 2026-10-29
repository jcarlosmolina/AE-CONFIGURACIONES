package com.keralty.aeconfiguraciones.service;

import com.keralty.aeconfiguraciones.exception.ModelException;
import com.keralty.aeconfiguraciones.exception.SystemException;
import com.keralty.aeconfiguraciones.global.Constants;
import com.keralty.aeconfiguraciones.global.MotDevAreaMedicaConstants;
import com.keralty.aeconfiguraciones.persistence.MotDevAreaMedica;
import com.keralty.aeconfiguraciones.persistence.oid.MotDevAreaMedicaOid;
import com.keralty.aeconfiguraciones.repository.MotDevAreaMedicaJpaRepository;
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
public class MotDevAreaMedicaService extends AbstractService {

    MotDevAreaMedicaJpaRepository repository;

    MotDevAreaMedicaService motDevAreaMedicaSrv;

    @Inject
    public MotDevAreaMedicaService(
            MotDevAreaMedicaService motDevAreaMedicaSrv,
            MotDevAreaMedicaJpaRepository repository) {
        this.motDevAreaMedicaSrv = motDevAreaMedicaSrv;
        this.repository = repository;
    }

    public List<Map<String, Object>> solveQuery(QueryRequest request) {
        return solveGenericQuery(request, MotDevAreaMedicaConstants.CLASS_NAME);
    }

    private List<MotDevAreaMedica> solveMotDevAreaMedicaTypedQuery(QueryRequest request) {
        return repository.solveQuery(processQueryRequest(request, MotDevAreaMedicaConstants.CLASS_NAME), request.getPageSize(), request.getPageNumber());
    }
    
    private int solveMotDevAreaMedicaTypedCountQuery(QueryRequest request) {
        return repository.solveCountQuery(processCountQueryRequest(request, MotDevAreaMedicaConstants.CLASS_NAME));
    }

    /*
     * Returns the population of class MotDevAreaMedica
     *
     * @return Population of class MotDevAreaMedica
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<MotDevAreaMedica> get() {
        List<MotDevAreaMedica> results = new java.util.ArrayList<>();
        repository.findAll().forEach(results::add);
        return results;
    }
    
    /*
     * Gets the preloaded list of objects of class MotDevAreaMedica
     *
     * @param queryRequest Request object that dictates the search conditions to 
     * retrieve objects
     *
     * @return Preloaded list of objects of class MotDevAreaMedica
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<PreloadViewModel> getPreload(QueryRequest queryRequest) {
        List<PreloadViewModel> results = new ArrayList<>();
        List<MotDevAreaMedica> instances = get(queryRequest);
        if(MotDevAreaMedicaConstants.DSDSMOTDEVAREAMEDICA.equals(queryRequest.getDisplaySetName())) {
            results = instances.stream().map(x -> new PreloadViewModel(x.getOid().toJson(), x.buildDSMotDevAreaMedica())).collect(Collectors.toList());
        }
        if(MotDevAreaMedicaConstants.DSICMOTDEVAREAMEDICA.equals(queryRequest.getDisplaySetName())) {
            results = instances.stream().map(x -> new PreloadViewModel(x.getOid().toJson(), x.buildICMotDevAreaMedica())).collect(Collectors.toList());
        }
        return results;
    }
    /*
     * Returns the population of class MotDevAreaMedica that matches certain search criteria
     *
     * @param queryRequest Search criteria
     *
     * @return Population of class MotDevAreaMedica matching the search criteria
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<MotDevAreaMedica> get(QueryRequest queryRequest) {
        return solveMotDevAreaMedicaTypedQuery(queryRequest);
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
            return new PopulationResponseViewModel(solveMotDevAreaMedicaTypedQuery(queryRequest).stream()
                    .map(instance -> buildDynamicObject(instance, queryRequest.getDisplaySetItems()))
                    .collect(Collectors.toList()), queryRequest.getPageSize() != 0 ? solveMotDevAreaMedicaTypedCountQuery(queryRequest) : 0);
        } else {
            return new PreloadResponseViewModel(solveMotDevAreaMedicaTypedQuery(queryRequest).stream()
                    .map(instance -> buildPreloadViewModel(instance, queryRequest.getDisplaySetName()))
                    .collect(Collectors.toList()));
        }
    }

    /*
     * Returns an instance of MotDevAreaMedica by ID
     *
     * @param motDevAreaMedicaId Identification field motDevAreaMedicaId
     *
     * @return Instance of MotDevAreaMedica
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public MotDevAreaMedica getById(Long motDevAreaMedicaId) {
        return getByOID(new MotDevAreaMedicaOid(motDevAreaMedicaId));
    }
    
    /*
     * Returns an instance of MotDevAreaMedica by OID
     *
     * @param oid Object IDentifier
     *
     * @return Instance of MotDevAreaMedica
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public MotDevAreaMedica getByOID(MotDevAreaMedicaOid oid) {
        Optional<MotDevAreaMedica> instance = getInstanceByOid(oid);
        return instance.isPresent() ? instance.get() : null;
    }
    
    /*
     * Returns an instance of MotDevAreaMedica by OID applying horizontal visibility constraints
     *
     * @param oid Object IDentifier
     *
     * @return Instance of MotDevAreaMedica
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public MotDevAreaMedica getByOIDWithHV(MotDevAreaMedicaOid oid) {
        Optional<MotDevAreaMedica> instance = getInstanceByOid(oid);
    	return instance.isPresent() && instance.get().checkHorizontalVisibility(getAgentInstance()) 
    		 ? instance.get() 
    		 : null;
    }
    
    private Optional<MotDevAreaMedica> getInstanceByOid(MotDevAreaMedicaOid oid) {
        return oid != null && oid.getMotDevAreaMedicaId() != null
               ? repository.findById(oid.getMotDevAreaMedicaId())
               : Optional.empty();
    }

    /*
     * Returns an instance of MotDevAreaMedica by OID in JSON format
     *
     * @param jsonOid Object IDentifier in JSON format
     *
     * @return Instance of MotDevAreaMedica
     */
    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public MotDevAreaMedica getByJsonOid(String jsonOid) {
        MotDevAreaMedicaOid oid = new MotDevAreaMedicaOid();
        oid.fromJsonString(jsonOid);
        return getByOIDWithHV(oid);
    }

    /*
     * Returns a set of displayset items for an instance of MotDevAreaMedica
     *
     * @param oid Object IDentifier
     *
     * @param displaySetItems List of comma-separated displayset items
     *
     * @return Set of displayset items for an instance of MotDevAreaMedica
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public InstanceResponseViewModel getMotDevAreaMedicaDynamic(MotDevAreaMedicaOid oid, String displaySetItems) {
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
    public String getSuppInfo(MotDevAreaMedicaOid oid, String dsName) {
        MotDevAreaMedica instance = getByOIDWithHV(oid);
        return (instance != null ? getSuppInfo(instance, dsName): "");
    }

    private String getSuppInfo(MotDevAreaMedica instance, String dsName) {
        if (instance == null || instance.isNull()) return "";
        if (MotDevAreaMedicaConstants.DSICMOTDEVAREAMEDICA.equals(dsName))
            return instance.buildICMotDevAreaMedica();
        return dsName.isEmpty() ? instance.getOid().toJson() : "";
    }

    public static PreloadViewModel buildPreloadViewModel(MotDevAreaMedica instance, String dsName) {
        if (MotDevAreaMedicaConstants.DSDSMOTDEVAREAMEDICA.equals(dsName))
            return new PreloadViewModel(instance.getOid().toJson(), instance.buildDSMotDevAreaMedica());
        if (MotDevAreaMedicaConstants.DSICMOTDEVAREAMEDICA.equals(dsName))
            return new PreloadViewModel(instance.getOid().toJson(), instance.buildICMotDevAreaMedica());
        return new PreloadViewModel(instance.getOid().toJson(), "");
    }


    @Override
    protected void applyServiceSIFilters(FilterRequest filterRequest, SQLSelect sql) {
        if ("SuppInfo_IC_MotDevAreaMedica".equals(filterRequest.getName()))
            suppInfo4ICMotDevAreaMedica(filterRequest.getFilterVariable("searchText").toString(), sql);
    }



    // IIU Methods


    /*
     * Solves the query needed for PIUMotDevAreaMedica
     *
     * @param queryRequest Query request
     * @return List of viewmodels for MotDevAreaMedica using displayset DSMotDevAreaMedica that 
     * match the search criteria
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<com.keralty.aeconfiguraciones.viewmodel.motdevareamedica.ds.DSMotDevAreaMedica> piuPIUMotDevAreaMedica(QueryRequest queryRequest) {
        return solveMotDevAreaMedicaTypedQuery(queryRequest).stream()
                .map(com.keralty.aeconfiguraciones.viewmodel.motdevareamedica.ds.DSMotDevAreaMedica::new)
                .collect(Collectors.toList());
    }

    /*
     * Solves the COUNT query needed for PIUMotDevAreaMedica
     *
     * @param queryRequest Query request
     * @returns Count of instances for PIUMotDevAreaMedica
     */
    public int getCount4PIUMotDevAreaMedica(QueryRequest queryRequest) {
        return solveMotDevAreaMedicaTypedQuery(queryRequest).size();
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
            com.keralty.aeconfiguraciones.viewmodel.motdevareamedica.siu.CrearRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = motDevAreaMedicaSrv.crearTxn(request);
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
            com.keralty.aeconfiguraciones.viewmodel.motdevareamedica.siu.CrearRequest request) throws ModelException {
        return motDevAreaMedicaSrv.crearImplementation(request);
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
            com.keralty.aeconfiguraciones.viewmodel.motdevareamedica.siu.CrearRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        String motDevAreaMedicacrearpatrDescripcion = request.getMotDevAreaMedicacrearpatrDescripcion();

        request.checkArguments();
        MotDevAreaMedica instance = new MotDevAreaMedica();
        instance.setMotDevAreaMedicaId(Long.valueOf(0));
        instance.setMotDevAreaMedicaDescripcion(motDevAreaMedicacrearpatrDescripcion);

        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICMotDevAreaMedica());

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
            com.keralty.aeconfiguraciones.viewmodel.motdevareamedica.siu.EliminarRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = motDevAreaMedicaSrv.eliminarTxn(request);
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
            com.keralty.aeconfiguraciones.viewmodel.motdevareamedica.siu.EliminarRequest request) throws ModelException {
        request.setMotDevAreaMedicaeliminarpthisMotDevAreaMedicaInstance(motDevAreaMedicaSrv.getByOIDWithHV(request.getMotDevAreaMedicaeliminarpthisMotDevAreaMedica()));
        return motDevAreaMedicaSrv.eliminarImplementation(request);
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
            com.keralty.aeconfiguraciones.viewmodel.motdevareamedica.siu.EliminarRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        MotDevAreaMedica motDevAreaMedicaeliminarpthisMotDevAreaMedica = request.getMotDevAreaMedicaeliminarpthisMotDevAreaMedicaInstance();

        request.checkArguments();
        MotDevAreaMedica instance = motDevAreaMedicaeliminarpthisMotDevAreaMedica;


        repository.delete(instance);
        
        response.setInstance(instance);
        response.setInstanceSuppInfo(instance.buildICMotDevAreaMedica());

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
            com.keralty.aeconfiguraciones.viewmodel.motdevareamedica.siu.ModificarRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = motDevAreaMedicaSrv.modificarTxn(request);
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
            com.keralty.aeconfiguraciones.viewmodel.motdevareamedica.siu.ModificarRequest request) throws ModelException {
        request.setMotDevAreaMedicamodificarpthisMotDevAreaMedicaInstance(motDevAreaMedicaSrv.getByOIDWithHV(request.getMotDevAreaMedicamodificarpthisMotDevAreaMedica()));
        return motDevAreaMedicaSrv.modificarImplementation(request);
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
            com.keralty.aeconfiguraciones.viewmodel.motdevareamedica.siu.ModificarRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        MotDevAreaMedica motDevAreaMedicamodificarpthisMotDevAreaMedica = request.getMotDevAreaMedicamodificarpthisMotDevAreaMedicaInstance();
        String motDevAreaMedicamodificarpDescripcion = request.getMotDevAreaMedicamodificarpDescripcion();

        request.checkArguments();
        MotDevAreaMedica instance = motDevAreaMedicamodificarpthisMotDevAreaMedica;
        instance.setMotDevAreaMedicaDescripcion(motDevAreaMedicamodificarpDescripcion);


        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICMotDevAreaMedica());

        return response;
    }


    private void suppInfo4ICMotDevAreaMedica(String searchText, SQLSelect select) {
        try {
            SQLWhereDisjunction disjunction = new SQLWhereDisjunction();

            SQLWhereComparison comparison1 = new SQLWhereComparison(select);
            comparison1.setOperator("LIKE");
            comparison1.setFirstOperand(new SQLWhereOperandPathAttribute(MotDevAreaMedicaConstants.ATTR_NAME_MOTDEVAREAMEDICADESCRIPCION, Constants.Type.STRING.getTypeName()));
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
        select.getOrderByPart().addItem(Constants.normalizePath(MotDevAreaMedicaConstants.CLASS_NAME, MotDevAreaMedicaConstants.ATTR_FIELD_MOTDEVAREAMEDICAID, false), true);
    }

    @Override
    protected void applyFilterByOid(String jsonOid, SQLSelect sql) {
        try {
            MotDevAreaMedicaOid oid = new MotDevAreaMedicaOid().fromJsonString(jsonOid);
            SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
            comparison1.setOperator("=");
            comparison1.setFirstOperand(new SQLWhereOperandPathAttribute(MotDevAreaMedicaConstants.ATTR_FIELD_MOTDEVAREAMEDICAID, Constants.Type.AUTONUMERIC.getTypeName()));
            comparison1.setSecondOperand(new SQLWhereOperandLiteral(oid.getMotDevAreaMedicaId(), Constants.Type.AUTONUMERIC.getTypeName()));
            sql.addComparison(comparison1);
        } catch (Exception e) {
            LoggerFactory.getLogger(MotDevAreaMedicaService.class).error(e.getMessage());
            throw new SystemException(e.getMessage());
        }
    }

}
