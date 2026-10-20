package com.keralty.aeconfiguraciones.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.keralty.aeconfiguraciones.exception.ModelException;
import com.keralty.aeconfiguraciones.global.AesolicitudesConstants;
import com.keralty.aeconfiguraciones.global.Constants;
import com.keralty.aeconfiguraciones.global.ContratanteConstants;
import com.keralty.aeconfiguraciones.global.SolicitudConstants;
import com.keralty.aeconfiguraciones.persistence.AbstractAppEntity;
import com.keralty.aeconfiguraciones.persistence.Contratante;
import com.keralty.aeconfiguraciones.persistence.Departamento;
import com.keralty.aeconfiguraciones.persistence.Municipio;
import com.keralty.aeconfiguraciones.persistence.oid.ContratanteOid;
import com.keralty.aeconfiguraciones.persistence.oid.DepartamentoOid;
import com.keralty.aeconfiguraciones.persistence.oid.MunicipioOid;
import com.keralty.aeconfiguraciones.persistence.oid.SolicitudOid;
import com.keralty.aeconfiguraciones.persistence.oid.TipoIdentificacionOid;
import com.keralty.aeconfiguraciones.persistence.Solicitud;
import com.keralty.aeconfiguraciones.persistence.TipoIdentificacion;
import com.keralty.aeconfiguraciones.repository.ContratanteJpaRepository;
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
public class ContratanteService extends AbstractService {

    ContratanteJpaRepository repository;

    ContratanteService contratanteSrv;

    TipoIdentificacionService tipoIdentificacionSrv;

    DepartamentoService departamentoSrv;

    MunicipioService municipioSrv;

    SolicitudService solicitudSrv;

    @Inject
    public ContratanteService(
            ContratanteService contratanteSrv,
            TipoIdentificacionService tipoIdentificacionSrv,
            DepartamentoService departamentoSrv,
            MunicipioService municipioSrv,
            SolicitudService solicitudSrv,
            ContratanteJpaRepository repository) {
        this.contratanteSrv = contratanteSrv;
        this.tipoIdentificacionSrv = tipoIdentificacionSrv;
        this.departamentoSrv = departamentoSrv;
        this.municipioSrv = municipioSrv;
        this.solicitudSrv = solicitudSrv;
        this.repository = repository;
    }

    public List<Map<String, Object>> solveQuery(QueryRequest request) {
        return solveGenericQuery(request, ContratanteConstants.CLASS_NAME);
    }

    private List<Contratante> solveContratanteTypedQuery(QueryRequest request) {
        return repository.solveQuery(processQueryRequest(request, ContratanteConstants.CLASS_NAME), request.getPageSize(), request.getPageNumber());
    }
    
    private int solveContratanteTypedCountQuery(QueryRequest request) {
        return repository.solveCountQuery(processCountQueryRequest(request, ContratanteConstants.CLASS_NAME));
    }

    /*
     * Returns the population of class Contratante
     *
     * @return Population of class Contratante
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<Contratante> get() {
        List<Contratante> results = new java.util.ArrayList<>();
        repository.findAll().forEach(results::add);
        return results;
    }
    /*
     * Returns the population of class Contratante that matches certain search criteria
     *
     * @param queryRequest Search criteria
     *
     * @return Population of class Contratante matching the search criteria
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<Contratante> get(QueryRequest queryRequest) {
        return solveContratanteTypedQuery(queryRequest);
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
            return new PopulationResponseViewModel(solveContratanteTypedQuery(queryRequest).stream()
                    .map(instance -> buildDynamicObject(instance, queryRequest.getDisplaySetItems()))
                    .collect(Collectors.toList()), queryRequest.getPageSize() != 0 ? solveContratanteTypedCountQuery(queryRequest) : 0);
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
        return new PopulationResponseViewModel(solveContratanteTypedQuery(queryRequest).stream()
                .map(instance -> buildDynamicObject(instance, displaySetItems))
                .collect(Collectors.toList()));
    }

    /*
     * Returns an instance of Contratante by ID
     *
     * @param aesolicitudesidsolicitud Identification field aesolicitudesidsolicitud
     *
     * @return Instance of Contratante
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public Contratante getById(Long aesolicitudesidsolicitud) {
        return getByOID(new ContratanteOid(aesolicitudesidsolicitud));
    }
    
    /*
     * Returns an instance of Contratante by OID
     *
     * @param oid Object IDentifier
     *
     * @return Instance of Contratante
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public Contratante getByOID(ContratanteOid oid) {
        Optional<Contratante> instance = getInstanceByOid(oid);
        return instance.isPresent() ? instance.get() : null;
    }
    
    /*
     * Returns an instance of Contratante by OID applying horizontal visibility constraints
     *
     * @param oid Object IDentifier
     *
     * @return Instance of Contratante
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public Contratante getByOIDWithHV(ContratanteOid oid) {
        Optional<Contratante> instance = getInstanceByOid(oid);
    	return instance.isPresent() && instance.get().checkHorizontalVisibility(getAgentInstance()) 
    		 ? instance.get() 
    		 : null;
    }
    
    private Optional<Contratante> getInstanceByOid(ContratanteOid oid) {
        return oid != null && oid.getAesolicitudesidsolicitud() != null
               ? repository.findById(oid.getAesolicitudesidsolicitud())
               : Optional.empty();
    }

    /*
     * Returns an instance of Contratante by OID in JSON format
     *
     * @param jsonOid Object IDentifier in JSON format
     *
     * @return Instance of Contratante
     */
    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public Contratante getByJsonOid(String jsonOid) {
        ContratanteOid oid = new ContratanteOid();
        oid.fromJsonString(jsonOid);
        return getByOIDWithHV(oid);
    }

    /*
     * Returns the list of instances of Contratante related to a given instance through 
     * a given path
     *
     * @param relatedInstance Related instance
     *
     * @param relatedInstancePath Related instance path
     *
     * @return List of instances of Contratante
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<Contratante> getByRelated(AbstractAppEntity relatedInstance, String relatedInstancePath) {
        QueryRequest queryRequest = new QueryRequest(new FilterRequest(Constants.FILTER_BY_RELATED,
                Arrays.asList(new FilterVariableRequest(Constants.FILTER_BY_RELATED_FVAR_PATH, relatedInstancePath),
                        new FilterVariableRequest(Constants.FILTER_BY_RELATED_FVAR_OID, relatedInstance.getOid().toJson()))));
        return solveContratanteTypedQuery(queryRequest);
    }

    /*
     * Returns the list of instances of Contratante related to a given instance through
     * a given path
     *
     * @param relatedInstance Related instance
     *
     * @param relatedInstancePath Related instance path
     *
     * @return List of instances of Contratante
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<Contratante> getInstancesByRelated(AbstractAppEntity relatedInstance, String relatedInstancePath) {
        return getByRelated(relatedInstance, relatedInstancePath);
    }

    /*
     * Returns a set of displayset items for an instance of Contratante
     *
     * @param oid Object IDentifier
     *
     * @param displaySetItems List of comma-separated displayset items
     *
     * @return Set of displayset items for an instance of Contratante
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public InstanceResponseViewModel getContratanteDynamic(ContratanteOid oid, String displaySetItems) {
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
    public String getSuppInfo(ContratanteOid oid, String dsName) {
        Contratante instance = getByOIDWithHV(oid);
        return (instance != null ? getSuppInfo(instance, dsName): "");
    }

    private String getSuppInfo(Contratante instance, String dsName) {
        if (instance == null || instance.isNull()) return "";
        return dsName.isEmpty() ? instance.getOid().toJson() : "";
    }




    // IIU Methods


    /*
     * Solves the query needed for PIUContratanteNull
     *
     * @param queryRequest Query request
     * @return List of viewmodels for Contratante using displayset DSContratanteNull that 
     * match the search criteria
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<com.keralty.aeconfiguraciones.viewmodel.contratante.ds.DSContratanteNull> piuPIUContratanteNull(QueryRequest queryRequest) {
        return solveContratanteTypedQuery(queryRequest).stream()
                .map(com.keralty.aeconfiguraciones.viewmodel.contratante.ds.DSContratanteNull::new)
                .collect(Collectors.toList());
    }

    /*
     * Solves the COUNT query needed for PIUContratanteNull
     *
     * @param queryRequest Query request
     * @returns Count of instances for PIUContratanteNull
     */
    public int getCount4PIUContratanteNull(QueryRequest queryRequest) {
        return solveContratanteTypedQuery(queryRequest).size();
    }

    // Service: CambTipoIdContratante

    /*
     * Entry point for service CambTipoIdContratante
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse cambTipoIdContratante(
            com.keralty.aeconfiguraciones.viewmodel.contratante.siu.CambTipoIdContratanteRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = contratanteSrv.cambTipoIdContratanteTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service CambTipoIdContratante
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse cambTipoIdContratanteTxn(
            com.keralty.aeconfiguraciones.viewmodel.contratante.siu.CambTipoIdContratanteRequest request) throws ModelException {
        request.setContratanteCambTipoIdContratantepthisContratanteInstance(contratanteSrv.getByOIDWithHV(request.getContratanteCambTipoIdContratantepthisContratante()));
        request.setContratanteCambTipoIdContratantepevcTipoIdentificacionInstance(tipoIdentificacionSrv.getByOIDWithHV(request.getContratanteCambTipoIdContratantepevcTipoIdentificacion()));
        return contratanteSrv.cambTipoIdContratanteImplementation(request, true);
    }

    /*
     * Implements service CambTipoIdContratante
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse cambTipoIdContratanteImplementation(
            com.keralty.aeconfiguraciones.viewmodel.contratante.siu.CambTipoIdContratanteRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Contratante contratanteCambTipoIdContratantepthisContratante = request.getContratanteCambTipoIdContratantepthisContratanteInstance();
        TipoIdentificacion contratanteCambTipoIdContratantepevcTipoIdentificacion = request.getContratanteCambTipoIdContratantepevcTipoIdentificacionInstance();

        request.checkArguments();
        Contratante instance = contratanteCambTipoIdContratantepthisContratante;
        if (contratanteCambTipoIdContratantepevcTipoIdentificacion.getTipoIdentificacionNIT().equals(true)) {
            instance.setContratanteTipoPersona("J");
        } else {
            instance.setContratanteTipoPersona("N");
        }

        instance.add2TipoIdentificacion(contratanteCambTipoIdContratantepevcTipoIdentificacion);
        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.tipoidentificacion.siu.CambTipoIdContratanteRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.tipoidentificacion.siu.CambTipoIdContratanteRequest();
            relatedRequest.setTipoIdentificacionCambTipoIdContratantepthisTipoIdentificacionInstance(contratanteCambTipoIdContratantepevcTipoIdentificacion);
            relatedRequest.setTipoIdentificacionCambTipoIdContratantepevcContratanteInstance(contratanteCambTipoIdContratantepthisContratante);
            tipoIdentificacionSrv.cambTipoIdContratanteImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));

        return response;
    }

    // Service: InsDepartamentoNacContratante

    /*
     * Entry point for service InsDepartamentoNacContratante
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse insDepartamentoNacContratante(
            com.keralty.aeconfiguraciones.viewmodel.contratante.siu.InsDepartamentoNacContratanteRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = contratanteSrv.insDepartamentoNacContratanteTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service InsDepartamentoNacContratante
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse insDepartamentoNacContratanteTxn(
            com.keralty.aeconfiguraciones.viewmodel.contratante.siu.InsDepartamentoNacContratanteRequest request) throws ModelException {
        request.setContratanteInsDepartamentoNacContratantepthisContratanteInstance(contratanteSrv.getByOIDWithHV(request.getContratanteInsDepartamentoNacContratantepthisContratante()));
        request.setContratanteInsDepartamentoNacContratantepevcDepartamentoInstance(departamentoSrv.getByOIDWithHV(request.getContratanteInsDepartamentoNacContratantepevcDepartamento()));
        return contratanteSrv.insDepartamentoNacContratanteImplementation(request, true);
    }

    /*
     * Implements service InsDepartamentoNacContratante
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse insDepartamentoNacContratanteImplementation(
            com.keralty.aeconfiguraciones.viewmodel.contratante.siu.InsDepartamentoNacContratanteRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Contratante contratanteInsDepartamentoNacContratantepthisContratante = request.getContratanteInsDepartamentoNacContratantepthisContratanteInstance();
        Departamento contratanteInsDepartamentoNacContratantepevcDepartamento = request.getContratanteInsDepartamentoNacContratantepevcDepartamentoInstance();

        request.checkArguments();
        Contratante instance = contratanteInsDepartamentoNacContratantepthisContratante;

        instance.add2DepartamentoNac(contratanteInsDepartamentoNacContratantepevcDepartamento);
        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.departamento.siu.InsDepartamentoNacContratanteRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.departamento.siu.InsDepartamentoNacContratanteRequest();
            relatedRequest.setDepartamentoInsDepartamentoNacContratantepthisDepartamentoInstance(contratanteInsDepartamentoNacContratantepevcDepartamento);
            relatedRequest.setDepartamentoInsDepartamentoNacContratantepevcContratanteInstance(contratanteInsDepartamentoNacContratantepthisContratante);
            departamentoSrv.insDepartamentoNacContratanteImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));

        return response;
    }

    // Service: DelDepartamentoNacContratante

    /*
     * Entry point for service DelDepartamentoNacContratante
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse delDepartamentoNacContratante(
            com.keralty.aeconfiguraciones.viewmodel.contratante.siu.DelDepartamentoNacContratanteRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = contratanteSrv.delDepartamentoNacContratanteTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service DelDepartamentoNacContratante
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse delDepartamentoNacContratanteTxn(
            com.keralty.aeconfiguraciones.viewmodel.contratante.siu.DelDepartamentoNacContratanteRequest request) throws ModelException {
        request.setContratanteDelDepartamentoNacContratantepthisContratanteInstance(contratanteSrv.getByOIDWithHV(request.getContratanteDelDepartamentoNacContratantepthisContratante()));
        request.setContratanteDelDepartamentoNacContratantepevcDepartamentoInstance(departamentoSrv.getByOIDWithHV(request.getContratanteDelDepartamentoNacContratantepevcDepartamento()));
        return contratanteSrv.delDepartamentoNacContratanteImplementation(request, true);
    }

    /*
     * Implements service DelDepartamentoNacContratante
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse delDepartamentoNacContratanteImplementation(
            com.keralty.aeconfiguraciones.viewmodel.contratante.siu.DelDepartamentoNacContratanteRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Contratante contratanteDelDepartamentoNacContratantepthisContratante = request.getContratanteDelDepartamentoNacContratantepthisContratanteInstance();
        Departamento contratanteDelDepartamentoNacContratantepevcDepartamento = request.getContratanteDelDepartamentoNacContratantepevcDepartamentoInstance();

        request.checkArguments();
        Contratante instance = contratanteDelDepartamentoNacContratantepthisContratante;

        instance.add2DepartamentoNac(null);
        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.departamento.siu.DelDepartamentoNacContratanteRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.departamento.siu.DelDepartamentoNacContratanteRequest();
            relatedRequest.setDepartamentoDelDepartamentoNacContratantepthisDepartamentoInstance(contratanteDelDepartamentoNacContratantepevcDepartamento);
            relatedRequest.setDepartamentoDelDepartamentoNacContratantepevcContratanteInstance(contratanteDelDepartamentoNacContratantepthisContratante);
            departamentoSrv.delDepartamentoNacContratanteImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));

        return response;
    }

    // Service: InsMunicipioNacContratante

    /*
     * Entry point for service InsMunicipioNacContratante
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse insMunicipioNacContratante(
            com.keralty.aeconfiguraciones.viewmodel.contratante.siu.InsMunicipioNacContratanteRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = contratanteSrv.insMunicipioNacContratanteTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service InsMunicipioNacContratante
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse insMunicipioNacContratanteTxn(
            com.keralty.aeconfiguraciones.viewmodel.contratante.siu.InsMunicipioNacContratanteRequest request) throws ModelException {
        request.setContratanteInsMunicipioNacContratantepthisContratanteInstance(contratanteSrv.getByOIDWithHV(request.getContratanteInsMunicipioNacContratantepthisContratante()));
        request.setContratanteInsMunicipioNacContratantepevcMunicipioInstance(municipioSrv.getByOIDWithHV(request.getContratanteInsMunicipioNacContratantepevcMunicipio()));
        return contratanteSrv.insMunicipioNacContratanteImplementation(request, true);
    }

    /*
     * Implements service InsMunicipioNacContratante
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse insMunicipioNacContratanteImplementation(
            com.keralty.aeconfiguraciones.viewmodel.contratante.siu.InsMunicipioNacContratanteRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Contratante contratanteInsMunicipioNacContratantepthisContratante = request.getContratanteInsMunicipioNacContratantepthisContratanteInstance();
        Municipio contratanteInsMunicipioNacContratantepevcMunicipio = request.getContratanteInsMunicipioNacContratantepevcMunicipioInstance();

        request.checkArguments();
        Contratante instance = contratanteInsMunicipioNacContratantepthisContratante;

        instance.add2MunicipioNac(contratanteInsMunicipioNacContratantepevcMunicipio);
        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.municipio.siu.InsMunicipioNacContratanteRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.municipio.siu.InsMunicipioNacContratanteRequest();
            relatedRequest.setMunicipioInsMunicipioNacContratantepthisMunicipioInstance(contratanteInsMunicipioNacContratantepevcMunicipio);
            relatedRequest.setMunicipioInsMunicipioNacContratantepevcContratanteInstance(contratanteInsMunicipioNacContratantepthisContratante);
            municipioSrv.insMunicipioNacContratanteImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));

        return response;
    }

    // Service: DelMunicipioNacContratante

    /*
     * Entry point for service DelMunicipioNacContratante
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse delMunicipioNacContratante(
            com.keralty.aeconfiguraciones.viewmodel.contratante.siu.DelMunicipioNacContratanteRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = contratanteSrv.delMunicipioNacContratanteTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service DelMunicipioNacContratante
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse delMunicipioNacContratanteTxn(
            com.keralty.aeconfiguraciones.viewmodel.contratante.siu.DelMunicipioNacContratanteRequest request) throws ModelException {
        request.setContratanteDelMunicipioNacContratantepthisContratanteInstance(contratanteSrv.getByOIDWithHV(request.getContratanteDelMunicipioNacContratantepthisContratante()));
        request.setContratanteDelMunicipioNacContratantepevcMunicipioInstance(municipioSrv.getByOIDWithHV(request.getContratanteDelMunicipioNacContratantepevcMunicipio()));
        return contratanteSrv.delMunicipioNacContratanteImplementation(request, true);
    }

    /*
     * Implements service DelMunicipioNacContratante
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse delMunicipioNacContratanteImplementation(
            com.keralty.aeconfiguraciones.viewmodel.contratante.siu.DelMunicipioNacContratanteRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Contratante contratanteDelMunicipioNacContratantepthisContratante = request.getContratanteDelMunicipioNacContratantepthisContratanteInstance();
        Municipio contratanteDelMunicipioNacContratantepevcMunicipio = request.getContratanteDelMunicipioNacContratantepevcMunicipioInstance();

        request.checkArguments();
        Contratante instance = contratanteDelMunicipioNacContratantepthisContratante;

        instance.add2MunicipioNac(null);
        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.municipio.siu.DelMunicipioNacContratanteRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.municipio.siu.DelMunicipioNacContratanteRequest();
            relatedRequest.setMunicipioDelMunicipioNacContratantepthisMunicipioInstance(contratanteDelMunicipioNacContratantepevcMunicipio);
            relatedRequest.setMunicipioDelMunicipioNacContratantepevcContratanteInstance(contratanteDelMunicipioNacContratantepthisContratante);
            municipioSrv.delMunicipioNacContratanteImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));

        return response;
    }

    // Service: InsDepartamentoRes

    /*
     * Entry point for service InsDepartamentoRes
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse insDepartamentoRes(
            com.keralty.aeconfiguraciones.viewmodel.contratante.siu.InsDepartamentoResRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = contratanteSrv.insDepartamentoResTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service InsDepartamentoRes
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse insDepartamentoResTxn(
            com.keralty.aeconfiguraciones.viewmodel.contratante.siu.InsDepartamentoResRequest request) throws ModelException {
        request.setContratanteInsDepartamentoRespthisContratanteInstance(contratanteSrv.getByOIDWithHV(request.getContratanteInsDepartamentoRespthisContratante()));
        request.setContratanteInsDepartamentoRespevcDepartamentoInstance(departamentoSrv.getByOIDWithHV(request.getContratanteInsDepartamentoRespevcDepartamento()));
        return contratanteSrv.insDepartamentoResImplementation(request, true);
    }

    /*
     * Implements service InsDepartamentoRes
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse insDepartamentoResImplementation(
            com.keralty.aeconfiguraciones.viewmodel.contratante.siu.InsDepartamentoResRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Contratante contratanteInsDepartamentoRespthisContratante = request.getContratanteInsDepartamentoRespthisContratanteInstance();
        Departamento contratanteInsDepartamentoRespevcDepartamento = request.getContratanteInsDepartamentoRespevcDepartamentoInstance();

        request.checkArguments();
        Contratante instance = contratanteInsDepartamentoRespthisContratante;

        instance.add2DepartamentoRes(contratanteInsDepartamentoRespevcDepartamento);
        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.departamento.siu.InsDepartamentoResRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.departamento.siu.InsDepartamentoResRequest();
            relatedRequest.setDepartamentoInsDepartamentoRespthisDepartamentoInstance(contratanteInsDepartamentoRespevcDepartamento);
            relatedRequest.setDepartamentoInsDepartamentoRespevcContratanteInstance(contratanteInsDepartamentoRespthisContratante);
            departamentoSrv.insDepartamentoResImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));

        return response;
    }

    // Service: DelDepartamentoRes

    /*
     * Entry point for service DelDepartamentoRes
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse delDepartamentoRes(
            com.keralty.aeconfiguraciones.viewmodel.contratante.siu.DelDepartamentoResRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = contratanteSrv.delDepartamentoResTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service DelDepartamentoRes
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse delDepartamentoResTxn(
            com.keralty.aeconfiguraciones.viewmodel.contratante.siu.DelDepartamentoResRequest request) throws ModelException {
        request.setContratanteDelDepartamentoRespthisContratanteInstance(contratanteSrv.getByOIDWithHV(request.getContratanteDelDepartamentoRespthisContratante()));
        request.setContratanteDelDepartamentoRespevcDepartamentoInstance(departamentoSrv.getByOIDWithHV(request.getContratanteDelDepartamentoRespevcDepartamento()));
        return contratanteSrv.delDepartamentoResImplementation(request, true);
    }

    /*
     * Implements service DelDepartamentoRes
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse delDepartamentoResImplementation(
            com.keralty.aeconfiguraciones.viewmodel.contratante.siu.DelDepartamentoResRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Contratante contratanteDelDepartamentoRespthisContratante = request.getContratanteDelDepartamentoRespthisContratanteInstance();
        Departamento contratanteDelDepartamentoRespevcDepartamento = request.getContratanteDelDepartamentoRespevcDepartamentoInstance();

        request.checkArguments();
        Contratante instance = contratanteDelDepartamentoRespthisContratante;

        instance.add2DepartamentoRes(null);
        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.departamento.siu.DelDepartamentoResRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.departamento.siu.DelDepartamentoResRequest();
            relatedRequest.setDepartamentoDelDepartamentoRespthisDepartamentoInstance(contratanteDelDepartamentoRespevcDepartamento);
            relatedRequest.setDepartamentoDelDepartamentoRespevcContratanteInstance(contratanteDelDepartamentoRespthisContratante);
            departamentoSrv.delDepartamentoResImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));

        return response;
    }

    // Service: InsMunicipioRes

    /*
     * Entry point for service InsMunicipioRes
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse insMunicipioRes(
            com.keralty.aeconfiguraciones.viewmodel.contratante.siu.InsMunicipioResRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = contratanteSrv.insMunicipioResTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service InsMunicipioRes
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse insMunicipioResTxn(
            com.keralty.aeconfiguraciones.viewmodel.contratante.siu.InsMunicipioResRequest request) throws ModelException {
        request.setContratanteInsMunicipioRespthisContratanteInstance(contratanteSrv.getByOIDWithHV(request.getContratanteInsMunicipioRespthisContratante()));
        request.setContratanteInsMunicipioRespevcMunicipioInstance(municipioSrv.getByOIDWithHV(request.getContratanteInsMunicipioRespevcMunicipio()));
        return contratanteSrv.insMunicipioResImplementation(request, true);
    }

    /*
     * Implements service InsMunicipioRes
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse insMunicipioResImplementation(
            com.keralty.aeconfiguraciones.viewmodel.contratante.siu.InsMunicipioResRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Contratante contratanteInsMunicipioRespthisContratante = request.getContratanteInsMunicipioRespthisContratanteInstance();
        Municipio contratanteInsMunicipioRespevcMunicipio = request.getContratanteInsMunicipioRespevcMunicipioInstance();

        request.checkArguments();
        Contratante instance = contratanteInsMunicipioRespthisContratante;

        instance.add2MunicipioRes(contratanteInsMunicipioRespevcMunicipio);
        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.municipio.siu.InsMunicipioResRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.municipio.siu.InsMunicipioResRequest();
            relatedRequest.setMunicipioInsMunicipioRespthisMunicipioInstance(contratanteInsMunicipioRespevcMunicipio);
            relatedRequest.setMunicipioInsMunicipioRespevcContratanteInstance(contratanteInsMunicipioRespthisContratante);
            municipioSrv.insMunicipioResImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));

        return response;
    }

    // Service: DelMunicipioRes

    /*
     * Entry point for service DelMunicipioRes
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse delMunicipioRes(
            com.keralty.aeconfiguraciones.viewmodel.contratante.siu.DelMunicipioResRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = contratanteSrv.delMunicipioResTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service DelMunicipioRes
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse delMunicipioResTxn(
            com.keralty.aeconfiguraciones.viewmodel.contratante.siu.DelMunicipioResRequest request) throws ModelException {
        request.setContratanteDelMunicipioRespthisContratanteInstance(contratanteSrv.getByOIDWithHV(request.getContratanteDelMunicipioRespthisContratante()));
        request.setContratanteDelMunicipioRespevcMunicipioInstance(municipioSrv.getByOIDWithHV(request.getContratanteDelMunicipioRespevcMunicipio()));
        return contratanteSrv.delMunicipioResImplementation(request, true);
    }

    /*
     * Implements service DelMunicipioRes
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse delMunicipioResImplementation(
            com.keralty.aeconfiguraciones.viewmodel.contratante.siu.DelMunicipioResRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Contratante contratanteDelMunicipioRespthisContratante = request.getContratanteDelMunicipioRespthisContratanteInstance();
        Municipio contratanteDelMunicipioRespevcMunicipio = request.getContratanteDelMunicipioRespevcMunicipioInstance();

        request.checkArguments();
        Contratante instance = contratanteDelMunicipioRespthisContratante;

        instance.add2MunicipioRes(null);
        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.municipio.siu.DelMunicipioResRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.municipio.siu.DelMunicipioResRequest();
            relatedRequest.setMunicipioDelMunicipioRespthisMunicipioInstance(contratanteDelMunicipioRespevcMunicipio);
            relatedRequest.setMunicipioDelMunicipioRespevcContratanteInstance(contratanteDelMunicipioRespthisContratante);
            municipioSrv.delMunicipioResImplementation(relatedRequest, false);
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
        select.getOrderByPart().addItem(Constants.normalizePath(ContratanteConstants.CLASS_NAME, ContratanteConstants.ROLE_NAME_SOLICITUD + "." + SolicitudConstants.ROLE_NAME_AESOLICITUDES + "." + AesolicitudesConstants.ATTR_FIELD_AESOLICITUDESIDSOLICITUD, false), true);
    }
    
    @Override
    protected AbstractAppEntity buildRelatedInstance(String relatedInstanceJsonOid, String relatedInstancePathFirstItem, String remainingPath) {
        AbstractAppEntity relatedInstance = null;
        if (relatedInstancePathFirstItem.isEmpty()) {
            relatedInstance = buildContratanteRelatedInstance(relatedInstanceJsonOid);
        }
        if (ContratanteConstants.ROLE_NAME_SOLICITUD.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildContratanteRelatedInstance4Solicitud(relatedInstanceJsonOid, remainingPath);
        }
        if (ContratanteConstants.ROLE_NAME_TIPOIDENTIFICACION.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildContratanteRelatedInstance4TipoIdentificacion(relatedInstanceJsonOid, remainingPath);
        }
        if (ContratanteConstants.ROLE_NAME_DEPARTAMENTONAC.equalsIgnoreCase(relatedInstancePathFirstItem) || ContratanteConstants.ROLE_NAME_DEPARTAMENTORES.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildContratanteRelatedInstance4Departamento(relatedInstanceJsonOid, remainingPath);
        }
        if (ContratanteConstants.ROLE_NAME_MUNICIPIONAC.equalsIgnoreCase(relatedInstancePathFirstItem) || ContratanteConstants.ROLE_NAME_MUNICIPIORES.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildContratanteRelatedInstance4Municipio(relatedInstanceJsonOid, remainingPath);
        }
        return relatedInstance;
    }
    
    private AbstractAppEntity buildContratanteRelatedInstance(String relatedInstanceJsonOid) {
    	ObjectMapper mapper = new ObjectMapper();
    	try {
    		return new Contratante(mapper.readValue(relatedInstanceJsonOid, ContratanteOid.class));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    private AbstractAppEntity buildContratanteRelatedInstance4Solicitud(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new Solicitud(mapper.readValue(relatedInstanceJsonOid, SolicitudOid.class)): Solicitud.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    private AbstractAppEntity buildContratanteRelatedInstance4TipoIdentificacion(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new TipoIdentificacion(mapper.readValue(relatedInstanceJsonOid, TipoIdentificacionOid.class)): TipoIdentificacion.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    private AbstractAppEntity buildContratanteRelatedInstance4Departamento(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new Departamento(mapper.readValue(relatedInstanceJsonOid, DepartamentoOid.class)): Departamento.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    private AbstractAppEntity buildContratanteRelatedInstance4Municipio(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new Municipio(mapper.readValue(relatedInstanceJsonOid, MunicipioOid.class)): Municipio.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    @Override
    protected void applyFilterByOid(String jsonOid, SQLSelect sql) {
        try {
            ContratanteOid oid = new ContratanteOid().fromJsonString(jsonOid);
            SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
            comparison1.setOperator("=");
            comparison1.setFirstOperand(new SQLWhereOperandPathAttribute(ContratanteConstants.ROLE_NAME_SOLICITUD + "." + SolicitudConstants.ROLE_NAME_AESOLICITUDES + "." + AesolicitudesConstants.ATTR_FIELD_AESOLICITUDESIDSOLICITUD, Constants.Type.AUTONUMERIC.getTypeName()));
            comparison1.setSecondOperand(new SQLWhereOperandLiteral(oid.getAesolicitudesidsolicitud(), Constants.Type.AUTONUMERIC.getTypeName()));
            sql.addComparison(comparison1);
        } catch (Exception e) {
            LoggerFactory.getLogger(ContratanteService.class).error(e.getMessage());
        }
    }

}
