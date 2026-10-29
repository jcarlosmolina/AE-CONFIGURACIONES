package com.keralty.aeconfiguraciones.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.keralty.aeconfiguraciones.exception.ModelException;
import com.keralty.aeconfiguraciones.exception.SystemException;
import com.keralty.aeconfiguraciones.global.AesolicitudesConstants;
import com.keralty.aeconfiguraciones.global.Constants;
import com.keralty.aeconfiguraciones.global.SolicitudConstants;
import com.keralty.aeconfiguraciones.global.TitularConstants;
import com.keralty.aeconfiguraciones.persistence.AbstractAppEntity;
import com.keralty.aeconfiguraciones.persistence.Departamento;
import com.keralty.aeconfiguraciones.persistence.Direccion;
import com.keralty.aeconfiguraciones.persistence.Municipio;
import com.keralty.aeconfiguraciones.persistence.oid.DepartamentoOid;
import com.keralty.aeconfiguraciones.persistence.oid.DireccionOid;
import com.keralty.aeconfiguraciones.persistence.oid.MunicipioOid;
import com.keralty.aeconfiguraciones.persistence.oid.SolicitudOid;
import com.keralty.aeconfiguraciones.persistence.oid.TipoIdentificacionOid;
import com.keralty.aeconfiguraciones.persistence.oid.TitularOid;
import com.keralty.aeconfiguraciones.persistence.Solicitud;
import com.keralty.aeconfiguraciones.persistence.TipoIdentificacion;
import com.keralty.aeconfiguraciones.persistence.Titular;
import com.keralty.aeconfiguraciones.repository.TitularJpaRepository;
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
import java.sql.Date;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.LoggerFactory;

@Transactional
@ApplicationScoped
public class TitularService extends AbstractService {

    TitularJpaRepository repository;

    TitularService titularSrv;

    TipoIdentificacionService tipoIdentificacionSrv;

    DepartamentoService departamentoSrv;

    MunicipioService municipioSrv;

    DireccionService direccionSrv;

    SolicitudService solicitudSrv;

    @Inject
    public TitularService(
            TitularService titularSrv,
            TipoIdentificacionService tipoIdentificacionSrv,
            DepartamentoService departamentoSrv,
            MunicipioService municipioSrv,
            DireccionService direccionSrv,
            SolicitudService solicitudSrv,
            TitularJpaRepository repository) {
        this.titularSrv = titularSrv;
        this.tipoIdentificacionSrv = tipoIdentificacionSrv;
        this.departamentoSrv = departamentoSrv;
        this.municipioSrv = municipioSrv;
        this.direccionSrv = direccionSrv;
        this.solicitudSrv = solicitudSrv;
        this.repository = repository;
    }

    public List<Map<String, Object>> solveQuery(QueryRequest request) {
        return solveGenericQuery(request, TitularConstants.CLASS_NAME);
    }

    private List<Titular> solveTitularTypedQuery(QueryRequest request) {
        return repository.solveQuery(processQueryRequest(request, TitularConstants.CLASS_NAME), request.getPageSize(), request.getPageNumber());
    }
    
    private int solveTitularTypedCountQuery(QueryRequest request) {
        return repository.solveCountQuery(processCountQueryRequest(request, TitularConstants.CLASS_NAME));
    }

    /*
     * Returns the population of class Titular
     *
     * @return Population of class Titular
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<Titular> get() {
        List<Titular> results = new java.util.ArrayList<>();
        repository.findAll().forEach(results::add);
        return results;
    }
    /*
     * Returns the population of class Titular that matches certain search criteria
     *
     * @param queryRequest Search criteria
     *
     * @return Population of class Titular matching the search criteria
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<Titular> get(QueryRequest queryRequest) {
        return solveTitularTypedQuery(queryRequest);
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
            return new PopulationResponseViewModel(solveTitularTypedQuery(queryRequest).stream()
                    .map(instance -> buildDynamicObject(instance, queryRequest.getDisplaySetItems()))
                    .collect(Collectors.toList()), queryRequest.getPageSize() != 0 ? solveTitularTypedCountQuery(queryRequest) : 0);
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
        return new PopulationResponseViewModel(solveTitularTypedQuery(queryRequest).stream()
                .map(instance -> buildDynamicObject(instance, displaySetItems))
                .collect(Collectors.toList()));
    }

    /*
     * Returns an instance of Titular by ID
     *
     * @param aesolicitudesidsolicitud Identification field aesolicitudesidsolicitud
     *
     * @return Instance of Titular
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public Titular getById(Long aesolicitudesidsolicitud) {
        return getByOID(new TitularOid(aesolicitudesidsolicitud));
    }
    
    /*
     * Returns an instance of Titular by OID
     *
     * @param oid Object IDentifier
     *
     * @return Instance of Titular
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public Titular getByOID(TitularOid oid) {
        Optional<Titular> instance = getInstanceByOid(oid);
        return instance.isPresent() ? instance.get() : null;
    }
    
    /*
     * Returns an instance of Titular by OID applying horizontal visibility constraints
     *
     * @param oid Object IDentifier
     *
     * @return Instance of Titular
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public Titular getByOIDWithHV(TitularOid oid) {
        Optional<Titular> instance = getInstanceByOid(oid);
    	return instance.isPresent() && instance.get().checkHorizontalVisibility(getAgentInstance()) 
    		 ? instance.get() 
    		 : null;
    }
    
    private Optional<Titular> getInstanceByOid(TitularOid oid) {
        return oid != null && oid.getAesolicitudesidsolicitud() != null
               ? repository.findById(oid.getAesolicitudesidsolicitud())
               : Optional.empty();
    }

    /*
     * Returns an instance of Titular by OID in JSON format
     *
     * @param jsonOid Object IDentifier in JSON format
     *
     * @return Instance of Titular
     */
    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public Titular getByJsonOid(String jsonOid) {
        TitularOid oid = new TitularOid();
        oid.fromJsonString(jsonOid);
        return getByOIDWithHV(oid);
    }

    /*
     * Returns the list of instances of Titular related to a given instance through 
     * a given path
     *
     * @param relatedInstance Related instance
     *
     * @param relatedInstancePath Related instance path
     *
     * @return List of instances of Titular
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<Titular> getByRelated(AbstractAppEntity relatedInstance, String relatedInstancePath) {
        QueryRequest queryRequest = new QueryRequest(new FilterRequest(Constants.FILTER_BY_RELATED,
                Arrays.asList(new FilterVariableRequest(Constants.FILTER_BY_RELATED_FVAR_PATH, relatedInstancePath),
                        new FilterVariableRequest(Constants.FILTER_BY_RELATED_FVAR_OID, relatedInstance.getOid().toJson()))));
        return solveTitularTypedQuery(queryRequest);
    }

    /*
     * Returns the list of instances of Titular related to a given instance through
     * a given path
     *
     * @param relatedInstance Related instance
     *
     * @param relatedInstancePath Related instance path
     *
     * @return List of instances of Titular
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<Titular> getInstancesByRelated(AbstractAppEntity relatedInstance, String relatedInstancePath) {
        return getByRelated(relatedInstance, relatedInstancePath);
    }

    /*
     * Returns a set of displayset items for an instance of Titular
     *
     * @param oid Object IDentifier
     *
     * @param displaySetItems List of comma-separated displayset items
     *
     * @return Set of displayset items for an instance of Titular
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public InstanceResponseViewModel getTitularDynamic(TitularOid oid, String displaySetItems) {
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
    public String getSuppInfo(TitularOid oid, String dsName) {
        Titular instance = getByOIDWithHV(oid);
        return (instance != null ? getSuppInfo(instance, dsName): "");
    }

    private String getSuppInfo(Titular instance, String dsName) {
        if (instance == null || instance.isNull()) return "";
        return dsName.isEmpty() ? instance.getOid().toJson() : "";
    }




    // IIU Methods


    /*
     * Solves the query needed for PIUTitularNull
     *
     * @param queryRequest Query request
     * @return List of viewmodels for Titular using displayset DSTitularNull that 
     * match the search criteria
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<com.keralty.aeconfiguraciones.viewmodel.titular.ds.DSTitularNull> piuPIUTitularNull(QueryRequest queryRequest) {
        return solveTitularTypedQuery(queryRequest).stream()
                .map(com.keralty.aeconfiguraciones.viewmodel.titular.ds.DSTitularNull::new)
                .collect(Collectors.toList());
    }

    /*
     * Solves the COUNT query needed for PIUTitularNull
     *
     * @param queryRequest Query request
     * @returns Count of instances for PIUTitularNull
     */
    public int getCount4PIUTitularNull(QueryRequest queryRequest) {
        return solveTitularTypedQuery(queryRequest).size();
    }

    // Service: CambTipoIdentificacionTitular

    /*
     * Entry point for service CambTipoIdentificacionTitular
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse cambTipoIdentificacionTitular(
            com.keralty.aeconfiguraciones.viewmodel.titular.siu.CambTipoIdentificacionTitularRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = titularSrv.cambTipoIdentificacionTitularTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service CambTipoIdentificacionTitular
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse cambTipoIdentificacionTitularTxn(
            com.keralty.aeconfiguraciones.viewmodel.titular.siu.CambTipoIdentificacionTitularRequest request) throws ModelException {
        request.setTitularCambTipoIdentificacionTitularpthisTitularInstance(titularSrv.getByOIDWithHV(request.getTitularCambTipoIdentificacionTitularpthisTitular()));
        request.setTitularCambTipoIdentificacionTitularpevcTipoIdentificacionInstance(tipoIdentificacionSrv.getByOIDWithHV(request.getTitularCambTipoIdentificacionTitularpevcTipoIdentificacion()));
        return titularSrv.cambTipoIdentificacionTitularImplementation(request, true);
    }

    /*
     * Implements service CambTipoIdentificacionTitular
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse cambTipoIdentificacionTitularImplementation(
            com.keralty.aeconfiguraciones.viewmodel.titular.siu.CambTipoIdentificacionTitularRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Titular titularCambTipoIdentificacionTitularpthisTitular = request.getTitularCambTipoIdentificacionTitularpthisTitularInstance();
        TipoIdentificacion titularCambTipoIdentificacionTitularpevcTipoIdentificacion = request.getTitularCambTipoIdentificacionTitularpevcTipoIdentificacionInstance();

        request.checkArguments();
        Titular instance = titularCambTipoIdentificacionTitularpthisTitular;
        if (titularCambTipoIdentificacionTitularpevcTipoIdentificacion.getTipoIdentificacionNIT().equals(true)) {
            instance.setTitularTipoPersona("J");
        } else {
            instance.setTitularTipoPersona("N");
        }

        instance.add2TipoIdentificacion(titularCambTipoIdentificacionTitularpevcTipoIdentificacion);
        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.tipoidentificacion.siu.CambTipoIdentificacionTitularRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.tipoidentificacion.siu.CambTipoIdentificacionTitularRequest();
            relatedRequest.setTipoIdentificacionCambTipoIdentificacionTitularpthisTipoIdentificacionInstance(titularCambTipoIdentificacionTitularpevcTipoIdentificacion);
            relatedRequest.setTipoIdentificacionCambTipoIdentificacionTitularpevcTitularInstance(titularCambTipoIdentificacionTitularpthisTitular);
            tipoIdentificacionSrv.cambTipoIdentificacionTitularImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));

        return response;
    }

    // Service: InsDepartamentoNacTitular

    /*
     * Entry point for service InsDepartamentoNacTitular
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse insDepartamentoNacTitular(
            com.keralty.aeconfiguraciones.viewmodel.titular.siu.InsDepartamentoNacTitularRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = titularSrv.insDepartamentoNacTitularTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service InsDepartamentoNacTitular
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse insDepartamentoNacTitularTxn(
            com.keralty.aeconfiguraciones.viewmodel.titular.siu.InsDepartamentoNacTitularRequest request) throws ModelException {
        request.setTitularInsDepartamentoNacTitularpthisTitularInstance(titularSrv.getByOIDWithHV(request.getTitularInsDepartamentoNacTitularpthisTitular()));
        request.setTitularInsDepartamentoNacTitularpevcDepartamentoInstance(departamentoSrv.getByOIDWithHV(request.getTitularInsDepartamentoNacTitularpevcDepartamento()));
        return titularSrv.insDepartamentoNacTitularImplementation(request, true);
    }

    /*
     * Implements service InsDepartamentoNacTitular
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse insDepartamentoNacTitularImplementation(
            com.keralty.aeconfiguraciones.viewmodel.titular.siu.InsDepartamentoNacTitularRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Titular titularInsDepartamentoNacTitularpthisTitular = request.getTitularInsDepartamentoNacTitularpthisTitularInstance();
        Departamento titularInsDepartamentoNacTitularpevcDepartamento = request.getTitularInsDepartamentoNacTitularpevcDepartamentoInstance();

        request.checkArguments();
        Titular instance = titularInsDepartamentoNacTitularpthisTitular;

        instance.add2DepartamentoNac(titularInsDepartamentoNacTitularpevcDepartamento);
        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.departamento.siu.InsDepartamentoNacTitularRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.departamento.siu.InsDepartamentoNacTitularRequest();
            relatedRequest.setDepartamentoInsDepartamentoNacTitularpthisDepartamentoInstance(titularInsDepartamentoNacTitularpevcDepartamento);
            relatedRequest.setDepartamentoInsDepartamentoNacTitularpevcTitularInstance(titularInsDepartamentoNacTitularpthisTitular);
            departamentoSrv.insDepartamentoNacTitularImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));

        return response;
    }

    // Service: DelDepartamentoNacTitular

    /*
     * Entry point for service DelDepartamentoNacTitular
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse delDepartamentoNacTitular(
            com.keralty.aeconfiguraciones.viewmodel.titular.siu.DelDepartamentoNacTitularRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = titularSrv.delDepartamentoNacTitularTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service DelDepartamentoNacTitular
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse delDepartamentoNacTitularTxn(
            com.keralty.aeconfiguraciones.viewmodel.titular.siu.DelDepartamentoNacTitularRequest request) throws ModelException {
        request.setTitularDelDepartamentoNacTitularpthisTitularInstance(titularSrv.getByOIDWithHV(request.getTitularDelDepartamentoNacTitularpthisTitular()));
        request.setTitularDelDepartamentoNacTitularpevcDepartamentoInstance(departamentoSrv.getByOIDWithHV(request.getTitularDelDepartamentoNacTitularpevcDepartamento()));
        return titularSrv.delDepartamentoNacTitularImplementation(request, true);
    }

    /*
     * Implements service DelDepartamentoNacTitular
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse delDepartamentoNacTitularImplementation(
            com.keralty.aeconfiguraciones.viewmodel.titular.siu.DelDepartamentoNacTitularRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Titular titularDelDepartamentoNacTitularpthisTitular = request.getTitularDelDepartamentoNacTitularpthisTitularInstance();
        Departamento titularDelDepartamentoNacTitularpevcDepartamento = request.getTitularDelDepartamentoNacTitularpevcDepartamentoInstance();

        request.checkArguments();
        Titular instance = titularDelDepartamentoNacTitularpthisTitular;

        instance.add2DepartamentoNac(null);
        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.departamento.siu.DelDepartamentoNacTitularRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.departamento.siu.DelDepartamentoNacTitularRequest();
            relatedRequest.setDepartamentoDelDepartamentoNacTitularpthisDepartamentoInstance(titularDelDepartamentoNacTitularpevcDepartamento);
            relatedRequest.setDepartamentoDelDepartamentoNacTitularpevcTitularInstance(titularDelDepartamentoNacTitularpthisTitular);
            departamentoSrv.delDepartamentoNacTitularImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));

        return response;
    }

    // Service: InsMunicipioNacTitular

    /*
     * Entry point for service InsMunicipioNacTitular
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse insMunicipioNacTitular(
            com.keralty.aeconfiguraciones.viewmodel.titular.siu.InsMunicipioNacTitularRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = titularSrv.insMunicipioNacTitularTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service InsMunicipioNacTitular
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse insMunicipioNacTitularTxn(
            com.keralty.aeconfiguraciones.viewmodel.titular.siu.InsMunicipioNacTitularRequest request) throws ModelException {
        request.setTitularInsMunicipioNacTitularpthisTitularInstance(titularSrv.getByOIDWithHV(request.getTitularInsMunicipioNacTitularpthisTitular()));
        request.setTitularInsMunicipioNacTitularpevcMunicipioInstance(municipioSrv.getByOIDWithHV(request.getTitularInsMunicipioNacTitularpevcMunicipio()));
        return titularSrv.insMunicipioNacTitularImplementation(request, true);
    }

    /*
     * Implements service InsMunicipioNacTitular
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse insMunicipioNacTitularImplementation(
            com.keralty.aeconfiguraciones.viewmodel.titular.siu.InsMunicipioNacTitularRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Titular titularInsMunicipioNacTitularpthisTitular = request.getTitularInsMunicipioNacTitularpthisTitularInstance();
        Municipio titularInsMunicipioNacTitularpevcMunicipio = request.getTitularInsMunicipioNacTitularpevcMunicipioInstance();

        request.checkArguments();
        Titular instance = titularInsMunicipioNacTitularpthisTitular;

        instance.add2MunicipioNac(titularInsMunicipioNacTitularpevcMunicipio);
        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.municipio.siu.InsMunicipioNacTitularRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.municipio.siu.InsMunicipioNacTitularRequest();
            relatedRequest.setMunicipioInsMunicipioNacTitularpthisMunicipioInstance(titularInsMunicipioNacTitularpevcMunicipio);
            relatedRequest.setMunicipioInsMunicipioNacTitularpevcTitularInstance(titularInsMunicipioNacTitularpthisTitular);
            municipioSrv.insMunicipioNacTitularImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));

        return response;
    }

    // Service: DelMunicipioNacTitular

    /*
     * Entry point for service DelMunicipioNacTitular
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse delMunicipioNacTitular(
            com.keralty.aeconfiguraciones.viewmodel.titular.siu.DelMunicipioNacTitularRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = titularSrv.delMunicipioNacTitularTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service DelMunicipioNacTitular
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse delMunicipioNacTitularTxn(
            com.keralty.aeconfiguraciones.viewmodel.titular.siu.DelMunicipioNacTitularRequest request) throws ModelException {
        request.setTitularDelMunicipioNacTitularpthisTitularInstance(titularSrv.getByOIDWithHV(request.getTitularDelMunicipioNacTitularpthisTitular()));
        request.setTitularDelMunicipioNacTitularpevcMunicipioInstance(municipioSrv.getByOIDWithHV(request.getTitularDelMunicipioNacTitularpevcMunicipio()));
        return titularSrv.delMunicipioNacTitularImplementation(request, true);
    }

    /*
     * Implements service DelMunicipioNacTitular
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse delMunicipioNacTitularImplementation(
            com.keralty.aeconfiguraciones.viewmodel.titular.siu.DelMunicipioNacTitularRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Titular titularDelMunicipioNacTitularpthisTitular = request.getTitularDelMunicipioNacTitularpthisTitularInstance();
        Municipio titularDelMunicipioNacTitularpevcMunicipio = request.getTitularDelMunicipioNacTitularpevcMunicipioInstance();

        request.checkArguments();
        Titular instance = titularDelMunicipioNacTitularpthisTitular;

        instance.add2MunicipioNac(null);
        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.municipio.siu.DelMunicipioNacTitularRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.municipio.siu.DelMunicipioNacTitularRequest();
            relatedRequest.setMunicipioDelMunicipioNacTitularpthisMunicipioInstance(titularDelMunicipioNacTitularpevcMunicipio);
            relatedRequest.setMunicipioDelMunicipioNacTitularpevcTitularInstance(titularDelMunicipioNacTitularpthisTitular);
            municipioSrv.delMunicipioNacTitularImplementation(relatedRequest, false);
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
        select.getOrderByPart().addItem(Constants.normalizePath(TitularConstants.CLASS_NAME, TitularConstants.ROLE_NAME_SOLICITUD + "." + SolicitudConstants.ROLE_NAME_AESOLICITUDES + "." + AesolicitudesConstants.ATTR_FIELD_AESOLICITUDESIDSOLICITUD, false), true);
    }
    
    @Override
    protected AbstractAppEntity buildRelatedInstance(String relatedInstanceJsonOid, String relatedInstancePathFirstItem, String remainingPath) {
        AbstractAppEntity relatedInstance = null;
        if (relatedInstancePathFirstItem.isEmpty()) {
            relatedInstance = buildTitularRelatedInstance(relatedInstanceJsonOid);
        }
        if (TitularConstants.ROLE_NAME_DIRECCION.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildTitularRelatedInstance4Direccion(relatedInstanceJsonOid, remainingPath);
        }
        if (TitularConstants.ROLE_NAME_SOLICITUD.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildTitularRelatedInstance4Solicitud(relatedInstanceJsonOid, remainingPath);
        }
        if (TitularConstants.ROLE_NAME_TIPOIDENTIFICACION.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildTitularRelatedInstance4TipoIdentificacion(relatedInstanceJsonOid, remainingPath);
        }
        if (TitularConstants.ROLE_NAME_DEPARTAMENTONAC.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildTitularRelatedInstance4Departamento(relatedInstanceJsonOid, remainingPath);
        }
        if (TitularConstants.ROLE_NAME_MUNICIPIONAC.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildTitularRelatedInstance4Municipio(relatedInstanceJsonOid, remainingPath);
        }
        return relatedInstance;
    }
    
    private AbstractAppEntity buildTitularRelatedInstance(String relatedInstanceJsonOid) {
    	ObjectMapper mapper = new ObjectMapper();
    	try {
    		return new Titular(mapper.readValue(relatedInstanceJsonOid, TitularOid.class));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            throw new SystemException(e.getMessage());
    	}
    }

    private AbstractAppEntity buildTitularRelatedInstance4Direccion(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new Direccion(mapper.readValue(relatedInstanceJsonOid, DireccionOid.class)): Direccion.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            throw new SystemException(e.getMessage());
    	}
    }

    private AbstractAppEntity buildTitularRelatedInstance4Solicitud(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new Solicitud(mapper.readValue(relatedInstanceJsonOid, SolicitudOid.class)): Solicitud.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            throw new SystemException(e.getMessage());
    	}
    }

    private AbstractAppEntity buildTitularRelatedInstance4TipoIdentificacion(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new TipoIdentificacion(mapper.readValue(relatedInstanceJsonOid, TipoIdentificacionOid.class)): TipoIdentificacion.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            throw new SystemException(e.getMessage());
    	}
    }

    private AbstractAppEntity buildTitularRelatedInstance4Departamento(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new Departamento(mapper.readValue(relatedInstanceJsonOid, DepartamentoOid.class)): Departamento.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            throw new SystemException(e.getMessage());
    	}
    }

    private AbstractAppEntity buildTitularRelatedInstance4Municipio(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new Municipio(mapper.readValue(relatedInstanceJsonOid, MunicipioOid.class)): Municipio.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            throw new SystemException(e.getMessage());
    	}
    }

    @Override
    protected void applyFilterByOid(String jsonOid, SQLSelect sql) {
        try {
            TitularOid oid = new TitularOid().fromJsonString(jsonOid);
            SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
            comparison1.setOperator("=");
            comparison1.setFirstOperand(new SQLWhereOperandPathAttribute(TitularConstants.ROLE_NAME_SOLICITUD + "." + SolicitudConstants.ROLE_NAME_AESOLICITUDES + "." + AesolicitudesConstants.ATTR_FIELD_AESOLICITUDESIDSOLICITUD, Constants.Type.AUTONUMERIC.getTypeName()));
            comparison1.setSecondOperand(new SQLWhereOperandLiteral(oid.getAesolicitudesidsolicitud(), Constants.Type.AUTONUMERIC.getTypeName()));
            sql.addComparison(comparison1);
        } catch (Exception e) {
            LoggerFactory.getLogger(TitularService.class).error(e.getMessage());
            throw new SystemException(e.getMessage());
        }
    }

}
