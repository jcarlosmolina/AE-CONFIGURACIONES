package com.keralty.aeconfiguraciones.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.keralty.aeconfiguraciones.exception.InstanceExistsException;
import com.keralty.aeconfiguraciones.exception.MinCardinalityException;
import com.keralty.aeconfiguraciones.exception.ModelException;
import com.keralty.aeconfiguraciones.exception.SystemException;
import com.keralty.aeconfiguraciones.global.Constants;
import com.keralty.aeconfiguraciones.global.TipoFacturaConstants;
import com.keralty.aeconfiguraciones.persistence.AbstractAppEntity;
import com.keralty.aeconfiguraciones.persistence.oid.SolicitudOid;
import com.keralty.aeconfiguraciones.persistence.oid.TipoFacturaOid;
import com.keralty.aeconfiguraciones.persistence.Solicitud;
import com.keralty.aeconfiguraciones.persistence.TipoFactura;
import com.keralty.aeconfiguraciones.repository.TipoFacturaJpaRepository;
import com.keralty.aeconfiguraciones.sqlutils.SQLSelect;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereComparison;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereConjunction;
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
public class TipoFacturaService extends AbstractService {

    TipoFacturaJpaRepository repository;

    TipoFacturaService tipoFacturaSrv;

    SolicitudService solicitudSrv;

    @Inject
    public TipoFacturaService(
            TipoFacturaService tipoFacturaSrv,
            SolicitudService solicitudSrv,
            TipoFacturaJpaRepository repository) {
        this.tipoFacturaSrv = tipoFacturaSrv;
        this.solicitudSrv = solicitudSrv;
        this.repository = repository;
    }

    public List<Map<String, Object>> solveQuery(QueryRequest request) {
        return solveGenericQuery(request, TipoFacturaConstants.CLASS_NAME);
    }

    private List<TipoFactura> solveTipoFacturaTypedQuery(QueryRequest request) {
        return repository.solveQuery(processQueryRequest(request, TipoFacturaConstants.CLASS_NAME), request.getPageSize(), request.getPageNumber());
    }
    
    private int solveTipoFacturaTypedCountQuery(QueryRequest request) {
        return repository.solveCountQuery(processCountQueryRequest(request, TipoFacturaConstants.CLASS_NAME));
    }

    /*
     * Returns the population of class TipoFactura
     *
     * @return Population of class TipoFactura
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<TipoFactura> get() {
        List<TipoFactura> results = new java.util.ArrayList<>();
        repository.findAll().forEach(results::add);
        return results;
    }
    
    /*
     * Gets the preloaded list of objects of class TipoFactura
     *
     * @param queryRequest Request object that dictates the search conditions to 
     * retrieve objects
     *
     * @return Preloaded list of objects of class TipoFactura
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<PreloadViewModel> getPreload(QueryRequest queryRequest) {
        List<PreloadViewModel> results = new ArrayList<>();
        List<TipoFactura> instances = get(queryRequest);
        if(TipoFacturaConstants.DSICTIPOFACTURA.equals(queryRequest.getDisplaySetName())) {
            results = instances.stream().map(x -> new PreloadViewModel(x.getOid().toJson(), x.buildICTipoFactura())).collect(Collectors.toList());
        }
        if(TipoFacturaConstants.DSDSTIPOFACTURA.equals(queryRequest.getDisplaySetName())) {
            results = instances.stream().map(x -> new PreloadViewModel(x.getOid().toJson(), x.buildDSTipoFactura())).collect(Collectors.toList());
        }
        return results;
    }
    /*
     * Returns the population of class TipoFactura that matches certain search criteria
     *
     * @param queryRequest Search criteria
     *
     * @return Population of class TipoFactura matching the search criteria
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<TipoFactura> get(QueryRequest queryRequest) {
        return solveTipoFacturaTypedQuery(queryRequest);
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
            return new PopulationResponseViewModel(solveTipoFacturaTypedQuery(queryRequest).stream()
                    .map(instance -> buildDynamicObject(instance, queryRequest.getDisplaySetItems()))
                    .collect(Collectors.toList()), queryRequest.getPageSize() != 0 ? solveTipoFacturaTypedCountQuery(queryRequest) : 0);
        } else {
            return new PreloadResponseViewModel(solveTipoFacturaTypedQuery(queryRequest).stream()
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
        return new PopulationResponseViewModel(solveTipoFacturaTypedQuery(queryRequest).stream()
                .map(instance -> buildDynamicObject(instance, displaySetItems))
                .collect(Collectors.toList()));
    }

    /*
     * Returns an instance of TipoFactura by ID
     *
     * @param tipoFacturaCodigo Identification field tipoFacturaCodigo
     *
     * @return Instance of TipoFactura
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public TipoFactura getById(String tipoFacturaCodigo) {
        return getByOID(new TipoFacturaOid(tipoFacturaCodigo));
    }
    
    /*
     * Returns an instance of TipoFactura by OID
     *
     * @param oid Object IDentifier
     *
     * @return Instance of TipoFactura
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public TipoFactura getByOID(TipoFacturaOid oid) {
        Optional<TipoFactura> instance = getInstanceByOid(oid);
        return instance.isPresent() ? instance.get() : null;
    }
    
    /*
     * Returns an instance of TipoFactura by OID applying horizontal visibility constraints
     *
     * @param oid Object IDentifier
     *
     * @return Instance of TipoFactura
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public TipoFactura getByOIDWithHV(TipoFacturaOid oid) {
        Optional<TipoFactura> instance = getInstanceByOid(oid);
    	return instance.isPresent() && instance.get().checkHorizontalVisibility(getAgentInstance()) 
    		 ? instance.get() 
    		 : null;
    }
    
    private Optional<TipoFactura> getInstanceByOid(TipoFacturaOid oid) {
        return oid != null && oid.getTipoFacturaCodigo() != null
               ? repository.findById(oid.getTipoFacturaCodigo())
               : Optional.empty();
    }

    /*
     * Returns an instance of TipoFactura by OID in JSON format
     *
     * @param jsonOid Object IDentifier in JSON format
     *
     * @return Instance of TipoFactura
     */
    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public TipoFactura getByJsonOid(String jsonOid) {
        TipoFacturaOid oid = new TipoFacturaOid();
        oid.fromJsonString(jsonOid);
        return getByOIDWithHV(oid);
    }

    /*
     * Returns the list of instances of TipoFactura related to a given instance through 
     * a given path
     *
     * @param relatedInstance Related instance
     *
     * @param relatedInstancePath Related instance path
     *
     * @return List of instances of TipoFactura
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<TipoFactura> getByRelated(AbstractAppEntity relatedInstance, String relatedInstancePath) {
        QueryRequest queryRequest = new QueryRequest(new FilterRequest(Constants.FILTER_BY_RELATED,
                Arrays.asList(new FilterVariableRequest(Constants.FILTER_BY_RELATED_FVAR_PATH, relatedInstancePath),
                        new FilterVariableRequest(Constants.FILTER_BY_RELATED_FVAR_OID, relatedInstance.getOid().toJson()))));
        return solveTipoFacturaTypedQuery(queryRequest);
    }

    /*
     * Returns the list of instances of TipoFactura related to a given instance through
     * a given path
     *
     * @param relatedInstance Related instance
     *
     * @param relatedInstancePath Related instance path
     *
     * @return List of instances of TipoFactura
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<TipoFactura> getInstancesByRelated(AbstractAppEntity relatedInstance, String relatedInstancePath) {
        return getByRelated(relatedInstance, relatedInstancePath);
    }

    /*
     * Returns a set of displayset items for an instance of TipoFactura
     *
     * @param oid Object IDentifier
     *
     * @param displaySetItems List of comma-separated displayset items
     *
     * @return Set of displayset items for an instance of TipoFactura
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public InstanceResponseViewModel getTipoFacturaDynamic(TipoFacturaOid oid, String displaySetItems) {
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
    public String getSuppInfo(TipoFacturaOid oid, String dsName) {
        TipoFactura instance = getByOIDWithHV(oid);
        return (instance != null ? getSuppInfo(instance, dsName): "");
    }

    private String getSuppInfo(TipoFactura instance, String dsName) {
        if (instance == null || instance.isNull()) return "";
        if (TipoFacturaConstants.DSICTIPOFACTURA.equals(dsName))
            return instance.buildICTipoFactura();
        return dsName.isEmpty() ? instance.getOid().toJson() : "";
    }

    public static PreloadViewModel buildPreloadViewModel(TipoFactura instance, String dsName) {
        if (TipoFacturaConstants.DSICTIPOFACTURA.equals(dsName))
            return new PreloadViewModel(instance.getOid().toJson(), instance.buildICTipoFactura());
        if (TipoFacturaConstants.DSDSTIPOFACTURA.equals(dsName))
            return new PreloadViewModel(instance.getOid().toJson(), instance.buildDSTipoFactura());
        return new PreloadViewModel(instance.getOid().toJson(), "");
    }


    @Override
    protected void applyNavigationalFilters(FilterRequest filterRequest, SQLSelect sql) throws SystemException {
        if ("Ae_solicitudes_TNUEVAAFILIACION_ptTipoFactura_NavFilter0".equalsIgnoreCase(filterRequest.getName()))
            clas1431956094976663FiltNav15(sql);
        if ("Solicitud_TMODENCABEZADO_ptTipoFactura_NavFilter0".equalsIgnoreCase(filterRequest.getName()))
            clas1431957012480960FiltNav48(sql);
    }


    private void clas1431956094976663FiltNav15(SQLSelect sql) {
        SQLWhereConjunction conjunction = new SQLWhereConjunction();

        // Activo = true
        // '=' Comparison
        SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
        comparison1.setOperator("=");
        
        // Operand 1
        SQLWhereOperandPathAttribute operand112 = new SQLWhereOperandPathAttribute(TipoFacturaConstants.ATTR_NAME_TIPOFACTURAACTIVO, Constants.Type.BOOL.getTypeName());
        comparison1.setFirstOperand(operand112);
        
        // Operand 2
        comparison1.setSecondOperand(new SQLWhereOperandLiteral(Boolean.TRUE, Constants.Type.BOOL.getTypeName()));
        conjunction.addOperand(comparison1);

        sql.addConjunction(conjunction);
    }

    private void clas1431957012480960FiltNav48(SQLSelect sql) {
        clas1431956094976663FiltNav15(sql);
    }

    // IIU Methods


    /*
     * Solves the query needed for PIUTipoFactura
     *
     * @param queryRequest Query request
     * @return List of viewmodels for TipoFactura using displayset DSTipoFactura that 
     * match the search criteria
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<com.keralty.aeconfiguraciones.viewmodel.tipofactura.ds.DSTipoFactura> piuPIUTipoFactura(QueryRequest queryRequest) {
        return solveTipoFacturaTypedQuery(queryRequest).stream()
                .map(com.keralty.aeconfiguraciones.viewmodel.tipofactura.ds.DSTipoFactura::new)
                .collect(Collectors.toList());
    }

    /*
     * Solves the COUNT query needed for PIUTipoFactura
     *
     * @param queryRequest Query request
     * @returns Count of instances for PIUTipoFactura
     */
    public int getCount4PIUTipoFactura(QueryRequest queryRequest) {
        return solveTipoFacturaTypedQuery(queryRequest).size();
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
            com.keralty.aeconfiguraciones.viewmodel.tipofactura.siu.CrearRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = tipoFacturaSrv.crearTxn(request);
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
            com.keralty.aeconfiguraciones.viewmodel.tipofactura.siu.CrearRequest request) throws ModelException {
        return tipoFacturaSrv.crearImplementation(request);
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
            com.keralty.aeconfiguraciones.viewmodel.tipofactura.siu.CrearRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        String tipoFacturacrearpatrCodigo = request.getTipoFacturacrearpatrCodigo();
        String tipoFacturacrearpatrDescripcion = request.getTipoFacturacrearpatrDescripcion();
        Boolean tipoFacturacrearpatrEsEmail = request.getTipoFacturacrearpatrEsEmail();
        Boolean tipoFacturacrearpatrEsCelular = request.getTipoFacturacrearpatrEsCelular();

        request.checkArguments();
        TipoFactura instance = new TipoFactura();
        instance.setTipoFacturaActivo(Boolean.TRUE);
        instance.setTipoFacturaCodigo(tipoFacturacrearpatrCodigo);
        instance.setTipoFacturaDescripcion(tipoFacturacrearpatrDescripcion);
        instance.setTipoFacturaEsEmail(tipoFacturacrearpatrEsEmail);
        instance.setTipoFacturaEsCelular(tipoFacturacrearpatrEsCelular);
        if (tipoFacturaSrv.getByOID(instance.getOid()) != null) {
            throw new InstanceExistsException(TipoFacturaConstants.CLASS_ALIAS);
        }

        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICTipoFactura());

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
            com.keralty.aeconfiguraciones.viewmodel.tipofactura.siu.EliminarRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = tipoFacturaSrv.eliminarTxn(request);
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
            com.keralty.aeconfiguraciones.viewmodel.tipofactura.siu.EliminarRequest request) throws ModelException {
        request.setTipoFacturaeliminarpthisTipoFacturaInstance(tipoFacturaSrv.getByOIDWithHV(new TipoFacturaOid().fromJsonString(request.getTipoFacturaeliminarpthisTipoFactura())));
        return tipoFacturaSrv.eliminarImplementation(request);
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
            com.keralty.aeconfiguraciones.viewmodel.tipofactura.siu.EliminarRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        TipoFactura tipoFacturaeliminarpthisTipoFactura = request.getTipoFacturaeliminarpthisTipoFacturaInstance();

        request.checkArguments();
        TipoFactura instance = tipoFacturaeliminarpthisTipoFactura;
        if(tipoFacturaeliminarpthisTipoFactura.getSolicitudes().size() > 0) {
            throw new MinCardinalityException(TipoFacturaConstants.CLASS_ALIAS, TipoFacturaConstants.ROLE_ALIAS_SOLICITUDES);
        }


        repository.delete(instance);
        
        response.setInstance(instance);
        response.setInstanceSuppInfo(instance.buildICTipoFactura());

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
            com.keralty.aeconfiguraciones.viewmodel.tipofactura.siu.ModificarRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = tipoFacturaSrv.modificarTxn(request);
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
            com.keralty.aeconfiguraciones.viewmodel.tipofactura.siu.ModificarRequest request) throws ModelException {
        request.setTipoFacturamodificarpthisTipoFacturaInstance(tipoFacturaSrv.getByOIDWithHV(new TipoFacturaOid().fromJsonString(request.getTipoFacturamodificarpthisTipoFactura())));
        return tipoFacturaSrv.modificarImplementation(request);
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
            com.keralty.aeconfiguraciones.viewmodel.tipofactura.siu.ModificarRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        TipoFactura tipoFacturamodificarpthisTipoFactura = request.getTipoFacturamodificarpthisTipoFacturaInstance();
        String tipoFacturamodificarpDescripcion = request.getTipoFacturamodificarpDescripcion();
        Boolean tipoFacturamodificarpEsEmail = request.getTipoFacturamodificarpEsEmail();
        Boolean tipoFacturamodificarpEsCelular = request.getTipoFacturamodificarpEsCelular();
        Boolean tipoFacturamodificarpActivo = request.getTipoFacturamodificarpActivo();

        request.checkArguments();
        TipoFactura instance = tipoFacturamodificarpthisTipoFactura;
        instance.setTipoFacturaDescripcion(tipoFacturamodificarpDescripcion);
        instance.setTipoFacturaEsEmail(tipoFacturamodificarpEsEmail);
        instance.setTipoFacturaEsCelular(tipoFacturamodificarpEsCelular);
        instance.setTipoFacturaActivo(tipoFacturamodificarpActivo);


        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICTipoFactura());

        return response;
    }

    // Service: InsTipoFactura

    /*
     * Entry point for service InsTipoFactura
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse insTipoFactura(
            com.keralty.aeconfiguraciones.viewmodel.tipofactura.siu.InsTipoFacturaRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = tipoFacturaSrv.insTipoFacturaTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service InsTipoFactura
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse insTipoFacturaTxn(
            com.keralty.aeconfiguraciones.viewmodel.tipofactura.siu.InsTipoFacturaRequest request) throws ModelException {
        request.setTipoFacturaInsTipoFacturapthisTipoFacturaInstance(tipoFacturaSrv.getByOIDWithHV(request.getTipoFacturaInsTipoFacturapthisTipoFactura()));
        request.setTipoFacturaInsTipoFacturapevcSolicitudInstance(solicitudSrv.getByOIDWithHV(request.getTipoFacturaInsTipoFacturapevcSolicitud()));
        return tipoFacturaSrv.insTipoFacturaImplementation(request, true);
    }

    /*
     * Implements service InsTipoFactura
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse insTipoFacturaImplementation(
            com.keralty.aeconfiguraciones.viewmodel.tipofactura.siu.InsTipoFacturaRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        TipoFactura tipoFacturaInsTipoFacturapthisTipoFactura = request.getTipoFacturaInsTipoFacturapthisTipoFacturaInstance();
        Solicitud tipoFacturaInsTipoFacturapevcSolicitud = request.getTipoFacturaInsTipoFacturapevcSolicitudInstance();

        request.checkArguments();
        TipoFactura instance = tipoFacturaInsTipoFacturapthisTipoFactura;

        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.solicitud.siu.InsTipoFacturaRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.solicitud.siu.InsTipoFacturaRequest();
            relatedRequest.setSolicitudInsTipoFacturapthisSolicitudInstance(tipoFacturaInsTipoFacturapevcSolicitud);
            relatedRequest.setSolicitudInsTipoFacturapevcTipoFacturaInstance(tipoFacturaInsTipoFacturapthisTipoFactura);
            solicitudSrv.insTipoFacturaImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICTipoFactura());

        return response;
    }

    // Service: DelTipoFactura

    /*
     * Entry point for service DelTipoFactura
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse delTipoFactura(
            com.keralty.aeconfiguraciones.viewmodel.tipofactura.siu.DelTipoFacturaRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = tipoFacturaSrv.delTipoFacturaTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service DelTipoFactura
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse delTipoFacturaTxn(
            com.keralty.aeconfiguraciones.viewmodel.tipofactura.siu.DelTipoFacturaRequest request) throws ModelException {
        request.setTipoFacturaDelTipoFacturapthisTipoFacturaInstance(tipoFacturaSrv.getByOIDWithHV(request.getTipoFacturaDelTipoFacturapthisTipoFactura()));
        request.setTipoFacturaDelTipoFacturapevcSolicitudInstance(solicitudSrv.getByOIDWithHV(request.getTipoFacturaDelTipoFacturapevcSolicitud()));
        return tipoFacturaSrv.delTipoFacturaImplementation(request, true);
    }

    /*
     * Implements service DelTipoFactura
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse delTipoFacturaImplementation(
            com.keralty.aeconfiguraciones.viewmodel.tipofactura.siu.DelTipoFacturaRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        TipoFactura tipoFacturaDelTipoFacturapthisTipoFactura = request.getTipoFacturaDelTipoFacturapthisTipoFacturaInstance();
        Solicitud tipoFacturaDelTipoFacturapevcSolicitud = request.getTipoFacturaDelTipoFacturapevcSolicitudInstance();

        request.checkArguments();
        TipoFactura instance = tipoFacturaDelTipoFacturapthisTipoFactura;

        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.solicitud.siu.DelTipoFacturaRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.solicitud.siu.DelTipoFacturaRequest();
            relatedRequest.setSolicitudDelTipoFacturapthisSolicitudInstance(tipoFacturaDelTipoFacturapevcSolicitud);
            relatedRequest.setSolicitudDelTipoFacturapevcTipoFacturaInstance(tipoFacturaDelTipoFacturapthisTipoFactura);
            solicitudSrv.delTipoFacturaImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICTipoFactura());

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
        select.getOrderByPart().addItem(Constants.normalizePath(TipoFacturaConstants.CLASS_NAME, TipoFacturaConstants.ATTR_FIELD_TIPOFACTURACODIGO, false), true);
    }
    
    @Override
    protected AbstractAppEntity buildRelatedInstance(String relatedInstanceJsonOid, String relatedInstancePathFirstItem, String remainingPath) {
        AbstractAppEntity relatedInstance = null;
        if (relatedInstancePathFirstItem.isEmpty()) {
            relatedInstance = buildTipoFacturaRelatedInstance(relatedInstanceJsonOid);
        }
        if (TipoFacturaConstants.ROLE_NAME_SOLICITUDES.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildTipoFacturaRelatedInstance4Solicitud(relatedInstanceJsonOid, remainingPath);
        }
        return relatedInstance;
    }
    
    private AbstractAppEntity buildTipoFacturaRelatedInstance(String relatedInstanceJsonOid) {
    	ObjectMapper mapper = new ObjectMapper();
    	try {
    		return new TipoFactura(mapper.readValue(relatedInstanceJsonOid, TipoFacturaOid.class));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            throw new SystemException(e.getMessage());
    	}
    }

    private AbstractAppEntity buildTipoFacturaRelatedInstance4Solicitud(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new Solicitud(mapper.readValue(relatedInstanceJsonOid, SolicitudOid.class)): Solicitud.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            throw new SystemException(e.getMessage());
    	}
    }

    @Override
    protected void applyFilterByOid(String jsonOid, SQLSelect sql) {
        try {
            TipoFacturaOid oid = new TipoFacturaOid().fromJsonString(jsonOid);
            SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
            comparison1.setOperator("=");
            comparison1.setFirstOperand(new SQLWhereOperandPathAttribute(TipoFacturaConstants.ATTR_FIELD_TIPOFACTURACODIGO, Constants.Type.STRING.getTypeName()));
            comparison1.setSecondOperand(new SQLWhereOperandLiteral(oid.getTipoFacturaCodigo(), Constants.Type.STRING.getTypeName()));
            sql.addComparison(comparison1);
        } catch (Exception e) {
            LoggerFactory.getLogger(TipoFacturaService.class).error(e.getMessage());
            throw new SystemException(e.getMessage());
        }
    }

}
