package com.keralty.aeconfiguraciones.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.keralty.aeconfiguraciones.exception.InstanceExistsException;
import com.keralty.aeconfiguraciones.exception.MinCardinalityException;
import com.keralty.aeconfiguraciones.exception.ModelException;
import com.keralty.aeconfiguraciones.exception.PreconditionException;
import com.keralty.aeconfiguraciones.exception.SystemException;
import com.keralty.aeconfiguraciones.global.Constants;
import com.keralty.aeconfiguraciones.global.DominioConstants;
import com.keralty.aeconfiguraciones.global.PeriodicidadConstants;
import com.keralty.aeconfiguraciones.global.SolicitudConstants;
import com.keralty.aeconfiguraciones.global.SolicitudNovedadConstants;
import com.keralty.aeconfiguraciones.persistence.AbstractAppEntity;
import com.keralty.aeconfiguraciones.persistence.AnexoC;
import com.keralty.aeconfiguraciones.persistence.AnexoMat;
import com.keralty.aeconfiguraciones.persistence.AnexoNeo;
import com.keralty.aeconfiguraciones.persistence.ContratoC;
import com.keralty.aeconfiguraciones.persistence.Dominio;
import com.keralty.aeconfiguraciones.persistence.oid.AnexoCOid;
import com.keralty.aeconfiguraciones.persistence.oid.AnexoMatOid;
import com.keralty.aeconfiguraciones.persistence.oid.AnexoNeoOid;
import com.keralty.aeconfiguraciones.persistence.oid.ContratoCOid;
import com.keralty.aeconfiguraciones.persistence.oid.DominioOid;
import com.keralty.aeconfiguraciones.persistence.oid.PeriodicidadOid;
import com.keralty.aeconfiguraciones.persistence.oid.SolicitudNovedadOid;
import com.keralty.aeconfiguraciones.persistence.oid.SolicitudOid;
import com.keralty.aeconfiguraciones.persistence.Periodicidad;
import com.keralty.aeconfiguraciones.persistence.Solicitud;
import com.keralty.aeconfiguraciones.persistence.SolicitudNovedad;
import com.keralty.aeconfiguraciones.repository.PeriodicidadJpaRepository;
import com.keralty.aeconfiguraciones.sqlutils.SQLSelect;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereComparison;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereConjunction;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereDisjunction;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereNegation;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereOperandLiteral;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereOperandPathAttribute;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereOperandSubQuery;
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
public class PeriodicidadService extends AbstractService {

    PeriodicidadJpaRepository repository;

    PeriodicidadService periodicidadSrv;

    DominioService dominioSrv;

    SolicitudService solicitudSrv;

    AnexoMatService anexoMatSrv;

    AnexoNeoService anexoNeoSrv;

    ContratoCService contratoCSrv;

    SolicitudNovedadService solicitudNovedadSrv;

    AnexoCService anexoCSrv;

    @Inject
    public PeriodicidadService(
            PeriodicidadService periodicidadSrv,
            DominioService dominioSrv,
            SolicitudService solicitudSrv,
            AnexoMatService anexoMatSrv,
            AnexoNeoService anexoNeoSrv,
            ContratoCService contratoCSrv,
            SolicitudNovedadService solicitudNovedadSrv,
            AnexoCService anexoCSrv,
            PeriodicidadJpaRepository repository) {
        this.periodicidadSrv = periodicidadSrv;
        this.dominioSrv = dominioSrv;
        this.solicitudSrv = solicitudSrv;
        this.anexoMatSrv = anexoMatSrv;
        this.anexoNeoSrv = anexoNeoSrv;
        this.contratoCSrv = contratoCSrv;
        this.solicitudNovedadSrv = solicitudNovedadSrv;
        this.anexoCSrv = anexoCSrv;
        this.repository = repository;
    }

    public List<Map<String, Object>> solveQuery(QueryRequest request) {
        return solveGenericQuery(request, PeriodicidadConstants.CLASS_NAME);
    }

    private List<Periodicidad> solvePeriodicidadTypedQuery(QueryRequest request) {
        return repository.solveQuery(processQueryRequest(request, PeriodicidadConstants.CLASS_NAME), request.getPageSize(), request.getPageNumber());
    }
    
    private int solvePeriodicidadTypedCountQuery(QueryRequest request) {
        return repository.solveCountQuery(processCountQueryRequest(request, PeriodicidadConstants.CLASS_NAME));
    }

    /*
     * Returns the population of class Periodicidad
     *
     * @return Population of class Periodicidad
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<Periodicidad> get() {
        List<Periodicidad> results = new java.util.ArrayList<>();
        repository.findAll().forEach(results::add);
        return results;
    }
    
    /*
     * Gets the preloaded list of objects of class Periodicidad
     *
     * @param queryRequest Request object that dictates the search conditions to 
     * retrieve objects
     *
     * @return Preloaded list of objects of class Periodicidad
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<PreloadViewModel> getPreload(QueryRequest queryRequest) {
        List<PreloadViewModel> results = new ArrayList<>();
        List<Periodicidad> instances = get(queryRequest);
        if(PeriodicidadConstants.DSICPERIODICIDAD.equals(queryRequest.getDisplaySetName())) {
            results = instances.stream().map(x -> new PreloadViewModel(x.getOid().toJson(), x.buildICPeriodicidad())).collect(Collectors.toList());
        }
        return results;
    }
    /*
     * Returns the population of class Periodicidad that matches certain search criteria
     *
     * @param queryRequest Search criteria
     *
     * @return Population of class Periodicidad matching the search criteria
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<Periodicidad> get(QueryRequest queryRequest) {
        return solvePeriodicidadTypedQuery(queryRequest);
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
            return new PopulationResponseViewModel(solvePeriodicidadTypedQuery(queryRequest).stream()
                    .map(instance -> buildDynamicObject(instance, queryRequest.getDisplaySetItems()))
                    .collect(Collectors.toList()), queryRequest.getPageSize() != 0 ? solvePeriodicidadTypedCountQuery(queryRequest) : 0);
        } else {
            return new PreloadResponseViewModel(solvePeriodicidadTypedQuery(queryRequest).stream()
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
        return new PopulationResponseViewModel(solvePeriodicidadTypedQuery(queryRequest).stream()
                .map(instance -> buildDynamicObject(instance, displaySetItems))
                .collect(Collectors.toList()));
    }

    /*
     * Returns an instance of Periodicidad by ID
     *
     * @param periodicidadCodigo Identification field periodicidadCodigo
     *
     * @return Instance of Periodicidad
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public Periodicidad getById(Long periodicidadCodigo) {
        return getByOID(new PeriodicidadOid(periodicidadCodigo));
    }
    
    /*
     * Returns an instance of Periodicidad by OID
     *
     * @param oid Object IDentifier
     *
     * @return Instance of Periodicidad
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public Periodicidad getByOID(PeriodicidadOid oid) {
        Optional<Periodicidad> instance = getInstanceByOid(oid);
        return instance.isPresent() ? instance.get() : null;
    }
    
    /*
     * Returns an instance of Periodicidad by OID applying horizontal visibility constraints
     *
     * @param oid Object IDentifier
     *
     * @return Instance of Periodicidad
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public Periodicidad getByOIDWithHV(PeriodicidadOid oid) {
        Optional<Periodicidad> instance = getInstanceByOid(oid);
    	return instance.isPresent() && instance.get().checkHorizontalVisibility(getAgentInstance()) 
    		 ? instance.get() 
    		 : null;
    }
    
    private Optional<Periodicidad> getInstanceByOid(PeriodicidadOid oid) {
        return oid != null && oid.getPeriodicidadCodigo() != null
               ? repository.findById(oid.getPeriodicidadCodigo())
               : Optional.empty();
    }

    /*
     * Returns an instance of Periodicidad by OID in JSON format
     *
     * @param jsonOid Object IDentifier in JSON format
     *
     * @return Instance of Periodicidad
     */
    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public Periodicidad getByJsonOid(String jsonOid) {
        PeriodicidadOid oid = new PeriodicidadOid();
        oid.fromJsonString(jsonOid);
        return getByOIDWithHV(oid);
    }

    /*
     * Returns the list of instances of Periodicidad related to a given instance through 
     * a given path
     *
     * @param relatedInstance Related instance
     *
     * @param relatedInstancePath Related instance path
     *
     * @return List of instances of Periodicidad
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<Periodicidad> getByRelated(AbstractAppEntity relatedInstance, String relatedInstancePath) {
        QueryRequest queryRequest = new QueryRequest(new FilterRequest(Constants.FILTER_BY_RELATED,
                Arrays.asList(new FilterVariableRequest(Constants.FILTER_BY_RELATED_FVAR_PATH, relatedInstancePath),
                        new FilterVariableRequest(Constants.FILTER_BY_RELATED_FVAR_OID, relatedInstance.getOid().toJson()))));
        return solvePeriodicidadTypedQuery(queryRequest);
    }

    /*
     * Returns the list of instances of Periodicidad related to a given instance through
     * a given path
     *
     * @param relatedInstance Related instance
     *
     * @param relatedInstancePath Related instance path
     *
     * @return List of instances of Periodicidad
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<Periodicidad> getInstancesByRelated(AbstractAppEntity relatedInstance, String relatedInstancePath) {
        return getByRelated(relatedInstance, relatedInstancePath);
    }

    /*
     * Returns a set of displayset items for an instance of Periodicidad
     *
     * @param oid Object IDentifier
     *
     * @param displaySetItems List of comma-separated displayset items
     *
     * @return Set of displayset items for an instance of Periodicidad
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public InstanceResponseViewModel getPeriodicidadDynamic(PeriodicidadOid oid, String displaySetItems) {
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
    public String getSuppInfo(PeriodicidadOid oid, String dsName) {
        Periodicidad instance = getByOIDWithHV(oid);
        return (instance != null ? getSuppInfo(instance, dsName): "");
    }

    private String getSuppInfo(Periodicidad instance, String dsName) {
        if (instance == null || instance.isNull()) return "";
        if (PeriodicidadConstants.DSICPERIODICIDAD.equals(dsName))
            return instance.buildICPeriodicidad();
        return dsName.isEmpty() ? instance.getOid().toJson() : "";
    }

    public static PreloadViewModel buildPreloadViewModel(Periodicidad instance, String dsName) {
        if (PeriodicidadConstants.DSICPERIODICIDAD.equals(dsName))
            return new PreloadViewModel(instance.getOid().toJson(), instance.buildICPeriodicidad());
        return new PreloadViewModel(instance.getOid().toJson(), "");
    }


    @Override
    protected void applyQueryFilters(FilterRequest filterRequest, SQLSelect sql) throws SystemException {
        if (PeriodicidadConstants.FILTER_NAME_IFPERIODICIDADMENSUAL.equalsIgnoreCase(filterRequest.getName()))
            iFPeriodicidadMensual(sql);
    }

    @Override
    protected void applyNavigationalFilters(FilterRequest filterRequest, SQLSelect sql) throws SystemException {
        if ("Dominio_InsPeriodicidadDominio_p_evcPeriodicidad_NavFilter0".equalsIgnoreCase(filterRequest.getName()))
            clas1431701422080423FiltNav4(filterRequest, sql);
        if ("Dominio_DelPeriodicidadDominio_p_evcPeriodicidad_NavFilter0".equalsIgnoreCase(filterRequest.getName()))
            clas1431701422080423FiltNav7(filterRequest, sql);
        if ("Solicitud_TMODIFICARINFOPAGO_pt_p_evcPeriodicidad_NavFilter0".equalsIgnoreCase(filterRequest.getName()))
            clas1431957012480960FiltNav5(filterRequest, sql);
        if ("SolicitudNovedad_TCHGNUEVAPERIODICIDAD_ptPeriodicidad_NavFilter0".equalsIgnoreCase(filterRequest.getName()))
            clas1581421428736234FiltNav22(filterRequest, sql);
    }

    @Override
    protected void applyServiceSIFilters(FilterRequest filterRequest, SQLSelect sql) {
        if ("SuppInfo_IC_Periodicidad".equals(filterRequest.getName()))
            suppInfo4ICPeriodicidad(filterRequest.getFilterVariable("searchText").toString(), sql);
    }


    private void iFPeriodicidadMensual(SQLSelect sql) {
        SQLWhereConjunction conjunction = new SQLWhereConjunction();

        // EsMensual = true
        // '=' Comparison
        SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
        comparison1.setOperator("=");
        
        // Operand 1
        SQLWhereOperandPathAttribute operand112 = new SQLWhereOperandPathAttribute(PeriodicidadConstants.ATTR_NAME_PERIODICIDADESMENSUAL, Constants.Type.BOOL.getTypeName());
        comparison1.setFirstOperand(operand112);
        
        // Operand 2
        comparison1.setSecondOperand(new SQLWhereOperandLiteral(Boolean.TRUE, Constants.Type.BOOL.getTypeName()));
        conjunction.addOperand(comparison1);


        sql.addConjunction(conjunction);
    }

    private void clas1431701422080423FiltNav4(FilterRequest filterRequest, SQLSelect sql) throws SystemException{
        SQLWhereConjunction conjunction = new SQLWhereConjunction();
        Dominio dominioInsPeriodicidadDominiopthisDominio = filterRequest.hasFilterVariable(DominioConstants.ARG_NAME_INSPERIODICIDADDOMINIO_DOMINIOINSPERIODICIDADDOMINIOPTHISDOMINIO) ? dominioSrv.getByOID(new DominioOid().fromJsonString(toJson(filterRequest.getFilterVariable(DominioConstants.ARG_NAME_INSPERIODICIDADDOMINIO_DOMINIOINSPERIODICIDADDOMINIOPTHISDOMINIO)))) : null;

        // EXIST (Dominios) WHERE (Dominios = pthisDominio) = false
        if (dominioInsPeriodicidadDominiopthisDominio != null) {
        {
            // 'not' Logical operator
            SQLWhereNegation comparison1 = new SQLWhereNegation(sql);
        
            // Operand 1
            // 'exist' Association operator
            SQLWhereOperandSubQuery operandSubQueryAssocOperator001 = new SQLWhereOperandSubQuery(repository.assocOperator001Range1Query(dominioInsPeriodicidadDominiopthisDominio, sql));
            comparison1.addOperand(operandSubQueryAssocOperator001);
            
            conjunction.addOperand(comparison1);
        }

        }        
        sql.addConjunction(conjunction);
    }

    private void clas1431701422080423FiltNav7(FilterRequest filterRequest, SQLSelect sql) throws SystemException{
        SQLWhereConjunction conjunction = new SQLWhereConjunction();
        Dominio dominioDelPeriodicidadDominiopthisDominio = filterRequest.hasFilterVariable(DominioConstants.ARG_NAME_DELPERIODICIDADDOMINIO_DOMINIODELPERIODICIDADDOMINIOPTHISDOMINIO) ? dominioSrv.getByOID(new DominioOid().fromJsonString(toJson(filterRequest.getFilterVariable(DominioConstants.ARG_NAME_DELPERIODICIDADDOMINIO_DOMINIODELPERIODICIDADDOMINIOPTHISDOMINIO)))) : null;

        // EXIST (Dominios) WHERE (Dominios = pthisDominio) = true
        if (dominioDelPeriodicidadDominiopthisDominio != null) {
    // 'exist' Association operator
    SQLWhereOperandSubQuery operandSubQueryAssocOperator002 = new SQLWhereOperandSubQuery(repository.assocOperator002Range1Query(dominioDelPeriodicidadDominiopthisDominio, sql));
    conjunction.addOperand(operandSubQueryAssocOperator002);

        }        
        sql.addConjunction(conjunction);
    }

    private void clas1431957012480960FiltNav5(FilterRequest filterRequest, SQLSelect sql) throws SystemException{
        SQLWhereConjunction conjunction = new SQLWhereConjunction();
        Solicitud solicitudTMODIFICARINFOPAGOpthisSolicitud = filterRequest.hasFilterVariable(SolicitudConstants.ARG_NAME_TMODIFICARINFOPAGO_SOLICITUDTMODIFICARINFOPAGOPTHISSOLICITUD) ? solicitudSrv.getByOID(new SolicitudOid().fromJsonString(toJson(filterRequest.getFilterVariable(SolicitudConstants.ARG_NAME_TMODIFICARINFOPAGO_SOLICITUDTMODIFICARINFOPAGOPTHISSOLICITUD)))) : null;

        // EXIST (Dominios) WHERE (Dominios.ProductoT = pthisSolicitud.Aesolicitudes.ProductoT AND Dominios.PlanT = pthisSolicitud.Aesolicitudes.PlanT) = true
        if (solicitudTMODIFICARINFOPAGOpthisSolicitud != null) {
    // 'exist' Association operator
    SQLWhereOperandSubQuery operandSubQueryAssocOperator003 = new SQLWhereOperandSubQuery(repository.assocOperator003Range1Query(solicitudTMODIFICARINFOPAGOpthisSolicitud, sql));
    conjunction.addOperand(operandSubQueryAssocOperator003);

        }        
        sql.addConjunction(conjunction);
    }

    private void clas1581421428736234FiltNav22(FilterRequest filterRequest, SQLSelect sql) throws SystemException{
        SQLWhereConjunction conjunction = new SQLWhereConjunction();
        SolicitudNovedad solicitudNovedadTCHGNUEVAPERIODICIDADpthisSolicitudNovedad = filterRequest.hasFilterVariable(SolicitudNovedadConstants.ARG_NAME_TCHGNUEVAPERIODICIDAD_SOLICITUDNOVEDADTCHGNUEVAPERIODICIDADPTHISSOLICITUDNOVEDAD) ? solicitudNovedadSrv.getByOID(new SolicitudNovedadOid().fromJsonString(toJson(filterRequest.getFilterVariable(SolicitudNovedadConstants.ARG_NAME_TCHGNUEVAPERIODICIDAD_SOLICITUDNOVEDADTCHGNUEVAPERIODICIDADPTHISSOLICITUDNOVEDAD)))) : null;

        // EXIST (Dominios) WHERE (Dominios.ProductoT = pthisSolicitudNovedad.PlanT.ProductoT AND Dominios.PlanT = pthisSolicitudNovedad.PlanT) = true
        if (solicitudNovedadTCHGNUEVAPERIODICIDADpthisSolicitudNovedad != null) {
    // 'exist' Association operator
    SQLWhereOperandSubQuery operandSubQueryAssocOperator004 = new SQLWhereOperandSubQuery(repository.assocOperator004Range1Query(solicitudNovedadTCHGNUEVAPERIODICIDADpthisSolicitudNovedad, sql));
    conjunction.addOperand(operandSubQueryAssocOperator004);

        }        
        sql.addConjunction(conjunction);
    }

    // IIU Methods


    /*
     * Solves the query needed for PIUPeriodicidad
     *
     * @param queryRequest Query request
     * @return List of viewmodels for Periodicidad using displayset AutoDS that 
     * match the search criteria
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<com.keralty.aeconfiguraciones.viewmodel.periodicidad.ds.AutoDS> piuPIUPeriodicidad(QueryRequest queryRequest) {
        return solvePeriodicidadTypedQuery(queryRequest).stream()
                .map(com.keralty.aeconfiguraciones.viewmodel.periodicidad.ds.AutoDS::new)
                .collect(Collectors.toList());
    }

    /*
     * Solves the COUNT query needed for PIUPeriodicidad
     *
     * @param queryRequest Query request
     * @returns Count of instances for PIUPeriodicidad
     */
    public int getCount4PIUPeriodicidad(QueryRequest queryRequest) {
        return solvePeriodicidadTypedQuery(queryRequest).size();
    }

    /*
     * Solves the query needed for PIUPeriodicidadDetDominio
     *
     * @param queryRequest Query request
     * @return List of viewmodels for Periodicidad using displayset AutoDS that 
     * match the search criteria
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<com.keralty.aeconfiguraciones.viewmodel.periodicidad.ds.AutoDS> piuPIUPeriodicidadDetDominio(QueryRequest queryRequest) {
        return solvePeriodicidadTypedQuery(queryRequest).stream()
                .map(com.keralty.aeconfiguraciones.viewmodel.periodicidad.ds.AutoDS::new)
                .collect(Collectors.toList());
    }

    /*
     * Solves the COUNT query needed for PIUPeriodicidadDetDominio
     *
     * @param queryRequest Query request
     * @returns Count of instances for PIUPeriodicidadDetDominio
     */
    public int getCount4PIUPeriodicidadDetDominio(QueryRequest queryRequest) {
        return solvePeriodicidadTypedQuery(queryRequest).size();
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
            com.keralty.aeconfiguraciones.viewmodel.periodicidad.siu.CrearRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = periodicidadSrv.crearTxn(request);
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
            com.keralty.aeconfiguraciones.viewmodel.periodicidad.siu.CrearRequest request) throws ModelException {
        return periodicidadSrv.crearImplementation(request);
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
            com.keralty.aeconfiguraciones.viewmodel.periodicidad.siu.CrearRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Long periodicidadcrearpatrCodigo = request.getPeriodicidadcrearpatrCodigo();
        String periodicidadcrearpatrDescripcion = request.getPeriodicidadcrearpatrDescripcion();

        request.checkArguments();
        Periodicidad instance = new Periodicidad();
        instance.setPeriodicidadEsMensual(Boolean.FALSE);
        instance.setPeriodicidadCodigo(periodicidadcrearpatrCodigo);
        instance.setPeriodicidadDescripcion(periodicidadcrearpatrDescripcion);
        if (periodicidadSrv.getByOID(instance.getOid()) != null) {
            throw new InstanceExistsException(PeriodicidadConstants.CLASS_ALIAS);
        }

        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICPeriodicidad());

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
            com.keralty.aeconfiguraciones.viewmodel.periodicidad.siu.EliminarRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = periodicidadSrv.eliminarTxn(request);
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
            com.keralty.aeconfiguraciones.viewmodel.periodicidad.siu.EliminarRequest request) throws ModelException {
        request.setPeriodicidadeliminarpthisPeriodicidadInstance(periodicidadSrv.getByOIDWithHV(request.getPeriodicidadeliminarpthisPeriodicidad()));
        return periodicidadSrv.eliminarImplementation(request);
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
            com.keralty.aeconfiguraciones.viewmodel.periodicidad.siu.EliminarRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Periodicidad periodicidadeliminarpthisPeriodicidad = request.getPeriodicidadeliminarpthisPeriodicidadInstance();

        request.checkArguments();

        eliminarPreconditions(request);
        Periodicidad instance = periodicidadeliminarpthisPeriodicidad;
        if(periodicidadeliminarpthisPeriodicidad.getSolicitudes().size() > 0) {
            throw new MinCardinalityException(PeriodicidadConstants.CLASS_ALIAS, PeriodicidadConstants.ROLE_ALIAS_SOLICITUDES);
        }
        if(periodicidadeliminarpthisPeriodicidad.getAnexosMat().size() > 0) {
            throw new MinCardinalityException(PeriodicidadConstants.CLASS_ALIAS, PeriodicidadConstants.ROLE_ALIAS_ANEXOSMAT);
        }
        if(periodicidadeliminarpthisPeriodicidad.getAnexosNeo().size() > 0) {
            throw new MinCardinalityException(PeriodicidadConstants.CLASS_ALIAS, PeriodicidadConstants.ROLE_ALIAS_ANEXOSNEO);
        }
        if(periodicidadeliminarpthisPeriodicidad.getContratosC().size() > 0) {
            throw new MinCardinalityException(PeriodicidadConstants.CLASS_ALIAS, PeriodicidadConstants.ROLE_ALIAS_CONTRATOSC);
        }
        if(periodicidadeliminarpthisPeriodicidad.getAnexosC().size() > 0) {
            throw new MinCardinalityException(PeriodicidadConstants.CLASS_ALIAS, PeriodicidadConstants.ROLE_ALIAS_ANEXOSC);
        }
        if(periodicidadeliminarpthisPeriodicidad.getSolicitudesNovedad().size() > 0) {
            throw new MinCardinalityException(PeriodicidadConstants.CLASS_ALIAS, PeriodicidadConstants.ROLE_ALIAS_SOLICITUDESNOVEDAD);
        }
        if(periodicidadeliminarpthisPeriodicidad.getSolicitudesNovedadNueva().size() > 0) {
            throw new MinCardinalityException(PeriodicidadConstants.CLASS_ALIAS, PeriodicidadConstants.ROLE_ALIAS_SOLICITUDESNOVEDADNUEVA);
        }


        repository.delete(instance);
        
        response.setInstance(instance);
        response.setInstanceSuppInfo(instance.buildICPeriodicidad());

        return response;
    }

    /*
     * Checks service eliminar preconditions individually
     *
     * @param request Service request viewmodel
     *
     * @return Service preconditions evaluation result
     */
    public ServiceResponse eliminarCheckPreconditions(com.keralty.aeconfiguraciones.viewmodel.periodicidad.siu.EliminarRequest request) {
        ServiceResponse response = new ServiceResponse();
        request.setPeriodicidadeliminarpthisPeriodicidadInstance(periodicidadSrv.getByOIDWithHV(request.getPeriodicidadeliminarpthisPeriodicidad()));
        try {
            if("Clas_1431699849216490Pre_1".equals(request.getPreconditionId()))
                checkClas1431699849216490Pre1(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    private void eliminarPreconditions(
            com.keralty.aeconfiguraciones.viewmodel.periodicidad.siu.EliminarRequest request) 
            throws ModelException {
        checkClas1431699849216490Pre1(request);
    }

    private void checkClas1431699849216490Pre1 (
            com.keralty.aeconfiguraciones.viewmodel.periodicidad.siu.EliminarRequest request) 
            throws ModelException {
        // EXIST( Dominios ) = false

        Periodicidad periodicidadeliminarpthisPeriodicidad = request.getPeriodicidadeliminarpthisPeriodicidadInstance();
        try {
            boolean preconditionHolds = repository.assocOperator000(periodicidadeliminarpthisPeriodicidad).equals(false);
            if (!preconditionHolds) throw new PreconditionException("No se puede eliminar la periodicidad porque existen dominios que la tienen especificada como periodicidad válida");
        } catch (SystemException e) {
            LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            throw new SystemException(e.getMessage());
        }
    }

    // Service: sincronizar

    /*
     * Entry point for service sincronizar
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse sincronizar(
            com.keralty.aeconfiguraciones.viewmodel.periodicidad.siu.SincronizarRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = periodicidadSrv.sincronizarTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service sincronizar
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse sincronizarTxn(
            com.keralty.aeconfiguraciones.viewmodel.periodicidad.siu.SincronizarRequest request) throws ModelException {
        request.setPeriodicidadsincronizarpthisPeriodicidadInstance(periodicidadSrv.getByOIDWithHV(request.getPeriodicidadsincronizarpthisPeriodicidad()));
        return periodicidadSrv.sincronizarImplementation(request);
    }

    /*
     * Implements service sincronizar
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse sincronizarImplementation(
            com.keralty.aeconfiguraciones.viewmodel.periodicidad.siu.SincronizarRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Periodicidad periodicidadsincronizarpthisPeriodicidad = request.getPeriodicidadsincronizarpthisPeriodicidadInstance();
        String periodicidadsincronizarpDescripcion = request.getPeriodicidadsincronizarpDescripcion();

        request.checkArguments();
        Periodicidad instance = periodicidadsincronizarpthisPeriodicidad;
        instance.setPeriodicidadDescripcion(periodicidadsincronizarpDescripcion);


        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICPeriodicidad());

        return response;
    }

    // Service: InsPeriodicidadDominio

    /*
     * Entry point for service InsPeriodicidadDominio
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse insPeriodicidadDominio(
            com.keralty.aeconfiguraciones.viewmodel.periodicidad.siu.InsPeriodicidadDominioRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = periodicidadSrv.insPeriodicidadDominioTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service InsPeriodicidadDominio
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse insPeriodicidadDominioTxn(
            com.keralty.aeconfiguraciones.viewmodel.periodicidad.siu.InsPeriodicidadDominioRequest request) throws ModelException {
        request.setPeriodicidadInsPeriodicidadDominiopthisPeriodicidadInstance(periodicidadSrv.getByOIDWithHV(request.getPeriodicidadInsPeriodicidadDominiopthisPeriodicidad()));
        request.setPeriodicidadInsPeriodicidadDominiopevcDominioInstance(dominioSrv.getByOIDWithHV(request.getPeriodicidadInsPeriodicidadDominiopevcDominio()));
        return periodicidadSrv.insPeriodicidadDominioImplementation(request, true);
    }

    /*
     * Implements service InsPeriodicidadDominio
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse insPeriodicidadDominioImplementation(
            com.keralty.aeconfiguraciones.viewmodel.periodicidad.siu.InsPeriodicidadDominioRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Periodicidad periodicidadInsPeriodicidadDominiopthisPeriodicidad = request.getPeriodicidadInsPeriodicidadDominiopthisPeriodicidadInstance();
        Dominio periodicidadInsPeriodicidadDominiopevcDominio = request.getPeriodicidadInsPeriodicidadDominiopevcDominioInstance();

        request.checkArguments();
        Periodicidad instance = periodicidadInsPeriodicidadDominiopthisPeriodicidad;

        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.dominio.siu.InsPeriodicidadDominioRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.dominio.siu.InsPeriodicidadDominioRequest();
            relatedRequest.setDominioInsPeriodicidadDominiopthisDominioInstance(periodicidadInsPeriodicidadDominiopevcDominio);
            relatedRequest.setDominioInsPeriodicidadDominiopevcPeriodicidadInstance(periodicidadInsPeriodicidadDominiopthisPeriodicidad);
            dominioSrv.insPeriodicidadDominioImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICPeriodicidad());

        return response;
    }

    // Service: DelPeriodicidadDominio

    /*
     * Entry point for service DelPeriodicidadDominio
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse delPeriodicidadDominio(
            com.keralty.aeconfiguraciones.viewmodel.periodicidad.siu.DelPeriodicidadDominioRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = periodicidadSrv.delPeriodicidadDominioTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service DelPeriodicidadDominio
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse delPeriodicidadDominioTxn(
            com.keralty.aeconfiguraciones.viewmodel.periodicidad.siu.DelPeriodicidadDominioRequest request) throws ModelException {
        request.setPeriodicidadDelPeriodicidadDominiopthisPeriodicidadInstance(periodicidadSrv.getByOIDWithHV(request.getPeriodicidadDelPeriodicidadDominiopthisPeriodicidad()));
        request.setPeriodicidadDelPeriodicidadDominiopevcDominioInstance(dominioSrv.getByOIDWithHV(request.getPeriodicidadDelPeriodicidadDominiopevcDominio()));
        return periodicidadSrv.delPeriodicidadDominioImplementation(request, true);
    }

    /*
     * Implements service DelPeriodicidadDominio
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse delPeriodicidadDominioImplementation(
            com.keralty.aeconfiguraciones.viewmodel.periodicidad.siu.DelPeriodicidadDominioRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Periodicidad periodicidadDelPeriodicidadDominiopthisPeriodicidad = request.getPeriodicidadDelPeriodicidadDominiopthisPeriodicidadInstance();
        Dominio periodicidadDelPeriodicidadDominiopevcDominio = request.getPeriodicidadDelPeriodicidadDominiopevcDominioInstance();

        request.checkArguments();
        Periodicidad instance = periodicidadDelPeriodicidadDominiopthisPeriodicidad;

        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.dominio.siu.DelPeriodicidadDominioRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.dominio.siu.DelPeriodicidadDominioRequest();
            relatedRequest.setDominioDelPeriodicidadDominiopthisDominioInstance(periodicidadDelPeriodicidadDominiopevcDominio);
            relatedRequest.setDominioDelPeriodicidadDominiopevcPeriodicidadInstance(periodicidadDelPeriodicidadDominiopthisPeriodicidad);
            dominioSrv.delPeriodicidadDominioImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICPeriodicidad());

        return response;
    }

    // Service: InsPeriodicidadSolicitud

    /*
     * Entry point for service InsPeriodicidadSolicitud
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse insPeriodicidadSolicitud(
            com.keralty.aeconfiguraciones.viewmodel.periodicidad.siu.InsPeriodicidadSolicitudRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = periodicidadSrv.insPeriodicidadSolicitudTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service InsPeriodicidadSolicitud
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse insPeriodicidadSolicitudTxn(
            com.keralty.aeconfiguraciones.viewmodel.periodicidad.siu.InsPeriodicidadSolicitudRequest request) throws ModelException {
        request.setPeriodicidadInsPeriodicidadSolicitudpthisPeriodicidadInstance(periodicidadSrv.getByOIDWithHV(request.getPeriodicidadInsPeriodicidadSolicitudpthisPeriodicidad()));
        request.setPeriodicidadInsPeriodicidadSolicitudpevcSolicitudInstance(solicitudSrv.getByOIDWithHV(request.getPeriodicidadInsPeriodicidadSolicitudpevcSolicitud()));
        return periodicidadSrv.insPeriodicidadSolicitudImplementation(request, true);
    }

    /*
     * Implements service InsPeriodicidadSolicitud
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse insPeriodicidadSolicitudImplementation(
            com.keralty.aeconfiguraciones.viewmodel.periodicidad.siu.InsPeriodicidadSolicitudRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Periodicidad periodicidadInsPeriodicidadSolicitudpthisPeriodicidad = request.getPeriodicidadInsPeriodicidadSolicitudpthisPeriodicidadInstance();
        Solicitud periodicidadInsPeriodicidadSolicitudpevcSolicitud = request.getPeriodicidadInsPeriodicidadSolicitudpevcSolicitudInstance();

        request.checkArguments();
        Periodicidad instance = periodicidadInsPeriodicidadSolicitudpthisPeriodicidad;

        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.solicitud.siu.InsPeriodicidadSolicitudRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.solicitud.siu.InsPeriodicidadSolicitudRequest();
            relatedRequest.setSolicitudInsPeriodicidadSolicitudpthisSolicitudInstance(periodicidadInsPeriodicidadSolicitudpevcSolicitud);
            relatedRequest.setSolicitudInsPeriodicidadSolicitudpevcPeriodicidadInstance(periodicidadInsPeriodicidadSolicitudpthisPeriodicidad);
            solicitudSrv.insPeriodicidadSolicitudImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICPeriodicidad());

        return response;
    }

    // Service: DelPeriodicidadSolicitud

    /*
     * Entry point for service DelPeriodicidadSolicitud
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse delPeriodicidadSolicitud(
            com.keralty.aeconfiguraciones.viewmodel.periodicidad.siu.DelPeriodicidadSolicitudRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = periodicidadSrv.delPeriodicidadSolicitudTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service DelPeriodicidadSolicitud
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse delPeriodicidadSolicitudTxn(
            com.keralty.aeconfiguraciones.viewmodel.periodicidad.siu.DelPeriodicidadSolicitudRequest request) throws ModelException {
        request.setPeriodicidadDelPeriodicidadSolicitudpthisPeriodicidadInstance(periodicidadSrv.getByOIDWithHV(request.getPeriodicidadDelPeriodicidadSolicitudpthisPeriodicidad()));
        request.setPeriodicidadDelPeriodicidadSolicitudpevcSolicitudInstance(solicitudSrv.getByOIDWithHV(request.getPeriodicidadDelPeriodicidadSolicitudpevcSolicitud()));
        return periodicidadSrv.delPeriodicidadSolicitudImplementation(request, true);
    }

    /*
     * Implements service DelPeriodicidadSolicitud
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse delPeriodicidadSolicitudImplementation(
            com.keralty.aeconfiguraciones.viewmodel.periodicidad.siu.DelPeriodicidadSolicitudRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Periodicidad periodicidadDelPeriodicidadSolicitudpthisPeriodicidad = request.getPeriodicidadDelPeriodicidadSolicitudpthisPeriodicidadInstance();
        Solicitud periodicidadDelPeriodicidadSolicitudpevcSolicitud = request.getPeriodicidadDelPeriodicidadSolicitudpevcSolicitudInstance();

        request.checkArguments();
        Periodicidad instance = periodicidadDelPeriodicidadSolicitudpthisPeriodicidad;

        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.solicitud.siu.DelPeriodicidadSolicitudRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.solicitud.siu.DelPeriodicidadSolicitudRequest();
            relatedRequest.setSolicitudDelPeriodicidadSolicitudpthisSolicitudInstance(periodicidadDelPeriodicidadSolicitudpevcSolicitud);
            relatedRequest.setSolicitudDelPeriodicidadSolicitudpevcPeriodicidadInstance(periodicidadDelPeriodicidadSolicitudpthisPeriodicidad);
            solicitudSrv.delPeriodicidadSolicitudImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICPeriodicidad());

        return response;
    }

    // Service: CambPeriodicidadAnexoMat

    /*
     * Entry point for service CambPeriodicidadAnexoMat
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse cambPeriodicidadAnexoMat(
            com.keralty.aeconfiguraciones.viewmodel.periodicidad.siu.CambPeriodicidadAnexoMatRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = periodicidadSrv.cambPeriodicidadAnexoMatTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service CambPeriodicidadAnexoMat
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse cambPeriodicidadAnexoMatTxn(
            com.keralty.aeconfiguraciones.viewmodel.periodicidad.siu.CambPeriodicidadAnexoMatRequest request) throws ModelException {
        request.setPeriodicidadCambPeriodicidadAnexoMatpthisPeriodicidadInstance(periodicidadSrv.getByOIDWithHV(request.getPeriodicidadCambPeriodicidadAnexoMatpthisPeriodicidad()));
        request.setPeriodicidadCambPeriodicidadAnexoMatpevcAnexoMatInstance(anexoMatSrv.getByOIDWithHV(request.getPeriodicidadCambPeriodicidadAnexoMatpevcAnexoMat()));
        return periodicidadSrv.cambPeriodicidadAnexoMatImplementation(request, true);
    }

    /*
     * Implements service CambPeriodicidadAnexoMat
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse cambPeriodicidadAnexoMatImplementation(
            com.keralty.aeconfiguraciones.viewmodel.periodicidad.siu.CambPeriodicidadAnexoMatRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Periodicidad periodicidadCambPeriodicidadAnexoMatpthisPeriodicidad = request.getPeriodicidadCambPeriodicidadAnexoMatpthisPeriodicidadInstance();
        AnexoMat periodicidadCambPeriodicidadAnexoMatpevcAnexoMat = request.getPeriodicidadCambPeriodicidadAnexoMatpevcAnexoMatInstance();

        request.checkArguments();
        Periodicidad instance = periodicidadCambPeriodicidadAnexoMatpthisPeriodicidad;

        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.anexomat.siu.CambPeriodicidadAnexoMatRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.anexomat.siu.CambPeriodicidadAnexoMatRequest();
            relatedRequest.setAnexoMatCambPeriodicidadAnexoMatpthisAnexoMatInstance(periodicidadCambPeriodicidadAnexoMatpevcAnexoMat);
            relatedRequest.setAnexoMatCambPeriodicidadAnexoMatpevcPeriodicidadInstance(periodicidadCambPeriodicidadAnexoMatpthisPeriodicidad);
            anexoMatSrv.cambPeriodicidadAnexoMatImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICPeriodicidad());

        return response;
    }

    // Service: CambPeriodicidadAnexoNeo

    /*
     * Entry point for service CambPeriodicidadAnexoNeo
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse cambPeriodicidadAnexoNeo(
            com.keralty.aeconfiguraciones.viewmodel.periodicidad.siu.CambPeriodicidadAnexoNeoRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = periodicidadSrv.cambPeriodicidadAnexoNeoTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service CambPeriodicidadAnexoNeo
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse cambPeriodicidadAnexoNeoTxn(
            com.keralty.aeconfiguraciones.viewmodel.periodicidad.siu.CambPeriodicidadAnexoNeoRequest request) throws ModelException {
        request.setPeriodicidadCambPeriodicidadAnexoNeopthisPeriodicidadInstance(periodicidadSrv.getByOIDWithHV(request.getPeriodicidadCambPeriodicidadAnexoNeopthisPeriodicidad()));
        request.setPeriodicidadCambPeriodicidadAnexoNeopevcAnexoNeoInstance(anexoNeoSrv.getByOIDWithHV(request.getPeriodicidadCambPeriodicidadAnexoNeopevcAnexoNeo()));
        return periodicidadSrv.cambPeriodicidadAnexoNeoImplementation(request, true);
    }

    /*
     * Implements service CambPeriodicidadAnexoNeo
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse cambPeriodicidadAnexoNeoImplementation(
            com.keralty.aeconfiguraciones.viewmodel.periodicidad.siu.CambPeriodicidadAnexoNeoRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Periodicidad periodicidadCambPeriodicidadAnexoNeopthisPeriodicidad = request.getPeriodicidadCambPeriodicidadAnexoNeopthisPeriodicidadInstance();
        AnexoNeo periodicidadCambPeriodicidadAnexoNeopevcAnexoNeo = request.getPeriodicidadCambPeriodicidadAnexoNeopevcAnexoNeoInstance();

        request.checkArguments();
        Periodicidad instance = periodicidadCambPeriodicidadAnexoNeopthisPeriodicidad;

        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.anexoneo.siu.CambPeriodicidadAnexoNeoRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.anexoneo.siu.CambPeriodicidadAnexoNeoRequest();
            relatedRequest.setAnexoNeoCambPeriodicidadAnexoNeopthisAnexoNeoInstance(periodicidadCambPeriodicidadAnexoNeopevcAnexoNeo);
            relatedRequest.setAnexoNeoCambPeriodicidadAnexoNeopevcPeriodicidadInstance(periodicidadCambPeriodicidadAnexoNeopthisPeriodicidad);
            anexoNeoSrv.cambPeriodicidadAnexoNeoImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICPeriodicidad());

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
            com.keralty.aeconfiguraciones.viewmodel.periodicidad.siu.ModificarRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = periodicidadSrv.modificarTxn(request);
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
            com.keralty.aeconfiguraciones.viewmodel.periodicidad.siu.ModificarRequest request) throws ModelException {
        request.setPeriodicidadmodificarpthisPeriodicidadInstance(periodicidadSrv.getByOIDWithHV(request.getPeriodicidadmodificarpthisPeriodicidad()));
        return periodicidadSrv.modificarImplementation(request);
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
            com.keralty.aeconfiguraciones.viewmodel.periodicidad.siu.ModificarRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Periodicidad periodicidadmodificarpthisPeriodicidad = request.getPeriodicidadmodificarpthisPeriodicidadInstance();
        Boolean periodicidadmodificarpEsMensual = request.getPeriodicidadmodificarpEsMensual();

        request.checkArguments();
        Periodicidad instance = periodicidadmodificarpthisPeriodicidad;
        instance.setPeriodicidadEsMensual(periodicidadmodificarpEsMensual);


        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICPeriodicidad());

        return response;
    }

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
            com.keralty.aeconfiguraciones.viewmodel.periodicidad.siu.InsPeriodicidadRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = periodicidadSrv.insPeriodicidadTxn(request);
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
            com.keralty.aeconfiguraciones.viewmodel.periodicidad.siu.InsPeriodicidadRequest request) throws ModelException {
        request.setPeriodicidadInsPeriodicidadpthisPeriodicidadInstance(periodicidadSrv.getByOIDWithHV(request.getPeriodicidadInsPeriodicidadpthisPeriodicidad()));
        request.setPeriodicidadInsPeriodicidadpevcContratoCInstance(contratoCSrv.getByOIDWithHV(request.getPeriodicidadInsPeriodicidadpevcContratoC()));
        return periodicidadSrv.insPeriodicidadImplementation(request, true);
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
            com.keralty.aeconfiguraciones.viewmodel.periodicidad.siu.InsPeriodicidadRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Periodicidad periodicidadInsPeriodicidadpthisPeriodicidad = request.getPeriodicidadInsPeriodicidadpthisPeriodicidadInstance();
        ContratoC periodicidadInsPeriodicidadpevcContratoC = request.getPeriodicidadInsPeriodicidadpevcContratoCInstance();

        request.checkArguments();
        Periodicidad instance = periodicidadInsPeriodicidadpthisPeriodicidad;

        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.contratoc.siu.InsPeriodicidadRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.contratoc.siu.InsPeriodicidadRequest();
            relatedRequest.setContratoCInsPeriodicidadpthisContratoCInstance(periodicidadInsPeriodicidadpevcContratoC);
            relatedRequest.setContratoCInsPeriodicidadpevcPeriodicidadInstance(periodicidadInsPeriodicidadpthisPeriodicidad);
            contratoCSrv.insPeriodicidadImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICPeriodicidad());

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
            com.keralty.aeconfiguraciones.viewmodel.periodicidad.siu.DelPeriodicidadRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = periodicidadSrv.delPeriodicidadTxn(request);
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
            com.keralty.aeconfiguraciones.viewmodel.periodicidad.siu.DelPeriodicidadRequest request) throws ModelException {
        request.setPeriodicidadDelPeriodicidadpthisPeriodicidadInstance(periodicidadSrv.getByOIDWithHV(request.getPeriodicidadDelPeriodicidadpthisPeriodicidad()));
        request.setPeriodicidadDelPeriodicidadpevcContratoCInstance(contratoCSrv.getByOIDWithHV(request.getPeriodicidadDelPeriodicidadpevcContratoC()));
        return periodicidadSrv.delPeriodicidadImplementation(request, true);
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
            com.keralty.aeconfiguraciones.viewmodel.periodicidad.siu.DelPeriodicidadRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Periodicidad periodicidadDelPeriodicidadpthisPeriodicidad = request.getPeriodicidadDelPeriodicidadpthisPeriodicidadInstance();
        ContratoC periodicidadDelPeriodicidadpevcContratoC = request.getPeriodicidadDelPeriodicidadpevcContratoCInstance();

        request.checkArguments();
        Periodicidad instance = periodicidadDelPeriodicidadpthisPeriodicidad;

        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.contratoc.siu.DelPeriodicidadRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.contratoc.siu.DelPeriodicidadRequest();
            relatedRequest.setContratoCDelPeriodicidadpthisContratoCInstance(periodicidadDelPeriodicidadpevcContratoC);
            relatedRequest.setContratoCDelPeriodicidadpevcPeriodicidadInstance(periodicidadDelPeriodicidadpthisPeriodicidad);
            contratoCSrv.delPeriodicidadImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICPeriodicidad());

        return response;
    }

    // Service: InsPeriodicidadNueva

    /*
     * Entry point for service InsPeriodicidadNueva
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse insPeriodicidadNueva(
            com.keralty.aeconfiguraciones.viewmodel.periodicidad.siu.InsPeriodicidadNuevaRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = periodicidadSrv.insPeriodicidadNuevaTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service InsPeriodicidadNueva
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse insPeriodicidadNuevaTxn(
            com.keralty.aeconfiguraciones.viewmodel.periodicidad.siu.InsPeriodicidadNuevaRequest request) throws ModelException {
        request.setPeriodicidadInsPeriodicidadNuevapthisPeriodicidadInstance(periodicidadSrv.getByOIDWithHV(request.getPeriodicidadInsPeriodicidadNuevapthisPeriodicidad()));
        request.setPeriodicidadInsPeriodicidadNuevapevcSolicitudNovedadInstance(solicitudNovedadSrv.getByOIDWithHV(request.getPeriodicidadInsPeriodicidadNuevapevcSolicitudNovedad()));
        return periodicidadSrv.insPeriodicidadNuevaImplementation(request, true);
    }

    /*
     * Implements service InsPeriodicidadNueva
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse insPeriodicidadNuevaImplementation(
            com.keralty.aeconfiguraciones.viewmodel.periodicidad.siu.InsPeriodicidadNuevaRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Periodicidad periodicidadInsPeriodicidadNuevapthisPeriodicidad = request.getPeriodicidadInsPeriodicidadNuevapthisPeriodicidadInstance();
        SolicitudNovedad periodicidadInsPeriodicidadNuevapevcSolicitudNovedad = request.getPeriodicidadInsPeriodicidadNuevapevcSolicitudNovedadInstance();

        request.checkArguments();
        Periodicidad instance = periodicidadInsPeriodicidadNuevapthisPeriodicidad;

        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.solicitudnovedad.siu.InsPeriodicidadNuevaRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.solicitudnovedad.siu.InsPeriodicidadNuevaRequest();
            relatedRequest.setSolicitudNovedadInsPeriodicidadNuevapthisSolicitudNovedadInstance(periodicidadInsPeriodicidadNuevapevcSolicitudNovedad);
            relatedRequest.setSolicitudNovedadInsPeriodicidadNuevapevcPeriodicidadInstance(periodicidadInsPeriodicidadNuevapthisPeriodicidad);
            solicitudNovedadSrv.insPeriodicidadNuevaImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICPeriodicidad());

        return response;
    }

    // Service: DelPeriodicidadNueva

    /*
     * Entry point for service DelPeriodicidadNueva
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse delPeriodicidadNueva(
            com.keralty.aeconfiguraciones.viewmodel.periodicidad.siu.DelPeriodicidadNuevaRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = periodicidadSrv.delPeriodicidadNuevaTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service DelPeriodicidadNueva
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse delPeriodicidadNuevaTxn(
            com.keralty.aeconfiguraciones.viewmodel.periodicidad.siu.DelPeriodicidadNuevaRequest request) throws ModelException {
        request.setPeriodicidadDelPeriodicidadNuevapthisPeriodicidadInstance(periodicidadSrv.getByOIDWithHV(request.getPeriodicidadDelPeriodicidadNuevapthisPeriodicidad()));
        request.setPeriodicidadDelPeriodicidadNuevapevcSolicitudNovedadInstance(solicitudNovedadSrv.getByOIDWithHV(request.getPeriodicidadDelPeriodicidadNuevapevcSolicitudNovedad()));
        return periodicidadSrv.delPeriodicidadNuevaImplementation(request, true);
    }

    /*
     * Implements service DelPeriodicidadNueva
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse delPeriodicidadNuevaImplementation(
            com.keralty.aeconfiguraciones.viewmodel.periodicidad.siu.DelPeriodicidadNuevaRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Periodicidad periodicidadDelPeriodicidadNuevapthisPeriodicidad = request.getPeriodicidadDelPeriodicidadNuevapthisPeriodicidadInstance();
        SolicitudNovedad periodicidadDelPeriodicidadNuevapevcSolicitudNovedad = request.getPeriodicidadDelPeriodicidadNuevapevcSolicitudNovedadInstance();

        request.checkArguments();
        Periodicidad instance = periodicidadDelPeriodicidadNuevapthisPeriodicidad;

        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.solicitudnovedad.siu.DelPeriodicidadNuevaRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.solicitudnovedad.siu.DelPeriodicidadNuevaRequest();
            relatedRequest.setSolicitudNovedadDelPeriodicidadNuevapthisSolicitudNovedadInstance(periodicidadDelPeriodicidadNuevapevcSolicitudNovedad);
            relatedRequest.setSolicitudNovedadDelPeriodicidadNuevapevcPeriodicidadInstance(periodicidadDelPeriodicidadNuevapthisPeriodicidad);
            solicitudNovedadSrv.delPeriodicidadNuevaImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICPeriodicidad());

        return response;
    }

    // Service: InsPeriodicidadSolNov

    /*
     * Entry point for service InsPeriodicidadSolNov
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse insPeriodicidadSolNov(
            com.keralty.aeconfiguraciones.viewmodel.periodicidad.siu.InsPeriodicidadSolNovRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = periodicidadSrv.insPeriodicidadSolNovTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service InsPeriodicidadSolNov
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse insPeriodicidadSolNovTxn(
            com.keralty.aeconfiguraciones.viewmodel.periodicidad.siu.InsPeriodicidadSolNovRequest request) throws ModelException {
        request.setPeriodicidadInsPeriodicidadSolNovpthisPeriodicidadInstance(periodicidadSrv.getByOIDWithHV(request.getPeriodicidadInsPeriodicidadSolNovpthisPeriodicidad()));
        request.setPeriodicidadInsPeriodicidadSolNovpevcSolicitudNovedadInstance(solicitudNovedadSrv.getByOIDWithHV(request.getPeriodicidadInsPeriodicidadSolNovpevcSolicitudNovedad()));
        return periodicidadSrv.insPeriodicidadSolNovImplementation(request, true);
    }

    /*
     * Implements service InsPeriodicidadSolNov
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse insPeriodicidadSolNovImplementation(
            com.keralty.aeconfiguraciones.viewmodel.periodicidad.siu.InsPeriodicidadSolNovRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Periodicidad periodicidadInsPeriodicidadSolNovpthisPeriodicidad = request.getPeriodicidadInsPeriodicidadSolNovpthisPeriodicidadInstance();
        SolicitudNovedad periodicidadInsPeriodicidadSolNovpevcSolicitudNovedad = request.getPeriodicidadInsPeriodicidadSolNovpevcSolicitudNovedadInstance();

        request.checkArguments();
        Periodicidad instance = periodicidadInsPeriodicidadSolNovpthisPeriodicidad;

        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.solicitudnovedad.siu.InsPeriodicidadSolNovRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.solicitudnovedad.siu.InsPeriodicidadSolNovRequest();
            relatedRequest.setSolicitudNovedadInsPeriodicidadSolNovpthisSolicitudNovedadInstance(periodicidadInsPeriodicidadSolNovpevcSolicitudNovedad);
            relatedRequest.setSolicitudNovedadInsPeriodicidadSolNovpevcPeriodicidadInstance(periodicidadInsPeriodicidadSolNovpthisPeriodicidad);
            solicitudNovedadSrv.insPeriodicidadSolNovImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICPeriodicidad());

        return response;
    }

    // Service: DelPeriodicidadSolNov

    /*
     * Entry point for service DelPeriodicidadSolNov
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse delPeriodicidadSolNov(
            com.keralty.aeconfiguraciones.viewmodel.periodicidad.siu.DelPeriodicidadSolNovRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = periodicidadSrv.delPeriodicidadSolNovTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service DelPeriodicidadSolNov
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse delPeriodicidadSolNovTxn(
            com.keralty.aeconfiguraciones.viewmodel.periodicidad.siu.DelPeriodicidadSolNovRequest request) throws ModelException {
        request.setPeriodicidadDelPeriodicidadSolNovpthisPeriodicidadInstance(periodicidadSrv.getByOIDWithHV(request.getPeriodicidadDelPeriodicidadSolNovpthisPeriodicidad()));
        request.setPeriodicidadDelPeriodicidadSolNovpevcSolicitudNovedadInstance(solicitudNovedadSrv.getByOIDWithHV(request.getPeriodicidadDelPeriodicidadSolNovpevcSolicitudNovedad()));
        return periodicidadSrv.delPeriodicidadSolNovImplementation(request, true);
    }

    /*
     * Implements service DelPeriodicidadSolNov
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse delPeriodicidadSolNovImplementation(
            com.keralty.aeconfiguraciones.viewmodel.periodicidad.siu.DelPeriodicidadSolNovRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Periodicidad periodicidadDelPeriodicidadSolNovpthisPeriodicidad = request.getPeriodicidadDelPeriodicidadSolNovpthisPeriodicidadInstance();
        SolicitudNovedad periodicidadDelPeriodicidadSolNovpevcSolicitudNovedad = request.getPeriodicidadDelPeriodicidadSolNovpevcSolicitudNovedadInstance();

        request.checkArguments();
        Periodicidad instance = periodicidadDelPeriodicidadSolNovpthisPeriodicidad;

        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.solicitudnovedad.siu.DelPeriodicidadSolNovRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.solicitudnovedad.siu.DelPeriodicidadSolNovRequest();
            relatedRequest.setSolicitudNovedadDelPeriodicidadSolNovpthisSolicitudNovedadInstance(periodicidadDelPeriodicidadSolNovpevcSolicitudNovedad);
            relatedRequest.setSolicitudNovedadDelPeriodicidadSolNovpevcPeriodicidadInstance(periodicidadDelPeriodicidadSolNovpthisPeriodicidad);
            solicitudNovedadSrv.delPeriodicidadSolNovImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICPeriodicidad());

        return response;
    }


    private void suppInfo4ICPeriodicidad(String searchText, SQLSelect select) {
        try {
            SQLWhereDisjunction disjunction = new SQLWhereDisjunction();

            SQLWhereComparison comparison1 = new SQLWhereComparison(select);
            comparison1.setOperator("LIKE");
            comparison1.setFirstOperand(new SQLWhereOperandPathAttribute(PeriodicidadConstants.ATTR_NAME_PERIODICIDADDESCRIPCION, Constants.Type.STRING.getTypeName()));
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
        applyOCOCPeriodicidad(queryRequest, select);
        applyDSItemSort(queryRequest, select);
        applyDefaultOrderCriteria(queryRequest, select);
    }

    private void applyDefaultOrderCriteria(QueryRequest queryRequest, SQLSelect select) {
        if (queryRequest.getOrderCriteriaName() != null && !queryRequest.getOrderCriteriaName().isBlank())
            return;
        if (queryRequest.getDsItemSort() != null && !queryRequest.getDsItemSort().isBlank())
            return;
        select.getOrderByPart().addItem(Constants.normalizePath(PeriodicidadConstants.CLASS_NAME, PeriodicidadConstants.ATTR_FIELD_PERIODICIDADCODIGO, false), true);
    }


    private void applyOCOCPeriodicidad(QueryRequest queryRequest, SQLSelect select) {
        if ("OC_Periodicidad".equalsIgnoreCase(queryRequest.getOrderCriteriaName())) {
            select.getOrderByPart().addItem(Constants.normalizePath(PeriodicidadConstants.CLASS_NAME, PeriodicidadConstants.ATTR_NAME_PERIODICIDADDESCRIPCION, false), true);
        }
    }
    
    @Override
    protected AbstractAppEntity buildRelatedInstance(String relatedInstanceJsonOid, String relatedInstancePathFirstItem, String remainingPath) {
        AbstractAppEntity relatedInstance = null;
        if (relatedInstancePathFirstItem.isEmpty()) {
            relatedInstance = buildPeriodicidadRelatedInstance(relatedInstanceJsonOid);
        }
        if (PeriodicidadConstants.ROLE_NAME_DOMINIOS.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildPeriodicidadRelatedInstance4Dominio(relatedInstanceJsonOid, remainingPath);
        }
        if (PeriodicidadConstants.ROLE_NAME_SOLICITUDES.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildPeriodicidadRelatedInstance4Solicitud(relatedInstanceJsonOid, remainingPath);
        }
        if (PeriodicidadConstants.ROLE_NAME_ANEXOSMAT.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildPeriodicidadRelatedInstance4AnexoMat(relatedInstanceJsonOid, remainingPath);
        }
        if (PeriodicidadConstants.ROLE_NAME_ANEXOSNEO.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildPeriodicidadRelatedInstance4AnexoNeo(relatedInstanceJsonOid, remainingPath);
        }
        if (PeriodicidadConstants.ROLE_NAME_CONTRATOSC.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildPeriodicidadRelatedInstance4ContratoC(relatedInstanceJsonOid, remainingPath);
        }
        if (PeriodicidadConstants.ROLE_NAME_ANEXOSC.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildPeriodicidadRelatedInstance4AnexoC(relatedInstanceJsonOid, remainingPath);
        }
        if (PeriodicidadConstants.ROLE_NAME_SOLICITUDESNOVEDAD.equalsIgnoreCase(relatedInstancePathFirstItem) || PeriodicidadConstants.ROLE_NAME_SOLICITUDESNOVEDADNUEVA.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildPeriodicidadRelatedInstance4SolicitudNovedad(relatedInstanceJsonOid, remainingPath);
        }
        return relatedInstance;
    }
    
    private AbstractAppEntity buildPeriodicidadRelatedInstance(String relatedInstanceJsonOid) {
    	ObjectMapper mapper = new ObjectMapper();
    	try {
    		return new Periodicidad(mapper.readValue(relatedInstanceJsonOid, PeriodicidadOid.class));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            throw new SystemException(e.getMessage());
    	}
    }

    private AbstractAppEntity buildPeriodicidadRelatedInstance4Dominio(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new Dominio(mapper.readValue(relatedInstanceJsonOid, DominioOid.class)): Dominio.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            throw new SystemException(e.getMessage());
    	}
    }

    private AbstractAppEntity buildPeriodicidadRelatedInstance4Solicitud(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new Solicitud(mapper.readValue(relatedInstanceJsonOid, SolicitudOid.class)): Solicitud.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            throw new SystemException(e.getMessage());
    	}
    }

    private AbstractAppEntity buildPeriodicidadRelatedInstance4AnexoMat(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new AnexoMat(mapper.readValue(relatedInstanceJsonOid, AnexoMatOid.class)): AnexoMat.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            throw new SystemException(e.getMessage());
    	}
    }

    private AbstractAppEntity buildPeriodicidadRelatedInstance4AnexoNeo(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new AnexoNeo(mapper.readValue(relatedInstanceJsonOid, AnexoNeoOid.class)): AnexoNeo.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            throw new SystemException(e.getMessage());
    	}
    }

    private AbstractAppEntity buildPeriodicidadRelatedInstance4ContratoC(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new ContratoC(mapper.readValue(relatedInstanceJsonOid, ContratoCOid.class)): ContratoC.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            throw new SystemException(e.getMessage());
    	}
    }

    private AbstractAppEntity buildPeriodicidadRelatedInstance4AnexoC(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new AnexoC(mapper.readValue(relatedInstanceJsonOid, AnexoCOid.class)): AnexoC.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            throw new SystemException(e.getMessage());
    	}
    }

    private AbstractAppEntity buildPeriodicidadRelatedInstance4SolicitudNovedad(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new SolicitudNovedad(mapper.readValue(relatedInstanceJsonOid, SolicitudNovedadOid.class)): SolicitudNovedad.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            throw new SystemException(e.getMessage());
    	}
    }

    @Override
    protected void applyFilterByOid(String jsonOid, SQLSelect sql) {
        try {
            PeriodicidadOid oid = new PeriodicidadOid().fromJsonString(jsonOid);
            SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
            comparison1.setOperator("=");
            comparison1.setFirstOperand(new SQLWhereOperandPathAttribute(PeriodicidadConstants.ATTR_FIELD_PERIODICIDADCODIGO, Constants.Type.INT.getTypeName()));
            comparison1.setSecondOperand(new SQLWhereOperandLiteral(oid.getPeriodicidadCodigo(), Constants.Type.INT.getTypeName()));
            sql.addComparison(comparison1);
        } catch (Exception e) {
            LoggerFactory.getLogger(PeriodicidadService.class).error(e.getMessage());
            throw new SystemException(e.getMessage());
        }
    }

}
