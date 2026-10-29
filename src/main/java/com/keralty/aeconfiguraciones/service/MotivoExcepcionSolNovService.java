package com.keralty.aeconfiguraciones.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.keralty.aeconfiguraciones.exception.MinCardinalityException;
import com.keralty.aeconfiguraciones.exception.ModelException;
import com.keralty.aeconfiguraciones.exception.SystemException;
import com.keralty.aeconfiguraciones.global.Constants;
import com.keralty.aeconfiguraciones.global.MotivoExcepcionSolNovConstants;
import com.keralty.aeconfiguraciones.persistence.AbstractAppEntity;
import com.keralty.aeconfiguraciones.persistence.MotivoExcepcionSolNov;
import com.keralty.aeconfiguraciones.persistence.oid.MotivoExcepcionSolNovOid;
import com.keralty.aeconfiguraciones.persistence.oid.SolicitudNovedadOid;
import com.keralty.aeconfiguraciones.persistence.SolicitudNovedad;
import com.keralty.aeconfiguraciones.repository.MotivoExcepcionSolNovJpaRepository;
import com.keralty.aeconfiguraciones.sqlutils.SQLSelect;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereComparison;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereOperandLiteral;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereOperandPathAttribute;
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
public class MotivoExcepcionSolNovService extends AbstractService {

    MotivoExcepcionSolNovJpaRepository repository;

    MotivoExcepcionSolNovService motivoExcepcionSolNovSrv;

    SolicitudNovedadService solicitudNovedadSrv;

    @Inject
    public MotivoExcepcionSolNovService(
            MotivoExcepcionSolNovService motivoExcepcionSolNovSrv,
            SolicitudNovedadService solicitudNovedadSrv,
            MotivoExcepcionSolNovJpaRepository repository) {
        this.motivoExcepcionSolNovSrv = motivoExcepcionSolNovSrv;
        this.solicitudNovedadSrv = solicitudNovedadSrv;
        this.repository = repository;
    }

    public List<Map<String, Object>> solveQuery(QueryRequest request) {
        return solveGenericQuery(request, MotivoExcepcionSolNovConstants.CLASS_NAME);
    }

    private List<MotivoExcepcionSolNov> solveMotivoExcepcionSolNovTypedQuery(QueryRequest request) {
        return repository.solveQuery(processQueryRequest(request, MotivoExcepcionSolNovConstants.CLASS_NAME), request.getPageSize(), request.getPageNumber());
    }
    
    private int solveMotivoExcepcionSolNovTypedCountQuery(QueryRequest request) {
        return repository.solveCountQuery(processCountQueryRequest(request, MotivoExcepcionSolNovConstants.CLASS_NAME));
    }

    /*
     * Returns the population of class MotivoExcepcionSolNov
     *
     * @return Population of class MotivoExcepcionSolNov
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<MotivoExcepcionSolNov> get() {
        List<MotivoExcepcionSolNov> results = new java.util.ArrayList<>();
        repository.findAll().forEach(results::add);
        return results;
    }
    
    /*
     * Gets the preloaded list of objects of class MotivoExcepcionSolNov
     *
     * @param queryRequest Request object that dictates the search conditions to 
     * retrieve objects
     *
     * @return Preloaded list of objects of class MotivoExcepcionSolNov
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<PreloadViewModel> getPreload(QueryRequest queryRequest) {
        List<PreloadViewModel> results = new ArrayList<>();
        List<MotivoExcepcionSolNov> instances = get(queryRequest);
        if(MotivoExcepcionSolNovConstants.DSDSMOTIVOEXCEPCIONSOLNOV.equals(queryRequest.getDisplaySetName())) {
            results = instances.stream().map(x -> new PreloadViewModel(x.getOid().toJson(), x.buildDSMotivoExcepcionSolNov())).collect(Collectors.toList());
        }
        if(MotivoExcepcionSolNovConstants.DSICMOTIVOEXCEPCIONSOLNOV.equals(queryRequest.getDisplaySetName())) {
            results = instances.stream().map(x -> new PreloadViewModel(x.getOid().toJson(), x.buildICMotivoExcepcionSolNov())).collect(Collectors.toList());
        }
        return results;
    }
    /*
     * Returns the population of class MotivoExcepcionSolNov that matches certain search criteria
     *
     * @param queryRequest Search criteria
     *
     * @return Population of class MotivoExcepcionSolNov matching the search criteria
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<MotivoExcepcionSolNov> get(QueryRequest queryRequest) {
        return solveMotivoExcepcionSolNovTypedQuery(queryRequest);
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
            return new PopulationResponseViewModel(solveMotivoExcepcionSolNovTypedQuery(queryRequest).stream()
                    .map(instance -> buildDynamicObject(instance, queryRequest.getDisplaySetItems()))
                    .collect(Collectors.toList()), queryRequest.getPageSize() != 0 ? solveMotivoExcepcionSolNovTypedCountQuery(queryRequest) : 0);
        } else {
            return new PreloadResponseViewModel(solveMotivoExcepcionSolNovTypedQuery(queryRequest).stream()
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
        return new PopulationResponseViewModel(solveMotivoExcepcionSolNovTypedQuery(queryRequest).stream()
                .map(instance -> buildDynamicObject(instance, displaySetItems))
                .collect(Collectors.toList()));
    }

    /*
     * Returns an instance of MotivoExcepcionSolNov by ID
     *
     * @param motivoExcepcionSolNovId Identification field motivoExcepcionSolNovId
     *
     * @return Instance of MotivoExcepcionSolNov
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public MotivoExcepcionSolNov getById(Long motivoExcepcionSolNovId) {
        return getByOID(new MotivoExcepcionSolNovOid(motivoExcepcionSolNovId));
    }
    
    /*
     * Returns an instance of MotivoExcepcionSolNov by OID
     *
     * @param oid Object IDentifier
     *
     * @return Instance of MotivoExcepcionSolNov
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public MotivoExcepcionSolNov getByOID(MotivoExcepcionSolNovOid oid) {
        Optional<MotivoExcepcionSolNov> instance = getInstanceByOid(oid);
        return instance.isPresent() ? instance.get() : null;
    }
    
    /*
     * Returns an instance of MotivoExcepcionSolNov by OID applying horizontal visibility constraints
     *
     * @param oid Object IDentifier
     *
     * @return Instance of MotivoExcepcionSolNov
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public MotivoExcepcionSolNov getByOIDWithHV(MotivoExcepcionSolNovOid oid) {
        Optional<MotivoExcepcionSolNov> instance = getInstanceByOid(oid);
    	return instance.isPresent() && instance.get().checkHorizontalVisibility(getAgentInstance()) 
    		 ? instance.get() 
    		 : null;
    }
    
    private Optional<MotivoExcepcionSolNov> getInstanceByOid(MotivoExcepcionSolNovOid oid) {
        return oid != null && oid.getMotivoExcepcionSolNovId() != null
               ? repository.findById(oid.getMotivoExcepcionSolNovId())
               : Optional.empty();
    }

    /*
     * Returns an instance of MotivoExcepcionSolNov by OID in JSON format
     *
     * @param jsonOid Object IDentifier in JSON format
     *
     * @return Instance of MotivoExcepcionSolNov
     */
    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public MotivoExcepcionSolNov getByJsonOid(String jsonOid) {
        MotivoExcepcionSolNovOid oid = new MotivoExcepcionSolNovOid();
        oid.fromJsonString(jsonOid);
        return getByOIDWithHV(oid);
    }

    /*
     * Returns the list of instances of MotivoExcepcionSolNov related to a given instance through 
     * a given path
     *
     * @param relatedInstance Related instance
     *
     * @param relatedInstancePath Related instance path
     *
     * @return List of instances of MotivoExcepcionSolNov
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<MotivoExcepcionSolNov> getByRelated(AbstractAppEntity relatedInstance, String relatedInstancePath) {
        QueryRequest queryRequest = new QueryRequest(new FilterRequest(Constants.FILTER_BY_RELATED,
                Arrays.asList(new FilterVariableRequest(Constants.FILTER_BY_RELATED_FVAR_PATH, relatedInstancePath),
                        new FilterVariableRequest(Constants.FILTER_BY_RELATED_FVAR_OID, relatedInstance.getOid().toJson()))));
        return solveMotivoExcepcionSolNovTypedQuery(queryRequest);
    }

    /*
     * Returns the list of instances of MotivoExcepcionSolNov related to a given instance through
     * a given path
     *
     * @param relatedInstance Related instance
     *
     * @param relatedInstancePath Related instance path
     *
     * @return List of instances of MotivoExcepcionSolNov
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<MotivoExcepcionSolNov> getInstancesByRelated(AbstractAppEntity relatedInstance, String relatedInstancePath) {
        return getByRelated(relatedInstance, relatedInstancePath);
    }

    /*
     * Returns a set of displayset items for an instance of MotivoExcepcionSolNov
     *
     * @param oid Object IDentifier
     *
     * @param displaySetItems List of comma-separated displayset items
     *
     * @return Set of displayset items for an instance of MotivoExcepcionSolNov
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public InstanceResponseViewModel getMotivoExcepcionSolNovDynamic(MotivoExcepcionSolNovOid oid, String displaySetItems) {
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
    public String getSuppInfo(MotivoExcepcionSolNovOid oid, String dsName) {
        MotivoExcepcionSolNov instance = getByOIDWithHV(oid);
        return (instance != null ? getSuppInfo(instance, dsName): "");
    }

    private String getSuppInfo(MotivoExcepcionSolNov instance, String dsName) {
        if (instance == null || instance.isNull()) return "";
        if (MotivoExcepcionSolNovConstants.DSICMOTIVOEXCEPCIONSOLNOV.equals(dsName))
            return instance.buildICMotivoExcepcionSolNov();
        return dsName.isEmpty() ? instance.getOid().toJson() : "";
    }

    public static PreloadViewModel buildPreloadViewModel(MotivoExcepcionSolNov instance, String dsName) {
        if (MotivoExcepcionSolNovConstants.DSDSMOTIVOEXCEPCIONSOLNOV.equals(dsName))
            return new PreloadViewModel(instance.getOid().toJson(), instance.buildDSMotivoExcepcionSolNov());
        if (MotivoExcepcionSolNovConstants.DSICMOTIVOEXCEPCIONSOLNOV.equals(dsName))
            return new PreloadViewModel(instance.getOid().toJson(), instance.buildICMotivoExcepcionSolNov());
        return new PreloadViewModel(instance.getOid().toJson(), "");
    }




    // IIU Methods


    /*
     * Solves the query needed for PIUMotivoExcepcionSolNov
     *
     * @param queryRequest Query request
     * @return List of viewmodels for MotivoExcepcionSolNov using displayset DSMotivoExcepcionSolNov that 
     * match the search criteria
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<com.keralty.aeconfiguraciones.viewmodel.motivoexcepcionsolnov.ds.DSMotivoExcepcionSolNov> piuPIUMotivoExcepcionSolNov(QueryRequest queryRequest) {
        return solveMotivoExcepcionSolNovTypedQuery(queryRequest).stream()
                .map(com.keralty.aeconfiguraciones.viewmodel.motivoexcepcionsolnov.ds.DSMotivoExcepcionSolNov::new)
                .collect(Collectors.toList());
    }

    /*
     * Solves the COUNT query needed for PIUMotivoExcepcionSolNov
     *
     * @param queryRequest Query request
     * @returns Count of instances for PIUMotivoExcepcionSolNov
     */
    public int getCount4PIUMotivoExcepcionSolNov(QueryRequest queryRequest) {
        return solveMotivoExcepcionSolNovTypedQuery(queryRequest).size();
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
            com.keralty.aeconfiguraciones.viewmodel.motivoexcepcionsolnov.siu.CrearRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = motivoExcepcionSolNovSrv.crearTxn(request);
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
            com.keralty.aeconfiguraciones.viewmodel.motivoexcepcionsolnov.siu.CrearRequest request) throws ModelException {
        return motivoExcepcionSolNovSrv.crearImplementation(request);
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
            com.keralty.aeconfiguraciones.viewmodel.motivoexcepcionsolnov.siu.CrearRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        String motivoExcepcionSolNovcrearpatrDescripcion = request.getMotivoExcepcionSolNovcrearpatrDescripcion();

        request.checkArguments();
        MotivoExcepcionSolNov instance = new MotivoExcepcionSolNov();
        instance.setMotivoExcepcionSolNovId(Long.valueOf(0));
        instance.setMotivoExcepcionSolNovDescripcion(motivoExcepcionSolNovcrearpatrDescripcion);

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
            com.keralty.aeconfiguraciones.viewmodel.motivoexcepcionsolnov.siu.EliminarRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = motivoExcepcionSolNovSrv.eliminarTxn(request);
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
            com.keralty.aeconfiguraciones.viewmodel.motivoexcepcionsolnov.siu.EliminarRequest request) throws ModelException {
        request.setMotivoExcepcionSolNoveliminarpthisMotivoExcepcionSolNovInstance(motivoExcepcionSolNovSrv.getByOIDWithHV(request.getMotivoExcepcionSolNoveliminarpthisMotivoExcepcionSolNov()));
        return motivoExcepcionSolNovSrv.eliminarImplementation(request);
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
            com.keralty.aeconfiguraciones.viewmodel.motivoexcepcionsolnov.siu.EliminarRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        MotivoExcepcionSolNov motivoExcepcionSolNoveliminarpthisMotivoExcepcionSolNov = request.getMotivoExcepcionSolNoveliminarpthisMotivoExcepcionSolNovInstance();

        request.checkArguments();
        MotivoExcepcionSolNov instance = motivoExcepcionSolNoveliminarpthisMotivoExcepcionSolNov;
        if(motivoExcepcionSolNoveliminarpthisMotivoExcepcionSolNov.getSolicitudes().size() > 0) {
            throw new MinCardinalityException(MotivoExcepcionSolNovConstants.CLASS_ALIAS, MotivoExcepcionSolNovConstants.ROLE_ALIAS_SOLICITUDES);
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
            com.keralty.aeconfiguraciones.viewmodel.motivoexcepcionsolnov.siu.ModificarRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = motivoExcepcionSolNovSrv.modificarTxn(request);
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
            com.keralty.aeconfiguraciones.viewmodel.motivoexcepcionsolnov.siu.ModificarRequest request) throws ModelException {
        request.setMotivoExcepcionSolNovmodificarpthisMotivoExcepcionSolNovInstance(motivoExcepcionSolNovSrv.getByOIDWithHV(request.getMotivoExcepcionSolNovmodificarpthisMotivoExcepcionSolNov()));
        return motivoExcepcionSolNovSrv.modificarImplementation(request);
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
            com.keralty.aeconfiguraciones.viewmodel.motivoexcepcionsolnov.siu.ModificarRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        MotivoExcepcionSolNov motivoExcepcionSolNovmodificarpthisMotivoExcepcionSolNov = request.getMotivoExcepcionSolNovmodificarpthisMotivoExcepcionSolNovInstance();
        String motivoExcepcionSolNovmodificarpDescripcion = request.getMotivoExcepcionSolNovmodificarpDescripcion();

        request.checkArguments();
        MotivoExcepcionSolNov instance = motivoExcepcionSolNovmodificarpthisMotivoExcepcionSolNov;
        instance.setMotivoExcepcionSolNovDescripcion(motivoExcepcionSolNovmodificarpDescripcion);


        response.setInstance(repository.save(instance));

        return response;
    }

    @Override
    protected void applyOrderCriteria(QueryRequest queryRequest, SQLSelect select) {
        applyOCOCMotivoExcepcionSolNov(queryRequest, select);
        applyDSItemSort(queryRequest, select);
        applyDefaultOrderCriteria(queryRequest, select);
    }

    private void applyDefaultOrderCriteria(QueryRequest queryRequest, SQLSelect select) {
        if (queryRequest.getOrderCriteriaName() != null && !queryRequest.getOrderCriteriaName().isBlank())
            return;
        if (queryRequest.getDsItemSort() != null && !queryRequest.getDsItemSort().isBlank())
            return;
        select.getOrderByPart().addItem(Constants.normalizePath(MotivoExcepcionSolNovConstants.CLASS_NAME, MotivoExcepcionSolNovConstants.ATTR_FIELD_MOTIVOEXCEPCIONSOLNOVID, false), true);
    }


    private void applyOCOCMotivoExcepcionSolNov(QueryRequest queryRequest, SQLSelect select) {
        if ("OC_MotivoExcepcionSolNov".equalsIgnoreCase(queryRequest.getOrderCriteriaName())) {
            select.getOrderByPart().addItem(Constants.normalizePath(MotivoExcepcionSolNovConstants.CLASS_NAME, MotivoExcepcionSolNovConstants.ATTR_NAME_MOTIVOEXCEPCIONSOLNOVDESCRIPCION, false), true);
        }
    }
    
    @Override
    protected AbstractAppEntity buildRelatedInstance(String relatedInstanceJsonOid, String relatedInstancePathFirstItem, String remainingPath) {
        AbstractAppEntity relatedInstance = null;
        if (relatedInstancePathFirstItem.isEmpty()) {
            relatedInstance = buildMotivoExcepcionSolNovRelatedInstance(relatedInstanceJsonOid);
        }
        if (MotivoExcepcionSolNovConstants.ROLE_NAME_SOLICITUDES.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildMotivoExcepcionSolNovRelatedInstance4SolicitudNovedad(relatedInstanceJsonOid, remainingPath);
        }
        return relatedInstance;
    }
    
    private AbstractAppEntity buildMotivoExcepcionSolNovRelatedInstance(String relatedInstanceJsonOid) {
    	ObjectMapper mapper = new ObjectMapper();
    	try {
    		return new MotivoExcepcionSolNov(mapper.readValue(relatedInstanceJsonOid, MotivoExcepcionSolNovOid.class));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            throw new SystemException(e.getMessage());
    	}
    }

    private AbstractAppEntity buildMotivoExcepcionSolNovRelatedInstance4SolicitudNovedad(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new SolicitudNovedad(mapper.readValue(relatedInstanceJsonOid, SolicitudNovedadOid.class)): SolicitudNovedad.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            throw new SystemException(e.getMessage());
    	}
    }

    @Override
    protected void applyFilterByOid(String jsonOid, SQLSelect sql) {
        try {
            MotivoExcepcionSolNovOid oid = new MotivoExcepcionSolNovOid().fromJsonString(jsonOid);
            SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
            comparison1.setOperator("=");
            comparison1.setFirstOperand(new SQLWhereOperandPathAttribute(MotivoExcepcionSolNovConstants.ATTR_FIELD_MOTIVOEXCEPCIONSOLNOVID, Constants.Type.AUTONUMERIC.getTypeName()));
            comparison1.setSecondOperand(new SQLWhereOperandLiteral(oid.getMotivoExcepcionSolNovId(), Constants.Type.AUTONUMERIC.getTypeName()));
            sql.addComparison(comparison1);
        } catch (Exception e) {
            LoggerFactory.getLogger(MotivoExcepcionSolNovService.class).error(e.getMessage());
            throw new SystemException(e.getMessage());
        }
    }

}
