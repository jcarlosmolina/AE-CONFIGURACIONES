package com.keralty.aeconfiguraciones.service;

import com.keralty.aeconfiguraciones.exception.ModelException;
import com.keralty.aeconfiguraciones.global.ConfiguracionFestivosConstants;
import com.keralty.aeconfiguraciones.global.Constants;
import com.keralty.aeconfiguraciones.persistence.ConfiguracionFestivos;
import com.keralty.aeconfiguraciones.persistence.oid.ConfiguracionFestivosOid;
import com.keralty.aeconfiguraciones.repository.ConfiguracionFestivosJpaRepository;
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
import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.LoggerFactory;

@Transactional
@ApplicationScoped
public class ConfiguracionFestivosService extends AbstractService {

    ConfiguracionFestivosJpaRepository repository;

    ConfiguracionFestivosService configuracionFestivosSrv;

    @Inject
    public ConfiguracionFestivosService(
            ConfiguracionFestivosService configuracionFestivosSrv,
            ConfiguracionFestivosJpaRepository repository) {
        this.configuracionFestivosSrv = configuracionFestivosSrv;
        this.repository = repository;
    }

    public List<Map<String, Object>> solveQuery(QueryRequest request) {
        return solveGenericQuery(request, ConfiguracionFestivosConstants.CLASS_NAME);
    }

    private List<ConfiguracionFestivos> solveConfiguracionFestivosTypedQuery(QueryRequest request) {
        return repository.solveQuery(processQueryRequest(request, ConfiguracionFestivosConstants.CLASS_NAME), request.getPageSize(), request.getPageNumber());
    }
    
    private int solveConfiguracionFestivosTypedCountQuery(QueryRequest request) {
        return repository.solveCountQuery(processCountQueryRequest(request, ConfiguracionFestivosConstants.CLASS_NAME));
    }

    /*
     * Returns the population of class ConfiguracionFestivos
     *
     * @return Population of class ConfiguracionFestivos
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<ConfiguracionFestivos> get() {
        List<ConfiguracionFestivos> results = new java.util.ArrayList<>();
        repository.findAll().forEach(results::add);
        return results;
    }
    /*
     * Returns the population of class ConfiguracionFestivos that matches certain search criteria
     *
     * @param queryRequest Search criteria
     *
     * @return Population of class ConfiguracionFestivos matching the search criteria
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<ConfiguracionFestivos> get(QueryRequest queryRequest) {
        return solveConfiguracionFestivosTypedQuery(queryRequest);
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
            return new PopulationResponseViewModel(solveConfiguracionFestivosTypedQuery(queryRequest).stream()
                    .map(instance -> buildDynamicObject(instance, queryRequest.getDisplaySetItems()))
                    .collect(Collectors.toList()), queryRequest.getPageSize() != 0 ? solveConfiguracionFestivosTypedCountQuery(queryRequest) : 0);
        } else {
            return new PreloadResponseViewModel();
        }
    }

    /*
     * Returns an instance of ConfiguracionFestivos by ID
     *
     * @param configuracionFestivosId Identification field configuracionFestivosId
     *
     * @return Instance of ConfiguracionFestivos
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ConfiguracionFestivos getById(Long configuracionFestivosId) {
        return getByOID(new ConfiguracionFestivosOid(configuracionFestivosId));
    }
    
    /*
     * Returns an instance of ConfiguracionFestivos by OID
     *
     * @param oid Object IDentifier
     *
     * @return Instance of ConfiguracionFestivos
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ConfiguracionFestivos getByOID(ConfiguracionFestivosOid oid) {
        Optional<ConfiguracionFestivos> instance = getInstanceByOid(oid);
        return instance.isPresent() ? instance.get() : null;
    }
    
    /*
     * Returns an instance of ConfiguracionFestivos by OID applying horizontal visibility constraints
     *
     * @param oid Object IDentifier
     *
     * @return Instance of ConfiguracionFestivos
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public ConfiguracionFestivos getByOIDWithHV(ConfiguracionFestivosOid oid) {
        Optional<ConfiguracionFestivos> instance = getInstanceByOid(oid);
    	return instance.isPresent() && instance.get().checkHorizontalVisibility(getAgentInstance()) 
    		 ? instance.get() 
    		 : null;
    }
    
    private Optional<ConfiguracionFestivos> getInstanceByOid(ConfiguracionFestivosOid oid) {
        return oid != null && oid.getConfiguracionFestivosId() != null
               ? repository.findById(oid.getConfiguracionFestivosId())
               : Optional.empty();
    }

    /*
     * Returns an instance of ConfiguracionFestivos by OID in JSON format
     *
     * @param jsonOid Object IDentifier in JSON format
     *
     * @return Instance of ConfiguracionFestivos
     */
    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public ConfiguracionFestivos getByJsonOid(String jsonOid) {
        ConfiguracionFestivosOid oid = new ConfiguracionFestivosOid();
        oid.fromJsonString(jsonOid);
        return getByOIDWithHV(oid);
    }

    /*
     * Returns a set of displayset items for an instance of ConfiguracionFestivos
     *
     * @param oid Object IDentifier
     *
     * @param displaySetItems List of comma-separated displayset items
     *
     * @return Set of displayset items for an instance of ConfiguracionFestivos
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public InstanceResponseViewModel getConfiguracionFestivosDynamic(ConfiguracionFestivosOid oid, String displaySetItems) {
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
    public String getSuppInfo(ConfiguracionFestivosOid oid, String dsName) {
        ConfiguracionFestivos instance = getByOIDWithHV(oid);
        return (instance != null ? getSuppInfo(instance, dsName): "");
    }

    private String getSuppInfo(ConfiguracionFestivos instance, String dsName) {
        if (instance == null || instance.isNull()) return "";
        return dsName.isEmpty() ? instance.getOid().toJson() : "";
    }




    // IIU Methods



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
            com.keralty.aeconfiguraciones.viewmodel.configuracionfestivos.siu.CrearRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = configuracionFestivosSrv.crearTxn(request);
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
            com.keralty.aeconfiguraciones.viewmodel.configuracionfestivos.siu.CrearRequest request) throws ModelException {
        return configuracionFestivosSrv.crearImplementation(request);
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
            com.keralty.aeconfiguraciones.viewmodel.configuracionfestivos.siu.CrearRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Date configuracionFestivoscrearpatrDiaFestivo = request.getConfiguracionFestivoscrearpatrDiaFestivo();

        request.checkArguments();
        ConfiguracionFestivos instance = new ConfiguracionFestivos();
        instance.setConfiguracionFestivosId(Long.valueOf(0));
        instance.setConfiguracionFestivosDiaFestivo(configuracionFestivoscrearpatrDiaFestivo);

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
            com.keralty.aeconfiguraciones.viewmodel.configuracionfestivos.siu.EliminarRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = configuracionFestivosSrv.eliminarTxn(request);
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
            com.keralty.aeconfiguraciones.viewmodel.configuracionfestivos.siu.EliminarRequest request) throws ModelException {
        request.setConfiguracionFestivoseliminarpthisConfiguracionFestivosInstance(configuracionFestivosSrv.getByOIDWithHV(request.getConfiguracionFestivoseliminarpthisConfiguracionFestivos()));
        return configuracionFestivosSrv.eliminarImplementation(request);
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
            com.keralty.aeconfiguraciones.viewmodel.configuracionfestivos.siu.EliminarRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        ConfiguracionFestivos configuracionFestivoseliminarpthisConfiguracionFestivos = request.getConfiguracionFestivoseliminarpthisConfiguracionFestivosInstance();

        request.checkArguments();
        ConfiguracionFestivos instance = configuracionFestivoseliminarpthisConfiguracionFestivos;


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
        select.getOrderByPart().addItem(Constants.normalizePath(ConfiguracionFestivosConstants.CLASS_NAME, ConfiguracionFestivosConstants.ATTR_FIELD_CONFIGURACIONFESTIVOSID, false), true);
    }

    @Override
    protected void applyFilterByOid(String jsonOid, SQLSelect sql) {
        try {
            ConfiguracionFestivosOid oid = new ConfiguracionFestivosOid().fromJsonString(jsonOid);
            SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
            comparison1.setOperator("=");
            comparison1.setFirstOperand(new SQLWhereOperandPathAttribute(ConfiguracionFestivosConstants.ATTR_FIELD_CONFIGURACIONFESTIVOSID, Constants.Type.AUTONUMERIC.getTypeName()));
            comparison1.setSecondOperand(new SQLWhereOperandLiteral(oid.getConfiguracionFestivosId(), Constants.Type.AUTONUMERIC.getTypeName()));
            sql.addComparison(comparison1);
        } catch (Exception e) {
            LoggerFactory.getLogger(ConfiguracionFestivosService.class).error(e.getMessage());
        }
    }

}
