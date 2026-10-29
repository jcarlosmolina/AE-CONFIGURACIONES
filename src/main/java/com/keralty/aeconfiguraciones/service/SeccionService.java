package com.keralty.aeconfiguraciones.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.keralty.aeconfiguraciones.exception.MinCardinalityException;
import com.keralty.aeconfiguraciones.exception.ModelException;
import com.keralty.aeconfiguraciones.exception.PreconditionException;
import com.keralty.aeconfiguraciones.exception.SystemException;
import com.keralty.aeconfiguraciones.global.Constants;
import com.keralty.aeconfiguraciones.global.SeccionConstants;
import com.keralty.aeconfiguraciones.persistence.AbstractAppEntity;
import com.keralty.aeconfiguraciones.persistence.CuestionarioMed;
import com.keralty.aeconfiguraciones.persistence.oid.CuestionarioMedOid;
import com.keralty.aeconfiguraciones.persistence.oid.PreguntaOid;
import com.keralty.aeconfiguraciones.persistence.oid.SeccionOid;
import com.keralty.aeconfiguraciones.persistence.Pregunta;
import com.keralty.aeconfiguraciones.persistence.Seccion;
import com.keralty.aeconfiguraciones.repository.SeccionJpaRepository;
import com.keralty.aeconfiguraciones.sqlutils.SQLSelect;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereComparison;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereConjunction;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereDisjunction;
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
public class SeccionService extends AbstractService {

    SeccionJpaRepository repository;

    SeccionService seccionSrv;

    CuestionarioMedService cuestionarioMedSrv;

    PreguntaService preguntaSrv;

    @Inject
    public SeccionService(
            SeccionService seccionSrv,
            CuestionarioMedService cuestionarioMedSrv,
            PreguntaService preguntaSrv,
            SeccionJpaRepository repository) {
        this.seccionSrv = seccionSrv;
        this.cuestionarioMedSrv = cuestionarioMedSrv;
        this.preguntaSrv = preguntaSrv;
        this.repository = repository;
    }

    public List<Map<String, Object>> solveQuery(QueryRequest request) {
        return solveGenericQuery(request, SeccionConstants.CLASS_NAME);
    }

    private List<Seccion> solveSeccionTypedQuery(QueryRequest request) {
        return repository.solveQuery(processQueryRequest(request, SeccionConstants.CLASS_NAME), request.getPageSize(), request.getPageNumber());
    }
    
    private int solveSeccionTypedCountQuery(QueryRequest request) {
        return repository.solveCountQuery(processCountQueryRequest(request, SeccionConstants.CLASS_NAME));
    }

    /*
     * Returns the population of class Seccion
     *
     * @return Population of class Seccion
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<Seccion> get() {
        List<Seccion> results = new java.util.ArrayList<>();
        repository.findAll().forEach(results::add);
        return results;
    }
    
    /*
     * Gets the preloaded list of objects of class Seccion
     *
     * @param queryRequest Request object that dictates the search conditions to 
     * retrieve objects
     *
     * @return Preloaded list of objects of class Seccion
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<PreloadViewModel> getPreload(QueryRequest queryRequest) {
        List<PreloadViewModel> results = new ArrayList<>();
        List<Seccion> instances = get(queryRequest);
        if(SeccionConstants.DSICSECCION.equals(queryRequest.getDisplaySetName())) {
            results = instances.stream().map(x -> new PreloadViewModel(x.getOid().toJson(), x.buildICSeccion())).collect(Collectors.toList());
        }
        if(SeccionConstants.DSDSSECCIONPIU.equals(queryRequest.getDisplaySetName())) {
            results = instances.stream().map(x -> new PreloadViewModel(x.getOid().toJson(), x.buildDSSeccionPIU())).collect(Collectors.toList());
        }
        return results;
    }
    /*
     * Returns the population of class Seccion that matches certain search criteria
     *
     * @param queryRequest Search criteria
     *
     * @return Population of class Seccion matching the search criteria
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<Seccion> get(QueryRequest queryRequest) {
        return solveSeccionTypedQuery(queryRequest);
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
            return new PopulationResponseViewModel(solveSeccionTypedQuery(queryRequest).stream()
                    .map(instance -> buildDynamicObject(instance, queryRequest.getDisplaySetItems()))
                    .collect(Collectors.toList()), queryRequest.getPageSize() != 0 ? solveSeccionTypedCountQuery(queryRequest) : 0);
        } else {
            return new PreloadResponseViewModel(solveSeccionTypedQuery(queryRequest).stream()
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
        return new PopulationResponseViewModel(solveSeccionTypedQuery(queryRequest).stream()
                .map(instance -> buildDynamicObject(instance, displaySetItems))
                .collect(Collectors.toList()));
    }

    /*
     * Returns an instance of Seccion by ID
     *
     * @param seccionIdentificador Identification field seccionIdentificador
     *
     * @return Instance of Seccion
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public Seccion getById(Long seccionIdentificador) {
        return getByOID(new SeccionOid(seccionIdentificador));
    }
    
    /*
     * Returns an instance of Seccion by OID
     *
     * @param oid Object IDentifier
     *
     * @return Instance of Seccion
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public Seccion getByOID(SeccionOid oid) {
        Optional<Seccion> instance = getInstanceByOid(oid);
        return instance.isPresent() ? instance.get() : null;
    }
    
    /*
     * Returns an instance of Seccion by OID applying horizontal visibility constraints
     *
     * @param oid Object IDentifier
     *
     * @return Instance of Seccion
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public Seccion getByOIDWithHV(SeccionOid oid) {
        Optional<Seccion> instance = getInstanceByOid(oid);
    	return instance.isPresent() && instance.get().checkHorizontalVisibility(getAgentInstance()) 
    		 ? instance.get() 
    		 : null;
    }
    
    private Optional<Seccion> getInstanceByOid(SeccionOid oid) {
        return oid != null && oid.getSeccionIdentificador() != null
               ? repository.findById(oid.getSeccionIdentificador())
               : Optional.empty();
    }

    /*
     * Returns an instance of Seccion by OID in JSON format
     *
     * @param jsonOid Object IDentifier in JSON format
     *
     * @return Instance of Seccion
     */
    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public Seccion getByJsonOid(String jsonOid) {
        SeccionOid oid = new SeccionOid();
        oid.fromJsonString(jsonOid);
        return getByOIDWithHV(oid);
    }

    /*
     * Returns the list of instances of Seccion related to a given instance through 
     * a given path
     *
     * @param relatedInstance Related instance
     *
     * @param relatedInstancePath Related instance path
     *
     * @return List of instances of Seccion
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<Seccion> getByRelated(AbstractAppEntity relatedInstance, String relatedInstancePath) {
        QueryRequest queryRequest = new QueryRequest(new FilterRequest(Constants.FILTER_BY_RELATED,
                Arrays.asList(new FilterVariableRequest(Constants.FILTER_BY_RELATED_FVAR_PATH, relatedInstancePath),
                        new FilterVariableRequest(Constants.FILTER_BY_RELATED_FVAR_OID, relatedInstance.getOid().toJson()))));
        return solveSeccionTypedQuery(queryRequest);
    }

    /*
     * Returns the list of instances of Seccion related to a given instance through
     * a given path
     *
     * @param relatedInstance Related instance
     *
     * @param relatedInstancePath Related instance path
     *
     * @return List of instances of Seccion
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<Seccion> getInstancesByRelated(AbstractAppEntity relatedInstance, String relatedInstancePath) {
        return getByRelated(relatedInstance, relatedInstancePath);
    }

    /*
     * Returns a set of displayset items for an instance of Seccion
     *
     * @param oid Object IDentifier
     *
     * @param displaySetItems List of comma-separated displayset items
     *
     * @return Set of displayset items for an instance of Seccion
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public InstanceResponseViewModel getSeccionDynamic(SeccionOid oid, String displaySetItems) {
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
    public String getSuppInfo(SeccionOid oid, String dsName) {
        Seccion instance = getByOIDWithHV(oid);
        return (instance != null ? getSuppInfo(instance, dsName): "");
    }

    private String getSuppInfo(Seccion instance, String dsName) {
        if (instance == null || instance.isNull()) return "";
        if (SeccionConstants.DSICSECCION.equals(dsName))
            return instance.buildICSeccion();
        return dsName.isEmpty() ? instance.getOid().toJson() : "";
    }

    public static PreloadViewModel buildPreloadViewModel(Seccion instance, String dsName) {
        if (SeccionConstants.DSICSECCION.equals(dsName))
            return new PreloadViewModel(instance.getOid().toJson(), instance.buildICSeccion());
        if (SeccionConstants.DSDSSECCIONPIU.equals(dsName))
            return new PreloadViewModel(instance.getOid().toJson(), instance.buildDSSeccionPIU());
        return new PreloadViewModel(instance.getOid().toJson(), "");
    }


    @Override
    protected void applyServiceSIFilters(FilterRequest filterRequest, SQLSelect sql) {
        if ("SuppInfo_IC_Seccion".equals(filterRequest.getName()))
            suppInfo4ICSeccion(filterRequest.getFilterVariable("searchText").toString(), sql);
    }



    // IIU Methods


    /*
     * Solves the query needed for PIUSeccion
     *
     * @param queryRequest Query request
     * @return List of viewmodels for Seccion using displayset DSSeccionPIU that 
     * match the search criteria
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<com.keralty.aeconfiguraciones.viewmodel.seccion.ds.DSSeccionPIU> piuPIUSeccion(QueryRequest queryRequest) {
        return solveSeccionTypedQuery(queryRequest).stream()
                .map(com.keralty.aeconfiguraciones.viewmodel.seccion.ds.DSSeccionPIU::new)
                .collect(Collectors.toList());
    }

    /*
     * Solves the COUNT query needed for PIUSeccion
     *
     * @param queryRequest Query request
     * @returns Count of instances for PIUSeccion
     */
    public int getCount4PIUSeccion(QueryRequest queryRequest) {
        return solveSeccionTypedQuery(queryRequest).size();
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
            com.keralty.aeconfiguraciones.viewmodel.seccion.siu.CrearRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = seccionSrv.crearTxn(request);
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
            com.keralty.aeconfiguraciones.viewmodel.seccion.siu.CrearRequest request) throws ModelException {
        request.setSeccioncrearpagrCuestionarioInstance(cuestionarioMedSrv.getByOIDWithHV(request.getSeccioncrearpagrCuestionario()));
        return seccionSrv.crearImplementation(request);
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
            com.keralty.aeconfiguraciones.viewmodel.seccion.siu.CrearRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        CuestionarioMed seccioncrearpagrCuestionario = request.getSeccioncrearpagrCuestionarioInstance();
        String seccioncrearpatrNombre = request.getSeccioncrearpatrNombre();
        Long seccioncrearpatrNumOrden = request.getSeccioncrearpatrNumOrden();

        request.checkArguments();
        Seccion instance = new Seccion();
        instance.add2Cuestionario(seccioncrearpagrCuestionario);

        instance.setSeccionIdentificador(Long.valueOf(0));
        instance.setSeccionNombre(seccioncrearpatrNombre);
        instance.setSeccionNumOrden(seccioncrearpatrNumOrden);

        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICSeccion());

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
            com.keralty.aeconfiguraciones.viewmodel.seccion.siu.EliminarRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = seccionSrv.eliminarTxn(request);
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
            com.keralty.aeconfiguraciones.viewmodel.seccion.siu.EliminarRequest request) throws ModelException {
        request.setSeccioneliminarpthisSeccionInstance(seccionSrv.getByOIDWithHV(request.getSeccioneliminarpthisSeccion()));
        return seccionSrv.eliminarImplementation(request);
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
            com.keralty.aeconfiguraciones.viewmodel.seccion.siu.EliminarRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Seccion seccioneliminarpthisSeccion = request.getSeccioneliminarpthisSeccionInstance();

        request.checkArguments();
        Seccion instance = seccioneliminarpthisSeccion;
        if(seccioneliminarpthisSeccion.getPreguntas().size() > 0) {
            throw new MinCardinalityException(SeccionConstants.CLASS_ALIAS, SeccionConstants.ROLE_ALIAS_PREGUNTAS);
        }


        repository.delete(instance);
        
        response.setInstance(instance);
        response.setInstanceSuppInfo(instance.buildICSeccion());

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
            com.keralty.aeconfiguraciones.viewmodel.seccion.siu.ModificarRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = seccionSrv.modificarTxn(request);
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
            com.keralty.aeconfiguraciones.viewmodel.seccion.siu.ModificarRequest request) throws ModelException {
        request.setSeccionmodificarpthisSeccionInstance(seccionSrv.getByOIDWithHV(request.getSeccionmodificarpthisSeccion()));
        return seccionSrv.modificarImplementation(request);
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
            com.keralty.aeconfiguraciones.viewmodel.seccion.siu.ModificarRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Seccion seccionmodificarpthisSeccion = request.getSeccionmodificarpthisSeccionInstance();
        String seccionmodificarpNombre = request.getSeccionmodificarpNombre();

        request.checkArguments();
        Seccion instance = seccionmodificarpthisSeccion;
        instance.setSeccionNombre(seccionmodificarpNombre);


        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICSeccion());

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
            com.keralty.aeconfiguraciones.viewmodel.seccion.siu.CambiarOrdenRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = seccionSrv.cambiarOrdenTxn(request);
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
            com.keralty.aeconfiguraciones.viewmodel.seccion.siu.CambiarOrdenRequest request) throws ModelException {
        request.setSeccioncambiarOrdenpthisSeccionInstance(seccionSrv.getByOIDWithHV(request.getSeccioncambiarOrdenpthisSeccion()));
        return seccionSrv.cambiarOrdenImplementation(request);
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
            com.keralty.aeconfiguraciones.viewmodel.seccion.siu.CambiarOrdenRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Seccion seccioncambiarOrdenpthisSeccion = request.getSeccioncambiarOrdenpthisSeccionInstance();
        Long seccioncambiarOrdenpNuevoOrden = request.getSeccioncambiarOrdenpNuevoOrden();

        request.checkArguments();
        Seccion instance = seccioncambiarOrdenpthisSeccion;
        instance.setSeccionNumOrden(seccioncambiarOrdenpNuevoOrden);


        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICSeccion());

        return response;
    }

    // Service: CambSeccionPregunta

    /*
     * Entry point for service CambSeccionPregunta
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse cambSeccionPregunta(
            com.keralty.aeconfiguraciones.viewmodel.seccion.siu.CambSeccionPreguntaRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = seccionSrv.cambSeccionPreguntaTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service CambSeccionPregunta
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse cambSeccionPreguntaTxn(
            com.keralty.aeconfiguraciones.viewmodel.seccion.siu.CambSeccionPreguntaRequest request) throws ModelException {
        request.setSeccionCambSeccionPreguntapthisSeccionInstance(seccionSrv.getByOIDWithHV(request.getSeccionCambSeccionPreguntapthisSeccion()));
        request.setSeccionCambSeccionPreguntapevcPreguntaInstance(preguntaSrv.getByOIDWithHV(request.getSeccionCambSeccionPreguntapevcPregunta()));
        return seccionSrv.cambSeccionPreguntaImplementation(request, true);
    }

    /*
     * Implements service CambSeccionPregunta
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse cambSeccionPreguntaImplementation(
            com.keralty.aeconfiguraciones.viewmodel.seccion.siu.CambSeccionPreguntaRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Seccion seccionCambSeccionPreguntapthisSeccion = request.getSeccionCambSeccionPreguntapthisSeccionInstance();
        Pregunta seccionCambSeccionPreguntapevcPregunta = request.getSeccionCambSeccionPreguntapevcPreguntaInstance();

        request.checkArguments();
        Seccion instance = seccionCambSeccionPreguntapthisSeccion;

        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.pregunta.siu.CambSeccionPreguntaRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.pregunta.siu.CambSeccionPreguntaRequest();
            relatedRequest.setPreguntaCambSeccionPreguntapthisPreguntaInstance(seccionCambSeccionPreguntapevcPregunta);
            relatedRequest.setPreguntaCambSeccionPreguntapevcSeccionInstance(seccionCambSeccionPreguntapthisSeccion);
            preguntaSrv.cambSeccionPreguntaImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICSeccion());

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
            com.keralty.aeconfiguraciones.viewmodel.seccion.siu.TCREARRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = seccionSrv.tCREARTxn(request);
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
            com.keralty.aeconfiguraciones.viewmodel.seccion.siu.TCREARRequest request) throws ModelException, SystemException {
        request.setSeccionTCREARptpagrCuestionarioInstance(cuestionarioMedSrv.getByOIDWithHV(new CuestionarioMedOid().fromJsonString(request.getSeccionTCREARptpagrCuestionario())));
        return seccionSrv.tCREARImplementation(request);
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
            com.keralty.aeconfiguraciones.viewmodel.seccion.siu.TCREARRequest request) throws ModelException, SystemException {
        ServiceResponse response = new ServiceResponse();
        CuestionarioMed seccionTCREARptpagrCuestionario = request.getSeccionTCREARptpagrCuestionarioInstance();
        String seccionTCREARptpatrNombre = request.getSeccionTCREARptpatrNombre();
        Long seccionTCREARptpatrNumOrden = request.getSeccionTCREARptpatrNumOrden();

        request.checkArguments();

        tCREARPreconditions(request);
        Seccion instance;

        com.keralty.aeconfiguraciones.viewmodel.seccion.siu.CrearRequest step1Request = new com.keralty.aeconfiguraciones.viewmodel.seccion.siu.CrearRequest();
        step1Request.setSeccioncrearpagrCuestionarioInstance(seccionTCREARptpagrCuestionario);
        step1Request.setSeccioncrearpatrNombre(seccionTCREARptpatrNombre);
        step1Request.setSeccioncrearpatrNumOrden(Long.valueOf(0));
        instance = (Seccion) seccionSrv.crearImplementation(step1Request).getInstance();

        for(Seccion step2Entity : repository.assocOperator001(instance, seccionTCREARptpatrNumOrden)) {
            com.keralty.aeconfiguraciones.viewmodel.seccion.siu.CambiarOrdenRequest step2Request = new com.keralty.aeconfiguraciones.viewmodel.seccion.siu.CambiarOrdenRequest();
            step2Request.setSeccioncambiarOrdenpthisSeccionInstance(step2Entity);
            step2Request.setSeccioncambiarOrdenpNuevoOrden(step2Entity.getSeccionNumOrden() + Long.valueOf(1));
            seccionSrv.cambiarOrdenImplementation(step2Request);
        }

        com.keralty.aeconfiguraciones.viewmodel.seccion.siu.CambiarOrdenRequest step3Request = new com.keralty.aeconfiguraciones.viewmodel.seccion.siu.CambiarOrdenRequest();
        step3Request.setSeccioncambiarOrdenpthisSeccionInstance(instance);
        step3Request.setSeccioncambiarOrdenpNuevoOrden(seccionTCREARptpatrNumOrden);
        seccionSrv.cambiarOrdenImplementation(step3Request);

        response.setInstance(getByOID(instance.getOid()));
        response.setInstanceSuppInfo(instance.buildICSeccion());

        return response;
    }

    /*
     * Checks service TCREAR preconditions individually
     *
     * @param request Service request viewmodel
     *
     * @return Service preconditions evaluation result
     */
    public ServiceResponse tCREARCheckPreconditions(com.keralty.aeconfiguraciones.viewmodel.seccion.siu.TCREARRequest request) {
        ServiceResponse response = new ServiceResponse();
        request.setSeccionTCREARptpagrCuestionarioInstance(cuestionarioMedSrv.getByOIDWithHV(new CuestionarioMedOid().fromJsonString(request.getSeccionTCREARptpagrCuestionario())));
        try {
            if("Clas_1431633526784668Pre_1".equals(request.getPreconditionId()))
                checkClas1431633526784668Pre1(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    private void tCREARPreconditions(
            com.keralty.aeconfiguraciones.viewmodel.seccion.siu.TCREARRequest request) 
            throws ModelException {
        checkClas1431633526784668Pre1(request);
    }

    private void checkClas1431633526784668Pre1 (
            com.keralty.aeconfiguraciones.viewmodel.seccion.siu.TCREARRequest request) 
            throws ModelException {
        // ptpatrNumOrden >= 1

        Long seccionTCREARptpatrNumOrden = request.getSeccionTCREARptpatrNumOrden();
        boolean preconditionHolds = seccionTCREARptpatrNumOrden >= Long.valueOf(1);
        if (!preconditionHolds) throw new PreconditionException("Debe indicar un orden para la sección mayor o igual a 1");
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
            com.keralty.aeconfiguraciones.viewmodel.seccion.siu.TMODIFICARRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = seccionSrv.tMODIFICARTxn(request);
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
            com.keralty.aeconfiguraciones.viewmodel.seccion.siu.TMODIFICARRequest request) throws ModelException, SystemException {
        request.setSeccionTMODIFICARpthisSeccionInstance(seccionSrv.getByOIDWithHV(request.getSeccionTMODIFICARpthisSeccion()));
        return seccionSrv.tMODIFICARImplementation(request);
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
            com.keralty.aeconfiguraciones.viewmodel.seccion.siu.TMODIFICARRequest request) throws ModelException, SystemException {
        ServiceResponse response = new ServiceResponse();
        Long seccionTMODIFICARptpNumOrden = request.getSeccionTMODIFICARptpNumOrden();
        String seccionTMODIFICARptpNombre = request.getSeccionTMODIFICARptpNombre();
        Seccion seccionTMODIFICARpthisSeccion = request.getSeccionTMODIFICARpthisSeccionInstance();

        request.checkArguments();

        tMODIFICARPreconditions(request);
        Seccion instance = seccionTMODIFICARpthisSeccion;

        /* Action 1: IF (ptpNumOrden < NumOrden) */
        boolean ifCondition1 = seccionTMODIFICARptpNumOrden < seccionTMODIFICARpthisSeccion.getSeccionNumOrden();
        if (ifCondition1) {
            for(Seccion step110Entity : repository.assocOperator002(seccionTMODIFICARpthisSeccion, seccionTMODIFICARptpNumOrden)) {
                com.keralty.aeconfiguraciones.viewmodel.seccion.siu.CambiarOrdenRequest step10Request = new com.keralty.aeconfiguraciones.viewmodel.seccion.siu.CambiarOrdenRequest();
                step10Request.setSeccioncambiarOrdenpthisSeccionInstance(step110Entity);
                step10Request.setSeccioncambiarOrdenpNuevoOrden(step110Entity.getSeccionNumOrden() + Long.valueOf(1));
                seccionSrv.cambiarOrdenImplementation(step10Request);
            }
        }

        /* Action 2: IF (ptpNumOrden > NumOrden) */
        boolean ifCondition2 = seccionTMODIFICARptpNumOrden > seccionTMODIFICARpthisSeccion.getSeccionNumOrden();
        if (ifCondition2) {
            for(Seccion step220Entity : repository.assocOperator003(seccionTMODIFICARpthisSeccion, seccionTMODIFICARptpNumOrden)) {
                com.keralty.aeconfiguraciones.viewmodel.seccion.siu.CambiarOrdenRequest step20Request = new com.keralty.aeconfiguraciones.viewmodel.seccion.siu.CambiarOrdenRequest();
                step20Request.setSeccioncambiarOrdenpthisSeccionInstance(step220Entity);
                step20Request.setSeccioncambiarOrdenpNuevoOrden(step220Entity.getSeccionNumOrden() - Long.valueOf(1));
                seccionSrv.cambiarOrdenImplementation(step20Request);
            }
        }

        com.keralty.aeconfiguraciones.viewmodel.seccion.siu.CambiarOrdenRequest step3Request = new com.keralty.aeconfiguraciones.viewmodel.seccion.siu.CambiarOrdenRequest();
        step3Request.setSeccioncambiarOrdenpthisSeccionInstance(seccionTMODIFICARpthisSeccion);
        step3Request.setSeccioncambiarOrdenpNuevoOrden(seccionTMODIFICARptpNumOrden);
        seccionSrv.cambiarOrdenImplementation(step3Request);

        com.keralty.aeconfiguraciones.viewmodel.seccion.siu.ModificarRequest step4Request = new com.keralty.aeconfiguraciones.viewmodel.seccion.siu.ModificarRequest();
        step4Request.setSeccionmodificarpthisSeccionInstance(seccionTMODIFICARpthisSeccion);
        step4Request.setSeccionmodificarpNombre(seccionTMODIFICARptpNombre);
        seccionSrv.modificarImplementation(step4Request);

        response.setInstance(getByOID(instance.getOid()));
        response.setInstanceSuppInfo(instance.buildICSeccion());

        return response;
    }

    /*
     * Checks service TMODIFICAR preconditions individually
     *
     * @param request Service request viewmodel
     *
     * @return Service preconditions evaluation result
     */
    public ServiceResponse tMODIFICARCheckPreconditions(com.keralty.aeconfiguraciones.viewmodel.seccion.siu.TMODIFICARRequest request) {
        ServiceResponse response = new ServiceResponse();
        request.setSeccionTMODIFICARpthisSeccionInstance(seccionSrv.getByOIDWithHV(request.getSeccionTMODIFICARpthisSeccion()));
        try {
            if("Clas_1431633526784668Pre_2".equals(request.getPreconditionId()))
                checkClas1431633526784668Pre2(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    private void tMODIFICARPreconditions(
            com.keralty.aeconfiguraciones.viewmodel.seccion.siu.TMODIFICARRequest request) 
            throws ModelException {
        checkClas1431633526784668Pre2(request);
    }

    private void checkClas1431633526784668Pre2 (
            com.keralty.aeconfiguraciones.viewmodel.seccion.siu.TMODIFICARRequest request) 
            throws ModelException {
        // ptpNumOrden >= 1

        Long seccionTMODIFICARptpNumOrden = request.getSeccionTMODIFICARptpNumOrden();
        boolean preconditionHolds = seccionTMODIFICARptpNumOrden >= Long.valueOf(1);
        if (!preconditionHolds) throw new PreconditionException("El número de orden ha de ser mayor o igual a 1");
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
            com.keralty.aeconfiguraciones.viewmodel.seccion.siu.TELIMINARRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = seccionSrv.tELIMINARTxn(request);
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
            com.keralty.aeconfiguraciones.viewmodel.seccion.siu.TELIMINARRequest request) throws ModelException, SystemException {
        request.setSeccionTELIMINARpthisSeccionInstance(seccionSrv.getByOIDWithHV(request.getSeccionTELIMINARpthisSeccion()));
        return seccionSrv.tELIMINARImplementation(request);
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
            com.keralty.aeconfiguraciones.viewmodel.seccion.siu.TELIMINARRequest request) throws ModelException, SystemException {
        ServiceResponse response = new ServiceResponse();
        Seccion seccionTELIMINARpthisSeccion = request.getSeccionTELIMINARpthisSeccionInstance();

        request.checkArguments();

        tELIMINARPreconditions(request);
        Seccion instance = seccionTELIMINARpthisSeccion;

        for(Seccion step1Entity : repository.assocOperator004(seccionTELIMINARpthisSeccion)) {
            com.keralty.aeconfiguraciones.viewmodel.seccion.siu.CambiarOrdenRequest step1Request = new com.keralty.aeconfiguraciones.viewmodel.seccion.siu.CambiarOrdenRequest();
            step1Request.setSeccioncambiarOrdenpthisSeccionInstance(step1Entity);
            step1Request.setSeccioncambiarOrdenpNuevoOrden(step1Entity.getSeccionNumOrden() - Long.valueOf(1));
            seccionSrv.cambiarOrdenImplementation(step1Request);
        }

        com.keralty.aeconfiguraciones.viewmodel.seccion.siu.EliminarRequest step2Request = new com.keralty.aeconfiguraciones.viewmodel.seccion.siu.EliminarRequest();
        step2Request.setSeccioneliminarpthisSeccionInstance(seccionTELIMINARpthisSeccion);
        seccionSrv.eliminarImplementation(step2Request);

        response.setInstance(getByOID(instance.getOid()));
        response.setInstanceSuppInfo(instance.buildICSeccion());

        return response;
    }

    /*
     * Checks service TELIMINAR preconditions individually
     *
     * @param request Service request viewmodel
     *
     * @return Service preconditions evaluation result
     */
    public ServiceResponse tELIMINARCheckPreconditions(com.keralty.aeconfiguraciones.viewmodel.seccion.siu.TELIMINARRequest request) {
        ServiceResponse response = new ServiceResponse();
        request.setSeccionTELIMINARpthisSeccionInstance(seccionSrv.getByOIDWithHV(request.getSeccionTELIMINARpthisSeccion()));
        try {
            if("Clas_1431633526784668Pre_3".equals(request.getPreconditionId()))
                checkClas1431633526784668Pre3(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    private void tELIMINARPreconditions(
            com.keralty.aeconfiguraciones.viewmodel.seccion.siu.TELIMINARRequest request) 
            throws ModelException {
        checkClas1431633526784668Pre3(request);
    }

    private void checkClas1431633526784668Pre3 (
            com.keralty.aeconfiguraciones.viewmodel.seccion.siu.TELIMINARRequest request) 
            throws ModelException {
        // EXIST(Preguntas) = FALSE

        Seccion seccionTELIMINARpthisSeccion = request.getSeccionTELIMINARpthisSeccionInstance();
        try {
            boolean preconditionHolds = repository.assocOperator000(seccionTELIMINARpthisSeccion).equals(false);
            if (!preconditionHolds) throw new PreconditionException("No puede eliminar la sección porque hay preguntas que se incluyen en la misma");
        } catch (SystemException e) {
            LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            throw new SystemException(e.getMessage());
        }
    }

    // Service: TCAMBIARORDENPREGS

    /*
     * Entry point for service TCAMBIARORDENPREGS
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse tCAMBIARORDENPREGS(
            com.keralty.aeconfiguraciones.viewmodel.seccion.siu.TCAMBIARORDENPREGSRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = seccionSrv.tCAMBIARORDENPREGSTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service TCAMBIARORDENPREGS
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse tCAMBIARORDENPREGSTxn(
            com.keralty.aeconfiguraciones.viewmodel.seccion.siu.TCAMBIARORDENPREGSRequest request) throws ModelException, SystemException {
        request.setSeccionTCAMBIARORDENPREGSpthisSeccionInstance(seccionSrv.getByOIDWithHV(request.getSeccionTCAMBIARORDENPREGSpthisSeccion()));
        return seccionSrv.tCAMBIARORDENPREGSImplementation(request);
    }

    /*
     * Implements service TCAMBIARORDENPREGS
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse tCAMBIARORDENPREGSImplementation(
            com.keralty.aeconfiguraciones.viewmodel.seccion.siu.TCAMBIARORDENPREGSRequest request) throws ModelException, SystemException {
        ServiceResponse response = new ServiceResponse();
        Boolean seccionTCAMBIARORDENPREGSptSubir = request.getSeccionTCAMBIARORDENPREGSptSubir();
        Long seccionTCAMBIARORDENPREGSptOrdenDesde = request.getSeccionTCAMBIARORDENPREGSptOrdenDesde();
        Long seccionTCAMBIARORDENPREGSptOrdenHasta = request.getSeccionTCAMBIARORDENPREGSptOrdenHasta();
        Seccion seccionTCAMBIARORDENPREGSpthisSeccion = request.getSeccionTCAMBIARORDENPREGSpthisSeccionInstance();

        request.checkArguments();
        Seccion instance = seccionTCAMBIARORDENPREGSpthisSeccion;

        /* Action 1: IF (ptSubir = true) */
        boolean ifCondition1 = seccionTCAMBIARORDENPREGSptSubir.equals(true);
        if (ifCondition1) {
            for(Pregunta step110Entity : repository.assocOperator005(seccionTCAMBIARORDENPREGSpthisSeccion, seccionTCAMBIARORDENPREGSptOrdenDesde, seccionTCAMBIARORDENPREGSptOrdenHasta)) {
                com.keralty.aeconfiguraciones.viewmodel.pregunta.siu.CambiarOrdenRequest step10Request = new com.keralty.aeconfiguraciones.viewmodel.pregunta.siu.CambiarOrdenRequest();
                step10Request.setPreguntacambiarOrdenpthisPreguntaInstance(step110Entity);
                step10Request.setPreguntacambiarOrdenpNuevoOrden(step110Entity.getPreguntaNumOrden() + Long.valueOf(1));
                preguntaSrv.cambiarOrdenImplementation(step10Request);
            }
        } else {
            for(Pregunta step111Entity : repository.assocOperator006(seccionTCAMBIARORDENPREGSpthisSeccion, seccionTCAMBIARORDENPREGSptOrdenDesde, seccionTCAMBIARORDENPREGSptOrdenHasta)) {
                com.keralty.aeconfiguraciones.viewmodel.pregunta.siu.CambiarOrdenRequest step11Request = new com.keralty.aeconfiguraciones.viewmodel.pregunta.siu.CambiarOrdenRequest();
                step11Request.setPreguntacambiarOrdenpthisPreguntaInstance(step111Entity);
                step11Request.setPreguntacambiarOrdenpNuevoOrden(step111Entity.getPreguntaNumOrden() - Long.valueOf(1));
                preguntaSrv.cambiarOrdenImplementation(step11Request);
            }
        }

        response.setInstance(getByOID(instance.getOid()));
        response.setInstanceSuppInfo(instance.buildICSeccion());

        return response;
    }


    private void suppInfo4ICSeccion(String searchText, SQLSelect select) {
        try {
            SQLWhereDisjunction disjunction = new SQLWhereDisjunction();

            SQLWhereComparison comparison1 = new SQLWhereComparison(select);
            comparison1.setOperator("LIKE");
            comparison1.setFirstOperand(new SQLWhereOperandPathAttribute(SeccionConstants.ATTR_NAME_SECCIONNOMBRE, Constants.Type.STRING.getTypeName()));
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
        select.getOrderByPart().addItem(Constants.normalizePath(SeccionConstants.CLASS_NAME, SeccionConstants.ATTR_FIELD_SECCIONIDENTIFICADOR, false), true);
    }
    
    @Override
    protected AbstractAppEntity buildRelatedInstance(String relatedInstanceJsonOid, String relatedInstancePathFirstItem, String remainingPath) {
        AbstractAppEntity relatedInstance = null;
        if (relatedInstancePathFirstItem.isEmpty()) {
            relatedInstance = buildSeccionRelatedInstance(relatedInstanceJsonOid);
        }
        if (SeccionConstants.ROLE_NAME_PREGUNTAS.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildSeccionRelatedInstance4Pregunta(relatedInstanceJsonOid, remainingPath);
        }
        if (SeccionConstants.ROLE_NAME_CUESTIONARIO.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildSeccionRelatedInstance4CuestionarioMed(relatedInstanceJsonOid, remainingPath);
        }
        return relatedInstance;
    }
    
    private AbstractAppEntity buildSeccionRelatedInstance(String relatedInstanceJsonOid) {
    	ObjectMapper mapper = new ObjectMapper();
    	try {
    		return new Seccion(mapper.readValue(relatedInstanceJsonOid, SeccionOid.class));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            throw new SystemException(e.getMessage());
    	}
    }

    private AbstractAppEntity buildSeccionRelatedInstance4Pregunta(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new Pregunta(mapper.readValue(relatedInstanceJsonOid, PreguntaOid.class)): Pregunta.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            throw new SystemException(e.getMessage());
    	}
    }

    private AbstractAppEntity buildSeccionRelatedInstance4CuestionarioMed(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new CuestionarioMed(mapper.readValue(relatedInstanceJsonOid, CuestionarioMedOid.class)): CuestionarioMed.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            throw new SystemException(e.getMessage());
    	}
    }

    @Override
    protected void applyFilterByOid(String jsonOid, SQLSelect sql) {
        try {
            SeccionOid oid = new SeccionOid().fromJsonString(jsonOid);
            SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
            comparison1.setOperator("=");
            comparison1.setFirstOperand(new SQLWhereOperandPathAttribute(SeccionConstants.ATTR_FIELD_SECCIONIDENTIFICADOR, Constants.Type.AUTONUMERIC.getTypeName()));
            comparison1.setSecondOperand(new SQLWhereOperandLiteral(oid.getSeccionIdentificador(), Constants.Type.AUTONUMERIC.getTypeName()));
            sql.addComparison(comparison1);
        } catch (Exception e) {
            LoggerFactory.getLogger(SeccionService.class).error(e.getMessage());
            throw new SystemException(e.getMessage());
        }
    }

}
