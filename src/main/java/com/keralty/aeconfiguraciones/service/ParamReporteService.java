package com.keralty.aeconfiguraciones.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.keralty.aeconfiguraciones.exception.ModelException;
import com.keralty.aeconfiguraciones.exception.SystemException;
import com.keralty.aeconfiguraciones.global.Constants;
import com.keralty.aeconfiguraciones.global.ParamReporteConstants;
import com.keralty.aeconfiguraciones.persistence.AbstractAppEntity;
import com.keralty.aeconfiguraciones.persistence.oid.ParamReporteOid;
import com.keralty.aeconfiguraciones.persistence.oid.ReportesProcAlmOid;
import com.keralty.aeconfiguraciones.persistence.ParamReporte;
import com.keralty.aeconfiguraciones.persistence.ReportesProcAlm;
import com.keralty.aeconfiguraciones.repository.ParamReporteJpaRepository;
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
public class ParamReporteService extends AbstractService {

    ParamReporteJpaRepository repository;

    ParamReporteService paramReporteSrv;

    ReportesProcAlmService reportesProcAlmSrv;

    @Inject
    public ParamReporteService(
            ParamReporteService paramReporteSrv,
            ReportesProcAlmService reportesProcAlmSrv,
            ParamReporteJpaRepository repository) {
        this.paramReporteSrv = paramReporteSrv;
        this.reportesProcAlmSrv = reportesProcAlmSrv;
        this.repository = repository;
    }

    public List<Map<String, Object>> solveQuery(QueryRequest request) {
        return solveGenericQuery(request, ParamReporteConstants.CLASS_NAME);
    }

    private List<ParamReporte> solveParamReporteTypedQuery(QueryRequest request) {
        return repository.solveQuery(processQueryRequest(request, ParamReporteConstants.CLASS_NAME), request.getPageSize(), request.getPageNumber());
    }
    
    private int solveParamReporteTypedCountQuery(QueryRequest request) {
        return repository.solveCountQuery(processCountQueryRequest(request, ParamReporteConstants.CLASS_NAME));
    }

    /*
     * Returns the population of class ParamReporte
     *
     * @return Population of class ParamReporte
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<ParamReporte> get() {
        List<ParamReporte> results = new java.util.ArrayList<>();
        repository.findAll().forEach(results::add);
        return results;
    }
    /*
     * Returns the population of class ParamReporte that matches certain search criteria
     *
     * @param queryRequest Search criteria
     *
     * @return Population of class ParamReporte matching the search criteria
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<ParamReporte> get(QueryRequest queryRequest) {
        return solveParamReporteTypedQuery(queryRequest);
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
            return new PopulationResponseViewModel(solveParamReporteTypedQuery(queryRequest).stream()
                    .map(instance -> buildDynamicObject(instance, queryRequest.getDisplaySetItems()))
                    .collect(Collectors.toList()), queryRequest.getPageSize() != 0 ? solveParamReporteTypedCountQuery(queryRequest) : 0);
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
        return new PopulationResponseViewModel(solveParamReporteTypedQuery(queryRequest).stream()
                .map(instance -> buildDynamicObject(instance, displaySetItems))
                .collect(Collectors.toList()));
    }

    /*
     * Returns an instance of ParamReporte by ID
     *
     * @param paramReporteidParamReporte Identification field paramReporteidParamReporte
     *
     * @return Instance of ParamReporte
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ParamReporte getById(Long paramReporteidParamReporte) {
        return getByOID(new ParamReporteOid(paramReporteidParamReporte));
    }
    
    /*
     * Returns an instance of ParamReporte by OID
     *
     * @param oid Object IDentifier
     *
     * @return Instance of ParamReporte
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ParamReporte getByOID(ParamReporteOid oid) {
        Optional<ParamReporte> instance = getInstanceByOid(oid);
        return instance.isPresent() ? instance.get() : null;
    }
    
    /*
     * Returns an instance of ParamReporte by OID applying horizontal visibility constraints
     *
     * @param oid Object IDentifier
     *
     * @return Instance of ParamReporte
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public ParamReporte getByOIDWithHV(ParamReporteOid oid) {
        Optional<ParamReporte> instance = getInstanceByOid(oid);
    	return instance.isPresent() && instance.get().checkHorizontalVisibility(getAgentInstance()) 
    		 ? instance.get() 
    		 : null;
    }
    
    private Optional<ParamReporte> getInstanceByOid(ParamReporteOid oid) {
        return oid != null && oid.getParamReporteidParamReporte() != null
               ? repository.findById(oid.getParamReporteidParamReporte())
               : Optional.empty();
    }

    /*
     * Returns an instance of ParamReporte by OID in JSON format
     *
     * @param jsonOid Object IDentifier in JSON format
     *
     * @return Instance of ParamReporte
     */
    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public ParamReporte getByJsonOid(String jsonOid) {
        ParamReporteOid oid = new ParamReporteOid();
        oid.fromJsonString(jsonOid);
        return getByOIDWithHV(oid);
    }

    /*
     * Returns the list of instances of ParamReporte related to a given instance through 
     * a given path
     *
     * @param relatedInstance Related instance
     *
     * @param relatedInstancePath Related instance path
     *
     * @return List of instances of ParamReporte
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<ParamReporte> getByRelated(AbstractAppEntity relatedInstance, String relatedInstancePath) {
        QueryRequest queryRequest = new QueryRequest(new FilterRequest(Constants.FILTER_BY_RELATED,
                Arrays.asList(new FilterVariableRequest(Constants.FILTER_BY_RELATED_FVAR_PATH, relatedInstancePath),
                        new FilterVariableRequest(Constants.FILTER_BY_RELATED_FVAR_OID, relatedInstance.getOid().toJson()))));
        return solveParamReporteTypedQuery(queryRequest);
    }

    /*
     * Returns the list of instances of ParamReporte related to a given instance through
     * a given path
     *
     * @param relatedInstance Related instance
     *
     * @param relatedInstancePath Related instance path
     *
     * @return List of instances of ParamReporte
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<ParamReporte> getInstancesByRelated(AbstractAppEntity relatedInstance, String relatedInstancePath) {
        return getByRelated(relatedInstance, relatedInstancePath);
    }

    /*
     * Returns a set of displayset items for an instance of ParamReporte
     *
     * @param oid Object IDentifier
     *
     * @param displaySetItems List of comma-separated displayset items
     *
     * @return Set of displayset items for an instance of ParamReporte
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public InstanceResponseViewModel getParamReporteDynamic(ParamReporteOid oid, String displaySetItems) {
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
    public String getSuppInfo(ParamReporteOid oid, String dsName) {
        ParamReporte instance = getByOIDWithHV(oid);
        return (instance != null ? getSuppInfo(instance, dsName): "");
    }

    private String getSuppInfo(ParamReporte instance, String dsName) {
        if (instance == null || instance.isNull()) return "";
        return dsName.isEmpty() ? instance.getOid().toJson() : "";
    }




    // IIU Methods


    /*
     * Solves the query needed for PIUDParametros
     *
     * @param queryRequest Query request
     * @return List of viewmodels for ParamReporte using displayset DSParamReporte that 
     * match the search criteria
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<com.keralty.aeconfiguraciones.viewmodel.paramreporte.ds.DSParamReporte> piuPIUDParametros(QueryRequest queryRequest) {
        return solveParamReporteTypedQuery(queryRequest).stream()
                .map(com.keralty.aeconfiguraciones.viewmodel.paramreporte.ds.DSParamReporte::new)
                .collect(Collectors.toList());
    }

    /*
     * Solves the COUNT query needed for PIUDParametros
     *
     * @param queryRequest Query request
     * @returns Count of instances for PIUDParametros
     */
    public int getCount4PIUDParametros(QueryRequest queryRequest) {
        return solveParamReporteTypedQuery(queryRequest).size();
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
            com.keralty.aeconfiguraciones.viewmodel.paramreporte.siu.CrearRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = paramReporteSrv.crearTxn(request);
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
            com.keralty.aeconfiguraciones.viewmodel.paramreporte.siu.CrearRequest request) throws ModelException {
        request.setParamReportecrearpagrReporteInstance(reportesProcAlmSrv.getByOIDWithHV(request.getParamReportecrearpagrReporte()));
        return paramReporteSrv.crearImplementation(request);
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
            com.keralty.aeconfiguraciones.viewmodel.paramreporte.siu.CrearRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        ReportesProcAlm paramReportecrearpagrReporte = request.getParamReportecrearpagrReporteInstance();
        Boolean paramReportecrearpatrEsNumerico = request.getParamReportecrearpatrEsNumerico();
        Long paramReportecrearpatrOrden = request.getParamReportecrearpatrOrden();
        String paramReportecrearpatrDescripcion = request.getParamReportecrearpatrDescripcion();
        String paramReportecrearpatrTitulo = request.getParamReportecrearpatrTitulo();

        request.checkArguments();
        ParamReporte instance = new ParamReporte();
        instance.add2Reporte(paramReportecrearpagrReporte);

        instance.setParamReporteidParamReporte(Long.valueOf(0));
        instance.setParamReporteEsNumerico(paramReportecrearpatrEsNumerico);
        instance.setParamReporteOrden(paramReportecrearpatrOrden);
        instance.setParamReporteDescripcion(paramReportecrearpatrDescripcion);
        instance.setParamReporteTitulo(paramReportecrearpatrTitulo);

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
            com.keralty.aeconfiguraciones.viewmodel.paramreporte.siu.EliminarRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = paramReporteSrv.eliminarTxn(request);
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
            com.keralty.aeconfiguraciones.viewmodel.paramreporte.siu.EliminarRequest request) throws ModelException {
        request.setParamReporteeliminarpthisParamReporteInstance(paramReporteSrv.getByOIDWithHV(request.getParamReporteeliminarpthisParamReporte()));
        return paramReporteSrv.eliminarImplementation(request);
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
            com.keralty.aeconfiguraciones.viewmodel.paramreporte.siu.EliminarRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        ParamReporte paramReporteeliminarpthisParamReporte = request.getParamReporteeliminarpthisParamReporteInstance();

        request.checkArguments();
        ParamReporte instance = paramReporteeliminarpthisParamReporte;


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
            com.keralty.aeconfiguraciones.viewmodel.paramreporte.siu.ModificarRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = paramReporteSrv.modificarTxn(request);
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
            com.keralty.aeconfiguraciones.viewmodel.paramreporte.siu.ModificarRequest request) throws ModelException {
        request.setParamReportemodificarpthisParamReporteInstance(paramReporteSrv.getByOIDWithHV(request.getParamReportemodificarpthisParamReporte()));
        return paramReporteSrv.modificarImplementation(request);
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
            com.keralty.aeconfiguraciones.viewmodel.paramreporte.siu.ModificarRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        ParamReporte paramReportemodificarpthisParamReporte = request.getParamReportemodificarpthisParamReporteInstance();
        Boolean paramReportemodificarpEsNumerico = request.getParamReportemodificarpEsNumerico();
        String paramReportemodificarpDescripcion = request.getParamReportemodificarpDescripcion();
        String paramReportemodificarpTitulo = request.getParamReportemodificarpTitulo();

        request.checkArguments();
        ParamReporte instance = paramReportemodificarpthisParamReporte;
        instance.setParamReporteEsNumerico(paramReportemodificarpEsNumerico);
        instance.setParamReporteDescripcion(paramReportemodificarpDescripcion);
        instance.setParamReporteTitulo(paramReportemodificarpTitulo);


        response.setInstance(repository.save(instance));

        return response;
    }

    // Service: cambiarOrden

    /*
     * Entry point for service cambiarOrden
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse cambiarOrden(
            com.keralty.aeconfiguraciones.viewmodel.paramreporte.siu.CambiarOrdenRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = paramReporteSrv.cambiarOrdenTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service cambiarOrden
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse cambiarOrdenTxn(
            com.keralty.aeconfiguraciones.viewmodel.paramreporte.siu.CambiarOrdenRequest request) throws ModelException, SystemException {
        request.setParamReportecambiarOrdenpthisParamReporteInstance(paramReporteSrv.getByOIDWithHV(request.getParamReportecambiarOrdenpthisParamReporte()));
        return paramReporteSrv.cambiarOrdenImplementation(request);
    }

    /*
     * Implements service cambiarOrden
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse cambiarOrdenImplementation(
            com.keralty.aeconfiguraciones.viewmodel.paramreporte.siu.CambiarOrdenRequest request) throws ModelException, SystemException {
        ServiceResponse response = new ServiceResponse();
        ParamReporte paramReportecambiarOrdenpthisParamReporte = request.getParamReportecambiarOrdenpthisParamReporteInstance();
        Long paramReportecambiarOrdennuevoOrden = request.getParamReportecambiarOrdennuevoOrden();

        request.checkArguments();
        ParamReporte instance = paramReportecambiarOrdenpthisParamReporte;
        if (paramReportecambiarOrdennuevoOrden > repository.assocOperator000(paramReportecambiarOrdenpthisParamReporte)) {
            instance.setParamReporteOrden(repository.assocOperator001(paramReportecambiarOrdenpthisParamReporte));
        } else if (paramReportecambiarOrdennuevoOrden <= Long.valueOf(0)) {
            instance.setParamReporteOrden(Long.valueOf(1));
        } else {
            instance.setParamReporteOrden(paramReportecambiarOrdennuevoOrden);
        }


        response.setInstance(repository.save(instance));

        return response;
    }

    // Service: TNEW

    /*
     * Entry point for service TNEW
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse tNEW(
            com.keralty.aeconfiguraciones.viewmodel.paramreporte.siu.TNEWRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = paramReporteSrv.tNEWTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service TNEW
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse tNEWTxn(
            com.keralty.aeconfiguraciones.viewmodel.paramreporte.siu.TNEWRequest request) throws ModelException, SystemException {
        request.setParamReporteTNEWptpagrReporteInstance(reportesProcAlmSrv.getByOIDWithHV(request.getParamReporteTNEWptpagrReporte()));
        return paramReporteSrv.tNEWImplementation(request);
    }

    /*
     * Implements service TNEW
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse tNEWImplementation(
            com.keralty.aeconfiguraciones.viewmodel.paramreporte.siu.TNEWRequest request) throws ModelException, SystemException {
        ServiceResponse response = new ServiceResponse();
        ReportesProcAlm paramReporteTNEWptpagrReporte = request.getParamReporteTNEWptpagrReporteInstance();
        Boolean paramReporteTNEWptpatrEsNumerico = request.getParamReporteTNEWptpatrEsNumerico();
        String paramReporteTNEWptpatrDescripcion = request.getParamReporteTNEWptpatrDescripcion();
        String paramReporteTNEWptpatrTitulo = request.getParamReporteTNEWptpatrTitulo();

        request.checkArguments();
        ParamReporte instance;

        com.keralty.aeconfiguraciones.viewmodel.paramreporte.siu.CrearRequest step1Request = new com.keralty.aeconfiguraciones.viewmodel.paramreporte.siu.CrearRequest();
        step1Request.setParamReportecrearpagrReporteInstance(paramReporteTNEWptpagrReporte);
        step1Request.setParamReportecrearpatrEsNumerico(paramReporteTNEWptpatrEsNumerico);
        step1Request.setParamReportecrearpatrOrden(repository.assocOperator003(paramReporteTNEWptpagrReporte) + Long.valueOf(1));
        step1Request.setParamReportecrearpatrDescripcion(paramReporteTNEWptpatrDescripcion);
        step1Request.setParamReportecrearpatrTitulo(paramReporteTNEWptpatrTitulo);
        instance = (ParamReporte) paramReporteSrv.crearImplementation(step1Request).getInstance();

        response.setInstance(getByOID(instance.getOid()));

        return response;
    }

    // Service: TDELETE

    /*
     * Entry point for service TDELETE
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse tDELETE(
            com.keralty.aeconfiguraciones.viewmodel.paramreporte.siu.TDELETERequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = paramReporteSrv.tDELETETxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service TDELETE
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse tDELETETxn(
            com.keralty.aeconfiguraciones.viewmodel.paramreporte.siu.TDELETERequest request) throws ModelException, SystemException {
        request.setParamReporteTDELETEpthisParamReporteInstance(paramReporteSrv.getByOIDWithHV(request.getParamReporteTDELETEpthisParamReporte()));
        return paramReporteSrv.tDELETEImplementation(request);
    }

    /*
     * Implements service TDELETE
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse tDELETEImplementation(
            com.keralty.aeconfiguraciones.viewmodel.paramreporte.siu.TDELETERequest request) throws ModelException, SystemException {
        ServiceResponse response = new ServiceResponse();
        ParamReporte paramReporteTDELETEpthisParamReporte = request.getParamReporteTDELETEpthisParamReporteInstance();

        request.checkArguments();
        ParamReporte instance = paramReporteTDELETEpthisParamReporte;

        for(ParamReporte step1Entity : repository.assocOperator004(paramReporteTDELETEpthisParamReporte)) {
            com.keralty.aeconfiguraciones.viewmodel.paramreporte.siu.CambiarOrdenRequest step1Request = new com.keralty.aeconfiguraciones.viewmodel.paramreporte.siu.CambiarOrdenRequest();
            step1Request.setParamReportecambiarOrdenpthisParamReporteInstance(step1Entity);
            step1Request.setParamReportecambiarOrdennuevoOrden(step1Entity.getParamReporteOrden() - Long.valueOf(1));
            paramReporteSrv.cambiarOrdenImplementation(step1Request);
        }

        com.keralty.aeconfiguraciones.viewmodel.paramreporte.siu.EliminarRequest step2Request = new com.keralty.aeconfiguraciones.viewmodel.paramreporte.siu.EliminarRequest();
        step2Request.setParamReporteeliminarpthisParamReporteInstance(paramReporteTDELETEpthisParamReporte);
        paramReporteSrv.eliminarImplementation(step2Request);

        response.setInstance(getByOID(instance.getOid()));

        return response;
    }

    // Service: TSUBIR

    /*
     * Entry point for service TSUBIR
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse tSUBIR(
            com.keralty.aeconfiguraciones.viewmodel.paramreporte.siu.TSUBIRRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = paramReporteSrv.tSUBIRTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service TSUBIR
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse tSUBIRTxn(
            com.keralty.aeconfiguraciones.viewmodel.paramreporte.siu.TSUBIRRequest request) throws ModelException, SystemException {
        request.setParamReporteTSUBIRpthisParamReporteInstance(paramReporteSrv.getByOIDWithHV(request.getParamReporteTSUBIRpthisParamReporte()));
        return paramReporteSrv.tSUBIRImplementation(request);
    }

    /*
     * Implements service TSUBIR
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse tSUBIRImplementation(
            com.keralty.aeconfiguraciones.viewmodel.paramreporte.siu.TSUBIRRequest request) throws ModelException, SystemException {
        ServiceResponse response = new ServiceResponse();
        ParamReporte paramReporteTSUBIRpthisParamReporte = request.getParamReporteTSUBIRpthisParamReporteInstance();

        request.checkArguments();
        ParamReporte instance = paramReporteTSUBIRpthisParamReporte;

        com.keralty.aeconfiguraciones.viewmodel.reportesprocalm.siu.TCAMBIARORDENPARAMSRequest step1Request = new com.keralty.aeconfiguraciones.viewmodel.reportesprocalm.siu.TCAMBIARORDENPARAMSRequest();
        step1Request.setReportesProcAlmTCAMBIARORDENPARAMSpthisReportesProcAlmInstance(paramReporteTSUBIRpthisParamReporte.getReporte());
        step1Request.setReportesProcAlmTCAMBIARORDENPARAMSptOrdenActual(paramReporteTSUBIRpthisParamReporte.getParamReporteOrden() - Long.valueOf(1));
        step1Request.setReportesProcAlmTCAMBIARORDENPARAMSptNuevoOrden(paramReporteTSUBIRpthisParamReporte.getParamReporteOrden());
        reportesProcAlmSrv.tCAMBIARORDENPARAMSImplementation(step1Request);

        com.keralty.aeconfiguraciones.viewmodel.paramreporte.siu.CambiarOrdenRequest step2Request = new com.keralty.aeconfiguraciones.viewmodel.paramreporte.siu.CambiarOrdenRequest();
        step2Request.setParamReportecambiarOrdenpthisParamReporteInstance(paramReporteTSUBIRpthisParamReporte);
        step2Request.setParamReportecambiarOrdennuevoOrden(paramReporteTSUBIRpthisParamReporte.getParamReporteOrden() - Long.valueOf(1));
        paramReporteSrv.cambiarOrdenImplementation(step2Request);

        response.setInstance(getByOID(instance.getOid()));

        return response;
    }

    // Service: TBAJAR

    /*
     * Entry point for service TBAJAR
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse tBAJAR(
            com.keralty.aeconfiguraciones.viewmodel.paramreporte.siu.TBAJARRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = paramReporteSrv.tBAJARTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service TBAJAR
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse tBAJARTxn(
            com.keralty.aeconfiguraciones.viewmodel.paramreporte.siu.TBAJARRequest request) throws ModelException, SystemException {
        request.setParamReporteTBAJARpthisParamReporteInstance(paramReporteSrv.getByOIDWithHV(request.getParamReporteTBAJARpthisParamReporte()));
        return paramReporteSrv.tBAJARImplementation(request);
    }

    /*
     * Implements service TBAJAR
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse tBAJARImplementation(
            com.keralty.aeconfiguraciones.viewmodel.paramreporte.siu.TBAJARRequest request) throws ModelException, SystemException {
        ServiceResponse response = new ServiceResponse();
        ParamReporte paramReporteTBAJARpthisParamReporte = request.getParamReporteTBAJARpthisParamReporteInstance();

        request.checkArguments();
        ParamReporte instance = paramReporteTBAJARpthisParamReporte;

        com.keralty.aeconfiguraciones.viewmodel.reportesprocalm.siu.TCAMBIARORDENPARAMSRequest step1Request = new com.keralty.aeconfiguraciones.viewmodel.reportesprocalm.siu.TCAMBIARORDENPARAMSRequest();
        step1Request.setReportesProcAlmTCAMBIARORDENPARAMSpthisReportesProcAlmInstance(paramReporteTBAJARpthisParamReporte.getReporte());
        step1Request.setReportesProcAlmTCAMBIARORDENPARAMSptOrdenActual(paramReporteTBAJARpthisParamReporte.getParamReporteOrden() + Long.valueOf(1));
        step1Request.setReportesProcAlmTCAMBIARORDENPARAMSptNuevoOrden(paramReporteTBAJARpthisParamReporte.getParamReporteOrden());
        reportesProcAlmSrv.tCAMBIARORDENPARAMSImplementation(step1Request);

        com.keralty.aeconfiguraciones.viewmodel.paramreporte.siu.CambiarOrdenRequest step2Request = new com.keralty.aeconfiguraciones.viewmodel.paramreporte.siu.CambiarOrdenRequest();
        step2Request.setParamReportecambiarOrdenpthisParamReporteInstance(paramReporteTBAJARpthisParamReporte);
        step2Request.setParamReportecambiarOrdennuevoOrden(paramReporteTBAJARpthisParamReporte.getParamReporteOrden() + Long.valueOf(1));
        paramReporteSrv.cambiarOrdenImplementation(step2Request);

        response.setInstance(getByOID(instance.getOid()));

        return response;
    }

    @Override
    protected void applyOrderCriteria(QueryRequest queryRequest, SQLSelect select) {
        applyOCOCOrden(queryRequest, select);
        applyDSItemSort(queryRequest, select);
        applyDefaultOrderCriteria(queryRequest, select);
    }

    private void applyDefaultOrderCriteria(QueryRequest queryRequest, SQLSelect select) {
        if (queryRequest.getOrderCriteriaName() != null && !queryRequest.getOrderCriteriaName().isBlank())
            return;
        if (queryRequest.getDsItemSort() != null && !queryRequest.getDsItemSort().isBlank())
            return;
        select.getOrderByPart().addItem(Constants.normalizePath(ParamReporteConstants.CLASS_NAME, ParamReporteConstants.ATTR_FIELD_PARAMREPORTEIDPARAMREPORTE, false), true);
    }


    private void applyOCOCOrden(QueryRequest queryRequest, SQLSelect select) {
        if ("OC_Orden".equalsIgnoreCase(queryRequest.getOrderCriteriaName())) {
            select.getOrderByPart().addItem(Constants.normalizePath(ParamReporteConstants.CLASS_NAME, ParamReporteConstants.ATTR_NAME_PARAMREPORTEORDEN, false), true);
        }
    }
    
    @Override
    protected AbstractAppEntity buildRelatedInstance(String relatedInstanceJsonOid, String relatedInstancePathFirstItem, String remainingPath) {
        AbstractAppEntity relatedInstance = null;
        if (relatedInstancePathFirstItem.isEmpty()) {
            relatedInstance = buildParamReporteRelatedInstance(relatedInstanceJsonOid);
        }
        if (ParamReporteConstants.ROLE_NAME_REPORTE.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildParamReporteRelatedInstance4ReportesProcAlm(relatedInstanceJsonOid, remainingPath);
        }
        return relatedInstance;
    }
    
    private AbstractAppEntity buildParamReporteRelatedInstance(String relatedInstanceJsonOid) {
    	ObjectMapper mapper = new ObjectMapper();
    	try {
    		return new ParamReporte(mapper.readValue(relatedInstanceJsonOid, ParamReporteOid.class));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    private AbstractAppEntity buildParamReporteRelatedInstance4ReportesProcAlm(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new ReportesProcAlm(mapper.readValue(relatedInstanceJsonOid, ReportesProcAlmOid.class)): ReportesProcAlm.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    @Override
    protected void applyFilterByOid(String jsonOid, SQLSelect sql) {
        try {
            ParamReporteOid oid = new ParamReporteOid().fromJsonString(jsonOid);
            SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
            comparison1.setOperator("=");
            comparison1.setFirstOperand(new SQLWhereOperandPathAttribute(ParamReporteConstants.ATTR_FIELD_PARAMREPORTEIDPARAMREPORTE, Constants.Type.AUTONUMERIC.getTypeName()));
            comparison1.setSecondOperand(new SQLWhereOperandLiteral(oid.getParamReporteidParamReporte(), Constants.Type.AUTONUMERIC.getTypeName()));
            sql.addComparison(comparison1);
        } catch (Exception e) {
            LoggerFactory.getLogger(ParamReporteService.class).error(e.getMessage());
        }
    }

}
