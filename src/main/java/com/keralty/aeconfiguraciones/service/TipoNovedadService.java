package com.keralty.aeconfiguraciones.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.keralty.aeconfiguraciones.exception.InstanceExistsException;
import com.keralty.aeconfiguraciones.exception.MinCardinalityException;
import com.keralty.aeconfiguraciones.exception.ModelException;
import com.keralty.aeconfiguraciones.exception.SystemException;
import com.keralty.aeconfiguraciones.global.Constants;
import com.keralty.aeconfiguraciones.global.SolicitudNovedadConstants;
import com.keralty.aeconfiguraciones.global.TipoNovedadConstants;
import com.keralty.aeconfiguraciones.persistence.AbstractAppEntity;
import com.keralty.aeconfiguraciones.persistence.Novedad;
import com.keralty.aeconfiguraciones.persistence.oid.NovedadOid;
import com.keralty.aeconfiguraciones.persistence.oid.PlanesOid;
import com.keralty.aeconfiguraciones.persistence.oid.TipoNovedadOid;
import com.keralty.aeconfiguraciones.persistence.Planes;
import com.keralty.aeconfiguraciones.persistence.TipoNovedad;
import com.keralty.aeconfiguraciones.repository.TipoNovedadJpaRepository;
import com.keralty.aeconfiguraciones.sqlutils.SQLSelect;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereComparison;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereConjunction;
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
public class TipoNovedadService extends AbstractService {

    TipoNovedadJpaRepository repository;

    TipoNovedadService tipoNovedadSrv;

    PlanesService planesSrv;

    NovedadService novedadSrv;

    @Inject
    public TipoNovedadService(
            TipoNovedadService tipoNovedadSrv,
            PlanesService planesSrv,
            NovedadService novedadSrv,
            TipoNovedadJpaRepository repository) {
        this.tipoNovedadSrv = tipoNovedadSrv;
        this.planesSrv = planesSrv;
        this.novedadSrv = novedadSrv;
        this.repository = repository;
    }

    public List<Map<String, Object>> solveQuery(QueryRequest request) {
        return solveGenericQuery(request, TipoNovedadConstants.CLASS_NAME);
    }

    private List<TipoNovedad> solveTipoNovedadTypedQuery(QueryRequest request) {
        return repository.solveQuery(processQueryRequest(request, TipoNovedadConstants.CLASS_NAME), request.getPageSize(), request.getPageNumber());
    }
    
    private int solveTipoNovedadTypedCountQuery(QueryRequest request) {
        return repository.solveCountQuery(processCountQueryRequest(request, TipoNovedadConstants.CLASS_NAME));
    }

    /*
     * Returns the population of class TipoNovedad
     *
     * @return Population of class TipoNovedad
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<TipoNovedad> get() {
        List<TipoNovedad> results = new java.util.ArrayList<>();
        repository.findAll().forEach(results::add);
        return results;
    }
    
    /*
     * Gets the preloaded list of objects of class TipoNovedad
     *
     * @param queryRequest Request object that dictates the search conditions to 
     * retrieve objects
     *
     * @return Preloaded list of objects of class TipoNovedad
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<PreloadViewModel> getPreload(QueryRequest queryRequest) {
        List<PreloadViewModel> results = new ArrayList<>();
        List<TipoNovedad> instances = get(queryRequest);
        if(TipoNovedadConstants.DSDSTIPONOVEDAD.equals(queryRequest.getDisplaySetName())) {
            results = instances.stream().map(x -> new PreloadViewModel(x.getOid().toJson(), x.buildDSTipoNovedad())).collect(Collectors.toList());
        }
        if(TipoNovedadConstants.DSICTIPONOVEDAD.equals(queryRequest.getDisplaySetName())) {
            results = instances.stream().map(x -> new PreloadViewModel(x.getOid().toJson(), x.buildICTipoNovedad())).collect(Collectors.toList());
        }
        if(TipoNovedadConstants.DSDSTIPONOVEDADNULL.equals(queryRequest.getDisplaySetName())) {
            results = instances.stream().map(x -> new PreloadViewModel(x.getOid().toJson(), x.buildDSTipoNovedadNull())).collect(Collectors.toList());
        }
        return results;
    }
    /*
     * Returns the population of class TipoNovedad that matches certain search criteria
     *
     * @param queryRequest Search criteria
     *
     * @return Population of class TipoNovedad matching the search criteria
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<TipoNovedad> get(QueryRequest queryRequest) {
        return solveTipoNovedadTypedQuery(queryRequest);
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
            return new PopulationResponseViewModel(solveTipoNovedadTypedQuery(queryRequest).stream()
                    .map(instance -> buildDynamicObject(instance, queryRequest.getDisplaySetItems()))
                    .collect(Collectors.toList()), queryRequest.getPageSize() != 0 ? solveTipoNovedadTypedCountQuery(queryRequest) : 0);
        } else {
            return new PreloadResponseViewModel(solveTipoNovedadTypedQuery(queryRequest).stream()
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
        return new PopulationResponseViewModel(solveTipoNovedadTypedQuery(queryRequest).stream()
                .map(instance -> buildDynamicObject(instance, displaySetItems))
                .collect(Collectors.toList()));
    }

    /*
     * Returns an instance of TipoNovedad by ID
     *
     * @param tipoNovedadTipo Identification field tipoNovedadTipo
     *
     * @return Instance of TipoNovedad
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public TipoNovedad getById(Long tipoNovedadTipo) {
        return getByOID(new TipoNovedadOid(tipoNovedadTipo));
    }
    
    /*
     * Returns an instance of TipoNovedad by OID
     *
     * @param oid Object IDentifier
     *
     * @return Instance of TipoNovedad
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public TipoNovedad getByOID(TipoNovedadOid oid) {
        Optional<TipoNovedad> instance = getInstanceByOid(oid);
        return instance.isPresent() ? instance.get() : null;
    }
    
    /*
     * Returns an instance of TipoNovedad by OID applying horizontal visibility constraints
     *
     * @param oid Object IDentifier
     *
     * @return Instance of TipoNovedad
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public TipoNovedad getByOIDWithHV(TipoNovedadOid oid) {
        Optional<TipoNovedad> instance = getInstanceByOid(oid);
    	return instance.isPresent() && instance.get().checkHorizontalVisibility(getAgentInstance()) 
    		 ? instance.get() 
    		 : null;
    }
    
    private Optional<TipoNovedad> getInstanceByOid(TipoNovedadOid oid) {
        return oid != null && oid.getTipoNovedadTipo() != null
               ? repository.findById(oid.getTipoNovedadTipo())
               : Optional.empty();
    }

    /*
     * Returns an instance of TipoNovedad by OID in JSON format
     *
     * @param jsonOid Object IDentifier in JSON format
     *
     * @return Instance of TipoNovedad
     */
    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public TipoNovedad getByJsonOid(String jsonOid) {
        TipoNovedadOid oid = new TipoNovedadOid();
        oid.fromJsonString(jsonOid);
        return getByOIDWithHV(oid);
    }

    /*
     * Returns the list of instances of TipoNovedad related to a given instance through 
     * a given path
     *
     * @param relatedInstance Related instance
     *
     * @param relatedInstancePath Related instance path
     *
     * @return List of instances of TipoNovedad
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<TipoNovedad> getByRelated(AbstractAppEntity relatedInstance, String relatedInstancePath) {
        QueryRequest queryRequest = new QueryRequest(new FilterRequest(Constants.FILTER_BY_RELATED,
                Arrays.asList(new FilterVariableRequest(Constants.FILTER_BY_RELATED_FVAR_PATH, relatedInstancePath),
                        new FilterVariableRequest(Constants.FILTER_BY_RELATED_FVAR_OID, relatedInstance.getOid().toJson()))));
        return solveTipoNovedadTypedQuery(queryRequest);
    }

    /*
     * Returns the list of instances of TipoNovedad related to a given instance through
     * a given path
     *
     * @param relatedInstance Related instance
     *
     * @param relatedInstancePath Related instance path
     *
     * @return List of instances of TipoNovedad
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<TipoNovedad> getInstancesByRelated(AbstractAppEntity relatedInstance, String relatedInstancePath) {
        return getByRelated(relatedInstance, relatedInstancePath);
    }

    /*
     * Returns a set of displayset items for an instance of TipoNovedad
     *
     * @param oid Object IDentifier
     *
     * @param displaySetItems List of comma-separated displayset items
     *
     * @return Set of displayset items for an instance of TipoNovedad
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public InstanceResponseViewModel getTipoNovedadDynamic(TipoNovedadOid oid, String displaySetItems) {
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
    public String getSuppInfo(TipoNovedadOid oid, String dsName) {
        TipoNovedad instance = getByOIDWithHV(oid);
        return (instance != null ? getSuppInfo(instance, dsName): "");
    }

    private String getSuppInfo(TipoNovedad instance, String dsName) {
        if (instance == null || instance.isNull()) return "";
        if (TipoNovedadConstants.DSICTIPONOVEDAD.equals(dsName))
            return instance.buildICTipoNovedad();
        return dsName.isEmpty() ? instance.getOid().toJson() : "";
    }

    public static PreloadViewModel buildPreloadViewModel(TipoNovedad instance, String dsName) {
        if (TipoNovedadConstants.DSDSTIPONOVEDAD.equals(dsName))
            return new PreloadViewModel(instance.getOid().toJson(), instance.buildDSTipoNovedad());
        if (TipoNovedadConstants.DSICTIPONOVEDAD.equals(dsName))
            return new PreloadViewModel(instance.getOid().toJson(), instance.buildICTipoNovedad());
        if (TipoNovedadConstants.DSDSTIPONOVEDADNULL.equals(dsName))
            return new PreloadViewModel(instance.getOid().toJson(), instance.buildDSTipoNovedadNull());
        return new PreloadViewModel(instance.getOid().toJson(), "");
    }


    @Override
    protected void applyNavigationalFilters(FilterRequest filterRequest, SQLSelect sql) throws SystemException {
        if ("SolicitudNovedad_TCREAR_ptTipoNovedad_NavFilter0".equalsIgnoreCase(filterRequest.getName()))
            clas1581421428736234FiltNav5(filterRequest, sql);
        if ("SolicitudNovedad_TCREAREXCEPCION_ptTipoNovedad_NavFilter0".equalsIgnoreCase(filterRequest.getName()))
            clas1581421428736234FiltNav13(filterRequest, sql);
    }


    private void clas1581421428736234FiltNav5(FilterRequest filterRequest, SQLSelect sql) throws SystemException{
        SQLWhereConjunction conjunction = new SQLWhereConjunction();
        Planes solicitudNovedadTCREARptpagrPlan = filterRequest.hasFilterVariable(SolicitudNovedadConstants.ARG_NAME_TCREAR_SOLICITUDNOVEDADTCREARPTPAGRPLAN) ? planesSrv.getByOID(new PlanesOid().fromJsonString(toJson(filterRequest.getFilterVariable(SolicitudNovedadConstants.ARG_NAME_TCREAR_SOLICITUDNOVEDADTCREARPTPAGRPLAN)))) : null;

        // EXIST (Novedades.Dominios) WHERE (Novedades.Dominios.PlanT = ptpagrPlan) = true
        if (solicitudNovedadTCREARptpagrPlan != null) {
    // 'exist' Association operator
    SQLWhereOperandSubQuery operandSubQueryAssocOperator000 = new SQLWhereOperandSubQuery(repository.assocOperator000Range1Query(solicitudNovedadTCREARptpagrPlan, sql));
    conjunction.addOperand(operandSubQueryAssocOperator000);

        }        
        sql.addConjunction(conjunction);
    }

    private void clas1581421428736234FiltNav13(FilterRequest filterRequest, SQLSelect sql) throws SystemException{
        clas1581421428736234FiltNav5(filterRequest, sql);
    }

    // IIU Methods


    /*
     * Solves the query needed for PIUTipoNovedad
     *
     * @param queryRequest Query request
     * @return List of viewmodels for TipoNovedad using displayset DSTipoNovedad that 
     * match the search criteria
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<com.keralty.aeconfiguraciones.viewmodel.tiponovedad.ds.DSTipoNovedad> piuPIUTipoNovedad(QueryRequest queryRequest) {
        return solveTipoNovedadTypedQuery(queryRequest).stream()
                .map(com.keralty.aeconfiguraciones.viewmodel.tiponovedad.ds.DSTipoNovedad::new)
                .collect(Collectors.toList());
    }

    /*
     * Solves the COUNT query needed for PIUTipoNovedad
     *
     * @param queryRequest Query request
     * @returns Count of instances for PIUTipoNovedad
     */
    public int getCount4PIUTipoNovedad(QueryRequest queryRequest) {
        return solveTipoNovedadTypedQuery(queryRequest).size();
    }

    /*
     * Solves the query needed for PIUTipoNovedadNull
     *
     * @param queryRequest Query request
     * @return List of viewmodels for TipoNovedad using displayset DSTipoNovedadNull that 
     * match the search criteria
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<com.keralty.aeconfiguraciones.viewmodel.tiponovedad.ds.DSTipoNovedadNull> piuPIUTipoNovedadNull(QueryRequest queryRequest) {
        return solveTipoNovedadTypedQuery(queryRequest).stream()
                .map(com.keralty.aeconfiguraciones.viewmodel.tiponovedad.ds.DSTipoNovedadNull::new)
                .collect(Collectors.toList());
    }

    /*
     * Solves the COUNT query needed for PIUTipoNovedadNull
     *
     * @param queryRequest Query request
     * @returns Count of instances for PIUTipoNovedadNull
     */
    public int getCount4PIUTipoNovedadNull(QueryRequest queryRequest) {
        return solveTipoNovedadTypedQuery(queryRequest).size();
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
            com.keralty.aeconfiguraciones.viewmodel.tiponovedad.siu.CrearRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = tipoNovedadSrv.crearTxn(request);
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
            com.keralty.aeconfiguraciones.viewmodel.tiponovedad.siu.CrearRequest request) throws ModelException {
        return tipoNovedadSrv.crearImplementation(request);
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
            com.keralty.aeconfiguraciones.viewmodel.tiponovedad.siu.CrearRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Long tipoNovedadcrearpatrTipo = request.getTipoNovedadcrearpatrTipo();
        String tipoNovedadcrearpatrAliasTipo = request.getTipoNovedadcrearpatrAliasTipo();
        String tipoNovedadcrearpatrNombre = request.getTipoNovedadcrearpatrNombre();
        String tipoNovedadcrearpatrDescripcion = request.getTipoNovedadcrearpatrDescripcion();
        String tipoNovedadcrearpatrCodigoLegal = request.getTipoNovedadcrearpatrCodigoLegal();

        request.checkArguments();
        TipoNovedad instance = new TipoNovedad();
        instance.setTipoNovedadTipo(tipoNovedadcrearpatrTipo);
        instance.setTipoNovedadAliasTipo(tipoNovedadcrearpatrAliasTipo);
        instance.setTipoNovedadNombre(tipoNovedadcrearpatrNombre);
        instance.setTipoNovedadDescripcion(tipoNovedadcrearpatrDescripcion);
        instance.setTipoNovedadCodigoLegal(tipoNovedadcrearpatrCodigoLegal);
        if (tipoNovedadSrv.getByOID(instance.getOid()) != null) {
            throw new InstanceExistsException(TipoNovedadConstants.CLASS_ALIAS);
        }

        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICTipoNovedad());

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
            com.keralty.aeconfiguraciones.viewmodel.tiponovedad.siu.EliminarRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = tipoNovedadSrv.eliminarTxn(request);
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
            com.keralty.aeconfiguraciones.viewmodel.tiponovedad.siu.EliminarRequest request) throws ModelException {
        request.setTipoNovedadeliminarpthisTipoNovedadInstance(tipoNovedadSrv.getByOIDWithHV(new TipoNovedadOid().fromJsonString(request.getTipoNovedadeliminarpthisTipoNovedad())));
        return tipoNovedadSrv.eliminarImplementation(request);
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
            com.keralty.aeconfiguraciones.viewmodel.tiponovedad.siu.EliminarRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        TipoNovedad tipoNovedadeliminarpthisTipoNovedad = request.getTipoNovedadeliminarpthisTipoNovedadInstance();

        request.checkArguments();
        TipoNovedad instance = tipoNovedadeliminarpthisTipoNovedad;
        if(tipoNovedadeliminarpthisTipoNovedad.getNovedades().size() > 0) {
            throw new MinCardinalityException(TipoNovedadConstants.CLASS_ALIAS, TipoNovedadConstants.ROLE_ALIAS_NOVEDADES);
        }


        repository.delete(instance);
        
        response.setInstance(instance);
        response.setInstanceSuppInfo(instance.buildICTipoNovedad());

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
            com.keralty.aeconfiguraciones.viewmodel.tiponovedad.siu.ModificarRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = tipoNovedadSrv.modificarTxn(request);
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
            com.keralty.aeconfiguraciones.viewmodel.tiponovedad.siu.ModificarRequest request) throws ModelException {
        request.setTipoNovedadmodificarpthisTipoNovedadInstance(tipoNovedadSrv.getByOIDWithHV(new TipoNovedadOid().fromJsonString(request.getTipoNovedadmodificarpthisTipoNovedad())));
        return tipoNovedadSrv.modificarImplementation(request);
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
            com.keralty.aeconfiguraciones.viewmodel.tiponovedad.siu.ModificarRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        TipoNovedad tipoNovedadmodificarpthisTipoNovedad = request.getTipoNovedadmodificarpthisTipoNovedadInstance();
        String tipoNovedadmodificarpAliasTipo = request.getTipoNovedadmodificarpAliasTipo();
        String tipoNovedadmodificarpNombre = request.getTipoNovedadmodificarpNombre();
        String tipoNovedadmodificarpDescripcion = request.getTipoNovedadmodificarpDescripcion();
        String tipoNovedadmodificarpCodigoLegal = request.getTipoNovedadmodificarpCodigoLegal();

        request.checkArguments();
        TipoNovedad instance = tipoNovedadmodificarpthisTipoNovedad;
        instance.setTipoNovedadAliasTipo(tipoNovedadmodificarpAliasTipo);
        instance.setTipoNovedadNombre(tipoNovedadmodificarpNombre);
        instance.setTipoNovedadDescripcion(tipoNovedadmodificarpDescripcion);
        instance.setTipoNovedadCodigoLegal(tipoNovedadmodificarpCodigoLegal);


        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICTipoNovedad());

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
        select.getOrderByPart().addItem(Constants.normalizePath(TipoNovedadConstants.CLASS_NAME, TipoNovedadConstants.ATTR_FIELD_TIPONOVEDADTIPO, false), true);
    }
    
    @Override
    protected AbstractAppEntity buildRelatedInstance(String relatedInstanceJsonOid, String relatedInstancePathFirstItem, String remainingPath) {
        AbstractAppEntity relatedInstance = null;
        if (relatedInstancePathFirstItem.isEmpty()) {
            relatedInstance = buildTipoNovedadRelatedInstance(relatedInstanceJsonOid);
        }
        if (TipoNovedadConstants.ROLE_NAME_NOVEDADES.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildTipoNovedadRelatedInstance4Novedad(relatedInstanceJsonOid, remainingPath);
        }
        return relatedInstance;
    }
    
    private AbstractAppEntity buildTipoNovedadRelatedInstance(String relatedInstanceJsonOid) {
    	ObjectMapper mapper = new ObjectMapper();
    	try {
    		return new TipoNovedad(mapper.readValue(relatedInstanceJsonOid, TipoNovedadOid.class));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            throw new SystemException(e.getMessage());
    	}
    }

    private AbstractAppEntity buildTipoNovedadRelatedInstance4Novedad(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new Novedad(mapper.readValue(relatedInstanceJsonOid, NovedadOid.class)): Novedad.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            throw new SystemException(e.getMessage());
    	}
    }

    @Override
    protected void applyFilterByOid(String jsonOid, SQLSelect sql) {
        try {
            TipoNovedadOid oid = new TipoNovedadOid().fromJsonString(jsonOid);
            SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
            comparison1.setOperator("=");
            comparison1.setFirstOperand(new SQLWhereOperandPathAttribute(TipoNovedadConstants.ATTR_FIELD_TIPONOVEDADTIPO, Constants.Type.NAT.getTypeName()));
            comparison1.setSecondOperand(new SQLWhereOperandLiteral(oid.getTipoNovedadTipo(), Constants.Type.NAT.getTypeName()));
            sql.addComparison(comparison1);
        } catch (Exception e) {
            LoggerFactory.getLogger(TipoNovedadService.class).error(e.getMessage());
            throw new SystemException(e.getMessage());
        }
    }

}
