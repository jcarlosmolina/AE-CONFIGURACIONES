package com.keralty.aeconfiguraciones.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.keralty.aeconfiguraciones.exception.ModelException;
import com.keralty.aeconfiguraciones.global.Constants;
import com.keralty.aeconfiguraciones.global.ContratoCConstants;
import com.keralty.aeconfiguraciones.global.UserFunctions;
import com.keralty.aeconfiguraciones.persistence.AbstractAppEntity;
import com.keralty.aeconfiguraciones.persistence.Asesor;
import com.keralty.aeconfiguraciones.persistence.BeneficiarioC;
import com.keralty.aeconfiguraciones.persistence.ContratoC;
import com.keralty.aeconfiguraciones.persistence.DireccionC;
import com.keralty.aeconfiguraciones.persistence.EstadoContrato;
import com.keralty.aeconfiguraciones.persistence.FamiliaC;
import com.keralty.aeconfiguraciones.persistence.FormaPago;
import com.keralty.aeconfiguraciones.persistence.GrupoAsociado;
import com.keralty.aeconfiguraciones.persistence.Municipio;
import com.keralty.aeconfiguraciones.persistence.oid.AsesorOid;
import com.keralty.aeconfiguraciones.persistence.oid.BeneficiarioCOid;
import com.keralty.aeconfiguraciones.persistence.oid.ContratoCOid;
import com.keralty.aeconfiguraciones.persistence.oid.DireccionCOid;
import com.keralty.aeconfiguraciones.persistence.oid.EstadoContratoOid;
import com.keralty.aeconfiguraciones.persistence.oid.FamiliaCOid;
import com.keralty.aeconfiguraciones.persistence.oid.FormaPagoOid;
import com.keralty.aeconfiguraciones.persistence.oid.GrupoAsociadoOid;
import com.keralty.aeconfiguraciones.persistence.oid.MunicipioOid;
import com.keralty.aeconfiguraciones.persistence.oid.PeriodicidadOid;
import com.keralty.aeconfiguraciones.persistence.oid.PersonaCOid;
import com.keralty.aeconfiguraciones.persistence.oid.PlanesOid;
import com.keralty.aeconfiguraciones.persistence.oid.ProductosOid;
import com.keralty.aeconfiguraciones.persistence.oid.SolicitudOid;
import com.keralty.aeconfiguraciones.persistence.oid.SucursalOid;
import com.keralty.aeconfiguraciones.persistence.oid.TarifaOid;
import com.keralty.aeconfiguraciones.persistence.Periodicidad;
import com.keralty.aeconfiguraciones.persistence.PersonaC;
import com.keralty.aeconfiguraciones.persistence.Planes;
import com.keralty.aeconfiguraciones.persistence.Productos;
import com.keralty.aeconfiguraciones.persistence.Solicitud;
import com.keralty.aeconfiguraciones.persistence.Sucursal;
import com.keralty.aeconfiguraciones.persistence.Tarifa;
import com.keralty.aeconfiguraciones.repository.ContratoCJpaRepository;
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
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.LoggerFactory;

@Transactional
@ApplicationScoped
public class ContratoCService extends AbstractService {

    ContratoCJpaRepository repository;

    ContratoCService contratoCSrv;

    PeriodicidadService periodicidadSrv;

    FormaPagoService formaPagoSrv;

    EstadoContratoService estadoContratoSrv;

    MunicipioService municipioSrv;

    ProductosService productosSrv;

    PlanesService planesSrv;

    SolicitudService solicitudSrv;

    PersonaCService personaCSrv;

    DireccionCService direccionCSrv;

    BeneficiarioCService beneficiarioCSrv;

    FamiliaCService familiaCSrv;

    TarifaService tarifaSrv;

    GrupoAsociadoService grupoAsociadoSrv;

    SucursalService sucursalSrv;

    UserFunctions userFunctionsSrv;

    @Inject
    public ContratoCService(
            ContratoCService contratoCSrv,
            PeriodicidadService periodicidadSrv,
            FormaPagoService formaPagoSrv,
            EstadoContratoService estadoContratoSrv,
            MunicipioService municipioSrv,
            ProductosService productosSrv,
            PlanesService planesSrv,
            SolicitudService solicitudSrv,
            PersonaCService personaCSrv,
            DireccionCService direccionCSrv,
            BeneficiarioCService beneficiarioCSrv,
            FamiliaCService familiaCSrv,
            TarifaService tarifaSrv,
            GrupoAsociadoService grupoAsociadoSrv,
            SucursalService sucursalSrv,
            UserFunctions userFunctionsSrv,
            ContratoCJpaRepository repository) {
        this.contratoCSrv = contratoCSrv;
        this.periodicidadSrv = periodicidadSrv;
        this.formaPagoSrv = formaPagoSrv;
        this.estadoContratoSrv = estadoContratoSrv;
        this.municipioSrv = municipioSrv;
        this.productosSrv = productosSrv;
        this.planesSrv = planesSrv;
        this.solicitudSrv = solicitudSrv;
        this.personaCSrv = personaCSrv;
        this.direccionCSrv = direccionCSrv;
        this.beneficiarioCSrv = beneficiarioCSrv;
        this.familiaCSrv = familiaCSrv;
        this.tarifaSrv = tarifaSrv;
        this.grupoAsociadoSrv = grupoAsociadoSrv;
        this.sucursalSrv = sucursalSrv;
        this.userFunctionsSrv = userFunctionsSrv;
        this.repository = repository;
    }

    public List<Map<String, Object>> solveQuery(QueryRequest request) {
        return solveGenericQuery(request, ContratoCConstants.CLASS_NAME);
    }

    private List<ContratoC> solveContratoCTypedQuery(QueryRequest request) {
        return repository.solveQuery(processQueryRequest(request, ContratoCConstants.CLASS_NAME), request.getPageSize(), request.getPageNumber());
    }
    
    private int solveContratoCTypedCountQuery(QueryRequest request) {
        return repository.solveCountQuery(processCountQueryRequest(request, ContratoCConstants.CLASS_NAME));
    }

    /*
     * Returns the population of class ContratoC
     *
     * @return Population of class ContratoC
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<ContratoC> get() {
        List<ContratoC> results = new java.util.ArrayList<>();
        repository.findAll().forEach(results::add);
        return results;
    }
    /*
     * Returns the population of class ContratoC that matches certain search criteria
     *
     * @param queryRequest Search criteria
     *
     * @return Population of class ContratoC matching the search criteria
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<ContratoC> get(QueryRequest queryRequest) {
        return solveContratoCTypedQuery(queryRequest);
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
            return new PopulationResponseViewModel(solveContratoCTypedQuery(queryRequest).stream()
                    .map(instance -> buildDynamicObject(instance, queryRequest.getDisplaySetItems()))
                    .collect(Collectors.toList()), queryRequest.getPageSize() != 0 ? solveContratoCTypedCountQuery(queryRequest) : 0);
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
        return new PopulationResponseViewModel(solveContratoCTypedQuery(queryRequest).stream()
                .map(instance -> buildDynamicObject(instance, displaySetItems))
                .collect(Collectors.toList()));
    }

    /*
     * Returns an instance of ContratoC by ID
     *
     * @param contratoCID Identification field contratoCID
     *
     * @return Instance of ContratoC
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ContratoC getById(Long contratoCID) {
        return getByOID(new ContratoCOid(contratoCID));
    }
    
    /*
     * Returns an instance of ContratoC by OID
     *
     * @param oid Object IDentifier
     *
     * @return Instance of ContratoC
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ContratoC getByOID(ContratoCOid oid) {
        Optional<ContratoC> instance = getInstanceByOid(oid);
        return instance.isPresent() ? instance.get() : null;
    }
    
    /*
     * Returns an instance of ContratoC by OID applying horizontal visibility constraints
     *
     * @param oid Object IDentifier
     *
     * @return Instance of ContratoC
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public ContratoC getByOIDWithHV(ContratoCOid oid) {
        Optional<ContratoC> instance = getInstanceByOid(oid);
    	return instance.isPresent() && instance.get().checkHorizontalVisibility(getAgentInstance()) 
    		 ? instance.get() 
    		 : null;
    }
    
    private Optional<ContratoC> getInstanceByOid(ContratoCOid oid) {
        return oid != null && oid.getContratoCID() != null
               ? repository.findById(oid.getContratoCID())
               : Optional.empty();
    }

    /*
     * Returns an instance of ContratoC by OID in JSON format
     *
     * @param jsonOid Object IDentifier in JSON format
     *
     * @return Instance of ContratoC
     */
    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public ContratoC getByJsonOid(String jsonOid) {
        ContratoCOid oid = new ContratoCOid();
        oid.fromJsonString(jsonOid);
        return getByOIDWithHV(oid);
    }

    /*
     * Returns the list of instances of ContratoC related to a given instance through 
     * a given path
     *
     * @param relatedInstance Related instance
     *
     * @param relatedInstancePath Related instance path
     *
     * @return List of instances of ContratoC
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<ContratoC> getByRelated(AbstractAppEntity relatedInstance, String relatedInstancePath) {
        QueryRequest queryRequest = new QueryRequest(new FilterRequest(Constants.FILTER_BY_RELATED,
                Arrays.asList(new FilterVariableRequest(Constants.FILTER_BY_RELATED_FVAR_PATH, relatedInstancePath),
                        new FilterVariableRequest(Constants.FILTER_BY_RELATED_FVAR_OID, relatedInstance.getOid().toJson()))));
        return solveContratoCTypedQuery(queryRequest);
    }

    /*
     * Returns the list of instances of ContratoC related to a given instance through
     * a given path
     *
     * @param relatedInstance Related instance
     *
     * @param relatedInstancePath Related instance path
     *
     * @return List of instances of ContratoC
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<ContratoC> getInstancesByRelated(AbstractAppEntity relatedInstance, String relatedInstancePath) {
        return getByRelated(relatedInstance, relatedInstancePath);
    }

    /*
     * Returns a set of displayset items for an instance of ContratoC
     *
     * @param oid Object IDentifier
     *
     * @param displaySetItems List of comma-separated displayset items
     *
     * @return Set of displayset items for an instance of ContratoC
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public InstanceResponseViewModel getContratoCDynamic(ContratoCOid oid, String displaySetItems) {
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
    public String getSuppInfo(ContratoCOid oid, String dsName) {
        ContratoC instance = getByOIDWithHV(oid);
        return (instance != null ? getSuppInfo(instance, dsName): "");
    }

    private String getSuppInfo(ContratoC instance, String dsName) {
        if (instance == null || instance.isNull()) return "";
        return dsName.isEmpty() ? instance.getOid().toJson() : "";
    }




    // IIU Methods



    // Service: InsPeriodicidad

    /*
     * Entry point for service InsPeriodicidad
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse insPeriodicidad(
            com.keralty.aeconfiguraciones.viewmodel.contratoc.siu.InsPeriodicidadRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = contratoCSrv.insPeriodicidadTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service InsPeriodicidad
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse insPeriodicidadTxn(
            com.keralty.aeconfiguraciones.viewmodel.contratoc.siu.InsPeriodicidadRequest request) throws ModelException {
        request.setContratoCInsPeriodicidadpthisContratoCInstance(contratoCSrv.getByOIDWithHV(request.getContratoCInsPeriodicidadpthisContratoC()));
        request.setContratoCInsPeriodicidadpevcPeriodicidadInstance(periodicidadSrv.getByOIDWithHV(request.getContratoCInsPeriodicidadpevcPeriodicidad()));
        return contratoCSrv.insPeriodicidadImplementation(request, true);
    }

    /*
     * Implements service InsPeriodicidad
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse insPeriodicidadImplementation(
            com.keralty.aeconfiguraciones.viewmodel.contratoc.siu.InsPeriodicidadRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        ContratoC contratoCInsPeriodicidadpthisContratoC = request.getContratoCInsPeriodicidadpthisContratoCInstance();
        Periodicidad contratoCInsPeriodicidadpevcPeriodicidad = request.getContratoCInsPeriodicidadpevcPeriodicidadInstance();

        request.checkArguments();
        ContratoC instance = contratoCInsPeriodicidadpthisContratoC;

        instance.add2PeriodicidadC(contratoCInsPeriodicidadpevcPeriodicidad);
        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.periodicidad.siu.InsPeriodicidadRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.periodicidad.siu.InsPeriodicidadRequest();
            relatedRequest.setPeriodicidadInsPeriodicidadpthisPeriodicidadInstance(contratoCInsPeriodicidadpevcPeriodicidad);
            relatedRequest.setPeriodicidadInsPeriodicidadpevcContratoCInstance(contratoCInsPeriodicidadpthisContratoC);
            periodicidadSrv.insPeriodicidadImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));

        return response;
    }

    // Service: DelPeriodicidad

    /*
     * Entry point for service DelPeriodicidad
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse delPeriodicidad(
            com.keralty.aeconfiguraciones.viewmodel.contratoc.siu.DelPeriodicidadRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = contratoCSrv.delPeriodicidadTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service DelPeriodicidad
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse delPeriodicidadTxn(
            com.keralty.aeconfiguraciones.viewmodel.contratoc.siu.DelPeriodicidadRequest request) throws ModelException {
        request.setContratoCDelPeriodicidadpthisContratoCInstance(contratoCSrv.getByOIDWithHV(request.getContratoCDelPeriodicidadpthisContratoC()));
        request.setContratoCDelPeriodicidadpevcPeriodicidadInstance(periodicidadSrv.getByOIDWithHV(request.getContratoCDelPeriodicidadpevcPeriodicidad()));
        return contratoCSrv.delPeriodicidadImplementation(request, true);
    }

    /*
     * Implements service DelPeriodicidad
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse delPeriodicidadImplementation(
            com.keralty.aeconfiguraciones.viewmodel.contratoc.siu.DelPeriodicidadRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        ContratoC contratoCDelPeriodicidadpthisContratoC = request.getContratoCDelPeriodicidadpthisContratoCInstance();
        Periodicidad contratoCDelPeriodicidadpevcPeriodicidad = request.getContratoCDelPeriodicidadpevcPeriodicidadInstance();

        request.checkArguments();
        ContratoC instance = contratoCDelPeriodicidadpthisContratoC;

        instance.add2PeriodicidadC(null);
        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.periodicidad.siu.DelPeriodicidadRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.periodicidad.siu.DelPeriodicidadRequest();
            relatedRequest.setPeriodicidadDelPeriodicidadpthisPeriodicidadInstance(contratoCDelPeriodicidadpevcPeriodicidad);
            relatedRequest.setPeriodicidadDelPeriodicidadpevcContratoCInstance(contratoCDelPeriodicidadpthisContratoC);
            periodicidadSrv.delPeriodicidadImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));

        return response;
    }

    // Service: InsFormaPagoC

    /*
     * Entry point for service InsFormaPagoC
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse insFormaPagoC(
            com.keralty.aeconfiguraciones.viewmodel.contratoc.siu.InsFormaPagoCRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = contratoCSrv.insFormaPagoCTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service InsFormaPagoC
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse insFormaPagoCTxn(
            com.keralty.aeconfiguraciones.viewmodel.contratoc.siu.InsFormaPagoCRequest request) throws ModelException {
        request.setContratoCInsFormaPagoCpthisContratoCInstance(contratoCSrv.getByOIDWithHV(request.getContratoCInsFormaPagoCpthisContratoC()));
        request.setContratoCInsFormaPagoCpevcFormaPagoInstance(formaPagoSrv.getByOIDWithHV(request.getContratoCInsFormaPagoCpevcFormaPago()));
        return contratoCSrv.insFormaPagoCImplementation(request, true);
    }

    /*
     * Implements service InsFormaPagoC
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse insFormaPagoCImplementation(
            com.keralty.aeconfiguraciones.viewmodel.contratoc.siu.InsFormaPagoCRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        ContratoC contratoCInsFormaPagoCpthisContratoC = request.getContratoCInsFormaPagoCpthisContratoCInstance();
        FormaPago contratoCInsFormaPagoCpevcFormaPago = request.getContratoCInsFormaPagoCpevcFormaPagoInstance();

        request.checkArguments();
        ContratoC instance = contratoCInsFormaPagoCpthisContratoC;

        instance.add2FormaPagoC(contratoCInsFormaPagoCpevcFormaPago);
        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.formapago.siu.InsFormaPagoCRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.formapago.siu.InsFormaPagoCRequest();
            relatedRequest.setFormaPagoInsFormaPagoCpthisFormaPagoInstance(contratoCInsFormaPagoCpevcFormaPago);
            relatedRequest.setFormaPagoInsFormaPagoCpevcContratoCInstance(contratoCInsFormaPagoCpthisContratoC);
            formaPagoSrv.insFormaPagoCImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));

        return response;
    }

    // Service: DelFormaPagoC

    /*
     * Entry point for service DelFormaPagoC
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse delFormaPagoC(
            com.keralty.aeconfiguraciones.viewmodel.contratoc.siu.DelFormaPagoCRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = contratoCSrv.delFormaPagoCTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service DelFormaPagoC
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse delFormaPagoCTxn(
            com.keralty.aeconfiguraciones.viewmodel.contratoc.siu.DelFormaPagoCRequest request) throws ModelException {
        request.setContratoCDelFormaPagoCpthisContratoCInstance(contratoCSrv.getByOIDWithHV(request.getContratoCDelFormaPagoCpthisContratoC()));
        request.setContratoCDelFormaPagoCpevcFormaPagoInstance(formaPagoSrv.getByOIDWithHV(request.getContratoCDelFormaPagoCpevcFormaPago()));
        return contratoCSrv.delFormaPagoCImplementation(request, true);
    }

    /*
     * Implements service DelFormaPagoC
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse delFormaPagoCImplementation(
            com.keralty.aeconfiguraciones.viewmodel.contratoc.siu.DelFormaPagoCRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        ContratoC contratoCDelFormaPagoCpthisContratoC = request.getContratoCDelFormaPagoCpthisContratoCInstance();
        FormaPago contratoCDelFormaPagoCpevcFormaPago = request.getContratoCDelFormaPagoCpevcFormaPagoInstance();

        request.checkArguments();
        ContratoC instance = contratoCDelFormaPagoCpthisContratoC;

        instance.add2FormaPagoC(null);
        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.formapago.siu.DelFormaPagoCRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.formapago.siu.DelFormaPagoCRequest();
            relatedRequest.setFormaPagoDelFormaPagoCpthisFormaPagoInstance(contratoCDelFormaPagoCpevcFormaPago);
            relatedRequest.setFormaPagoDelFormaPagoCpevcContratoCInstance(contratoCDelFormaPagoCpthisContratoC);
            formaPagoSrv.delFormaPagoCImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));

        return response;
    }

    // Service: InsEstadoContratoC

    /*
     * Entry point for service InsEstadoContratoC
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse insEstadoContratoC(
            com.keralty.aeconfiguraciones.viewmodel.contratoc.siu.InsEstadoContratoCRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = contratoCSrv.insEstadoContratoCTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service InsEstadoContratoC
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse insEstadoContratoCTxn(
            com.keralty.aeconfiguraciones.viewmodel.contratoc.siu.InsEstadoContratoCRequest request) throws ModelException {
        request.setContratoCInsEstadoContratoCpthisContratoCInstance(contratoCSrv.getByOIDWithHV(request.getContratoCInsEstadoContratoCpthisContratoC()));
        request.setContratoCInsEstadoContratoCpevcEstadoContratoInstance(estadoContratoSrv.getByOIDWithHV(request.getContratoCInsEstadoContratoCpevcEstadoContrato()));
        return contratoCSrv.insEstadoContratoCImplementation(request, true);
    }

    /*
     * Implements service InsEstadoContratoC
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse insEstadoContratoCImplementation(
            com.keralty.aeconfiguraciones.viewmodel.contratoc.siu.InsEstadoContratoCRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        ContratoC contratoCInsEstadoContratoCpthisContratoC = request.getContratoCInsEstadoContratoCpthisContratoCInstance();
        EstadoContrato contratoCInsEstadoContratoCpevcEstadoContrato = request.getContratoCInsEstadoContratoCpevcEstadoContratoInstance();

        request.checkArguments();
        ContratoC instance = contratoCInsEstadoContratoCpthisContratoC;

        instance.add2EstadoContratoC(contratoCInsEstadoContratoCpevcEstadoContrato);
        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.estadocontrato.siu.InsEstadoContratoCRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.estadocontrato.siu.InsEstadoContratoCRequest();
            relatedRequest.setEstadoContratoInsEstadoContratoCpthisEstadoContratoInstance(contratoCInsEstadoContratoCpevcEstadoContrato);
            relatedRequest.setEstadoContratoInsEstadoContratoCpevcContratoCInstance(contratoCInsEstadoContratoCpthisContratoC);
            estadoContratoSrv.insEstadoContratoCImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));

        return response;
    }

    // Service: DelEstadoContratoC

    /*
     * Entry point for service DelEstadoContratoC
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse delEstadoContratoC(
            com.keralty.aeconfiguraciones.viewmodel.contratoc.siu.DelEstadoContratoCRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = contratoCSrv.delEstadoContratoCTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service DelEstadoContratoC
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse delEstadoContratoCTxn(
            com.keralty.aeconfiguraciones.viewmodel.contratoc.siu.DelEstadoContratoCRequest request) throws ModelException {
        request.setContratoCDelEstadoContratoCpthisContratoCInstance(contratoCSrv.getByOIDWithHV(request.getContratoCDelEstadoContratoCpthisContratoC()));
        request.setContratoCDelEstadoContratoCpevcEstadoContratoInstance(estadoContratoSrv.getByOIDWithHV(request.getContratoCDelEstadoContratoCpevcEstadoContrato()));
        return contratoCSrv.delEstadoContratoCImplementation(request, true);
    }

    /*
     * Implements service DelEstadoContratoC
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse delEstadoContratoCImplementation(
            com.keralty.aeconfiguraciones.viewmodel.contratoc.siu.DelEstadoContratoCRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        ContratoC contratoCDelEstadoContratoCpthisContratoC = request.getContratoCDelEstadoContratoCpthisContratoCInstance();
        EstadoContrato contratoCDelEstadoContratoCpevcEstadoContrato = request.getContratoCDelEstadoContratoCpevcEstadoContratoInstance();

        request.checkArguments();
        ContratoC instance = contratoCDelEstadoContratoCpthisContratoC;

        instance.add2EstadoContratoC(null);
        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.estadocontrato.siu.DelEstadoContratoCRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.estadocontrato.siu.DelEstadoContratoCRequest();
            relatedRequest.setEstadoContratoDelEstadoContratoCpthisEstadoContratoInstance(contratoCDelEstadoContratoCpevcEstadoContrato);
            relatedRequest.setEstadoContratoDelEstadoContratoCpevcContratoCInstance(contratoCDelEstadoContratoCpthisContratoC);
            estadoContratoSrv.delEstadoContratoCImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));

        return response;
    }

    // Service: InsMunicipioContrato

    /*
     * Entry point for service InsMunicipioContrato
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse insMunicipioContrato(
            com.keralty.aeconfiguraciones.viewmodel.contratoc.siu.InsMunicipioContratoRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = contratoCSrv.insMunicipioContratoTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service InsMunicipioContrato
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse insMunicipioContratoTxn(
            com.keralty.aeconfiguraciones.viewmodel.contratoc.siu.InsMunicipioContratoRequest request) throws ModelException {
        request.setContratoCInsMunicipioContratopthisContratoCInstance(contratoCSrv.getByOIDWithHV(request.getContratoCInsMunicipioContratopthisContratoC()));
        request.setContratoCInsMunicipioContratopevcMunicipioInstance(municipioSrv.getByOIDWithHV(request.getContratoCInsMunicipioContratopevcMunicipio()));
        return contratoCSrv.insMunicipioContratoImplementation(request, true);
    }

    /*
     * Implements service InsMunicipioContrato
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse insMunicipioContratoImplementation(
            com.keralty.aeconfiguraciones.viewmodel.contratoc.siu.InsMunicipioContratoRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        ContratoC contratoCInsMunicipioContratopthisContratoC = request.getContratoCInsMunicipioContratopthisContratoCInstance();
        Municipio contratoCInsMunicipioContratopevcMunicipio = request.getContratoCInsMunicipioContratopevcMunicipioInstance();

        request.checkArguments();
        ContratoC instance = contratoCInsMunicipioContratopthisContratoC;

        instance.add2MunicipioC(contratoCInsMunicipioContratopevcMunicipio);
        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.municipio.siu.InsMunicipioContratoRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.municipio.siu.InsMunicipioContratoRequest();
            relatedRequest.setMunicipioInsMunicipioContratopthisMunicipioInstance(contratoCInsMunicipioContratopevcMunicipio);
            relatedRequest.setMunicipioInsMunicipioContratopevcContratoCInstance(contratoCInsMunicipioContratopthisContratoC);
            municipioSrv.insMunicipioContratoImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));

        return response;
    }

    // Service: DelMunicipioContrato

    /*
     * Entry point for service DelMunicipioContrato
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse delMunicipioContrato(
            com.keralty.aeconfiguraciones.viewmodel.contratoc.siu.DelMunicipioContratoRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = contratoCSrv.delMunicipioContratoTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service DelMunicipioContrato
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse delMunicipioContratoTxn(
            com.keralty.aeconfiguraciones.viewmodel.contratoc.siu.DelMunicipioContratoRequest request) throws ModelException {
        request.setContratoCDelMunicipioContratopthisContratoCInstance(contratoCSrv.getByOIDWithHV(request.getContratoCDelMunicipioContratopthisContratoC()));
        request.setContratoCDelMunicipioContratopevcMunicipioInstance(municipioSrv.getByOIDWithHV(request.getContratoCDelMunicipioContratopevcMunicipio()));
        return contratoCSrv.delMunicipioContratoImplementation(request, true);
    }

    /*
     * Implements service DelMunicipioContrato
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse delMunicipioContratoImplementation(
            com.keralty.aeconfiguraciones.viewmodel.contratoc.siu.DelMunicipioContratoRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        ContratoC contratoCDelMunicipioContratopthisContratoC = request.getContratoCDelMunicipioContratopthisContratoCInstance();
        Municipio contratoCDelMunicipioContratopevcMunicipio = request.getContratoCDelMunicipioContratopevcMunicipioInstance();

        request.checkArguments();
        ContratoC instance = contratoCDelMunicipioContratopthisContratoC;

        instance.add2MunicipioC(null);
        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.municipio.siu.DelMunicipioContratoRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.municipio.siu.DelMunicipioContratoRequest();
            relatedRequest.setMunicipioDelMunicipioContratopthisMunicipioInstance(contratoCDelMunicipioContratopevcMunicipio);
            relatedRequest.setMunicipioDelMunicipioContratopevcContratoCInstance(contratoCDelMunicipioContratopthisContratoC);
            municipioSrv.delMunicipioContratoImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));

        return response;
    }

    // Service: chgProductoC

    /*
     * Entry point for service chgProductoC
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse chgProductoC(
            com.keralty.aeconfiguraciones.viewmodel.contratoc.siu.ChgProductoCRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = contratoCSrv.chgProductoCTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service chgProductoC
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse chgProductoCTxn(
            com.keralty.aeconfiguraciones.viewmodel.contratoc.siu.ChgProductoCRequest request) throws ModelException {
        request.setContratoCchgProductoCpthisContratoCInstance(contratoCSrv.getByOIDWithHV(request.getContratoCchgProductoCpthisContratoC()));
        request.setContratoCchgProductoCpevcProductosInstance(productosSrv.getByOIDWithHV(request.getContratoCchgProductoCpevcProductos()));
        return contratoCSrv.chgProductoCImplementation(request, true);
    }

    /*
     * Implements service chgProductoC
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse chgProductoCImplementation(
            com.keralty.aeconfiguraciones.viewmodel.contratoc.siu.ChgProductoCRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        ContratoC contratoCchgProductoCpthisContratoC = request.getContratoCchgProductoCpthisContratoCInstance();
        Productos contratoCchgProductoCpevcProductos = request.getContratoCchgProductoCpevcProductosInstance();

        request.checkArguments();
        ContratoC instance = contratoCchgProductoCpthisContratoC;

        instance.add2ProductoC(contratoCchgProductoCpevcProductos);
        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.productos.siu.ChgProductoCRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.productos.siu.ChgProductoCRequest();
            relatedRequest.setProductoschgProductoCpthisProductosInstance(contratoCchgProductoCpevcProductos);
            relatedRequest.setProductoschgProductoCpevcContratoCInstance(contratoCchgProductoCpthisContratoC);
            productosSrv.chgProductoCImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));

        return response;
    }

    // Service: chgPlanC

    /*
     * Entry point for service chgPlanC
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse chgPlanC(
            com.keralty.aeconfiguraciones.viewmodel.contratoc.siu.ChgPlanCRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = contratoCSrv.chgPlanCTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service chgPlanC
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse chgPlanCTxn(
            com.keralty.aeconfiguraciones.viewmodel.contratoc.siu.ChgPlanCRequest request) throws ModelException {
        request.setContratoCchgPlanCpthisContratoCInstance(contratoCSrv.getByOIDWithHV(request.getContratoCchgPlanCpthisContratoC()));
        request.setContratoCchgPlanCpevcPlanesInstance(planesSrv.getByOIDWithHV(request.getContratoCchgPlanCpevcPlanes()));
        return contratoCSrv.chgPlanCImplementation(request, true);
    }

    /*
     * Implements service chgPlanC
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse chgPlanCImplementation(
            com.keralty.aeconfiguraciones.viewmodel.contratoc.siu.ChgPlanCRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        ContratoC contratoCchgPlanCpthisContratoC = request.getContratoCchgPlanCpthisContratoCInstance();
        Planes contratoCchgPlanCpevcPlanes = request.getContratoCchgPlanCpevcPlanesInstance();

        request.checkArguments();
        ContratoC instance = contratoCchgPlanCpthisContratoC;

        instance.add2PlanC(contratoCchgPlanCpevcPlanes);
        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.planes.siu.ChgPlanCRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.planes.siu.ChgPlanCRequest();
            relatedRequest.setPlaneschgPlanCpthisPlanesInstance(contratoCchgPlanCpevcPlanes);
            relatedRequest.setPlaneschgPlanCpevcContratoCInstance(contratoCchgPlanCpthisContratoC);
            planesSrv.chgPlanCImplementation(relatedRequest, false);
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
        select.getOrderByPart().addItem(Constants.normalizePath(ContratoCConstants.CLASS_NAME, ContratoCConstants.ATTR_FIELD_CONTRATOCID, false), true);
    }
    
    @Override
    protected AbstractAppEntity buildRelatedInstance(String relatedInstanceJsonOid, String relatedInstancePathFirstItem, String remainingPath) {
        AbstractAppEntity relatedInstance = null;
        if (relatedInstancePathFirstItem.isEmpty()) {
            relatedInstance = buildContratoCRelatedInstance(relatedInstanceJsonOid);
        }
        if (ContratoCConstants.ROLE_NAME_SOLICITUD.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildContratoCRelatedInstance4Solicitud(relatedInstanceJsonOid, remainingPath);
        }
        if (ContratoCConstants.ROLE_NAME_PERSONACCONTRATANTE.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildContratoCRelatedInstance4PersonaC(relatedInstanceJsonOid, remainingPath);
        }
        if (ContratoCConstants.ROLE_NAME_DIRECCIONESC.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildContratoCRelatedInstance4DireccionC(relatedInstanceJsonOid, remainingPath);
        }
        if (ContratoCConstants.ROLE_NAME_BENEFICIARIOSC.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildContratoCRelatedInstance4BeneficiarioC(relatedInstanceJsonOid, remainingPath);
        }
        if (ContratoCConstants.ROLE_NAME_FAMILIASC.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildContratoCRelatedInstance4FamiliaC(relatedInstanceJsonOid, remainingPath);
        }
        if (ContratoCConstants.ROLE_NAME_PERIODICIDADC.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildContratoCRelatedInstance4Periodicidad(relatedInstanceJsonOid, remainingPath);
        }
        if (ContratoCConstants.ROLE_NAME_FORMAPAGOC.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildContratoCRelatedInstance4FormaPago(relatedInstanceJsonOid, remainingPath);
        }
        if (ContratoCConstants.ROLE_NAME_ESTADOCONTRATOC.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildContratoCRelatedInstance4EstadoContrato(relatedInstanceJsonOid, remainingPath);
        }
        if (ContratoCConstants.ROLE_NAME_PRODUCTOC.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildContratoCRelatedInstance4Productos(relatedInstanceJsonOid, remainingPath);
        }
        if (ContratoCConstants.ROLE_NAME_PLANC.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildContratoCRelatedInstance4Planes(relatedInstanceJsonOid, remainingPath);
        }
        if (ContratoCConstants.ROLE_NAME_TARIFAC.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildContratoCRelatedInstance4Tarifa(relatedInstanceJsonOid, remainingPath);
        }
        if (ContratoCConstants.ROLE_NAME_GRUPOASOCIADOC.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildContratoCRelatedInstance4GrupoAsociado(relatedInstanceJsonOid, remainingPath);
        }
        if (ContratoCConstants.ROLE_NAME_MUNICIPIOC.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildContratoCRelatedInstance4Municipio(relatedInstanceJsonOid, remainingPath);
        }
        if (ContratoCConstants.ROLE_NAME_SUCURSALC.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildContratoCRelatedInstance4Sucursal(relatedInstanceJsonOid, remainingPath);
        }
        if (ContratoCConstants.ROLE_NAME_ASESORC.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildContratoCRelatedInstance4Asesor(relatedInstanceJsonOid, remainingPath);
        }
        return relatedInstance;
    }
    
    private AbstractAppEntity buildContratoCRelatedInstance(String relatedInstanceJsonOid) {
    	ObjectMapper mapper = new ObjectMapper();
    	try {
    		return new ContratoC(mapper.readValue(relatedInstanceJsonOid, ContratoCOid.class));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    private AbstractAppEntity buildContratoCRelatedInstance4Solicitud(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new Solicitud(mapper.readValue(relatedInstanceJsonOid, SolicitudOid.class)): Solicitud.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    private AbstractAppEntity buildContratoCRelatedInstance4PersonaC(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new PersonaC(mapper.readValue(relatedInstanceJsonOid, PersonaCOid.class)): PersonaC.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    private AbstractAppEntity buildContratoCRelatedInstance4DireccionC(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new DireccionC(mapper.readValue(relatedInstanceJsonOid, DireccionCOid.class)): DireccionC.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    private AbstractAppEntity buildContratoCRelatedInstance4BeneficiarioC(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new BeneficiarioC(mapper.readValue(relatedInstanceJsonOid, BeneficiarioCOid.class)): BeneficiarioC.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    private AbstractAppEntity buildContratoCRelatedInstance4FamiliaC(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new FamiliaC(mapper.readValue(relatedInstanceJsonOid, FamiliaCOid.class)): FamiliaC.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    private AbstractAppEntity buildContratoCRelatedInstance4Periodicidad(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new Periodicidad(mapper.readValue(relatedInstanceJsonOid, PeriodicidadOid.class)): Periodicidad.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    private AbstractAppEntity buildContratoCRelatedInstance4FormaPago(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new FormaPago(mapper.readValue(relatedInstanceJsonOid, FormaPagoOid.class)): FormaPago.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    private AbstractAppEntity buildContratoCRelatedInstance4EstadoContrato(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new EstadoContrato(mapper.readValue(relatedInstanceJsonOid, EstadoContratoOid.class)): EstadoContrato.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    private AbstractAppEntity buildContratoCRelatedInstance4Productos(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new Productos(mapper.readValue(relatedInstanceJsonOid, ProductosOid.class)): Productos.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    private AbstractAppEntity buildContratoCRelatedInstance4Planes(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new Planes(mapper.readValue(relatedInstanceJsonOid, PlanesOid.class)): Planes.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    private AbstractAppEntity buildContratoCRelatedInstance4Tarifa(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new Tarifa(mapper.readValue(relatedInstanceJsonOid, TarifaOid.class)): Tarifa.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    private AbstractAppEntity buildContratoCRelatedInstance4GrupoAsociado(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new GrupoAsociado(mapper.readValue(relatedInstanceJsonOid, GrupoAsociadoOid.class)): GrupoAsociado.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    private AbstractAppEntity buildContratoCRelatedInstance4Municipio(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new Municipio(mapper.readValue(relatedInstanceJsonOid, MunicipioOid.class)): Municipio.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    private AbstractAppEntity buildContratoCRelatedInstance4Sucursal(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new Sucursal(mapper.readValue(relatedInstanceJsonOid, SucursalOid.class)): Sucursal.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    private AbstractAppEntity buildContratoCRelatedInstance4Asesor(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new Asesor(mapper.readValue(relatedInstanceJsonOid, AsesorOid.class)): Asesor.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    @Override
    protected void applyFilterByOid(String jsonOid, SQLSelect sql) {
        try {
            ContratoCOid oid = new ContratoCOid().fromJsonString(jsonOid);
            SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
            comparison1.setOperator("=");
            comparison1.setFirstOperand(new SQLWhereOperandPathAttribute(ContratoCConstants.ATTR_FIELD_CONTRATOCID, Constants.Type.AUTONUMERIC.getTypeName()));
            comparison1.setSecondOperand(new SQLWhereOperandLiteral(oid.getContratoCID(), Constants.Type.AUTONUMERIC.getTypeName()));
            sql.addComparison(comparison1);
        } catch (Exception e) {
            LoggerFactory.getLogger(ContratoCService.class).error(e.getMessage());
        }
    }

}
