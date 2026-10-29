package com.keralty.aeconfiguraciones.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.keralty.aeconfiguraciones.exception.ModelException;
import com.keralty.aeconfiguraciones.exception.SystemException;
import com.keralty.aeconfiguraciones.global.Constants;
import com.keralty.aeconfiguraciones.global.DireccionCConstants;
import com.keralty.aeconfiguraciones.persistence.AbstractAppEntity;
import com.keralty.aeconfiguraciones.persistence.ContratoC;
import com.keralty.aeconfiguraciones.persistence.Departamento;
import com.keralty.aeconfiguraciones.persistence.DireccionC;
import com.keralty.aeconfiguraciones.persistence.Municipio;
import com.keralty.aeconfiguraciones.persistence.oid.ContratoCOid;
import com.keralty.aeconfiguraciones.persistence.oid.DepartamentoOid;
import com.keralty.aeconfiguraciones.persistence.oid.DireccionCOid;
import com.keralty.aeconfiguraciones.persistence.oid.MunicipioOid;
import com.keralty.aeconfiguraciones.persistence.oid.PaisOid;
import com.keralty.aeconfiguraciones.persistence.oid.PersonaCOid;
import com.keralty.aeconfiguraciones.persistence.oid.TipoDireccionOid;
import com.keralty.aeconfiguraciones.persistence.Pais;
import com.keralty.aeconfiguraciones.persistence.PersonaC;
import com.keralty.aeconfiguraciones.persistence.TipoDireccion;
import com.keralty.aeconfiguraciones.repository.DireccionCJpaRepository;
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
public class DireccionCService extends AbstractService {

    DireccionCJpaRepository repository;

    DireccionCService direccionCSrv;

    TipoDireccionService tipoDireccionSrv;

    DepartamentoService departamentoSrv;

    MunicipioService municipioSrv;

    PaisService paisSrv;

    PersonaCService personaCSrv;

    ContratoCService contratoCSrv;

    @Inject
    public DireccionCService(
            DireccionCService direccionCSrv,
            TipoDireccionService tipoDireccionSrv,
            DepartamentoService departamentoSrv,
            MunicipioService municipioSrv,
            PaisService paisSrv,
            PersonaCService personaCSrv,
            ContratoCService contratoCSrv,
            DireccionCJpaRepository repository) {
        this.direccionCSrv = direccionCSrv;
        this.tipoDireccionSrv = tipoDireccionSrv;
        this.departamentoSrv = departamentoSrv;
        this.municipioSrv = municipioSrv;
        this.paisSrv = paisSrv;
        this.personaCSrv = personaCSrv;
        this.contratoCSrv = contratoCSrv;
        this.repository = repository;
    }

    public List<Map<String, Object>> solveQuery(QueryRequest request) {
        return solveGenericQuery(request, DireccionCConstants.CLASS_NAME);
    }

    private List<DireccionC> solveDireccionCTypedQuery(QueryRequest request) {
        return repository.solveQuery(processQueryRequest(request, DireccionCConstants.CLASS_NAME), request.getPageSize(), request.getPageNumber());
    }
    
    private int solveDireccionCTypedCountQuery(QueryRequest request) {
        return repository.solveCountQuery(processCountQueryRequest(request, DireccionCConstants.CLASS_NAME));
    }

    /*
     * Returns the population of class DireccionC
     *
     * @return Population of class DireccionC
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<DireccionC> get() {
        List<DireccionC> results = new java.util.ArrayList<>();
        repository.findAll().forEach(results::add);
        return results;
    }
    /*
     * Returns the population of class DireccionC that matches certain search criteria
     *
     * @param queryRequest Search criteria
     *
     * @return Population of class DireccionC matching the search criteria
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<DireccionC> get(QueryRequest queryRequest) {
        return solveDireccionCTypedQuery(queryRequest);
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
            return new PopulationResponseViewModel(solveDireccionCTypedQuery(queryRequest).stream()
                    .map(instance -> buildDynamicObject(instance, queryRequest.getDisplaySetItems()))
                    .collect(Collectors.toList()), queryRequest.getPageSize() != 0 ? solveDireccionCTypedCountQuery(queryRequest) : 0);
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
        return new PopulationResponseViewModel(solveDireccionCTypedQuery(queryRequest).stream()
                .map(instance -> buildDynamicObject(instance, displaySetItems))
                .collect(Collectors.toList()));
    }

    /*
     * Returns an instance of DireccionC by ID
     *
     * @param direccionCCodigo Identification field direccionCCodigo
     *
     * @return Instance of DireccionC
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public DireccionC getById(Long direccionCCodigo) {
        return getByOID(new DireccionCOid(direccionCCodigo));
    }
    
    /*
     * Returns an instance of DireccionC by OID
     *
     * @param oid Object IDentifier
     *
     * @return Instance of DireccionC
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public DireccionC getByOID(DireccionCOid oid) {
        Optional<DireccionC> instance = getInstanceByOid(oid);
        return instance.isPresent() ? instance.get() : null;
    }
    
    /*
     * Returns an instance of DireccionC by OID applying horizontal visibility constraints
     *
     * @param oid Object IDentifier
     *
     * @return Instance of DireccionC
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public DireccionC getByOIDWithHV(DireccionCOid oid) {
        Optional<DireccionC> instance = getInstanceByOid(oid);
    	return instance.isPresent() && instance.get().checkHorizontalVisibility(getAgentInstance()) 
    		 ? instance.get() 
    		 : null;
    }
    
    private Optional<DireccionC> getInstanceByOid(DireccionCOid oid) {
        return oid != null && oid.getDireccionCCodigo() != null
               ? repository.findById(oid.getDireccionCCodigo())
               : Optional.empty();
    }

    /*
     * Returns an instance of DireccionC by OID in JSON format
     *
     * @param jsonOid Object IDentifier in JSON format
     *
     * @return Instance of DireccionC
     */
    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public DireccionC getByJsonOid(String jsonOid) {
        DireccionCOid oid = new DireccionCOid();
        oid.fromJsonString(jsonOid);
        return getByOIDWithHV(oid);
    }

    /*
     * Returns the list of instances of DireccionC related to a given instance through 
     * a given path
     *
     * @param relatedInstance Related instance
     *
     * @param relatedInstancePath Related instance path
     *
     * @return List of instances of DireccionC
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<DireccionC> getByRelated(AbstractAppEntity relatedInstance, String relatedInstancePath) {
        QueryRequest queryRequest = new QueryRequest(new FilterRequest(Constants.FILTER_BY_RELATED,
                Arrays.asList(new FilterVariableRequest(Constants.FILTER_BY_RELATED_FVAR_PATH, relatedInstancePath),
                        new FilterVariableRequest(Constants.FILTER_BY_RELATED_FVAR_OID, relatedInstance.getOid().toJson()))));
        return solveDireccionCTypedQuery(queryRequest);
    }

    /*
     * Returns the list of instances of DireccionC related to a given instance through
     * a given path
     *
     * @param relatedInstance Related instance
     *
     * @param relatedInstancePath Related instance path
     *
     * @return List of instances of DireccionC
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<DireccionC> getInstancesByRelated(AbstractAppEntity relatedInstance, String relatedInstancePath) {
        return getByRelated(relatedInstance, relatedInstancePath);
    }

    /*
     * Returns a set of displayset items for an instance of DireccionC
     *
     * @param oid Object IDentifier
     *
     * @param displaySetItems List of comma-separated displayset items
     *
     * @return Set of displayset items for an instance of DireccionC
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public InstanceResponseViewModel getDireccionCDynamic(DireccionCOid oid, String displaySetItems) {
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
    public String getSuppInfo(DireccionCOid oid, String dsName) {
        DireccionC instance = getByOIDWithHV(oid);
        return (instance != null ? getSuppInfo(instance, dsName): "");
    }

    private String getSuppInfo(DireccionC instance, String dsName) {
        if (instance == null || instance.isNull()) return "";
        return dsName.isEmpty() ? instance.getOid().toJson() : "";
    }




    // IIU Methods



    // Service: InsTipoDireccionC

    /*
     * Entry point for service InsTipoDireccionC
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse insTipoDireccionC(
            com.keralty.aeconfiguraciones.viewmodel.direccionc.siu.InsTipoDireccionCRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = direccionCSrv.insTipoDireccionCTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service InsTipoDireccionC
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse insTipoDireccionCTxn(
            com.keralty.aeconfiguraciones.viewmodel.direccionc.siu.InsTipoDireccionCRequest request) throws ModelException {
        request.setDireccionCInsTipoDireccionCpthisDireccionCInstance(direccionCSrv.getByOIDWithHV(request.getDireccionCInsTipoDireccionCpthisDireccionC()));
        request.setDireccionCInsTipoDireccionCpevcTipoDireccionInstance(tipoDireccionSrv.getByOIDWithHV(request.getDireccionCInsTipoDireccionCpevcTipoDireccion()));
        return direccionCSrv.insTipoDireccionCImplementation(request, true);
    }

    /*
     * Implements service InsTipoDireccionC
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse insTipoDireccionCImplementation(
            com.keralty.aeconfiguraciones.viewmodel.direccionc.siu.InsTipoDireccionCRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        DireccionC direccionCInsTipoDireccionCpthisDireccionC = request.getDireccionCInsTipoDireccionCpthisDireccionCInstance();
        TipoDireccion direccionCInsTipoDireccionCpevcTipoDireccion = request.getDireccionCInsTipoDireccionCpevcTipoDireccionInstance();

        request.checkArguments();
        DireccionC instance = direccionCInsTipoDireccionCpthisDireccionC;

        instance.add2TipoDireccionC(direccionCInsTipoDireccionCpevcTipoDireccion);
        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.tipodireccion.siu.InsTipoDireccionCRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.tipodireccion.siu.InsTipoDireccionCRequest();
            relatedRequest.setTipoDireccionInsTipoDireccionCpthisTipoDireccionInstance(direccionCInsTipoDireccionCpevcTipoDireccion);
            relatedRequest.setTipoDireccionInsTipoDireccionCpevcDireccionCInstance(direccionCInsTipoDireccionCpthisDireccionC);
            tipoDireccionSrv.insTipoDireccionCImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));

        return response;
    }

    // Service: DelTipoDireccionC

    /*
     * Entry point for service DelTipoDireccionC
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse delTipoDireccionC(
            com.keralty.aeconfiguraciones.viewmodel.direccionc.siu.DelTipoDireccionCRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = direccionCSrv.delTipoDireccionCTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service DelTipoDireccionC
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse delTipoDireccionCTxn(
            com.keralty.aeconfiguraciones.viewmodel.direccionc.siu.DelTipoDireccionCRequest request) throws ModelException {
        request.setDireccionCDelTipoDireccionCpthisDireccionCInstance(direccionCSrv.getByOIDWithHV(request.getDireccionCDelTipoDireccionCpthisDireccionC()));
        request.setDireccionCDelTipoDireccionCpevcTipoDireccionInstance(tipoDireccionSrv.getByOIDWithHV(request.getDireccionCDelTipoDireccionCpevcTipoDireccion()));
        return direccionCSrv.delTipoDireccionCImplementation(request, true);
    }

    /*
     * Implements service DelTipoDireccionC
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse delTipoDireccionCImplementation(
            com.keralty.aeconfiguraciones.viewmodel.direccionc.siu.DelTipoDireccionCRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        DireccionC direccionCDelTipoDireccionCpthisDireccionC = request.getDireccionCDelTipoDireccionCpthisDireccionCInstance();
        TipoDireccion direccionCDelTipoDireccionCpevcTipoDireccion = request.getDireccionCDelTipoDireccionCpevcTipoDireccionInstance();

        request.checkArguments();
        DireccionC instance = direccionCDelTipoDireccionCpthisDireccionC;

        instance.add2TipoDireccionC(null);
        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.tipodireccion.siu.DelTipoDireccionCRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.tipodireccion.siu.DelTipoDireccionCRequest();
            relatedRequest.setTipoDireccionDelTipoDireccionCpthisTipoDireccionInstance(direccionCDelTipoDireccionCpevcTipoDireccion);
            relatedRequest.setTipoDireccionDelTipoDireccionCpevcDireccionCInstance(direccionCDelTipoDireccionCpthisDireccionC);
            tipoDireccionSrv.delTipoDireccionCImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));

        return response;
    }

    // Service: InsDepartamentoC

    /*
     * Entry point for service InsDepartamentoC
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse insDepartamentoC(
            com.keralty.aeconfiguraciones.viewmodel.direccionc.siu.InsDepartamentoCRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = direccionCSrv.insDepartamentoCTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service InsDepartamentoC
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse insDepartamentoCTxn(
            com.keralty.aeconfiguraciones.viewmodel.direccionc.siu.InsDepartamentoCRequest request) throws ModelException {
        request.setDireccionCInsDepartamentoCpthisDireccionCInstance(direccionCSrv.getByOIDWithHV(request.getDireccionCInsDepartamentoCpthisDireccionC()));
        request.setDireccionCInsDepartamentoCpevcDepartamentoInstance(departamentoSrv.getByOIDWithHV(request.getDireccionCInsDepartamentoCpevcDepartamento()));
        return direccionCSrv.insDepartamentoCImplementation(request, true);
    }

    /*
     * Implements service InsDepartamentoC
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse insDepartamentoCImplementation(
            com.keralty.aeconfiguraciones.viewmodel.direccionc.siu.InsDepartamentoCRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        DireccionC direccionCInsDepartamentoCpthisDireccionC = request.getDireccionCInsDepartamentoCpthisDireccionCInstance();
        Departamento direccionCInsDepartamentoCpevcDepartamento = request.getDireccionCInsDepartamentoCpevcDepartamentoInstance();

        request.checkArguments();
        DireccionC instance = direccionCInsDepartamentoCpthisDireccionC;

        instance.add2DepartamentoC(direccionCInsDepartamentoCpevcDepartamento);
        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.departamento.siu.InsDepartamentoCRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.departamento.siu.InsDepartamentoCRequest();
            relatedRequest.setDepartamentoInsDepartamentoCpthisDepartamentoInstance(direccionCInsDepartamentoCpevcDepartamento);
            relatedRequest.setDepartamentoInsDepartamentoCpevcDireccionCInstance(direccionCInsDepartamentoCpthisDireccionC);
            departamentoSrv.insDepartamentoCImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));

        return response;
    }

    // Service: DelDepartamentoC

    /*
     * Entry point for service DelDepartamentoC
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse delDepartamentoC(
            com.keralty.aeconfiguraciones.viewmodel.direccionc.siu.DelDepartamentoCRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = direccionCSrv.delDepartamentoCTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service DelDepartamentoC
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse delDepartamentoCTxn(
            com.keralty.aeconfiguraciones.viewmodel.direccionc.siu.DelDepartamentoCRequest request) throws ModelException {
        request.setDireccionCDelDepartamentoCpthisDireccionCInstance(direccionCSrv.getByOIDWithHV(request.getDireccionCDelDepartamentoCpthisDireccionC()));
        request.setDireccionCDelDepartamentoCpevcDepartamentoInstance(departamentoSrv.getByOIDWithHV(request.getDireccionCDelDepartamentoCpevcDepartamento()));
        return direccionCSrv.delDepartamentoCImplementation(request, true);
    }

    /*
     * Implements service DelDepartamentoC
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse delDepartamentoCImplementation(
            com.keralty.aeconfiguraciones.viewmodel.direccionc.siu.DelDepartamentoCRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        DireccionC direccionCDelDepartamentoCpthisDireccionC = request.getDireccionCDelDepartamentoCpthisDireccionCInstance();
        Departamento direccionCDelDepartamentoCpevcDepartamento = request.getDireccionCDelDepartamentoCpevcDepartamentoInstance();

        request.checkArguments();
        DireccionC instance = direccionCDelDepartamentoCpthisDireccionC;

        instance.add2DepartamentoC(null);
        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.departamento.siu.DelDepartamentoCRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.departamento.siu.DelDepartamentoCRequest();
            relatedRequest.setDepartamentoDelDepartamentoCpthisDepartamentoInstance(direccionCDelDepartamentoCpevcDepartamento);
            relatedRequest.setDepartamentoDelDepartamentoCpevcDireccionCInstance(direccionCDelDepartamentoCpthisDireccionC);
            departamentoSrv.delDepartamentoCImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));

        return response;
    }

    // Service: InsMunicipioC

    /*
     * Entry point for service InsMunicipioC
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse insMunicipioC(
            com.keralty.aeconfiguraciones.viewmodel.direccionc.siu.InsMunicipioCRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = direccionCSrv.insMunicipioCTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service InsMunicipioC
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse insMunicipioCTxn(
            com.keralty.aeconfiguraciones.viewmodel.direccionc.siu.InsMunicipioCRequest request) throws ModelException {
        request.setDireccionCInsMunicipioCpthisDireccionCInstance(direccionCSrv.getByOIDWithHV(request.getDireccionCInsMunicipioCpthisDireccionC()));
        request.setDireccionCInsMunicipioCpevcMunicipioInstance(municipioSrv.getByOIDWithHV(request.getDireccionCInsMunicipioCpevcMunicipio()));
        return direccionCSrv.insMunicipioCImplementation(request, true);
    }

    /*
     * Implements service InsMunicipioC
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse insMunicipioCImplementation(
            com.keralty.aeconfiguraciones.viewmodel.direccionc.siu.InsMunicipioCRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        DireccionC direccionCInsMunicipioCpthisDireccionC = request.getDireccionCInsMunicipioCpthisDireccionCInstance();
        Municipio direccionCInsMunicipioCpevcMunicipio = request.getDireccionCInsMunicipioCpevcMunicipioInstance();

        request.checkArguments();
        DireccionC instance = direccionCInsMunicipioCpthisDireccionC;

        instance.add2MunicipioC(direccionCInsMunicipioCpevcMunicipio);
        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.municipio.siu.InsMunicipioCRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.municipio.siu.InsMunicipioCRequest();
            relatedRequest.setMunicipioInsMunicipioCpthisMunicipioInstance(direccionCInsMunicipioCpevcMunicipio);
            relatedRequest.setMunicipioInsMunicipioCpevcDireccionCInstance(direccionCInsMunicipioCpthisDireccionC);
            municipioSrv.insMunicipioCImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));

        return response;
    }

    // Service: DelMunicipioC

    /*
     * Entry point for service DelMunicipioC
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse delMunicipioC(
            com.keralty.aeconfiguraciones.viewmodel.direccionc.siu.DelMunicipioCRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = direccionCSrv.delMunicipioCTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service DelMunicipioC
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse delMunicipioCTxn(
            com.keralty.aeconfiguraciones.viewmodel.direccionc.siu.DelMunicipioCRequest request) throws ModelException {
        request.setDireccionCDelMunicipioCpthisDireccionCInstance(direccionCSrv.getByOIDWithHV(request.getDireccionCDelMunicipioCpthisDireccionC()));
        request.setDireccionCDelMunicipioCpevcMunicipioInstance(municipioSrv.getByOIDWithHV(request.getDireccionCDelMunicipioCpevcMunicipio()));
        return direccionCSrv.delMunicipioCImplementation(request, true);
    }

    /*
     * Implements service DelMunicipioC
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse delMunicipioCImplementation(
            com.keralty.aeconfiguraciones.viewmodel.direccionc.siu.DelMunicipioCRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        DireccionC direccionCDelMunicipioCpthisDireccionC = request.getDireccionCDelMunicipioCpthisDireccionCInstance();
        Municipio direccionCDelMunicipioCpevcMunicipio = request.getDireccionCDelMunicipioCpevcMunicipioInstance();

        request.checkArguments();
        DireccionC instance = direccionCDelMunicipioCpthisDireccionC;

        instance.add2MunicipioC(null);
        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.municipio.siu.DelMunicipioCRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.municipio.siu.DelMunicipioCRequest();
            relatedRequest.setMunicipioDelMunicipioCpthisMunicipioInstance(direccionCDelMunicipioCpevcMunicipio);
            relatedRequest.setMunicipioDelMunicipioCpevcDireccionCInstance(direccionCDelMunicipioCpthisDireccionC);
            municipioSrv.delMunicipioCImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));

        return response;
    }

    // Service: InsPaisC

    /*
     * Entry point for service InsPaisC
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse insPaisC(
            com.keralty.aeconfiguraciones.viewmodel.direccionc.siu.InsPaisCRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = direccionCSrv.insPaisCTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service InsPaisC
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse insPaisCTxn(
            com.keralty.aeconfiguraciones.viewmodel.direccionc.siu.InsPaisCRequest request) throws ModelException {
        request.setDireccionCInsPaisCpthisDireccionCInstance(direccionCSrv.getByOIDWithHV(request.getDireccionCInsPaisCpthisDireccionC()));
        request.setDireccionCInsPaisCpevcPaisInstance(paisSrv.getByOIDWithHV(request.getDireccionCInsPaisCpevcPais()));
        return direccionCSrv.insPaisCImplementation(request, true);
    }

    /*
     * Implements service InsPaisC
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse insPaisCImplementation(
            com.keralty.aeconfiguraciones.viewmodel.direccionc.siu.InsPaisCRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        DireccionC direccionCInsPaisCpthisDireccionC = request.getDireccionCInsPaisCpthisDireccionCInstance();
        Pais direccionCInsPaisCpevcPais = request.getDireccionCInsPaisCpevcPaisInstance();

        request.checkArguments();
        DireccionC instance = direccionCInsPaisCpthisDireccionC;

        instance.add2PaisC(direccionCInsPaisCpevcPais);
        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.pais.siu.InsPaisCRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.pais.siu.InsPaisCRequest();
            relatedRequest.setPaisInsPaisCpthisPaisInstance(direccionCInsPaisCpevcPais);
            relatedRequest.setPaisInsPaisCpevcDireccionCInstance(direccionCInsPaisCpthisDireccionC);
            paisSrv.insPaisCImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));

        return response;
    }

    // Service: DelPaisC

    /*
     * Entry point for service DelPaisC
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse delPaisC(
            com.keralty.aeconfiguraciones.viewmodel.direccionc.siu.DelPaisCRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = direccionCSrv.delPaisCTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service DelPaisC
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse delPaisCTxn(
            com.keralty.aeconfiguraciones.viewmodel.direccionc.siu.DelPaisCRequest request) throws ModelException {
        request.setDireccionCDelPaisCpthisDireccionCInstance(direccionCSrv.getByOIDWithHV(request.getDireccionCDelPaisCpthisDireccionC()));
        request.setDireccionCDelPaisCpevcPaisInstance(paisSrv.getByOIDWithHV(request.getDireccionCDelPaisCpevcPais()));
        return direccionCSrv.delPaisCImplementation(request, true);
    }

    /*
     * Implements service DelPaisC
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse delPaisCImplementation(
            com.keralty.aeconfiguraciones.viewmodel.direccionc.siu.DelPaisCRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        DireccionC direccionCDelPaisCpthisDireccionC = request.getDireccionCDelPaisCpthisDireccionCInstance();
        Pais direccionCDelPaisCpevcPais = request.getDireccionCDelPaisCpevcPaisInstance();

        request.checkArguments();
        DireccionC instance = direccionCDelPaisCpthisDireccionC;

        instance.add2PaisC(null);
        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.pais.siu.DelPaisCRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.pais.siu.DelPaisCRequest();
            relatedRequest.setPaisDelPaisCpthisPaisInstance(direccionCDelPaisCpevcPais);
            relatedRequest.setPaisDelPaisCpevcDireccionCInstance(direccionCDelPaisCpthisDireccionC);
            paisSrv.delPaisCImplementation(relatedRequest, false);
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
        select.getOrderByPart().addItem(Constants.normalizePath(DireccionCConstants.CLASS_NAME, DireccionCConstants.ATTR_FIELD_DIRECCIONCCODIGO, false), true);
    }
    
    @Override
    protected AbstractAppEntity buildRelatedInstance(String relatedInstanceJsonOid, String relatedInstancePathFirstItem, String remainingPath) {
        AbstractAppEntity relatedInstance = null;
        if (relatedInstancePathFirstItem.isEmpty()) {
            relatedInstance = buildDireccionCRelatedInstance(relatedInstanceJsonOid);
        }
        if (DireccionCConstants.ROLE_NAME_PERSONAC.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildDireccionCRelatedInstance4PersonaC(relatedInstanceJsonOid, remainingPath);
        }
        if (DireccionCConstants.ROLE_NAME_CONTRATOC.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildDireccionCRelatedInstance4ContratoC(relatedInstanceJsonOid, remainingPath);
        }
        if (DireccionCConstants.ROLE_NAME_TIPODIRECCIONC.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildDireccionCRelatedInstance4TipoDireccion(relatedInstanceJsonOid, remainingPath);
        }
        if (DireccionCConstants.ROLE_NAME_DEPARTAMENTOC.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildDireccionCRelatedInstance4Departamento(relatedInstanceJsonOid, remainingPath);
        }
        if (DireccionCConstants.ROLE_NAME_MUNICIPIOC.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildDireccionCRelatedInstance4Municipio(relatedInstanceJsonOid, remainingPath);
        }
        if (DireccionCConstants.ROLE_NAME_PAISC.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildDireccionCRelatedInstance4Pais(relatedInstanceJsonOid, remainingPath);
        }
        return relatedInstance;
    }
    
    private AbstractAppEntity buildDireccionCRelatedInstance(String relatedInstanceJsonOid) {
    	ObjectMapper mapper = new ObjectMapper();
    	try {
    		return new DireccionC(mapper.readValue(relatedInstanceJsonOid, DireccionCOid.class));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            throw new SystemException(e.getMessage());
    	}
    }

    private AbstractAppEntity buildDireccionCRelatedInstance4PersonaC(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new PersonaC(mapper.readValue(relatedInstanceJsonOid, PersonaCOid.class)): PersonaC.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            throw new SystemException(e.getMessage());
    	}
    }

    private AbstractAppEntity buildDireccionCRelatedInstance4ContratoC(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new ContratoC(mapper.readValue(relatedInstanceJsonOid, ContratoCOid.class)): ContratoC.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            throw new SystemException(e.getMessage());
    	}
    }

    private AbstractAppEntity buildDireccionCRelatedInstance4TipoDireccion(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new TipoDireccion(mapper.readValue(relatedInstanceJsonOid, TipoDireccionOid.class)): TipoDireccion.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            throw new SystemException(e.getMessage());
    	}
    }

    private AbstractAppEntity buildDireccionCRelatedInstance4Departamento(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new Departamento(mapper.readValue(relatedInstanceJsonOid, DepartamentoOid.class)): Departamento.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            throw new SystemException(e.getMessage());
    	}
    }

    private AbstractAppEntity buildDireccionCRelatedInstance4Municipio(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new Municipio(mapper.readValue(relatedInstanceJsonOid, MunicipioOid.class)): Municipio.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            throw new SystemException(e.getMessage());
    	}
    }

    private AbstractAppEntity buildDireccionCRelatedInstance4Pais(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new Pais(mapper.readValue(relatedInstanceJsonOid, PaisOid.class)): Pais.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            throw new SystemException(e.getMessage());
    	}
    }

    @Override
    protected void applyFilterByOid(String jsonOid, SQLSelect sql) {
        try {
            DireccionCOid oid = new DireccionCOid().fromJsonString(jsonOid);
            SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
            comparison1.setOperator("=");
            comparison1.setFirstOperand(new SQLWhereOperandPathAttribute(DireccionCConstants.ATTR_FIELD_DIRECCIONCCODIGO, Constants.Type.AUTONUMERIC.getTypeName()));
            comparison1.setSecondOperand(new SQLWhereOperandLiteral(oid.getDireccionCCodigo(), Constants.Type.AUTONUMERIC.getTypeName()));
            sql.addComparison(comparison1);
        } catch (Exception e) {
            LoggerFactory.getLogger(DireccionCService.class).error(e.getMessage());
            throw new SystemException(e.getMessage());
        }
    }

}
