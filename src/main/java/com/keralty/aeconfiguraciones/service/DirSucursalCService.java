package com.keralty.aeconfiguraciones.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.keralty.aeconfiguraciones.exception.ModelException;
import com.keralty.aeconfiguraciones.global.Constants;
import com.keralty.aeconfiguraciones.global.DirSucursalCConstants;
import com.keralty.aeconfiguraciones.persistence.AbstractAppEntity;
import com.keralty.aeconfiguraciones.persistence.Departamento;
import com.keralty.aeconfiguraciones.persistence.DirSucursalC;
import com.keralty.aeconfiguraciones.persistence.Municipio;
import com.keralty.aeconfiguraciones.persistence.oid.DepartamentoOid;
import com.keralty.aeconfiguraciones.persistence.oid.DirSucursalCOid;
import com.keralty.aeconfiguraciones.persistence.oid.MunicipioOid;
import com.keralty.aeconfiguraciones.persistence.oid.PaisOid;
import com.keralty.aeconfiguraciones.persistence.oid.PersonaCOid;
import com.keralty.aeconfiguraciones.persistence.oid.RegionalOid;
import com.keralty.aeconfiguraciones.persistence.oid.TipoSucursalCOid;
import com.keralty.aeconfiguraciones.persistence.Pais;
import com.keralty.aeconfiguraciones.persistence.PersonaC;
import com.keralty.aeconfiguraciones.persistence.Regional;
import com.keralty.aeconfiguraciones.persistence.TipoSucursalC;
import com.keralty.aeconfiguraciones.repository.DirSucursalCJpaRepository;
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
public class DirSucursalCService extends AbstractService {

    DirSucursalCJpaRepository repository;

    DirSucursalCService dirSucursalCSrv;

    TipoSucursalCService tipoSucursalCSrv;

    MunicipioService municipioSrv;

    DepartamentoService departamentoSrv;

    PaisService paisSrv;

    RegionalService regionalSrv;

    PersonaCService personaCSrv;

    @Inject
    public DirSucursalCService(
            DirSucursalCService dirSucursalCSrv,
            TipoSucursalCService tipoSucursalCSrv,
            MunicipioService municipioSrv,
            DepartamentoService departamentoSrv,
            PaisService paisSrv,
            RegionalService regionalSrv,
            PersonaCService personaCSrv,
            DirSucursalCJpaRepository repository) {
        this.dirSucursalCSrv = dirSucursalCSrv;
        this.tipoSucursalCSrv = tipoSucursalCSrv;
        this.municipioSrv = municipioSrv;
        this.departamentoSrv = departamentoSrv;
        this.paisSrv = paisSrv;
        this.regionalSrv = regionalSrv;
        this.personaCSrv = personaCSrv;
        this.repository = repository;
    }

    public List<Map<String, Object>> solveQuery(QueryRequest request) {
        return solveGenericQuery(request, DirSucursalCConstants.CLASS_NAME);
    }

    private List<DirSucursalC> solveDirSucursalCTypedQuery(QueryRequest request) {
        return repository.solveQuery(processQueryRequest(request, DirSucursalCConstants.CLASS_NAME), request.getPageSize(), request.getPageNumber());
    }
    
    private int solveDirSucursalCTypedCountQuery(QueryRequest request) {
        return repository.solveCountQuery(processCountQueryRequest(request, DirSucursalCConstants.CLASS_NAME));
    }

    /*
     * Returns the population of class DirSucursalC
     *
     * @return Population of class DirSucursalC
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<DirSucursalC> get() {
        List<DirSucursalC> results = new java.util.ArrayList<>();
        repository.findAll().forEach(results::add);
        return results;
    }
    /*
     * Returns the population of class DirSucursalC that matches certain search criteria
     *
     * @param queryRequest Search criteria
     *
     * @return Population of class DirSucursalC matching the search criteria
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<DirSucursalC> get(QueryRequest queryRequest) {
        return solveDirSucursalCTypedQuery(queryRequest);
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
            return new PopulationResponseViewModel(solveDirSucursalCTypedQuery(queryRequest).stream()
                    .map(instance -> buildDynamicObject(instance, queryRequest.getDisplaySetItems()))
                    .collect(Collectors.toList()), queryRequest.getPageSize() != 0 ? solveDirSucursalCTypedCountQuery(queryRequest) : 0);
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
        return new PopulationResponseViewModel(solveDirSucursalCTypedQuery(queryRequest).stream()
                .map(instance -> buildDynamicObject(instance, displaySetItems))
                .collect(Collectors.toList()));
    }

    /*
     * Returns an instance of DirSucursalC by ID
     *
     * @param dirSucursalCCodigo Identification field dirSucursalCCodigo
     *
     * @return Instance of DirSucursalC
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public DirSucursalC getById(Long dirSucursalCCodigo) {
        return getByOID(new DirSucursalCOid(dirSucursalCCodigo));
    }
    
    /*
     * Returns an instance of DirSucursalC by OID
     *
     * @param oid Object IDentifier
     *
     * @return Instance of DirSucursalC
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public DirSucursalC getByOID(DirSucursalCOid oid) {
        Optional<DirSucursalC> instance = getInstanceByOid(oid);
        return instance.isPresent() ? instance.get() : null;
    }
    
    /*
     * Returns an instance of DirSucursalC by OID applying horizontal visibility constraints
     *
     * @param oid Object IDentifier
     *
     * @return Instance of DirSucursalC
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public DirSucursalC getByOIDWithHV(DirSucursalCOid oid) {
        Optional<DirSucursalC> instance = getInstanceByOid(oid);
    	return instance.isPresent() && instance.get().checkHorizontalVisibility(getAgentInstance()) 
    		 ? instance.get() 
    		 : null;
    }
    
    private Optional<DirSucursalC> getInstanceByOid(DirSucursalCOid oid) {
        return oid != null && oid.getDirSucursalCCodigo() != null
               ? repository.findById(oid.getDirSucursalCCodigo())
               : Optional.empty();
    }

    /*
     * Returns an instance of DirSucursalC by OID in JSON format
     *
     * @param jsonOid Object IDentifier in JSON format
     *
     * @return Instance of DirSucursalC
     */
    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public DirSucursalC getByJsonOid(String jsonOid) {
        DirSucursalCOid oid = new DirSucursalCOid();
        oid.fromJsonString(jsonOid);
        return getByOIDWithHV(oid);
    }

    /*
     * Returns the list of instances of DirSucursalC related to a given instance through 
     * a given path
     *
     * @param relatedInstance Related instance
     *
     * @param relatedInstancePath Related instance path
     *
     * @return List of instances of DirSucursalC
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<DirSucursalC> getByRelated(AbstractAppEntity relatedInstance, String relatedInstancePath) {
        QueryRequest queryRequest = new QueryRequest(new FilterRequest(Constants.FILTER_BY_RELATED,
                Arrays.asList(new FilterVariableRequest(Constants.FILTER_BY_RELATED_FVAR_PATH, relatedInstancePath),
                        new FilterVariableRequest(Constants.FILTER_BY_RELATED_FVAR_OID, relatedInstance.getOid().toJson()))));
        return solveDirSucursalCTypedQuery(queryRequest);
    }

    /*
     * Returns the list of instances of DirSucursalC related to a given instance through
     * a given path
     *
     * @param relatedInstance Related instance
     *
     * @param relatedInstancePath Related instance path
     *
     * @return List of instances of DirSucursalC
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<DirSucursalC> getInstancesByRelated(AbstractAppEntity relatedInstance, String relatedInstancePath) {
        return getByRelated(relatedInstance, relatedInstancePath);
    }

    /*
     * Returns a set of displayset items for an instance of DirSucursalC
     *
     * @param oid Object IDentifier
     *
     * @param displaySetItems List of comma-separated displayset items
     *
     * @return Set of displayset items for an instance of DirSucursalC
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public InstanceResponseViewModel getDirSucursalCDynamic(DirSucursalCOid oid, String displaySetItems) {
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
    public String getSuppInfo(DirSucursalCOid oid, String dsName) {
        DirSucursalC instance = getByOIDWithHV(oid);
        return (instance != null ? getSuppInfo(instance, dsName): "");
    }

    private String getSuppInfo(DirSucursalC instance, String dsName) {
        if (instance == null || instance.isNull()) return "";
        return dsName.isEmpty() ? instance.getOid().toJson() : "";
    }




    // IIU Methods



    // Service: InsTiposSucursalC

    /*
     * Entry point for service InsTiposSucursalC
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse insTiposSucursalC(
            com.keralty.aeconfiguraciones.viewmodel.dirsucursalc.siu.InsTiposSucursalCRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = dirSucursalCSrv.insTiposSucursalCTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service InsTiposSucursalC
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse insTiposSucursalCTxn(
            com.keralty.aeconfiguraciones.viewmodel.dirsucursalc.siu.InsTiposSucursalCRequest request) throws ModelException {
        request.setDirSucursalCInsTiposSucursalCpthisDirSucursalCInstance(dirSucursalCSrv.getByOIDWithHV(request.getDirSucursalCInsTiposSucursalCpthisDirSucursalC()));
        request.setDirSucursalCInsTiposSucursalCpevcTipoSucursalCInstance(tipoSucursalCSrv.getByOIDWithHV(request.getDirSucursalCInsTiposSucursalCpevcTipoSucursalC()));
        return dirSucursalCSrv.insTiposSucursalCImplementation(request, true);
    }

    /*
     * Implements service InsTiposSucursalC
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse insTiposSucursalCImplementation(
            com.keralty.aeconfiguraciones.viewmodel.dirsucursalc.siu.InsTiposSucursalCRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        DirSucursalC dirSucursalCInsTiposSucursalCpthisDirSucursalC = request.getDirSucursalCInsTiposSucursalCpthisDirSucursalCInstance();
        TipoSucursalC dirSucursalCInsTiposSucursalCpevcTipoSucursalC = request.getDirSucursalCInsTiposSucursalCpevcTipoSucursalCInstance();

        request.checkArguments();
        DirSucursalC instance = dirSucursalCInsTiposSucursalCpthisDirSucursalC;

        instance.add2TiposSucursalC(dirSucursalCInsTiposSucursalCpevcTipoSucursalC);
        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.tiposucursalc.siu.InsTiposSucursalCRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.tiposucursalc.siu.InsTiposSucursalCRequest();
            relatedRequest.setTipoSucursalCInsTiposSucursalCpthisTipoSucursalCInstance(dirSucursalCInsTiposSucursalCpevcTipoSucursalC);
            relatedRequest.setTipoSucursalCInsTiposSucursalCpevcDirSucursalCInstance(dirSucursalCInsTiposSucursalCpthisDirSucursalC);
            tipoSucursalCSrv.insTiposSucursalCImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));

        return response;
    }

    // Service: DelTiposSucursalC

    /*
     * Entry point for service DelTiposSucursalC
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse delTiposSucursalC(
            com.keralty.aeconfiguraciones.viewmodel.dirsucursalc.siu.DelTiposSucursalCRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = dirSucursalCSrv.delTiposSucursalCTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service DelTiposSucursalC
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse delTiposSucursalCTxn(
            com.keralty.aeconfiguraciones.viewmodel.dirsucursalc.siu.DelTiposSucursalCRequest request) throws ModelException {
        request.setDirSucursalCDelTiposSucursalCpthisDirSucursalCInstance(dirSucursalCSrv.getByOIDWithHV(request.getDirSucursalCDelTiposSucursalCpthisDirSucursalC()));
        request.setDirSucursalCDelTiposSucursalCpevcTipoSucursalCInstance(tipoSucursalCSrv.getByOIDWithHV(request.getDirSucursalCDelTiposSucursalCpevcTipoSucursalC()));
        return dirSucursalCSrv.delTiposSucursalCImplementation(request, true);
    }

    /*
     * Implements service DelTiposSucursalC
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse delTiposSucursalCImplementation(
            com.keralty.aeconfiguraciones.viewmodel.dirsucursalc.siu.DelTiposSucursalCRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        DirSucursalC dirSucursalCDelTiposSucursalCpthisDirSucursalC = request.getDirSucursalCDelTiposSucursalCpthisDirSucursalCInstance();
        TipoSucursalC dirSucursalCDelTiposSucursalCpevcTipoSucursalC = request.getDirSucursalCDelTiposSucursalCpevcTipoSucursalCInstance();

        request.checkArguments();
        DirSucursalC instance = dirSucursalCDelTiposSucursalCpthisDirSucursalC;

        instance.removeFromTiposSucursalC(dirSucursalCDelTiposSucursalCpevcTipoSucursalC);
        dirSucursalCDelTiposSucursalCpevcTipoSucursalC.getDirSucursalesC().remove(instance);
        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.tiposucursalc.siu.DelTiposSucursalCRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.tiposucursalc.siu.DelTiposSucursalCRequest();
            relatedRequest.setTipoSucursalCDelTiposSucursalCpthisTipoSucursalCInstance(dirSucursalCDelTiposSucursalCpevcTipoSucursalC);
            relatedRequest.setTipoSucursalCDelTiposSucursalCpevcDirSucursalCInstance(dirSucursalCDelTiposSucursalCpthisDirSucursalC);
            tipoSucursalCSrv.delTiposSucursalCImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));

        return response;
    }

    // Service: InsMunicipioDirSucursal

    /*
     * Entry point for service InsMunicipioDirSucursal
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse insMunicipioDirSucursal(
            com.keralty.aeconfiguraciones.viewmodel.dirsucursalc.siu.InsMunicipioDirSucursalRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = dirSucursalCSrv.insMunicipioDirSucursalTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service InsMunicipioDirSucursal
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse insMunicipioDirSucursalTxn(
            com.keralty.aeconfiguraciones.viewmodel.dirsucursalc.siu.InsMunicipioDirSucursalRequest request) throws ModelException {
        request.setDirSucursalCInsMunicipioDirSucursalpthisDirSucursalCInstance(dirSucursalCSrv.getByOIDWithHV(request.getDirSucursalCInsMunicipioDirSucursalpthisDirSucursalC()));
        request.setDirSucursalCInsMunicipioDirSucursalpevcMunicipioInstance(municipioSrv.getByOIDWithHV(request.getDirSucursalCInsMunicipioDirSucursalpevcMunicipio()));
        return dirSucursalCSrv.insMunicipioDirSucursalImplementation(request, true);
    }

    /*
     * Implements service InsMunicipioDirSucursal
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse insMunicipioDirSucursalImplementation(
            com.keralty.aeconfiguraciones.viewmodel.dirsucursalc.siu.InsMunicipioDirSucursalRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        DirSucursalC dirSucursalCInsMunicipioDirSucursalpthisDirSucursalC = request.getDirSucursalCInsMunicipioDirSucursalpthisDirSucursalCInstance();
        Municipio dirSucursalCInsMunicipioDirSucursalpevcMunicipio = request.getDirSucursalCInsMunicipioDirSucursalpevcMunicipioInstance();

        request.checkArguments();
        DirSucursalC instance = dirSucursalCInsMunicipioDirSucursalpthisDirSucursalC;

        instance.add2MunicipioC(dirSucursalCInsMunicipioDirSucursalpevcMunicipio);
        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.municipio.siu.InsMunicipioDirSucursalRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.municipio.siu.InsMunicipioDirSucursalRequest();
            relatedRequest.setMunicipioInsMunicipioDirSucursalpthisMunicipioInstance(dirSucursalCInsMunicipioDirSucursalpevcMunicipio);
            relatedRequest.setMunicipioInsMunicipioDirSucursalpevcDirSucursalCInstance(dirSucursalCInsMunicipioDirSucursalpthisDirSucursalC);
            municipioSrv.insMunicipioDirSucursalImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));

        return response;
    }

    // Service: DelMunicipioDirSucursal

    /*
     * Entry point for service DelMunicipioDirSucursal
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse delMunicipioDirSucursal(
            com.keralty.aeconfiguraciones.viewmodel.dirsucursalc.siu.DelMunicipioDirSucursalRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = dirSucursalCSrv.delMunicipioDirSucursalTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service DelMunicipioDirSucursal
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse delMunicipioDirSucursalTxn(
            com.keralty.aeconfiguraciones.viewmodel.dirsucursalc.siu.DelMunicipioDirSucursalRequest request) throws ModelException {
        request.setDirSucursalCDelMunicipioDirSucursalpthisDirSucursalCInstance(dirSucursalCSrv.getByOIDWithHV(request.getDirSucursalCDelMunicipioDirSucursalpthisDirSucursalC()));
        request.setDirSucursalCDelMunicipioDirSucursalpevcMunicipioInstance(municipioSrv.getByOIDWithHV(request.getDirSucursalCDelMunicipioDirSucursalpevcMunicipio()));
        return dirSucursalCSrv.delMunicipioDirSucursalImplementation(request, true);
    }

    /*
     * Implements service DelMunicipioDirSucursal
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse delMunicipioDirSucursalImplementation(
            com.keralty.aeconfiguraciones.viewmodel.dirsucursalc.siu.DelMunicipioDirSucursalRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        DirSucursalC dirSucursalCDelMunicipioDirSucursalpthisDirSucursalC = request.getDirSucursalCDelMunicipioDirSucursalpthisDirSucursalCInstance();
        Municipio dirSucursalCDelMunicipioDirSucursalpevcMunicipio = request.getDirSucursalCDelMunicipioDirSucursalpevcMunicipioInstance();

        request.checkArguments();
        DirSucursalC instance = dirSucursalCDelMunicipioDirSucursalpthisDirSucursalC;

        instance.add2MunicipioC(null);
        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.municipio.siu.DelMunicipioDirSucursalRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.municipio.siu.DelMunicipioDirSucursalRequest();
            relatedRequest.setMunicipioDelMunicipioDirSucursalpthisMunicipioInstance(dirSucursalCDelMunicipioDirSucursalpevcMunicipio);
            relatedRequest.setMunicipioDelMunicipioDirSucursalpevcDirSucursalCInstance(dirSucursalCDelMunicipioDirSucursalpthisDirSucursalC);
            municipioSrv.delMunicipioDirSucursalImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));

        return response;
    }

    // Service: InsDepartamentoDirSucursalC

    /*
     * Entry point for service InsDepartamentoDirSucursalC
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse insDepartamentoDirSucursalC(
            com.keralty.aeconfiguraciones.viewmodel.dirsucursalc.siu.InsDepartamentoDirSucursalCRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = dirSucursalCSrv.insDepartamentoDirSucursalCTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service InsDepartamentoDirSucursalC
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse insDepartamentoDirSucursalCTxn(
            com.keralty.aeconfiguraciones.viewmodel.dirsucursalc.siu.InsDepartamentoDirSucursalCRequest request) throws ModelException {
        request.setDirSucursalCInsDepartamentoDirSucursalCpthisDirSucursalCInstance(dirSucursalCSrv.getByOIDWithHV(request.getDirSucursalCInsDepartamentoDirSucursalCpthisDirSucursalC()));
        request.setDirSucursalCInsDepartamentoDirSucursalCpevcDepartamentoInstance(departamentoSrv.getByOIDWithHV(request.getDirSucursalCInsDepartamentoDirSucursalCpevcDepartamento()));
        return dirSucursalCSrv.insDepartamentoDirSucursalCImplementation(request, true);
    }

    /*
     * Implements service InsDepartamentoDirSucursalC
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse insDepartamentoDirSucursalCImplementation(
            com.keralty.aeconfiguraciones.viewmodel.dirsucursalc.siu.InsDepartamentoDirSucursalCRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        DirSucursalC dirSucursalCInsDepartamentoDirSucursalCpthisDirSucursalC = request.getDirSucursalCInsDepartamentoDirSucursalCpthisDirSucursalCInstance();
        Departamento dirSucursalCInsDepartamentoDirSucursalCpevcDepartamento = request.getDirSucursalCInsDepartamentoDirSucursalCpevcDepartamentoInstance();

        request.checkArguments();
        DirSucursalC instance = dirSucursalCInsDepartamentoDirSucursalCpthisDirSucursalC;

        instance.add2DepartamentoC(dirSucursalCInsDepartamentoDirSucursalCpevcDepartamento);
        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.departamento.siu.InsDepartamentoDirSucursalCRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.departamento.siu.InsDepartamentoDirSucursalCRequest();
            relatedRequest.setDepartamentoInsDepartamentoDirSucursalCpthisDepartamentoInstance(dirSucursalCInsDepartamentoDirSucursalCpevcDepartamento);
            relatedRequest.setDepartamentoInsDepartamentoDirSucursalCpevcDirSucursalCInstance(dirSucursalCInsDepartamentoDirSucursalCpthisDirSucursalC);
            departamentoSrv.insDepartamentoDirSucursalCImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));

        return response;
    }

    // Service: DelDepartamentoDirSucursalC

    /*
     * Entry point for service DelDepartamentoDirSucursalC
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse delDepartamentoDirSucursalC(
            com.keralty.aeconfiguraciones.viewmodel.dirsucursalc.siu.DelDepartamentoDirSucursalCRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = dirSucursalCSrv.delDepartamentoDirSucursalCTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service DelDepartamentoDirSucursalC
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse delDepartamentoDirSucursalCTxn(
            com.keralty.aeconfiguraciones.viewmodel.dirsucursalc.siu.DelDepartamentoDirSucursalCRequest request) throws ModelException {
        request.setDirSucursalCDelDepartamentoDirSucursalCpthisDirSucursalCInstance(dirSucursalCSrv.getByOIDWithHV(request.getDirSucursalCDelDepartamentoDirSucursalCpthisDirSucursalC()));
        request.setDirSucursalCDelDepartamentoDirSucursalCpevcDepartamentoInstance(departamentoSrv.getByOIDWithHV(request.getDirSucursalCDelDepartamentoDirSucursalCpevcDepartamento()));
        return dirSucursalCSrv.delDepartamentoDirSucursalCImplementation(request, true);
    }

    /*
     * Implements service DelDepartamentoDirSucursalC
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse delDepartamentoDirSucursalCImplementation(
            com.keralty.aeconfiguraciones.viewmodel.dirsucursalc.siu.DelDepartamentoDirSucursalCRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        DirSucursalC dirSucursalCDelDepartamentoDirSucursalCpthisDirSucursalC = request.getDirSucursalCDelDepartamentoDirSucursalCpthisDirSucursalCInstance();
        Departamento dirSucursalCDelDepartamentoDirSucursalCpevcDepartamento = request.getDirSucursalCDelDepartamentoDirSucursalCpevcDepartamentoInstance();

        request.checkArguments();
        DirSucursalC instance = dirSucursalCDelDepartamentoDirSucursalCpthisDirSucursalC;

        instance.add2DepartamentoC(null);
        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.departamento.siu.DelDepartamentoDirSucursalCRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.departamento.siu.DelDepartamentoDirSucursalCRequest();
            relatedRequest.setDepartamentoDelDepartamentoDirSucursalCpthisDepartamentoInstance(dirSucursalCDelDepartamentoDirSucursalCpevcDepartamento);
            relatedRequest.setDepartamentoDelDepartamentoDirSucursalCpevcDirSucursalCInstance(dirSucursalCDelDepartamentoDirSucursalCpthisDirSucursalC);
            departamentoSrv.delDepartamentoDirSucursalCImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));

        return response;
    }

    // Service: InsPaisDirSucursalC

    /*
     * Entry point for service InsPaisDirSucursalC
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse insPaisDirSucursalC(
            com.keralty.aeconfiguraciones.viewmodel.dirsucursalc.siu.InsPaisDirSucursalCRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = dirSucursalCSrv.insPaisDirSucursalCTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service InsPaisDirSucursalC
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse insPaisDirSucursalCTxn(
            com.keralty.aeconfiguraciones.viewmodel.dirsucursalc.siu.InsPaisDirSucursalCRequest request) throws ModelException {
        request.setDirSucursalCInsPaisDirSucursalCpthisDirSucursalCInstance(dirSucursalCSrv.getByOIDWithHV(request.getDirSucursalCInsPaisDirSucursalCpthisDirSucursalC()));
        request.setDirSucursalCInsPaisDirSucursalCpevcPaisInstance(paisSrv.getByOIDWithHV(request.getDirSucursalCInsPaisDirSucursalCpevcPais()));
        return dirSucursalCSrv.insPaisDirSucursalCImplementation(request, true);
    }

    /*
     * Implements service InsPaisDirSucursalC
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse insPaisDirSucursalCImplementation(
            com.keralty.aeconfiguraciones.viewmodel.dirsucursalc.siu.InsPaisDirSucursalCRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        DirSucursalC dirSucursalCInsPaisDirSucursalCpthisDirSucursalC = request.getDirSucursalCInsPaisDirSucursalCpthisDirSucursalCInstance();
        Pais dirSucursalCInsPaisDirSucursalCpevcPais = request.getDirSucursalCInsPaisDirSucursalCpevcPaisInstance();

        request.checkArguments();
        DirSucursalC instance = dirSucursalCInsPaisDirSucursalCpthisDirSucursalC;

        instance.add2PaisC(dirSucursalCInsPaisDirSucursalCpevcPais);
        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.pais.siu.InsPaisDirSucursalCRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.pais.siu.InsPaisDirSucursalCRequest();
            relatedRequest.setPaisInsPaisDirSucursalCpthisPaisInstance(dirSucursalCInsPaisDirSucursalCpevcPais);
            relatedRequest.setPaisInsPaisDirSucursalCpevcDirSucursalCInstance(dirSucursalCInsPaisDirSucursalCpthisDirSucursalC);
            paisSrv.insPaisDirSucursalCImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));

        return response;
    }

    // Service: DelPaisDirSucursalC

    /*
     * Entry point for service DelPaisDirSucursalC
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse delPaisDirSucursalC(
            com.keralty.aeconfiguraciones.viewmodel.dirsucursalc.siu.DelPaisDirSucursalCRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = dirSucursalCSrv.delPaisDirSucursalCTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service DelPaisDirSucursalC
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse delPaisDirSucursalCTxn(
            com.keralty.aeconfiguraciones.viewmodel.dirsucursalc.siu.DelPaisDirSucursalCRequest request) throws ModelException {
        request.setDirSucursalCDelPaisDirSucursalCpthisDirSucursalCInstance(dirSucursalCSrv.getByOIDWithHV(request.getDirSucursalCDelPaisDirSucursalCpthisDirSucursalC()));
        request.setDirSucursalCDelPaisDirSucursalCpevcPaisInstance(paisSrv.getByOIDWithHV(request.getDirSucursalCDelPaisDirSucursalCpevcPais()));
        return dirSucursalCSrv.delPaisDirSucursalCImplementation(request, true);
    }

    /*
     * Implements service DelPaisDirSucursalC
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse delPaisDirSucursalCImplementation(
            com.keralty.aeconfiguraciones.viewmodel.dirsucursalc.siu.DelPaisDirSucursalCRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        DirSucursalC dirSucursalCDelPaisDirSucursalCpthisDirSucursalC = request.getDirSucursalCDelPaisDirSucursalCpthisDirSucursalCInstance();
        Pais dirSucursalCDelPaisDirSucursalCpevcPais = request.getDirSucursalCDelPaisDirSucursalCpevcPaisInstance();

        request.checkArguments();
        DirSucursalC instance = dirSucursalCDelPaisDirSucursalCpthisDirSucursalC;

        instance.add2PaisC(null);
        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.pais.siu.DelPaisDirSucursalCRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.pais.siu.DelPaisDirSucursalCRequest();
            relatedRequest.setPaisDelPaisDirSucursalCpthisPaisInstance(dirSucursalCDelPaisDirSucursalCpevcPais);
            relatedRequest.setPaisDelPaisDirSucursalCpevcDirSucursalCInstance(dirSucursalCDelPaisDirSucursalCpthisDirSucursalC);
            paisSrv.delPaisDirSucursalCImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));

        return response;
    }

    // Service: InsRegionalDirSucursal

    /*
     * Entry point for service InsRegionalDirSucursal
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse insRegionalDirSucursal(
            com.keralty.aeconfiguraciones.viewmodel.dirsucursalc.siu.InsRegionalDirSucursalRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = dirSucursalCSrv.insRegionalDirSucursalTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service InsRegionalDirSucursal
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse insRegionalDirSucursalTxn(
            com.keralty.aeconfiguraciones.viewmodel.dirsucursalc.siu.InsRegionalDirSucursalRequest request) throws ModelException {
        request.setDirSucursalCInsRegionalDirSucursalpthisDirSucursalCInstance(dirSucursalCSrv.getByOIDWithHV(request.getDirSucursalCInsRegionalDirSucursalpthisDirSucursalC()));
        request.setDirSucursalCInsRegionalDirSucursalpevcRegionalInstance(regionalSrv.getByOIDWithHV(request.getDirSucursalCInsRegionalDirSucursalpevcRegional()));
        return dirSucursalCSrv.insRegionalDirSucursalImplementation(request, true);
    }

    /*
     * Implements service InsRegionalDirSucursal
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse insRegionalDirSucursalImplementation(
            com.keralty.aeconfiguraciones.viewmodel.dirsucursalc.siu.InsRegionalDirSucursalRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        DirSucursalC dirSucursalCInsRegionalDirSucursalpthisDirSucursalC = request.getDirSucursalCInsRegionalDirSucursalpthisDirSucursalCInstance();
        Regional dirSucursalCInsRegionalDirSucursalpevcRegional = request.getDirSucursalCInsRegionalDirSucursalpevcRegionalInstance();

        request.checkArguments();
        DirSucursalC instance = dirSucursalCInsRegionalDirSucursalpthisDirSucursalC;

        instance.add2RegionalC(dirSucursalCInsRegionalDirSucursalpevcRegional);
        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.regional.siu.InsRegionalDirSucursalRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.regional.siu.InsRegionalDirSucursalRequest();
            relatedRequest.setRegionalInsRegionalDirSucursalpthisRegionalInstance(dirSucursalCInsRegionalDirSucursalpevcRegional);
            relatedRequest.setRegionalInsRegionalDirSucursalpevcDirSucursalCInstance(dirSucursalCInsRegionalDirSucursalpthisDirSucursalC);
            regionalSrv.insRegionalDirSucursalImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));

        return response;
    }

    // Service: DelRegionalDirSucursal

    /*
     * Entry point for service DelRegionalDirSucursal
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse delRegionalDirSucursal(
            com.keralty.aeconfiguraciones.viewmodel.dirsucursalc.siu.DelRegionalDirSucursalRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = dirSucursalCSrv.delRegionalDirSucursalTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service DelRegionalDirSucursal
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse delRegionalDirSucursalTxn(
            com.keralty.aeconfiguraciones.viewmodel.dirsucursalc.siu.DelRegionalDirSucursalRequest request) throws ModelException {
        request.setDirSucursalCDelRegionalDirSucursalpthisDirSucursalCInstance(dirSucursalCSrv.getByOIDWithHV(request.getDirSucursalCDelRegionalDirSucursalpthisDirSucursalC()));
        request.setDirSucursalCDelRegionalDirSucursalpevcRegionalInstance(regionalSrv.getByOIDWithHV(request.getDirSucursalCDelRegionalDirSucursalpevcRegional()));
        return dirSucursalCSrv.delRegionalDirSucursalImplementation(request, true);
    }

    /*
     * Implements service DelRegionalDirSucursal
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse delRegionalDirSucursalImplementation(
            com.keralty.aeconfiguraciones.viewmodel.dirsucursalc.siu.DelRegionalDirSucursalRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        DirSucursalC dirSucursalCDelRegionalDirSucursalpthisDirSucursalC = request.getDirSucursalCDelRegionalDirSucursalpthisDirSucursalCInstance();
        Regional dirSucursalCDelRegionalDirSucursalpevcRegional = request.getDirSucursalCDelRegionalDirSucursalpevcRegionalInstance();

        request.checkArguments();
        DirSucursalC instance = dirSucursalCDelRegionalDirSucursalpthisDirSucursalC;

        instance.add2RegionalC(null);
        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.regional.siu.DelRegionalDirSucursalRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.regional.siu.DelRegionalDirSucursalRequest();
            relatedRequest.setRegionalDelRegionalDirSucursalpthisRegionalInstance(dirSucursalCDelRegionalDirSucursalpevcRegional);
            relatedRequest.setRegionalDelRegionalDirSucursalpevcDirSucursalCInstance(dirSucursalCDelRegionalDirSucursalpthisDirSucursalC);
            regionalSrv.delRegionalDirSucursalImplementation(relatedRequest, false);
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
        select.getOrderByPart().addItem(Constants.normalizePath(DirSucursalCConstants.CLASS_NAME, DirSucursalCConstants.ATTR_FIELD_DIRSUCURSALCCODIGO, false), true);
    }
    
    @Override
    protected AbstractAppEntity buildRelatedInstance(String relatedInstanceJsonOid, String relatedInstancePathFirstItem, String remainingPath) {
        AbstractAppEntity relatedInstance = null;
        if (relatedInstancePathFirstItem.isEmpty()) {
            relatedInstance = buildDirSucursalCRelatedInstance(relatedInstanceJsonOid);
        }
        if (DirSucursalCConstants.ROLE_NAME_PERSONAC.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildDirSucursalCRelatedInstance4PersonaC(relatedInstanceJsonOid, remainingPath);
        }
        if (DirSucursalCConstants.ROLE_NAME_TIPOSSUCURSALC.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildDirSucursalCRelatedInstance4TipoSucursalC(relatedInstanceJsonOid, remainingPath);
        }
        if (DirSucursalCConstants.ROLE_NAME_MUNICIPIOC.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildDirSucursalCRelatedInstance4Municipio(relatedInstanceJsonOid, remainingPath);
        }
        if (DirSucursalCConstants.ROLE_NAME_DEPARTAMENTOC.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildDirSucursalCRelatedInstance4Departamento(relatedInstanceJsonOid, remainingPath);
        }
        if (DirSucursalCConstants.ROLE_NAME_PAISC.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildDirSucursalCRelatedInstance4Pais(relatedInstanceJsonOid, remainingPath);
        }
        if (DirSucursalCConstants.ROLE_NAME_REGIONALC.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildDirSucursalCRelatedInstance4Regional(relatedInstanceJsonOid, remainingPath);
        }
        return relatedInstance;
    }
    
    private AbstractAppEntity buildDirSucursalCRelatedInstance(String relatedInstanceJsonOid) {
    	ObjectMapper mapper = new ObjectMapper();
    	try {
    		return new DirSucursalC(mapper.readValue(relatedInstanceJsonOid, DirSucursalCOid.class));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    private AbstractAppEntity buildDirSucursalCRelatedInstance4PersonaC(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new PersonaC(mapper.readValue(relatedInstanceJsonOid, PersonaCOid.class)): PersonaC.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    private AbstractAppEntity buildDirSucursalCRelatedInstance4TipoSucursalC(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new TipoSucursalC(mapper.readValue(relatedInstanceJsonOid, TipoSucursalCOid.class)): TipoSucursalC.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    private AbstractAppEntity buildDirSucursalCRelatedInstance4Municipio(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new Municipio(mapper.readValue(relatedInstanceJsonOid, MunicipioOid.class)): Municipio.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    private AbstractAppEntity buildDirSucursalCRelatedInstance4Departamento(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new Departamento(mapper.readValue(relatedInstanceJsonOid, DepartamentoOid.class)): Departamento.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    private AbstractAppEntity buildDirSucursalCRelatedInstance4Pais(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new Pais(mapper.readValue(relatedInstanceJsonOid, PaisOid.class)): Pais.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    private AbstractAppEntity buildDirSucursalCRelatedInstance4Regional(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new Regional(mapper.readValue(relatedInstanceJsonOid, RegionalOid.class)): Regional.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    @Override
    protected void applyFilterByOid(String jsonOid, SQLSelect sql) {
        try {
            DirSucursalCOid oid = new DirSucursalCOid().fromJsonString(jsonOid);
            SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
            comparison1.setOperator("=");
            comparison1.setFirstOperand(new SQLWhereOperandPathAttribute(DirSucursalCConstants.ATTR_FIELD_DIRSUCURSALCCODIGO, Constants.Type.AUTONUMERIC.getTypeName()));
            comparison1.setSecondOperand(new SQLWhereOperandLiteral(oid.getDirSucursalCCodigo(), Constants.Type.AUTONUMERIC.getTypeName()));
            sql.addComparison(comparison1);
        } catch (Exception e) {
            LoggerFactory.getLogger(DirSucursalCService.class).error(e.getMessage());
        }
    }

}
