package com.keralty.aeconfiguraciones.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.keralty.aeconfiguraciones.exception.ModelException;
import com.keralty.aeconfiguraciones.global.Constants;
import com.keralty.aeconfiguraciones.global.ProteccionDatosConstants;
import com.keralty.aeconfiguraciones.global.UserFunctions;
import com.keralty.aeconfiguraciones.persistence.AbstractAppEntity;
import com.keralty.aeconfiguraciones.persistence.oid.ProductosOid;
import com.keralty.aeconfiguraciones.persistence.oid.ProteccionDatosOid;
import com.keralty.aeconfiguraciones.persistence.oid.SolicitudNovedadOid;
import com.keralty.aeconfiguraciones.persistence.oid.SolicitudOid;
import com.keralty.aeconfiguraciones.persistence.Productos;
import com.keralty.aeconfiguraciones.persistence.ProteccionDatos;
import com.keralty.aeconfiguraciones.persistence.Solicitud;
import com.keralty.aeconfiguraciones.persistence.SolicitudNovedad;
import com.keralty.aeconfiguraciones.repository.ProteccionDatosJpaRepository;
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
public class ProteccionDatosService extends AbstractService {

    ProteccionDatosJpaRepository repository;

    ProteccionDatosService proteccionDatosSrv;

    ProductosService productosSrv;

    SolicitudNovedadService solicitudNovedadSrv;

    SolicitudService solicitudSrv;

    UserFunctions userFunctionsSrv;

    @Inject
    public ProteccionDatosService(
            ProteccionDatosService proteccionDatosSrv,
            ProductosService productosSrv,
            SolicitudNovedadService solicitudNovedadSrv,
            SolicitudService solicitudSrv,
            UserFunctions userFunctionsSrv,
            ProteccionDatosJpaRepository repository) {
        this.proteccionDatosSrv = proteccionDatosSrv;
        this.productosSrv = productosSrv;
        this.solicitudNovedadSrv = solicitudNovedadSrv;
        this.solicitudSrv = solicitudSrv;
        this.userFunctionsSrv = userFunctionsSrv;
        this.repository = repository;
    }

    public List<Map<String, Object>> solveQuery(QueryRequest request) {
        return solveGenericQuery(request, ProteccionDatosConstants.CLASS_NAME);
    }

    private List<ProteccionDatos> solveProteccionDatosTypedQuery(QueryRequest request) {
        return repository.solveQuery(processQueryRequest(request, ProteccionDatosConstants.CLASS_NAME), request.getPageSize(), request.getPageNumber());
    }
    
    private int solveProteccionDatosTypedCountQuery(QueryRequest request) {
        return repository.solveCountQuery(processCountQueryRequest(request, ProteccionDatosConstants.CLASS_NAME));
    }

    /*
     * Returns the population of class ProteccionDatos
     *
     * @return Population of class ProteccionDatos
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<ProteccionDatos> get() {
        List<ProteccionDatos> results = new java.util.ArrayList<>();
        repository.findAll().forEach(results::add);
        return results;
    }
    /*
     * Returns the population of class ProteccionDatos that matches certain search criteria
     *
     * @param queryRequest Search criteria
     *
     * @return Population of class ProteccionDatos matching the search criteria
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<ProteccionDatos> get(QueryRequest queryRequest) {
        return solveProteccionDatosTypedQuery(queryRequest);
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
            return new PopulationResponseViewModel(solveProteccionDatosTypedQuery(queryRequest).stream()
                    .map(instance -> buildDynamicObject(instance, queryRequest.getDisplaySetItems()))
                    .collect(Collectors.toList()), queryRequest.getPageSize() != 0 ? solveProteccionDatosTypedCountQuery(queryRequest) : 0);
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
        return new PopulationResponseViewModel(solveProteccionDatosTypedQuery(queryRequest).stream()
                .map(instance -> buildDynamicObject(instance, displaySetItems))
                .collect(Collectors.toList()));
    }

    /*
     * Returns an instance of ProteccionDatos by ID
     *
     * @param proteccionDatosIdentificador Identification field proteccionDatosIdentificador
     *
     * @return Instance of ProteccionDatos
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ProteccionDatos getById(Long proteccionDatosIdentificador) {
        return getByOID(new ProteccionDatosOid(proteccionDatosIdentificador));
    }
    
    /*
     * Returns an instance of ProteccionDatos by OID
     *
     * @param oid Object IDentifier
     *
     * @return Instance of ProteccionDatos
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ProteccionDatos getByOID(ProteccionDatosOid oid) {
        Optional<ProteccionDatos> instance = getInstanceByOid(oid);
        return instance.isPresent() ? instance.get() : null;
    }
    
    /*
     * Returns an instance of ProteccionDatos by OID applying horizontal visibility constraints
     *
     * @param oid Object IDentifier
     *
     * @return Instance of ProteccionDatos
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public ProteccionDatos getByOIDWithHV(ProteccionDatosOid oid) {
        Optional<ProteccionDatos> instance = getInstanceByOid(oid);
    	return instance.isPresent() && instance.get().checkHorizontalVisibility(getAgentInstance()) 
    		 ? instance.get() 
    		 : null;
    }
    
    private Optional<ProteccionDatos> getInstanceByOid(ProteccionDatosOid oid) {
        return oid != null && oid.getProteccionDatosIdentificador() != null
               ? repository.findById(oid.getProteccionDatosIdentificador())
               : Optional.empty();
    }

    /*
     * Returns an instance of ProteccionDatos by OID in JSON format
     *
     * @param jsonOid Object IDentifier in JSON format
     *
     * @return Instance of ProteccionDatos
     */
    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public ProteccionDatos getByJsonOid(String jsonOid) {
        ProteccionDatosOid oid = new ProteccionDatosOid();
        oid.fromJsonString(jsonOid);
        return getByOIDWithHV(oid);
    }

    /*
     * Returns the list of instances of ProteccionDatos related to a given instance through 
     * a given path
     *
     * @param relatedInstance Related instance
     *
     * @param relatedInstancePath Related instance path
     *
     * @return List of instances of ProteccionDatos
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<ProteccionDatos> getByRelated(AbstractAppEntity relatedInstance, String relatedInstancePath) {
        QueryRequest queryRequest = new QueryRequest(new FilterRequest(Constants.FILTER_BY_RELATED,
                Arrays.asList(new FilterVariableRequest(Constants.FILTER_BY_RELATED_FVAR_PATH, relatedInstancePath),
                        new FilterVariableRequest(Constants.FILTER_BY_RELATED_FVAR_OID, relatedInstance.getOid().toJson()))));
        return solveProteccionDatosTypedQuery(queryRequest);
    }

    /*
     * Returns the list of instances of ProteccionDatos related to a given instance through
     * a given path
     *
     * @param relatedInstance Related instance
     *
     * @param relatedInstancePath Related instance path
     *
     * @return List of instances of ProteccionDatos
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<ProteccionDatos> getInstancesByRelated(AbstractAppEntity relatedInstance, String relatedInstancePath) {
        return getByRelated(relatedInstance, relatedInstancePath);
    }

    /*
     * Returns a set of displayset items for an instance of ProteccionDatos
     *
     * @param oid Object IDentifier
     *
     * @param displaySetItems List of comma-separated displayset items
     *
     * @return Set of displayset items for an instance of ProteccionDatos
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public InstanceResponseViewModel getProteccionDatosDynamic(ProteccionDatosOid oid, String displaySetItems) {
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
    public String getSuppInfo(ProteccionDatosOid oid, String dsName) {
        ProteccionDatos instance = getByOIDWithHV(oid);
        return (instance != null ? getSuppInfo(instance, dsName): "");
    }

    private String getSuppInfo(ProteccionDatos instance, String dsName) {
        if (instance == null || instance.isNull()) return "";
        return dsName.isEmpty() ? instance.getOid().toJson() : "";
    }




    // IIU Methods


    /*
     * Solves the query needed for PIUProteccionDatosNull
     *
     * @param queryRequest Query request
     * @return List of viewmodels for ProteccionDatos using displayset DSProteccionDatosNull that 
     * match the search criteria
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<com.keralty.aeconfiguraciones.viewmodel.protecciondatos.ds.DSProteccionDatosNull> piuPIUProteccionDatosNull(QueryRequest queryRequest) {
        return solveProteccionDatosTypedQuery(queryRequest).stream()
                .map(com.keralty.aeconfiguraciones.viewmodel.protecciondatos.ds.DSProteccionDatosNull::new)
                .collect(Collectors.toList());
    }

    /*
     * Solves the COUNT query needed for PIUProteccionDatosNull
     *
     * @param queryRequest Query request
     * @returns Count of instances for PIUProteccionDatosNull
     */
    public int getCount4PIUProteccionDatosNull(QueryRequest queryRequest) {
        return solveProteccionDatosTypedQuery(queryRequest).size();
    }

    // Service: InsProductoProteccionDatos

    /*
     * Entry point for service InsProductoProteccionDatos
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse insProductoProteccionDatos(
            com.keralty.aeconfiguraciones.viewmodel.protecciondatos.siu.InsProductoProteccionDatosRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = proteccionDatosSrv.insProductoProteccionDatosTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service InsProductoProteccionDatos
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse insProductoProteccionDatosTxn(
            com.keralty.aeconfiguraciones.viewmodel.protecciondatos.siu.InsProductoProteccionDatosRequest request) throws ModelException {
        request.setProteccionDatosInsProductoProteccionDatospthisProteccionDatosInstance(proteccionDatosSrv.getByOIDWithHV(request.getProteccionDatosInsProductoProteccionDatospthisProteccionDatos()));
        request.setProteccionDatosInsProductoProteccionDatospevcProductosInstance(productosSrv.getByOIDWithHV(request.getProteccionDatosInsProductoProteccionDatospevcProductos()));
        return proteccionDatosSrv.insProductoProteccionDatosImplementation(request, true);
    }

    /*
     * Implements service InsProductoProteccionDatos
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse insProductoProteccionDatosImplementation(
            com.keralty.aeconfiguraciones.viewmodel.protecciondatos.siu.InsProductoProteccionDatosRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        ProteccionDatos proteccionDatosInsProductoProteccionDatospthisProteccionDatos = request.getProteccionDatosInsProductoProteccionDatospthisProteccionDatosInstance();
        Productos proteccionDatosInsProductoProteccionDatospevcProductos = request.getProteccionDatosInsProductoProteccionDatospevcProductosInstance();

        request.checkArguments();
        ProteccionDatos instance = proteccionDatosInsProductoProteccionDatospthisProteccionDatos;

        instance.add2ProductoT(proteccionDatosInsProductoProteccionDatospevcProductos);
        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.productos.siu.InsProductoProteccionDatosRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.productos.siu.InsProductoProteccionDatosRequest();
            relatedRequest.setProductosInsProductoProteccionDatospthisProductoInstance(proteccionDatosInsProductoProteccionDatospevcProductos);
            relatedRequest.setProductosInsProductoProteccionDatospevcProteccionDatosInstance(proteccionDatosInsProductoProteccionDatospthisProteccionDatos);
            productosSrv.insProductoProteccionDatosImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));

        return response;
    }

    // Service: DelProductoProteccionDatos

    /*
     * Entry point for service DelProductoProteccionDatos
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse delProductoProteccionDatos(
            com.keralty.aeconfiguraciones.viewmodel.protecciondatos.siu.DelProductoProteccionDatosRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = proteccionDatosSrv.delProductoProteccionDatosTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service DelProductoProteccionDatos
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse delProductoProteccionDatosTxn(
            com.keralty.aeconfiguraciones.viewmodel.protecciondatos.siu.DelProductoProteccionDatosRequest request) throws ModelException {
        request.setProteccionDatosDelProductoProteccionDatospthisProteccionDatosInstance(proteccionDatosSrv.getByOIDWithHV(request.getProteccionDatosDelProductoProteccionDatospthisProteccionDatos()));
        request.setProteccionDatosDelProductoProteccionDatospevcProductosInstance(productosSrv.getByOIDWithHV(request.getProteccionDatosDelProductoProteccionDatospevcProductos()));
        return proteccionDatosSrv.delProductoProteccionDatosImplementation(request, true);
    }

    /*
     * Implements service DelProductoProteccionDatos
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse delProductoProteccionDatosImplementation(
            com.keralty.aeconfiguraciones.viewmodel.protecciondatos.siu.DelProductoProteccionDatosRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        ProteccionDatos proteccionDatosDelProductoProteccionDatospthisProteccionDatos = request.getProteccionDatosDelProductoProteccionDatospthisProteccionDatosInstance();
        Productos proteccionDatosDelProductoProteccionDatospevcProductos = request.getProteccionDatosDelProductoProteccionDatospevcProductosInstance();

        request.checkArguments();
        ProteccionDatos instance = proteccionDatosDelProductoProteccionDatospthisProteccionDatos;

        instance.add2ProductoT(null);
        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.productos.siu.DelProductoProteccionDatosRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.productos.siu.DelProductoProteccionDatosRequest();
            relatedRequest.setProductosDelProductoProteccionDatospthisProductoInstance(proteccionDatosDelProductoProteccionDatospevcProductos);
            relatedRequest.setProductosDelProductoProteccionDatospevcProteccionDatosInstance(proteccionDatosDelProductoProteccionDatospthisProteccionDatos);
            productosSrv.delProductoProteccionDatosImplementation(relatedRequest, false);
        }


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
        select.getOrderByPart().addItem(Constants.normalizePath(ProteccionDatosConstants.CLASS_NAME, ProteccionDatosConstants.ATTR_FIELD_PROTECCIONDATOSIDENTIFICADOR, false), true);
    }
    
    @Override
    protected AbstractAppEntity buildRelatedInstance(String relatedInstanceJsonOid, String relatedInstancePathFirstItem, String remainingPath) {
        AbstractAppEntity relatedInstance = null;
        if (relatedInstancePathFirstItem.isEmpty()) {
            relatedInstance = buildProteccionDatosRelatedInstance(relatedInstanceJsonOid);
        }
        if (ProteccionDatosConstants.ROLE_NAME_SOLICITUDNOVEDAD.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildProteccionDatosRelatedInstance4SolicitudNovedad(relatedInstanceJsonOid, remainingPath);
        }
        if (ProteccionDatosConstants.ROLE_NAME_PRODUCTOT.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildProteccionDatosRelatedInstance4Productos(relatedInstanceJsonOid, remainingPath);
        }
        if (ProteccionDatosConstants.ROLE_NAME_SOLICITUD.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildProteccionDatosRelatedInstance4Solicitud(relatedInstanceJsonOid, remainingPath);
        }
        return relatedInstance;
    }
    
    private AbstractAppEntity buildProteccionDatosRelatedInstance(String relatedInstanceJsonOid) {
    	ObjectMapper mapper = new ObjectMapper();
    	try {
    		return new ProteccionDatos(mapper.readValue(relatedInstanceJsonOid, ProteccionDatosOid.class));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    private AbstractAppEntity buildProteccionDatosRelatedInstance4SolicitudNovedad(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new SolicitudNovedad(mapper.readValue(relatedInstanceJsonOid, SolicitudNovedadOid.class)): SolicitudNovedad.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    private AbstractAppEntity buildProteccionDatosRelatedInstance4Productos(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new Productos(mapper.readValue(relatedInstanceJsonOid, ProductosOid.class)): Productos.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    private AbstractAppEntity buildProteccionDatosRelatedInstance4Solicitud(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new Solicitud(mapper.readValue(relatedInstanceJsonOid, SolicitudOid.class)): Solicitud.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    @Override
    protected void applyFilterByOid(String jsonOid, SQLSelect sql) {
        try {
            ProteccionDatosOid oid = new ProteccionDatosOid().fromJsonString(jsonOid);
            SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
            comparison1.setOperator("=");
            comparison1.setFirstOperand(new SQLWhereOperandPathAttribute(ProteccionDatosConstants.ATTR_FIELD_PROTECCIONDATOSIDENTIFICADOR, Constants.Type.AUTONUMERIC.getTypeName()));
            comparison1.setSecondOperand(new SQLWhereOperandLiteral(oid.getProteccionDatosIdentificador(), Constants.Type.AUTONUMERIC.getTypeName()));
            sql.addComparison(comparison1);
        } catch (Exception e) {
            LoggerFactory.getLogger(ProteccionDatosService.class).error(e.getMessage());
        }
    }

}
