package com.keralty.aeconfiguraciones.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.keralty.aeconfiguraciones.exception.InstanceExistsException;
import com.keralty.aeconfiguraciones.exception.MinCardinalityException;
import com.keralty.aeconfiguraciones.exception.ModelException;
import com.keralty.aeconfiguraciones.exception.PreconditionException;
import com.keralty.aeconfiguraciones.exception.SystemException;
import com.keralty.aeconfiguraciones.global.Constants;
import com.keralty.aeconfiguraciones.global.PreguntaConstants;
import com.keralty.aeconfiguraciones.global.STDFunctions;
import com.keralty.aeconfiguraciones.persistence.AbstractAppEntity;
import com.keralty.aeconfiguraciones.persistence.oid.PreguntaOid;
import com.keralty.aeconfiguraciones.persistence.oid.PreguntaPOid;
import com.keralty.aeconfiguraciones.persistence.oid.SeccionOid;
import com.keralty.aeconfiguraciones.persistence.oid.ValorOid;
import com.keralty.aeconfiguraciones.persistence.Pregunta;
import com.keralty.aeconfiguraciones.persistence.PreguntaP;
import com.keralty.aeconfiguraciones.persistence.Seccion;
import com.keralty.aeconfiguraciones.persistence.Valor;
import com.keralty.aeconfiguraciones.repository.PreguntaJpaRepository;
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
public class PreguntaService extends AbstractService {

    PreguntaJpaRepository repository;

    PreguntaService preguntaSrv;

    SeccionService seccionSrv;

    ValorService valorSrv;

    PreguntaPService preguntaPSrv;

    @Inject
    public PreguntaService(
            PreguntaService preguntaSrv,
            SeccionService seccionSrv,
            ValorService valorSrv,
            PreguntaPService preguntaPSrv,
            PreguntaJpaRepository repository) {
        this.preguntaSrv = preguntaSrv;
        this.seccionSrv = seccionSrv;
        this.valorSrv = valorSrv;
        this.preguntaPSrv = preguntaPSrv;
        this.repository = repository;
    }

    public List<Map<String, Object>> solveQuery(QueryRequest request) {
        return solveGenericQuery(request, PreguntaConstants.CLASS_NAME);
    }

    private List<Pregunta> solvePreguntaTypedQuery(QueryRequest request) {
        return repository.solveQuery(processQueryRequest(request, PreguntaConstants.CLASS_NAME), request.getPageSize(), request.getPageNumber());
    }
    
    private int solvePreguntaTypedCountQuery(QueryRequest request) {
        return repository.solveCountQuery(processCountQueryRequest(request, PreguntaConstants.CLASS_NAME));
    }

    /*
     * Returns the population of class Pregunta
     *
     * @return Population of class Pregunta
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<Pregunta> get() {
        List<Pregunta> results = new java.util.ArrayList<>();
        repository.findAll().forEach(results::add);
        return results;
    }
    /*
     * Returns the population of class Pregunta that matches certain search criteria
     *
     * @param queryRequest Search criteria
     *
     * @return Population of class Pregunta matching the search criteria
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<Pregunta> get(QueryRequest queryRequest) {
        return solvePreguntaTypedQuery(queryRequest);
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
            return new PopulationResponseViewModel(solvePreguntaTypedQuery(queryRequest).stream()
                    .map(instance -> buildDynamicObject(instance, queryRequest.getDisplaySetItems()))
                    .collect(Collectors.toList()), queryRequest.getPageSize() != 0 ? solvePreguntaTypedCountQuery(queryRequest) : 0);
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
        return new PopulationResponseViewModel(solvePreguntaTypedQuery(queryRequest).stream()
                .map(instance -> buildDynamicObject(instance, displaySetItems))
                .collect(Collectors.toList()));
    }

    /*
     * Returns an instance of Pregunta by ID
     *
     * @param preguntaNumero Identification field preguntaNumero
     *
     * @return Instance of Pregunta
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public Pregunta getById(Long preguntaNumero) {
        return getByOID(new PreguntaOid(preguntaNumero));
    }
    
    /*
     * Returns an instance of Pregunta by OID
     *
     * @param oid Object IDentifier
     *
     * @return Instance of Pregunta
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public Pregunta getByOID(PreguntaOid oid) {
        Optional<Pregunta> instance = getInstanceByOid(oid);
        return instance.isPresent() ? instance.get() : null;
    }
    
    /*
     * Returns an instance of Pregunta by OID applying horizontal visibility constraints
     *
     * @param oid Object IDentifier
     *
     * @return Instance of Pregunta
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public Pregunta getByOIDWithHV(PreguntaOid oid) {
        Optional<Pregunta> instance = getInstanceByOid(oid);
    	return instance.isPresent() && instance.get().checkHorizontalVisibility(getAgentInstance()) 
    		 ? instance.get() 
    		 : null;
    }
    
    private Optional<Pregunta> getInstanceByOid(PreguntaOid oid) {
        return oid != null && oid.getPreguntaNumero() != null
               ? repository.findById(oid.getPreguntaNumero())
               : Optional.empty();
    }

    /*
     * Returns an instance of Pregunta by OID in JSON format
     *
     * @param jsonOid Object IDentifier in JSON format
     *
     * @return Instance of Pregunta
     */
    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public Pregunta getByJsonOid(String jsonOid) {
        PreguntaOid oid = new PreguntaOid();
        oid.fromJsonString(jsonOid);
        return getByOIDWithHV(oid);
    }

    /*
     * Returns the list of instances of Pregunta related to a given instance through 
     * a given path
     *
     * @param relatedInstance Related instance
     *
     * @param relatedInstancePath Related instance path
     *
     * @return List of instances of Pregunta
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<Pregunta> getByRelated(AbstractAppEntity relatedInstance, String relatedInstancePath) {
        QueryRequest queryRequest = new QueryRequest(new FilterRequest(Constants.FILTER_BY_RELATED,
                Arrays.asList(new FilterVariableRequest(Constants.FILTER_BY_RELATED_FVAR_PATH, relatedInstancePath),
                        new FilterVariableRequest(Constants.FILTER_BY_RELATED_FVAR_OID, relatedInstance.getOid().toJson()))));
        return solvePreguntaTypedQuery(queryRequest);
    }

    /*
     * Returns the list of instances of Pregunta related to a given instance through
     * a given path
     *
     * @param relatedInstance Related instance
     *
     * @param relatedInstancePath Related instance path
     *
     * @return List of instances of Pregunta
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<Pregunta> getInstancesByRelated(AbstractAppEntity relatedInstance, String relatedInstancePath) {
        return getByRelated(relatedInstance, relatedInstancePath);
    }

    /*
     * Returns a set of displayset items for an instance of Pregunta
     *
     * @param oid Object IDentifier
     *
     * @param displaySetItems List of comma-separated displayset items
     *
     * @return Set of displayset items for an instance of Pregunta
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public InstanceResponseViewModel getPreguntaDynamic(PreguntaOid oid, String displaySetItems) {
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
    public String getSuppInfo(PreguntaOid oid, String dsName) {
        Pregunta instance = getByOIDWithHV(oid);
        return (instance != null ? getSuppInfo(instance, dsName): "");
    }

    private String getSuppInfo(Pregunta instance, String dsName) {
        if (instance == null || instance.isNull()) return "";
        return dsName.isEmpty() ? instance.getOid().toJson() : "";
    }




    // IIU Methods

    /*
     * Returns a viewmodel for class Pregunta with displayset DSPreguntaIIU for the instance with a given OID
     * @param oid Class oid
     * @return Viewmodel for class class Pregunta with displayset DSPreguntaIIU
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public com.keralty.aeconfiguraciones.viewmodel.pregunta.ds.DSPreguntaIIU iIUPregunta(PreguntaOid oid) {
        return new com.keralty.aeconfiguraciones.viewmodel.pregunta.ds.DSPreguntaIIU(getByOIDWithHV(oid));
    }


    /*
     * Solves the query needed for PIUPregunta
     *
     * @param queryRequest Query request
     * @return List of viewmodels for Pregunta using displayset AutoDS that 
     * match the search criteria
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<com.keralty.aeconfiguraciones.viewmodel.pregunta.ds.AutoDS> piuPIUPregunta(QueryRequest queryRequest) {
        return solvePreguntaTypedQuery(queryRequest).stream()
                .map(com.keralty.aeconfiguraciones.viewmodel.pregunta.ds.AutoDS::new)
                .collect(Collectors.toList());
    }

    /*
     * Solves the COUNT query needed for PIUPregunta
     *
     * @param queryRequest Query request
     * @returns Count of instances for PIUPregunta
     */
    public int getCount4PIUPregunta(QueryRequest queryRequest) {
        return solvePreguntaTypedQuery(queryRequest).size();
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
            com.keralty.aeconfiguraciones.viewmodel.pregunta.siu.CrearRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = preguntaSrv.crearTxn(request);
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
            com.keralty.aeconfiguraciones.viewmodel.pregunta.siu.CrearRequest request) throws ModelException {
        request.setPreguntacrearpagrSeccionInstance(seccionSrv.getByOIDWithHV(request.getPreguntacrearpagrSeccion()));
        return preguntaSrv.crearImplementation(request);
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
            com.keralty.aeconfiguraciones.viewmodel.pregunta.siu.CrearRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Seccion preguntacrearpagrSeccion = request.getPreguntacrearpagrSeccionInstance();
        Long preguntacrearpatrNumero = request.getPreguntacrearpatrNumero();
        Long preguntacrearpatrNumOrden = request.getPreguntacrearpatrNumOrden();
        String preguntacrearpatrDescripcion = request.getPreguntacrearpatrDescripcion();
        Boolean preguntacrearpatrValorSNObliga = request.getPreguntacrearpatrValorSNObliga();
        Boolean preguntacrearpatrObsOblig = request.getPreguntacrearpatrObsOblig();
        Boolean preguntacrearpatrEsNumero = request.getPreguntacrearpatrEsNumero();
        Double preguntacrearpatrValorMax = request.getPreguntacrearpatrValorMax();
        Double preguntacrearpatrValorMin = request.getPreguntacrearpatrValorMin();
        Boolean preguntacrearpatrSoloHombres = request.getPreguntacrearpatrSoloHombres();
        Boolean preguntacrearpatrSoloMujeres = request.getPreguntacrearpatrSoloMujeres();
        Boolean preguntacrearpatrRespMultiple = request.getPreguntacrearpatrRespMultiple();
        Boolean preguntacrearpatrSeEnviaAlCORE = request.getPreguntacrearpatrSeEnviaAlCORE();
        Long preguntacrearpatrCondicionEdad = request.getPreguntacrearpatrCondicionEdad();
        Long preguntacrearpatrCondicionEdadAnyos = request.getPreguntacrearpatrCondicionEdadAnyos();

        request.checkArguments();
        Pregunta instance = new Pregunta();
        instance.add2Seccion(preguntacrearpagrSeccion);

        instance.setPreguntaNumero(preguntacrearpatrNumero);
        instance.setPreguntaNumOrden(preguntacrearpatrNumOrden);
        instance.setPreguntaDescripcion(preguntacrearpatrDescripcion);
        instance.setPreguntaValorSNObliga(preguntacrearpatrValorSNObliga);
        instance.setPreguntaObsOblig(preguntacrearpatrObsOblig);
        instance.setPreguntaEsNumero(preguntacrearpatrEsNumero);
        instance.setPreguntaValorMax(preguntacrearpatrValorMax);
        instance.setPreguntaValorMin(preguntacrearpatrValorMin);
        instance.setPreguntaSoloHombres(preguntacrearpatrSoloHombres);
        instance.setPreguntaSoloMujeres(preguntacrearpatrSoloMujeres);
        instance.setPreguntaRespMultiple(preguntacrearpatrRespMultiple);
        instance.setPreguntaSeEnviaAlCORE(preguntacrearpatrSeEnviaAlCORE);
        instance.setPreguntaCondicionEdad(preguntacrearpatrCondicionEdad);
        instance.setPreguntaCondicionEdadAnyos(preguntacrearpatrCondicionEdadAnyos);
        if (preguntaSrv.getByOID(instance.getOid()) != null) {
            throw new InstanceExistsException(PreguntaConstants.CLASS_ALIAS);
        }

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
            com.keralty.aeconfiguraciones.viewmodel.pregunta.siu.EliminarRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = preguntaSrv.eliminarTxn(request);
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
            com.keralty.aeconfiguraciones.viewmodel.pregunta.siu.EliminarRequest request) throws ModelException {
        request.setPreguntaeliminarpthisPreguntaInstance(preguntaSrv.getByOIDWithHV(request.getPreguntaeliminarpthisPregunta()));
        return preguntaSrv.eliminarImplementation(request);
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
            com.keralty.aeconfiguraciones.viewmodel.pregunta.siu.EliminarRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Pregunta preguntaeliminarpthisPregunta = request.getPreguntaeliminarpthisPreguntaInstance();

        request.checkArguments();
        Pregunta instance = preguntaeliminarpthisPregunta;
        if(preguntaeliminarpthisPregunta.getPreguntasPersona().size() > 0) {
            throw new MinCardinalityException(PreguntaConstants.CLASS_ALIAS, PreguntaConstants.ROLE_ALIAS_PREGUNTASPERSONA);
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
            com.keralty.aeconfiguraciones.viewmodel.pregunta.siu.ModificarRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = preguntaSrv.modificarTxn(request);
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
            com.keralty.aeconfiguraciones.viewmodel.pregunta.siu.ModificarRequest request) throws ModelException {
        request.setPreguntamodificarpthisPreguntaInstance(preguntaSrv.getByOIDWithHV(request.getPreguntamodificarpthisPregunta()));
        return preguntaSrv.modificarImplementation(request);
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
            com.keralty.aeconfiguraciones.viewmodel.pregunta.siu.ModificarRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Pregunta preguntamodificarpthisPregunta = request.getPreguntamodificarpthisPreguntaInstance();
        String preguntamodificarpDescripcion = request.getPreguntamodificarpDescripcion();
        Boolean preguntamodificarpValorSNObliga = request.getPreguntamodificarpValorSNObliga();
        Boolean preguntamodificarpObsOblig = request.getPreguntamodificarpObsOblig();
        Boolean preguntamodificarpEsNumero = request.getPreguntamodificarpEsNumero();
        Double preguntamodificarpValorMax = request.getPreguntamodificarpValorMax();
        Double preguntamodificarpValorMin = request.getPreguntamodificarpValorMin();
        Boolean preguntamodificarpSoloHombres = request.getPreguntamodificarpSoloHombres();
        Boolean preguntamodificarpSoloMujeres = request.getPreguntamodificarpSoloMujeres();
        Boolean preguntamodificarpRespMultiple = request.getPreguntamodificarpRespMultiple();
        Boolean preguntamodificarpSeEnviaAlCORE = request.getPreguntamodificarpSeEnviaAlCORE();
        Long preguntamodificarpCondicionEdad = request.getPreguntamodificarpCondicionEdad();
        Long preguntamodificarpCondicionEdadAnyos = request.getPreguntamodificarpCondicionEdadAnyos();

        request.checkArguments();
        Pregunta instance = preguntamodificarpthisPregunta;
        instance.setPreguntaDescripcion(preguntamodificarpDescripcion);
        instance.setPreguntaValorSNObliga(preguntamodificarpValorSNObliga);
        instance.setPreguntaObsOblig(preguntamodificarpObsOblig);
        instance.setPreguntaEsNumero(preguntamodificarpEsNumero);
        instance.setPreguntaValorMax(preguntamodificarpValorMax);
        instance.setPreguntaValorMin(preguntamodificarpValorMin);
        instance.setPreguntaSoloHombres(preguntamodificarpSoloHombres);
        instance.setPreguntaSoloMujeres(preguntamodificarpSoloMujeres);
        instance.setPreguntaRespMultiple(preguntamodificarpRespMultiple);
        instance.setPreguntaSeEnviaAlCORE(preguntamodificarpSeEnviaAlCORE);
        instance.setPreguntaCondicionEdad(preguntamodificarpCondicionEdad);
        instance.setPreguntaCondicionEdadAnyos(preguntamodificarpCondicionEdadAnyos);


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
            com.keralty.aeconfiguraciones.viewmodel.pregunta.siu.CambiarOrdenRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = preguntaSrv.cambiarOrdenTxn(request);
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
            com.keralty.aeconfiguraciones.viewmodel.pregunta.siu.CambiarOrdenRequest request) throws ModelException {
        request.setPreguntacambiarOrdenpthisPreguntaInstance(preguntaSrv.getByOIDWithHV(request.getPreguntacambiarOrdenpthisPregunta()));
        return preguntaSrv.cambiarOrdenImplementation(request);
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
            com.keralty.aeconfiguraciones.viewmodel.pregunta.siu.CambiarOrdenRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Pregunta preguntacambiarOrdenpthisPregunta = request.getPreguntacambiarOrdenpthisPreguntaInstance();
        Long preguntacambiarOrdenpNuevoOrden = request.getPreguntacambiarOrdenpNuevoOrden();

        request.checkArguments();
        Pregunta instance = preguntacambiarOrdenpthisPregunta;
        instance.setPreguntaNumOrden(preguntacambiarOrdenpNuevoOrden);


        response.setInstance(repository.save(instance));

        return response;
    }

    // Service: InsValorPregunta

    /*
     * Entry point for service InsValorPregunta
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse insValorPregunta(
            com.keralty.aeconfiguraciones.viewmodel.pregunta.siu.InsValorPreguntaRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = preguntaSrv.insValorPreguntaTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service InsValorPregunta
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse insValorPreguntaTxn(
            com.keralty.aeconfiguraciones.viewmodel.pregunta.siu.InsValorPreguntaRequest request) throws ModelException {
        request.setPreguntaInsValorPreguntapthisPreguntaInstance(preguntaSrv.getByOIDWithHV(request.getPreguntaInsValorPreguntapthisPregunta()));
        request.setPreguntaInsValorPreguntapevcValorInstance(valorSrv.getByOIDWithHV(request.getPreguntaInsValorPreguntapevcValor()));
        return preguntaSrv.insValorPreguntaImplementation(request, true);
    }

    /*
     * Implements service InsValorPregunta
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse insValorPreguntaImplementation(
            com.keralty.aeconfiguraciones.viewmodel.pregunta.siu.InsValorPreguntaRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Pregunta preguntaInsValorPreguntapthisPregunta = request.getPreguntaInsValorPreguntapthisPreguntaInstance();
        Valor preguntaInsValorPreguntapevcValor = request.getPreguntaInsValorPreguntapevcValorInstance();

        request.checkArguments();
        Pregunta instance = preguntaInsValorPreguntapthisPregunta;

        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.valor.siu.InsValorPreguntaRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.valor.siu.InsValorPreguntaRequest();
            relatedRequest.setValorInsValorPreguntapthisValorInstance(preguntaInsValorPreguntapevcValor);
            relatedRequest.setValorInsValorPreguntapevcPreguntaInstance(preguntaInsValorPreguntapthisPregunta);
            valorSrv.insValorPreguntaImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));

        return response;
    }

    // Service: DelValorPregunta

    /*
     * Entry point for service DelValorPregunta
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse delValorPregunta(
            com.keralty.aeconfiguraciones.viewmodel.pregunta.siu.DelValorPreguntaRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = preguntaSrv.delValorPreguntaTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service DelValorPregunta
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse delValorPreguntaTxn(
            com.keralty.aeconfiguraciones.viewmodel.pregunta.siu.DelValorPreguntaRequest request) throws ModelException {
        request.setPreguntaDelValorPreguntapthisPreguntaInstance(preguntaSrv.getByOIDWithHV(request.getPreguntaDelValorPreguntapthisPregunta()));
        request.setPreguntaDelValorPreguntapevcValorInstance(valorSrv.getByOIDWithHV(new ValorOid().fromJsonString(request.getPreguntaDelValorPreguntapevcValor())));
        return preguntaSrv.delValorPreguntaImplementation(request, true);
    }

    /*
     * Implements service DelValorPregunta
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse delValorPreguntaImplementation(
            com.keralty.aeconfiguraciones.viewmodel.pregunta.siu.DelValorPreguntaRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Pregunta preguntaDelValorPreguntapthisPregunta = request.getPreguntaDelValorPreguntapthisPreguntaInstance();
        Valor preguntaDelValorPreguntapevcValor = request.getPreguntaDelValorPreguntapevcValorInstance();

        request.checkArguments();
        Pregunta instance = preguntaDelValorPreguntapthisPregunta;

        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.valor.siu.DelValorPreguntaRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.valor.siu.DelValorPreguntaRequest();
            relatedRequest.setValorDelValorPreguntapthisValorInstance(preguntaDelValorPreguntapevcValor);
            relatedRequest.setValorDelValorPreguntapevcPreguntaInstance(preguntaDelValorPreguntapthisPregunta);
            valorSrv.delValorPreguntaImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));

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
            com.keralty.aeconfiguraciones.viewmodel.pregunta.siu.CambSeccionPreguntaRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = preguntaSrv.cambSeccionPreguntaTxn(request);
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
            com.keralty.aeconfiguraciones.viewmodel.pregunta.siu.CambSeccionPreguntaRequest request) throws ModelException {
        request.setPreguntaCambSeccionPreguntapthisPreguntaInstance(preguntaSrv.getByOIDWithHV(request.getPreguntaCambSeccionPreguntapthisPregunta()));
        request.setPreguntaCambSeccionPreguntapevcSeccionInstance(seccionSrv.getByOIDWithHV(request.getPreguntaCambSeccionPreguntapevcSeccion()));
        return preguntaSrv.cambSeccionPreguntaImplementation(request, true);
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
            com.keralty.aeconfiguraciones.viewmodel.pregunta.siu.CambSeccionPreguntaRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Pregunta preguntaCambSeccionPreguntapthisPregunta = request.getPreguntaCambSeccionPreguntapthisPreguntaInstance();
        Seccion preguntaCambSeccionPreguntapevcSeccion = request.getPreguntaCambSeccionPreguntapevcSeccionInstance();

        request.checkArguments();
        Pregunta instance = preguntaCambSeccionPreguntapthisPregunta;

        instance.add2Seccion(preguntaCambSeccionPreguntapevcSeccion);
        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.seccion.siu.CambSeccionPreguntaRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.seccion.siu.CambSeccionPreguntaRequest();
            relatedRequest.setSeccionCambSeccionPreguntapthisSeccionInstance(preguntaCambSeccionPreguntapevcSeccion);
            relatedRequest.setSeccionCambSeccionPreguntapevcPreguntaInstance(preguntaCambSeccionPreguntapthisPregunta);
            seccionSrv.cambSeccionPreguntaImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));

        return response;
    }

    // Service: InsPreguntaAPersona

    /*
     * Entry point for service InsPreguntaAPersona
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse insPreguntaAPersona(
            com.keralty.aeconfiguraciones.viewmodel.pregunta.siu.InsPreguntaAPersonaRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = preguntaSrv.insPreguntaAPersonaTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service InsPreguntaAPersona
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse insPreguntaAPersonaTxn(
            com.keralty.aeconfiguraciones.viewmodel.pregunta.siu.InsPreguntaAPersonaRequest request) throws ModelException {
        request.setPreguntaInsPreguntaAPersonapthisPreguntaInstance(preguntaSrv.getByOIDWithHV(request.getPreguntaInsPreguntaAPersonapthisPregunta()));
        request.setPreguntaInsPreguntaAPersonapevcPreguntaPInstance(preguntaPSrv.getByOIDWithHV(request.getPreguntaInsPreguntaAPersonapevcPreguntaP()));
        return preguntaSrv.insPreguntaAPersonaImplementation(request, true);
    }

    /*
     * Implements service InsPreguntaAPersona
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse insPreguntaAPersonaImplementation(
            com.keralty.aeconfiguraciones.viewmodel.pregunta.siu.InsPreguntaAPersonaRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Pregunta preguntaInsPreguntaAPersonapthisPregunta = request.getPreguntaInsPreguntaAPersonapthisPreguntaInstance();
        PreguntaP preguntaInsPreguntaAPersonapevcPreguntaP = request.getPreguntaInsPreguntaAPersonapevcPreguntaPInstance();

        request.checkArguments();
        Pregunta instance = preguntaInsPreguntaAPersonapthisPregunta;

        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.preguntap.siu.InsPreguntaAPersonaRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.preguntap.siu.InsPreguntaAPersonaRequest();
            relatedRequest.setPreguntaPInsPreguntaAPersonapthisPreguntaPInstance(preguntaInsPreguntaAPersonapevcPreguntaP);
            relatedRequest.setPreguntaPInsPreguntaAPersonapevcPreguntaInstance(preguntaInsPreguntaAPersonapthisPregunta);
            preguntaPSrv.insPreguntaAPersonaImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));

        return response;
    }

    // Service: DelPreguntaDePersona

    /*
     * Entry point for service DelPreguntaDePersona
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse delPreguntaDePersona(
            com.keralty.aeconfiguraciones.viewmodel.pregunta.siu.DelPreguntaDePersonaRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = preguntaSrv.delPreguntaDePersonaTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service DelPreguntaDePersona
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse delPreguntaDePersonaTxn(
            com.keralty.aeconfiguraciones.viewmodel.pregunta.siu.DelPreguntaDePersonaRequest request) throws ModelException {
        request.setPreguntaDelPreguntaDePersonapthisPreguntaInstance(preguntaSrv.getByOIDWithHV(request.getPreguntaDelPreguntaDePersonapthisPregunta()));
        request.setPreguntaDelPreguntaDePersonapevcPreguntaPInstance(preguntaPSrv.getByOIDWithHV(request.getPreguntaDelPreguntaDePersonapevcPreguntaP()));
        return preguntaSrv.delPreguntaDePersonaImplementation(request, true);
    }

    /*
     * Implements service DelPreguntaDePersona
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse delPreguntaDePersonaImplementation(
            com.keralty.aeconfiguraciones.viewmodel.pregunta.siu.DelPreguntaDePersonaRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Pregunta preguntaDelPreguntaDePersonapthisPregunta = request.getPreguntaDelPreguntaDePersonapthisPreguntaInstance();
        PreguntaP preguntaDelPreguntaDePersonapevcPreguntaP = request.getPreguntaDelPreguntaDePersonapevcPreguntaPInstance();

        request.checkArguments();
        Pregunta instance = preguntaDelPreguntaDePersonapthisPregunta;

        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.preguntap.siu.DelPreguntaDePersonaRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.preguntap.siu.DelPreguntaDePersonaRequest();
            relatedRequest.setPreguntaPDelPreguntaDePersonapthisPreguntaPInstance(preguntaDelPreguntaDePersonapevcPreguntaP);
            relatedRequest.setPreguntaPDelPreguntaDePersonapevcPreguntaInstance(preguntaDelPreguntaDePersonapthisPregunta);
            preguntaPSrv.delPreguntaDePersonaImplementation(relatedRequest, false);
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
            com.keralty.aeconfiguraciones.viewmodel.pregunta.siu.TCREARRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = preguntaSrv.tCREARTxn(request);
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
            com.keralty.aeconfiguraciones.viewmodel.pregunta.siu.TCREARRequest request) throws ModelException, SystemException {
        request.setPreguntaTCREARptpagrSeccionInstance(seccionSrv.getByOIDWithHV(new SeccionOid().fromJsonString(request.getPreguntaTCREARptpagrSeccion())));
        return preguntaSrv.tCREARImplementation(request);
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
            com.keralty.aeconfiguraciones.viewmodel.pregunta.siu.TCREARRequest request) throws ModelException, SystemException {
        ServiceResponse response = new ServiceResponse();
        Seccion preguntaTCREARptpagrSeccion = request.getPreguntaTCREARptpagrSeccionInstance();
        Long preguntaTCREARptpatrNumero = request.getPreguntaTCREARptpatrNumero();
        String preguntaTCREARptpatrDescripcion = request.getPreguntaTCREARptpatrDescripcion();
        Boolean preguntaTCREARptpatrValorSNObliga = request.getPreguntaTCREARptpatrValorSNObliga();
        Boolean preguntaTCREARptpatrObsOblig = request.getPreguntaTCREARptpatrObsOblig();
        Boolean preguntaTCREARptpatrEsNumero = request.getPreguntaTCREARptpatrEsNumero();
        Double preguntaTCREARptpatrValorMax = request.getPreguntaTCREARptpatrValorMax();
        Double preguntaTCREARptpatrValorMin = request.getPreguntaTCREARptpatrValorMin();
        Boolean preguntaTCREARptpatrSoloHombres = request.getPreguntaTCREARptpatrSoloHombres();
        Boolean preguntaTCREARptpatrSoloMujeres = request.getPreguntaTCREARptpatrSoloMujeres();
        Boolean preguntaTCREARptpatrRespMultiple = request.getPreguntaTCREARptpatrRespMultiple();
        Boolean preguntaTCREARptpatrSeEnviaAlCORE = request.getPreguntaTCREARptpatrSeEnviaAlCORE();
        Long preguntaTCREARptpatrCondicionEdad = request.getPreguntaTCREARptpatrCondicionEdad();
        Long preguntaTCREARptpatrCondicionEdadAnyos = request.getPreguntaTCREARptpatrCondicionEdadAnyos();
        Long preguntaTCREARptpatrNumOrden = request.getPreguntaTCREARptpatrNumOrden();

        request.checkArguments();

        tCREARPreconditions(request);
        Pregunta instance;

        com.keralty.aeconfiguraciones.viewmodel.pregunta.siu.CrearRequest step1Request = new com.keralty.aeconfiguraciones.viewmodel.pregunta.siu.CrearRequest();
        step1Request.setPreguntacrearpagrSeccionInstance(preguntaTCREARptpagrSeccion);
        step1Request.setPreguntacrearpatrNumero(preguntaTCREARptpatrNumero);
        step1Request.setPreguntacrearpatrNumOrden(Long.valueOf(0));
        step1Request.setPreguntacrearpatrDescripcion(preguntaTCREARptpatrDescripcion);
        step1Request.setPreguntacrearpatrValorSNObliga(preguntaTCREARptpatrValorSNObliga);
        step1Request.setPreguntacrearpatrObsOblig(preguntaTCREARptpatrObsOblig);
        step1Request.setPreguntacrearpatrEsNumero(preguntaTCREARptpatrEsNumero);
        step1Request.setPreguntacrearpatrValorMax(preguntaTCREARptpatrValorMax);
        step1Request.setPreguntacrearpatrValorMin(preguntaTCREARptpatrValorMin);
        step1Request.setPreguntacrearpatrSoloHombres(preguntaTCREARptpatrSoloHombres);
        step1Request.setPreguntacrearpatrSoloMujeres(preguntaTCREARptpatrSoloMujeres);
        step1Request.setPreguntacrearpatrRespMultiple(preguntaTCREARptpatrRespMultiple);
        step1Request.setPreguntacrearpatrSeEnviaAlCORE(preguntaTCREARptpatrSeEnviaAlCORE);
        step1Request.setPreguntacrearpatrCondicionEdad(preguntaTCREARptpatrCondicionEdad);
        step1Request.setPreguntacrearpatrCondicionEdadAnyos(preguntaTCREARptpatrCondicionEdadAnyos);
        instance = (Pregunta) preguntaSrv.crearImplementation(step1Request).getInstance();

        com.keralty.aeconfiguraciones.viewmodel.seccion.siu.TCAMBIARORDENPREGSRequest step2Request = new com.keralty.aeconfiguraciones.viewmodel.seccion.siu.TCAMBIARORDENPREGSRequest();
        step2Request.setSeccionTCAMBIARORDENPREGSpthisSeccionInstance(instance.getSeccion());
        step2Request.setSeccionTCAMBIARORDENPREGSptOrdenDesde(preguntaTCREARptpatrNumOrden);
        step2Request.setSeccionTCAMBIARORDENPREGSptSubir(Boolean.TRUE);
        step2Request.setSeccionTCAMBIARORDENPREGSptOrdenHasta(Long.valueOf(9999));
        seccionSrv.tCAMBIARORDENPREGSImplementation(step2Request);

        com.keralty.aeconfiguraciones.viewmodel.pregunta.siu.CambiarOrdenRequest step3Request = new com.keralty.aeconfiguraciones.viewmodel.pregunta.siu.CambiarOrdenRequest();
        step3Request.setPreguntacambiarOrdenpthisPreguntaInstance(instance);
        step3Request.setPreguntacambiarOrdenpNuevoOrden(preguntaTCREARptpatrNumOrden);
        preguntaSrv.cambiarOrdenImplementation(step3Request);

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
    public ServiceResponse tCREARCheckPreconditions(com.keralty.aeconfiguraciones.viewmodel.pregunta.siu.TCREARRequest request) {
        ServiceResponse response = new ServiceResponse();
        request.setPreguntaTCREARptpagrSeccionInstance(seccionSrv.getByOIDWithHV(new SeccionOid().fromJsonString(request.getPreguntaTCREARptpagrSeccion())));
        try {
            if("Clas_1431633788928040Pre_1".equals(request.getPreconditionId()))
                checkClas1431633788928040Pre1(request);
            if("Clas_1431633788928040Pre_3".equals(request.getPreconditionId()))
                checkClas1431633788928040Pre3(request);
            if("Clas_1431633788928040Pre_4".equals(request.getPreconditionId()))
                checkClas1431633788928040Pre4(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    private void tCREARPreconditions(
            com.keralty.aeconfiguraciones.viewmodel.pregunta.siu.TCREARRequest request) 
            throws ModelException {
        checkClas1431633788928040Pre1(request);
        checkClas1431633788928040Pre3(request);
        checkClas1431633788928040Pre4(request);
    }

    private void checkClas1431633788928040Pre1 (
            com.keralty.aeconfiguraciones.viewmodel.pregunta.siu.TCREARRequest request) 
            throws ModelException {
        // ptpatrNumOrden >= 1

        Long preguntaTCREARptpatrNumOrden = request.getPreguntaTCREARptpatrNumOrden();
        boolean preconditionHolds = preguntaTCREARptpatrNumOrden >= Long.valueOf(1);
        if (!preconditionHolds) throw new PreconditionException("El número de orden tiene que ser mayor o igual a 1");
    }

    private void checkClas1431633788928040Pre3 (
            com.keralty.aeconfiguraciones.viewmodel.pregunta.siu.TCREARRequest request) 
            throws ModelException {
        // EXIST( ptpagrSeccion.Cuestionario.Secciones.Preguntas ) WHERE (ptpagrSeccion.Cuestionario.Secciones.Preguntas.Numero = ptpatrNumero) = false

        Seccion preguntaTCREARptpagrSeccion = request.getPreguntaTCREARptpagrSeccionInstance();
        Long preguntaTCREARptpatrNumero = request.getPreguntaTCREARptpatrNumero();
        try {
            boolean preconditionHolds = repository.assocOperator000(preguntaTCREARptpagrSeccion, preguntaTCREARptpatrNumero).equals(false);
            if (!preconditionHolds) throw new PreconditionException("Ya existe una pregunta con número " + STDFunctions.intToStrFun(preguntaTCREARptpatrNumero));
        } catch (SystemException e) {
            LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
        }
    }

    private void checkClas1431633788928040Pre4 (
            com.keralty.aeconfiguraciones.viewmodel.pregunta.siu.TCREARRequest request) 
            throws ModelException {
        // ptpatrCondicionEdad = 0 OR ( ptpatrCondicionEdadAnyos <> NULL AND ptpatrCondicionEdadAnyos >= 0 )

        Long preguntaTCREARptpatrCondicionEdad = request.getPreguntaTCREARptpatrCondicionEdad();
        Long preguntaTCREARptpatrCondicionEdadAnyos = request.getPreguntaTCREARptpatrCondicionEdadAnyos();
        boolean preconditionHolds = (preguntaTCREARptpatrCondicionEdad).equals(Long.valueOf(0)) || (preguntaTCREARptpatrCondicionEdadAnyos != null && preguntaTCREARptpatrCondicionEdadAnyos >= Long.valueOf(0));
        if (!preconditionHolds) throw new PreconditionException("Si se condiciona la edad, los años deben ser igual o mayor a cero");
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
            com.keralty.aeconfiguraciones.viewmodel.pregunta.siu.TMODIFICARRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = preguntaSrv.tMODIFICARTxn(request);
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
            com.keralty.aeconfiguraciones.viewmodel.pregunta.siu.TMODIFICARRequest request) throws ModelException, SystemException {
        request.setPreguntaTMODIFICARpthisPreguntaInstance(preguntaSrv.getByOIDWithHV(request.getPreguntaTMODIFICARpthisPregunta()));
        request.setPreguntaTMODIFICARptpevcSeccionInstance(seccionSrv.getByOIDWithHV(new SeccionOid().fromJsonString(request.getPreguntaTMODIFICARptpevcSeccion())));
        return preguntaSrv.tMODIFICARImplementation(request);
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
            com.keralty.aeconfiguraciones.viewmodel.pregunta.siu.TMODIFICARRequest request) throws ModelException, SystemException {
        ServiceResponse response = new ServiceResponse();
        String preguntaTMODIFICARptpDescripcion = request.getPreguntaTMODIFICARptpDescripcion();
        Boolean preguntaTMODIFICARptpValorSNObliga = request.getPreguntaTMODIFICARptpValorSNObliga();
        Boolean preguntaTMODIFICARptpObsOblig = request.getPreguntaTMODIFICARptpObsOblig();
        Boolean preguntaTMODIFICARptpEsNumero = request.getPreguntaTMODIFICARptpEsNumero();
        Double preguntaTMODIFICARptpValorMax = request.getPreguntaTMODIFICARptpValorMax();
        Double preguntaTMODIFICARptpValorMin = request.getPreguntaTMODIFICARptpValorMin();
        Boolean preguntaTMODIFICARptpSoloHombres = request.getPreguntaTMODIFICARptpSoloHombres();
        Boolean preguntaTMODIFICARptpSoloMujeres = request.getPreguntaTMODIFICARptpSoloMujeres();
        Boolean preguntaTMODIFICARptpRespMultiple = request.getPreguntaTMODIFICARptpRespMultiple();
        Boolean preguntaTMODIFICARptpSeEnviaAlCORE = request.getPreguntaTMODIFICARptpSeEnviaAlCORE();
        Long preguntaTMODIFICARptpCondicionEdad = request.getPreguntaTMODIFICARptpCondicionEdad();
        Long preguntaTMODIFICARptpCondicionEdadAnyos = request.getPreguntaTMODIFICARptpCondicionEdadAnyos();
        Seccion preguntaTMODIFICARptpevcSeccion = request.getPreguntaTMODIFICARptpevcSeccionInstance();
        Long preguntaTMODIFICARptpNuevoOrden = request.getPreguntaTMODIFICARptpNuevoOrden();
        Pregunta preguntaTMODIFICARpthisPregunta = request.getPreguntaTMODIFICARpthisPreguntaInstance();

        request.checkArguments();

        tMODIFICARPreconditions(request);
        Pregunta instance = preguntaTMODIFICARpthisPregunta;

        com.keralty.aeconfiguraciones.viewmodel.pregunta.siu.ModificarRequest step1Request = new com.keralty.aeconfiguraciones.viewmodel.pregunta.siu.ModificarRequest();
        step1Request.setPreguntamodificarpthisPreguntaInstance(preguntaTMODIFICARpthisPregunta);
        step1Request.setPreguntamodificarpDescripcion(preguntaTMODIFICARptpDescripcion);
        step1Request.setPreguntamodificarpValorSNObliga(preguntaTMODIFICARptpValorSNObliga);
        step1Request.setPreguntamodificarpObsOblig(preguntaTMODIFICARptpObsOblig);
        step1Request.setPreguntamodificarpEsNumero(preguntaTMODIFICARptpEsNumero);
        step1Request.setPreguntamodificarpValorMax(preguntaTMODIFICARptpValorMax);
        step1Request.setPreguntamodificarpValorMin(preguntaTMODIFICARptpValorMin);
        step1Request.setPreguntamodificarpSoloHombres(preguntaTMODIFICARptpSoloHombres);
        step1Request.setPreguntamodificarpSoloMujeres(preguntaTMODIFICARptpSoloMujeres);
        step1Request.setPreguntamodificarpRespMultiple(preguntaTMODIFICARptpRespMultiple);
        step1Request.setPreguntamodificarpSeEnviaAlCORE(preguntaTMODIFICARptpSeEnviaAlCORE);
        step1Request.setPreguntamodificarpCondicionEdad(preguntaTMODIFICARptpCondicionEdad);
        step1Request.setPreguntamodificarpCondicionEdadAnyos(preguntaTMODIFICARptpCondicionEdadAnyos);
        preguntaSrv.modificarImplementation(step1Request);

        /* Action 2: IF (ptpevcSeccion <> Seccion) */
        boolean ifCondition2 = !(preguntaTMODIFICARptpevcSeccion.equalsObject(preguntaTMODIFICARpthisPregunta.getSeccion()));
        if (ifCondition2) {
            com.keralty.aeconfiguraciones.viewmodel.seccion.siu.TCAMBIARORDENPREGSRequest step20Request = new com.keralty.aeconfiguraciones.viewmodel.seccion.siu.TCAMBIARORDENPREGSRequest();
            step20Request.setSeccionTCAMBIARORDENPREGSpthisSeccionInstance(preguntaTMODIFICARpthisPregunta.getSeccion());
            step20Request.setSeccionTCAMBIARORDENPREGSptOrdenDesde(preguntaTMODIFICARpthisPregunta.getPreguntaNumOrden() + Long.valueOf(1));
            step20Request.setSeccionTCAMBIARORDENPREGSptSubir(Boolean.FALSE);
            step20Request.setSeccionTCAMBIARORDENPREGSptOrdenHasta(Long.valueOf(9999));
            seccionSrv.tCAMBIARORDENPREGSImplementation(step20Request);
            com.keralty.aeconfiguraciones.viewmodel.pregunta.siu.CambSeccionPreguntaRequest step21Request = new com.keralty.aeconfiguraciones.viewmodel.pregunta.siu.CambSeccionPreguntaRequest();
            step21Request.setPreguntaCambSeccionPreguntapthisPreguntaInstance(preguntaTMODIFICARpthisPregunta);
            step21Request.setPreguntaCambSeccionPreguntapevcSeccionInstance(preguntaTMODIFICARptpevcSeccion);
            preguntaSrv.cambSeccionPreguntaImplementation(step21Request, true);
            com.keralty.aeconfiguraciones.viewmodel.seccion.siu.TCAMBIARORDENPREGSRequest step22Request = new com.keralty.aeconfiguraciones.viewmodel.seccion.siu.TCAMBIARORDENPREGSRequest();
            step22Request.setSeccionTCAMBIARORDENPREGSpthisSeccionInstance(preguntaTMODIFICARpthisPregunta.getSeccion());
            step22Request.setSeccionTCAMBIARORDENPREGSptOrdenDesde(preguntaTMODIFICARptpNuevoOrden);
            step22Request.setSeccionTCAMBIARORDENPREGSptSubir(Boolean.TRUE);
            step22Request.setSeccionTCAMBIARORDENPREGSptOrdenHasta(Long.valueOf(9999));
            seccionSrv.tCAMBIARORDENPREGSImplementation(step22Request);
        } else {
            /* Action 23: IF (ptpNuevoOrden < NumOrden) */
            boolean ifCondition23 = preguntaTMODIFICARptpNuevoOrden < preguntaTMODIFICARpthisPregunta.getPreguntaNumOrden();
            if (ifCondition23) {
                com.keralty.aeconfiguraciones.viewmodel.seccion.siu.TCAMBIARORDENPREGSRequest step230Request = new com.keralty.aeconfiguraciones.viewmodel.seccion.siu.TCAMBIARORDENPREGSRequest();
                step230Request.setSeccionTCAMBIARORDENPREGSpthisSeccionInstance(preguntaTMODIFICARpthisPregunta.getSeccion());
                step230Request.setSeccionTCAMBIARORDENPREGSptOrdenDesde(preguntaTMODIFICARptpNuevoOrden);
                step230Request.setSeccionTCAMBIARORDENPREGSptSubir(Boolean.TRUE);
                step230Request.setSeccionTCAMBIARORDENPREGSptOrdenHasta(preguntaTMODIFICARpthisPregunta.getPreguntaNumOrden() - Long.valueOf(1));
                seccionSrv.tCAMBIARORDENPREGSImplementation(step230Request);
            }
            /* Action 24: IF (ptpNuevoOrden > NumOrden) */
            boolean ifCondition24 = preguntaTMODIFICARptpNuevoOrden > preguntaTMODIFICARpthisPregunta.getPreguntaNumOrden();
            if (ifCondition24) {
                com.keralty.aeconfiguraciones.viewmodel.seccion.siu.TCAMBIARORDENPREGSRequest step240Request = new com.keralty.aeconfiguraciones.viewmodel.seccion.siu.TCAMBIARORDENPREGSRequest();
                step240Request.setSeccionTCAMBIARORDENPREGSpthisSeccionInstance(preguntaTMODIFICARpthisPregunta.getSeccion());
                step240Request.setSeccionTCAMBIARORDENPREGSptOrdenDesde(preguntaTMODIFICARpthisPregunta.getPreguntaNumOrden() + Long.valueOf(1));
                step240Request.setSeccionTCAMBIARORDENPREGSptSubir(Boolean.FALSE);
                step240Request.setSeccionTCAMBIARORDENPREGSptOrdenHasta(preguntaTMODIFICARptpNuevoOrden);
                seccionSrv.tCAMBIARORDENPREGSImplementation(step240Request);
            }
        }

        com.keralty.aeconfiguraciones.viewmodel.pregunta.siu.CambiarOrdenRequest step3Request = new com.keralty.aeconfiguraciones.viewmodel.pregunta.siu.CambiarOrdenRequest();
        step3Request.setPreguntacambiarOrdenpthisPreguntaInstance(preguntaTMODIFICARpthisPregunta);
        step3Request.setPreguntacambiarOrdenpNuevoOrden(preguntaTMODIFICARptpNuevoOrden);
        preguntaSrv.cambiarOrdenImplementation(step3Request);

        response.setInstance(getByOID(instance.getOid()));

        return response;
    }

    /*
     * Checks service TMODIFICAR preconditions individually
     *
     * @param request Service request viewmodel
     *
     * @return Service preconditions evaluation result
     */
    public ServiceResponse tMODIFICARCheckPreconditions(com.keralty.aeconfiguraciones.viewmodel.pregunta.siu.TMODIFICARRequest request) {
        ServiceResponse response = new ServiceResponse();
        request.setPreguntaTMODIFICARpthisPreguntaInstance(preguntaSrv.getByOIDWithHV(request.getPreguntaTMODIFICARpthisPregunta()));
        request.setPreguntaTMODIFICARptpevcSeccionInstance(seccionSrv.getByOIDWithHV(new SeccionOid().fromJsonString(request.getPreguntaTMODIFICARptpevcSeccion())));
        try {
            if("Clas_1431633788928040Pre_2".equals(request.getPreconditionId()))
                checkClas1431633788928040Pre2(request);
            if("Clas_1431633788928040Pre_5".equals(request.getPreconditionId()))
                checkClas1431633788928040Pre5(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    private void tMODIFICARPreconditions(
            com.keralty.aeconfiguraciones.viewmodel.pregunta.siu.TMODIFICARRequest request) 
            throws ModelException {
        checkClas1431633788928040Pre2(request);
        checkClas1431633788928040Pre5(request);
    }

    private void checkClas1431633788928040Pre2 (
            com.keralty.aeconfiguraciones.viewmodel.pregunta.siu.TMODIFICARRequest request) 
            throws ModelException {
        // ptpNuevoOrden >= 1

        Long preguntaTMODIFICARptpNuevoOrden = request.getPreguntaTMODIFICARptpNuevoOrden();
        boolean preconditionHolds = preguntaTMODIFICARptpNuevoOrden >= Long.valueOf(1);
        if (!preconditionHolds) throw new PreconditionException("El número de orden ha de ser mayor o igual a 1");
    }

    private void checkClas1431633788928040Pre5 (
            com.keralty.aeconfiguraciones.viewmodel.pregunta.siu.TMODIFICARRequest request) 
            throws ModelException {
        // ptpCondicionEdad = 0 OR ( ptpCondicionEdadAnyos <> NULL AND ptpCondicionEdadAnyos >= 0 )

        Long preguntaTMODIFICARptpCondicionEdad = request.getPreguntaTMODIFICARptpCondicionEdad();
        Long preguntaTMODIFICARptpCondicionEdadAnyos = request.getPreguntaTMODIFICARptpCondicionEdadAnyos();
        boolean preconditionHolds = (preguntaTMODIFICARptpCondicionEdad).equals(Long.valueOf(0)) || (preguntaTMODIFICARptpCondicionEdadAnyos != null && preguntaTMODIFICARptpCondicionEdadAnyos >= Long.valueOf(0));
        if (!preconditionHolds) throw new PreconditionException("Si se condiciona la edad, los años deben ser igual o mayor a cero");
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
            com.keralty.aeconfiguraciones.viewmodel.pregunta.siu.TELIMINARRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = preguntaSrv.tELIMINARTxn(request);
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
            com.keralty.aeconfiguraciones.viewmodel.pregunta.siu.TELIMINARRequest request) throws ModelException, SystemException {
        request.setPreguntaTELIMINARpthisPreguntaInstance(preguntaSrv.getByOIDWithHV(request.getPreguntaTELIMINARpthisPregunta()));
        return preguntaSrv.tELIMINARImplementation(request);
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
            com.keralty.aeconfiguraciones.viewmodel.pregunta.siu.TELIMINARRequest request) throws ModelException, SystemException {
        ServiceResponse response = new ServiceResponse();
        Pregunta preguntaTELIMINARpthisPregunta = request.getPreguntaTELIMINARpthisPreguntaInstance();

        request.checkArguments();
        Pregunta instance = preguntaTELIMINARpthisPregunta;

        com.keralty.aeconfiguraciones.viewmodel.seccion.siu.TCAMBIARORDENPREGSRequest step1Request = new com.keralty.aeconfiguraciones.viewmodel.seccion.siu.TCAMBIARORDENPREGSRequest();
        step1Request.setSeccionTCAMBIARORDENPREGSpthisSeccionInstance(preguntaTELIMINARpthisPregunta.getSeccion());
        step1Request.setSeccionTCAMBIARORDENPREGSptOrdenDesde(preguntaTELIMINARpthisPregunta.getPreguntaNumOrden() + Long.valueOf(1));
        step1Request.setSeccionTCAMBIARORDENPREGSptSubir(Boolean.FALSE);
        step1Request.setSeccionTCAMBIARORDENPREGSptOrdenHasta(Long.valueOf(9999));
        seccionSrv.tCAMBIARORDENPREGSImplementation(step1Request);

        for(Valor step2Entity : repository.assocOperator001(preguntaTELIMINARpthisPregunta)) {
            com.keralty.aeconfiguraciones.viewmodel.valor.siu.DelValorPreguntaRequest step2Request = new com.keralty.aeconfiguraciones.viewmodel.valor.siu.DelValorPreguntaRequest();
            step2Request.setValorDelValorPreguntapthisValorInstance(step2Entity);
            step2Request.setValorDelValorPreguntapevcPreguntaInstance(preguntaTELIMINARpthisPregunta);
            valorSrv.delValorPreguntaImplementation(step2Request, true);
        }

        com.keralty.aeconfiguraciones.viewmodel.pregunta.siu.EliminarRequest step3Request = new com.keralty.aeconfiguraciones.viewmodel.pregunta.siu.EliminarRequest();
        step3Request.setPreguntaeliminarpthisPreguntaInstance(preguntaTELIMINARpthisPregunta);
        preguntaSrv.eliminarImplementation(step3Request);

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
        select.getOrderByPart().addItem(Constants.normalizePath(PreguntaConstants.CLASS_NAME, PreguntaConstants.ATTR_FIELD_PREGUNTANUMERO, false), true);
    }
    
    @Override
    protected AbstractAppEntity buildRelatedInstance(String relatedInstanceJsonOid, String relatedInstancePathFirstItem, String remainingPath) {
        AbstractAppEntity relatedInstance = null;
        if (relatedInstancePathFirstItem.isEmpty()) {
            relatedInstance = buildPreguntaRelatedInstance(relatedInstanceJsonOid);
        }
        if (PreguntaConstants.ROLE_NAME_VALORES.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildPreguntaRelatedInstance4Valor(relatedInstanceJsonOid, remainingPath);
        }
        if (PreguntaConstants.ROLE_NAME_PREGUNTASPERSONA.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildPreguntaRelatedInstance4PreguntaP(relatedInstanceJsonOid, remainingPath);
        }
        if (PreguntaConstants.ROLE_NAME_SECCION.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildPreguntaRelatedInstance4Seccion(relatedInstanceJsonOid, remainingPath);
        }
        return relatedInstance;
    }
    
    private AbstractAppEntity buildPreguntaRelatedInstance(String relatedInstanceJsonOid) {
    	ObjectMapper mapper = new ObjectMapper();
    	try {
    		return new Pregunta(mapper.readValue(relatedInstanceJsonOid, PreguntaOid.class));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    private AbstractAppEntity buildPreguntaRelatedInstance4Valor(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new Valor(mapper.readValue(relatedInstanceJsonOid, ValorOid.class)): Valor.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    private AbstractAppEntity buildPreguntaRelatedInstance4PreguntaP(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new PreguntaP(mapper.readValue(relatedInstanceJsonOid, PreguntaPOid.class)): PreguntaP.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    private AbstractAppEntity buildPreguntaRelatedInstance4Seccion(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new Seccion(mapper.readValue(relatedInstanceJsonOid, SeccionOid.class)): Seccion.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    @Override
    protected void applyFilterByOid(String jsonOid, SQLSelect sql) {
        try {
            PreguntaOid oid = new PreguntaOid().fromJsonString(jsonOid);
            SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
            comparison1.setOperator("=");
            comparison1.setFirstOperand(new SQLWhereOperandPathAttribute(PreguntaConstants.ATTR_FIELD_PREGUNTANUMERO, Constants.Type.INT.getTypeName()));
            comparison1.setSecondOperand(new SQLWhereOperandLiteral(oid.getPreguntaNumero(), Constants.Type.INT.getTypeName()));
            sql.addComparison(comparison1);
        } catch (Exception e) {
            LoggerFactory.getLogger(PreguntaService.class).error(e.getMessage());
        }
    }

}
