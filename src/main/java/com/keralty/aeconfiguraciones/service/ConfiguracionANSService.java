package com.keralty.aeconfiguraciones.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.keralty.aeconfiguraciones.exception.MinCardinalityException;
import com.keralty.aeconfiguraciones.exception.ModelException;
import com.keralty.aeconfiguraciones.exception.PreconditionException;
import com.keralty.aeconfiguraciones.exception.SystemException;
import com.keralty.aeconfiguraciones.global.ConfiguracionANSConstants;
import com.keralty.aeconfiguraciones.global.Constants;
import com.keralty.aeconfiguraciones.persistence.AbstractAppEntity;
import com.keralty.aeconfiguraciones.persistence.Causal;
import com.keralty.aeconfiguraciones.persistence.ConfiguracionANS;
import com.keralty.aeconfiguraciones.persistence.Dominio;
import com.keralty.aeconfiguraciones.persistence.LogConfiguracionANS;
import com.keralty.aeconfiguraciones.persistence.Novedad;
import com.keralty.aeconfiguraciones.persistence.oid.CausalOid;
import com.keralty.aeconfiguraciones.persistence.oid.ConfiguracionANSOid;
import com.keralty.aeconfiguraciones.persistence.oid.DominioOid;
import com.keralty.aeconfiguraciones.persistence.oid.LogConfiguracionANSOid;
import com.keralty.aeconfiguraciones.persistence.oid.NovedadOid;
import com.keralty.aeconfiguraciones.repository.ConfiguracionANSJpaRepository;
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
public class ConfiguracionANSService extends AbstractService {

    ConfiguracionANSJpaRepository repository;

    ConfiguracionANSService configuracionANSSrv;

    NovedadService novedadSrv;

    CausalService causalSrv;

    DominioService dominioSrv;

    LogConfiguracionANSService logConfiguracionANSSrv;

    @Inject
    public ConfiguracionANSService(
            ConfiguracionANSService configuracionANSSrv,
            NovedadService novedadSrv,
            CausalService causalSrv,
            DominioService dominioSrv,
            LogConfiguracionANSService logConfiguracionANSSrv,
            ConfiguracionANSJpaRepository repository) {
        this.configuracionANSSrv = configuracionANSSrv;
        this.novedadSrv = novedadSrv;
        this.causalSrv = causalSrv;
        this.dominioSrv = dominioSrv;
        this.logConfiguracionANSSrv = logConfiguracionANSSrv;
        this.repository = repository;
    }

    public List<Map<String, Object>> solveQuery(QueryRequest request) {
        return solveGenericQuery(request, ConfiguracionANSConstants.CLASS_NAME);
    }

    private List<ConfiguracionANS> solveConfiguracionANSTypedQuery(QueryRequest request) {
        return repository.solveQuery(processQueryRequest(request, ConfiguracionANSConstants.CLASS_NAME), request.getPageSize(), request.getPageNumber());
    }
    
    private int solveConfiguracionANSTypedCountQuery(QueryRequest request) {
        return repository.solveCountQuery(processCountQueryRequest(request, ConfiguracionANSConstants.CLASS_NAME));
    }

    /*
     * Returns the population of class ConfiguracionANS
     *
     * @return Population of class ConfiguracionANS
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<ConfiguracionANS> get() {
        List<ConfiguracionANS> results = new java.util.ArrayList<>();
        repository.findAll().forEach(results::add);
        return results;
    }
    /*
     * Returns the population of class ConfiguracionANS that matches certain search criteria
     *
     * @param queryRequest Search criteria
     *
     * @return Population of class ConfiguracionANS matching the search criteria
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<ConfiguracionANS> get(QueryRequest queryRequest) {
        return solveConfiguracionANSTypedQuery(queryRequest);
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
            return new PopulationResponseViewModel(solveConfiguracionANSTypedQuery(queryRequest).stream()
                    .map(instance -> buildDynamicObject(instance, queryRequest.getDisplaySetItems()))
                    .collect(Collectors.toList()), queryRequest.getPageSize() != 0 ? solveConfiguracionANSTypedCountQuery(queryRequest) : 0);
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
        return new PopulationResponseViewModel(solveConfiguracionANSTypedQuery(queryRequest).stream()
                .map(instance -> buildDynamicObject(instance, displaySetItems))
                .collect(Collectors.toList()));
    }

    /*
     * Returns an instance of ConfiguracionANS by ID
     *
     * @param configuracionANSId Identification field configuracionANSId
     *
     * @return Instance of ConfiguracionANS
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ConfiguracionANS getById(Long configuracionANSId) {
        return getByOID(new ConfiguracionANSOid(configuracionANSId));
    }
    
    /*
     * Returns an instance of ConfiguracionANS by OID
     *
     * @param oid Object IDentifier
     *
     * @return Instance of ConfiguracionANS
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ConfiguracionANS getByOID(ConfiguracionANSOid oid) {
        Optional<ConfiguracionANS> instance = getInstanceByOid(oid);
        return instance.isPresent() ? instance.get() : null;
    }
    
    /*
     * Returns an instance of ConfiguracionANS by OID applying horizontal visibility constraints
     *
     * @param oid Object IDentifier
     *
     * @return Instance of ConfiguracionANS
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public ConfiguracionANS getByOIDWithHV(ConfiguracionANSOid oid) {
        Optional<ConfiguracionANS> instance = getInstanceByOid(oid);
    	return instance.isPresent() && instance.get().checkHorizontalVisibility(getAgentInstance()) 
    		 ? instance.get() 
    		 : null;
    }
    
    private Optional<ConfiguracionANS> getInstanceByOid(ConfiguracionANSOid oid) {
        return oid != null && oid.getConfiguracionANSId() != null
               ? repository.findById(oid.getConfiguracionANSId())
               : Optional.empty();
    }

    /*
     * Returns an instance of ConfiguracionANS by OID in JSON format
     *
     * @param jsonOid Object IDentifier in JSON format
     *
     * @return Instance of ConfiguracionANS
     */
    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public ConfiguracionANS getByJsonOid(String jsonOid) {
        ConfiguracionANSOid oid = new ConfiguracionANSOid();
        oid.fromJsonString(jsonOid);
        return getByOIDWithHV(oid);
    }

    /*
     * Returns the list of instances of ConfiguracionANS related to a given instance through 
     * a given path
     *
     * @param relatedInstance Related instance
     *
     * @param relatedInstancePath Related instance path
     *
     * @return List of instances of ConfiguracionANS
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<ConfiguracionANS> getByRelated(AbstractAppEntity relatedInstance, String relatedInstancePath) {
        QueryRequest queryRequest = new QueryRequest(new FilterRequest(Constants.FILTER_BY_RELATED,
                Arrays.asList(new FilterVariableRequest(Constants.FILTER_BY_RELATED_FVAR_PATH, relatedInstancePath),
                        new FilterVariableRequest(Constants.FILTER_BY_RELATED_FVAR_OID, relatedInstance.getOid().toJson()))));
        return solveConfiguracionANSTypedQuery(queryRequest);
    }

    /*
     * Returns the list of instances of ConfiguracionANS related to a given instance through
     * a given path
     *
     * @param relatedInstance Related instance
     *
     * @param relatedInstancePath Related instance path
     *
     * @return List of instances of ConfiguracionANS
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<ConfiguracionANS> getInstancesByRelated(AbstractAppEntity relatedInstance, String relatedInstancePath) {
        return getByRelated(relatedInstance, relatedInstancePath);
    }

    /*
     * Returns a set of displayset items for an instance of ConfiguracionANS
     *
     * @param oid Object IDentifier
     *
     * @param displaySetItems List of comma-separated displayset items
     *
     * @return Set of displayset items for an instance of ConfiguracionANS
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public InstanceResponseViewModel getConfiguracionANSDynamic(ConfiguracionANSOid oid, String displaySetItems) {
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
    public String getSuppInfo(ConfiguracionANSOid oid, String dsName) {
        ConfiguracionANS instance = getByOIDWithHV(oid);
        return (instance != null ? getSuppInfo(instance, dsName): "");
    }

    private String getSuppInfo(ConfiguracionANS instance, String dsName) {
        if (instance == null || instance.isNull()) return "";
        return dsName.isEmpty() ? instance.getOid().toJson() : "";
    }




    // IIU Methods

    /*
     * Returns a viewmodel for class ConfiguracionANS with displayset DSConfiguracionANSDetalle for the instance with a given OID
     * @param oid Class oid
     * @return Viewmodel for class class ConfiguracionANS with displayset DSConfiguracionANSDetalle
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public com.keralty.aeconfiguraciones.viewmodel.configuracionans.ds.DSConfiguracionANSDetalle iIUConfiguracionANS(ConfiguracionANSOid oid) {
        return new com.keralty.aeconfiguraciones.viewmodel.configuracionans.ds.DSConfiguracionANSDetalle(getByOIDWithHV(oid));
    }


    /*
     * Solves the query needed for PIUConfiguracionANS
     *
     * @param queryRequest Query request
     * @return List of viewmodels for ConfiguracionANS using displayset DSConfiguracionANS that 
     * match the search criteria
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<com.keralty.aeconfiguraciones.viewmodel.configuracionans.ds.DSConfiguracionANS> piuPIUConfiguracionANS(QueryRequest queryRequest) {
        return solveConfiguracionANSTypedQuery(queryRequest).stream()
                .map(com.keralty.aeconfiguraciones.viewmodel.configuracionans.ds.DSConfiguracionANS::new)
                .collect(Collectors.toList());
    }

    /*
     * Solves the COUNT query needed for PIUConfiguracionANS
     *
     * @param queryRequest Query request
     * @returns Count of instances for PIUConfiguracionANS
     */
    public int getCount4PIUConfiguracionANS(QueryRequest queryRequest) {
        return solveConfiguracionANSTypedQuery(queryRequest).size();
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
            com.keralty.aeconfiguraciones.viewmodel.configuracionans.siu.CrearRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = configuracionANSSrv.crearTxn(request);
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
            com.keralty.aeconfiguraciones.viewmodel.configuracionans.siu.CrearRequest request) throws ModelException {
        request.setConfiguracionANScrearpagrNovedadInstance(novedadSrv.getByOIDWithHV(request.getConfiguracionANScrearpagrNovedad()));
        request.setConfiguracionANScrearpagrCausalInstance(causalSrv.getByOIDWithHV(request.getConfiguracionANScrearpagrCausal()));
        request.setConfiguracionANScrearpagrDominioInstance(dominioSrv.getByOIDWithHV(request.getConfiguracionANScrearpagrDominio()));
        return configuracionANSSrv.crearImplementation(request);
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
            com.keralty.aeconfiguraciones.viewmodel.configuracionans.siu.CrearRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Novedad configuracionANScrearpagrNovedad = request.getConfiguracionANScrearpagrNovedadInstance();
        Causal configuracionANScrearpagrCausal = request.getConfiguracionANScrearpagrCausalInstance();
        Dominio configuracionANScrearpagrDominio = request.getConfiguracionANScrearpagrDominioInstance();
        Long configuracionANScrearpatrTramite = request.getConfiguracionANScrearpatrTramite();
        Long configuracionANScrearpatrANSAreaMedica = request.getConfiguracionANScrearpatrANSAreaMedica();
        Long configuracionANScrearpatrANSAfiliaciones = request.getConfiguracionANScrearpatrANSAfiliaciones();
        Boolean configuracionANScrearpatrHabilLunes = request.getConfiguracionANScrearpatrHabilLunes();
        Boolean configuracionANScrearpatrHabilMartes = request.getConfiguracionANScrearpatrHabilMartes();
        Boolean configuracionANScrearpatrHabilMiercoles = request.getConfiguracionANScrearpatrHabilMiercoles();
        Boolean configuracionANScrearpatrHabilJueves = request.getConfiguracionANScrearpatrHabilJueves();
        Boolean configuracionANScrearpatrHabilViernes = request.getConfiguracionANScrearpatrHabilViernes();
        Boolean configuracionANScrearpatrHabilSabado = request.getConfiguracionANScrearpatrHabilSabado();
        Boolean configuracionANScrearpatrHabilDomingo = request.getConfiguracionANScrearpatrHabilDomingo();

        request.checkArguments();
        ConfiguracionANS instance = new ConfiguracionANS();
        if (configuracionANScrearpagrNovedad != null && !configuracionANScrearpagrNovedad.isNull()) {
            instance.add2Novedad(configuracionANScrearpagrNovedad);
        }

        if (configuracionANScrearpagrCausal != null && !configuracionANScrearpagrCausal.isNull()) {
            instance.add2Causal(configuracionANScrearpagrCausal);
        }

        instance.add2Dominio(configuracionANScrearpagrDominio);

        instance.setConfiguracionANSId(Long.valueOf(0));
        instance.setConfiguracionANSTramite(configuracionANScrearpatrTramite);
        instance.setConfiguracionANSANSAreaMedica(configuracionANScrearpatrANSAreaMedica);
        instance.setConfiguracionANSANSAfiliaciones(configuracionANScrearpatrANSAfiliaciones);
        instance.setConfiguracionANSHabilLunes(configuracionANScrearpatrHabilLunes);
        instance.setConfiguracionANSHabilMartes(configuracionANScrearpatrHabilMartes);
        instance.setConfiguracionANSHabilMiercoles(configuracionANScrearpatrHabilMiercoles);
        instance.setConfiguracionANSHabilJueves(configuracionANScrearpatrHabilJueves);
        instance.setConfiguracionANSHabilViernes(configuracionANScrearpatrHabilViernes);
        instance.setConfiguracionANSHabilSabado(configuracionANScrearpatrHabilSabado);
        instance.setConfiguracionANSHabilDomingo(configuracionANScrearpatrHabilDomingo);

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
            com.keralty.aeconfiguraciones.viewmodel.configuracionans.siu.EliminarRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = configuracionANSSrv.eliminarTxn(request);
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
            com.keralty.aeconfiguraciones.viewmodel.configuracionans.siu.EliminarRequest request) throws ModelException {
        request.setConfiguracionANSeliminarpthisConfiguracionANSInstance(configuracionANSSrv.getByOIDWithHV(request.getConfiguracionANSeliminarpthisConfiguracionANS()));
        return configuracionANSSrv.eliminarImplementation(request);
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
            com.keralty.aeconfiguraciones.viewmodel.configuracionans.siu.EliminarRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        ConfiguracionANS configuracionANSeliminarpthisConfiguracionANS = request.getConfiguracionANSeliminarpthisConfiguracionANSInstance();

        request.checkArguments();
        ConfiguracionANS instance = configuracionANSeliminarpthisConfiguracionANS;
        if(configuracionANSeliminarpthisConfiguracionANS.getLogConfiguracionANS().size() > 0) {
            throw new MinCardinalityException(ConfiguracionANSConstants.CLASS_ALIAS, ConfiguracionANSConstants.ROLE_ALIAS_LOGCONFIGURACIONANS);
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
            com.keralty.aeconfiguraciones.viewmodel.configuracionans.siu.ModificarRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = configuracionANSSrv.modificarTxn(request);
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
            com.keralty.aeconfiguraciones.viewmodel.configuracionans.siu.ModificarRequest request) throws ModelException {
        request.setConfiguracionANSmodificarpthisConfiguracionANSInstance(configuracionANSSrv.getByOIDWithHV(request.getConfiguracionANSmodificarpthisConfiguracionANS()));
        return configuracionANSSrv.modificarImplementation(request);
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
            com.keralty.aeconfiguraciones.viewmodel.configuracionans.siu.ModificarRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        ConfiguracionANS configuracionANSmodificarpthisConfiguracionANS = request.getConfiguracionANSmodificarpthisConfiguracionANSInstance();
        Long configuracionANSmodificarpANSAreaMedica = request.getConfiguracionANSmodificarpANSAreaMedica();
        Long configuracionANSmodificarpANSAfiliaciones = request.getConfiguracionANSmodificarpANSAfiliaciones();
        Boolean configuracionANSmodificarpHabilLunes = request.getConfiguracionANSmodificarpHabilLunes();
        Boolean configuracionANSmodificarpHabilMartes = request.getConfiguracionANSmodificarpHabilMartes();
        Boolean configuracionANSmodificarpHabilMiercoles = request.getConfiguracionANSmodificarpHabilMiercoles();
        Boolean configuracionANSmodificarpHabilJueves = request.getConfiguracionANSmodificarpHabilJueves();
        Boolean configuracionANSmodificarpHabilViernes = request.getConfiguracionANSmodificarpHabilViernes();
        Boolean configuracionANSmodificarpHabilSabado = request.getConfiguracionANSmodificarpHabilSabado();
        Boolean configuracionANSmodificarpHabilDomingo = request.getConfiguracionANSmodificarpHabilDomingo();

        request.checkArguments();
        ConfiguracionANS instance = configuracionANSmodificarpthisConfiguracionANS;
        instance.setConfiguracionANSANSAreaMedica(configuracionANSmodificarpANSAreaMedica);
        instance.setConfiguracionANSANSAfiliaciones(configuracionANSmodificarpANSAfiliaciones);
        instance.setConfiguracionANSHabilLunes(configuracionANSmodificarpHabilLunes);
        instance.setConfiguracionANSHabilMartes(configuracionANSmodificarpHabilMartes);
        instance.setConfiguracionANSHabilMiercoles(configuracionANSmodificarpHabilMiercoles);
        instance.setConfiguracionANSHabilJueves(configuracionANSmodificarpHabilJueves);
        instance.setConfiguracionANSHabilViernes(configuracionANSmodificarpHabilViernes);
        instance.setConfiguracionANSHabilSabado(configuracionANSmodificarpHabilSabado);
        instance.setConfiguracionANSHabilDomingo(configuracionANSmodificarpHabilDomingo);


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
            com.keralty.aeconfiguraciones.viewmodel.configuracionans.siu.TCREARRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = configuracionANSSrv.tCREARTxn(request);
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
            com.keralty.aeconfiguraciones.viewmodel.configuracionans.siu.TCREARRequest request) throws ModelException {
        request.setConfiguracionANSTCREARptpagrDominioInstance(dominioSrv.getByOIDWithHV(request.getConfiguracionANSTCREARptpagrDominio()));
        request.setConfiguracionANSTCREARptpagrNovedadInstance(novedadSrv.getByOIDWithHV(new NovedadOid().fromJsonString(request.getConfiguracionANSTCREARptpagrNovedad())));
        request.setConfiguracionANSTCREARptpagrCausalInstance(causalSrv.getByOIDWithHV(new CausalOid().fromJsonString(request.getConfiguracionANSTCREARptpagrCausal())));
        return configuracionANSSrv.tCREARImplementation(request);
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
            com.keralty.aeconfiguraciones.viewmodel.configuracionans.siu.TCREARRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Novedad configuracionANSTCREARptpagrNovedad = request.getConfiguracionANSTCREARptpagrNovedadInstance();
        Causal configuracionANSTCREARptpagrCausal = request.getConfiguracionANSTCREARptpagrCausalInstance();
        Dominio configuracionANSTCREARptpagrDominio = request.getConfiguracionANSTCREARptpagrDominioInstance();
        Long configuracionANSTCREARptpatrTramite = request.getConfiguracionANSTCREARptpatrTramite();
        Long configuracionANSTCREARptpatrANSAreaMedica = request.getConfiguracionANSTCREARptpatrANSAreaMedica();
        Long configuracionANSTCREARptpatrANSAfiliaciones = request.getConfiguracionANSTCREARptpatrANSAfiliaciones();
        Boolean configuracionANSTCREARptpatrHabilLunes = request.getConfiguracionANSTCREARptpatrHabilLunes();
        Boolean configuracionANSTCREARptpatrHabilMartes = request.getConfiguracionANSTCREARptpatrHabilMartes();
        Boolean configuracionANSTCREARptpatrHabilMiercoles = request.getConfiguracionANSTCREARptpatrHabilMiercoles();
        Boolean configuracionANSTCREARptpatrHabilJueves = request.getConfiguracionANSTCREARptpatrHabilJueves();
        Boolean configuracionANSTCREARptpatrHabilViernes = request.getConfiguracionANSTCREARptpatrHabilViernes();
        Boolean configuracionANSTCREARptpatrHabilSabado = request.getConfiguracionANSTCREARptpatrHabilSabado();
        Boolean configuracionANSTCREARptpatrHabilDomingo = request.getConfiguracionANSTCREARptpatrHabilDomingo();

        request.checkArguments();

        tCREARPreconditions(request);
        ConfiguracionANS instance;

        com.keralty.aeconfiguraciones.viewmodel.configuracionans.siu.CrearRequest step1Request = new com.keralty.aeconfiguraciones.viewmodel.configuracionans.siu.CrearRequest();
        step1Request.setConfiguracionANScrearpagrNovedadInstance(configuracionANSTCREARptpagrNovedad);
        step1Request.setConfiguracionANScrearpagrCausalInstance(configuracionANSTCREARptpagrCausal);
        step1Request.setConfiguracionANScrearpagrDominioInstance(configuracionANSTCREARptpagrDominio);
        step1Request.setConfiguracionANScrearpatrTramite(configuracionANSTCREARptpatrTramite);
        step1Request.setConfiguracionANScrearpatrANSAreaMedica(configuracionANSTCREARptpatrANSAreaMedica);
        step1Request.setConfiguracionANScrearpatrANSAfiliaciones(configuracionANSTCREARptpatrANSAfiliaciones);
        step1Request.setConfiguracionANScrearpatrHabilLunes(configuracionANSTCREARptpatrHabilLunes);
        step1Request.setConfiguracionANScrearpatrHabilMartes(configuracionANSTCREARptpatrHabilMartes);
        step1Request.setConfiguracionANScrearpatrHabilMiercoles(configuracionANSTCREARptpatrHabilMiercoles);
        step1Request.setConfiguracionANScrearpatrHabilJueves(configuracionANSTCREARptpatrHabilJueves);
        step1Request.setConfiguracionANScrearpatrHabilViernes(configuracionANSTCREARptpatrHabilViernes);
        step1Request.setConfiguracionANScrearpatrHabilSabado(configuracionANSTCREARptpatrHabilSabado);
        step1Request.setConfiguracionANScrearpatrHabilDomingo(configuracionANSTCREARptpatrHabilDomingo);
        instance = (ConfiguracionANS) configuracionANSSrv.crearImplementation(step1Request).getInstance();

        com.keralty.aeconfiguraciones.viewmodel.logconfiguracionans.siu.TCREARRequest step2Request = new com.keralty.aeconfiguraciones.viewmodel.logconfiguracionans.siu.TCREARRequest();
        step2Request.setLogConfiguracionANSTCREARptpagrConfiguracionANSInstance(instance);
        step2Request.setLogConfiguracionANSTCREARptpatrANSAfiliaciones(instance.getConfiguracionANSANSAfiliaciones());
        step2Request.setLogConfiguracionANSTCREARptpatrANSAreaMedica(instance.getConfiguracionANSANSAreaMedica());
        step2Request.setLogConfiguracionANSTCREARptpatrHabilLunes(instance.getConfiguracionANSHabilLunes());
        step2Request.setLogConfiguracionANSTCREARptpatrHabilMartes(instance.getConfiguracionANSHabilMartes());
        step2Request.setLogConfiguracionANSTCREARptpatrHabilMiercoles(instance.getConfiguracionANSHabilMiercoles());
        step2Request.setLogConfiguracionANSTCREARptpatrHabilJueves(instance.getConfiguracionANSHabilJueves());
        step2Request.setLogConfiguracionANSTCREARptpatrHabilViernes(instance.getConfiguracionANSHabilViernes());
        step2Request.setLogConfiguracionANSTCREARptpatrHabilSabado(instance.getConfiguracionANSHabilSabado());
        step2Request.setLogConfiguracionANSTCREARptpatrHabilDomingo(instance.getConfiguracionANSHabilDomingo());
        logConfiguracionANSSrv.tCREARImplementation(step2Request);

        response.setInstance(getByOID(instance.getOid()));

        return response;
    }

    /*
     * Checks service TCREAR preconditions individually
     *
     * @param request Service request viewmodel
     *
     * @return Service preconditions evaluation result
     */
    public ServiceResponse tCREARCheckPreconditions(com.keralty.aeconfiguraciones.viewmodel.configuracionans.siu.TCREARRequest request) {
        ServiceResponse response = new ServiceResponse();
        request.setConfiguracionANSTCREARptpagrDominioInstance(dominioSrv.getByOIDWithHV(request.getConfiguracionANSTCREARptpagrDominio()));
        request.setConfiguracionANSTCREARptpagrNovedadInstance(novedadSrv.getByOIDWithHV(new NovedadOid().fromJsonString(request.getConfiguracionANSTCREARptpagrNovedad())));
        request.setConfiguracionANSTCREARptpagrCausalInstance(causalSrv.getByOIDWithHV(new CausalOid().fromJsonString(request.getConfiguracionANSTCREARptpagrCausal())));
        try {
            if("Clas_1586859868160432Pre_4".equals(request.getPreconditionId()))
                checkClas1586859868160432Pre4(request);
            if("Clas_1586859868160432Pre_1".equals(request.getPreconditionId()))
                checkClas1586859868160432Pre1(request);
            if("Clas_1586859868160432Pre_2".equals(request.getPreconditionId()))
                checkClas1586859868160432Pre2(request);
            if("Clas_1586859868160432Pre_3".equals(request.getPreconditionId()))
                checkClas1586859868160432Pre3(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    private void tCREARPreconditions(
            com.keralty.aeconfiguraciones.viewmodel.configuracionans.siu.TCREARRequest request) 
            throws ModelException {
        checkClas1586859868160432Pre4(request);
        checkClas1586859868160432Pre1(request);
        checkClas1586859868160432Pre2(request);
        checkClas1586859868160432Pre3(request);
    }

    private void checkClas1586859868160432Pre4 (
            com.keralty.aeconfiguraciones.viewmodel.configuracionans.siu.TCREARRequest request) 
            throws ModelException {
        // ptpatrTramite <> 3 OR ptpagrNovedad <> NULL

        Long configuracionANSTCREARptpatrTramite = request.getConfiguracionANSTCREARptpatrTramite();
        Novedad configuracionANSTCREARptpagrNovedad = request.getConfiguracionANSTCREARptpagrNovedadInstance();
        boolean preconditionHolds = !(configuracionANSTCREARptpatrTramite).equals(Long.valueOf(3)) || (configuracionANSTCREARptpagrNovedad != null && !configuracionANSTCREARptpagrNovedad.isNull());
        if (!preconditionHolds) throw new PreconditionException("Debe diligenciar la Novedad");
    }

    private void checkClas1586859868160432Pre1 (
            com.keralty.aeconfiguraciones.viewmodel.configuracionans.siu.TCREARRequest request) 
            throws ModelException {
        // ptpatrTramite = 3 OR COUNT( ptpagrDominio.ConfiguracionesANS ) WHERE (ptpagrDominio.ConfiguracionesANS.Tramite = ptpatrTramite) = 0

        Long configuracionANSTCREARptpatrTramite = request.getConfiguracionANSTCREARptpatrTramite();
        Dominio configuracionANSTCREARptpagrDominio = request.getConfiguracionANSTCREARptpagrDominioInstance();
        try {
            boolean preconditionHolds = (configuracionANSTCREARptpatrTramite).equals(Long.valueOf(3)) || (repository.assocOperator001(configuracionANSTCREARptpagrDominio, configuracionANSTCREARptpatrTramite)).equals(Long.valueOf(0));
            if (!preconditionHolds) throw new PreconditionException("Configuración existente. Solamente puede existir una parametrización por tipo de trámite Nueva Afiliación o Inclusión para cada Dominio");
        } catch (SystemException e) {
            LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
        }
    }

    private void checkClas1586859868160432Pre2 (
            com.keralty.aeconfiguraciones.viewmodel.configuracionans.siu.TCREARRequest request) 
            throws ModelException {
        // ptpatrTramite <> 3 OR ptpagrCausal <> NULL OR COUNT( ptpagrDominio.ConfiguracionesANS ) WHERE (ptpagrDominio.ConfiguracionesANS.Novedad = ptpagrNovedad AND ptpagrDominio.ConfiguracionesANS.drvTieneCausal = false) = 0

        Long configuracionANSTCREARptpatrTramite = request.getConfiguracionANSTCREARptpatrTramite();
        Causal configuracionANSTCREARptpagrCausal = request.getConfiguracionANSTCREARptpagrCausalInstance();
        Dominio configuracionANSTCREARptpagrDominio = request.getConfiguracionANSTCREARptpagrDominioInstance();
        Novedad configuracionANSTCREARptpagrNovedad = request.getConfiguracionANSTCREARptpagrNovedadInstance();
        try {
            boolean preconditionHolds = !(configuracionANSTCREARptpatrTramite).equals(Long.valueOf(3)) || (configuracionANSTCREARptpagrCausal != null && !configuracionANSTCREARptpagrCausal.isNull()) || (repository.assocOperator002(configuracionANSTCREARptpagrDominio, configuracionANSTCREARptpagrNovedad)).equals(Long.valueOf(0));
            if (!preconditionHolds) throw new PreconditionException("Configuración existente. Solamente puede existir una parametrización por Dominio y Novedad");
        } catch (SystemException e) {
            LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
        }
    }

    private void checkClas1586859868160432Pre3 (
            com.keralty.aeconfiguraciones.viewmodel.configuracionans.siu.TCREARRequest request) 
            throws ModelException {
        // ptpatrTramite <> 3 OR ptpagrCausal = NULL OR COUNT( ptpagrDominio.ConfiguracionesANS ) WHERE (ptpagrDominio.ConfiguracionesANS.Causal = ptpagrCausal) = 0

        Long configuracionANSTCREARptpatrTramite = request.getConfiguracionANSTCREARptpatrTramite();
        Causal configuracionANSTCREARptpagrCausal = request.getConfiguracionANSTCREARptpagrCausalInstance();
        Dominio configuracionANSTCREARptpagrDominio = request.getConfiguracionANSTCREARptpagrDominioInstance();
        try {
            boolean preconditionHolds = !(configuracionANSTCREARptpatrTramite).equals(Long.valueOf(3)) || (configuracionANSTCREARptpagrCausal == null || configuracionANSTCREARptpagrCausal.isNull()) || (repository.assocOperator003(configuracionANSTCREARptpagrDominio, configuracionANSTCREARptpagrCausal)).equals(Long.valueOf(0));
            if (!preconditionHolds) throw new PreconditionException("Configuración existente. Solamente puede existir una parametrización por Dominio, Novedad y Causal");
        } catch (SystemException e) {
            LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
        }
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
            com.keralty.aeconfiguraciones.viewmodel.configuracionans.siu.TMODIFICARRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = configuracionANSSrv.tMODIFICARTxn(request);
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
            com.keralty.aeconfiguraciones.viewmodel.configuracionans.siu.TMODIFICARRequest request) throws ModelException {
        request.setConfiguracionANSTMODIFICARpthisConfiguracionANSInstance(configuracionANSSrv.getByOIDWithHV(request.getConfiguracionANSTMODIFICARpthisConfiguracionANS()));
        return configuracionANSSrv.tMODIFICARImplementation(request);
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
            com.keralty.aeconfiguraciones.viewmodel.configuracionans.siu.TMODIFICARRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Long configuracionANSTMODIFICARptpANSAfiliaciones = request.getConfiguracionANSTMODIFICARptpANSAfiliaciones();
        Long configuracionANSTMODIFICARptpANSAreaMedica = request.getConfiguracionANSTMODIFICARptpANSAreaMedica();
        Boolean configuracionANSTMODIFICARptpHabilLunes = request.getConfiguracionANSTMODIFICARptpHabilLunes();
        Boolean configuracionANSTMODIFICARptpHabilMartes = request.getConfiguracionANSTMODIFICARptpHabilMartes();
        Boolean configuracionANSTMODIFICARptpHabilMiercoles = request.getConfiguracionANSTMODIFICARptpHabilMiercoles();
        Boolean configuracionANSTMODIFICARptpHabilJueves = request.getConfiguracionANSTMODIFICARptpHabilJueves();
        Boolean configuracionANSTMODIFICARptpHabilViernes = request.getConfiguracionANSTMODIFICARptpHabilViernes();
        Boolean configuracionANSTMODIFICARptpHabilSabado = request.getConfiguracionANSTMODIFICARptpHabilSabado();
        Boolean configuracionANSTMODIFICARptpHabilDomingo = request.getConfiguracionANSTMODIFICARptpHabilDomingo();
        ConfiguracionANS configuracionANSTMODIFICARpthisConfiguracionANS = request.getConfiguracionANSTMODIFICARpthisConfiguracionANSInstance();

        request.checkArguments();
        ConfiguracionANS instance = configuracionANSTMODIFICARpthisConfiguracionANS;

        /* Action 1: IF (ptpANSAfiliaciones <> ANSAfiliaciones OR ptpANSAreaMedica <> ANSAreaMedica OR ptpHabilLunes <> HabilLunes OR ptpHabilMartes <> HabilMartes OR ptpHabilMiercoles <> HabilMiercoles OR ptpHabilJueves <> HabilJueves OR ptpHabilViernes <> HabilViernes OR ptpHabilSabado <> HabilSabado OR ptpHabilDomingo <> HabilDomingo) */
        boolean ifCondition1 = !(configuracionANSTMODIFICARptpANSAfiliaciones).equals(configuracionANSTMODIFICARpthisConfiguracionANS.getConfiguracionANSANSAfiliaciones()) || !(configuracionANSTMODIFICARptpANSAreaMedica).equals(configuracionANSTMODIFICARpthisConfiguracionANS.getConfiguracionANSANSAreaMedica()) || !(configuracionANSTMODIFICARptpHabilLunes).equals(configuracionANSTMODIFICARpthisConfiguracionANS.getConfiguracionANSHabilLunes()) || !(configuracionANSTMODIFICARptpHabilMartes).equals(configuracionANSTMODIFICARpthisConfiguracionANS.getConfiguracionANSHabilMartes()) || !(configuracionANSTMODIFICARptpHabilMiercoles).equals(configuracionANSTMODIFICARpthisConfiguracionANS.getConfiguracionANSHabilMiercoles()) || !(configuracionANSTMODIFICARptpHabilJueves).equals(configuracionANSTMODIFICARpthisConfiguracionANS.getConfiguracionANSHabilJueves()) || !(configuracionANSTMODIFICARptpHabilViernes).equals(configuracionANSTMODIFICARpthisConfiguracionANS.getConfiguracionANSHabilViernes()) || !(configuracionANSTMODIFICARptpHabilSabado).equals(configuracionANSTMODIFICARpthisConfiguracionANS.getConfiguracionANSHabilSabado()) || !(configuracionANSTMODIFICARptpHabilDomingo).equals(configuracionANSTMODIFICARpthisConfiguracionANS.getConfiguracionANSHabilDomingo());
        if (ifCondition1) {
            com.keralty.aeconfiguraciones.viewmodel.configuracionans.siu.ModificarRequest step10Request = new com.keralty.aeconfiguraciones.viewmodel.configuracionans.siu.ModificarRequest();
            step10Request.setConfiguracionANSmodificarpthisConfiguracionANSInstance(configuracionANSTMODIFICARpthisConfiguracionANS);
            step10Request.setConfiguracionANSmodificarpANSAreaMedica(configuracionANSTMODIFICARptpANSAreaMedica);
            step10Request.setConfiguracionANSmodificarpHabilLunes(configuracionANSTMODIFICARptpHabilLunes);
            step10Request.setConfiguracionANSmodificarpHabilMartes(configuracionANSTMODIFICARptpHabilMartes);
            step10Request.setConfiguracionANSmodificarpHabilMiercoles(configuracionANSTMODIFICARptpHabilMiercoles);
            step10Request.setConfiguracionANSmodificarpHabilJueves(configuracionANSTMODIFICARptpHabilJueves);
            step10Request.setConfiguracionANSmodificarpHabilViernes(configuracionANSTMODIFICARptpHabilViernes);
            step10Request.setConfiguracionANSmodificarpHabilSabado(configuracionANSTMODIFICARptpHabilSabado);
            step10Request.setConfiguracionANSmodificarpHabilDomingo(configuracionANSTMODIFICARptpHabilDomingo);
            step10Request.setConfiguracionANSmodificarpANSAfiliaciones(configuracionANSTMODIFICARptpANSAfiliaciones);
            configuracionANSSrv.modificarImplementation(step10Request);
            com.keralty.aeconfiguraciones.viewmodel.logconfiguracionans.siu.TCREARRequest step11Request = new com.keralty.aeconfiguraciones.viewmodel.logconfiguracionans.siu.TCREARRequest();
            step11Request.setLogConfiguracionANSTCREARptpagrConfiguracionANSInstance(configuracionANSTMODIFICARpthisConfiguracionANS);
            step11Request.setLogConfiguracionANSTCREARptpatrANSAfiliaciones(configuracionANSTMODIFICARpthisConfiguracionANS.getConfiguracionANSANSAfiliaciones());
            step11Request.setLogConfiguracionANSTCREARptpatrANSAreaMedica(configuracionANSTMODIFICARpthisConfiguracionANS.getConfiguracionANSANSAreaMedica());
            step11Request.setLogConfiguracionANSTCREARptpatrHabilLunes(configuracionANSTMODIFICARpthisConfiguracionANS.getConfiguracionANSHabilLunes());
            step11Request.setLogConfiguracionANSTCREARptpatrHabilMartes(configuracionANSTMODIFICARpthisConfiguracionANS.getConfiguracionANSHabilMartes());
            step11Request.setLogConfiguracionANSTCREARptpatrHabilMiercoles(configuracionANSTMODIFICARpthisConfiguracionANS.getConfiguracionANSHabilMiercoles());
            step11Request.setLogConfiguracionANSTCREARptpatrHabilJueves(configuracionANSTMODIFICARpthisConfiguracionANS.getConfiguracionANSHabilJueves());
            step11Request.setLogConfiguracionANSTCREARptpatrHabilViernes(configuracionANSTMODIFICARpthisConfiguracionANS.getConfiguracionANSHabilViernes());
            step11Request.setLogConfiguracionANSTCREARptpatrHabilSabado(configuracionANSTMODIFICARpthisConfiguracionANS.getConfiguracionANSHabilSabado());
            step11Request.setLogConfiguracionANSTCREARptpatrHabilDomingo(configuracionANSTMODIFICARpthisConfiguracionANS.getConfiguracionANSHabilDomingo());
            logConfiguracionANSSrv.tCREARImplementation(step11Request);
        }

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
            com.keralty.aeconfiguraciones.viewmodel.configuracionans.siu.TELIMINARRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = configuracionANSSrv.tELIMINARTxn(request);
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
            com.keralty.aeconfiguraciones.viewmodel.configuracionans.siu.TELIMINARRequest request) throws ModelException, SystemException {
        request.setConfiguracionANSTELIMINARpthisConfiguracionANSInstance(configuracionANSSrv.getByOIDWithHV(request.getConfiguracionANSTELIMINARpthisConfiguracionANS()));
        return configuracionANSSrv.tELIMINARImplementation(request);
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
            com.keralty.aeconfiguraciones.viewmodel.configuracionans.siu.TELIMINARRequest request) throws ModelException, SystemException {
        ServiceResponse response = new ServiceResponse();
        ConfiguracionANS configuracionANSTELIMINARpthisConfiguracionANS = request.getConfiguracionANSTELIMINARpthisConfiguracionANSInstance();

        request.checkArguments();
        ConfiguracionANS instance = configuracionANSTELIMINARpthisConfiguracionANS;

        for(LogConfiguracionANS step1Entity : repository.assocOperator004(configuracionANSTELIMINARpthisConfiguracionANS)) {
            com.keralty.aeconfiguraciones.viewmodel.logconfiguracionans.siu.EliminarRequest step1Request = new com.keralty.aeconfiguraciones.viewmodel.logconfiguracionans.siu.EliminarRequest();
            step1Request.setLogConfiguracionANSeliminarpthisLogConfiguracionANSInstance(step1Entity);
            logConfiguracionANSSrv.eliminarImplementation(step1Request);
        }

        com.keralty.aeconfiguraciones.viewmodel.configuracionans.siu.EliminarRequest step2Request = new com.keralty.aeconfiguraciones.viewmodel.configuracionans.siu.EliminarRequest();
        step2Request.setConfiguracionANSeliminarpthisConfiguracionANSInstance(configuracionANSTELIMINARpthisConfiguracionANS);
        configuracionANSSrv.eliminarImplementation(step2Request);

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
        select.getOrderByPart().addItem(Constants.normalizePath(ConfiguracionANSConstants.CLASS_NAME, ConfiguracionANSConstants.ATTR_FIELD_CONFIGURACIONANSID, false), true);
    }
    
    @Override
    protected AbstractAppEntity buildRelatedInstance(String relatedInstanceJsonOid, String relatedInstancePathFirstItem, String remainingPath) {
        AbstractAppEntity relatedInstance = null;
        if (relatedInstancePathFirstItem.isEmpty()) {
            relatedInstance = buildConfiguracionANSRelatedInstance(relatedInstanceJsonOid);
        }
        if (ConfiguracionANSConstants.ROLE_NAME_LOGCONFIGURACIONANS.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildConfiguracionANSRelatedInstance4LogConfiguracionANS(relatedInstanceJsonOid, remainingPath);
        }
        if (ConfiguracionANSConstants.ROLE_NAME_DOMINIO.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildConfiguracionANSRelatedInstance4Dominio(relatedInstanceJsonOid, remainingPath);
        }
        if (ConfiguracionANSConstants.ROLE_NAME_NOVEDAD.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildConfiguracionANSRelatedInstance4Novedad(relatedInstanceJsonOid, remainingPath);
        }
        if (ConfiguracionANSConstants.ROLE_NAME_CAUSAL.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildConfiguracionANSRelatedInstance4Causal(relatedInstanceJsonOid, remainingPath);
        }
        return relatedInstance;
    }
    
    private AbstractAppEntity buildConfiguracionANSRelatedInstance(String relatedInstanceJsonOid) {
    	ObjectMapper mapper = new ObjectMapper();
    	try {
    		return new ConfiguracionANS(mapper.readValue(relatedInstanceJsonOid, ConfiguracionANSOid.class));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    private AbstractAppEntity buildConfiguracionANSRelatedInstance4LogConfiguracionANS(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new LogConfiguracionANS(mapper.readValue(relatedInstanceJsonOid, LogConfiguracionANSOid.class)): LogConfiguracionANS.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    private AbstractAppEntity buildConfiguracionANSRelatedInstance4Dominio(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new Dominio(mapper.readValue(relatedInstanceJsonOid, DominioOid.class)): Dominio.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    private AbstractAppEntity buildConfiguracionANSRelatedInstance4Novedad(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new Novedad(mapper.readValue(relatedInstanceJsonOid, NovedadOid.class)): Novedad.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    private AbstractAppEntity buildConfiguracionANSRelatedInstance4Causal(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new Causal(mapper.readValue(relatedInstanceJsonOid, CausalOid.class)): Causal.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    @Override
    protected void applyFilterByOid(String jsonOid, SQLSelect sql) {
        try {
            ConfiguracionANSOid oid = new ConfiguracionANSOid().fromJsonString(jsonOid);
            SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
            comparison1.setOperator("=");
            comparison1.setFirstOperand(new SQLWhereOperandPathAttribute(ConfiguracionANSConstants.ATTR_FIELD_CONFIGURACIONANSID, Constants.Type.AUTONUMERIC.getTypeName()));
            comparison1.setSecondOperand(new SQLWhereOperandLiteral(oid.getConfiguracionANSId(), Constants.Type.AUTONUMERIC.getTypeName()));
            sql.addComparison(comparison1);
        } catch (Exception e) {
            LoggerFactory.getLogger(ConfiguracionANSService.class).error(e.getMessage());
        }
    }

}
