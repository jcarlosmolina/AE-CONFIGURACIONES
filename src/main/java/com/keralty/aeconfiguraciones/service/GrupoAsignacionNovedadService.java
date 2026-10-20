package com.keralty.aeconfiguraciones.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.keralty.aeconfiguraciones.exception.ModelException;
import com.keralty.aeconfiguraciones.exception.PreconditionException;
import com.keralty.aeconfiguraciones.exception.SystemException;
import com.keralty.aeconfiguraciones.global.Constants;
import com.keralty.aeconfiguraciones.global.GrupoAsignacionNovedadConstants;
import com.keralty.aeconfiguraciones.persistence.AbstractAppEntity;
import com.keralty.aeconfiguraciones.persistence.Causal;
import com.keralty.aeconfiguraciones.persistence.Dominio;
import com.keralty.aeconfiguraciones.persistence.GrupoAsignacion;
import com.keralty.aeconfiguraciones.persistence.GrupoAsignacionNovedad;
import com.keralty.aeconfiguraciones.persistence.Novedad;
import com.keralty.aeconfiguraciones.persistence.oid.CausalOid;
import com.keralty.aeconfiguraciones.persistence.oid.DominioOid;
import com.keralty.aeconfiguraciones.persistence.oid.GrupoAsignacionNovedadOid;
import com.keralty.aeconfiguraciones.persistence.oid.GrupoAsignacionOid;
import com.keralty.aeconfiguraciones.persistence.oid.NovedadOid;
import com.keralty.aeconfiguraciones.repository.GrupoAsignacionNovedadJpaRepository;
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
public class GrupoAsignacionNovedadService extends AbstractService {

    GrupoAsignacionNovedadJpaRepository repository;

    GrupoAsignacionNovedadService grupoAsignacionNovedadSrv;

    CausalService causalSrv;

    GrupoAsignacionService grupoAsignacionSrv;

    DominioService dominioSrv;

    NovedadService novedadSrv;

    @Inject
    public GrupoAsignacionNovedadService(
            GrupoAsignacionNovedadService grupoAsignacionNovedadSrv,
            CausalService causalSrv,
            GrupoAsignacionService grupoAsignacionSrv,
            DominioService dominioSrv,
            NovedadService novedadSrv,
            GrupoAsignacionNovedadJpaRepository repository) {
        this.grupoAsignacionNovedadSrv = grupoAsignacionNovedadSrv;
        this.causalSrv = causalSrv;
        this.grupoAsignacionSrv = grupoAsignacionSrv;
        this.dominioSrv = dominioSrv;
        this.novedadSrv = novedadSrv;
        this.repository = repository;
    }

    public List<Map<String, Object>> solveQuery(QueryRequest request) {
        return solveGenericQuery(request, GrupoAsignacionNovedadConstants.CLASS_NAME);
    }

    private List<GrupoAsignacionNovedad> solveGrupoAsignacionNovedadTypedQuery(QueryRequest request) {
        return repository.solveQuery(processQueryRequest(request, GrupoAsignacionNovedadConstants.CLASS_NAME), request.getPageSize(), request.getPageNumber());
    }
    
    private int solveGrupoAsignacionNovedadTypedCountQuery(QueryRequest request) {
        return repository.solveCountQuery(processCountQueryRequest(request, GrupoAsignacionNovedadConstants.CLASS_NAME));
    }

    /*
     * Returns the population of class GrupoAsignacionNovedad
     *
     * @return Population of class GrupoAsignacionNovedad
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<GrupoAsignacionNovedad> get() {
        List<GrupoAsignacionNovedad> results = new java.util.ArrayList<>();
        repository.findAll().forEach(results::add);
        return results;
    }
    /*
     * Returns the population of class GrupoAsignacionNovedad that matches certain search criteria
     *
     * @param queryRequest Search criteria
     *
     * @return Population of class GrupoAsignacionNovedad matching the search criteria
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<GrupoAsignacionNovedad> get(QueryRequest queryRequest) {
        return solveGrupoAsignacionNovedadTypedQuery(queryRequest);
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
            return new PopulationResponseViewModel(solveGrupoAsignacionNovedadTypedQuery(queryRequest).stream()
                    .map(instance -> buildDynamicObject(instance, queryRequest.getDisplaySetItems()))
                    .collect(Collectors.toList()), queryRequest.getPageSize() != 0 ? solveGrupoAsignacionNovedadTypedCountQuery(queryRequest) : 0);
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
        return new PopulationResponseViewModel(solveGrupoAsignacionNovedadTypedQuery(queryRequest).stream()
                .map(instance -> buildDynamicObject(instance, displaySetItems))
                .collect(Collectors.toList()));
    }

    /*
     * Returns an instance of GrupoAsignacionNovedad by ID
     *
     * @param grupoAsignacionNovedadId Identification field grupoAsignacionNovedadId
     *
     * @return Instance of GrupoAsignacionNovedad
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public GrupoAsignacionNovedad getById(Long grupoAsignacionNovedadId) {
        return getByOID(new GrupoAsignacionNovedadOid(grupoAsignacionNovedadId));
    }
    
    /*
     * Returns an instance of GrupoAsignacionNovedad by OID
     *
     * @param oid Object IDentifier
     *
     * @return Instance of GrupoAsignacionNovedad
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public GrupoAsignacionNovedad getByOID(GrupoAsignacionNovedadOid oid) {
        Optional<GrupoAsignacionNovedad> instance = getInstanceByOid(oid);
        return instance.isPresent() ? instance.get() : null;
    }
    
    /*
     * Returns an instance of GrupoAsignacionNovedad by OID applying horizontal visibility constraints
     *
     * @param oid Object IDentifier
     *
     * @return Instance of GrupoAsignacionNovedad
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public GrupoAsignacionNovedad getByOIDWithHV(GrupoAsignacionNovedadOid oid) {
        Optional<GrupoAsignacionNovedad> instance = getInstanceByOid(oid);
    	return instance.isPresent() && instance.get().checkHorizontalVisibility(getAgentInstance()) 
    		 ? instance.get() 
    		 : null;
    }
    
    private Optional<GrupoAsignacionNovedad> getInstanceByOid(GrupoAsignacionNovedadOid oid) {
        return oid != null && oid.getGrupoAsignacionNovedadId() != null
               ? repository.findById(oid.getGrupoAsignacionNovedadId())
               : Optional.empty();
    }

    /*
     * Returns an instance of GrupoAsignacionNovedad by OID in JSON format
     *
     * @param jsonOid Object IDentifier in JSON format
     *
     * @return Instance of GrupoAsignacionNovedad
     */
    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public GrupoAsignacionNovedad getByJsonOid(String jsonOid) {
        GrupoAsignacionNovedadOid oid = new GrupoAsignacionNovedadOid();
        oid.fromJsonString(jsonOid);
        return getByOIDWithHV(oid);
    }

    /*
     * Returns the list of instances of GrupoAsignacionNovedad related to a given instance through 
     * a given path
     *
     * @param relatedInstance Related instance
     *
     * @param relatedInstancePath Related instance path
     *
     * @return List of instances of GrupoAsignacionNovedad
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<GrupoAsignacionNovedad> getByRelated(AbstractAppEntity relatedInstance, String relatedInstancePath) {
        QueryRequest queryRequest = new QueryRequest(new FilterRequest(Constants.FILTER_BY_RELATED,
                Arrays.asList(new FilterVariableRequest(Constants.FILTER_BY_RELATED_FVAR_PATH, relatedInstancePath),
                        new FilterVariableRequest(Constants.FILTER_BY_RELATED_FVAR_OID, relatedInstance.getOid().toJson()))));
        return solveGrupoAsignacionNovedadTypedQuery(queryRequest);
    }

    /*
     * Returns the list of instances of GrupoAsignacionNovedad related to a given instance through
     * a given path
     *
     * @param relatedInstance Related instance
     *
     * @param relatedInstancePath Related instance path
     *
     * @return List of instances of GrupoAsignacionNovedad
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<GrupoAsignacionNovedad> getInstancesByRelated(AbstractAppEntity relatedInstance, String relatedInstancePath) {
        return getByRelated(relatedInstance, relatedInstancePath);
    }

    /*
     * Returns a set of displayset items for an instance of GrupoAsignacionNovedad
     *
     * @param oid Object IDentifier
     *
     * @param displaySetItems List of comma-separated displayset items
     *
     * @return Set of displayset items for an instance of GrupoAsignacionNovedad
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public InstanceResponseViewModel getGrupoAsignacionNovedadDynamic(GrupoAsignacionNovedadOid oid, String displaySetItems) {
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
    public String getSuppInfo(GrupoAsignacionNovedadOid oid, String dsName) {
        GrupoAsignacionNovedad instance = getByOIDWithHV(oid);
        return (instance != null ? getSuppInfo(instance, dsName): "");
    }

    private String getSuppInfo(GrupoAsignacionNovedad instance, String dsName) {
        if (instance == null || instance.isNull()) return "";
        return dsName.isEmpty() ? instance.getOid().toJson() : "";
    }




    // IIU Methods


    /*
     * Solves the query needed for PIUGrpAsigNovedadDDominio
     *
     * @param queryRequest Query request
     * @return List of viewmodels for GrupoAsignacionNovedad using displayset DSGrupoAsignacionNovedad that 
     * match the search criteria
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<com.keralty.aeconfiguraciones.viewmodel.grupoasignacionnovedad.ds.DSGrupoAsignacionNovedad> piuPIUGrpAsigNovedadDDominio(QueryRequest queryRequest) {
        return solveGrupoAsignacionNovedadTypedQuery(queryRequest).stream()
                .map(com.keralty.aeconfiguraciones.viewmodel.grupoasignacionnovedad.ds.DSGrupoAsignacionNovedad::new)
                .collect(Collectors.toList());
    }

    /*
     * Solves the COUNT query needed for PIUGrpAsigNovedadDDominio
     *
     * @param queryRequest Query request
     * @returns Count of instances for PIUGrpAsigNovedadDDominio
     */
    public int getCount4PIUGrpAsigNovedadDDominio(QueryRequest queryRequest) {
        return solveGrupoAsignacionNovedadTypedQuery(queryRequest).size();
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
            com.keralty.aeconfiguraciones.viewmodel.grupoasignacionnovedad.siu.CrearRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = grupoAsignacionNovedadSrv.crearTxn(request);
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
            com.keralty.aeconfiguraciones.viewmodel.grupoasignacionnovedad.siu.CrearRequest request) throws ModelException {
        request.setGrupoAsignacionNovedadcrearpagrCausalInstance(causalSrv.getByOIDWithHV(request.getGrupoAsignacionNovedadcrearpagrCausal()));
        request.setGrupoAsignacionNovedadcrearpagrGrupoUsuariosInstance(grupoAsignacionSrv.getByOIDWithHV(request.getGrupoAsignacionNovedadcrearpagrGrupoUsuarios()));
        request.setGrupoAsignacionNovedadcrearpagrDominioInstance(dominioSrv.getByOIDWithHV(request.getGrupoAsignacionNovedadcrearpagrDominio()));
        return grupoAsignacionNovedadSrv.crearImplementation(request);
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
            com.keralty.aeconfiguraciones.viewmodel.grupoasignacionnovedad.siu.CrearRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Causal grupoAsignacionNovedadcrearpagrCausal = request.getGrupoAsignacionNovedadcrearpagrCausalInstance();
        GrupoAsignacion grupoAsignacionNovedadcrearpagrGrupoUsuarios = request.getGrupoAsignacionNovedadcrearpagrGrupoUsuariosInstance();
        Dominio grupoAsignacionNovedadcrearpagrDominio = request.getGrupoAsignacionNovedadcrearpagrDominioInstance();

        request.checkArguments();
        GrupoAsignacionNovedad instance = new GrupoAsignacionNovedad();
        instance.add2Causal(grupoAsignacionNovedadcrearpagrCausal);

        if (grupoAsignacionNovedadcrearpagrGrupoUsuarios != null && !grupoAsignacionNovedadcrearpagrGrupoUsuarios.isNull()) {
            instance.add2GrupoUsuarios(grupoAsignacionNovedadcrearpagrGrupoUsuarios);
        }

        instance.add2Dominio(grupoAsignacionNovedadcrearpagrDominio);

        instance.setGrupoAsignacionNovedadId(Long.valueOf(0));

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
            com.keralty.aeconfiguraciones.viewmodel.grupoasignacionnovedad.siu.EliminarRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = grupoAsignacionNovedadSrv.eliminarTxn(request);
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
            com.keralty.aeconfiguraciones.viewmodel.grupoasignacionnovedad.siu.EliminarRequest request) throws ModelException {
        request.setGrupoAsignacionNovedadeliminarpthisGrupoAsignacionNovedadInstance(grupoAsignacionNovedadSrv.getByOIDWithHV(request.getGrupoAsignacionNovedadeliminarpthisGrupoAsignacionNovedad()));
        return grupoAsignacionNovedadSrv.eliminarImplementation(request);
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
            com.keralty.aeconfiguraciones.viewmodel.grupoasignacionnovedad.siu.EliminarRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        GrupoAsignacionNovedad grupoAsignacionNovedadeliminarpthisGrupoAsignacionNovedad = request.getGrupoAsignacionNovedadeliminarpthisGrupoAsignacionNovedadInstance();

        request.checkArguments();
        GrupoAsignacionNovedad instance = grupoAsignacionNovedadeliminarpthisGrupoAsignacionNovedad;


        repository.delete(instance);
        
        response.setInstance(instance);

        return response;
    }

    // Service: InsGrupoUsuarios

    /*
     * Entry point for service InsGrupoUsuarios
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse insGrupoUsuarios(
            com.keralty.aeconfiguraciones.viewmodel.grupoasignacionnovedad.siu.InsGrupoUsuariosRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = grupoAsignacionNovedadSrv.insGrupoUsuariosTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service InsGrupoUsuarios
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse insGrupoUsuariosTxn(
            com.keralty.aeconfiguraciones.viewmodel.grupoasignacionnovedad.siu.InsGrupoUsuariosRequest request) throws ModelException {
        request.setGrupoAsignacionNovedadInsGrupoUsuariospthisGrupoAsignacionNovedadInstance(grupoAsignacionNovedadSrv.getByOIDWithHV(request.getGrupoAsignacionNovedadInsGrupoUsuariospthisGrupoAsignacionNovedad()));
        request.setGrupoAsignacionNovedadInsGrupoUsuariospevcGrupoAsignacionInstance(grupoAsignacionSrv.getByOIDWithHV(request.getGrupoAsignacionNovedadInsGrupoUsuariospevcGrupoAsignacion()));
        return grupoAsignacionNovedadSrv.insGrupoUsuariosImplementation(request, true);
    }

    /*
     * Implements service InsGrupoUsuarios
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse insGrupoUsuariosImplementation(
            com.keralty.aeconfiguraciones.viewmodel.grupoasignacionnovedad.siu.InsGrupoUsuariosRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        GrupoAsignacionNovedad grupoAsignacionNovedadInsGrupoUsuariospthisGrupoAsignacionNovedad = request.getGrupoAsignacionNovedadInsGrupoUsuariospthisGrupoAsignacionNovedadInstance();
        GrupoAsignacion grupoAsignacionNovedadInsGrupoUsuariospevcGrupoAsignacion = request.getGrupoAsignacionNovedadInsGrupoUsuariospevcGrupoAsignacionInstance();

        request.checkArguments();
        GrupoAsignacionNovedad instance = grupoAsignacionNovedadInsGrupoUsuariospthisGrupoAsignacionNovedad;

        instance.add2GrupoUsuarios(grupoAsignacionNovedadInsGrupoUsuariospevcGrupoAsignacion);
        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.grupoasignacion.siu.InsGrupoUsuariosRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.grupoasignacion.siu.InsGrupoUsuariosRequest();
            relatedRequest.setGrupoAsignacionInsGrupoUsuariospthisGrupoAsignacionInstance(grupoAsignacionNovedadInsGrupoUsuariospevcGrupoAsignacion);
            relatedRequest.setGrupoAsignacionInsGrupoUsuariospevcGrupoAsignacionNovedadInstance(grupoAsignacionNovedadInsGrupoUsuariospthisGrupoAsignacionNovedad);
            grupoAsignacionSrv.insGrupoUsuariosImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));

        return response;
    }

    // Service: DelGrupoUsuarios

    /*
     * Entry point for service DelGrupoUsuarios
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse delGrupoUsuarios(
            com.keralty.aeconfiguraciones.viewmodel.grupoasignacionnovedad.siu.DelGrupoUsuariosRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = grupoAsignacionNovedadSrv.delGrupoUsuariosTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service DelGrupoUsuarios
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse delGrupoUsuariosTxn(
            com.keralty.aeconfiguraciones.viewmodel.grupoasignacionnovedad.siu.DelGrupoUsuariosRequest request) throws ModelException {
        request.setGrupoAsignacionNovedadDelGrupoUsuariospthisGrupoAsignacionNovedadInstance(grupoAsignacionNovedadSrv.getByOIDWithHV(request.getGrupoAsignacionNovedadDelGrupoUsuariospthisGrupoAsignacionNovedad()));
        request.setGrupoAsignacionNovedadDelGrupoUsuariospevcGrupoAsignacionInstance(grupoAsignacionSrv.getByOIDWithHV(request.getGrupoAsignacionNovedadDelGrupoUsuariospevcGrupoAsignacion()));
        return grupoAsignacionNovedadSrv.delGrupoUsuariosImplementation(request, true);
    }

    /*
     * Implements service DelGrupoUsuarios
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse delGrupoUsuariosImplementation(
            com.keralty.aeconfiguraciones.viewmodel.grupoasignacionnovedad.siu.DelGrupoUsuariosRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        GrupoAsignacionNovedad grupoAsignacionNovedadDelGrupoUsuariospthisGrupoAsignacionNovedad = request.getGrupoAsignacionNovedadDelGrupoUsuariospthisGrupoAsignacionNovedadInstance();
        GrupoAsignacion grupoAsignacionNovedadDelGrupoUsuariospevcGrupoAsignacion = request.getGrupoAsignacionNovedadDelGrupoUsuariospevcGrupoAsignacionInstance();

        request.checkArguments();
        GrupoAsignacionNovedad instance = grupoAsignacionNovedadDelGrupoUsuariospthisGrupoAsignacionNovedad;

        instance.add2GrupoUsuarios(null);
        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.grupoasignacion.siu.DelGrupoUsuariosRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.grupoasignacion.siu.DelGrupoUsuariosRequest();
            relatedRequest.setGrupoAsignacionDelGrupoUsuariospthisGrupoAsignacionInstance(grupoAsignacionNovedadDelGrupoUsuariospevcGrupoAsignacion);
            relatedRequest.setGrupoAsignacionDelGrupoUsuariospevcGrupoAsignacionNovedadInstance(grupoAsignacionNovedadDelGrupoUsuariospthisGrupoAsignacionNovedad);
            grupoAsignacionSrv.delGrupoUsuariosImplementation(relatedRequest, false);
        }


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
            com.keralty.aeconfiguraciones.viewmodel.grupoasignacionnovedad.siu.TCREARRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = grupoAsignacionNovedadSrv.tCREARTxn(request);
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
            com.keralty.aeconfiguraciones.viewmodel.grupoasignacionnovedad.siu.TCREARRequest request) throws ModelException {
        request.setGrupoAsignacionNovedadTCREARptpagrDominioInstance(dominioSrv.getByOIDWithHV(request.getGrupoAsignacionNovedadTCREARptpagrDominio()));
        request.setGrupoAsignacionNovedadTCREARptpagrCausalInstance(causalSrv.getByOIDWithHV(new CausalOid().fromJsonString(request.getGrupoAsignacionNovedadTCREARptpagrCausal())));
        request.setGrupoAsignacionNovedadTCREARptpagrGrupoUsuariosInstance(grupoAsignacionSrv.getByOIDWithHV(request.getGrupoAsignacionNovedadTCREARptpagrGrupoUsuarios()));
        request.setGrupoAsignacionNovedadTCREARptNovedadInstance(novedadSrv.getByOIDWithHV(new NovedadOid().fromJsonString(request.getGrupoAsignacionNovedadTCREARptNovedad())));
        return grupoAsignacionNovedadSrv.tCREARImplementation(request);
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
            com.keralty.aeconfiguraciones.viewmodel.grupoasignacionnovedad.siu.TCREARRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Causal grupoAsignacionNovedadTCREARptpagrCausal = request.getGrupoAsignacionNovedadTCREARptpagrCausalInstance();
        GrupoAsignacion grupoAsignacionNovedadTCREARptpagrGrupoUsuarios = request.getGrupoAsignacionNovedadTCREARptpagrGrupoUsuariosInstance();
        Dominio grupoAsignacionNovedadTCREARptpagrDominio = request.getGrupoAsignacionNovedadTCREARptpagrDominioInstance();

        request.checkArguments();

        tCREARPreconditions(request);
        GrupoAsignacionNovedad instance;

        com.keralty.aeconfiguraciones.viewmodel.grupoasignacionnovedad.siu.CrearRequest step1Request = new com.keralty.aeconfiguraciones.viewmodel.grupoasignacionnovedad.siu.CrearRequest();
        step1Request.setGrupoAsignacionNovedadcrearpagrCausalInstance(grupoAsignacionNovedadTCREARptpagrCausal);
        step1Request.setGrupoAsignacionNovedadcrearpagrGrupoUsuariosInstance(grupoAsignacionNovedadTCREARptpagrGrupoUsuarios);
        step1Request.setGrupoAsignacionNovedadcrearpagrDominioInstance(grupoAsignacionNovedadTCREARptpagrDominio);
        instance = (GrupoAsignacionNovedad) grupoAsignacionNovedadSrv.crearImplementation(step1Request).getInstance();

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
    public ServiceResponse tCREARCheckPreconditions(com.keralty.aeconfiguraciones.viewmodel.grupoasignacionnovedad.siu.TCREARRequest request) {
        ServiceResponse response = new ServiceResponse();
        request.setGrupoAsignacionNovedadTCREARptpagrDominioInstance(dominioSrv.getByOIDWithHV(request.getGrupoAsignacionNovedadTCREARptpagrDominio()));
        request.setGrupoAsignacionNovedadTCREARptpagrCausalInstance(causalSrv.getByOIDWithHV(new CausalOid().fromJsonString(request.getGrupoAsignacionNovedadTCREARptpagrCausal())));
        request.setGrupoAsignacionNovedadTCREARptpagrGrupoUsuariosInstance(grupoAsignacionSrv.getByOIDWithHV(request.getGrupoAsignacionNovedadTCREARptpagrGrupoUsuarios()));
        request.setGrupoAsignacionNovedadTCREARptNovedadInstance(novedadSrv.getByOIDWithHV(new NovedadOid().fromJsonString(request.getGrupoAsignacionNovedadTCREARptNovedad())));
        try {
            if("Clas_1586852003840652Pre_1".equals(request.getPreconditionId()))
                checkClas1586852003840652Pre1(request);
            if("Clas_1586852003840652Pre_2".equals(request.getPreconditionId()))
                checkClas1586852003840652Pre2(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    private void tCREARPreconditions(
            com.keralty.aeconfiguraciones.viewmodel.grupoasignacionnovedad.siu.TCREARRequest request) 
            throws ModelException {
        checkClas1586852003840652Pre1(request);
        checkClas1586852003840652Pre2(request);
    }

    private void checkClas1586852003840652Pre1 (
            com.keralty.aeconfiguraciones.viewmodel.grupoasignacionnovedad.siu.TCREARRequest request) 
            throws ModelException {
        // COUNT( ptpagrDominio.GrupoAsignacionNovedad ) WHERE (ptpagrDominio.GrupoAsignacionNovedad.Causal = ptpagrCausal) = 0

        Dominio grupoAsignacionNovedadTCREARptpagrDominio = request.getGrupoAsignacionNovedadTCREARptpagrDominioInstance();
        Causal grupoAsignacionNovedadTCREARptpagrCausal = request.getGrupoAsignacionNovedadTCREARptpagrCausalInstance();
        try {
            boolean preconditionHolds = (repository.assocOperator000(grupoAsignacionNovedadTCREARptpagrDominio, grupoAsignacionNovedadTCREARptpagrCausal)).equals(Long.valueOf(0));
            if (!preconditionHolds) throw new PreconditionException("Ya existe una configuración para el Causal eleccionado en el Dominio");
        } catch (SystemException e) {
            LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
        }
    }

    private void checkClas1586852003840652Pre2 (
            com.keralty.aeconfiguraciones.viewmodel.grupoasignacionnovedad.siu.TCREARRequest request) 
            throws ModelException {
        // ptpagrGrupoUsuarios.Tipo = 1

        GrupoAsignacion grupoAsignacionNovedadTCREARptpagrGrupoUsuarios = request.getGrupoAsignacionNovedadTCREARptpagrGrupoUsuariosInstance();
        boolean preconditionHolds = (grupoAsignacionNovedadTCREARptpagrGrupoUsuarios.getGrupoAsignacionTipo()).equals(Long.valueOf(1));
        if (!preconditionHolds) throw new PreconditionException("El grupo debe ser del tipo Asesores Integrales y Grabador Afiliaciones");
    }

    // Service: TASGGRUPO

    /*
     * Entry point for service TASGGRUPO
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse tASGGRUPO(
            com.keralty.aeconfiguraciones.viewmodel.grupoasignacionnovedad.siu.TASGGRUPORequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = grupoAsignacionNovedadSrv.tASGGRUPOTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service TASGGRUPO
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse tASGGRUPOTxn(
            com.keralty.aeconfiguraciones.viewmodel.grupoasignacionnovedad.siu.TASGGRUPORequest request) throws ModelException, SystemException {
        request.setGrupoAsignacionNovedadTASGGRUPOpthisGrupoAsignacionNovedadInstance(grupoAsignacionNovedadSrv.getByOIDWithHV(request.getGrupoAsignacionNovedadTASGGRUPOpthisGrupoAsignacionNovedad()));
        request.setGrupoAsignacionNovedadTASGGRUPOptGrupoInstance(grupoAsignacionSrv.getByOIDWithHV(request.getGrupoAsignacionNovedadTASGGRUPOptGrupo()));
        return grupoAsignacionNovedadSrv.tASGGRUPOImplementation(request);
    }

    /*
     * Implements service TASGGRUPO
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse tASGGRUPOImplementation(
            com.keralty.aeconfiguraciones.viewmodel.grupoasignacionnovedad.siu.TASGGRUPORequest request) throws ModelException, SystemException {
        ServiceResponse response = new ServiceResponse();
        GrupoAsignacion grupoAsignacionNovedadTASGGRUPOptGrupo = request.getGrupoAsignacionNovedadTASGGRUPOptGrupoInstance();
        GrupoAsignacionNovedad grupoAsignacionNovedadTASGGRUPOpthisGrupoAsignacionNovedad = request.getGrupoAsignacionNovedadTASGGRUPOpthisGrupoAsignacionNovedadInstance();

        request.checkArguments();

        tASGGRUPOPreconditions(request);
        GrupoAsignacionNovedad instance = grupoAsignacionNovedadTASGGRUPOpthisGrupoAsignacionNovedad;

        /* Action 1: IF (EXIST (GrupoUsuarios) = true) */
        boolean ifCondition1 = repository.assocOperator002(grupoAsignacionNovedadTASGGRUPOpthisGrupoAsignacionNovedad).equals(true);
        if (ifCondition1) {
            com.keralty.aeconfiguraciones.viewmodel.grupoasignacionnovedad.siu.DelGrupoUsuariosRequest step10Request = new com.keralty.aeconfiguraciones.viewmodel.grupoasignacionnovedad.siu.DelGrupoUsuariosRequest();
            step10Request.setGrupoAsignacionNovedadDelGrupoUsuariospthisGrupoAsignacionNovedadInstance(grupoAsignacionNovedadTASGGRUPOpthisGrupoAsignacionNovedad);
            step10Request.setGrupoAsignacionNovedadDelGrupoUsuariospevcGrupoAsignacionInstance(grupoAsignacionNovedadTASGGRUPOpthisGrupoAsignacionNovedad.getGrupoUsuarios());
            grupoAsignacionNovedadSrv.delGrupoUsuariosImplementation(step10Request, true);
        }

        com.keralty.aeconfiguraciones.viewmodel.grupoasignacionnovedad.siu.InsGrupoUsuariosRequest step2Request = new com.keralty.aeconfiguraciones.viewmodel.grupoasignacionnovedad.siu.InsGrupoUsuariosRequest();
        step2Request.setGrupoAsignacionNovedadInsGrupoUsuariospthisGrupoAsignacionNovedadInstance(grupoAsignacionNovedadTASGGRUPOpthisGrupoAsignacionNovedad);
        step2Request.setGrupoAsignacionNovedadInsGrupoUsuariospevcGrupoAsignacionInstance(grupoAsignacionNovedadTASGGRUPOptGrupo);
        grupoAsignacionNovedadSrv.insGrupoUsuariosImplementation(step2Request, true);

        response.setInstance(getByOID(instance.getOid()));

        return response;
    }

    /*
     * Checks service TASGGRUPO preconditions individually
     *
     * @param request Service request viewmodel
     *
     * @return Service preconditions evaluation result
     */
    public ServiceResponse tASGGRUPOCheckPreconditions(com.keralty.aeconfiguraciones.viewmodel.grupoasignacionnovedad.siu.TASGGRUPORequest request) {
        ServiceResponse response = new ServiceResponse();
        request.setGrupoAsignacionNovedadTASGGRUPOpthisGrupoAsignacionNovedadInstance(grupoAsignacionNovedadSrv.getByOIDWithHV(request.getGrupoAsignacionNovedadTASGGRUPOpthisGrupoAsignacionNovedad()));
        request.setGrupoAsignacionNovedadTASGGRUPOptGrupoInstance(grupoAsignacionSrv.getByOIDWithHV(request.getGrupoAsignacionNovedadTASGGRUPOptGrupo()));
        try {
            if("Clas_1586852003840652Pre_3".equals(request.getPreconditionId()))
                checkClas1586852003840652Pre3(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    private void tASGGRUPOPreconditions(
            com.keralty.aeconfiguraciones.viewmodel.grupoasignacionnovedad.siu.TASGGRUPORequest request) 
            throws ModelException {
        checkClas1586852003840652Pre3(request);
    }

    private void checkClas1586852003840652Pre3 (
            com.keralty.aeconfiguraciones.viewmodel.grupoasignacionnovedad.siu.TASGGRUPORequest request) 
            throws ModelException {
        // ptGrupo.Tipo = 1

        GrupoAsignacion grupoAsignacionNovedadTASGGRUPOptGrupo = request.getGrupoAsignacionNovedadTASGGRUPOptGrupoInstance();
        boolean preconditionHolds = (grupoAsignacionNovedadTASGGRUPOptGrupo.getGrupoAsignacionTipo()).equals(Long.valueOf(1));
        if (!preconditionHolds) throw new PreconditionException("El grupo debe ser del tipo Asesores Integrales y Grabador Afiliaciones");
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
        select.getOrderByPart().addItem(Constants.normalizePath(GrupoAsignacionNovedadConstants.CLASS_NAME, GrupoAsignacionNovedadConstants.ATTR_FIELD_GRUPOASIGNACIONNOVEDADID, false), true);
    }
    
    @Override
    protected AbstractAppEntity buildRelatedInstance(String relatedInstanceJsonOid, String relatedInstancePathFirstItem, String remainingPath) {
        AbstractAppEntity relatedInstance = null;
        if (relatedInstancePathFirstItem.isEmpty()) {
            relatedInstance = buildGrupoAsignacionNovedadRelatedInstance(relatedInstanceJsonOid);
        }
        if (GrupoAsignacionNovedadConstants.ROLE_NAME_DOMINIO.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildGrupoAsignacionNovedadRelatedInstance4Dominio(relatedInstanceJsonOid, remainingPath);
        }
        if (GrupoAsignacionNovedadConstants.ROLE_NAME_CAUSAL.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildGrupoAsignacionNovedadRelatedInstance4Causal(relatedInstanceJsonOid, remainingPath);
        }
        if (GrupoAsignacionNovedadConstants.ROLE_NAME_GRUPOUSUARIOS.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildGrupoAsignacionNovedadRelatedInstance4GrupoAsignacion(relatedInstanceJsonOid, remainingPath);
        }
        return relatedInstance;
    }
    
    private AbstractAppEntity buildGrupoAsignacionNovedadRelatedInstance(String relatedInstanceJsonOid) {
    	ObjectMapper mapper = new ObjectMapper();
    	try {
    		return new GrupoAsignacionNovedad(mapper.readValue(relatedInstanceJsonOid, GrupoAsignacionNovedadOid.class));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    private AbstractAppEntity buildGrupoAsignacionNovedadRelatedInstance4Dominio(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new Dominio(mapper.readValue(relatedInstanceJsonOid, DominioOid.class)): Dominio.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    private AbstractAppEntity buildGrupoAsignacionNovedadRelatedInstance4Causal(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new Causal(mapper.readValue(relatedInstanceJsonOid, CausalOid.class)): Causal.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    private AbstractAppEntity buildGrupoAsignacionNovedadRelatedInstance4GrupoAsignacion(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new GrupoAsignacion(mapper.readValue(relatedInstanceJsonOid, GrupoAsignacionOid.class)): GrupoAsignacion.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    @Override
    protected void applyFilterByOid(String jsonOid, SQLSelect sql) {
        try {
            GrupoAsignacionNovedadOid oid = new GrupoAsignacionNovedadOid().fromJsonString(jsonOid);
            SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
            comparison1.setOperator("=");
            comparison1.setFirstOperand(new SQLWhereOperandPathAttribute(GrupoAsignacionNovedadConstants.ATTR_FIELD_GRUPOASIGNACIONNOVEDADID, Constants.Type.AUTONUMERIC.getTypeName()));
            comparison1.setSecondOperand(new SQLWhereOperandLiteral(oid.getGrupoAsignacionNovedadId(), Constants.Type.AUTONUMERIC.getTypeName()));
            sql.addComparison(comparison1);
        } catch (Exception e) {
            LoggerFactory.getLogger(GrupoAsignacionNovedadService.class).error(e.getMessage());
        }
    }

}
